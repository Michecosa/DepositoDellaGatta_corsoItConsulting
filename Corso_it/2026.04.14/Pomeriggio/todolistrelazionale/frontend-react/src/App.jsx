import React, { useState } from 'react';
import UserList from './components/UserList';
import TodoList from './components/TodoList';
import { Menu, X } from 'lucide-react';

function App() {
  const [selectedUser, setSelectedUser] = useState(null);
  const [mobileMenuOpen, setMobileMenuOpen] = useState(false);

  const handleSelectUser = (user) => {
    setSelectedUser(user);
    setMobileMenuOpen(false); // Chiudi il menu su mobile dopo la selezione
  };

  return (
    <div className="h-screen w-full bg-slate-100 flex overflow-hidden font-sans text-slate-800 selection:bg-blue-100 selection:text-blue-900">
      
      {/* Mobile Navbar / Header */}
      <div className="md:hidden absolute top-0 left-0 w-full bg-white z-40 border-b border-slate-200 px-4 py-3 flex items-center justify-between shadow-sm">
        <h1 className="font-bold text-lg text-slate-800">ToDo App</h1>
        <button 
          onClick={() => setMobileMenuOpen(!mobileMenuOpen)}
          className="p-2 -mr-2 text-slate-600 hover:bg-slate-100 rounded-lg transition"
        >
          {mobileMenuOpen ? <X size={24} /> : <Menu size={24} />}
        </button>
      </div>

      {/* Sidebar Mobile Overlay */}
      {mobileMenuOpen && (
        <div 
          className="md:hidden fixed inset-0 bg-slate-900/20 backdrop-blur-sm z-30"
          onClick={() => setMobileMenuOpen(false)}
        />
      )}

      {/* Sidebar Container */}
      <div className={`
        fixed md:static inset-y-0 left-0 transform bg-white z-40 w-72 h-full flex-shrink-0 transition-transform duration-300 ease-in-out md:translate-x-0 pt-14 md:pt-0
        ${mobileMenuOpen ? 'translate-x-0' : '-translate-x-full'}
      `}>
        <UserList onSelectUser={handleSelectUser} selectedUserId={selectedUser?.id} />
      </div>

      {/* Main Content (TodoList) */}
      <main className="flex-1 flex flex-col min-w-0 h-full pt-14 md:pt-0">
        <TodoList selectedUser={selectedUser} />
      </main>

    </div>
  );
}

export default App;
