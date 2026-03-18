import java.util.Scanner;

public class EsercizioValutazioneVoti {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    // 1. Chiede quanti voti inserire con validazione (deve essere > 0)
    int nVoti = 0;
    while (nVoti <= 0) {
      System.out.print("Quanti voti vuoi inserire? (Inserisci un intero positivo): ");
      nVoti = input.nextInt();
      
      if (nVoti <= 0) {
        System.out.println("Errore: devi inserire un numero maggiore di zero.");
      }
    }

    int votiValidiContatore = 0;

    // 2. Ciclo for per inserire ogni singolo voto
    for (int i = 1; i <= nVoti; i++) {
      System.out.print("Inserisci il voto n." + i + ": ");
      int voto = input.nextInt();

      // 3. Controllo validità del voto (0 - 30)
      if (voto >= 0 && voto <= 30) {
        
        // Se il voto è valido, procediamo con la valutazione
        if (voto >= 18 && voto < 24) {
          System.out.println("Esito: Sufficiente\n");
        } else if (voto >= 24) {
          System.out.println("Esito: Buono o Ottimo\n");
        } else {
          System.out.println("Esito: Insufficiente\n");
        }

        // Incrementiamo il contatore dei voti validi
        votiValidiContatore++;

      } else {
        // 4. Se il voto non è valido (fuori range)
        System.out.println("Voto non valido (non verrà conteggiato)\n");
      }
    }

    // 5. Stampa finale del totale voti validi
    System.out.println("\n--- Riepilogo ---");
    System.out.println("Numero totale di voti validi inseriti: " + votiValidiContatore);

    input.close();
  }
}