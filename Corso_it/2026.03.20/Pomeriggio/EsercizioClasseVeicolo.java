import java.util.Scanner;

// Definizione della classe Veicolo
class Veicolo {
  String marca;
  String modello;
  int anno;
  double prezzo;
  boolean inserito = false; // Serve per il controllo al punto 2

  // Metodo d'istanza per visualizzare i dati
  public void visualizzaDati() {
    if (!inserito) {
      System.out.println("Nessun veicolo inserito");
    } else {
      System.out.println("Veicolo: " + marca + " " + modello + " (" + anno + ") - Prezzo: " + prezzo + " EUR");
    }
  }

  // Metodo per verificare l'età del veicolo
  public void verificaEta() {
    if (!inserito) {
      System.out.println("Nessun veicolo inserito");
      return;
    }
    int anniAttuali = 2026 - anno; // Calcolato sull'anno attuale
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

  
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    Veicolo mioVeicolo = new Veicolo(); // Creazione dell'oggetto
    int scelta;

    do {
      mostraMenu();

      scelta = in.nextInt();
      in.nextLine(); // Pulisce il buffer

      switch (scelta) {
        case 1:
          System.out.print("Marca: ");
          mioVeicolo.marca = in.nextLine();
          System.out.print("Modello: ");
          mioVeicolo.modello = in.nextLine();
          
          // Controllo Anno
          do {
            System.out.print("Anno (> 1900): ");
            mioVeicolo.anno = in.nextInt();
          } while (mioVeicolo.anno <= 1900 || mioVeicolo.anno > 2026);
          
          // Controllo Prezzo
          do {
            System.out.print("Prezzo (> 0): ");
            mioVeicolo.prezzo = in.nextDouble();
          } while (mioVeicolo.prezzo <= 0);
          
          mioVeicolo.inserito = true;
          System.out.println("Dati salvati con successo!");
          break;

        case 2:
          mioVeicolo.visualizzaDati();
          break;

        case 3:
          if (mioVeicolo.inserito) {
            System.out.print("Inserisci nuovo prezzo: ");
            double nuovoPrezzo = in.nextDouble();
            if (nuovoPrezzo > 0) {
              mioVeicolo.prezzo = nuovoPrezzo;
              System.out.println("Prezzo aggiornato");
            } else {
              System.out.println("Errore: prezzo non valido");
            }
          } else {
            System.out.println("Nessun veicolo inserito");
          }
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
    
    // Chiudo delle risorse
    in.close();
  }
}