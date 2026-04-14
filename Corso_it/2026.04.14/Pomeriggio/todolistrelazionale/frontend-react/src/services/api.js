import axios from 'axios';

const api = axios.create({
  baseURL: 'http://localhost:8080', // Assicurati che l'API Spring Boot esponga qui i suoi endpoint
  headers: {
    'Content-Type': 'application/json',
  },
});

export const getUtenti = () => api.get('/utenti'); // Endpoint fittizio o reale (verifica nel controller)
export const getTodosByUtente = (utenteId) => api.get(`/utenti/${utenteId}/todo`); // O altro parametro a seconda dell'implementazione
export const getTodos = () => api.get('/todo'); 
export const createTodo = (todo) => api.post('/todo', todo);
export const updateTodo = (id, todo) => api.put(`/todo/${id}`, todo);
export const deleteTodo = (id) => api.delete(`/todo/${id}`);

// Commenti
export const getCommentiByTodo = (todoId) => api.get(`/todo/${todoId}/commenti`);
export const createCommento = (commento) => api.post('/commenti', commento);
export const deleteCommento = (id) => api.delete(`/commenti/${id}`);

export default api;
