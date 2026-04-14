import React, { useState } from 'react';
import { PlusCircle } from 'lucide-react';
import api from '../services/api';

const TodoForm = ({ onTodoCreated, utenteId }) => {
  const [descrizione, setDescrizione] = useState('');
  const [isSubmitting, setIsSubmitting] = useState(false);
  const [error, setError] = useState('');

  // Validazione in tempo reale
  const handleChange = (e) => {
    const val = e.target.value;
    setDescrizione(val);
    if (val.length > 0 && val.length < 3) {
      setError('La descrizione deve avere almeno 3 caratteri.');
    } else {
      setError('');
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (descrizione.length < 3) {
      setError('La descrizione deve avere almeno 3 caratteri.');
      return;
    }

    try {
      setIsSubmitting(true);
      // Backend: Crea un task associandolo potenzialmente all'utente
      // (Dipende da come il backend mappa l'utente, passiamo utenteId se richiesto)
      const newTodo = { descrizione, completato: false, utente: { id: utenteId } };
      const response = await api.post('/todo', newTodo);
      onTodoCreated(response.data);
      setDescrizione('');
    } catch (err) {
      console.error("Errore durante la creazione", err);
      setError('Errore di connessione. Riprova.');
    } finally {
      setIsSubmitting(false);
    }
  };

  return (
    <div className="bg-white p-5 rounded-2xl shadow-sm border border-slate-100 mb-6">
      <h3 className="text-sm font-semibold text-slate-800 mb-3 uppercase tracking-wider">Aggiungi Task</h3>
      <form onSubmit={handleSubmit}>
        <div className="flex flex-col sm:flex-row sm:items-start gap-3">
          <div className="flex-1">
            <input
              type="text"
              placeholder="Cosa devi fare?"
              value={descrizione}
              onChange={handleChange}
              className={`w-full px-4 py-3 rounded-xl border bg-slate-50 focus:bg-white transition-all outline-none focus:ring-2 ${
                error 
                  ? 'border-red-300 focus:ring-red-200' 
                  : 'border-slate-200 focus:border-blue-400 focus:ring-blue-100'
              }`}
            />
            {error && <p className="text-red-500 text-xs mt-2 px-1">{error}</p>}
          </div>
          <button
            type="submit"
            disabled={isSubmitting || !!error || descrizione.length === 0}
            className="px-6 py-3 bg-blue-600 text-white font-medium rounded-xl hover:bg-blue-700 focus:ring-4 focus:ring-blue-200 disabled:opacity-50 disabled:cursor-not-allowed transition-all flex items-center justify-center min-w-[120px]"
          >
            {isSubmitting ? (
              <span className="animate-spin rounded-full h-5 w-5 border-b-2 border-white"></span>
            ) : (
              <>
                <PlusCircle size={20} className="mr-2" />
                Aggiungi
              </>
            )}
          </button>
        </div>
      </form>
    </div>
  );
};

export default TodoForm;
