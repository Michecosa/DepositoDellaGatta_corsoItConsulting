import java.sql.*;

public class EsercizioStudenteBD {
  public static void main(String[] args) {
    // Dati per la connessione
    String url = "jdbc:mysql://localhost:3306/scuola";
    String user = "root";
    String password = "root";

    // Il blocco try-with-resources chiude automaticamente la connessione alla fine
    try (Connection conn = DriverManager.getConnection(url, user, password)) {
      System.out.println("Connessione stabilita con successo!");

      // Istruzione SQL
      String query = "SELECT id, nome, voto FROM studenti";
      Statement stmt = conn.createStatement();

      // Eseguo la query e ottengo i risultati
      ResultSet rs = stmt.executeQuery(query);

      // Itero sui risultati finché ce ne sono
      System.out.println("\n--- ELENCO STUDENTI DAL DATABASE ---");
      while (rs.next()) {
        int id = rs.getInt("id");
        String nome = rs.getString("nome");
        int voto = rs.getInt("voto");

        System.out.println("ID " + id + ": " + nome + " | Voto: " + voto);
      }

    } catch (SQLException e) {
      System.out.println("Errore di connessione: " + e.getMessage());
    }
  }
}