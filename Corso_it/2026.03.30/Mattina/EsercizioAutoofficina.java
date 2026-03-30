import java.util.ArrayList;
import java.util.Scanner;

class Auto {
  String targa;
  String modello;

  Auto(String targa, String modello) {
    this.targa = targa;
    this.modello = modello;
  }
}

class Officina {
  ArrayList<Auto> listaAuto = new ArrayList<Auto>();

  void aggiungiAuto(Auto nuovaAuto) {
    listaAuto.add(nuovaAuto);
    System.out.println("Aggiunta auto: "+nuovaAuto.modello);
  }

  void stampaElenco() {
    if (listaAuto.isEmpty()) {
      System.out.println("L'officina è vuota");
    } else {
      System.out.println("\n--- AUTO IN OFFICINA ---");
      for (Auto a : listaAuto) {
        System.out.println(a.modello+" - Targa: "+a.targa);
      }
    }
  }
}

public class EsercizioAutoofficina {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    Officina miaOfficina = new Officina();
    boolean continua = true;

    while (continua) {
      System.out.println("\n--- MENU OFFICINA ---");
      System.out.println("1 - Aggiungi auto");
      System.out.println("2 - Visualizza elenco");
      System.out.println("3 - Esci");
      System.out.print("Scelta: ");
      
      int scelta = input.nextInt();
      input.nextLine();

      switch (scelta) {
        case 1:
          System.out.print("Inserisci modello: ");
          String mod = input.nextLine();
          System.out.print("Inserisci targa: ");
          String targ = input.nextLine();
          
          miaOfficina.aggiungiAuto(new Auto(targ, mod));
          break;

        case 2:
          miaOfficina.stampaElenco();
          break;

        case 3:
          continua = false;
          System.out.println("Chiusura officina...");
          break;

        default:
          System.out.println("Scelta non valida");
      }
    }
    input.close();
  }
}
