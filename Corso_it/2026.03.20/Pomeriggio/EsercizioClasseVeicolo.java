import java.util.Scanner;

// Definizione della classe Veicolo
class Veicolo {
  String marca;
  String modello;
  int anno;
  double prezzo;
  boolean inserito = false;

  public void visualizzaDati() {
    if (!inserito) {
      System.out.println("Nessun veicolo inserito");
    } else {
      System.out.println("Veicolo: " + marca + " " + modello + " (" + anno + ") - Prezzo: " + prezzo + " EUR");
    }
  }

  public void verificaEta() {
    if (!inserito) {
      System.out.println("Nessun veicolo inserito");
      return;
    }
    int anniAttuali = 2026 - anno;
    if (anniAttuali < 5) {
      System.out.println("Il veicolo è... NUOVO");
    } else if (anniAttuali <= 15) {
      System.out.println("Il veicolo è... USATO");
    } else {
      System.out.println("Il veicolo è... VECCHIO");
    }
  }
}

public class EsercizioClasseVeicolo {

  public static void mostraMenu() {
    System.out.println("\n\n--- MENU GESTIONE VEICOLO ---");
    System.out.println("1 - Inserisci dati veicolo");
    System.out.println("2 - Visualizza veicolo");
    System.out.println("3 - Modifica prezzo");
    System.out.println("4 - Verifica età veicolo");
    System.out.println("5 - Esci");
    System.out.print("\nScegli un'opzione: ");
  }

  public static void inserisciDati(Veicolo v, Scanner in) {
    System.out.print("Marca: ");
    v.marca = in.nextLine();
    System.out.print("Modello: ");
    v.modello = in.nextLine();

    do {
      System.out.print("Anno (> 1900): ");
      v.anno = in.nextInt();
    } while (v.anno <= 1900 || v.anno > 2026);

    do {
      System.out.print("Prezzo (> 0): ");
      v.prezzo = in.nextDouble();
    } while (v.prezzo <= 0);
    
    v.inserito = true;
    System.out.println("Dati salvati con successo!");
  }

  public static void modificaPrezzo(Veicolo v, Scanner in) {
    if (v.inserito) {
      System.out.print("Inserisci nuovo prezzo: ");
      double nuovoPrezzo = in.nextDouble();
      if (nuovoPrezzo > 0) {
        v.prezzo = nuovoPrezzo;
        System.out.println("Prezzo aggiornato");
      } else {
        System.out.println("Errore: prezzo non valido");
      }
    } else {
      System.out.println("Nessun veicolo inserito");
    }
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    Veicolo mioVeicolo = new Veicolo();
    int scelta;

    do {
      mostraMenu();

      scelta = in.nextInt();
      in.nextLine(); // Pulisce il buffer

      switch (scelta) {
        case 1:
          inserisciDati(mioVeicolo, in);
          break;

        case 2:
          mioVeicolo.visualizzaDati();
          break;

        case 3:
          modificaPrezzo(mioVeicolo, in);
          break;

        case 4:
          mioVeicolo.verificaEta();
          break;

        case 5:
          System.out.println("Chiusura del programma in corso...");
          System.out.println("Arrivederci!\n");
          break;

        default:
          System.out.println("Opzione non valida");
      }
    } while (scelta != 5);
    
    // Chiudo la risorsa
    in.close();
  }
}