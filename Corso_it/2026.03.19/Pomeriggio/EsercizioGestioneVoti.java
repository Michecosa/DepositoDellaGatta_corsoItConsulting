import java.util.Scanner;

public class EsercizioGestioneVoti {

  // Metodo per trovare il voto massimo
  public static void trovaMax(double[] listaVoti) {
    double max = listaVoti[0]; // Assegno il primo elemento come massimo
    for (int i = 0; i < listaVoti.length; i++) {
      // Se il voto appena letto è più grande di max...
      if (listaVoti[i] > max) {
        max = listaVoti[i]; // ...allora il nuovo massimo diventa questo voto
      }
    }
    System.out.println("Il voto più alto è: " + max);
  }

  // Metodo per trovare il voto minimo
  public static void trovaMin(double[] listaVoti) {
    double min = listaVoti[0]; // Assegno il primo elemento come minimo
    for (int i = 0; i < listaVoti.length; i++) {
      // Se il voto appena letto è più piccolo di min...
      if (listaVoti[i] < min) {
        min = listaVoti[i]; // ...allora il nuovo minimo diventa questo voto
      }
    }
    System.out.println("Il voto più basso è: " + min);
  }

  // Metodo per calcolare la media dei voti
  public static double calcolaMedia(double[] listaVoti) {
    double somma = 0;
    for (int i = 0; i < listaVoti.length; i++) {
      somma += listaVoti[i];
    }
    return somma / listaVoti.length;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Quanti voti vuoi inserire? ");
    int n = scanner.nextInt();

    double[] voti = new double[n];
    for (int i = 0; i < n; i++) {
      System.out.print("Voto n." + (i + 1) + ": ");
      voti[i] = scanner.nextDouble();
    }

    int scelta;
    do {
      System.out.println("\n1-Media, 2-Max, 3-Min, 4-Promozione, 5-Esci");
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
          if (m >= 6) System.out.println("Promosso");
          else System.out.println("Bocciato");
          break;
        }
    } while (scelta != 5);

    System.out.println();
    scanner.close();
  }
}