import java.util.Scanner;

public class EsercizioSwitch {
  public static void main(String[] args) {
    // Creo un oggetto Scanner per leggere l'input dell'utente
    Scanner input = new Scanner(System.in);

    // 1. Stampa menu testuale
    System.out.println("--- MENU UTENTE ---");
    System.out.println("1 - Visualizza profilo");
    System.out.println("2 - Modifica nome");
    System.out.println("3 - Logout");
    System.out.print("\nInserisci un numero per scegliere: ");

    // 2. Chiedo all'utente di inserire un numero
    int scelta = input.nextInt();
    
    // Consumo il carattere "invio" residuo dopo l'inserimento del numero
    input.nextLine(); 

    // 3. Switch per gestire la scelta
    switch (scelta) {
      case 1:
        // Comportamento per l'opzione 1: Visualizza profilo
        System.out.println("Profilo utente visualizzato");
        break;

      case 2:
        // Comportamento per l'opzione 2: Modifica nome
        System.out.print("Inserisci un nuovo nome: ");
        String nuovoNome = input.nextLine();
        System.out.println("Nome aggiornato in: " + nuovoNome);
        break;

      case 3:
        // Comportamento per l'opzione 3: Logout
        System.out.println("Logout effettuato");
        break;

      default:
        // Gestione di qualsiasi altro valore (scelta non valida)
        System.out.println("Scelta non valida");
        break;
    }

    // Chiudo lo scanner 
    input.close();
  }
}
