package sistemaordini.observer;

import sistemaordini.model.Ordine;
import sistemaordini.model.StatoOrdine;

// Tutti i reparti che vogliono essere notificati dei cambiamenti di stato
// degli ordini devono implementare questa interfaccia
public interface OrdineObserver {
    
    // Chiamato dal Subject (GestoreOrdini) quando lo stato di un ordine cambia
    void aggiorna(Ordine ordine, StatoOrdine vecchioStato);
}