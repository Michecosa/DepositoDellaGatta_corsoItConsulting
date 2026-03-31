import java.sql.*;
import java.util.Scanner;
import java.util.Random;

// Classe base che modella l'Astronauta con i suoi attributi
class Astronauta {
  protected String nome;
  protected String email;
  protected float creditoOssigeno;

  public Astronauta(String nome, String email, float creditoOssigeno) {
    this.nome = nome;
    this.email = email;
    this.creditoOssigeno = creditoOssigeno;
  }

  public void mostraDati() {
    System.out.println("Nome: " + nome);
    System.out.println("Email: " + email);
    System.out.println("Ossigeno: " + creditoOssigeno);
  }
}

// Classe che modella lo Scienziato (estende Astronauta)
class Scienziato extends Astronauta {
  protected int azioni;

  public Scienziato(String nome, String email, float creditoOssigeno, int azioni) {
    super(nome, email, creditoOssigeno);
    this.azioni = azioni;
  }

  public void mostraDati() {
    super.mostraDati();
    System.out.println("Ruolo: " + (azioni >= 3 ? "ScienziatoCapo" : "Scienziato"));
    System.out.println("Azioni completate: " + azioni);
  }
}

// Classe che modella l'Ispettore (estende Astronauta)
class Ispettore extends Astronauta {
  protected int azioni;

  public Ispettore(String nome, String email, float creditoOssigeno, int azioni) {
    super(nome, email, creditoOssigeno);
    this.azioni = azioni;
  }

  public void mostraDati() {
    super.mostraDati();
    System.out.println("Ruolo: " + (azioni >= 3 ? "IspettoreEsperto" : "Ispettore"));
    System.out.println("Azioni completate: " + azioni);
  }
}

public class EsercizioMissioneSpaziale_DB {
  // Costanti per la connessione JDBC
  static final String URL = "jdbc:mysql://localhost:3306/missione_spaziale";
  static final String USER = "root";
  static final String PASS = "root";

  public static void main(String[] args) {
    // Try-with-resources per gestire automaticamente la chiusura di connessione e scanner
    try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
         Scanner sc = new Scanner(System.in)) {
      System.out.println("Connesso al database");
      inizializzaDB(conn);
      menu(conn, sc);
    } catch (SQLException e) { e.printStackTrace(); }
  }

  // Creazione delle tabelle se non esistono (setup iniziale del DB)
  static void inizializzaDB(Connection conn) throws SQLException {
    String sqlAstronauti =
      "CREATE TABLE IF NOT EXISTS astronauti (" +
      "  id INT AUTO_INCREMENT PRIMARY KEY," +
      "  nome VARCHAR(100) NOT NULL," +
      "  email VARCHAR(100) NOT NULL," +
      "  credito_ossigeno FLOAT NOT NULL," +
      "  ruolo ENUM('Scienziato','Ispettore') NOT NULL," +
      "  azioni INT DEFAULT 0" +
      ")";

    String sqlEsperimenti =
      "CREATE TABLE IF NOT EXISTS esperimenti (" +
      "  id INT AUTO_INCREMENT PRIMARY KEY," +
      "  nome VARCHAR(200) NOT NULL," +
      "  valutazione INT DEFAULT NULL," +
      "  id_astronauta INT NOT NULL," +
      "  FOREIGN KEY (id_astronauta) REFERENCES astronauti(id)" +
      ")";

    try (Statement st = conn.createStatement()) {
      st.execute(sqlAstronauti);
      st.execute(sqlEsperimenti);
    }
    System.out.println("Tabelle pronte");
  }

  // Gestione del menu testuale
  static void menu(Connection conn, Scanner sc) throws SQLException {
    int scelta;
    do {
      System.out.println("\n--- MENU MISSIONE SPAZIALE ---");
      System.out.println("1. Crea astronauta");
      System.out.println("2. Visualizza dati astronauta");
      System.out.println("3. Login (rigenera ossigeno)");
      System.out.println("4. Aggiungi esperimento (Scienziato)");
      System.out.println("5. Aggiungi valutazione (Ispettore)");
      System.out.println("6. Mostra esperimenti stazione");
      System.out.println("7. Elenco astronauti");
      System.out.println("0. Esci");
      System.out.print("Scelta: ");
      scelta = sc.nextInt();
      sc.nextLine();

      switch (scelta) {
        case 1 -> creaAstronauta(conn, sc);
        case 2 -> visualizzaAstronauta(conn, sc);
        case 3 -> loginAstronauta(conn, sc);
        case 4 -> aggiungiEsperimento(conn, sc);
        case 5 -> aggiungiValutazione(conn, sc);
        case 6 -> mostraEsperimenti(conn, sc);
        case 7 -> elencaAstronauti(conn);
        case 0 -> System.out.println("Arrivederci!");
        default -> System.out.println("Scelta non valida");
      }
    } while (scelta != 0);
  }

  // CASE 1: Creazione astronauta con ossigeno casuale e recupero dell'ID autoincrementale
  static void creaAstronauta(Connection conn, Scanner sc) throws SQLException {
    System.out.print("Nome: ");
    String nome = sc.nextLine().trim();

    System.out.print("Email: ");
    String email = sc.nextLine().trim();

    System.out.print("Pianeta preferito? (Marte = Scienziato, altro = Ispettore): ");
    String pianeta = sc.nextLine().trim();

    String ruolo = pianeta.equalsIgnoreCase("Marte") ? "Scienziato" : "Ispettore";
    float ossigeno = new Random().nextFloat() * 100;

    String sql = "INSERT INTO astronauti (nome, email, credito_ossigeno, ruolo, azioni) VALUES (?,?,?,?,0)";
    try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
      ps.setString(1, nome);
      ps.setString(2, email);
      ps.setFloat(3, ossigeno);
      ps.setString(4, ruolo);
      ps.executeUpdate();

      ResultSet rs = ps.getGeneratedKeys();
      if (rs.next())
        System.out.println("Astronauta creato con ID " + rs.getInt(1) + " | Ruolo: " + ruolo + " | Ossigeno: " + ossigeno);
    }
  }

  // CASE 2: Caricamento dati dal DB e creazione dell'oggetto Java per la stampa
  static void visualizzaAstronauta(Connection conn, Scanner sc) throws SQLException {
    elencaAstronauti(conn);
    System.out.print("ID astronauta: ");
    int id = sc.nextInt(); sc.nextLine();

    Astronauta a = caricaAstronauta(conn, id);
    if (a != null) a.mostraDati();
    else System.out.println("Astronauta non trovato");
  }

  // CASE 3: Aggiornamento del credito ossigeno nel DB (simulazione login)
  static void loginAstronauta(Connection conn, Scanner sc) throws SQLException {
    elencaAstronauti(conn);
    System.out.print("ID astronauta: ");
    int id = sc.nextInt(); sc.nextLine();

    if (!astronautaEsiste(conn, id)) return;

    float nuovoOssigeno = new Random().nextFloat() * 100;
    String sql = "UPDATE astronauti SET credito_ossigeno = ? WHERE id = ?";
    try (PreparedStatement ps = conn.prepareStatement(sql)) {
      ps.setFloat(1, nuovoOssigeno);
      ps.setInt(2, id);
      ps.executeUpdate();
      System.out.println("Login effettuato. Nuovo ossigeno: " + nuovoOssigeno);
    }
  }

  // CASE 4: Inserimento esperimento con riferimento alla Foreign Key dell'astronauta (solo Scienziato)
  static void aggiungiEsperimento(Connection conn, Scanner sc) throws SQLException {
    elencaAstronautiPerRuolo(conn, "Scienziato");
    System.out.print("ID scienziato: ");
    int id = sc.nextInt(); sc.nextLine();

    if (!astronautaEsisteConRuolo(conn, id, "Scienziato")) {
      System.out.println("Scienziato non trovato");
      return;
    }

    System.out.print("Nome esperimento: ");
    String nomeExp = sc.nextLine().trim();

    String sql = "INSERT INTO esperimenti (nome, id_astronauta) VALUES (?,?)";
    try (PreparedStatement ps = conn.prepareStatement(sql)) {
      ps.setString(1, nomeExp);
      ps.setInt(2, id);
      ps.executeUpdate();
    }

    // Incremento del contatore azioni e verifica promozione a ScienziatoCapo
    aggiornaAzioni(conn, id);
    System.out.println("Esperimento aggiunto: " + nomeExp);

    int azioni = getAzioni(conn, id);
    if (azioni == 3) System.out.println("Sei diventato ScienziatoCapo!");
  }

  // CASE 5: Aggiornamento valutazione esperimento (solo Ispettore)
  static void aggiungiValutazione(Connection conn, Scanner sc) throws SQLException {
    elencaAstronautiPerRuolo(conn, "Ispettore");
    System.out.print("ID ispettore: ");
    int id = sc.nextInt(); sc.nextLine();

    if (!astronautaEsisteConRuolo(conn, id, "Ispettore")) {
      System.out.println("Ispettore non trovato");
      return;
    }

    // Mostra gli esperimenti disponibili (senza valutazione) per la selezione
    String sqlExp = "SELECT id, nome FROM esperimenti WHERE valutazione IS NULL";
    try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sqlExp)) {
      boolean trovato = false;
      System.out.println("Esperimenti disponibili da valutare:");
      while (rs.next()) {
        System.out.println("  [" + rs.getInt("id") + "] " + rs.getString("nome"));
        trovato = true;
      }
      if (!trovato) { System.out.println("Nessun esperimento da valutare"); return; }
    }

    System.out.print("ID esperimento: ");
    int idExp = sc.nextInt();
    System.out.print("Valutazione (1-5): ");
    int val = sc.nextInt(); sc.nextLine();

    if (val < 1 || val > 5) { System.out.println("Valutazione non valida (1-5)"); return; }

    String sql = "UPDATE esperimenti SET valutazione = ? WHERE id = ?";
    try (PreparedStatement ps = conn.prepareStatement(sql)) {
      ps.setInt(1, val);
      ps.setInt(2, idExp);
      int righe = ps.executeUpdate();
      if (righe == 0) { System.out.println("Esperimento non trovato"); return; }
    }

    // Incremento del contatore azioni e verifica promozione a IspettoreEsperto
    aggiornaAzioni(conn, id);
    System.out.println("Valutazione " + val + " aggiunta");

    int azioni = getAzioni(conn, id);
    if (azioni == 3) System.out.println("Sei diventato IspettoreEsperto!");
  }

  // CASE 6: Visualizzazione di tutti gli esperimenti della stazione (con filtro opzionale per ScienziatoCapo/IspettoreEsperto)
  static void mostraEsperimenti(Connection conn, Scanner sc) throws SQLException {
    elencaAstronauti(conn);
    System.out.print("ID astronauta (0 per tutti): ");
    int id = sc.nextInt(); sc.nextLine();

    String sql;
    PreparedStatement ps;

    if (id == 0) {
      // Tutti gli esperimenti: accesso da ScienziatoCapo o IspettoreEsperto
      sql = "SELECT e.nome, e.valutazione, a.nome AS autore FROM esperimenti e JOIN astronauti a ON e.id_astronauta = a.id";
      ps = conn.prepareStatement(sql);
    } else {
      if (!astronautaEsiste(conn, id)) return;
      // Solo gli esperimenti dell'astronauta selezionato
      sql = "SELECT e.nome, e.valutazione, a.nome AS autore FROM esperimenti e JOIN astronauti a ON e.id_astronauta = a.id WHERE e.id_astronauta = ?";
      ps = conn.prepareStatement(sql);
      ps.setInt(1, id);
    }

    try (ps; ResultSet rs = ps.executeQuery()) {
      System.out.println("\n--- ESPERIMENTI ---");
      boolean trovato = false;
      while (rs.next()) {
        String valStr = rs.getObject("valutazione") != null ? String.valueOf(rs.getInt("valutazione")) : "nessuna";
        System.out.println("  Autore: " + rs.getString("autore") + " | Esperimento: " + rs.getString("nome") + " | Valutazione: " + valStr);
        trovato = true;
      }
      if (!trovato) System.out.println("  Nessun esperimento trovato");
    }
  }

  // CASE 7: Semplice query SELECT per mostrare tutti gli astronauti con il loro ruolo attuale
  static void elencaAstronauti(Connection conn) throws SQLException {
    String sql = "SELECT id, nome, ruolo, azioni FROM astronauti";
    try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
      System.out.println("\n--- ASTRONAUTI ---");
      while (rs.next()) {
        String ruolo = rs.getString("ruolo");
        int azioni = rs.getInt("azioni");
        // Visualizzazione del livello avanzato se le azioni sono >= 3
        String livello = (ruolo.equals("Scienziato") && azioni >= 3) ? "ScienziatoCapo"
                       : (ruolo.equals("Ispettore") && azioni >= 3) ? "IspettoreEsperto"
                       : ruolo;
        System.out.println("  [" + rs.getInt("id") + "] " + rs.getString("nome") + " | " + livello);
      }
    }
  }

  // Funzione ausiliaria: elenca solo gli astronauti di un determinato ruolo
  static void elencaAstronautiPerRuolo(Connection conn, String ruolo) throws SQLException {
    String sql = "SELECT id, nome, azioni FROM astronauti WHERE ruolo = ?";
    try (PreparedStatement ps = conn.prepareStatement(sql)) {
      ps.setString(1, ruolo);
      ResultSet rs = ps.executeQuery();
      System.out.println("\n--- " + ruolo.toUpperCase() + "I ---");
      while (rs.next()) {
        int azioni = rs.getInt("azioni");
        String livello = (ruolo.equals("Scienziato") && azioni >= 3) ? "ScienziatoCapo"
                       : (ruolo.equals("Ispettore") && azioni >= 3) ? "IspettoreEsperto"
                       : ruolo;
        System.out.println("  [" + rs.getInt("id") + "] " + rs.getString("nome") + " | " + livello);
      }
    }
  }

  // Funzione core: trasforma una riga della tabella SQL in un oggetto Java (O.R.M. manuale)
  static Astronauta caricaAstronauta(Connection conn, int id) throws SQLException {
    String sql = "SELECT nome, email, credito_ossigeno, ruolo, azioni FROM astronauti WHERE id = ?";
    try (PreparedStatement ps = conn.prepareStatement(sql)) {
      ps.setInt(1, id);
      ResultSet rs = ps.executeQuery();
      if (rs.next()) {
        String nome = rs.getString("nome");
        String email = rs.getString("email");
        float ossigeno = rs.getFloat("credito_ossigeno");
        String ruolo = rs.getString("ruolo");
        int azioni = rs.getInt("azioni");

        if (ruolo.equals("Scienziato")) return new Scienziato(nome, email, ossigeno, azioni);
        else return new Ispettore(nome, email, ossigeno, azioni);
      }
    }
    return null;
  }

  // Funzione di validazione per controllare l'integrità referenziale prima degli inserimenti
  static boolean astronautaEsiste(Connection conn, int id) throws SQLException {
    String sql = "SELECT id FROM astronauti WHERE id = ?";
    try (PreparedStatement ps = conn.prepareStatement(sql)) {
      ps.setInt(1, id);
      if (!ps.executeQuery().next()) {
        System.out.println("Astronauta non trovato");
        return false;
      }
      return true;
    }
  }

  // Funzione di validazione con verifica del ruolo specifico
  static boolean astronautaEsisteConRuolo(Connection conn, int id, String ruolo) throws SQLException {
    String sql = "SELECT id FROM astronauti WHERE id = ? AND ruolo = ?";
    try (PreparedStatement ps = conn.prepareStatement(sql)) {
      ps.setInt(1, id);
      ps.setString(2, ruolo);
      return ps.executeQuery().next();
    }
  }

  // Incrementa il contatore azioni di un astronauta dopo ogni operazione
  static void aggiornaAzioni(Connection conn, int id) throws SQLException {
    String sql = "UPDATE astronauti SET azioni = azioni + 1 WHERE id = ?";
    try (PreparedStatement ps = conn.prepareStatement(sql)) {
      ps.setInt(1, id);
      ps.executeUpdate();
    }
  }

  // Recupera il numero attuale di azioni di un astronauta
  static int getAzioni(Connection conn, int id) throws SQLException {
    String sql = "SELECT azioni FROM astronauti WHERE id = ?";
    try (PreparedStatement ps = conn.prepareStatement(sql)) {
      ps.setInt(1, id);
      ResultSet rs = ps.executeQuery();
      if (rs.next()) return rs.getInt("azioni");
    }
    return 0;
  }
}