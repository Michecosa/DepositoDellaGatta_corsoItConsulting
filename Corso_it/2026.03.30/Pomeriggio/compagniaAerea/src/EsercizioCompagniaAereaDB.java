import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

// Classe che modella l'oggetto Aereo con i suoi attributi privati
class Aereo {
  private String modello, codice;
  private int numeroPosti;

  public Aereo(String modello, int numeroPosti, String codice) {
    this.modello = modello;
    this.codice = codice;
    setNumeroPosti(numeroPosti);
  }

  // Metodi Getter per l'accesso ai dati privati
  public String getModello() { return modello; }
  public String getCodice() { return codice; }
  public int getNumeroPosti() { return numeroPosti; }

  // Metodi Setter con logica di validazione (Incapsulamento)
  public void setModello(String modello) { this.modello = modello; }
  public void setCodice(String codice) { this.codice = codice; }
  public void setNumeroPosti(int numeroPosti) {
    if (numeroPosti > 0) this.numeroPosti = numeroPosti;
    else System.out.println("Errore: il numero di posti deve essere positivo");
  }
}

// Classe che modella il Pilota
class Pilota {
  private String nome, numeroBrevetto;
  private int oreVolo;

  public Pilota(String nome, String numeroBrevetto, int oreVolo) {
    this.nome = nome;
    this.numeroBrevetto = numeroBrevetto;
    setOreVolo(oreVolo);
  }

  public String getNome() { return nome; }
  public String getNumeroBrevetto() { return numeroBrevetto; }
  public int getOreVolo() { return oreVolo; }

  public void setNome(String nome) { this.nome = nome; }
  public void setNumeroBrevetto(String brevetto) { this.numeroBrevetto = brevetto; }
  public void setOreVolo(int oreVolo) {
    if (oreVolo > 0) this.oreVolo = oreVolo;
    else System.out.println("Errore: le ore di volo devono essere maggiori di zero");
  }
}

// Classe CompagniaAerea che aggrega aerei e piloti (Composizione)
class CompagniaAerea {
  private String nome;
  private ArrayList<Aereo> flotta = new ArrayList<>();
  private ArrayList<Pilota> piloti = new ArrayList<>();

  public CompagniaAerea(String nome) { this.nome = nome; }
  public String getNome() { return nome; }
  public void setNome(String nome) { this.nome = nome; }
  public void aggiungiAereo(Aereo aereo) { flotta.add(aereo); }
  public void aggiungiPilota(Pilota pilota) { piloti.add(pilota); }

  // Metodo per stampare il riepilogo completo dell'oggetto caricato
  public void stampaInfo() {
    System.out.println("\n--- COMPAGNIA: " + nome.toUpperCase() + " ---");
    System.out.println("FLOTTA (" + flotta.size() + " aerei)");

    if (flotta.isEmpty()) 
      System.out.println("  Nessun aereo presente");

    else 
      for (Aereo a : flotta) 
        System.out.println("  Modello: " + a.getModello() + " | Codice: " + a.getCodice() + " | Posti: " + a.getNumeroPosti());

    System.out.println("\nPILOTI (" + piloti.size() + " piloti)");
    if (piloti.isEmpty()) 
      System.out.println("  Nessun pilota presente");

    else 
      for (Pilota p : piloti) System.out.println("  Nome: " + p.getNome() + " | Brevetto: " + p.getNumeroBrevetto() + " | Ore: " + p.getOreVolo());
  }
}

public class EsercizioCompagniaAereaDB {
  // Costanti per la connessione JDBC
  static final String URL = "jdbc:mysql://localhost:3306/gestione_flotta";
  static final String USER = "root";
  static final String PASS = "root";

  public static void main(String[] args) {
    // Try-with-resources per gestire automaticamente la chiusura di connessione e scanner
    try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
      Scanner sc = new Scanner(System.in)) {
      System.out.println("Connesso al database");
      menu(conn, sc);
    } catch (SQLException e) { e.printStackTrace(); }
  }

  // Gestione del menu testuale
  static void menu(Connection conn, Scanner sc) throws SQLException {
    int scelta;
    do {
      System.out.println("\n --- MENU GESTIONE FLOTTE xCOMPAGNIA ---");
      System.out.println("1. Crea compagnia");
      System.err.println("2. Aggiungi aereo");
      System.out.println("3. Aggiungi pilota");
      System.out.println("4. Visualizza info");
      System.out.println("5. Elenco");
      System.out.println("0. Esci");
      System.out.print("Scelta: ");
      scelta = sc.nextInt(); 
      sc.nextLine();

      switch (scelta) {
        case 1 -> creaCompagnia(conn, sc);
        case 2 -> aggiungiAereo(conn, sc);
        case 3 -> aggiungiPilota(conn, sc);
        case 4 -> visualizzaCompagnia(conn, sc);
        case 5 -> elencaCompagnie(conn);
        case 0 -> System.out.println("Arrivederci!");
      }
    } while (scelta != 0);
  }

  // CASE 1: Inserimento nuova compagnia e recupero dell'ID autoincrementale
  static void creaCompagnia(Connection conn, Scanner sc) throws SQLException {
    System.out.print("Nome compagnia: ");
    String nome = sc.nextLine().trim();

    String sql = "INSERT INTO compagnie (nome) VALUES (?)";

    try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
      ps.setString(1, nome); 
      ps.executeUpdate();

      ResultSet rs = ps.getGeneratedKeys();

      if (rs.next()) 
        System.out.println("Creata con ID " + rs.getInt(1));
    }
  }

  // CASE 2: Inserimento aereo con riferimento alla Foreign Key della compagnia
  static void aggiungiAereo(Connection conn, Scanner sc) throws SQLException {
    elencaCompagnie(conn);

    System.out.print("ID compagnia: "); 
    int id = sc.nextInt(); sc.nextLine();

    if (!compagniaEsiste(conn, id)) return;

    System.out.print("Modello: "); 
    String mod = sc.nextLine();

    System.out.print("Codice: "); 
    String cod = sc.nextLine();

    System.out.print("Posti: "); 
    int posti = sc.nextInt();

    String sql = "INSERT INTO aerei (modello, codice, numero_posti, id_compagnia) VALUES (?,?,?,?)";

    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setString(1, mod); 
      pstmt.setString(2, cod); 
      pstmt.setInt(3, posti); 
      pstmt.setInt(4, id);

      pstmt.executeUpdate(); 
      System.out.println("Aereo aggiunto");
    }
  }

  // CASE 3: Inserimento pilota collegato al database
  static void aggiungiPilota(Connection conn, Scanner sc) throws SQLException {
    elencaCompagnie(conn);
    System.out.print("ID compagnia: "); 
    int id = sc.nextInt(); 
    sc.nextLine();

    if (!compagniaEsiste(conn, id)) return;
    
    System.out.print("Nome: "); 
    String nome = sc.nextLine();

    System.out.print("Brevetto: "); 
    String brev = sc.nextLine();

    System.out.print("Ore volo: "); 
    int ore = sc.nextInt();

    String sql = "INSERT INTO piloti (nome, numero_brevetto, ore_volo, id_compagnia) VALUES (?,?,?,?)";
    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setString(1, nome); 
      pstmt.setString(2, brev); 
      pstmt.setInt(3, ore); 
      pstmt.setInt(4, id);

      pstmt.executeUpdate(); 
      System.out.println("Pilota aggiunto");
    }
  }

  // CASE 4: Caricamento dati dal DB e creazione degli oggetti Java per la stampa
  static void visualizzaCompagnia(Connection conn, Scanner sc) throws SQLException {
    elencaCompagnie(conn);
    System.out.print("ID compagnia: ");
    CompagniaAerea compagnia = caricaCompagniaCompleta(conn, sc.nextInt());
    if (compagnia != null) compagnia.stampaInfo();
    else System.out.println("Compagnia non trovata");
  }

  // CASE 5: Semplice query SELECT per mostrare le compagnie disponibili
  static void elencaCompagnie(Connection conn) throws SQLException {
    String sql = "SELECT id, nome FROM compagnie";
    try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
      while (rs.next()) System.out.println("  [" + rs.getInt("id") + "] " + rs.getString("nome"));
    }
  }

  // Funzione core: Trasforma le righe delle tabelle SQL in oggetti Java (O.R.M. manuale)
  static CompagniaAerea caricaCompagniaCompleta(Connection conn, int id) throws SQLException {
    CompagniaAerea compagnia = null;
    String sqlC = "SELECT nome FROM compagnie WHERE id = ?";
    try (PreparedStatement ps = conn.prepareStatement(sqlC)) {
      ps.setInt(1, id);
      ResultSet rs = ps.executeQuery();
      if (rs.next()) compagnia = new CompagniaAerea(rs.getString("nome"));
    }
    if (compagnia != null) {
      // Caricamento della lista aerei
      String sqlA = "SELECT modello, numero_posti, codice FROM aerei WHERE id_compagnia = ?";
      try (PreparedStatement ps = conn.prepareStatement(sqlA)) {
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) compagnia.aggiungiAereo(new Aereo(rs.getString("modello"), rs.getInt("numero_posti"), rs.getString("codice")));
      }
      // Caricamento della lista piloti
      String sqlP = "SELECT nome, numero_brevetto, ore_volo FROM piloti WHERE id_compagnia = ?";
      try (PreparedStatement ps = conn.prepareStatement(sqlP)) {
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) compagnia.aggiungiPilota(new Pilota(rs.getString("nome"), rs.getString("numero_brevetto"), rs.getInt("ore_volo")));
      }
    }
    return compagnia;
  }

  // Funzione di validazione per controllare l'integrità referenziale prima degli inserimenti
  static boolean compagniaEsiste(Connection conn, int id) throws SQLException {
    String sql = "SELECT id FROM compagnie WHERE id = ?";
    try (PreparedStatement ps = conn.prepareStatement(sql)) {
      ps.setInt(1, id);
      return ps.executeQuery().next();
    }
  }
}