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


// --- CLASSE TERZA (CUCINA) --
class Cucina {
  public static void cucinaPiatto(Cibo c) {
    System.out.print("CUCINA -> ");
    c.prepara();
  }
}


public class EsercizioHamburgeria {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    ArrayList<Cibo> ordine = new ArrayList<>();
    boolean continua = true;

    System.out.println("\n--- HAMBURGERIA (e dessert) ---");

    while(continua) {
      System.out.println("\nCosa vuoi ordinare?");
      System.out.println("1. Cheeseburger");
      System.out.println("2. VegBurger");
      System.out.println("3. DoubleBacon");
      System.out.println("4. Tiramisù (Dessert)");
      System.out.println("5. Chiudi ordine e prepara tutto");
      System.out.print("Scelta: ");

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

    System.out.println("\n--- INIZIO PREPARAZIONE ORDINE ---");
    for (Cibo piatto : ordine) {
      // Richiamo del metodo tramite la classe terza
      Cucina.cucinaPiatto(piatto);
    }
    
    System.out.println("\nOrdine completato! Buon appetito");
    input.close();
  }
}
