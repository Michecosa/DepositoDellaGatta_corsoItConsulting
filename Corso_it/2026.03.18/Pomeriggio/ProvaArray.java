public class ProvaArray {
  public static void main(String[] args) {

    // 1. DICHIARAZIONE E ALLOCAZIONE
    // Creiamo un array di interi chiamato 'numeri'.
    // Usiamo 'new int[5]' per riservare spazio in memoria per 5 numeri interi.
    // In questo momento, l'array contiene tutti zeri [0, 0, 0, 0, 0].
    int[] numeri = new int[5]; 

    // 2. INIZIALIZZAZIONE DIRETTA (LITERAL)
    // Se conosciamo già i valori, possiamo caricarli subito tra parentesi graffe.
    // La dimensione viene calcolata automaticamente in base a quanti elementi inseriamo.
    int[] valori = {10, 20, 30, 40, 50}; 

    // 3. ACCESSO AGLI ELEMENTI (NOTA SULL'INDICE)
    // RICORDA: in Java il conteggio parte sempre da 0!
    // valori[0] è il primo elemento (10)
    // valori[4] è l'ultimo elemento (50)
    
    System.out.println("Il primo elemento di 'valori' è: " + valori[0]);

    // 4. PROPRIETÀ LENGTH
    // Ogni array ha una proprietà chiamata 'length' che ci dice quanto è grande.
    System.out.println("L'array 'numeri' può ospitare " + numeri.length + " elementi.");




    /* ------------------------------------------------------------------------------------ */




    // Scorrere un array con i cicli
    System.out.println("\n--- Scorrimento Array ---");

    // Uso del ciclo FOR classico:
    // L'indice 'i' parte da 0 e arriva fino a 'lunghezza - 1'
    System.out.print("Contenuto di 'valori' (ciclo for): ");
    for (int i = 0; i < valori.length; i++) {
      System.out.print(valori[i] + " ");
    }

    System.out.println(); // Va a capo

    // Uso del ciclo FOR-EACH (più rapido se devi solo leggere i dati):
    // "Per ogni intero 'v' presente nell'array 'valori'..."
    System.out.print("Contenuto di 'valori' (ciclo for-each): ");
    for (int v : valori) {
        System.out.print(v + " ");
    }
    
    System.out.println("\n------------------------------------------------------");





    /* ------------------------------------------------------------------------------------ */



    // Riempimento e stampa dinamica
    System.out.println("\n--- Riempimento Dinamico ---");

    // Dichiarazione di un array di 5 elementi (inizialmente tutti 0)
    int[] num = new int[5]; 

    // Riempimento dell'array con i numeri 1-5 usando un ciclo
    for (int i = 0; i < num.length; i++) {
      // Assegno alla posizione 'i' il valore 'i + 1'
      // Esempio: quando i=0, l'array riceve 0+1 = 1
      num[i] = i + 1;
    }

    // Stampa degli elementi dell'array
    System.out.println("Elementi dell'array 'num':");
    for (int i = 0; i < num.length; i++) {
      System.out.print(num[i] + " ");
    }

    System.out.println("\n------------------------------------------------------");





    /* ------------------------------------------------------------------------------------ */


    

    // Matrici (Array Bidimensionali)
    System.out.println("\n--- Esempio Matrici ---");

    // 1. DICHIARAZIONE E ALLOCAZIONE
    // Creiamo una matrice 3x3 (3 righe e 3 colonne).
    // In totale può ospitare 9 elementi (3 * 3).
    // int[][] matrice = new int[3][3];

    // 2. INIZIALIZZAZIONE DIRETTA
    // Ogni riga è racchiusa tra parentesi graffe all'interno di una graffa principale.
    int[][] matricePredefinita = {
    // Colonna 0  1  2
              {1, 2, 3}, // Riga 0
              {4, 5, 6}, // Riga 1
              {7, 8, 9}  // Riga 2
    };

    // 3. ACCESSO AGLI ELEMENTI
    // Per leggere un valore servono DUE indici: [riga][colonna]
    int valoreCentrale = matricePredefinita[1][1]; // Prende il numero 5
    System.out.println("Il valore al centro della matrice è : " + valoreCentrale);

    // 4. STAMPA DELLA MATRICE (Ciclo annidato)
    System.out.println("Contenuto della matrice completa:");
    for (int i = 0; i < matricePredefinita.length; i++) {        // Ciclo per le righe
      for (int j = 0; j < matricePredefinita[i].length; j++) { // Ciclo per le colonne
        System.out.print(matricePredefinita[i][j] + " ");
      }
      System.out.println(); // Va a capo dopo ogni riga
    }

    System.out.println("------------------------------------------------------");
  }
}