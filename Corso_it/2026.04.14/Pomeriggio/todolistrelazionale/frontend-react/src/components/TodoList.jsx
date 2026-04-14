import React, { useEffect, useState } from 'react';
import { ListTodo, Search } from 'lucide-react';
import api from '../services/api';
import TodoItem from './TodoItem';
import TodoForm from './TodoForm';

const TodoList = ({ selectedUser }) => {
  const [todos, setTodos] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
  const [searchQuery, setSearchQuery] = useState('');

  useEffect(() => {
    if (selectedUser) {
      fetchTodos();
    }
  }, [selectedUser]);

  const fetchTodos = async () => {
    try {
      setLoading(true);
      const res = await api.get(`/utenti/${selectedUser.id}/todo`);
      setTodos(res.data);
      setError(null);
    } catch (err) {
      setError("Impossibile caricare i task.");
      console.error(err);
    } finally {
      setLoading(false);
    }
  };

  const handleCreateTodo = (newTodo) => {
    // Aggiungo il nuovo todo in cima
    setTodos([newTodo, ...todos]);
  };

  const handleUpdateTodo = (updatedTodo) => {
    setTodos(todos.map(t => (t.id === updatedTodo.id ? updatedTodo : t)));
  };

  const handleDeleteTodo = (deletedId) => {
    setTodos(todos.filter(t => t.id !== deletedId));
  };

  const handleSearch = (e) => {
    setSearchQuery(e.target.value);
  };

  const displayedTodos = todos.filter(t => 
    t.descrizione.toLowerCase().includes(searchQuery.toLowerCase())
  );

  if (!selectedUser) {
    return (
      <div className="flex-1 flex flex-col items-center justify-center p-8 text-center bg-slate-50/50">
        <div className="w-24 h-24 mb-6 rounded-full bg-blue-50 flex items-center justify-center text-blue-200">
          <ListTodo size={40} />
        </div>
        <h2 className="text-2xl font-bold text-slate-700 mb-2">Nessun utente selezionato</h2>
        <p className="text-slate-500 max-w-sm">Seleziona un utente dalla sidebar a sinistra per visualizzare e gestire i relativi task.</p>
      </div>
    );
  }

  return (
    <div className="flex-1 overflow-y-auto bg-slate-50 relative">
      <div className="sticky top-0 z-20 bg-white/80 backdrop-blur-md border-b border-slate-200 px-8 py-5">
        <div className="flex flex-col md:flex-row md:items-center justify-between gap-4">
          <div>
            <h1 className="text-2xl font-bold text-slate-800">Task di {selectedUser.nome || selectedUser.username}</h1>
            <p className="text-slate-500 text-sm mt-1">Gestisci la to-do list utente</p>
          </div>
          
          <div className="relative">
            <Search className="absolute left-3 top-1/2 transform -translate-y-1/2 text-slate-400" size={18} />
            <input
              type="text"
              placeholder="Cerca task..."
              value={searchQuery}
              onChange={handleSearch}
              className="pl-10 pr-4 py-2 border border-slate-200 rounded-lg text-sm bg-slate-50 focus:bg-white focus:ring-2 focus:ring-blue-100 focus:border-blue-400 outline-none transition-all w-full md:w-64"
            />
          </div>
        </div>
      </div>

      <div className="p-8 max-w-4xl mx-auto">
        <TodoForm onTodoCreated={handleCreateTodo} utenteId={selectedUser.id} />

        {loading ? (
          <div className="flex justify-center p-12">
            <div className="animate-spin rounded-full h-8 w-8 border-b-2 border-blue-600"></div>
          </div>
        ) : error ? (
           <p className="text-red-500 text-center">{error}</p>
        ) : displayedTodos.length === 0 ? (
           <div className="text-center py-12 px-4 rounded-xl border border-dashed border-slate-300 bg-white">
             <ListTodo size={32} className="mx-auto text-slate-300 mb-3" />
             <p className="text-slate-500 font-medium">Non ci sono task.</p>
             <p className="text-slate-400 text-sm mt-1">Aggiungi il tuo primo todo qui sopra.</p>
           </div>
        ) : (
          <div className="space-y-1">
            {displayedTodos.map(todo => (
              <TodoItem 
                key={todo.id} 
                todo={todo} 
                onUpdate={handleUpdateTodo} 
                onDelete={handleDeleteTodo} 
              />
            ))}
          </div>
        )}
      </div>
    </div>
  );
};

export default TodoList;
