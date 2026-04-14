const API_BASE = 'http://localhost:8080/api';

// State
let users = [];
let currentUser = null;
let pizze = [];
let extra = [];
let cart = {
    pizze: [],
    extra: [],
    total: 0
};

// DOM Elements
const userSelect = document.getElementById('user-select');
const mainContent = document.getElementById('main-content');
const welcomeMessage = document.getElementById('welcome-message');
const tabShop = document.getElementById('tab-shop');
const tabQuests = document.getElementById('tab-quests');
const tabAdmin = document.getElementById('tab-admin');
const viewShop = document.getElementById('view-shop');
const viewQuests = document.getElementById('view-quests');
const viewAdmin = document.getElementById('view-admin');

// Icons for states
const STATE_ICONS = {
    'IN_ATTESA': '⏳',
    'IN_PREPARAZIONE': '🔥',
    'IN_CONSEGNA': '🏃',
    'CONSEGNATO': '💰',
    'ANNULLATO': '💀'
};

const STATE_NAMES = {
    'IN_ATTESA': 'In Attesa',
    'IN_PREPARAZIONE': 'In Preparazione',
    'IN_CONSEGNA': 'In Consegna',
    'CONSEGNATO': 'Consegnato',
    'ANNULLATO': 'Annullato'
};

// Init
async function init() {
    await fetchUsers();
    await fetchCatalog();
    setupEventListeners();
}

async function fetchUsers() {
    try {
        const res = await axios.get(`${API_BASE}/utenti`);
        users = res.data;
        userSelect.innerHTML = '<option value="">Seleziona Eroe...</option>';
        users.forEach(u => {
            const opt = document.createElement('option');
            opt.value = u.id;
            opt.textContent = `${u.nome} ${u.admin ? '[GM]' : ''}`;
            userSelect.appendChild(opt);
        });
    } catch (e) {
        console.error('Failed to fetch users', e);
        userSelect.innerHTML = '<option value="">Errore locanda disconnessa</option>';
    }
}

async function fetchCatalog() {
    try {
        const [resPizze, resExtra] = await Promise.all([
            axios.get(`${API_BASE}/pizze`),
            axios.get(`${API_BASE}/extra`)
        ]);
        pizze = resPizze.data;
        extra = resExtra.data;
        renderCatalog();
    } catch (e) {
        console.error('Failed to fetch catalog', e);
    }
}

function setupEventListeners() {
    userSelect.addEventListener('change', (e) => {
        const id = e.target.value;
        if (!id) {
            currentUser = null;
            mainContent.style.display = 'none';
            welcomeMessage.style.display = 'block';
            return;
        }
        currentUser = users.find(u => u.id == id);
        mainContent.style.display = 'block';
        welcomeMessage.style.display = 'none';
        
        tabAdmin.classList.toggle('hidden', !currentUser.admin);
        switchTab('shop');
        
        // Reset cart on user switch for safety
        cart = { pizze: [], extra: [], total: 0 };
        renderCart();
    });

    tabShop.addEventListener('click', () => switchTab('shop'));
    tabQuests.addEventListener('click', () => {
        switchTab('quests');
        loadUserOrders();
    });
    tabAdmin.addEventListener('click', () => {
        switchTab('admin');
        loadAllOrders();
    });

    document.getElementById('btn-checkout').addEventListener('click', placeOrder);
}

function switchTab(tabId) {
    [tabShop, tabQuests, tabAdmin].forEach(b => b.classList.remove('active'));
    viewShop.classList.add('hidden');
    viewQuests.classList.add('hidden');
    viewAdmin.classList.add('hidden');

    if (tabId === 'shop') {
        tabShop.classList.add('active');
        viewShop.classList.remove('hidden');
    } else if (tabId === 'quests') {
        tabQuests.classList.add('active');
        viewQuests.classList.remove('hidden');
    } else if (tabId === 'admin') {
        tabAdmin.classList.add('active');
        viewAdmin.classList.remove('hidden');
    }
}

function renderCatalog() {
    const pList = document.getElementById('pizze-list');
    const eList = document.getElementById('extra-list');
    
    if(pizze.length === 0) pList.innerHTML = '<p class="text-rpg-parchment">Le scorte sono terminate...</p>';
    else pList.innerHTML = pizze.map(p => `
        <div class="pixel-box bg-rpg-parchment p-5 pixel-item-card flex flex-col justify-between overflow-hidden relative">
            <div class="absolute -right-4 -top-4 opacity-10 text-6xl">🍕</div>
            <div class="relative z-10">
                <h3 class="font-bold text-sm border-b-2 border-dashed border-gray-400 pb-2">🍕 ${p.nome}</h3>
                <p class="text-[10px] mt-3 text-gray-700 leading-relaxed min-h-[40px]">${p.descrizione || 'Un potenziamento misterioso.'}</p>
            </div>
            <div class="flex justify-between items-center mt-5 pt-3 relative z-10">
                <span class="text-rpg-gold font-bold drop-shadow-[1px_1px_0_#1e1e1e] text-base">${p.prezzo}G</span>
                <button onclick="addToCart('pizza', ${p.id})" class="pixel-btn text-[9px] hover:bg-rpg-green hover:text-white">Equipaggia</button>
            </div>
        </div>
    `).join('');

    if(extra.length === 0) eList.innerHTML = '<p class="text-rpg-parchment">Nessun consumabile disponibile.</p>';
    else eList.innerHTML = extra.map(e => `
        <div class="pixel-box bg-rpg-parchment p-4 pixel-item-card flex justify-between items-center relative overflow-hidden">
            <div class="absolute -right-2 -top-2 opacity-10 text-4xl">🧪</div>
            <div class="relative z-10 w-full flex justify-between items-center">
                <h3 class="font-bold text-[11px]">🧪 ${e.nome}</h3>
                <div class="flex items-center gap-4">
                    <span class="text-rpg-gold font-bold text-xs drop-shadow-[1px_1px_0_#1e1e1e]">${e.prezzo}G</span>
                    <button onclick="addToCart('extra', ${e.id})" class="pixel-btn text-[#1e1e1e] py-1 px-2 text-[12px] bg-blue-300">+</button>
                </div>
            </div>
        </div>
    `).join('');
}

function addToCart(type, id) {
    if (type === 'pizza') {
        const item = pizze.find(p => p.id === id);
        cart.pizze.push(item);
        cart.total += item.prezzo;
    } else {
        const item = extra.find(e => e.id === id);
        cart.extra.push(item);
        cart.total += item.prezzo;
    }
    renderCart();
}

// Make globally accessible
window.addToCart = addToCart;

function removeFromCart(type, index) {
    if (type === 'pizza') {
        cart.total -= cart.pizze[index].prezzo;
        cart.pizze.splice(index, 1);
    } else {
        cart.total -= cart.extra[index].prezzo;
        cart.extra.splice(index, 1);
    }
    // Fix JS floating point issues
    cart.total = Math.round(cart.total * 100) / 100;
    renderCart();
}

window.removeFromCart = removeFromCart;

function renderCart() {
    const cList = document.getElementById('cart-list');
    const totalEl = document.getElementById('cart-total');
    const btnCheckout = document.getElementById('btn-checkout');
    const msg = document.getElementById('checkout-msg');
    
    msg.classList.add('hidden');
    
    if (cart.pizze.length === 0 && cart.extra.length === 0) {
        cList.innerHTML = '<li class="text-center text-gray-500 my-8 shadow-none border-none">Vuoto, serve oro!</li>';
        btnCheckout.disabled = true;
    } else {
        let html = '';
        cart.pizze.forEach((p, idx) => {
            html += `<li class="flex justify-between items-center border-b-[2px] border-dotted border-gray-400 pb-2">
                <span class="truncate pr-2">🗡️ ${p.nome}</span>
                <div class="flex items-center gap-3 flex-shrink-0">
                    <span class="text-rpg-wood font-bold">${p.prezzo}G</span>
                    <button onclick="removeFromCart('pizza', ${idx})" class="text-red-500 hover:text-white hover:bg-red-500 rounded px-1 transition-colors text-lg leading-none">&times;</button>
                </div>
            </li>`;
        });
        cart.extra.forEach((e, idx) => {
            html += `<li class="flex justify-between items-center border-b-[2px] border-dotted border-gray-400 pb-2">
                <span class="truncate pr-2">🧪 ${e.nome}</span>
                <div class="flex items-center gap-3 flex-shrink-0">
                    <span class="text-rpg-wood font-bold">${e.prezzo}G</span>
                    <button onclick="removeFromCart('extra', ${idx})" class="text-red-500 hover:text-white hover:bg-red-500 rounded px-1 transition-colors text-lg leading-none">&times;</button>
                </div>
            </li>`;
        });
        cList.innerHTML = html;
        btnCheckout.disabled = false;
    }
    
    totalEl.textContent = cart.total.toFixed(2);
}

async function placeOrder() {
    const btn = document.getElementById('btn-checkout');
    btn.disabled = true;
    btn.textContent = 'Trattativa...';
    
    const payload = {
        utente: { id: currentUser.id },
        pizze: cart.pizze.map(p => ({ id: p.id })),
        extra: cart.extra.map(e => ({ id: e.id })),
        stato: 'IN_ATTESA'
    };

    try {
        await axios.post(`${API_BASE}/ordini`, payload);
        
        // Reset cart
        cart = { pizze: [], extra: [], total: 0 };
        renderCart();
        
        const msg = document.getElementById('checkout-msg');
        msg.textContent = 'Transazione completata! Check Quest Log.';
        msg.classList.remove('hidden', 'text-red-600');
        msg.classList.add('text-green-700');
        
        btn.textContent = 'Acquista';
    } catch (e) {
        console.error('Checkout failed', e);
        const msg = document.getElementById('checkout-msg');
        msg.textContent = 'ERRORE: I troll hanno rubato i rifornimenti.';
        msg.classList.remove('hidden', 'text-green-700');
        msg.classList.add('text-red-600');
        btn.textContent = 'Acquista';
        btn.disabled = false;
    }
}

// User Orders
async function loadUserOrders() {
    if (!currentUser) return;
    const list = document.getElementById('quest-list');
    list.innerHTML = '<p class="text-center text-rpg-parchment">Consultando gli archivi...</p>';
    try {
        const res = await axios.get(`${API_BASE}/ordini/utente/${currentUser.id}`);
        const ordini = res.data.sort((a,b) => b.id - a.id);
        
        if (ordini.length === 0) {
            list.innerHTML = '<p class="text-center text-rpg-parchment py-8">Nessuna missione in corso. Vai allo shop!</p>';
            return;
        }
        
        list.innerHTML = ordini.map(o => `
            <div class="pixel-box bg-rpg-parchment border-[4px] border-rpg-border p-6 flex flex-col md:flex-row justify-between items-start md:items-center gap-6">
                <div class="flex-1">
                    <h3 class="font-bold flex items-center gap-3 border-b-[4px] border-rpg-border pb-2 mb-3">
                        <span class="text-3xl drop-shadow-[2px_2px_0_rgba(0,0,0,0.2)]">${STATE_ICONS[o.stato] || '📜'}</span>
                        <span class="text-lg">Quest #${o.id}</span>
                    </h3>
                    <p class="text-[9px] text-gray-500 mb-4 bg-gray-200 inline-block px-2 py-1 rounded">
                        Accettata: ${new Date(o.dataOra).toLocaleString()}
                    </p>
                    <div class="text-[10px] flex gap-2 flex-wrap text-[#1e1e1e]">
                        ${o.pizze.map(p => `<span class="bg-orange-200 px-2 py-1 border-2 border-orange-400">🗡️ ${p.nome}</span>`).join('')}
                        ${o.extra.map(e => `<span class="bg-blue-200 px-2 py-1 border-2 border-blue-400">🧪 ${e.nome}</span>`).join('')}
                    </div>
                </div>
                <div class="pixel-box bg-[#1e1e1e] text-rpg-parchment px-4 py-3 text-center min-w-[140px] text-[10px] transform rotate-1 drop-shadow-md">
                    ${STATE_ICONS[o.stato] || ''} <br><div class="mt-2 text-xs">${STATE_NAMES[o.stato] || o.stato}</div>
                </div>
            </div>
        `).join('');
    } catch (e) {
        list.innerHTML = '<p class="text-red-400 text-center py-8">Errore magico nel caricamento delle quest.</p>';
    }
}

// Admin / GM View
async function loadAllOrders() {
    const tbody = document.getElementById('admin-orders-list');
    tbody.innerHTML = '<tr><td colspan="6" class="text-center py-8 text-lg">Scrying the realm... 🔮</td></tr>';
    try {
        const res = await axios.get(`${API_BASE}/ordini`);
        const ordini = res.data.sort((a,b) => b.id - a.id);
        
        if (ordini.length === 0) {
            tbody.innerHTML = '<tr><td colspan="6" class="text-center py-8 text-lg">Il reame è in pace. Nessun ordine.</td></tr>';
            return;
        }

        const stati = ['IN_ATTESA', 'IN_PREPARAZIONE', 'IN_CONSEGNA', 'CONSEGNATO', 'ANNULLATO'];
        
        tbody.innerHTML = ordini.map(o => `
            <tr class="border-b-[4px] border-rpg-border hover:bg-[#eaddc5] transition-colors">
                <td class="p-4 font-bold">#${o.id}</td>
                <td class="p-4 font-bold text-rpg-green">
                    ${o.utente ? o.utente.nome : '<span class="text-gray-400">?</span>'}
                </td>
                <td class="p-4">
                    <div class="flex flex-col gap-1 text-[9px]">
                        <span class="bg-orange-200 px-1 border border-orange-400 w-fit">🗡️ Pizze: ${o.pizze.length}</span>
                        <span class="bg-blue-200 px-1 border border-blue-400 w-fit">🧪 Extra: ${o.extra.length}</span>
                    </div>
                </td>
                <td class="p-4 text-[9px] text-gray-600">${new Date(o.dataOra).toLocaleString()}</td>
                <td class="p-4">
                    <div class="flex items-center gap-2">
                        <span class="text-xl drop-shadow-[1px_1px_0_#1e1e1e]">${STATE_ICONS[o.stato] || ''}</span>
                        <span>${STATE_NAMES[o.stato] || o.stato}</span>
                    </div>
                </td>
                <td class="p-4 text-center">
                    <select class="pixel-select p-2 bg-white cursor-pointer w-full" onchange="changeOrderStatus(${o.id}, this.value, event)">
                        <option value="" disabled selected>Cambia...</option>
                        ${stati.filter(s => s !== o.stato).map(s => `<option value="${s}">${STATE_ICONS[s] || ''} ${STATE_NAMES[s]}</option>`).join('')}
                    </select>
                </td>
            </tr>
        `).join('');
    } catch (e) {
        tbody.innerHTML = '<tr><td colspan="6" class="text-red-500 font-bold text-center py-8">Impossibile consultare gli archivi divini. Errore API.</td></tr>';
    }
}

// Cambia stato ordine (Per Admin)
window.changeOrderStatus = async function(id, nuovoStato, event) {
    const sel = event.target;
    const oldVal = sel.value;
    sel.disabled = true;
    
    try {
        const resGet = await axios.get(`${API_BASE}/ordini/${id}`);
        const ordine = resGet.data;
        
        ordine.stato = nuovoStato;
        
        await axios.post(`${API_BASE}/ordini`, ordine);
        
        // Show brief a popup or notification would be nice, but just reloading is enough
        loadAllOrders(); // Reload the list
    } catch (e) {
        console.error('Failed to change status', e);
        alert('Magia Fallita! Impossibile aggiornare lo stato della quest.');
        sel.disabled = false;
        sel.value = ''; // Reset selection
    }
}

document.addEventListener("DOMContentLoaded", init);
