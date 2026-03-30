class Persona {
  // Campi privati (incapsulamento)
  private String nome;
  private int eta;

  Persona(String nome, int eta) {
    this.nome = nome;
    this.eta = eta;
  }

  // Metodo privato: invisibile fuori da questa classe
  private boolean verificaMaggiorenne() {
    return this.eta >= 18;
  }

  // Metodo pubblico: utilizzabile nel main
  public void stampaStatus() {
    if (verificaMaggiorenne()) {
      System.out.println(this.nome + " è maggiorenne");
    } else {
      System.out.println(this.nome + " non è maggiorenne");
    }
  }
}

public class ProvaPersona {
  public static void main(String[] args) {
    // Creo gli oggetti Persona
    Persona p1 = new Persona("Miche", 24);
    Persona p2 = new Persona("Mario", 15);

    // Chiamo il metodo pubblico
    p1.stampaStatus();
    p2.stampaStatus();

    // NOTA: p1.verificaMaggiorenne() qui NON funzionerebbe
  }
}