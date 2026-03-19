import java.util.ArrayList;
import java.util.Scanner;

public class EsercizioGestioneNomiMenuArrayList {
  public static void main(String[] args) {
    // Inizializzazione ArrayList e Scanner
    ArrayList<String> listaNomi = new ArrayList<>();
    Scanner input = new Scanner(System.in);
    int scelta = 0;

    // 1. Uso un while per mantenere attivo il programma
    while (scelta != 5) {
      // Mostra il menu 
      System.out.println("\n--- MENU ---");
      System.out.println("1 - Aggiungi nome");
      System.out.println("2 - Visualizza tutti i nomi");
      System.out.println("3 - Cerca nome");
      System.out.println("4 - Rimuovi nome");
      System.out.println("5 - Esci");
      System.out.print("Scegli un'opzione: ");
      
      scelta = input.nextInt();
      input.nextLine(); // Pulizia buffer

      // 2. Uso uno switch per gestire le operazioni
      switch (scelta) {
        case 1:
          // Aggiungi nome
          System.out.print("Inserisci il nome da aggiungere: ");
          String nuovoNome = input.nextLine();
          listaNomi.add(nuovoNome);
          System.out.println("Nome inserito con successo.");
          break;

        case 2:
          // Visualizza tutti i nomi
          if (listaNomi.isEmpty()) {
              System.out.println("Lista vuota.");
          } else {
              System.out.println("Nomi in lista: " + listaNomi);
          }
          break;

        case 3:
          // Cerca nome
          System.out.print("Inserisci il nome da cercare: ");
          String nomeDaCercare = input.nextLine();
          if (listaNomi.contains(nomeDaCercare)) {
            System.out.println("Nome trovato.");
          } else {
            System.out.println("Nome non trovato.");
          }
          break;

        case 4:
          // Rimuovi nome
          System.out.print("Inserisci il nome da rimuovere: ");
          String nomeDaRimuovere = input.nextLine();
          // .remove(oggetto) restituisce true se l'elemento è stato trovato e rimosso
          if (listaNomi.remove(nomeDaRimuovere)) {
            System.out.println("Nome rimosso.");
          } else {
            System.out.println("Nome non trovato.");
          }
          break;

        case 5:
          // Esci
          System.out.println("Arrivederci.");
          break;

        default:
          // Gestione di input numerici non previsti
          System.out.println("Opzione non valida, riprova.");
          break;
      }
    }
    
    input.close();
  }
} 