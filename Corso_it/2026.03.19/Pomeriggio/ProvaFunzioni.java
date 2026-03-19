public class ProvaFunzioni {

  // 1. Metodo senza parametri e senza ritorno (void)
  static void saluta() {
    System.out.println("Ciao, Java!");
  }

  // 2. Metodo somma standard per numeri interi
  static int somma(int a, int b) {
    return a + b;
  }

  // --- ESEMPI DI OVERLOAD ---

  // Overload 1: Stesso nome, ma accetta numeri con la virgola (double)
  static double somma(double a, double b) {
    return a + b;
  }

  // Overload 2: Stesso nome, ma somma TRE numeri invece di due
  static int somma(int a, int b, int c) {
    return a + b + c;
  }

  // 3. RICORSIONE (Fattoriale)
  static int fattoriale(int n) {
    if (n <= 1) return 1;
    return n * fattoriale(n - 1);
  }

  // 4. PASSAGGIO PER VALORE (Primitivi: il valore originale non cambia)
  static void modifica(int x) {
    x = x + 10;
  }

  // 5. PASSAGGIO DI RIFERIMENTO (Array: la modifica è visibile all'esterno)
  static void modifica(int[] arr) {
    arr[0] = 99; // Questa modifica influisce sul'array originale
  }

  public static void main(String[] args) {
    saluta(); 
    
    // Test Somme
    System.out.println("Somma interi: " + somma(5, 10));
    System.out.println("Somma decimali: " + somma(5.5, 2.3));
    
    // Test Ricorsione
    System.out.println("Fattoriale di 5: " + fattoriale(5));

    // Test Passaggio per Valore (Variabile semplice)
    int num = 5;
    modifica(num);
    System.out.println("Variabile dopo modifica(int): " + num); // Resta 5

    // Test Passaggio di Riferimento (Array)
    int[] numeri = {1, 2, 3};
    modifica(numeri); // Chiama l'overload che accetta int[]
    System.out.println("Array dopo modifica(int[]): " + numeri[0]); // Diventa 99
  }
}