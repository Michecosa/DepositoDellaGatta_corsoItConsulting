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
    
    const inactiveClass = "w-8 h-8 rounded-md flex items-center justify-center font-medium text-sm text-textMuted bg-white/40 hover:bg-white/60 border border-white/50 backdrop-blur-sm transition-all focus:outline-none";
    const activeClass = "w-8 h-8 rounded-md flex items-center justify-center font-bold text-primary bg-white/80 border border-white/80 shadow-md backdrop-blur-md transition-all focus:outline-none scale-105";

    for (let i = 1; i <= 3; i++) {
        const btn = document.getElementById(`prio-${i}`);
        if(btn) btn.className = inactiveClass;
    }
    
    const activeBtn = document.getElementById(`prio-${prio}`);
    if(activeBtn) activeBtn.className = activeClass;
    
    // Nessuna variazione dinamica al bottone Crea (rimane quello standard HTML)
}

function showNotification(message, type = 'success') {
    const container = document.getElementById('notification-area');
    const note = document.createElement('div');
    
    const bgClass = 'bg-white/70 backdrop-blur-md border-white/50 text-textMain';
    const iconClass = type === 'success' ? 'fa-check text-green-500' : 'fa-triangle-exclamation text-red-500';
    
    note.className = `border px-4 py-3 rounded-lg shadow-lg shadow-gray-200/50 flex items-center gap-3 notification-enter ${bgClass}`;
    note.innerHTML = `
        <i class="fa-solid ${iconClass}"></i>
        <p class="text-sm font-medium pr-2">${message}</p>
    `;
    
    container.appendChild(note);
    
    setTimeout(() => {
        note.classList.replace('notification-enter', 'notification-exit');
        setTimeout(() => note.remove(), 300);
    }, 3000);
}

function setFilter(filter) {
    currentFilter = filter;
    
    document.querySelectorAll('.tab-btn').forEach(btn => {
        btn.classList.remove('tab-active');
    });
    
    const activeTab = document.getElementById(`filter-${filter}`);
    if(activeTab) activeTab.classList.add('tab-active');
    
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
    if (!confirm("Sicuro di voler eliminare?")) return;
    
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
        // Glassmorphism base classes
        const baseClasses = "task-card p-5 rounded-b-xl flex flex-col h-full relative group bg-white/50 backdrop-blur-lg border border-white/60 shadow-lg shadow-blue-500/5 hover:bg-white/70 hover:-translate-y-1";
        
        card.className = isCompletedOrCancelled ? `${baseClasses} opacity-60` : baseClasses;
        
        // Actions
        let actionButtonsHtml = '';
        
        if (task.stato === 'TODO') {
            actionButtonsHtml += `<button onclick="updateTaskStatus(${task.id}, 'IN_PROGRESS')" class="text-textMuted hover:text-primary transition-colors text-sm font-medium py-1 px-2 rounded hover:bg-white/60 backdrop-blur-sm flex items-center gap-1.5"><i class="fa-solid fa-play text-xs opacity-70"></i> Inizia</button>`;
        } else if (task.stato === 'IN_PROGRESS') {
            actionButtonsHtml += `<button onclick="updateTaskStatus(${task.id}, 'DONE')" class="text-textMuted hover:text-green-600 transition-colors text-sm font-medium py-1 px-2 rounded hover:bg-white/60 backdrop-blur-sm flex items-center gap-1.5"><i class="fa-solid fa-check text-xs opacity-70"></i> Fatto</button>`;
        }
        
        if (task.stato === 'TODO' || task.stato === 'IN_PROGRESS') {
             actionButtonsHtml += `<button onclick="updateTaskStatus(${task.id}, 'CANCELLED')" class="text-textMuted hover:text-textMain transition-colors text-sm font-medium py-1 px-2 rounded hover:bg-white/60 backdrop-blur-sm flex items-center gap-1.5" title="Annulla"><i class="fa-solid fa-ban text-xs opacity-70"></i> Annulla</button>`;
        }

        const deleteBtn = `<button onclick="deleteTask(${task.id})" class="text-textMuted hover:text-red-500 transition-colors py-1 px-2 rounded hover:bg-white/60 backdrop-blur-sm" title="Elimina"><i class="fa-regular fa-trash-can opacity-80"></i></button>`;
        
        let bottomActions = '';
        if (actionButtonsHtml || isCompletedOrCancelled) {
             bottomActions = `
                <div class="mt-4 pt-3 border-t border-white/50 flex justify-between items-center gap-2">
                    <div class="flex gap-0.5">${actionButtonsHtml}</div>
                    ${deleteBtn}
                </div>
             `;
        } else {
             bottomActions = `
                <div class="mt-4 pt-3 border-t border-white/50 flex justify-end items-center gap-2">
                    ${deleteBtn}
                </div>
             `;
        }

        let strikeClass = isCompletedOrCancelled ? 'line-through text-textMuted' : 'text-textMain';

        let neonLine = '';
        if (task.priorita === 1) neonLine = '<div class="absolute top-0 left-0 w-full h-[2px] bg-red-500/60 z-10"></div>';
        else if (task.priorita === 2) neonLine = '<div class="absolute top-0 left-0 w-full h-[2px] bg-orange-500/60 z-10"></div>';
        else neonLine = '<div class="absolute top-0 left-0 w-full h-[2px] bg-teal-500/50 z-10"></div>';

        card.innerHTML = `
            ${neonLine}
            <div class="flex items-center gap-2 mb-3 text-textMuted pt-1">
                <i class="${stateInfo.icon} text-sm"></i>
                <span class="text-[11px] font-semibold uppercase tracking-wider">${stateInfo.label}</span>
                <span class="text-xs ml-auto font-mono text-gray-400">#${task.id}</span>
            </div>
            
            <h3 class="${strikeClass} text-base font-medium leading-snug flex-grow mb-1 break-words pl-1 border-l-2 border-transparent group-hover:border-primary/50 transition-all">${task.descrizione}</h3>
            
            ${bottomActions}
        `;
        
        taskGrid.appendChild(card);
    });
}
