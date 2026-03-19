import java.util.Scanner;

public class EsercizioGestioneVotiExtra {

  // --- EXTRA: METODO INSEGNANTE ---
  // Restituisce l'array di voti popolato solo se la password è corretta
  public static void metodoInsegnante(double[] listaVoti, Scanner scanner) {
    System.out.print("Inserisci password docente per assegnare i voti: ");
    String password = scanner.next();

    if (password.equals("admin123")) {
      System.out.println("Accesso garantito. Inserisci i voti:");
      for (int i = 0; i < listaVoti.length; i++) {
        System.out.print("Voto n." + (i + 1) + ": ");
        listaVoti[i] = scanner.nextDouble();
      }
    } else {
      System.out.println("Accesso negato! Solo l'insegnante può assegnare voti");
    }
  }

  public static void trovaMax(double[] listaVoti) {
    double max = listaVoti[0]; 
    for (int i = 0; i < listaVoti.length; i++) {
      if (listaVoti[i] > max) max = listaVoti[i];
    }
    System.out.println("Il voto più alto è: " + max);
  }

  public static void trovaMin(double[] listaVoti) {
    double min = listaVoti[0]; 
    for (int i = 0; i < listaVoti.length; i++) {
      if (listaVoti[i] < min) min = listaVoti[i];
    }
    System.out.println("Il voto più basso è: " + min);
  }

  public static double calcolaMedia(double[] listaVoti) {
    double somma = 0;
    for (int i = 0; i < listaVoti.length; i++) {
      somma += listaVoti[i];
    }
    return somma / listaVoti.length;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Quanti voti vuoi gestire? ");
    int n = scanner.nextInt();

    double[] voti = new double[n];
    
    // Richiamo il metodo insegnante subito per caricare i dati
    metodoInsegnante(voti, scanner);

    int scelta;
    do {
      System.out.println("\n--- MENU ---");
      System.out.println("1-Media, 2-Max, 3-Min, 4-Promozione, 5-Esci");
      System.out.print("Scelta: ");
      scelta = scanner.nextInt();

      switch (scelta) {
        case 1: 
          System.out.println("Media: " + calcolaMedia(voti)); 
          break;
        case 2: 
          trovaMax(voti); 
          break;
        case 3: 
          trovaMin(voti); 
          break;
        case 4: 
          double m = calcolaMedia(voti);
          if (m >= 6) System.out.println("Risultato: Promosso");
          else System.out.println("Risultato: Bocciato");
          break;
        case 5:
          System.out.println("Arrivederci!");
          break;
      }
    } while (scelta != 5);

    scanner.close();
  }
}