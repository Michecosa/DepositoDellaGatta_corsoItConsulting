/**
 * Restituire l'elenco di tutti i film 
 * che contengono una stringa inserita dall'utente
 */

import java.sql.*;
import java.util.Scanner;

public class Esercizio2_CercaTitolo {
  public static void main(String[] args) {
    String url = "jdbc:mysql://localhost:3306/sakila";
    String user = "root";
    String pwd = "root";

    Scanner input = new Scanner(System.in);
    System.out.print("Quale film stai cercando? ");
    String search = input.nextLine();

    String query = "SELECT \n" + //
            "\tfilm.title AS 'Titolo', \n" + //
            "  film.description AS 'Descrizione', \n" + //
            "  film.release_year 'Anno di Rilascio' \n" + //
            "FROM film\n" + //
            "WHERE film.title LIKE ?";

    try (Connection conn = DriverManager.getConnection(url, user, pwd);
          PreparedStatement pstmt = conn.prepareStatement(query)) {
      
      pstmt.setString(1, "%"+search+"%");
      ResultSet rs = pstmt.executeQuery();
    
      // ResultSetMetaData meta = rs.getMetaData();
      // int nColumn = meta.getColumnCount();

      while (rs.next()){
        String film = rs.getString("Titolo");
        String descrizione = rs.getString("Descrizione");
        int anno = rs.getInt("Anno di Rilascio");

        System.out.println(film+" ("+anno+")\n"+descrizione+"\n");
        /*         
          for (int i=1; i<=nColumn; i++) {
          String column = meta.getColumnName(i);
          Object value = rs.getObject(i);

          System.out.println(column+": "+value);
        }
        System.out.println(); 
        */
      }
    } catch (SQLException e) {

      e.printStackTrace();
    }

    input.close();

  }
}
