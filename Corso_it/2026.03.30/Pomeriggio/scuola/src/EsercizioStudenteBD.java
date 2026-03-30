import java.sql.*;
import java.util.Scanner;

public class EsercizioStudenteBD {
  static final String url = "jdbc:mysql://localhost:3306/scuola";
  static final String user = "root";
  static final String pwd = "root"; 

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    boolean continua = true;

    try (Connection conn = DriverManager.getConnection(url, user, pwd)) {
      while (continua) {
        System.out.println("\n--- GESTIONE STUDENTI (DB) ---");
        System.out.println("1. Visualizza elenco");
        System.out.println("2. Modifica voto");
        System.out.println("0. Esci");
        System.out.print("Scelta: ");
        int scelta = input.nextInt();
        input.nextLine(); 

        switch (scelta) {
          case 1:
            visualizzaElenco(conn);
            break;
          
          case 2:
            System.out.print("Inserisci l'ID dello studente: ");
            int id = input.nextInt();
            System.out.print("Inserisci il nuovo voto (0-10): ");
            int nuovoVoto = input.nextInt();
            modificaVoto(conn, id, nuovoVoto);
            break;
          
          case 0:
            continua = false;
            break;
          
          default:
            System.out.println("Scelta non valida");
        }
      }
    } catch (SQLException e) {
      System.out.println("Errore di connessione: " + e.getMessage());
    }
    input.close();
  }

  // Metodo per visualizzare i dati (SELECT)
  private static void visualizzaElenco(Connection conn) throws SQLException {
    String query = "SELECT * FROM studenti";
    Statement stmt = conn.createStatement();
    ResultSet rs = stmt.executeQuery(query);

    System.out.println("\nID | Nome | Voto");
    while (rs.next()) {
      System.out.println(rs.getInt("id") + " | " + rs.getString("nome") + " | " + rs.getInt("voto"));
    }
  }

  // Metodo per modificare i dati (UPDATE)
  private static void modificaVoto(Connection conn, int id, int voto) throws SQLException {
    if (voto < 0 || voto > 10) {
      System.out.println("Errore: voto non valido");
      return;
    }
    // Uso ? come segnaposto per la sicurezza
    String sql = "UPDATE studenti SET voto = ? WHERE id = ?";
    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
      pstmt.setInt(1, voto); // Imposta il primo ?
      pstmt.setInt(2, id);   // Imposta il secondo ?
      
      int righeColpite = pstmt.executeUpdate();
      if (righeColpite > 0) {
        System.out.println("Voto aggiornato sul database!");
      } else {
        System.out.println("Studente non trovato");
      }
    }
  }
}