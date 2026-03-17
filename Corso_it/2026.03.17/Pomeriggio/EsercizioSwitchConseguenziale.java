import java.util.Scanner;

public class EsercizioSwitchConseguenziale {
  public static void main(String[] args) {
    // Creo un oggetto Scanner per leggere l'input dell'utente
    Scanner input = new Scanner(System.in);

    System.out.println("--- ESECUZIONE CONSEGUENZIALE ---");
    System.out.println("1 - Esegui tutto (1 -> 2 -> 3)");
    System.out.println("2 - Esegui da 2 in poi (2 -> 3)");
    System.out.println("3 - Solo Logout");
    System.out.print("\nInserisci il punto di partenza: ");

    int scelta = input.nextInt();
    input.nextLine(); 

    switch (scelta) {
      case 1:
        System.out.println("Profilo utente visualizzato");
        scelta = 2;

      case 2:
        System.out.print("Inserisci un nuovo nome: ");
        String nuovoNome = input.nextLine();
        System.out.println("Nome aggiornato in: " + nuovoNome);
        scelta = 3;

      case 3:
        System.out.println("Logout effettuato");
        break; // Serve per non finire nel default

      default:
        System.out.println("Scelta non valida");
        break;
    }

    // Chiudo lo scanner 
    input.close();
  }
}