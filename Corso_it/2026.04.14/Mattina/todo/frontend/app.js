const API_URL = 'http://localhost:8080/api/todos';

// DOM Elements
const todoForm = document.getElementById('todo-form');
const todoInput = document.getElementById('todo-input');
const todoList = document.getElementById('todo-list');
const todoListContainer = document.getElementById('todo-list-container');
const loadingIndicator = document.getElementById('loading');
const emptyState = document.getElementById('empty-state');
const toast = document.getElementById('toast');
const toastMessage = document.getElementById('toast-message');
const toastIcon = document.getElementById('toast-icon');

// State
let todos = [];

// Initialize
document.addEventListener('DOMContentLoaded', fetchTodos);
todoForm.addEventListener('submit', handleAddTodo);

// Functions
async function fetchTodos() {
    showLoading();
    try {
        const response = await axios.get(API_URL);
        todos = response.data;
        renderTodos();
    } catch (error) {
        showToast('Errore di connessione al server', 'error');
        console.error('Fetch error:', error);
    } finally {
        hideLoading();
    }
}

async function handleAddTodo(e) {
    e.preventDefault();
    const text = todoInput.value.trim();
    if (!text) return;

    todoInput.disabled = true;
    
    try {
        const response = await axios.post(API_URL, {
            descrizione: text,
            completato: false
        });
        
        todos.unshift(response.data); // Add to beginning
        renderTodos();
        todoInput.value = '';
        showToast('Task aggiunta con successo!', 'success');
    } catch (error) {
        showToast('Errore durante la creazione', 'error');
        console.error('Create error:', error);
    } finally {
        todoInput.disabled = false;
        todoInput.focus();
    }
}

async function toggleComplete(id, currentStatus, event) {
    // Optimistic UI Update for perceived performance
    const todo = todos.find(t => t.id === Number(id));
    if (!todo) return;
    
    todo.completato = !currentStatus;
    updateTodoVisuals(id, todo.completato);
    
    // Prevent multiple rapid clicks by disabling pointer events on the parent temporarily
    const li = document.getElementById(`todo-${id}`);
    if(li) li.style.pointerEvents = 'none';

    try {
        await axios.put(`${API_URL}/${id}`, {
            id: todo.id,
            descrizione: todo.descrizione,
            completato: todo.completato
        });
        showToast(todo.completato ? 'Task completata!' : 'Task da terminare', 'info');
    } catch (error) {
        // Revert on error
        todo.completato = currentStatus;
        updateTodoVisuals(id, currentStatus);
        
        const checkbox = document.getElementById(`checkbox-${id}`);
        if(checkbox) checkbox.checked = currentStatus;

        showToast('Errore durante l\'aggiornamento', 'error');
        console.error('Update error:', error);
    } finally {
        if(li) li.style.pointerEvents = 'auto';
        renderTodos(); // Re-render to sort properly
    }
}

async function deleteTodo(id) {
    const li = document.getElementById(`todo-${id}`);
    if(li) {
        li.classList.add('deleting'); // trigger out animation
    }

    try {
        await axios.delete(`${API_URL}/${id}`);
        todos = todos.filter(t => t.id !== Number(id));
        
        setTimeout(() => {
            renderTodos();
            showToast('Task eliminata', 'warning');
        }, 300); // Wait for the slideOut animation to finish
    } catch (error) {
        if(li) li.classList.remove('deleting');
        showToast("Errore durante l'eliminazione", 'error');
        console.error('Delete error:', error);
    }
}

// Rendering Logic
function renderTodos() {
    todoList.innerHTML = '';
    
    if (todos.length === 0) {
        todoListContainer.classList.add('hidden');
        emptyState.classList.remove('hidden');
    } else {
        emptyState.classList.add('hidden');
        todoListContainer.classList.remove('hidden');
        
        // Sort: incomplete first, then by ID descending (newest first)
        const sortedTodos = [...todos].sort((a, b) => {
             if (a.completato === b.completato) return b.id - a.id; 
             return a.completato ? 1 : -1;
        });

        sortedTodos.forEach(todo => {
            const li = document.createElement('li');
            li.id = `todo-${todo.id}`;
            li.className = `task-item group flex items-center justify-between p-4 bg-white/5 border border-white/5 rounded-2xl hover:bg-white/10 transition-all duration-300 ${todo.completato ? 'opacity-50' : ''}`;
            
            // Allow clicking entire row to toggle checkbox
            li.innerHTML = `
                <div class="flex items-center gap-4 flex-1 cursor-pointer" onclick="document.getElementById('checkbox-${todo.id}').click()">
                    <input type="checkbox" 
                        id="checkbox-${todo.id}"
                        class="custom-checkbox w-6 h-6 rounded-md cursor-pointer transition-colors duration-200 flex-shrink-0" 
                        ${todo.completato ? 'checked' : ''} 
                        onclick="event.stopPropagation(); toggleComplete(${todo.id}, ${todo.completato}, event)">
                    
                    <span id="text-${todo.id}" class="text-lg transition-all duration-300 ${todo.completato ? 'line-through text-slate-400' : 'text-slate-100'}">
                        ${escapeHtml(todo.descrizione)}
                    </span>
                </div>
                <button onclick="event.stopPropagation(); deleteTodo(${todo.id})" class="ml-4 opacity-0 group-hover:opacity-100 text-slate-400 hover:text-red-400 transition-all duration-200 transform hover:scale-110 p-2 focus:opacity-100 flex-shrink-0">
                    <i class="fas fa-trash-alt"></i>
                </button>
            `;
            todoList.appendChild(li);
        });
    }
}

function updateTodoVisuals(id, isCompleted) {
    const li = document.getElementById(`todo-${id}`);
    const text = document.getElementById(`text-${id}`);
    if (li && text) {
        if (isCompleted) {
            li.classList.add('opacity-50');
            text.classList.add('line-through', 'text-slate-400');
            text.classList.remove('text-slate-100');
        } else {
            li.classList.remove('opacity-50');
            text.classList.remove('line-through', 'text-slate-400');
            text.classList.add('text-slate-100');
        }
    }
}

// Visual Utilities
function showLoading() {
    loadingIndicator.classList.remove('hidden');
    todoListContainer.classList.add('hidden');
    emptyState.classList.add('hidden');
}

function hideLoading() {
    loadingIndicator.classList.add('hidden');
}

let toastTimeout;
function showToast(message, type = 'success') {
    toastMessage.textContent = message;
    
    // Reset toast classes before applying new ones
    toast.className = 'fixed bottom-5 right-5 transform translate-y-0 opacity-100 transition-all duration-300 flex items-center gap-3 bg-white/10 backdrop-blur-md border px-6 py-4 rounded-xl shadow-lg z-50 text-white';
    
    // Icon & Color styling based on type
    if (type === 'success') {
        toastIcon.innerHTML = '<i class="fas fa-check-circle"></i>';
        toastIcon.className = 'text-green-400 text-xl';
        toast.classList.add('border-green-500/30', 'shadow-green-500/20');
    } else if (type === 'error') {
        toastIcon.innerHTML = '<i class="fas fa-exclamation-circle"></i>';
        toastIcon.className = 'text-red-400 text-xl';
        toast.classList.add('border-red-500/30', 'shadow-red-500/20');
    } else if (type === 'warning') {
        toastIcon.innerHTML = '<i class="fas fa-trash-alt"></i>';
        toastIcon.className = 'text-orange-400 text-xl';
        toast.classList.add('border-orange-500/30', 'shadow-orange-500/20');
    } else {
        toastIcon.innerHTML = '<i class="fas fa-info-circle"></i>';
        toastIcon.className = 'text-blue-400 text-xl';
        toast.classList.add('border-blue-500/30', 'shadow-blue-500/20');
    }

    clearTimeout(toastTimeout);
    toastTimeout = setTimeout(() => {
        toast.classList.remove('translate-y-0', 'opacity-100');
        toast.classList.add('translate-y-24', 'opacity-0');
    }, 3000);
}

// Prevent XSS
function escapeHtml(unsafe) {
    if(!unsafe) return '';
    return unsafe
         .replace(/&/g, "&amp;")
         .replace(/</g, "&lt;")
         .replace(/>/g, "&gt;")
         .replace(/"/g, "&quot;")
         .replace(/'/g, "&#039;");
}
