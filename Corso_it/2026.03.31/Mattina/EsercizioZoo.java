import java.util.ArrayList;
import java.util.Scanner;

class Animale {
  protected String nome;
  protected int eta;

  public Animale(String nome, int eta) {
    this.nome = nome;
    this.eta = eta;
  }

  public void faiVerso() {
    System.out.println("Yo.");
  }
}

class Cane extends Animale {
  public Cane(String nome, int eta) { 
    super(nome, eta); 
  }

  public void faiVerso() { 
    System.out.println("Bau!"); 
  }
}

class Gatto extends Animale {
  public Gatto(String nome, int eta) { 
    super(nome, eta); 
  }

  public void faiVerso() { 
    System.out.println("Miao!"); 
  }
}

public class EsercizioZoo {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    // Array con cani e gatti
    Animale[] arrayAnimali = {
      new Cane("Jack", 5),
      new Gatto("Luna", 3),
      new Cane("Rex", 2)
    };

    // Liste separate
    ArrayList<Cane> cani = new ArrayList<>();
    ArrayList<Gatto> gatti = new ArrayList<>();
    ArrayList<Animale> animaliGenerici = new ArrayList<>();

    int scelta = 1;

    while (scelta != 0) {
      System.out.println("\n1. Mostra zoo");
      System.out.println("2. Aggiungi cane");
      System.out.println("3. Aggiungi gatto");
      System.out.println("4. Aggiungi animale generico");
      System.out.println("0. Esci");
      System.out.print("Scelta: ");
      scelta = Integer.parseInt(scanner.nextLine());

      switch (scelta) {
        case 1:
          for (Animale a : arrayAnimali) {
            System.out.print(a.nome+" ("+a.eta+" anni): ");
            a.faiVerso();
          }

          for (Cane c : cani) {
            System.out.print(c.nome+" ("+c.eta+" anni): ");
            c.faiVerso();
          }

          for (Gatto g : gatti) {
            System.out.print(g.nome+" ("+g.eta+" anni): ");
            g.faiVerso();
          }

          for (Animale a : animaliGenerici) {
            System.out.print(a.nome+" ("+a.eta+" anni): ");
            a.faiVerso();
          }
          break;
          
        case 2:
          System.out.print("Nome: ");
          String nomeCane = scanner.nextLine();
          System.out.print("Età: ");
          int etaCane = Integer.parseInt(scanner.nextLine());
          cani.add(new Cane(nomeCane, etaCane));
          break;
          
        case 3:
          System.out.print("Nome: ");
          String nomeGatto = scanner.nextLine();
          System.out.print("Età: ");
          int etaGatto = Integer.parseInt(scanner.nextLine());
          gatti.add(new Gatto(nomeGatto, etaGatto));
          break;
          
        case 4:
          System.out.print("Nome: ");
          String nomeAnimale = scanner.nextLine();
          System.out.print("Età: ");
          int etaAnimale = Integer.parseInt(scanner.nextLine());
          animaliGenerici.add(new Animale(nomeAnimale, etaAnimale));
          break;
          
        case 0:
          System.out.println("Arrivederci!");
          break;
          
        default:
          System.out.println("Scelta non valida");
        }
      }
    scanner.close();
  }
}