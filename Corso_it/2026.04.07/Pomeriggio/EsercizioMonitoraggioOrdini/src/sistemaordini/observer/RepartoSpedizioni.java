package sistemaordini.observer;

import sistemaordini.model.Ordine;
import sistemaordini.model.StatoOrdine;

// Viene notificato quando un ordine è stato completamente preparato (SPEDITO)
// o quando lo stato cambia in modo rilevante per la logistica
public class RepartoSpedizioni implements OrdineObserver {

    private static final String NOME = "-- REPARTO SPEDIZIONI --";

    @Override
    public void aggiorna(Ordine ordine, StatoOrdine vecchioStato) {
        switch (ordine.getStato()) {

            case SPEDITO:
                System.out.printf(
                    "%n[%s]%n" +
                    "   > ORDINE PRONTO PER LA SPEDIZIONE!%n" +
                    "   > Ordine #%d - Cliente: %s%n" +
                    "   > Prodotto: %s  |  Quantità: %d unità%n" +
                    "   > Stato: %s -> %s%n" +
                    "   > Generare etichetta di spedizione e affidare al corriere%n",
                    NOME,
                    ordine.getId(), ordine.getCliente(),
                    ordine.getProdotto(), ordine.getQuantita(),
                    vecchioStato, ordine.getStato()
                );
                break;

            case CONSEGNATO:
                System.out.printf(
                    "%n[%s]%n" +
                    "   [OK] Ordine #%d confermato come CONSEGNATO al cliente '%s'%n" +
                    "   > Archiviare la pratica di spedizione.%n",
                    NOME, ordine.getId(), ordine.getCliente()
                );
                break;

            default:
                // Il reparto spedizioni ignora gli altri stati
                break;
        }
    }
}