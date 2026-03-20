public class ProvaOggetti {
  public static void main(String[] args) {
    // --- TEST CLASSE NOMECLASSE ---
    NomeClasse mioOggetto = new NomeClasse();
    mioOggetto.nomeAttributo = "Esempio";
    mioOggetto.nomeMetodo();

    System.out.println("---------------------------");

    // --- TEST CLASSE AUTO ---
    Auto miaAuto = new Auto();
    miaAuto.marca = "Fiat";
    miaAuto.anno = 2022;
    miaAuto.prezzo = 15500.50;
    System.out.print("Informazioni auto: ");
    miaAuto.mostraInfo();

    System.out.println("---------------------------");

    // --- TEST CLASSE STUDENTE ---
    // Creo tre studenti diversi
    Studente s1 = new Studente("Alice");
    Studente s2 = new Studente("Bob");
    // Studente s3 = new Studente("Charlie");

    // Stampiamo i nomi (variabili di istanza - cambiano per ogni oggetto)
    System.out.println("Studente 1: " + s1.nome);
    System.out.println("Studente 2: " + s2.nome);

    // Stampiamo il totale (variabile statica - condivisa da tutti)
    // Nota: si accede preferibilmente tramite il nome della Classe
    System.out.println("Totale studenti creati: " + Studente.totaleStudenti);
  }
}

/**
 * Esercizio: Variabili Statiche vs Istanza
 */
class Studente {
  String nome; // Variabile di istanza: ogni studente ha il suo nome
  static int totaleStudenti = 0; // Variabile statica: comune a tutta la classe

  // Costruttore: viene chiamato quando scrivi 'new Studente(...)'
  Studente(String nome) {
    this.nome = nome;
    totaleStudenti++; // Incrementa il contatore comune ogni volta che nasce un oggetto
  }
}

/**
 * Definizione della classe Auto
 */
class Auto {
  String marca;
  int anno;
  double prezzo;

  void mostraInfo() {
    System.out.println(marca + " - " + anno + " - €" + prezzo);
  }
}

/**
 * Struttura generica
 */
class NomeClasse {
  String nomeAttributo; 
  
  void nomeMetodo() {
    System.out.println("Metodo di NomeClasse eseguito.");
  }
}