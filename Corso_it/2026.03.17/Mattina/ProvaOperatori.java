public class ProvaOperatori {
  public static void main(String[] args) {
    int sum1 = 100 + 50; // 150 (100 + 50)
    int sum2 = sum1 + 250; // 400 (150 + 250)
    int sum3 = sum2 + sum2; // 800 (400 + 400)

    System.out.println("sum1: "+ sum1);
    System.out.println("sum2: "+ sum2);
    System.out.println("sum3: "+ sum3);


    int num  = 10;
    num += 1;
    num -= 2;
    num *= 3;
    num /= 3;

    System.out.println("num: " + num);
    System.out.println(num++); // Post-incremento
    System.out.println(++num); // Pre-incremento


    int x = 10;
    int y = 20;

    // Uguale a (==) 
    System.out.println(x == y); // false: 10 non è uguale a 20

    // Diverso da (!=)
    System.out.println(x != y); // true: 10 è effettivamente diverso da 20

    // Maggiore di (>) e Minore di (<)
    System.out.println(x > y);  // false: 10 non è maggiore di 20
    System.out.println(x < y);  // true: 10 è minore di 20

    // Maggiore o uguale (>=) e Minore o uguale (<=)
    System.out.println(x >= 10); // true: x è 10, quindi è "uguale"
    System.out.println(y <= 15); // false: 20 non è minore né uguale a 15

    // Esempio con char (confronta i codici ASCII)
    System.out.println('a' < 'b'); // true: in ordine alfabetico 'a' viene prima





    int eta = 20;
    boolean haDocumento = true;
    boolean isSospeso = false;

    // 1. AND (&&) - Entrambi devono essere TRUE
    // "Entri se hai almeno 18 anni E hai il documento"
    boolean puoEntrare = (eta >= 18) && haDocumento;
    System.out.println("Può entrare? " + puoEntrare); // true

    // 2. OR (||) - Almeno uno deve essere TRUE
    // "Entri se sei maggiorenne OPPURE se sei accompagnato (anche senza documento)"
    boolean accompagnato = false;
    boolean accessoSpeciale = (eta >= 18) || accompagnato;
    System.out.println("Accesso speciale? " + accessoSpeciale); // true

    // 3. NOT (!) - Inverte il valore
    // "Se NON sei sospeso, il tuo account è attivo"
    boolean accountAttivo = !isSospeso;
    System.out.println("Account attivo? " + accountAttivo); // true

    // Esempio combinato:
    // "Entri se (Sei maggiorenne E hai il documento) E NON sei sospeso"
    boolean controlloFinale = (eta >= 18 && haDocumento) && !isSospeso;
    System.out.println("Controllo finale passato: " + controlloFinale); // true
  }
}