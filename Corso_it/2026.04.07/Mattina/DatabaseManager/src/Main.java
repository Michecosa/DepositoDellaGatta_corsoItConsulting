import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // entrambe le variabili puntano alla stessa istanza
        DatabaseManager db1 = DatabaseManager.getInstance();
        DatabaseManager db2 = DatabaseManager.getInstance();
        System.out.println("db1 == db2 ? " + (db1 == db2));   // true

        // Connessione al DB
        try {
            db1.connect(); // connessioni attive: 1
            db1.connect(); // connessioni attive: 2
            db1.connect(); // connessioni attive: 3
            System.out.println("Totale connessioni: " + db1.getConnectionCount());

            db1.initSchema();
        } catch (SQLException e) {
            System.err.println("[ERRORE] " + e.getMessage());
        }

        // Menu interattivo
        Scanner sc = new Scanner(System.in);
        DatabaseManager db = DatabaseManager.getInstance(); // stessa istanza
        int utenteId = -1;
        String utenteNome = null;

        boolean inEsecuzione = true;

        while (inEsecuzione) {
            System.out.println("\n========================================");
            System.out.println("           MENU PRINCIPALE");
            System.out.println("========================================");
            if (utenteNome == null) {
                System.out.println("  [U] Login / Registrazione utente");
            } else {
                System.out.println("  Utente attivo: " + utenteNome);
                System.out.println("  [1] Salva un dato");
                System.out.println("  [2] Richiama un dato per chiave");
                System.out.println("  [3] Elenca tutti i miei dati");
                System.out.println("  [U] Cambia utente");
            }
            System.out.println("  [I] Info connessioni");
            System.out.println("  [Q] Esci");
            System.out.println("========================================");
            System.out.print("Scelta: ");

            String scelta = sc.nextLine().trim().toUpperCase();

            try {
                switch (scelta) {

                    case "U":
                        System.out.print("Inserisci username: ");
                        String username = sc.nextLine().trim();
                        if (username.isEmpty()) {
                            System.out.println("Username non valido");
                            break;
                        }
                        utenteId = db.registraUtente(username);
                        utenteNome = username;
                        System.out.println("Benvenuto/a, " + utenteNome + "! (id=" + utenteId + ")");
                        break;

                    case "1":
                        if (utenteId == -1) { 
                            System.out.println("Prima effettua il login [U]"); 
                            break; 
                        }
                        System.out.print("Chiave: ");
                        String chiave = sc.nextLine().trim();
                        System.out.print("Valore: ");
                        String valore = sc.nextLine().trim();
                        db.salvaDato(utenteId, chiave, valore);
                        break;

                    case "2":
                        if (utenteId == -1) { 
                            System.out.println("Prima effettua il login [U]"); 
                            break; 
                        }
                        System.out.print("Chiave da cercare: ");
                        String k = sc.nextLine().trim();
                        String found = db.richiamaDato(utenteId, k);
                        if (found != null)
                            System.out.println("Risultato -> " + k + " = " + found);
                        else
                            System.out.println("Chiave '" + k + "' non trovata");
                        break;

                    case "3":
                        if (utenteId == -1) { System.out.println("Prima effettua il login [U]"); break; }
                        System.out.println("Dati di " + utenteNome + ":");
                        db.elencaDati(utenteId);
                        break;

                    case "I":
                        System.out.println("Connessioni totali : " + db.getConnectionCount());
                        break;

                    case "Q":
                        db.disconnect();
                        System.out.println("Arrivederci!\n");
                        inEsecuzione = false;
                        break;

                    default:
                        System.out.println("Scelta non valida");
                }
            } catch (SQLException e) {
                System.err.println("[SQL ERRORE] " + e.getMessage());
            }
        }

        sc.close();
    }
}