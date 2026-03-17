import java.util.Scanner;

public class EsercizioLogin {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);

    // Credenziali predefinite
    String userCorretto = "admin";
    String passCorretta = "1234";

    // Input dell'utente
    System.out.print("Inserisci Username: ");
    String userInserito = in.nextLine();

    System.out.print("Inserisci Password: ");
    String passInserita = in.nextLine();

    // Verifico se sono corretti
    // Trasformo l'input dell'utente tutto in minuscolo (.toLowerCase) e lo confronto con "admin" 
    boolean userOk = userInserito.toLowerCase().equals(userCorretto);
    boolean passOk = passInserita.equals(passCorretta);

    // Controllo delle condizioni
    if (userOk && passOk) {
        System.out.println("Accesso consentito");
    } 
    else if (userOk || passOk) {
        System.out.println("Credenziali errate");
    } 
    else {
        System.out.println("Accesso negato");
    }

    in.close();
  }
}
