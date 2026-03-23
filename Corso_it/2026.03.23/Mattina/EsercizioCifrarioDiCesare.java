import java.util.Scanner;

public class EsercizioCifrarioDiCesare {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Inserisci la parola: ");
    String parola = scanner.nextLine().toUpperCase();

    System.out.print("Inserisci la chiave: ");
    int chiave = scanner.nextInt();

    String criptata = cripta(parola, chiave);
    System.out.println("\nParola Criptata: " + criptata);

    String decriptata = decripta(criptata, chiave);
    System.out.println("Parola Decriptata: " + decriptata+"\n");
    
    scanner.close();
  }

  // Metodo per criptare
  public static String cripta(String testo, int chiave) {
    char[] caratteri = testo.toCharArray();

    // Normalizzo la chiave nel range 0-25
    int chiaveRidotta = ((chiave % 26) + 26) % 26;

    for (int i = 0; i < caratteri.length; i++) {
      char c = caratteri[i];

      if (c >= 'A' && c <= 'Z') {
        // Calcolo la nuova posizione usando il modulo
        // (c - 'A') porta il carattere in un range 0-25
        // Aggiungo la chiave, applico % 26 e riporto in ASCII aggiungendo 'A'
        caratteri[i] = (char) ('A' + (c - 'A' + chiaveRidotta) % 26);
      }
    }
    return new String(caratteri);
  }

  // Metodo per decriptare
  public static String decripta(String testo, int chiave) {
    // Passo la chiave negativa per decriptare
    return cripta(testo, -chiave);
  }
}