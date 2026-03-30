import java.util.ArrayList;
import java.util.Scanner;

class BankAccount {
  String accountHolderName;
  double balance;

  BankAccount(String accountHolderName, double balance) {
    this.accountHolderName = accountHolderName;
    this.balance = balance;
  }

  void deposit(double amount) {
    if(amount > 0) {
      this.balance = this.balance + amount;
      System.out.println("Depositati "+amount+" EUR\nSaldo attuale: "+this.balance+" EUR\n\n");
    } else {
      System.out.println("Il valore inserito non è valido\n\n");
    }
  }

  void withdraw(double amount) {
    if(amount > 0 && this.balance - amount >= 0) {
      this.balance = this.balance - amount;
      System.out.println("Prelevati "+amount+" EUR\nSaldo attuale: "+this.balance+" EUR\n\n");
    } else {
      System.out.println("Il valore inserito non è valido o saldo insufficiente\n\n");
    }
  }

  void displayBalance() {
    System.out.println("Saldo attuale: "+this.balance+" EUR\n\n");
  }
}

public class EsercizioBankAccount {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    
    // Due ArrayList separate: una per i nomi (String) e una per i conti (BankAccount)
    ArrayList<String> credenziali = new ArrayList<>();
    ArrayList<BankAccount> conti = new ArrayList<>();

    // Popolamento in parallelo
    credenziali.add("Miche");
    conti.add(new BankAccount("Miche", 0.0));

    credenziali.add("Mario");
    conti.add(new BankAccount("Mario", 500.0));

    System.out.print("Inserisci il nome del titolare per accedere al conto: ");
    String nomeAccesso = input.nextLine();
    
    BankAccount accountAttivo = null;

    // Ricerca basata sull'indice della lista credenziali
    for (int i = 0; i < credenziali.size(); i++) {
      if (credenziali.get(i).equalsIgnoreCase(nomeAccesso)) {
        accountAttivo = conti.get(i); // Prelevo il conto corrispondente alla posizione del nome
        break;
      }
    }

    // Verifico se l'account è stato trovato 
    if (accountAttivo == null) {
      System.out.println("Accesso negato: account non trovato.");
    } else {
      System.out.println("Ciao " + accountAttivo.accountHolderName);
      boolean continua = true;

      while (continua) {
        // Menu di navigazione
        System.out.println("\n--- CONTO DI " + accountAttivo.accountHolderName.toUpperCase() + " ---");
        System.out.println("1 - Visualizza saldo");
        System.out.println("2 - Effettua deposito");
        System.out.println("3 - Effettua prelievo");
        System.out.println("4 - Esci");
        System.out.print("Scelta: ");
        int scelta = input.nextInt();
        input.nextLine(); // Pulizia buffer dopo nextInt()

        switch (scelta) {
          case 1:
            accountAttivo.displayBalance();
            break;
          
          case 2:
            System.out.print("Quanto vuoi depositare? ");
            double depositAmount = input.nextDouble();
            accountAttivo.deposit(depositAmount);
            break;
          
          case 3: 
            System.out.print("Quanto vuoi prelevare? ");
            double withdrawAmount = input.nextDouble();
            accountAttivo.withdraw(withdrawAmount);
            break;
          
          case 4: 
            continua = false;
            System.out.println("\nGrazie per averci scelto, buona giornata\n");
            break;
          
          default:
            System.out.println("Scelta non valida\n\n");
        }
      }

      input.close();
    }
  }
}