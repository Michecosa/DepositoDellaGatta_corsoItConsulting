import java.util.Scanner;

public class EsercizioCalcolatrice {

  static double somma(double a, double b) {
    return a + b;
  }

  static double sottrazione(double a, double b) {
    return a - b;
  }

  static double moltiplicazione(double a, double b) {
    return a * b;
  }

  static double divisione(double a, double b) {
    // Controllo per evitare errori
    if (b == 0) {
      System.out.println("\nErrore divisione per zero\n");
      return 0;
    }
    return a / b;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    
    // Uso double per permettere operazioni con numeri decimali
    System.out.print("Inserisci il primo numero: ");
    double n1 = scanner.nextDouble();
    
    System.out.print("Inserisci il secondo numero: ");
    double n2 = scanner.nextDouble();

    System.out.println("\nOPERAZIONI:");
    System.out.println("1 - Somma\n2 - Sottrazione\n3 - Moltiplicazione\n4 - Divisione\n5 - Esci\n");
    System.out.println("-----------------------\n");
    System.out.print("Numero dell'operazione da eseguire: ");
    
    int scelta = scanner.nextInt();

    // Switch per scegliere l'operazione da effettuare sui numeri inseriti
    switch (scelta) {
      case 1:
        System.out.println("Risultato: " + somma(n1, n2));
        break;
      case 2:
        System.out.println("Risultato: " + sottrazione(n1, n2));
        break;
      case 3:
        System.out.println("Risultato: " + moltiplicazione(n1, n2));
        break;
      case 4:
        System.out.println("Risultato: " + divisione(n1, n2));
        break;
      case 5:
        System.out.println("\nArrivederci!\n");
        break;
      default:
        // Gestione di input numerici fuori dal range 1-5
        System.out.println("\nScelta non valida\n");
    }

    System.out.println();

    // Chiudo lo scanner
    scanner.close();
  }
}