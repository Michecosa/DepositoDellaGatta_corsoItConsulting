import java.util.Scanner;

public class EsercizioFattoriale {
  // 1. Metodo che accetta un intero e calcola il fattoriale
  static long calcolaFattoriale(int n) {
    if (n < 0) return 0; // Gestione per numeri negativi
    long risultato = 1;
    // Ciclo per moltiplicare i numeri da 1 a n
    for (int i = 1; i <= n; i++) {
      risultato *= i;
    }
    return risultato;
  }

  // 2. Overloading: stesso nome, ma accetta una stringa
  static void calcolaFattoriale(String s) {
    System.out.println("Inserisci un numero valido");
  }

  public static void main(String[] args) {
    // Creo l'oggetto Scanner
    Scanner tastiera = new Scanner(System.in);

    System.out.print("Inserisci un numero intero per calcolare il fattoriale: ");
    
    // Controllo se l'input dell'utente è effettivamente un numero
    if (tastiera.hasNextInt()) {
      int numero = tastiera.nextInt();
      long fattoriale = calcolaFattoriale(numero);
      System.out.println("Il fattoriale di " + numero + " è: " + fattoriale);
    } else {
      // Se non è un numero, leggo la stringa errata e uso l'overload
      String inputErrato = tastiera.next();
      calcolaFattoriale(inputErrato);
    }

    // Chiudo lo scanner
    tastiera.close();
  }
}