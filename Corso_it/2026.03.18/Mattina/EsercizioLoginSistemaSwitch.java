import java.util.Scanner;

public class EsercizioLoginSistemaSwitch {
  public static void main(String[] args) {
    Scanner tastiera = new Scanner(System.in);
    
    // Dati di login
    String passwordCorretta = "java123";
    int tentativiMassimi = 3;
    int tentativiEffettuati = 0;
    boolean loginRiuscito = false;

    // --- Ciclo WHILE per i tentativi di login ---
    // Il ciclo continua finché NON è vero che il login è riuscito E non si superano i 3 tentativi
    while (!loginRiuscito && tentativiEffettuati < tentativiMassimi) {
      System.out.print("Inserisci la password: ");
      String inputPassword = tastiera.nextLine();
      tentativiEffettuati++;

      if (inputPassword.equals(passwordCorretta)) {
        loginRiuscito = true;
      } else {
        int rimanenti = tentativiMassimi - tentativiEffettuati;
        if (rimanenti > 0) {
          System.out.println("Password errata. Tentativi rimasti: " + rimanenti+"\n");
        }
      }
    }

    // --- Gestione dell'esito del login ---
    if (loginRiuscito) {
      System.out.println("\nLogin effettuato con successo!");

      String conferma;
      do {
        System.out.print("Vuoi accedere al sistema? (s/n): ");
        conferma = tastiera.nextLine().toLowerCase();

        switch (conferma) {
          case "s":
            System.out.println("Accesso al sistema effettuato.\n");
            break;
          case "n":
            System.out.println("Accesso annullato.\n");
            break;
          default:
            System.out.println("\nInput non valido. Digita 's' oppure 'n'.\n");
            break;
        }

      } while (!conferma.equals("s") && !conferma.equals("n"));

    } else {
      System.out.println("\nAccesso bloccato. Hai esaurito i 3 tentativi.\n");
    }

    tastiera.close();
  }
}