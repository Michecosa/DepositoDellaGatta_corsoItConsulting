import java.util.Scanner;

// 1. CLASSE SVILUPPATORE
class Sviluppatore {
  String nome;
  String cognome;
  String ruolo;

  public Sviluppatore(String nome, String cognome, String ruolo) {
    this.nome = nome;
    this.cognome = cognome;
    this.ruolo = ruolo;
  }

  public void mostraSviluppatore() {
    System.out.println("Lead Developer: " + nome + " " + cognome + " [" + ruolo + "]");
  }
}

// 2. CLASSE GIOCO
class Gioco {
  String titolo;
  String genere;
  float costoSviluppo;
  String statoProgetto; // "IN SVILUPPO", "IN TEST", "PUBBLICATO"

  public Gioco(String titolo, String genere, float costoSviluppo, String statoProgetto) {
    this.titolo = titolo;
    this.genere = genere;
    this.costoSviluppo = costoSviluppo;
    this.statoProgetto = statoProgetto;
  }

  public void mostraDettagli() {
    System.out.println("Progetto: " + titolo + " (" + genere + ")");
    System.out.println("Budget: " + costoSviluppo + " EUR | Stato: " + statoProgetto);
  }
}

// 3. CLASSE TEAM
class Team {
  String nomeTeam;
  Sviluppatore leadDev;
  Gioco giocoAssegnato;

  // Un team non può esistere senza almeno uno sviluppatore
  public Team(String nomeTeam, Sviluppatore leadDev) {
    this.nomeTeam = nomeTeam;
    this.leadDev = leadDev;
    this.giocoAssegnato = null;
  }

  public void mostraInfoTeam() {
    System.out.println("\n--- TEAM: " + nomeTeam + " ---");
    leadDev.mostraSviluppatore();

    if (giocoAssegnato != null) {
      giocoAssegnato.mostraDettagli();
    } else {
      System.out.println("Stato: In attesa");
    }
  }
}

public class EsercizioSimpatico {

  // Funzione 1: Assegna Gioco
  static void assegnaGioco(Scanner input, Team t1, Team t2, Team t3) {
    System.out.print("Team (1, 2 o 3): ");
    int scelta = input.nextInt();
    input.nextLine(); 

    System.out.print("Titolo: ");
    String titolo = input.nextLine();
    System.out.print("Genere: ");
    String genere = input.nextLine();
    System.out.print("Costo: ");
    float costo = input.nextFloat();
    input.nextLine();
    System.out.print("Stato: ");
    String stato = input.nextLine().toUpperCase();

    Gioco nuovo = new Gioco(titolo, genere, costo, stato);

    if (scelta == 1) t1.giocoAssegnato = nuovo;
    else if (scelta == 2) t2.giocoAssegnato = nuovo;
    else if (scelta == 3) t3.giocoAssegnato = nuovo;
    else System.out.println("Errore team");
  }

  // Funzione 3: Cerca Gioco più costoso
  static void trovaGiocoCostoso(Team t1, Team t2, Team t3) {
    Team vincitore = t1;
 
    if (t2.giocoAssegnato != null && (vincitore.giocoAssegnato == null || t2.giocoAssegnato.costoSviluppo > vincitore.giocoAssegnato.costoSviluppo)) {
      vincitore = t2;
    }
    if (t3.giocoAssegnato != null && (vincitore.giocoAssegnato == null || t3.giocoAssegnato.costoSviluppo > vincitore.giocoAssegnato.costoSviluppo)) {
      vincitore = t3;
    }
 
    if (vincitore.giocoAssegnato != null) {
      System.out.println("Gioco più costoso: " + vincitore.giocoAssegnato.titolo + " del team " + vincitore.nomeTeam);
    } else {
      System.out.println("Nessun gioco presente");
    }
  }

  // Funzione 4: Modifica Stato
  static void modificaStato(Scanner input, Team t1, Team t2, Team t3) {
    System.out.print("Team (1, 2, 3): ");
    int scelta = input.nextInt();
    input.nextLine();

    Team teamScelto;
    
    switch (scelta) {
      case 1: teamScelto = t1; break;
      case 2: teamScelto = t2; break;
      case 3: teamScelto = t3; break;
      default:
        System.out.println("Team non valido");
        return; // Esci dalla funzione se l'input è errato
    }

    if (teamScelto.giocoAssegnato != null) {
      System.out.print("Nuovo stato: ");
      teamScelto.giocoAssegnato.statoProgetto = input.nextLine().toUpperCase();
    } else {
      System.out.println("Il team " + teamScelto.nomeTeam + " non ha giochi assegnati.");
    }
  }

  // Funzione 5: ASSEGNA LEAD DEVELOPER
  static void assegnaSviluppatore(Scanner input, Team t1, Team t2, Team t3) {
    System.out.print("Team (1, 2, 3): ");
    int scelta = input.nextInt();
    input.nextLine();
    System.out.print("Nome: ");
    String n = input.nextLine();
    System.out.print("Cognome: ");
    String c = input.nextLine();
    System.out.print("Ruolo (es. Senior, Junior): ");
    String r = input.nextLine();

    Sviluppatore dev = new Sviluppatore(n, c, r);

    if (scelta == 1) t1.leadDev = dev;
    else if (scelta == 2) t2.leadDev = dev;
    else if (scelta == 3) t3.leadDev = dev;
    else System.out.println("Team non valido");
  }

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    // Ogni team viene creato con il proprio sviluppatore iniziale obbligatorio
    Team t1 = new Team("Alpha-Coders", new Sviluppatore("Mario", "Rossi", "Senior"));
    Team t2 = new Team("Beta-Testers", new Sviluppatore("Luca", "Bianchi", "Junior"));
    Team t3 = new Team("Gamma-Design", new Sviluppatore("Sara", "Verdi", "Mid"));

    int scelta;
    do {
      System.out.println("\n--- GESTIONE SOFTWARE HOUSE ---");
      System.out.println("1 - Assegna gioco a un team");
      System.out.println("2 - Visualizza tutti i team");
      System.out.println("3 - Cerca il gioco con costo più alto");
      System.out.println("4 - Modifica stato di un gioco");
      System.out.println("5 - Assegna Sviluppatore");
      System.out.println("6 - Esci");
      System.out.print("Scelta: ");
      scelta = input.nextInt();

      switch (scelta) {
        case 1: assegnaGioco(input, t1, t2, t3); break;
        case 2: 
          t1.mostraInfoTeam(); 
          t2.mostraInfoTeam(); 
          t3.mostraInfoTeam(); 
          break;
        case 3: trovaGiocoCostoso(t1, t2, t3); break;
        case 4: modificaStato(input, t1, t2, t3); break;
        case 5: assegnaSviluppatore(input, t1, t2, t3); break;
        case 6: System.out.println("Adios\n"); break;
        default: System.out.println("Eh?\n");
      }
    } while (scelta != 6);
    input.close();
  }
}