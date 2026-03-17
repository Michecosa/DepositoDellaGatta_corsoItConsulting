public class ProveCasting {
  public static void main(String[] args) {
    // 1. Casting Automatico (Widening Casting) - Da piccolo a grande
    int myInt = 9;
    double myDouble = myInt; // Automatico: int -> double

    System.out.println("Intero originale: " + myInt);      // Output: 9
    System.out.println("Dopo casting a double: " + myDouble); // Output: 9.0

    // 2. Casting Manuale (Narrowing Casting) - Da grande a piccolo
    // NOTA: Non uso "double" o "int" davanti perché le variabili esistono già
    myDouble = 9.78d;
    myInt = (int) myDouble; // Manuale: double -> int (perde i decimali)

    System.out.println("Double originale: " + myDouble);   // Output: 9.78
    System.out.println("Dopo casting a int: " + myInt);      // Output: 9



    double pi = 3.14159;
    String s = String.valueOf(pi);

    System.out.println("La stringa è: " + s); // Output: "3.14159"
  }
}