import java.util.Scanner;

// Classe Prodotto
class Prodotto {
  String nome;
  double prezzo;
  int quantita;

  public Prodotto(String nome, double prezzo, int quantita) {
    this.nome = nome;
    this.prezzo = prezzo;
    this.quantita = quantita;
  }

  public void mostraDati() {
    System.out.println("Prodotto: " + nome + " - " + prezzo + " EUR");
    System.out.println("Qta: x" + quantita + "\n");
  }
}

// Classe Negozio
class Negozio {
  String nomeNegozio;
  Prodotto prodotto;

  public Negozio(String nomeNegozio) {
    this.nomeNegozio = nomeNegozio;
    this.prodotto = null; 
  }

  public void mostraNegozio() {
    System.out.println(nomeNegozio + " - Con voi dal 1200");
    if (prodotto != null) {
      prodotto.mostraDati();
    } else {
      System.out.println("Scaffali vuoti! Inserisci un prodotto\n");
    }
  }
}

public class EsercizioGestioneCommerciale {

  // 1. Funzione per l'inserimento
  static void inserisciProdotto(Scanner input, Negozio n1, Negozio n2, Negozio n3) {
    System.out.print("Indica il negozio (1, 2 o 3)? ");
    int numeroNegozio = input.nextInt();
    input.nextLine(); 
    
    System.out.print("Nome prodotto: ");
    String nomeP = input.nextLine();
    System.out.print("Prezzo: ");
    double prezzoP = input.nextDouble();
    System.out.print("Quantità: ");
    int qtaP = input.nextInt();

    Prodotto nuovo = new Prodotto(nomeP, prezzoP, qtaP);

    if (numeroNegozio == 1) n1.prodotto = nuovo;
    else if (numeroNegozio == 2) n2.prodotto = nuovo;
    else if (numeroNegozio == 3) n3.prodotto = nuovo;
    else System.out.println("Negozio non valido!");
  }

  // 2. Funzione per visualizzare tutto
  static void visualizzaTutti(Negozio n1, Negozio n2, Negozio n3) {
    n1.mostraNegozio();
    n2.mostraNegozio();
    n3.mostraNegozio();
  }

  // 3. Funzione per trovare il più costoso
  static void trovaPiuCostoso(Negozio n1, Negozio n2, Negozio n3) {
    Negozio vincitore = n1;

    if (n2.prodotto != null && (vincitore.prodotto == null || n2.prodotto.prezzo > vincitore.prodotto.prezzo)) {
      vincitore = n2;
    }
    if (n3.prodotto != null && (vincitore.prodotto == null || n3.prodotto.prezzo > vincitore.prodotto.prezzo)) {
      vincitore = n3;
    }

    if (vincitore.prodotto != null) {
      System.out.println("Il negozio con il prodotto più costoso è: " + vincitore.nomeNegozio);
    } else {
      System.out.println("Nessun prodotto presente");
    }
  }

  // 4. Funzione per modificare la quantità
  static void modificaQuantita(Scanner input, Negozio n1, Negozio n2, Negozio n3) {
    System.out.print("Indica il negozio (1, 2, 3)? ");
    int sceltaNegozio = input.nextInt();
    System.out.print("Nuova quantità: ");
    int nuovaQta = input.nextInt();

    if (sceltaNegozio == 1 && n1.prodotto != null) n1.prodotto.quantita = nuovaQta;
    else if (sceltaNegozio == 2 && n2.prodotto != null) n2.prodotto.quantita = nuovaQta;
    else if (sceltaNegozio == 3 && n3.prodotto != null) n3.prodotto.quantita = nuovaQta;
    else System.out.println("Errore: Negozio vuoto o non esistente");
  }

  // 5. Funzione per la vendita
  static void vendiProdotto(Scanner input, Negozio n1, Negozio n2, Negozio n3) {
    System.out.print("Indica il negozio (1, 2, 3)? ");
    int sceltaNegozio = input.nextInt();
    System.out.print("Quanti pezzi vendere? ");
    int qtaDaVendere = input.nextInt();

    Negozio selezionato = (sceltaNegozio == 1) ? n1 : (sceltaNegozio == 2 ? n2 : n3);
    
    if (selezionato.prodotto != null && selezionato.prodotto.quantita >= qtaDaVendere) {
      selezionato.prodotto.quantita -= qtaDaVendere;
      System.out.println("Vendita effettuata!");
    } else {
      System.out.println("Quantità insufficiente o prodotto assente");
    }
  }

  static void mostraMenu() {
    System.out.println("\n--- MENU GESTIONE COMMERCIALE ---");
    System.out.println("1 - Inserisci prodotto");
    System.out.println("2 - Visualizza negozi");
    System.out.println("3 - Cerca prodotto più costoso");
    System.out.println("4 - Modifica quantità");
    System.out.println("5 - Vendi prodotti");
    System.out.println("6 - Esci");
    System.out.print("Scegli un'opzione: ");
  }

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    int scelta;

    Negozio n1 = new Negozio("Negozio 1");
    Negozio n2 = new Negozio("Negozio 2");
    Negozio n3 = new Negozio("Negozio 3");

    do {
      mostraMenu();
      scelta = input.nextInt();
      input.nextLine(); 

      switch (scelta) {
        case 1: inserisciProdotto(input, n1, n2, n3); break;
        case 2: visualizzaTutti(n1, n2, n3); break;
        case 3: trovaPiuCostoso(n1, n2, n3); break;
        case 4: modificaQuantita(input, n1, n2, n3); break;
        case 5: vendiProdotto(input, n1, n2, n3); break;
        case 6: System.out.println("Chiusura programma..."); break;
        default: System.out.println("Opzione non valida"); break;
      }
    } while (scelta != 6);

    input.close();
  }
}