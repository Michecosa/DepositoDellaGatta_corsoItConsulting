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

  public static void main(String[] args) {
    saluta(); 
    
    // Chiama il metodo somma(int, int)
    int r1 = somma(5, 10);
    System.out.println("Somma interi: " + r1);
    
    // Chiama il metodo somma(double, double)
    double r2 = somma(5.5, 2.3);
    System.out.println("Somma decimali: " + r2);
    
    // Chiama il metodo somma(int, int, int)
    int r3 = somma(10, 20, 30);
    System.out.println("Somma di tre numeri: " + r3);
  }
}