package sistemaordini.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    // Campi statici per l'istanza e la connessione
    private static DBConnection instance;
    private Connection connection;

    // Costruttore privato
    private DBConnection() {
        String url = "jdbc:mysql://localhost:3306/magazzino";
        String user = "root";
        String pass = "root";

        try {
            this.connection = DriverManager.getConnection(url, user, pass);
            System.out.println("[DB] Connessione aperta con successo");
        } catch (SQLException e) {
            System.out.println("[DB] [ERRORE]: " + e.getMessage());
        }
    }

    public static DBConnection getInstance() {
        // Se l'istanza non esiste, la crea. Altrimenti restituisce quella vecchia
        if (instance == null) instance = new DBConnection();
        return instance;
    }

    // Getter per l'oggetto Connection
    public Connection getConnection() {
        return connection;
    }
}