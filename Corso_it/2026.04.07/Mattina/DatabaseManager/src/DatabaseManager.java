import java.sql.*;

public class DatabaseManager {

    // Parametri JDBC
    private static final String URL = "jdbc:mysql://localhost:3306/singleton_db";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    // Unica istanza statica
    private static DatabaseManager instance = null;

    private Connection connection;
    private int connectionCount = 0;

    // Costruttore privato
    private DatabaseManager() {}

    // getInstance() — restituisce sempre la stessa istanza
    public static DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
            System.out.println("[Singleton] Nuova istanza DatabaseManager creata");
        }
        return instance;
    }

    // Apre la connessione (o la riusa se già aperta)
    public void connect() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        connectionCount++;
        System.out.println("Connessione stabilita. Connessioni attive: " + connectionCount);
    }

    // Chiude la connessione
    public void disconnect() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
            System.out.println("\n[DB] Connessione chiusa");
        }
    }

    // Restituisce il numero totale di connessioni effettuate
    public int getConnectionCount() {
        return connectionCount;
    }

    // Crea le tabelle se non esistono
    public void initSchema() throws SQLException {
        String sqlUtenti =
            "CREATE TABLE IF NOT EXISTS utenti (" +
            "  id       INT          AUTO_INCREMENT PRIMARY KEY," +
            "  username VARCHAR(100) NOT NULL UNIQUE" +
            ")";
        String sqlDati =
            "CREATE TABLE IF NOT EXISTS dati (" +
            "  id        INT          AUTO_INCREMENT PRIMARY KEY," +
            "  utente_id INT          NOT NULL," +
            "  chiave    VARCHAR(200) NOT NULL," +
            "  valore    TEXT," +
            "  FOREIGN KEY (utente_id) REFERENCES utenti(id) ON DELETE CASCADE" +
            ")";
        Statement st = connection.createStatement();
        st.execute(sqlUtenti);
        st.execute(sqlDati);
        st.close();
        System.out.println("[DB] Schema inizializzato");
    }

    // Registra un utente oppure lo recupera se già esiste
    public int registraUtente(String username) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(
            "SELECT id FROM utenti WHERE username = ?");
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int id = rs.getInt("id");
            ps.close();
            return id;
        }
        ps.close();

        PreparedStatement ins = connection.prepareStatement(
            "INSERT INTO utenti (username) VALUES (?)",
            Statement.RETURN_GENERATED_KEYS);
        ins.setString(1, username);
        ins.executeUpdate();
        ResultSet keys = ins.getGeneratedKeys();
        if (keys.next()) {
            int id = keys.getInt(1);
            System.out.println("[DB] Utente '" + username + "' registrato con id=" + id);
            ins.close();
            return id;
        }
        ins.close();
        throw new SQLException("Impossibile registrare l'utente");
    }

    // Salva (o aggiorna) un dato per l'utente
    public void salvaDato(int utenteId, String chiave, String valore) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(
            "SELECT id FROM dati WHERE utente_id = ? AND chiave = ?");
        ps.setInt(1, utenteId);
        ps.setString(2, chiave);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int id = rs.getInt("id");
            ps.close();
            PreparedStatement upd = connection.prepareStatement(
                "UPDATE dati SET valore = ? WHERE id = ?");
            upd.setString(1, valore);
            upd.setInt(2, id);
            upd.executeUpdate();
            upd.close();
            System.out.println("[DB] Dato aggiornato → " + chiave + " = " + valore);
            return;
        }
        ps.close();

        PreparedStatement ins = connection.prepareStatement(
            "INSERT INTO dati (utente_id, chiave, valore) VALUES (?, ?, ?)");
        ins.setInt(1, utenteId);
        ins.setString(2, chiave);
        ins.setString(3, valore);
        ins.executeUpdate();
        ins.close();
        System.out.println("[DB] Dato salvato → " + chiave + " = " + valore);
    }

    // Recupera un dato per chiave
    public String richiamaDato(int utenteId, String chiave) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(
            "SELECT valore FROM dati WHERE utente_id = ? AND chiave = ?");
        ps.setInt(1, utenteId);
        ps.setString(2, chiave);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            String valore = rs.getString("valore");
            ps.close();
            return valore;
        }
        ps.close();
        return null;
    }

    // Elenca tutte le coppie chiave/valore dell'utente
    public void elencaDati(int utenteId) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(
            "SELECT chiave, valore FROM dati WHERE utente_id = ?");
        ps.setInt(1, utenteId);
        ResultSet rs = ps.executeQuery();
        boolean trovato = false;
        while (rs.next()) {
            System.out.printf("   %-25s -> %s%n", rs.getString("chiave"), rs.getString("valore"));
            trovato = true;
        }
        if (!trovato) System.out.println("   (nessun dato salvato)");
        ps.close();
    }
}