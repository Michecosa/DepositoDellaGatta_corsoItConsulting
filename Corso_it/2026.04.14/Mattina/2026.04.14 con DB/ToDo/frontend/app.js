const API_URL = 'http://localhost:8080/todos';

// State management
let tasks = [];
let currentFilter = 'ALL';
let searchQuery = '';
let currentPriority = 1; // Default priorità alta

// DOM Elements
const taskGrid = document.getElementById('taskGrid');
const loadingIndicator = document.getElementById('loadingIndicator');
const emptyState = document.getElementById('emptyState');
const searchInput = document.getElementById('searchInput');

// Configuration for mapping states to UI
const stateConfig = {
    'TODO': { label: 'Da Fare', color: 'blue', icon: 'fa-circle', nextStates: ['IN_PROGRESS', 'CANCELLED'] },
    'IN_PROGRESS': { label: 'In Corso', color: 'yellow', icon: 'fa-spinner fa-spin', nextStates: ['DONE', 'CANCELLED'] },
    'DONE': { label: 'Completato', color: 'green', icon: 'fa-check-circle', nextStates: [] },
    'CANCELLED': { label: 'Annullato', color: 'red', icon: 'fa-times-circle', nextStates: [] }
};

// Initialize App
document.addEventListener('DOMContentLoaded', () => {
    fetchTasks();
    
    // Auto search delay helper
    let debounceTimer;
    searchInput.addEventListener('input', (e) => {
        clearTimeout(debounceTimer);
        debounceTimer = setTimeout(() => {
            searchQuery = e.target.value.trim();
            fetchTasks(); // Fetch with query to use backend filtering
        }, 400);
    });
});

// Show Notification
function showNotification(message, type = 'success') {
    const container = document.getElementById('notification-area');
    const note = document.createElement('div');
    
    // Setup colors based on type
    const bgClass = type === 'success' ? 'bg-green-500/20 border-green-500/50 text-green-200' : 'bg-red-500/20 border-red-500/50 text-red-200';
    const iconClass = type === 'success' ? 'fa-check' : 'fa-exclamation-triangle';
    
    note.className = `glass-card border px-4 py-3 rounded-xl shadow-lg flex items-center gap-3 notification-enter ${bgClass}`;
    note.innerHTML = `
        <div class="w-8 h-8 rounded-full bg-white/10 flex items-center justify-center shrink-0">
            <i class="fa-solid ${iconClass}"></i>
        </div>
        <p class="text-sm font-medium pr-2">${message}</p>
    `;
    
    container.appendChild(note);
    
    // Auto remove
    setTimeout(() => {
        note.classList.replace('notification-enter', 'notification-exit');
        setTimeout(() => note.remove(), 300);
    }, 4000);
}

// Set Filter
function setFilter(filter) {
    currentFilter = filter;
    
    // Update UI for buttons
    document.querySelectorAll('.filter-btn').forEach(btn => {
        btn.classList.remove('active');
    });
    document.getElementById(`filter-${filter}`).classList.add('active');
    
    // Re-fetch with backend filter if possible, or filter frontend
    fetchTasks();
}

// Fetch Tasks
async function fetchTasks() {
    try {
        setLoading(true);
        
        let url = API_URL;
        const params = new URLSearchParams();
        
        if (currentFilter !== 'ALL') {
            params.append('stato', currentFilter);
        }
        if (searchQuery !== '') {
            params.append('search', searchQuery);
        }
        
        if (params.toString()) {
            url += `?${params.toString()}`;
        }

        const response = await axios.get(url);
        tasks = response.data;
        
        renderTasks();
    } catch (error) {
        console.error("Error fetching tasks:", error);
        showNotification("Impossibile caricare i task. Verifica che il server sia acceso.", "error");
    } finally {
        setLoading(false);
    }
}

// Priority toggle logic
function setPriority(prio) {
    if (prio < 1 || prio > 3) return;
    currentPriority = prio;
    
    // Reset di tutti i bottoni
    for (let i = 1; i <= 3; i++) {
        const btn = document.getElementById(`prio-${i}`);
        if(btn) {
            btn.className = "w-10 h-10 rounded-lg flex items-center justify-center font-bold text-gray-400 hover:bg-white/10 transition-all focus:outline-none";
        }
    }
    
    // Evidenzia il bottone selezionato (intuitivo)
    const activeBtn = document.getElementById(`prio-${prio}`);
    if(activeBtn) {
        activeBtn.className = "w-10 h-10 rounded-lg flex items-center justify-center font-bold text-white bg-purple-600 shadow-md shadow-purple-500/20 transition-all focus:outline-none scale-110";
    }
}

// Create Task
async function createTask() {
    const descInput = document.getElementById('newTaskDesc');
    
    const desc = descInput.value.trim();
    const prio = currentPriority;
    
    if (!desc) {
        showNotification("Inserisci una descrizione per il task.", "error");
        return;
    }
    
    try {
        const response = await axios.post(API_URL, {
            descrizione: desc,
            priorita: prio
        });
        
        descInput.value = '';
        setPriority(1); // Reset to default
        
        showNotification("Task creato con successo!");
        
        // If the current filter excludes TODOs, user might not see it, usually better to fetch all
        fetchTasks();
    } catch (error) {
        console.error("Creation error:", error);
        showNotification("Errore durante la creazione del task.", "error");
    }
}

// Update Task Status
async function updateTaskStatus(id, newStatus) {
    try {
        const taskToUpdate = tasks.find(t => t.id === id);
        if (!taskToUpdate) return;
        
        const response = await axios.put(`${API_URL}/${id}`, {
            descrizione: taskToUpdate.descrizione,
            priorita: taskToUpdate.priorita,
            stato: newStatus
        });
        
        if (typeof response.data === 'string' && response.data.includes("non consentita")) {
             showNotification(response.data, "error");
             return;
        }

        showNotification(`Task spostato in ${stateConfig[newStatus].label}`);
        fetchTasks();
    } catch (error) {
        console.error("Update error:", error);
        showNotification("Errore durante l'aggiornamento.", "error");
    }
}

// Delete Task
async function deleteTask(id) {
    if (!confirm("Sei sicuro di voler eliminare questo task?")) return;
    
    try {
        await axios.delete(`${API_URL}/${id}`);
        showNotification("Task eliminato permanentemente.");
        fetchTasks();
    } catch (error) {
        console.error("Delete error:", error);
        showNotification("Errore durante l'eliminazione.", "error");
    }
}

// UI Helpers
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

function getPriorityBadge(priority) {
    let colorClass = 'bg-gray-500/20 text-gray-400 border-gray-500/30';
    if (priority === 1) colorClass = 'bg-red-500/20 text-red-400 border-red-500/30';
    else if (priority === 2) colorClass = 'bg-orange-500/20 text-orange-400 border-orange-500/30';
    else if (priority === 3) colorClass = 'bg-yellow-500/20 text-yellow-400 border-yellow-500/30';
    
    return `<span class="px-2 py-0.5 rounded text-xs font-medium border ${colorClass}">Prio ${priority}</span>`;
}

function renderTasks() {
    taskGrid.innerHTML = '';
    
    tasks.forEach((task, index) => {
        const stateConf = stateConfig[task.stato];
        
        // Define color classes based on state
        const colorTitle = `text-${stateConf.color}-400`;
        const borderCard = `border-${stateConf.color}-500/30`;
        
        const card = document.createElement('div');
        card.className = `glass-card task-card p-5 rounded-2xl border ${borderCard} flex flex-col h-full opacity-0 animate-slide-up relative overflow-hidden group`;
        card.style.animationDelay = `${index * 0.05}s`;
        
        // Subtle background glow based on state
        const glowColor = {
            'blue': 'from-blue-500/5',
            'yellow': 'from-yellow-500/5',
            'green': 'from-green-500/5',
            'red': 'from-red-500/5'
        }[stateConf.color];
        
        // Build Action Buttons
        let actionButtonsHtml = '';
        
        if (task.stato === 'TODO') {
            actionButtonsHtml += `
                <button onclick="updateTaskStatus(${task.id}, 'IN_PROGRESS')" class="flex-1 bg-yellow-500/10 hover:bg-yellow-500/20 text-yellow-400 border border-yellow-500/30 rounded-xl py-2 px-3 text-sm font-medium transition-all flex items-center justify-center gap-2">
                    <i class="fa-solid fa-play text-xs"></i> Inizia
                </button>
            `;
        } else if (task.stato === 'IN_PROGRESS') {
            actionButtonsHtml += `
                <button onclick="updateTaskStatus(${task.id}, 'DONE')" class="flex-1 bg-green-500/10 hover:bg-green-500/20 text-green-400 border border-green-500/30 rounded-xl py-2 px-3 text-sm font-medium transition-all flex items-center justify-center gap-2">
                    <i class="fa-solid fa-check text-xs"></i> Fatto
                </button>
            `;
        }
        
        if (task.stato === 'TODO' || task.stato === 'IN_PROGRESS') {
             actionButtonsHtml += `
                <button onclick="updateTaskStatus(${task.id}, 'CANCELLED')" class="bg-red-500/10 hover:bg-red-500/20 text-red-400 border border-red-500/30 rounded-xl py-2 px-3 text-sm font-medium transition-all" title="Annulla">
                    <i class="fa-solid fa-ban"></i>
                </button>
            `;
        }

        // Delete is always possible via API, hide until hover unless finished
        const deleteClass = (task.stato === 'DONE' || task.stato === 'CANCELLED') 
            ? 'flex-1 bg-gray-500/10 hover:bg-red-500/20 hover:text-red-400 border border-gray-500/30 hover:border-red-500/30 text-gray-400 rounded-xl py-2 px-3 text-sm font-medium transition-all flex items-center justify-center gap-2'
            : 'absolute top-4 right-4 text-gray-500 hover:text-red-400 transition-colors opacity-0 group-hover:opacity-100';

        let deleteBtnHtml = `
            <button onclick="deleteTask(${task.id})" class="${deleteClass}" title="Elimina Task">
                <i class="fa-regular fa-trash-can"></i> ${(task.stato === 'DONE' || task.stato === 'CANCELLED') ? 'Elimina' : ''}
            </button>
        `;
        
        let bottomActions = '';
        if (actionButtonsHtml || (task.stato === 'DONE' || task.stato === 'CANCELLED')) {
             bottomActions = `
                <div class="mt-auto pt-4 flex gap-2">
                    ${actionButtonsHtml}
                    ${(task.stato === 'DONE' || task.stato === 'CANCELLED') ? deleteBtnHtml : ''}
                </div>
             `;
        }

        card.innerHTML = `
            <div class="absolute top-0 right-0 w-32 h-32 bg-gradient-to-bl ${glowColor} to-transparent rounded-bl-full pointer-events-none"></div>
            
            ${(task.stato !== 'DONE' && task.stato !== 'CANCELLED') ? deleteBtnHtml : ''}
            
            <div class="flex items-center gap-3 mb-3">
                <div class="w-8 h-8 rounded-full bg-${stateConf.color}-500/20 flex items-center justify-center text-${stateConf.color}-400">
                    <i class="fa-solid ${stateConf.icon}"></i>
                </div>
                <div class="flex flex-col">
                    <span class="text-xs text-gray-400 font-medium tracking-wider uppercase">${stateConf.label}</span>
                    <span class="text-xs text-gray-500">ID: #${task.id}</span>
                </div>
            </div>
            
            <h3 class="text-lg font-medium text-white mb-4 pr-6 leading-tight flex-grow">${task.descrizione}</h3>
            
            <div class="flex items-center gap-2 mb-2">
                ${getPriorityBadge(task.priorita)}
            </div>
            
            ${bottomActions}
        `;
        
        taskGrid.appendChild(card);
    });
}
