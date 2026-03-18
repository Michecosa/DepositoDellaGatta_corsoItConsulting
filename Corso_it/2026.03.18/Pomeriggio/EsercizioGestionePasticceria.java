import java.util.Scanner;

public class EsercizioGestionePasticceria {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    // 1. Chiediamo quanti dolci ordinare (con limite massimo di 10)
    int numeroDolci = 0;
    while (numeroDolci <= 0 || numeroDolci > 10) {
      System.out.print("Quanti tipi di dolci vuoi ordinare? (max 10): ");
      numeroDolci = scanner.nextInt();
      if (numeroDolci <= 0 || numeroDolci > 10) {
        System.out.println("Errore: inserire un numero tra 1 e 10.");
      }
    }
    
    // Pulisco il buffer dopo nextInt()
    scanner.nextLine(); 

    // 2. Dichiarazione array paralleli
    String[] nomiDolci = new String[numeroDolci];
    int[] quantitaDolci = new int[numeroDolci];

    // 3. Raccolta dati
    for (int i = 0; i < numeroDolci; i++) {
      System.out.print("\nInserisci il nome del dolce n." + (i + 1) + ": ");
      nomiDolci[i] = scanner.nextLine();

      // Validazione quantità (ciclo while per gestire errori)
      int qta = 0;
      while (qta <= 0) {
        System.out.print("Inserisci la quantita per " + nomiDolci[i] + ": ");
        qta = scanner.nextInt();
        if (qta <= 0) {
          System.out.println("Errore: la quantita' deve essere maggiore di 0\n");
        }
      }
      quantitaDolci[i] = qta;
      scanner.nextLine(); // Pulizia buffer
    }

    // 4. Stampa elenco e calcolo totale
    System.out.println("\n--- RIEPILOGO ORDINE ---");
    int totaleQuantita = 0;

    for (int i = 0; i < numeroDolci; i++) {
      System.out.println("- " + nomiDolci[i] + ": " + quantitaDolci[i] + " pezzi");
      totaleQuantita += quantitaDolci[i];
    }

    System.out.println("------------------------------------");
    System.out.println("Quantita' totale dolci ordinati: " + totaleQuantita+"\n\n");

    scanner.close();
  }
}