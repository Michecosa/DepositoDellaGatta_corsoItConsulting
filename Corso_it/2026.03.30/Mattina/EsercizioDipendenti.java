import java.util.ArrayList;
import java.util.Scanner;

// Classe "Modello": definisce la struttura dei dati
class Dipendente {
    String nome;
    double stipendio;
    String dipartimento;

    // Costruttore per inizializzare l'oggetto nello Heap
    Dipendente(String nome, double stipendio, String dipartimento) {
        this.nome = nome;
        this.stipendio = stipendio;
        this.dipartimento = dipartimento;
    }
}

public class EsercizioDipendenti {
    public static void main(String[] args) {
        System.out.println("Caffettino preso");

        Scanner input = new Scanner(System.in);
        
        // La lista memorizza riferimenti a oggetti Dipendente
        ArrayList<Dipendente> dipendenti = new ArrayList<Dipendente>();
        boolean continua = true;

        while (continua) {
            // Menu di navigazione
            System.out.println("\n--- MENU DIPENDENTI ---");
            System.out.println("1 - Aggiungi dipendente");
            System.out.println("2 - Visualizza dipendenti");
            System.out.println("3 - Modifica stipendio");
            System.out.println("4 - Elimina dipendente");
            System.out.println("5 - Esci");
            System.out.print("Scelta: ");
            int scelta = input.nextInt();
            input.nextLine(); // Pulizia buffer dopo nextInt()

            switch (scelta) {
                case 1:
                    // CREATE: Creazione e aggiunta alla lista
                    System.out.print("Inserisci nome: ");
                    String nome = input.nextLine();

                    System.out.print("Inserisci stipendio: ");
                    double stipendio = input.nextDouble();
                    input.nextLine(); // Pulizia buffer

                    System.out.print("Inserisci dipartimento: ");
                    String dipartimento = input.nextLine();

                    // Viene creato l'oggetto e il suo indirizzo è salvato nella lista
                    Dipendente nuovo = new Dipendente(nome, stipendio, dipartimento);
                    dipendenti.add(nuovo);

                    System.out.println("Dipendente aggiunto con successo");
                    break;

                case 2:
                    // READ: Scorrimento della lista dinamica
                    if (dipendenti.size() == 0) {
                        System.out.println("Nessun dipendente presente");
                    } else {
                        System.out.println("\n--- LISTA DIPENDENTI ---");
                        for (int i = 0; i < dipendenti.size(); i++) {
                            // Si accede all'oggetto tramite l'indice i
                            System.out.println((i + 1) + " - Nome: " + dipendenti.get(i).nome
                                    + ", Stipendio: " + dipendenti.get(i).stipendio
                                    + ", Dipartimento: " + dipendenti.get(i).dipartimento);
                        }
                    }
                    break;

                case 3:
                    // UPDATE: Modifica di un oggetto esistente
                    if (dipendenti.size() == 0) {
                        System.out.println("Nessun dipendente da modificare");
                    } else {
                        System.out.print("Inserisci il nome del dipendente da modificare: ");
                        String nomeDaModificare = input.nextLine();
                        boolean trovato = false;

                        for (int i = 0; i < dipendenti.size(); i++) {
                            // Confronto tra stringhe usando equalsIgnoreCase
                            if (dipendenti.get(i).nome.equalsIgnoreCase(nomeDaModificare)) {
                                System.out.print("Inserisci il nuovo stipendio: ");
                                double nuovoStipendio = input.nextDouble();
                                input.nextLine(); // Pulizia buffer

                                // Modificando l'attributo, la modifica si riflette nella lista 
                                // perché stiamo agendo sullo stesso oggetto in memoria
                                dipendenti.get(i).stipendio = nuovoStipendio;
                                System.out.println("Stipendio aggiornato.");
                                trovato = true;
                            }
                        }
                        if (!trovato) System.out.println("Dipendente non trovato");
                    }
                    break;

                case 4:
                    // DELETE: Rimozione di un riferimento dalla lista
                    if (dipendenti.size() == 0) {
                        System.out.println("Nessun dipendente da eliminare");
                    } else {
                        System.out.print("Inserisci il nome del dipendente da eliminare: ");
                        String nomeDaEliminare = input.nextLine();
                        boolean trovato = false;

                        for (int i = 0; i < dipendenti.size(); i++) {
                            if (dipendenti.get(i).nome.equalsIgnoreCase(nomeDaEliminare)) {
                                // Rimuove l'elemento e sposta automaticamente gli altri (gestione dinamica)
                                dipendenti.remove(i);
                                System.out.println("Dipendente eliminato");
                                trovato = true;
                                break; 
                            }
                        }
                        if (!trovato) System.out.println("Dipendente non trovato");
                    }
                    break;

                case 5:
                    continua = false;
                    System.out.println("Programma terminato");
                    break;

                default:
                    System.out.println("Scelta non valida");
            }
        }
        input.close();
    }
}