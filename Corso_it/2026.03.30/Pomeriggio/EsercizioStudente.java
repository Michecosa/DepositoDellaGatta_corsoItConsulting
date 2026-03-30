import java.util.ArrayList;
import java.util.Scanner;

class Studente {
  // Attributi privati
  private String nome;
  private int voto;
  private int id; 
  
  // Variabile statica per gestire l'autoincremento
  private static int prossimoId = 1;

  // Costruttore per inizializzare nome e voto
  Studente(String nome, int voto) {
    this.nome = nome;
    this.voto = voto;
    
    // Meccanismo autoincrementante
    this.id = prossimoId;
    prossimoId++;
  }

  // Metodo getter per leggere il nome
  public String getNome() {
    return this.nome;
  }

  // Metodo getter per leggere il voto
  public int getVoto() {
    return this.voto;
  }

  // Getter per l'id
  public int getId() {
    return this.id;
  }

  // Metodo setter per il voto con controllo intervallo 0-10
  public void setVoto(int nuovoVoto) {
    if (nuovoVoto >= 0 && nuovoVoto <= 10) {
      this.voto = nuovoVoto;
    } else {
      System.out.println("Errore: il voto " + nuovoVoto + " non è valido (deve essere tra 0 e 10)");
    }
  }
}

public class EsercizioStudente {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    
    // Gestione della lista tramite ArrayList
    ArrayList<Studente> listaStudenti = new ArrayList<>();

    // Creo oggetti Studente e li aggiungo alla lista
    Studente s1 = new Studente("Miche", 10);
    Studente s2 = new Studente("ehciM", 5);
    listaStudenti.add(s1);
    listaStudenti.add(s2);

    // Stampo nome e voto iniziale
    System.out.println("Dati iniziali:");
    for (Studente s : listaStudenti) {
      System.out.println("ID " + s.getId() + " - " + s.getNome() + "\nVoto: " + s.getVoto()+"\n\n");
    }

    // Provo a cambiare il voto con valori validi e non validi
    System.out.println("\nVerifica validazione voto su " + s1.getNome() + ": s1.setVoto(8)");
    s1.setVoto(8);  // Valido
    System.out.println("Nuovo voto valido: " + s1.getVoto());
    
    System.out.println("\nVerifica validazione voto su " + s1.getNome() + ": s1.setVoto(15)");
    s1.setVoto(15); // Non valido
    System.out.println("Voto dopo tentativo non valido: " + s1.getVoto());

    // Ricerca di uno studente tramite nome
    System.out.print("\nInserisci il nome dello studente da cercare: ");
    String nomeCercato = input.nextLine();
    boolean trovato = false;

    for (Studente s : listaStudenti) {
      if (s.getNome().equalsIgnoreCase(nomeCercato)) {
        System.out.println("Studente trovato\n-> ID: " + s.getId() + ", Nome: " + s.getNome() + ", Voto: " + s.getVoto());
        trovato = true;
        break;
      }
    }

    if (!trovato) {
      System.out.println("Studente con nome '" + nomeCercato + "' non trovato");
    }

    input.close();
  }
}