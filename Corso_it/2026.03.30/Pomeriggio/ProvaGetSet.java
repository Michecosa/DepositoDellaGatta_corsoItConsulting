class Umano {
  // 1. Variabili private: non accessibili direttamente dall'esterno
  private String nome;
  private int eta;

  // Costruttore
  public Umano(String nome, int eta) {
    this.nome = nome;
    this.eta = eta;
  }

  // 2. GETTER: Serve per LEGGERE il valore
  public String getNome() {
    return nome;
  }

  // 3. SETTER: Serve per MODIFICARE il valore con controllo
  public void setNome(String nuovoNome) {
    this.nome = nuovoNome;
  }

  public int getEta() {
    return eta;
  }

  public void setEta(int nuovaEta) {
    // Il setter permette di aggiungere logica di controllo!
    if (nuovaEta > 0) {
      this.eta = nuovaEta;
    } else {
      System.out.println("Errore: l'età deve essere positiva");
    }
  }
}

public class ProvaGetSet {
  public static void main(String[] args) {
    Umano p = new Umano("Miche", 24);

    // p.nome = "Mario"; // ERRORE! La variabile è private
    
    // Usiamo il Setter per modificare
    p.setEta(18); 
    
    // Usiamo il Getter per leggere
    System.out.println("Nome: " + p.getNome());
    System.out.println("Età: " + p.getEta());
  }
}