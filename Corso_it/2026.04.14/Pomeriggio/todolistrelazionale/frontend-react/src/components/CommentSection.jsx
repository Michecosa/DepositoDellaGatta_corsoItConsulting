import React, { useState, useEffect } from 'react';
import { Send, Trash2, MessageSquare, AlertCircle } from 'lucide-react';
import { getCommentiByTodo, createCommento, deleteCommento } from '../services/api';

const CommentSection = ({ todoId }) => {
  const [comments, setComments] = useState([]);
  const [newComment, setNewComment] = useState('');
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');
  const [isSubmitting, setIsSubmitting] = useState(false);

  useEffect(() => {
    fetchComments();
  }, [todoId]);

  const fetchComments = async () => {
    try {
      setLoading(true);
      const res = await getCommentiByTodo(todoId);
      setComments(res.data);
    } catch (err) {
      console.error("Errore caricamento commenti", err);
      setError("Impossibile caricare i commenti.");
    } finally {
      setLoading(false);
    }
  };

  const handleAddComment = async (e) => {
    e.preventDefault();
    if (newComment.trim().length < 3) {
      setError("Il commento deve avere almeno 3 caratteri.");
      return;
    }

    try {
      setIsSubmitting(true);
      setError('');
      const commentoData = {
        testo: newComment,
        todo: { id: todoId }
      };
      const res = await createCommento(commentoData);
      setComments([...comments, res.data]);
      setNewComment('');
    } catch (err) {
      console.error("Errore aggiunta commento", err);
      setError("Errore durante l'invio del commento.");
    } finally {
      setIsSubmitting(false);
    }
  };

  const handleDeleteComment = async (commentId) => {
    try {
      await deleteCommento(commentId);
      setComments(comments.filter(c => c.id !== commentId));
    } catch (err) {
      console.error("Errore eliminazione commento", err);
      alert("Impossibile eliminare il commento.");
    }
  };

  return (
    <div className="mt-4 pt-4 border-t border-slate-100">
      <div className="flex items-center mb-3 text-slate-600">
        <MessageSquare size={16} className="mr-2" />
        <h4 className="text-sm font-semibold uppercase tracking-wider">Commenti ({comments.length})</h4>
      </div>

      {/* Lista Commenti */}
      <div className="space-y-3 mb-4">
        {loading ? (
          <div className="flex justify-center py-2">
            <div className="animate-spin rounded-full h-4 w-4 border-b-2 border-blue-500"></div>
          </div>
        ) : comments.length === 0 ? (
          <p className="text-xs text-slate-400 italic px-1">Nessun commento presente.</p>
        ) : (
          comments.map(comment => (
            <div key={comment.id} className="group/comment flex items-start justify-between bg-slate-50 p-2 rounded-lg border border-slate-100 transition-colors hover:bg-white hover:border-blue-100">
              <p className="text-sm text-slate-700 leading-snug">{comment.testo}</p>
              <button 
                onClick={() => handleDeleteComment(comment.id)}
                className="ml-2 p-1 text-slate-300 hover:text-red-500 opacity-0 group-hover/comment:opacity-100 transition-all"
                title="Elimina commento"
              >
                <Trash2 size={14} />
              </button>
            </div>
          ))
        )}
      </div>

      {/* Form Nuovo Commento */}
      <form onSubmit={handleAddComment} className="relative mt-2">
        <input
          type="text"
          placeholder="Scrivi un commento..."
          value={newComment}
          onChange={(e) => {
            setNewComment(e.target.value);
            if (e.target.value.length >= 3 || e.target.value.length === 0) setError('');
          }}
          disabled={isSubmitting}
          className={`w-full pl-3 pr-10 py-2 text-sm bg-white border rounded-xl outline-none transition-all ${
            error ? 'border-red-300 focus:ring-1 focus:ring-red-200' : 'border-slate-200 focus:border-blue-400 focus:ring-2 focus:ring-blue-50/50'
          }`}
        />
        <button
          type="submit"
          disabled={isSubmitting || newComment.trim().length < 3}
          className="absolute right-1 top-1/2 -translate-y-1/2 p-2 text-blue-500 hover:text-blue-700 disabled:text-slate-300 transition-colors"
        >
          {isSubmitting ? (
            <div className="animate-spin rounded-full h-4 w-4 border-b-2 border-blue-500"></div>
          ) : (
            <Send size={16} />
          )}
        </button>
        {error && (
          <div className="flex items-center mt-1 px-1 text-red-500 text-[10px]">
            <AlertCircle size={10} className="mr-1" />
            {error}
          </div>
        )}
      </form>
    </div>
  );
};

export default CommentSection;
