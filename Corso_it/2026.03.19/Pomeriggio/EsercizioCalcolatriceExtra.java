import java.util.Scanner;

public class EsercizioCalcolatriceExtra {

  // Somma di tutti i numeri nell'array
  static double somma(double[] numeri) {
    double totale = 0;
    for (double n : numeri) totale += n;
    return totale;
  }

  // Sottrazione di tutti i numeri nell'array
  static double sottrazione(double[] numeri) {
    double totale = numeri[0];
    for (int i = 1; i < numeri.length; i++) totale -= numeri[i];
    return totale;
  }

  // Moltiplicazione di tutti i numeri nell'array
  static double moltiplicazione(double[] numeri) {
    double totale = 1;
    for (double n : numeri) totale *= n;
    return totale;
  }

  // Divisione a catena
  static double divisione(double[] numeri) {
    double totale = numeri[0];
    for (int i = 1; i < numeri.length; i++) {
      if (numeri[i] == 0) {
        System.out.println("Errore divisione per zero");
        return 0;
      }
      totale /= numeri[i];
    }
    return totale;
  }

  // Potenza: prendo solo i primi due numeri dell'array (base ed esponente)
  static double potenza(double[] numeri) {
    if (numeri.length < 2) {
      System.out.println("Errore: servono almeno 2 numeri (base ed esponente)");
      return 0;
    }
    return Math.pow(numeri[0], numeri[1]);
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    
    System.out.print("Su quanti numeri vuoi operare? ");
    int quanti = scanner.nextInt();
    double[] dati = new double[quanti];

    // Ciclo FOR per riempire l'array
    for (int i = 0; i < quanti; i++) {
      System.out.print("Inserisci il numero n." + (i + 1) + ": ");
      dati[i] = scanner.nextDouble();
    }

    System.out.println("\nOPERAZIONI:");
    System.out.println("1 - Somma");
    System.out.println("2 - Sottrazione");
    System.out.println("3 - Moltiplicazione");
    System.out.println("4 - Divisione");
    System.out.println("5 - Potenza (base ^ esponente)");
    System.out.println("6 - Esci");
    System.out.println("-----------------------\n");
    System.out.print("Numero dell'operazione da eseguire: ");

    int scelta = scanner.nextInt();

    switch (scelta) {
      case 1: 
        System.out.println("Risultato: " + somma(dati)); 
        break;
      case 2: 
        System.out.println("Risultato: " + sottrazione(dati)); 
        break;
      case 3: 
        System.out.println("Risultato: " + moltiplicazione(dati)); 
        break;
      case 4: 
        System.out.println("Risultato: " + divisione(dati)); 
        break;
      case 5: 
        System.out.println("Risultato: " + potenza(dati)); 
        break;
      case 6: 
        System.out.println("\nArrivederci!\n"); 
        break;
      default: 
        System.out.println("\nScelta non valida\n");
    }

    System.out.println();

    scanner.close();
  }
}