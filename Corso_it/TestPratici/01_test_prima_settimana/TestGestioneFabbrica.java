import java.util.ArrayList;
import java.util.Scanner;

public class TestGestioneFabbrica {

  // Semplice stampa delle opzioni disponibili per l'utente
  public static void mostraMenu() {
    System.out.println("\n\n\n--- MENU GESTIONE FABBRICA ---");
    System.out.println("1 - Aggiungi prodotto");
    System.out.println("2 - Visualizza produzione");
    System.out.println("3 - Cerca prodotto");
    System.out.println("4 - Modifica quantità");
    System.out.println("5 - Rimuovi prodotto");
    System.out.println("6 - Calcola totale pezzi prodotti");
    System.out.println("7 - Esci");
    System.out.println("-------------------------------");
  }


  public static void aggiungiProdotto(ArrayList<String> nomi, ArrayList<Integer> qta, Scanner scString, Scanner scInt) {
    System.out.print("Nome prodotto: ");
    String nome = scString.nextLine();
    System.out.print("Quantità: ");
    int valore = scInt.nextInt();

    // Piccolo controllo di sicurezza per non inserire dati senza senso
    if (nome.trim().isEmpty()) {
      System.out.println("Errore: manca il nome!");
    } else if (valore <= 0) {
      System.out.println("Errore: quantità non valida!");
    } else {
      nomi.add(nome);
      qta.add(valore);
      System.out.println("Prodotto aggiunto");
    }
  }

  // Ciclo per scorrere le liste e stampare i dati accoppiati
  public static void visualizzaProduzione(ArrayList<String> nomi, ArrayList<Integer> qta) {
    if (nomi.isEmpty()) {
      System.out.println("Nessun prodotto registrato");
    } else {
      for (int i = 0; i < nomi.size(); i++) {
        // Sincronizzo i due ArrayList tramite l'indice 
        System.out.println((i + 1) + " - " + nomi.get(i) + " | Quantità: " + qta.get(i));
      }
    }
  }

  public static void cercaProdotto(ArrayList<String> nomi, ArrayList<Integer> qta, Scanner scString) {
    System.out.print("Prodotto da cercare: ");
    String nomeCercato = scString.nextLine();
    // indexOf restituisce la posizione se esiste, altrimenti -1
    int indice = nomi.indexOf(nomeCercato);

    if (indice != -1) {
      System.out.println( (indice + 1) + " - " + nomi.get(indice) + " | Qta: " + qta.get(indice));
    } else {
      System.out.println("Prodotto non trovato");
    }
  }

  public static void modificaQuantita(ArrayList<String> nomi, ArrayList<Integer> qta, Scanner scString, Scanner scInt) {
    System.out.print("Prodotto da modificare: ");
    String nome = scString.nextLine();
    int indice = nomi.indexOf(nome);

    if (indice != -1) {
      System.out.print("Inserisci il nuovo valore: ");
      int nuovaQta = scInt.nextInt();
      if (nuovaQta > 0) {
        qta.set(indice, nuovaQta); // Sovrascrive il vecchio valore nella stessa posizione
        System.out.println("Quantità aggiornata!");
      } else {
        System.out.println("Quantità non valida");
      }
    } else {
      System.out.println("Prodotto non trovato");
    }
  }

  public static void rimuoviProdotto(ArrayList<String> nomi, ArrayList<Integer> qta, Scanner scString) {
    System.out.print("Prodotto da eliminare: ");
    String nome = scString.nextLine();
    int indice = nomi.indexOf(nome);

    if (indice != -1) {
      // Effettuo la rimozione da entrambi gli ArrayList per tenerli allineati
      nomi.remove(indice);
      qta.remove(indice);
      System.out.println("Prodotto rimosso");
    } else {
      System.out.println("Prodotto non trovato");
    }
  }

  // Somma di tutti i numeri presenti nella lista quantità
  public static void calcolaTotale(ArrayList<Integer> qta) {
    int tot = 0;
    for (int i = 0; i < qta.size(); i++) {
      tot += qta.get(i);
    }
    System.out.println("Totale produzione: " + tot + " pezzi");
  }

  public static void main(String[] args) {
    // Separo gli scanner: scannerString per le parole (nextLine) e scannerInt per i numeri (nextInt)
    Scanner scannerString = new Scanner(System.in);
    Scanner scannerInt = new Scanner(System.in);
    
    // Strutture dati parallele: l'elemento in nomiProdotti[0] corrisponde a quantitaProdotti[0]
    ArrayList<String> nomiProdotti = new ArrayList<>();
    ArrayList<Integer> quantitaProdotti = new ArrayList<>();
    
    int scelta = 0;

    // Loop infinito finché l'utente non decide di uscire (opzione 7)
    while (scelta != 7) {
      mostraMenu();
      System.out.print("\nScegli un'opzione: ");
      
      // Se l'utente non dovesse inserire un numero, il programma crasherebbe
      // per questo faccio un controllo sul valore inserito prima di procedere
      if (scannerInt.hasNextInt()) {
        scelta = scannerInt.nextInt();

        switch (scelta) {
          case 1: 
            aggiungiProdotto(nomiProdotti, quantitaProdotti, scannerString, scannerInt); 
            break;

          case 2: 
            visualizzaProduzione(nomiProdotti, quantitaProdotti); 
            break;

          case 3: 
            cercaProdotto(nomiProdotti, quantitaProdotti, scannerString); 
            break;

          case 4: 
            modificaQuantita(nomiProdotti, quantitaProdotti, scannerString, scannerInt); 
            break;

          case 5: 
            rimuoviProdotto(nomiProdotti, quantitaProdotti, scannerString); 
            break;

          case 6: 
            calcolaTotale(quantitaProdotti); 
            break;

          case 7: 
            System.out.println("Chiusura programma. A presto!\n\n"); 
            break;

          default: 
          System.out.println("Opzione non valida, riprova");

        }
      } else {
        System.out.println("Inserisci un numero tra quelli mostrati nel menu");
        scannerInt.next(); // Pulisco l'input errato per evitare un loop infinito
      }
    }

    // Chiudo le risorse;
    scannerString.close();
    scannerInt.close();
  }
}