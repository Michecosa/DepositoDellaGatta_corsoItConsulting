public class EserciziFunzioni {

  // ESERCIZIO 1: Funzione Base
  static int somma(int a, int b) {
    return a + b;
  }

  // ESERCIZIO 2: Sfida sull'Overloading
  // 1. Due interi
  static int multiply(int a, int b) {
    return a * b;
  }
  // 2. Tre double
  static double multiply(double a, double b, double c) {
    return a * b * c;
  }

  // ESERCIZIO 3: Ricorsione - Somma dei Numeri Naturali
  static int sommaNaturali(int n) {
    // Se n è 1, restituisce 1
    if (n <= 1) {
      return 1;
    }
    // Formula: S(n) = n + S(n-1)
    return n + sommaNaturali(n - 1);
  }

  // ESERCIZIO 4: Passaggio per Valore & Riferimento

  // 4.1 - Metodo che modifica una variabile primitiva
  static int modificaPrimitiva(int x) {
    int y = x + 100;
    return y;
  }

  // 4.2 - Metodo che modifica un array
  static void modificaArray(int[] arr) {
    if (arr.length > 0) {
      arr[0] = 33;
    }
  }

  public static void main(String[] args) {
    // Esercizio 1 - Test
    System.out.println("\nESERCIZIO 1");
    System.out.println("Somma base: " + somma(5, 3));
    
    // Esercizio 2 - Test
    System.out.println("--------------------------");
    System.out.println("\nESERCIZIO 2");
    System.out.println("Multiply (int): " + multiply(4, 5));
    System.out.println("Multiply (double): " + multiply(2.5, 2.0, 3.0));
    
    // Esercizio 3 - Test
    System.out.println("--------------------------");
    System.out.println("\nESERCIZIO 3");
    int n = 5;
    System.out.println("Somma naturali dei primi " + n + " numeri: " + sommaNaturali(n));
    
    // Esercizio 4 - Test
    
    // 4.1 - Variabile primitiva
    System.out.println("--------------------------");
    System.out.println("\nESERCIZIO 4");
    int valore = 10;
    System.out.println("Primitiva PRIMA: " + valore);
    valore = modificaPrimitiva(valore);
    System.out.println("Primitiva DOPO: " + valore); // Diventa 110

    // 4.1 - Array
    int[] mioArray = {8, 2, 55};
    System.out.println("\nmioArray[0] PRIMA: " + mioArray[0]);
    modificaArray(mioArray);
    System.out.println("mioArray[0] DOPO: " + mioArray[0]); // Diventa 33
    System.out.println("--------------------------");
  }
}