package Extra;
import java.util.ArrayList;
import java.util.Scanner;

class Cibo {
  protected String nome;
  
  public Cibo(String nome) {
    this.nome = nome;
  }

  public String getNome() { return nome; }
  public void prepara() { System.out.println("Preparazione");}
}

// Famiglia Hamburger
class Hamburger extends Cibo {
  public Hamburger(String nome) { super(nome); }

  @Override
  public void prepara() { System.out.println("Preparazione Hamburger: pane, carne"); }
}

class Cheeseburger extends Hamburger {
  public Cheeseburger() { super("Cheeseburger"); }

  @Override
  public void prepara() { System.out.println("Preparazione Cheeseburger: pane, carne, formaggio, ketchup"); }
}
class VegBurger extends Hamburger {
  public VegBurger() { super("VegBurger"); }

  @Override
  public void prepara() { System.out.println("Preparazione VegBurger: pane integrale, burger vegetale, insalata, pomodoro"); }
}
class DoubleBacon extends Hamburger {
  public DoubleBacon() { super("DoubleBacon"); }

  @Override
  public void prepara() { System.out.println("Preparazione DoubleBacon: pane, doppia carne, bacon, cheddar, maionese"); }
}

// Famiglia Dessert (Extra)
class Dessert extends Cibo {
  public Dessert(String nome) { super(nome); }

  @Override
  public void prepara() { System.out.println("Preparazione Dessert: impiattamento, decorazione"); }
}

class Tiramisu extends Dessert {
  public Tiramisu() { super("Tiramisù"); }

  @Override
  public void prepara() { System.out.println("Preparazione Tiramisù: savoiardi, caffè, crema al mascarpone"); }
}


// --- GESTORE RISTORANTI (EXTRA EXTRA) ---

class Ristorante {
  protected String nomeRisto;
  public Ristorante(String nome) { this.nomeRisto = nome; }
  
  public void servi(ArrayList<Cibo> ordine) {
    System.out.println("\n--- Cucina di " + nomeRisto + " ---");
    for (Cibo piatto : ordine) {
      System.out.print(nomeRisto + " sta preparando: ");
      piatto.prepara();
    }
  }
}

class FastFood extends Ristorante {
    public FastFood() { super("McDocker"); }
    @Override
    public void servi(ArrayList<Cibo> ordine) {
      System.out.println("\n--- " + nomeRisto + " ---");
      for (Cibo c : ordine) { System.out.print("[FAST] "); c.prepara(); }
    }
}

class RistoranteStellato extends Ristorante {
  public RistoranteStellato() { super("Java Gourmet"); }
  @Override
  public void servi(ArrayList<Cibo> ordine) {
    System.out.println("\n--- " + nomeRisto + " ---");
    for (Cibo c : ordine) { System.out.print("[GOURMET] Impiattamento di: "); c.prepara(); }
  }
}

public class EsercizioHamburgeriaExtra {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    ArrayList<Cibo> ordine = new ArrayList<>();
    
    // Selezione Ristorante
    System.out.println("\n--- ELENCO RISTORANTI ---");
    System.out.println("[1] McDocker");
    System.out.println("[2] Java Gourmet");
    System.out.print("Seleziona: ");
    int sceltaR = input.nextInt();
    
    Ristorante ristoScelto = (sceltaR == 1) ?  new FastFood() : new RistoranteStellato();

    boolean continua = true;
    while(continua) {
      System.out.println("\n--- MENU DI " + ristoScelto.nomeRisto.toUpperCase() + " --- ");
      System.out.println("  1. Cheeseburger");
      System.out.println("  2. VegBurger");
      System.out.println("  3. DoubleBacon");
      System.out.println("  4. Tiramisù");
      System.out.println("  5. Ordina");
      System.out.print("\nScelta: ");
      int scelta = input.nextInt();

      switch (scelta) {
        case 1 -> ordine.add(new Cheeseburger());
        case 2 -> ordine.add(new VegBurger());
        case 3 -> ordine.add(new DoubleBacon());
        case 4 -> ordine.add(new Tiramisu());
        case 5 -> continua = false;
        default -> System.out.println("Scelta non valida");
      }
    }

    // Preparo l'ordine
    ristoScelto.servi(ordine);
    
    System.out.println("\nArrivederci da " + ristoScelto.nomeRisto + "!\nNon tornare presto");
    input.close();
  }
}