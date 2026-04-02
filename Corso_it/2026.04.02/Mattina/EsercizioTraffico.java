import java.util.ArrayList;

abstract class Veicolo {
  private String targa;
  private double velocita;
  private int numeroAssi;

  public Veicolo (String targa, double velocita, int numeroAssi) {
    this.targa = targa;
    setVelocita(velocita);
    this.numeroAssi=numeroAssi;
  }

  // Getter e Setter con controllo
  public String getTarga() {return targa;}
  public void setTarga(String targa) {this.targa = targa;}

  public double getVelocita() {return velocita;}
  public void setVelocita(double velocita) {
    if(velocita>=0) this.velocita = velocita;
    else System.out.println("[ERRORE] la velocita non può essere negativa");
  }

  public int getNumeroAssi() {return numeroAssi;}
  public void setNumeroAssi(int numeroAssi) {this.numeroAssi=numeroAssi;}


  // Metodo Astratto
  public abstract double calcolaPedaggio();


  @Override
  public String toString() {
    return "Targa: "+targa+", "+velocita+" km/h\nAssi: "+numeroAssi;
  }
}

class Auto extends Veicolo {
  private int numeroPosti;

  public Auto(String targa, double velocita, int numeroAssi, int numeroPosti) {
    super(targa, velocita, numeroAssi);
    this.numeroPosti = numeroPosti;
  }

  @Override
  public double calcolaPedaggio() {
    // €2 + €0.5 xAsse
    return 2 + (getNumeroAssi()*0.5);
  }

  @Override
  public String toString() {
    return "[AUTO] - "+super.toString() + ", Posti: "+numeroPosti;
  }
}

class Camion extends Veicolo {
  private double caricoTonnellate;

  public Camion(String targa, double velocita, int numeroAssi, double caricoTonnellate){
    super(targa, velocita, numeroAssi);
    this.caricoTonnellate = caricoTonnellate;
  }

  @Override
  public double calcolaPedaggio() {
    // €2 + ( €0.5 xAsse ) + 5
    return 2 + (getNumeroAssi()*1.2) + 5;
  }

  @Override
  public String toString() {
    return "[CAMION] - "+super.toString()+", Carico: "+caricoTonnellate+"t";
  }
}

class Moto extends Veicolo {
  private int cilindrata;

  public Moto(String targa, double velocita, int numeroAssi, int cilindrata) {
    super(targa, velocita, numeroAssi);
    this.cilindrata = cilindrata;
  }

  @Override
  public double calcolaPedaggio() {
    return 1.5;
  }

  @Override
  public String toString() {
    return "[MOTO] - "+super.toString()+", Cilindrata"+cilindrata+"cc";
  }
}

public class EsercizioTraffico {
  public static void main(String[] args) {
    ArrayList<Veicolo> autostrada = new ArrayList<>();
    
    // Creazione di diversi veicoli
    autostrada.add(new Auto("AA123BB", 110.0, 2, 5));
    autostrada.add(new Camion("CC999ZZ", 80.0, 4, 15.5));
    autostrada.add(new Moto("XY000KK", 120.0, 2, 600));
    autostrada.add(new Auto("BB456CC", 130.0, 2, 2));

    System.out.println("\n\n--- SISTEMA GESTIONE TRAFFICO AUTOSTRADALE ---");
    
    // Iterazione polimorfica
    for (Veicolo v : autostrada) {
      System.out.println("----------------------------------------------");
      System.out.println(v.toString());

      System.out.printf("Pedaggio da pagare: %.2f EUR\n", v.calcolaPedaggio());
    }
    System.out.println("\n\n");
  }
}
