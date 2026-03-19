import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class EsercizioGestioneStudenti {
  public static void main(String[] args) {
    // Inizializzo la lista e lo scanner per l'input
    ArrayList<String> studenti = new ArrayList<>();
    Scanner input = new Scanner(System.in);
    String scelta;

    System.out.println("--- Inserimento Studenti ---");
    System.out.println("Scrivi i nomi degli studenti (digita 'fine' per terminare):");

    // 1. Fase di inserimento dinamico
    while (true) {
      System.out.print("Nome: ");
      scelta = input.nextLine();

      // Trasformo l'input in minuscolo e uso .equals()
      // Questo riconoscerà "Fine", "FINE", "fiNe", ecc.
      if (scelta.toLowerCase().equals("fine")) {
        break;
      }
      studenti.add(scelta);
    }

    // 2. Ordinamento alfabetico
    Collections.sort(studenti);

    // 3. Stampa dei risultati e del numero totale
    System.out.println("\n--- Lista Studenti (Ordine Alfabetico) ---");
    System.out.println(studenti);
    System.out.println("Totale studenti inseriti: " + studenti.size());

    // 4. Fase di eliminazione
    System.out.println("\n--- Eliminazione Studente ---");
    System.out.print("Inserisci il nome dello studente da eliminare: ");
    String daRimuovere = input.nextLine();

    // Il metodo remove restituisce true se l'elemento esisteva ed è stato rimosso
    if (studenti.remove(daRimuovere)) {
      System.out.println("Studente eliminato con successo");
    } else {
      System.out.println("Errore: Studente non trovato in lista.");
    }

    // Stampa finale aggiornata
    System.out.println("\nLista finale: " + studenti+"\n");
    
    input.close(); // Chiudo lo scanner
  }
}