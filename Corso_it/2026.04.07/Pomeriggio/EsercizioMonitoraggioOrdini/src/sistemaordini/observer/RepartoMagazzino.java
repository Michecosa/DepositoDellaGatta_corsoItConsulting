package sistemaordini.observer;

import sistemaordini.model.Ordine;
import sistemaordini.model.StatoOrdine;

/**
 * Viene notificato quando un ordine entra nello stato IN_PREPARAZIONE
 * Il magazzino deve raccogliere i prodotti e prepararli per la spedizione
 */
public class RepartoMagazzino implements OrdineObserver {

    private static final String NOME = "-- REPARTO MAGAZZINO --";

    @Override
    public void aggiorna(Ordine ordine, StatoOrdine vecchioStato) {
        if (ordine.getStato() == StatoOrdine.IN_PREPARAZIONE) {
            System.out.printf(
                    "%n[%s]%n" +
                            "   > NUOVO ORDINE DA PREPARARE!%n" +
                            "   > Ordine #%d per il cliente '%s'%n" +
                            "   > Prodotto: %s  |  Quantità: %d unità%n" +
                            "   > Stato aggiornato: %s -> %s%n" +
                            "   > Avviare raccolta articoli in magazzino.%n",
                    NOME,
                    ordine.getId(), ordine.getCliente(),
                    ordine.getProdotto(), ordine.getQuantita(),
                    vecchioStato, ordine.getStato());
        }
        // Il magazzino non reagisce agli altri cambiamenti di stato
    }
}