import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

//  CLASSE BASE 
class Astronauta {
  protected String nome;
  protected String email;
  protected float creditoOssigeno;

  public Astronauta(String nome, String email) {
    this.nome = nome;
    this.email = email;
    this.creditoOssigeno = new Random().nextFloat() * 100;
  }

  public void mostraDati() {
    System.out.println("Nome: " + nome);
    System.out.println("Email: " + email);
    System.out.println("Ossigeno: " + creditoOssigeno);
  }

  public void login() {
    this.creditoOssigeno = new Random().nextFloat() * 100;
    System.out.println("Login effettuato. Nuovo ossigeno: " + creditoOssigeno);
  }
}

//  CLASSE CONTENITORE 
class StazioneSpaziale {
  static class Esperimento {
    String nome;
    Integer valutazione;

    Esperimento(String nome) { 
      this.nome = nome; 
    }
  }

  ArrayList<Esperimento> esperimenti = new ArrayList<>();
}

//  CLASSI DERIVATE 
class Scienziato extends Astronauta {
  protected int azioni = 0;
  protected StazioneSpaziale stazione;

  public Scienziato(String nome, String email, StazioneSpaziale stazione) {
    super(nome, email);
    this.stazione = stazione;
  }

  public Astronauta aggiungiEsperimento(String exp) {
    stazione.esperimenti.add(new StazioneSpaziale.Esperimento(exp));
    azioni++;
    System.out.println("Esperimento aggiunto: " + exp);
    if (azioni >= 3) {
      ScienziatoCapo capo = new ScienziatoCapo(nome, email, stazione);
      capo.azioni = azioni;
      System.out.println("Sei diventato ScienziatoCapo!");
      return capo;
    }
    return this;
  }
}

class ScienziatoCapo extends Scienziato {
  public ScienziatoCapo(String nome, String email, StazioneSpaziale stazione) {
    super(nome, email, stazione);
  }

  public void mostraEsperimentiTutti() {
    System.out.println("Tutti gli esperimenti:");
    for (StazioneSpaziale.Esperimento e : stazione.esperimenti)
      System.out.println("- " + e.nome + " | valutazione: " + (e.valutazione != null ? e.valutazione : "nessuna"));
  }
}

class Ispettore extends Astronauta {
  protected int azioni = 0;
  protected StazioneSpaziale stazione;

  public Ispettore(String nome, String email, StazioneSpaziale stazione) {
    super(nome, email);
    this.stazione = stazione;
  }

  public Astronauta aggiungiValutazione(int val, int indice) {
    if (val < 1 || val > 5) { 
      System.out.println("Valutazione non valida (1-5)."); 
      return this; 
    }

    if (indice < 0 || indice >= stazione.esperimenti.size()) { 
      System.out.println("Indice esperimento non valido."); 
      return this; 
    }
    stazione.esperimenti.get(indice).valutazione = val;
    azioni++;

    System.out.println("Valutazione aggiunta: " + val);
    if (azioni >= 3) {
      IspettoreEsperto esperto = new IspettoreEsperto(nome, email, stazione);
      esperto.azioni = azioni;
      System.out.println("Sei diventato IspettoreEsperto!");
      return esperto;
    }
    return this;
  }
}

class IspettoreEsperto extends Ispettore {
  public IspettoreEsperto(String nome, String email, StazioneSpaziale stazione) {
    super(nome, email, stazione);
  }

  public void mostraValutazioniTutte() {
    System.out.println("Tutte le valutazioni:");
    for (StazioneSpaziale.Esperimento e : stazione.esperimenti)
      if (e.valutazione != null) System.out.println("- " + e.nome + ": " + e.valutazione);
  }
}

//  MAIN 
public class EsercizioMissioneSpaziale {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    StazioneSpaziale stazione = new StazioneSpaziale();
    Astronauta astronauta = null;

    int scelta = 1;
    while (scelta != 5) {
      System.out.println("\n1. Crea astronauta");
      System.out.println("2. Visualizza dati");
      System.out.println("3. Login (rigenera ossigeno)");
      System.out.println("4. Interagisci con il profilo");
      System.out.println("5. Esci");
      System.out.print("Scelta: ");
      scelta = Integer.parseInt(scanner.nextLine());

      switch (scelta) {

        case 1:
          System.out.print("Nome: ");
          String nome = scanner.nextLine();

          System.out.print("Email: ");
          String email = scanner.nextLine();

          System.out.print("Pianeta preferito? (Marte = Scienziato, altro = Ispettore): ");
          String pianeta = scanner.nextLine();

          if (pianeta.equalsIgnoreCase("Marte")) {
            astronauta = new Scienziato(nome, email, stazione);
            System.out.println("Ruolo: Scienziato");
          } else {
            astronauta = new Ispettore(nome, email, stazione);
            System.out.println("Ruolo: Ispettore");
          }
          break;

        case 2:
          if (astronauta == null) { 
            System.out.println("Nessun astronauta creato"); 
            break; 
          }
          astronauta.mostraDati();
          break;

        case 3:
          if (astronauta == null) { 
            System.out.println("Nessun astronauta creato"); 
            break; 
          }
          astronauta.login();
          break;

        case 4:
          if (astronauta == null) { 
            System.out.println("Nessun astronauta creato"); 
            break; 
          }

          if (astronauta instanceof Scienziato) {
            Scienziato scienziato = (Scienziato) astronauta;
            System.out.println("a. Aggiungi esperimento");
            if (scienziato instanceof ScienziatoCapo) 
              System.out.println("b. Mostra tutti gli esperimenti (ScienziatoCapo)");

            System.out.print("Scelta: ");
            String sub = scanner.nextLine();

            if (sub.equals("a")) {
              System.out.print("Nome esperimento: ");
              astronauta = scienziato.aggiungiEsperimento(scanner.nextLine());
            } else if (sub.equals("b") && scienziato instanceof ScienziatoCapo) {
              ((ScienziatoCapo) scienziato).mostraEsperimentiTutti();
            }

          } else if (astronauta instanceof Ispettore) {
            Ispettore ispettore = (Ispettore) astronauta;
            System.out.println("a. Inserisci valutazione (1-5)");

            if (ispettore instanceof IspettoreEsperto) 
              System.out.println("b. Mostra tutte le valutazioni (IspettoreEsperto)");

            System.out.print("Scelta: ");
            String sub = scanner.nextLine();

            if (sub.equals("a")) {
              if (stazione.esperimenti.isEmpty()) { 
                System.out.println("Nessun esperimento disponibile"); 
                break; 
              }

              System.out.println("Scegli esperimento da valutare:");
              for (int idx = 0; idx < stazione.esperimenti.size(); idx++)
                System.out.println(idx + ". " + stazione.esperimenti.get(idx).nome);

              System.out.print("Indice: ");
              int indice = Integer.parseInt(scanner.nextLine());
              System.out.print("Valutazione (1-5): ");
              astronauta = ispettore.aggiungiValutazione(Integer.parseInt(scanner.nextLine()), indice);

            } else if (sub.equals("b") && ispettore instanceof IspettoreEsperto) {
              ((IspettoreEsperto) ispettore).mostraValutazioniTutte();
            }
          }
          break;

        case 5:
          System.out.println("Arrivederci!");
          break;

        default:
          System.out.println("Scelta non valida");
      }
    }
    scanner.close();
  }
}