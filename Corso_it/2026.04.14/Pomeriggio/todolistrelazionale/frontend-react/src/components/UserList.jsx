import React, { useEffect, useState } from 'react';
import { Users, User, AlertCircle } from 'lucide-react';
import api from '../services/api';

const UserList = ({ onSelectUser, selectedUserId }) => {
  const [utenti, setUtenti] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    fetchUtenti();
  }, []);

  const fetchUtenti = async () => {
    try {
      setLoading(true);
      const response = await api.get('/utenti'); // Assumiamo esista GET /api/utenti
      setUtenti(response.data);
      setError(null);
    } catch (err) {
      setError('Errore nel caricamento degli utenti.');
      console.error(err);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="w-full md:w-72 bg-white border-r border-slate-200 flex flex-col h-full overflow-hidden shadow-sm z-10 hidden md:flex rounded-l-2xl md:rounded-none">
      <div className="p-5 border-b border-slate-100 flex items-center space-x-3 bg-white">
        <div className="p-2 bg-blue-100 text-blue-600 rounded-lg">
          <Users size={24} />
        </div>
        <h2 className="font-semibold text-lg text-slate-800">Utenti</h2>
      </div>

      <div className="flex-1 overflow-y-auto p-4 space-y-2">
        {loading && (
          <div className="flex justify-center p-4">
            <div className="animate-spin rounded-full h-6 w-6 border-b-2 border-blue-600"></div>
          </div>
        )}
        
        {error && (
          <div className="p-3 bg-red-50 text-red-600 rounded-lg flex items-start space-x-2 text-sm">
            <AlertCircle size={16} className="mt-0.5 shrink-0" />
            <span>{error}</span>
          </div>
        )}

        {!loading && !error && utenti.length === 0 && (
          <p className="text-slate-500 text-sm text-center py-4">Nessun utente trovato.</p>
        )}

        {!loading && !error && utenti.map(utente => (
          <button
            key={utente.id}
            onClick={() => onSelectUser(utente)}
            className={`w-full flex items-center p-3 rounded-xl transition-all duration-200 text-left ${
              selectedUserId === utente.id
                ? 'bg-blue-50 border-blue-100 shadow-sm border text-blue-700'
                : 'hover:bg-slate-50 border border-transparent text-slate-600'
            }`}
          >
            <div className={`p-2 rounded-full mr-3 ${
              selectedUserId === utente.id ? 'bg-blue-200 text-blue-700' : 'bg-slate-100 text-slate-500'
            }`}>
               <User size={18} />
            </div>
            <span className="font-medium truncate">{utente.nome || utente.username || 'Utente ' + utente.id}</span>
          </button>
        ))}
      </div>
    </div>
  );
};

export default UserList;
