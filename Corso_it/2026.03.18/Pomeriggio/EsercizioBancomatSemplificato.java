import java.util.Scanner;

public class EsercizioBancomatSemplificato {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    // Forza lo scanner a usare il punto per le cifre decimali
    scanner.useLocale(java.util.Locale.US);
    
    /* Nota di chiarimento
      Mi dava fastidio che i numeri fossero stampati col punto (es. 1000.0)
      e che per inserirne di nuovi dovevo usare la virgola (es. 100,5)

      Quindi ho usato:
        scanner.useLocale(java.util.Locale.US);
      
      Così ho una sorta di coerenza visiva
    */

    // Stato iniziale del bancomat
    double saldo = 1000.0;
    int scelta = 0;

    System.out.println("--- SISTEMA BANCARIO ---");

    // Il ciclo continua finché l'utente non sceglie l'opzione 4 (Esci)
    while (scelta != 4) {
      System.out.println("\nSeleziona un'operazione:");
      System.out.println("-----------------------------");
      System.out.println("1 - Visualizza saldo");
      System.out.println("2 - Preleva");
      System.out.println("3 - Deposita");
      System.out.println("4 - Esci");
      System.out.println("-----------------------------");
      System.out.print("\nScelta: ");

      scelta = scanner.nextInt();

      switch (scelta) {
        case 1:
          // Visualizzazione semplice del saldo
          System.out.println("Il tuo saldo attuale è: "+ saldo+" EUR\n");
          break;

        case 2:
          // Logica di prelievo con controllo fondi
          System.out.print("Inserisci l'importo da prelevare: ");
          double importoPrelievo = scanner.nextDouble();

          if (importoPrelievo > saldo) {
            System.out.println("Errore: Fondi insufficienti!");
          } else if (importoPrelievo <= 0) {
            System.out.println("Errore: Inserisci un importo valido.");
          } else {
            saldo -= importoPrelievo;
            System.out.println("Prelievo effettuato. Nuovo saldo: "+ saldo+" EUR\n");
          }
          break;

        case 3:
          // Logica di deposito
          System.out.print("Inserisci l'importo da depositare: ");
          double importoDeposito = scanner.nextDouble();

          if (importoDeposito > 0) {
            saldo += importoDeposito;
            System.out.println("Deposito effettuato. Nuovo saldo: "+ saldo+" EUR\n");
          } else {
            System.out.println("Errore: Inserisci un importo positivo.");
          }
          break;

        case 4:
          // Messaggio di chiusura
          System.out.println("Arrivederci!");
          break;

        default:
          // Gestione di numeri non presenti nel menu
          System.out.println("Scelta non valida. Riprova");
          break;
      }
    }

    scanner.close();
  }
}