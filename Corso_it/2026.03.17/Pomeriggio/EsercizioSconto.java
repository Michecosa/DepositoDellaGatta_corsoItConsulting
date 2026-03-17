import java.util.Scanner;

public class EsercizioSconto {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);

    // Input dell'utente
    System.out.print("Inserisci il codice sconto: ");
    String codice = in.nextLine();

    System.out.print("Inserisci l'importo totale della spesa: ");
    String spesaStringa = in.nextLine();

    // 1. Converto l'importo inserito in numero
    double spesa = Double.parseDouble(spesaStringa);
    
    double scontoPercentuale = 0;
    boolean codiceValido = true;

    // 2. Verifico il codice sconto
    if (codice.equals("SCONTO10")) {
      scontoPercentuale = 0.10; // 10%
    } 
    else if (codice.equals("SCONTO20")) {
      scontoPercentuale = 0.20; // 20%
    } 
    else if (codice.equals("VIP")) {
      // Sconto del 30% SOLO se la spesa è maggiore di 100
      if (spesa > 100) {
        scontoPercentuale = 0.30;
      } else {
        System.out.println("Lo sconto VIP si applica solo per spese superiori a 100.");
        codiceValido = false;
      }
    } 
    else {
      // 3. Se il codice non è valido
      System.out.println("Codice non valido");
      codiceValido = false;
    }

    // 4. Stampo il totale scontato (solo se il codice è valido)
    if (codiceValido) {
      double valoreSconto = spesa * scontoPercentuale;
      double totaleScontato = spesa - valoreSconto;
      
      System.out.println("Sconto applicato: " + (scontoPercentuale * 100) + "%");
      System.out.println("Totale scontato: " + totaleScontato);
    }

    in.close();
  }
}
