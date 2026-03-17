import java.util.Scanner;

public class EsercizioVerificaUsername {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);

    // Chiediamo l'username all'utente
    System.out.print("Inserisci il tuo username: ");
    String username = in.nextLine();

    // Controllo delle condizioni
    if (username.isEmpty()) {
      // Se non è stato scritto nulla
      System.out.println("Username non valido");
    } 
    else if (username.length() < 5) {
      // Se ha meno di 5 lettere
      System.out.println("Username troppo corto");
    } 
    else {
      // Se ha 5 o più lettere (maggiore o uguale a 5)
      System.out.println("Username valido");
    }

    in.close();
  }
}

