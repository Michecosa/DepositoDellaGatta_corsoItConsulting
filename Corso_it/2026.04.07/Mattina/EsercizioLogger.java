import java.util.Date;

// Classe Logger implementata come Singleton
class Logger {
    // Campo privato statico per l'unica istanza
    private static Logger istanza;

    // Costruttore privato: impedisce la creazione di nuovi Logger con 'new'
    private Logger() {
        System.out.println("[SISTEMA] Logger inizializzato");
    }

    // Metodo statico pubblico per ottenere l'istanza
    public static Logger GetIstanza() {
        if (istanza == null) istanza = new Logger();
        return istanza;
    }

    // Metodo per scrivere messaggi con data e ora
    public void scriviMessaggio(String messaggio) {
        System.out.println("[" + new Date() + "] LOG: " + messaggio);
    }
}

// Classe Main
public class EsercizioLogger {
    public static void main(String[] args) {
        System.out.println("--- Inizio Programma ---");

        // Richiamo il LoggerA
        Logger loggerA = Logger.GetIstanza();
        loggerA.scriviMessaggio("Avvio autenticazione");

        // Richiamo il LoggerB
        Logger loggerB = Logger.GetIstanza();
        loggerB.scriviMessaggio("Connessione stabilita");

        System.out.println("\n--- Verifica Singleton ---");

        // Confronto dei riferimenti
        if (loggerA == loggerB) {
            System.out.println("[OK] loggerA e loggerB puntano alla stessa area di memoria");
            System.out.println("Riferimento A: " + loggerA);
            System.out.println("Riferimento B: " + loggerB);
        } else {
            System.out.println("[ERRORE] Sono state create due istanze diverse");
        }
    }
}