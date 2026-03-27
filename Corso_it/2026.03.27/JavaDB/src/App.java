import java.sql.*;

public class App {
    public static void main(String[] args) throws Exception {
        String host = "localhost";
        String port = "3306";
        String url = "jdbc:mysql://" + host + ":" + port + "/sakila";
        String user = "root";
        String psw = "root";

        String query = "SELECT * FROM actor LIMIT ?";
        int nRow = 5;

        try (Connection conn = DriverManager.getConnection(url, user, psw);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, nRow);
            ResultSet result = pstmt.executeQuery();

            ResultSetMetaData meta = result.getMetaData();
            int numColumns = meta.getColumnCount();

            // 1. STAMPA INTESTAZIONE
            System.out.println("-".repeat(numColumns * 25)); // Linea decorativa
            for (int i = 1; i <= numColumns; i++) {
                // %-20s significa: stringa, allineata a sinistra, larga 20 caratteri
                System.out.printf("%-20s | ", meta.getColumnName(i).toUpperCase());
            }
            System.out.println("\n" + "-".repeat(numColumns * 25));

            // 2. STAMPA DATI
            while (result.next()) {
                for (int i = 1; i <= numColumns; i++) {
                    Object val = result.getObject(i);
                    // Gestisco il null graficamente e usco lo stesso spazio dell'header
                    String printVal = (val != null) ? val.toString() : "NULL";
                    System.out.printf("%-20s | ", printVal);
                }
                System.out.println();
            }
            System.out.println("-".repeat(numColumns * 25));

        } catch (SQLException e) {
            System.err.println("Errore di connessione: " + e.getMessage());
        }
    }
}