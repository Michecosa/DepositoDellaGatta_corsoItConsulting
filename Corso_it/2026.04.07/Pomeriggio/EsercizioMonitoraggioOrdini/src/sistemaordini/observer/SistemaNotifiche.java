package sistemaordini.observer;

import sistemaordini.model.Ordine;
import sistemaordini.model.StatoOrdine;

// Viene notificato ad ogni cambio di stato e simula l'invio di una comunicazione al cliente
public class SistemaNotifiche implements OrdineObserver {

    private static final String NOME = "-- SISTEMA NOTIFICHE --";

    @Override
    public void aggiorna(Ordine ordine, StatoOrdine vecchioStato) {
        String messaggio = costruisciMessaggio(ordine);

        System.out.printf(
                "%n[%s]%n" +
                        "   > Invio notifica al cliente '%s' per ordine #%d%n" +
                        "   > Stato precedente: %s -> Nuovo stato: %s%n" +
                        "   __________________________________________________%n" +
                        "   Messaggio: \"%s\"%n",
                NOME, ordine.getCliente(), ordine.getId(), vecchioStato, ordine.getStato(), messaggio);
    }

    // Genera il testo della notifica in base allo stato attuale dell'ordine
    private String costruisciMessaggio(Ordine ordine) {
        return switch (ordine.getStato()) {
            case CREATO ->
                "Gentile " + ordine.getCliente() + ", il suo ordine #" +
                        ordine.getId() + " per \"" + ordine.getProdotto() +
                        "\" è stato ricevuto con successo";

            case IN_PREPARAZIONE ->
                "Buone notizie! Il suo ordine #" + ordine.getId() + " è in fase di preparazione. La contatteremo appena sarà spedito";

            case SPEDITO ->
                "Il suo ordine #" + ordine.getId() + " (" + ordine.getProdotto()+ ") è stato spedito! Potrà tracciare la consegna tramite il numero di tracking che riceverà a breve";

            case CONSEGNATO ->
                "Ordine #" + ordine.getId() + " consegnato con successo.\nGrazie per aver scelto i nostri servizi, " + ordine.getCliente();
        };
    }
}