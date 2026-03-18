import java.util.Scanner;

public class EsercizioDistributoreAutomatico {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    // Inizializzazione del credito iniziale
    double credito = 10.00;
    int scelta;

    System.out.println("--- Distributore Automatico ---");

    do {
      // Mostra il credito attuale e il menu
      System.out.println("\n\nCredito disponibile: " + credito+ " EUR");
      System.out.println("-----------------------------");
      System.out.println("1. Caffè\t- 1.50 EUR");
      System.out.println("2. Cappuccino\t- 2.00 EUR");
      System.out.println("3. Tè\t\t- 1.00 EUR");
      System.out.println("4. Acqua\t- 0.50 EUR");
      System.out.println("5. Uscire");
      System.out.println("-----------------------------");
      System.out.print("\nScegli un'opzione: ");

      scelta = scanner.nextInt();
      double costo = 0;
      String bevanda = "";

      // Gestione della scelta dell'utente
      switch (scelta) {
        case 1:
          bevanda = "Caffè";
          costo = 1.50;
          break;
        case 2:
          bevanda = "Cappuccino";
          costo = 2.00;
          break;
        case 3:
          bevanda = "Tè";
          costo = 1.00;
          break;
        case 4:
          bevanda = "Acqua";
          costo = 0.50;
          break;
        case 5:
          System.out.println("Uscita in corso...");
          continue; // Torno alla condizione del while
        default:
          System.out.println("Opzione non valida");
          continue;
      }

      // Controllo se il credito è sufficiente
      if (costo > 0) {
        if (credito >= costo) {
          credito -= costo; // Scalo il costo dal credito
          System.out.println("****Erogazione " + bevanda + " completata****");
        } else {
          System.out.println("****Errore: Credito insufficiente per acquistare " + bevanda+"****");
        }
      }

      // Se il credito finisce del tutto, il programma termina automaticamente
      if (credito <= 0) {
        System.out.println("\nHai esaurito il credito");
        break;
      }

    } while (scelta != 5);

    // Messaggio finale
    System.out.printf("\nCredito rimanente: " + credito + " EUR\n");
    System.out.println("Grazie per averci scelto. Arrivederci!\n\n");
    
    // Chiudo le risorse
    scanner.close();
  }
}