package sistemaordini;

import sistemaordini.database.DBConnection;
import sistemaordini.model.Ordine;
import sistemaordini.model.StatoOrdine;
import sistemaordini.observer.RepartoMagazzino;
import sistemaordini.observer.RepartoSpedizioni;
import sistemaordini.observer.SistemaNotifiche;
import sistemaordini.service.GestoreOrdini;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * Classe principale: punto di ingresso
 *
 * Inizializza il sistema (Singleton DB + Observer) e presenta il menu interattivo
 */
public class Main {

    //  Costanti per la formattazione del menu

    private static final String LINEA  = "=".repeat(50);
    private static final String LINEAS = "-".repeat(50);

    public static void main(String[] args) {

        // 1. Inizializzazione Singleton DBConnection
        System.out.println(LINEA);
        System.out.println("  SISTEMA DI MONITORAGGIO ORDINI - MAGAZZINO");
        System.out.println(LINEA);
        DBConnection.getInstance();   // apre la connessione al DB

        // 2. Creazione del Subject (GestoreOrdini)
        GestoreOrdini gestore = new GestoreOrdini();

        // 3. Registrazione degli Observer
        System.out.println("\n[SETUP] Registrazione observer...");
        gestore.registraObserver(new RepartoMagazzino());
        gestore.registraObserver(new RepartoSpedizioni());
        gestore.registraObserver(new SistemaNotifiche());

        // 4. Menu interattivo
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            mostraMenu();
            int scelta = leggiIntero(scanner, "Scelta: ", 1, 5);

            switch (scelta) {
                case 1 -> inserisciNuovoOrdine(gestore, scanner);
                case 2 -> visualizzaTuttiGliOrdini(gestore);
                case 3 -> cercaOrdinePerID(gestore, scanner);
                case 4 -> aggiornaStatoOrdine(gestore, scanner);
                case 5 -> {
                    System.out.println("\n[SISTEMA] Arrivederci!");
                    running = false;
                }
            }
        }
        scanner.close();
    }

    //  Funzioni di menu

    private static void mostraMenu() {
        System.out.println("\n" + LINEA);
        System.out.println("  MENU PRINCIPALE");
        System.out.println(LINEAS);
        System.out.println("  1. Inserisci nuovo ordine");
        System.out.println("  2. Visualizza tutti gli ordini");
        System.out.println("  3. Cerca ordine per ID");
        System.out.println("  4. Aggiorna stato ordine");
        System.out.println("  5. Esci");
        System.out.println(LINEA);
    }

    // 1. Inserimento nuovo ordine

    private static void inserisciNuovoOrdine(GestoreOrdini gestore, Scanner sc) {
        System.out.println("\n" + LINEAS);
        System.out.println("  INSERIMENTO NUOVO ORDINE");
        System.out.println(LINEAS);

        System.out.print("  Nome cliente  : ");
        String cliente = sc.nextLine().trim();
        if (cliente.isBlank()) { System.out.println("[ERRORE] Cliente non valido."); return; }

        System.out.print("  Prodotto      : ");
        String prodotto = sc.nextLine().trim();
        if (prodotto.isBlank()) { System.out.println("[ERRORE] Prodotto non valido."); return; }

        int quantita = leggiIntero(sc, "  Quantità      : ", 1, Integer.MAX_VALUE);

        Ordine nuovo = gestore.creaOrdine(cliente, prodotto, quantita);
        System.out.println("\n[OK] Ordine creato con successo:");
        System.out.println(nuovo);
    }

    // 2. Visualizza tutti gli ordini

    private static void visualizzaTuttiGliOrdini(GestoreOrdini gestore) {
        System.out.println("\n" + LINEAS);
        System.out.println("  LISTA COMPLETA ORDINI");
        System.out.println(LINEAS);

        List<Ordine> ordini = gestore.getTuttiGliOrdini();

        if (ordini.isEmpty()) {
            System.out.println("  Nessun ordine presente nel database.");
            return;
        }

        System.out.printf("  Totale ordini: %d%n%n", ordini.size());
        System.out.printf("  %-5s %-22s %-27s %-5s %s%n",
                "ID", "CLIENTE", "PRODOTTO", "QTY", "STATO");
        System.out.println("  " + "_".repeat(78));

        for (Ordine o : ordini) {
            System.out.println("  " + o.toStringBreve());
        }
    }

    // 3. Cerca ordine per ID

    private static void cercaOrdinePerID(GestoreOrdini gestore, Scanner sc) {
        System.out.println("\n" + LINEAS);
        System.out.println("  RICERCA ORDINE PER ID");
        System.out.println(LINEAS);

        int id = leggiIntero(sc, "  Inserisci ID ordine: ", 1, Integer.MAX_VALUE);
        Optional<Ordine> risultato = gestore.cercaPerID(id);

        if (risultato.isPresent()) {
            System.out.println("\n  Ordine trovato:");
            System.out.println(risultato.get());
        } else {
            System.out.printf("  [ERRORE] Nessun ordine trovato con ID %d%n", id);
        }
    }

    // 4. Aggiornamento stato ordine

    private static void aggiornaStatoOrdine(GestoreOrdini gestore, Scanner sc) {
        System.out.println("\n" + LINEAS);
        System.out.println("  AGGIORNAMENTO STATO ORDINE");
        System.out.println(LINEAS);

        int id = leggiIntero(sc, "  ID ordine da aggiornare: ", 1, Integer.MAX_VALUE);

        // Verifica esistenza ordine prima di chiedere il nuovo stato
        Optional<Ordine> opt = gestore.cercaPerID(id);
        if (opt.isEmpty()) {
            System.out.printf("  [ERRORE] Ordine #%d non trovato.%n", id);
            return;
        }

        System.out.println("  Stato attuale: " + opt.get().getStato());
        System.out.println("\n  Stati disponibili:");
        StatoOrdine[] stati = StatoOrdine.values();
        for (int i = 0; i < stati.length; i++) {
            System.out.printf("    %d. %s%n", i + 1, stati[i]);
        }

        int sceltaStato = leggiIntero(sc, "\n  Scegli nuovo stato (1-"+ stati.length + "): ", 1, stati.length);
        StatoOrdine nuovoStato = stati[sceltaStato - 1];

        boolean ok = gestore.aggiornaStatoOrdine(id, nuovoStato);
        if (ok) {
            System.out.printf("%n  [OK] Stato ordine #%d aggiornato a '%s'.%n",
                    id, nuovoStato);
        } else {
            System.out.println("  [ERRORE] Aggiornamento non riuscito");
        }
    }

    //  Utility

    /**
     * Legge un intero compreso nell'intervallo [min, max] gestendo input errati
     */
    private static int leggiIntero(Scanner sc, String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String linea = sc.nextLine().trim();
            try {
                int valore = Integer.parseInt(linea);
                if (valore >= min && valore <= max) {
                    return valore;
                }
                System.out.printf("  [ERRORE] Inserisci un numero tra %d e %d.%n", min, max);
            } catch (NumberFormatException e) {
                System.out.println("  [ERRORE] Input non valido. Inserisci un numero intero.");
            }
        }
    }
}