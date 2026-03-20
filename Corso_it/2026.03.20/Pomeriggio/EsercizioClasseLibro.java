public class EsercizioClasseLibro {
  public static void main(String[] args) {
    // Istanze della classe Libro
    Libro libro1 = new Libro("Il Signore degli Anelli", "J.R.R. Tolkien", 25.50);
    Libro libro2 = new Libro("1984", "George Orwell", 15.00);

    // Stampa dei dettagli degli oggetti creati
    System.out.println(libro1.toString());
    System.out.println(libro2.toString());
  }
}

class Libro {
  // * Variabili di istanza *
  // Ogni libro ha il suo titolo, autore e prezzo
  String titolo;
  String autore;
  double prezzo;
  int codiceNumerico;

  // * Variabile statica *
  // Usata per tenere il conteggio globale e generare il codice autoincrementante
  static int contatoreCodice = 1;

  // * Costruttore *
  public Libro(String titolo, String autore, double prezzo) {
    this.titolo = titolo;
    this.autore = autore;
    this.prezzo = prezzo;
    
    // Assegno il valore attuale del contatore al libro e poi lo incremento
    this.codiceNumerico = contatoreCodice;
    contatoreCodice++; 
  }

  // * Metodo toString *
  // Restituisce una descrizione dell'oggetto
  public String toString() {
    return "Libro [Codice: " + codiceNumerico + ", Titolo: " + titolo + ", Autore: " + autore + ", Prezzo: " + prezzo + " EUR]";
  }
}