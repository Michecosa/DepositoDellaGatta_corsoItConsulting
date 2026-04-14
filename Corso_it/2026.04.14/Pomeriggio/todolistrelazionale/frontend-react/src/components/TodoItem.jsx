import React, { useState } from 'react';
import { Trash2, Check, Clock } from 'lucide-react';
import api from '../services/api';

const TodoItem = ({ todo, onUpdate, onDelete }) => {
  const [isDeleting, setIsDeleting] = useState(false);
  const [isToggling, setIsToggling] = useState(false);

  const handleToggle = async () => {
    try {
      setIsToggling(true);
      // PUT per aggiornare stato
      const updatedTodo = { ...todo, completato: !todo.completato };
      await api.put(`/todos/${todo.id}`, updatedTodo);
      onUpdate(updatedTodo);
    } catch (err) {
      console.error("Errore nell'aggiornamento del todo", err);
      alert("Impossibile aggiornare il task.");
    } finally {
      setIsToggling(false);
    }
  };

  const handleDelete = async () => {
    try {
      setIsDeleting(true);
      await api.delete(`/todos/${todo.id}`);
      onDelete(todo.id);
    } catch (err) {
      console.error("Errore nell'eliminazione del todo", err);
      alert("Impossibile eliminare il task.");
      setIsDeleting(false);
    }
  };

  return (
    <div className={`p-4 mb-3 rounded-2xl border transition-all duration-300 flex items-center shadow-sm group ${
      todo.completato 
        ? 'bg-slate-50 border-slate-200 opacity-75' 
        : 'bg-white border-blue-100 hover:border-blue-300 hover:shadow-md'
    }`}>
      <button
        onClick={handleToggle}
        disabled={isToggling}
        className={`shrink-0 w-6 h-6 rounded flex items-center justify-center border transition-colors ${
          todo.completato 
            ? 'bg-green-500 border-green-500 text-white' 
            : 'bg-white border-slate-300 hover:border-blue-500 text-transparent hover:text-blue-200'
        }`}
      >
        <Check size={16} strokeWidth={3} />
      </button>

      <div className="ml-4 flex-1">
        <p className={`text-[15px] transition-all duration-300 ${
          todo.completato ? 'text-slate-400 line-through' : 'text-slate-800 font-medium'
        }`}>
          {todo.descrizione}
        </p>
        <div className="flex items-center text-xs text-slate-400 mt-1 space-x-1">
          <Clock size={12} />
          <span>Task #{todo.id}</span>
        </div>
      </div>

      <button
        onClick={handleDelete}
        disabled={isDeleting}
        className="ml-4 p-2 text-slate-300 hover:text-red-500 hover:bg-red-50 rounded-xl opacity-0 group-hover:opacity-100 focus:opacity-100 transition-all"
        title="Elimina"
      >
        <Trash2 size={18} />
      </button>
    </div>
  );
};

export default TodoItem;
