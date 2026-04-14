const API_URL = 'http://localhost:8080/todos';

// State management
let tasks = [];
let currentFilter = 'ALL';
let searchQuery = '';
let currentPriority = 1;

// DOM Elements
const taskGrid = document.getElementById('taskGrid');
const loadingIndicator = document.getElementById('loadingIndicator');
const emptyState = document.getElementById('emptyState');
const searchInput = document.getElementById('searchInput');

// Monochrome icons for states
const stateConfig = {
    'TODO': { label: 'Da Fare', icon: 'fa-regular fa-circle text-gray-400' },
    'IN_PROGRESS': { label: 'In Corso', icon: 'fa-solid fa-spinner fa-spin text-blue-500' },
    'DONE': { label: 'Completato', icon: 'fa-regular fa-circle-check text-gray-400' },
    'CANCELLED': { label: 'Annullato', icon: 'fa-regular fa-circle-xmark text-gray-400' }
};

document.addEventListener('DOMContentLoaded', () => {
    fetchTasks();
    setPriority(1);
    
    let debounceTimer;
    searchInput.addEventListener('input', (e) => {
        clearTimeout(debounceTimer);
        debounceTimer = setTimeout(() => {
            searchQuery = e.target.value.trim();
            fetchTasks();
        }, 300);
    });
});

function setPriority(prio) {
    if (prio < 1 || prio > 3) return;
    currentPriority = prio;
    
    const inactiveClass = "xp-button xp-button-classic";
    const activeClass = "xp-button xp-button-classic xp-inset bg-[#cfcbd6] font-bold";

    for (let i = 1; i <= 3; i++) {
        const btn = document.getElementById(`prio-${i}`);
        if(btn) btn.className = inactiveClass;
    }
    
    const activeBtn = document.getElementById(`prio-${prio}`);
    if(activeBtn) activeBtn.className = activeClass;
}

function showXPDialog(message, type = 'confirm') {
    return new Promise((resolve) => {
        const overlay = document.createElement('div');
        overlay.className = 'xp-modal-overlay';
        
        const dialog = document.createElement('div');
        dialog.className = 'xp-dialog';
        
        const icon = type === 'confirm' ? 'fa-circle-question' : 'fa-circle-info';
        const titleText = type === 'confirm' ? 'Confirm' : 'Information';
        
        dialog.innerHTML = `
            <div class="xp-title-bar">
                <div class="xp-title-text">${titleText}</div>
                <div class="xp-close-btn" id="dialogClose">✕</div>
            </div>
            <div class="xp-dialog-body">
                <div class="xp-dialog-icon">
                    <i class="fa-solid ${icon}"></i>
                </div>
                <div class="xp-dialog-message">${message}</div>
            </div>
            <div class="xp-dialog-footer">
                <button class="xp-button xp-button-classic xp-dialog-btn" id="dialogOk">OK</button>
                ${type === 'confirm' ? '<button class="xp-button xp-button-classic xp-dialog-btn" id="dialogCancel">Annulla</button>' : ''}
            </div>
        `;
        
        overlay.appendChild(dialog);
        document.body.appendChild(overlay);
        
        const close = (val) => {
            overlay.remove();
            resolve(val);
        };
        
        document.getElementById('dialogOk').onclick = () => close(true);
        if (type === 'confirm') {
            document.getElementById('dialogCancel').onclick = () => close(false);
        }
        document.getElementById('dialogClose').onclick = () => close(false);
    });
}

function showNotification(message, type = 'success') {
    const container = document.getElementById('notification-area');
    const note = document.createElement('div');
    
    const iconClass = type === 'success' ? 'fa-circle-info text-blue-600' : 'fa-triangle-exclamation text-red-600';
    const title = type === 'success' ? 'TaskMaster Pro' : 'Attenzione';
    
    note.className = `xp-balloon notification-enter`;
    note.innerHTML = `
        <div class="xp-balloon-inner">
            <div class="xp-balloon-close" onclick="this.parentElement.parentElement.remove()">✕</div>
            <div class="flex items-center gap-2 mb-1">
                <i class="fa-solid ${iconClass}"></i>
                <span class="font-bold">${title}</span>
            </div>
            <p>${message}</p>
        </div>
    `;
    
    container.appendChild(note);
    
    setTimeout(() => {
        if(note.parentElement) {
            note.classList.add('notification-exit');
            setTimeout(() => note.remove(), 300);
        }
    }, 5000);
}

function setFilter(filter) {
    currentFilter = filter;
    
    document.querySelectorAll('.xp-tab').forEach(btn => {
        btn.classList.remove('xp-tab-active');
    });
    
    const activeTab = document.getElementById(`filter-${filter}`);
    if(activeTab) activeTab.classList.add('xp-tab-active');
    
    fetchTasks();
}

async function fetchTasks() {
    try {
        setLoading(true);
        
        let url = API_URL;
        const params = new URLSearchParams();
        
        if (currentFilter !== 'ALL') params.append('stato', currentFilter);
        if (searchQuery !== '') params.append('search', searchQuery);
        
        if (params.toString()) url += `?${params.toString()}`;

        const response = await axios.get(url);
        tasks = response.data;
        
        renderTasks();
    } catch (error) {
        console.error("Fetch error:", error);
        showNotification("Errore nel caricamento task", "error");
    } finally {
        setLoading(false);
    }
}

async function createTask() {
    const descInput = document.getElementById('newTaskDesc');
    const desc = descInput.value.trim();
    
    if (!desc) {
        showNotification("Inserisci la descrizione", "error");
        return;
    }
    
    try {
        await axios.post(API_URL, {
            descrizione: desc,
            priorita: currentPriority
        });
        
        descInput.value = '';
        setPriority(1);
        fetchTasks();
        showNotification("Task aggiunto");
    } catch (error) {
        console.error("Create error:", error);
        showNotification("Errore elaborazione richiesta", "error");
    }
}

async function updateTaskStatus(id, newStatus) {
    try {
        const currentTask = tasks.find(t => t.id === id);
        if(!currentTask) return;
        
        const response = await axios.put(`${API_URL}/${id}`, {
            descrizione: currentTask.descrizione,
            priorita: currentTask.priorita,
            stato: newStatus
        });
        
        if (typeof response.data === 'string' && response.data.includes("non consentita")) {
             showNotification(response.data, "error");
             return;
        }

        fetchTasks();
    } catch (error) {
        console.error("Update error:", error);
        showNotification("Errore aggiornamento", "error");
    }
}

async function deleteTask(id) {
    const confirmed = await showXPDialog("Sicuro di voler eliminare questo task?");
    if (!confirmed) return;
    
    try {
        await axios.delete(`${API_URL}/${id}`);
        fetchTasks();
    } catch (error) {
        console.error("Delete error:", error);
        showNotification("Errore eliminazione", "error");
    }
}

function setLoading(isLoading) {
    if (isLoading) {
        loadingIndicator.classList.remove('hidden');
        taskGrid.classList.add('hidden');
        emptyState.classList.add('hidden');
    } else {
        loadingIndicator.classList.add('hidden');
        if (tasks.length === 0) {
            emptyState.classList.remove('hidden');
            emptyState.classList.add('flex');
            taskGrid.classList.add('hidden');
        } else {
            emptyState.classList.add('hidden');
            emptyState.classList.remove('flex');
            taskGrid.classList.remove('hidden');
            taskGrid.classList.add('grid');
        }
    }
}

function renderTasks() {
    taskGrid.innerHTML = '';
    
    tasks.forEach((task) => {
        const stateInfo = stateConfig[task.stato];
        const isCompletedOrCancelled = task.stato === 'DONE' || task.stato === 'CANCELLED';
        
        const card = document.createElement('div');
        card.className = "task-card-xp xp-outset shadow-sm";
        if (isCompletedOrCancelled) card.style.opacity = "0.7";
        
        // Actions
        let actionButtonsHtml = '';
        
        if (task.stato === 'TODO') {
            actionButtonsHtml += `<button onclick="updateTaskStatus(${task.id}, 'IN_PROGRESS')" class="xp-button xp-button-classic text-[13px] py-1 px-3"><i class="fa-solid fa-play scale-75"></i> Vai</button>`;
        } else if (task.stato === 'IN_PROGRESS') {
            actionButtonsHtml += `<button onclick="updateTaskStatus(${task.id}, 'DONE')" class="xp-button xp-button-classic text-[13px] py-1 px-3 text-green-700 font-bold"><i class="fa-solid fa-check scale-75"></i> OK</button>`;
        }
        
        if (task.stato === 'TODO' || task.stato === 'IN_PROGRESS') {
             actionButtonsHtml += `<button onclick="updateTaskStatus(${task.id}, 'CANCELLED')" class="xp-button xp-button-classic text-[13px] py-1 px-3" title="Annulla"><i class="fa-solid fa-ban scale-75"></i> No</button>`;
        }

        const deleteBtn = `<button onclick="deleteTask(${task.id})" class="xp-button xp-button-classic text-[13px] py-1 px-3 ml-auto" title="Elimina"><i class="fa-regular fa-trash-can scale-75 text-red-600"></i></button>`;
        
        let strikeClass = isCompletedOrCancelled ? 'line-through text-gray-500' : 'text-black';

        // Priority Banner Label
        let prioLabel = '';
        let prioBorderColor = '';
        if (task.priorita === 1) { prioLabel = 'ALTA'; prioBorderColor = '#ff0000'; }
        else if (task.priorita === 2) { prioLabel = 'MEDIA'; prioBorderColor = '#ff9900'; }
        else { prioLabel = 'BASSA'; prioBorderColor = '#008000'; }

        card.innerHTML = `
            <div class="task-card-title flex justify-between items-center px-2 py-1">
                <span class="text-[12px]">Task #${task.id}</span>
                <span class="text-[12px] uppercase font-bold text-white/90">${stateInfo.label}</span>
            </div>
            <div class="p-4 flex-grow flex flex-col bg-white">
                <div class="flex items-center justify-between mb-3">
                    <div class="flex items-center gap-2 text-gray-600">
                        <i class="${stateInfo.icon} text-base"></i>
                    </div>
                    <span class="text-[12px] font-bold px-1.5 py-0.5 border" style="color: ${prioBorderColor}; border-color: ${prioBorderColor}">PRIORITÀ: ${prioLabel}</span>
                </div>
                
                <h3 class="${strikeClass} task-desc-xp break-words mb-6">${task.descrizione}</h3>
                
                <div class="mt-auto pt-3 border-t border-gray-100 flex items-center gap-2">
                    <div class="flex gap-1">
                        ${actionButtonsHtml}
                    </div>
                    ${deleteBtn}
                </div>
            </div>
        `;
        
        taskGrid.appendChild(card);
    });
}
