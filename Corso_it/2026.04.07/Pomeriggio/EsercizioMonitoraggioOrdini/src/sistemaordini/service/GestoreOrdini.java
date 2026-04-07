package sistemaordini.service;

import sistemaordini.database.OrdineDAO;
import sistemaordini.model.Ordine;
import sistemaordini.model.StatoOrdine;
import sistemaordini.observer.OrdineObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Gestisce gli ordini (operazioni CRUD tramite DAO) e notifica automaticamente
 * tutti gli observer registrati ad ogni cambio di stato
 */
public class GestoreOrdini {

    // Lista degli observer registrati
    private final List<OrdineObserver> observers = new ArrayList<>();

    // DAO per l'accesso al database
    private final OrdineDAO dao = new OrdineDAO();

    // Gestione Observer (Subject Interface)

    /**
     * Registra un nuovo observer che verrà notificato ai cambi di stato
     */
    public void registraObserver(OrdineObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
            System.out.println("[GESTORE] Observer registrato: " + observer.getClass().getSimpleName());
        }
    }

    /**
     * Rimuove un observer precedentemente registrato
     */
    public void rimuoviObserver(OrdineObserver observer) {
        if (observers.remove(observer)) {
            System.out.println("[GESTORE] Observer rimosso: "+ observer.getClass().getSimpleName());
        }
    }

    // Notifica tutti gli observer registrati di un cambio di stato
    private void notificaObserver(Ordine ordine, StatoOrdine vecchioStato) {
        System.out.println("\n-----------------------------------------------");
        System.out.printf(" Notifica cambio stato ordine #%d: %s -> %s%n",
                ordine.getId(), vecchioStato, ordine.getStato());
        System.out.println("-----------------------------------------------");

        for (OrdineObserver obs : observers) {
            obs.aggiorna(ordine, vecchioStato);
        }
        System.out.println("__________________________________________________");
    }


    // Crea un nuovo ordine, lo salva nel DB e notifica gli observer
    public Ordine creaOrdine(String cliente, String prodotto, int quantita) {
        Ordine ordine = new Ordine(cliente, prodotto, quantita, "CREATO");
        dao.inserisci(ordine);
        System.out.printf("%n[GESTORE] Ordine #%d creato con successo per '%s'%n",ordine.getId(), cliente);
        // Notifica: lo stato iniziale è CREATO (vecchioStato = null in questo caso)
        notificaObserver(ordine, null);
        return ordine;
    }

    // Aggiorna lo stato di un ordine esistente, salva la modifica nel DB e notifica
    // tutti gli observer registrati
    public boolean aggiornaStatoOrdine(int idOrdine, StatoOrdine nuovoStato) {
        Optional<Ordine> opt = dao.trovaPerID(idOrdine);

        if (opt.isEmpty()) {
            System.out.printf("[GESTORE] [ERRORE] Ordine #%d non trovato%n", idOrdine);
            return false;
        }

        Ordine ordine = opt.get();
        StatoOrdine vecchio = ordine.getStato();

        if (vecchio == nuovoStato) {
            System.out.printf("[GESTORE] [ERRORE] L'ordine #%d è già nello stato '%s'%n", idOrdine, nuovoStato);
            return false;
        }

        // Aggiorna nel database
        boolean ok = dao.aggiornaStato(idOrdine, nuovoStato);
        if (ok) {
            ordine.setStato(nuovoStato);
            notificaObserver(ordine, vecchio);
        }
        return ok; // true se andato a buon fine
    }

    /**
     * Restituisce tutti gli ordini presenti nel database
     */
    public List<Ordine> getTuttiGliOrdini() {
        return dao.trovaTutti();
    }

    // Cerca un ordine tramite ID
    public Optional<Ordine> cercaPerID(int id) {
        return dao.trovaPerID(id);
    }
}