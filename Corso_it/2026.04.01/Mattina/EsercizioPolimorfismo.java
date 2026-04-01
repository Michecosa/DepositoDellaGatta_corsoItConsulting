// Classe base
class Persona {
  protected String nome;

  public Persona (String nome) {
    this.nome = nome;
  }

  public void saluta() {
    System.out.println("Ciao, sono "+nome+"... e niente");
  }
}

// Sottoclasse che eredita da Persona
class Pirata extends Persona {
  public Pirata(String nome) {
    super(nome);
  }

  // POLIMORFISMO D'EREDITARIETÀ (Override del metodo saluta)
  @Override
  public void saluta() {
    System.out.println("Sono il pirata "+nome);
  }
}

// Terza classe per gestire il saluto
class GestoreSaluti {
  public static void eseguiSaluto(Persona p) {
    p.saluta();
  }
}

// Classe principale
public class EsercizioPolimorfismo {
  public static void main(String[] args) {
    Persona p1 = new Persona("Mario");
    Persona p2 = new Persona("Luigi");

    Pirata pirata = new Pirata("Barbossa");

    System.out.println("\n--- GestoreSaluti per PERSONA ---");
    GestoreSaluti.eseguiSaluto(p1);
    GestoreSaluti.eseguiSaluto(p2);

    System.out.println("\n--- GestoreSaluti per PIRATA ---");
    GestoreSaluti.eseguiSaluto(pirata);
  }
}
