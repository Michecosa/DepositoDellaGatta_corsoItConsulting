import java.util.Scanner;

public class ProvaIterazioni {
  public static void main(String[] args) {
    // 1. Dichiarazione e inizializzazione della variabile di controllo
    int i = 1; 

    // 2. Ciclo while: la condizione (i <= 5) viene verificata PRIMA di ogni esecuzione.
    // Il ciclo continua finché 'i' è minore o uguale a 5.
    while (i <= 5) {
        
      // 3. Incremento: 'i' aumenta di 1 immediatamente all'inizio del blocco.
      // Nota: questo influenzerà il valore stampato subito dopo.
      i++; 
      
      // 4. Stampa a video il valore corrente di 'i'.
      // Poiché l'incremento avviene prima della stampa, il primo numero stampato sarà 2.
      System.out.println(i);
    }

    // Alla fine del ciclo, il valore finale di 'i' sarà 6.




    /* ----------------------------------------------------------------------------------- */




    // Esempio di ciclo controllato da una condizione logica (booleana)
    // Questo metodo permette di interrompere il ciclo in base a un evento (es. input utente)

    Scanner scanner = new Scanner(System.in);

    // 1. Inizializzazione della variabile "flag" a true per avviare il ciclo
    boolean continua = true; 

    // 2. Il ciclo continua finché la variabile 'continua' rimane true
    while (continua) {

      // Chiediamo all'utente di inserire un valore
      System.out.print("Inserisci un numero (0 per uscire): ");
      
      // Leggiamo l'input dell'utente (presuppone la presenza di un oggetto Scanner)
      int numero = scanner.nextInt();

      // 3. Controllo del valore inserito
      if (numero == 0) {
        // Se l'utente digita 0, cambiamo lo stato della variabile di controllo
        continua = false; // Questo renderà la condizione del while falsa alla prossima verifica
      } else {
        // Altrimenti, confermiamo il numero inserito e il ciclo ricomincia
        System.out.println("Hai inserito: " + numero);
      }
    }

    // 4. Messaggio finale stampato solo dopo che il ciclo è terminato
    System.out.println("Programma terminato.");





    /* ----------------------------------------------------------------------------------- */




    int scelta;

    // 1. Inizio del blocco DO: il codice qui dentro viene eseguito PRIMA del controllo
    do {
      System.out.println("--- MENU UTENTE ---");
      System.out.println("1. Saluta");
      System.out.println("2. Opzioni");
      System.out.println("0. Esci");
      System.out.print("Scegli un'opzione: ");
      
      // Leggiamo la scelta dell'utente
      scelta = scanner.nextInt();

      // 2. Logica di risposta basata sulla scelta
      if (scelta == 1) {
        System.out.println("\nCiao! Benvenuto nel programma.\n");
      } else if (scelta == 2) {
        System.out.println("\nHai selezionato le impostazioni.\n");
      } else if (scelta != 0) {
        System.out.println("\nOpzione non valida, riprova.\n");
      }

    // 3. Condizione WHILE: se l'utente NON ha digitato 0, il ciclo ricomincia da 'do'
    } while (scelta != 0);

    // 4. Messaggio finale
    System.out.println("\nUscita in corso... Arrivederci!\n");
    



    /* ----------------------------------------------------------------------------------- */




    System.out.print("\n--- ESERCIZIO TABELLINA ---\n");
    System.out.print("Inserisci un numero per vederne la tabellina: ");
    int numeroTabellina = scanner.nextInt();

    System.out.println("Tabellina del " + numeroTabellina + ":");

    // Inizializzazione; Condizione; Incremento
    for (int j = 1; j <= 10; j++) {
      // Stampa il calcolo riga per riga
      System.out.println(numeroTabellina + " x " + j + " = " + (numeroTabellina * j));
    }
    System.out.println("-------------------------------------------------------\n");

    scanner.close();
  }
}