public class EsercizioFattoriale {
  // 1. Metodo che accetta un intero e calcola il fattoriale
  static long calcolaFattoriale(int n) {
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
    // Test con un numero intero 
    int numero = 5;
    long fattoriale = calcolaFattoriale(numero); // calcola 5! => 1*2*3*4*5
    System.out.println("\nIl fattoriale di " + numero + " è: " + fattoriale);

    // Test con una stringa
    calcolaFattoriale("cinque");

    System.out.println();
  }
}