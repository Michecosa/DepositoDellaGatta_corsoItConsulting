// import java.util.ArrayList;
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
      System.out.println("Il valore inserito non è valido\n\n");
    }
  }

  void displayBalance() {
    System.out.println("Saldo attuale: "+this.balance+" EUR\n\n");
  }
}

public class EsercizioBankAccount {
  public static void main(String[] args) {
    BankAccount account1 = new BankAccount("Miche", 0); // accurato
    Scanner input = new Scanner(System.in);

      boolean continua = true;

      while (continua) {
        // Menu di navigazione
        System.out.println("\n--- MENU \"PER UN SOLO CONTO\" ---");
        System.out.println("1 - Visualizza saldo");
        System.out.println("2 - Effettua deposito");
        System.out.println("3 - Effettua prelievo");
        System.out.println("4 - Esci");
        System.out.print("Scelta: ");
        int scelta = input.nextInt();
        input.nextLine(); // Pulizia buffer dopo nextInt()

        switch (scelta) {
          case 1:
            account1.displayBalance();
            break;
          
          case 2:
            System.out.print("Quanto vuoi depositare? ");
            double depositAmount = input.nextDouble();
            account1.deposit(depositAmount);
            break;
          
          case 3: 
            System.out.print("Quanto vuoi prelevare? ");
            double withdrawAmount = input.nextDouble();
            account1.withdraw(withdrawAmount);
            break;
          
          case 4: 
            continua = false;
            System.out.println("Adios\n");
            break;
          
          default:
            System.out.println("Scelta non valida\n\n");
        }
      }

    input.close();
  }
}