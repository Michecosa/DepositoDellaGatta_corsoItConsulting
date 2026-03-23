import java.util.Scanner;

public class EsercizioIndovinaNumero {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    
    // --- Variabili per il gioco ---
    int numeroSegreto;
    int tentativiMassimi = 5;
    int tentativiEffettuati = 0;
    boolean indovinato = false;

    // Inserimento del numero da parte del Giocatore 1
    do {
      System.out.print("Giocatore 1, inserisci un numero tra 1 e 100: ");
      numeroSegreto = input.nextInt();
    } while (numeroSegreto < 1 || numeroSegreto > 100);

    // Pulizia buffer dopo nextInt()
    input.nextLine(); 

    // Creo spazio vuoto per nascondere il numero
    for (int i = 0; i < 50; i++) System.out.println();

    System.out.println("Giocatore 2, puoi iniziare! Hai 5 tentativi\n");

    // Ciclo di vita del gioco
    while (tentativiEffettuati < tentativiMassimi && !indovinato) {
      tentativiEffettuati++;
      System.out.print("Tentativo n." + tentativiEffettuati + " (o scrivi 'mi arrendo'): ");
      String scelta = input.nextLine();

      // Caso in cui l'utente scrive "mi arrendo"
      if (scelta.toLowerCase().equals("mi arrendo")) {
        System.out.println("Sfigato\n");
        break;
      }

      int tentativo = Integer.parseInt(scelta);

      // Controllo del tentativo
      if (tentativo == numeroSegreto) {
        System.out.println("Indovinato!\n");
        indovinato = true;
      } else if (tentativo < numeroSegreto) {
        System.out.println("Troppo basso\n");
      } else {
        System.out.println("Troppo alto\n");
      }
    }

    // Esito finale
    if (!indovinato) {
      System.out.println("Il numero era: " + numeroSegreto+"\n");
    }

    // Chiudo le risorse
    input.close();
  }
}