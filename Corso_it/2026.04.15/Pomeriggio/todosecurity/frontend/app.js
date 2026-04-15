// Configurazione Axios
const api = axios.create({
    baseURL: 'http://localhost:8080',
    headers: {
        'Content-Type': 'application/json'
    }
});

// Interceptor per aggiungere il token JWT a ogni richiesta
api.interceptors.request.use(config => {
    const token = localStorage.getItem('token');
    if (token) {
        config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
}, error => {
    return Promise.reject(error);
});

// Interceptor per gestire errori di autenticazione (es. token scaduto)
api.interceptors.response.use(response => response, error => {
    if (error.response && error.response.status === 401) {
        logout();
        showToast('Sessione scaduta o non valida. Riaccedi.', 'error');
    }
    return Promise.reject(error);
});

// Elementi DOM
const authView = document.getElementById('auth-view');
const dashboardView = document.getElementById('dashboard-view');
const authForm = document.getElementById('auth-form');
const regFields = document.getElementById('reg-fields');
const switchToRegister = document.getElementById('switch-to-register');
const authTitle = document.getElementById('auth-title');
const authSubtitle = document.getElementById('auth-subtitle');
const authSubmitBtn = document.getElementById('auth-submit');
const authBtnText = document.getElementById('auth-btn-text');
const authLoader = document.getElementById('auth-loader');
const userDisplay = document.getElementById('user-display');
const logoutBtn = document.getElementById('logout-btn');
const themeToggle = document.getElementById('theme-toggle');
const todoList = document.getElementById('todo-list');
const todoLoading = document.getElementById('todo-loading');
const todoEmpty = document.getElementById('todo-empty');
const openAddModalBtn = document.getElementById('open-add-modal');
const modalOverlay = document.getElementById('modal-overlay');
const closeModalBtn = document.getElementById('close-modal');
const todoForm = document.getElementById('todo-form');
const toastContainer = document.getElementById('toast-container');

// Stato Applicazione
let isRegisterMode = false;
let currentTodos = [];

// Inizializzazione
document.addEventListener('DOMContentLoaded', () => {
    const token = localStorage.getItem('token');
    const username = localStorage.getItem('username');
    
    if (token && username) {
        showDashboard(username);
    } else {
        showAuth();
    }

    // Inizializza tema
    if (localStorage.getItem('theme') === 'dark') {
        document.body.classList.add('dark-mode');
        themeToggle.querySelector('i').className = 'fas fa-sun';
    }
});

// Funzioni di Navigazione
function showAuth() {
    authView.classList.remove('hidden');
    dashboardView.classList.add('hidden');
}

function showDashboard(username) {
    authView.classList.add('hidden');
    dashboardView.classList.remove('hidden');
    userDisplay.textContent = username;
    fetchTodos();
}

// Gestione Autenticazione
switchToRegister.addEventListener('click', (e) => {
    e.preventDefault();
    isRegisterMode = !isRegisterMode;
    
    if (isRegisterMode) {
        authTitle.textContent = 'Crea Account';
        authSubtitle.textContent = 'Unisciti a noi per gestire i tuoi task';
        authBtnText.textContent = 'Registrati';
        regFields.classList.remove('hidden');
        switchToRegister.textContent = 'Accedi';
        document.getElementById('toggle-auth').firstChild.textContent = 'Hai già un account? ';
    } else {
        authTitle.textContent = 'Bentornato';
        authSubtitle.textContent = 'Inserisci le tue credenziali per accedere';
        authBtnText.textContent = 'Accedi';
        regFields.classList.add('hidden');
        switchToRegister.textContent = 'Registrati';
        document.getElementById('toggle-auth').firstChild.textContent = 'Non hai un account? ';
    }
});

authForm.addEventListener('submit', async (e) => {
    e.preventDefault();
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;
    
    setLoading(true);

    try {
        if (isRegisterMode) {
            const ruolo = document.getElementById('ruolo').value;
            await api.post('/auth/register', { username, password, ruolo });
            showToast('Registrazione completata! Ora puoi accedere.', 'success');
            // Switch to login
            switchToRegister.click();
        } else {
            const response = await api.post('/auth/login', { username, password });
            const { token, refreshToken } = response.data;
            
            if (!token) throw new Error('Token non ricevuto dal server');

            localStorage.setItem('token', token);
            localStorage.setItem('refreshToken', refreshToken);
            localStorage.setItem('username', username);
            
            showDashboard(username);
            showToast('Accesso effettuato con successo!', 'success');
        }
    } catch (error) {
        const msg = error.response?.data?.message || error.response?.data || 'Errore durante l\'operazione';
        showToast(msg, 'error');
    } finally {
        setLoading(false);
    }
});

function logout() {
    const refreshToken = localStorage.getItem('refreshToken');
    if (refreshToken) {
        api.post('/auth/logout', { refreshToken }).catch(() => {});
    }
    localStorage.removeItem('token');
    localStorage.removeItem('refreshToken');
    localStorage.removeItem('username');
    showAuth();
    showToast('Logout effettuato.', 'success');
}

logoutBtn.addEventListener('click', logout);

// Gestione Todo
async function fetchTodos() {
    todoLoading.classList.remove('hidden');
    todoEmpty.classList.add('hidden');
    
    // Clear current list items except loader and empty state
    const items = todoList.querySelectorAll('.todo-card');
    items.forEach(item => item.remove());

    try {
        const response = await api.get('/todos');
        currentTodos = response.data;
        
        if (currentTodos.length === 0) {
            todoEmpty.classList.remove('hidden');
        } else {
            currentTodos.forEach(renderTodo);
        }
    } catch (error) {
        showToast('Errore nel recupero dei task', 'error');
    } finally {
        todoLoading.classList.add('hidden');
    }
}

function renderTodo(todo) {
    const card = document.createElement('div');
    card.className = `todo-card ${todo.completato ? 'completed' : ''}`;
    card.dataset.id = todo.id;
    
    card.innerHTML = `
        ${todo.completato ? '<span class="todo-badge">Fatto</span>' : ''}
        <h3 class="todo-title">${todo.titolo}</h3>
        <p class="todo-desc">${todo.descrizione || 'Nessuna descrizione'}</p>
        <div class="todo-actions">
            <button class="btn btn-icon btn-toggle" title="${todo.completato ? 'Segna come da fare' : 'Segna come completato'}">
                <i class="fas ${todo.completato ? 'fa-undo' : 'fa-check'}"></i>
            </button>
            <button class="btn btn-icon btn-edit" title="Modifica">
                <i class="fas fa-edit"></i>
            </button>
            <button class="btn btn-icon btn-delete" title="Elimina">
                <i class="fas fa-trash"></i>
            </button>
        </div>
    `;

    // Event Listeners for actions
    card.querySelector('.btn-toggle').onclick = () => toggleTodo(todo.id);
    card.querySelector('.btn-edit').onclick = () => openEditModal(todo);
    card.querySelector('.btn-delete').onclick = () => deleteTodo(todo.id);

    todoList.appendChild(card);
}

async function toggleTodo(id) {
    try {
        await api.patch(`/todos/${id}/toggle`);
        fetchTodos();
    } catch (error) {
        showToast('Errore durante l\'aggiornamento', 'error');
    }
}

async function deleteTodo(id) {
    if (!confirm('Sei sicuro di voler eliminare questo task?')) return;
    
    try {
        await api.delete(`/todos/${id}`);
        showToast('Task eliminato.', 'success');
        fetchTodos();
    } catch (error) {
        showToast('Errore durante l\'eliminazione', 'error');
    }
}

// Modal Logic
openAddModalBtn.onclick = () => {
    document.getElementById('modal-title').textContent = 'Nuovo Task';
    todoForm.reset();
    document.getElementById('todo-id').value = '';
    modalOverlay.classList.remove('hidden');
};

function openEditModal(todo) {
    document.getElementById('modal-title').textContent = 'Modifica Task';
    document.getElementById('todo-id').value = todo.id;
    document.getElementById('todo-titolo').value = todo.titolo;
    document.getElementById('todo-descrizione').value = todo.descrizione;
    modalOverlay.classList.remove('hidden');
}

closeModalBtn.onclick = () => modalOverlay.classList.add('hidden');
document.getElementById('cancel-todo').onclick = () => modalOverlay.classList.add('hidden');

todoForm.onsubmit = async (e) => {
    e.preventDefault();
    const id = document.getElementById('todo-id').value;
    const data = {
        titolo: document.getElementById('todo-titolo').value,
        descrizione: document.getElementById('todo-descrizione').value
    };

    try {
        if (id) {
            await api.put(`/todos/${id}`, data);
            showToast('Task aggiornato con successo!', 'success');
        } else {
            await api.post('/todos', data);
            showToast('Nuovo task creato!', 'success');
        }
        modalOverlay.classList.add('hidden');
        fetchTodos();
    } catch (error) {
        showToast('Errore durante il salvataggio', 'error');
    }
};

// Utility
function setLoading(loading) {
    if (loading) {
        authSubmitBtn.disabled = true;
        authBtnText.classList.add('hidden');
        authLoader.classList.remove('hidden');
    } else {
        authSubmitBtn.disabled = false;
        authBtnText.classList.remove('hidden');
        authLoader.classList.add('hidden');
    }
}

function showToast(message, type = 'info') {
    const toast = document.createElement('div');
    toast.className = `toast ${type}`;
    const icon = type === 'success' ? 'fa-check-circle' : (type === 'error' ? 'fa-exclamation-circle' : 'fa-info-circle');
    
    toast.innerHTML = `
        <i class="fas ${icon}"></i>
        <span>${message}</span>
    `;
    
    toastContainer.appendChild(toast);
    
    setTimeout(() => {
        toast.style.opacity = '0';
        toast.style.transform = 'translateX(100%)';
        setTimeout(() => toast.remove(), 300);
    }, 3000);
}

// Theme Toggle
themeToggle.onclick = () => {
    document.body.classList.toggle('dark-mode');
    const isDark = document.body.classList.contains('dark-mode');
    localStorage.setItem('theme', isDark ? 'dark' : 'light');
    themeToggle.querySelector('i').className = isDark ? 'fas fa-sun' : 'fas fa-moon';
};
