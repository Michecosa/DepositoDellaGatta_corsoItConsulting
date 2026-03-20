public class EsercizioClassePersona {
  public static void main(String[] args) {
    // Creazione di due oggetti Persona usando il costruttore
    Persona p1 = new Persona("Michela Della Gatta", 24, "Napoli");
    Persona p2 = new Persona("Otto Bit", 64, "Silicon Valley");

    // Mostro i dettagli di p1 e p2
    p1.mostraDati();
    p2.mostraDati();
  }
}

class Persona {
  // Variabili di istanza
  String nome;
  int eta;
  String citta;

  // * COSTRUTTORE con Parametri*
  public Persona(String nome, int eta, String citta) {
    this.nome = nome;
    this.eta = eta;
    this.citta = citta;
  }

  // Metodo per mostrare i dettagli
  public void mostraDati() {
    System.out.println("\n-------- SCHEDA PERSONA --------");
    System.out.println(nome + ", " + citta);
    System.out.println(eta + " anni");
    System.out.println("--------------------------------\n");
  }
}
