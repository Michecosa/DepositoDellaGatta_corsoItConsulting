import java.util.ArrayList;
import java.util.Scanner;

public class EsercizioGestioneStudentiExtra {
  public static void main(String[] args) {
    // Creo tre liste separate, una per ogni tipo di dato
    ArrayList<String> studenti = new ArrayList<>();
    ArrayList<Integer> eta = new ArrayList<>();
    ArrayList<Float> voti = new ArrayList<>();
    
    // Creo l'ArrayList "contenitore" che ospiterà le tre liste precedenti
    ArrayList<ArrayList> database = new ArrayList<>();
    
    // Collego le liste al database
    database.add(studenti); // Indice 0 del database
    database.add(eta);      // Indice 1 del database
    database.add(voti);     // Indice 2 del database

    // Unico scanner per gestire tutti i tipi di input
    Scanner input = new Scanner(System.in);

    // --- Inserimento Dati ---
    System.out.println("--- Inserimento Studenti ---");
    System.out.println("Scrivi i nomi degli studenti (digita 'fine' per terminare):");

    // 1. Fase di inserimento dinamico
    while (true) {
      System.out.print("\nNome: ");
      String nome = input.nextLine();

      // Trasformo l'input in minuscolo e uso .equals()
      // Questo riconoscerà "Fine", "FINE", "fiNe", ecc.
      if (nome.toLowerCase().equals("fine")) {
        break;
      }
      studenti.add(nome);

      System.out.print("Età (numero intero): ");
      int e = input.nextInt();
      eta.add(e); // Aggiungo l'età alla lista corrispondente

      System.out.print("Voto (es. 7,5): ");
      float v = input.nextFloat();
      voti.add(v); // Aggiungo il voto alla lista corrispondente
      
      // Pulizia del buffer (chiedo venia, ma tre scanner erano osceni da vedere)
      input.nextLine(); 
    }

    // 2. Visualizzazione
    System.out.println("\n--- Stato del Database ---");
    // database.get(0) = lista nomi, database.get(1) = età, database.get(2) = voti
    System.out.println("Nomi: " + database.get(0));
    System.out.println("Età: " + database.get(1));
    System.out.println("Voti: " + database.get(2));
    System.out.println("Numero totale di record: " + studenti.size());

    // 3. Eliminazione
    System.out.println("\n--- Eliminazione Studente ---");
    System.out.print("Inserisci il nome dello studente da eliminare: ");
    String daRimuovere = input.nextLine();

    // Cerco la posizione (indice) del nome nella lista studenti
    int indice = studenti.indexOf(daRimuovere);

    // indice = -1 => il nome non è stato trovato
    if (indice != -1) {
      // Rimuovo l'elemento alla stessa posizione da tutte le liste
      studenti.remove(indice);
      eta.remove(indice);
      voti.remove(indice);
      System.out.println("Tutti i dati relativi a '" + daRimuovere + "' sono stati eliminati\n");
    } else {
      System.out.println("Errore: Studente non trovato.");
    }

    // 4. FINE
    System.out.println("\n--- Database Finale ---\n");
    System.out.println("Nomi: " + database.get(0));
    System.out.println("Età: " + database.get(1));
    System.out.println("Voti: " + database.get(2));
    System.out.println("Numero totale di record: " + studenti.size()+"\n");
    
    input.close(); // Chiudo lo scanner
  }
}