import axios from 'axios';

const api = axios.create({
  baseURL: 'http://localhost:8080/api', // Assicurati che l'API Spring Boot esponga qui i suoi endpoint
  headers: {
    'Content-Type': 'application/json',
  },
});

export const getUtenti = () => api.get('/utenti'); // Endpoint fittizio o reale (verifica nel controller)
export const getTodosByUtente = (utenteId) => api.get(`/todos?utenteId=${utenteId}`); // O altro parametro a seconda dell'implementazione
export const getTodos = () => api.get('/todos'); 
export const createTodo = (todo) => api.post('/todos', todo);
export const updateTodo = (id, todo) => api.put(`/todos/${id}`, todo);
export const deleteTodo = (id) => api.delete(`/todos/${id}`);

export default api;
