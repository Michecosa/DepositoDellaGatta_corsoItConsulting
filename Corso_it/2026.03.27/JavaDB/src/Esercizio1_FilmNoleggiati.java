/**
 * Recuperare i 10 film più noleggiati dalla tabella rental
*/

import java.sql.*;

public class Esercizio1_FilmNoleggiati {
  public static void main(String[] args) {
    String url = "jdbc:mysql://localhost:3306/sakila";
    String user = "root";
    String pwd = "root";

    try (Connection conn = DriverManager.getConnection(url, user, pwd);
          Statement stmt = conn.createStatement();
          ResultSet rs = stmt.executeQuery(
            "SELECT film.title AS 'Film', COUNT(film.title) AS 'Conteggio Noleggiamento' "+
            "FROM rental "+
            "  JOIN inventory ON rental.inventory_id = inventory.inventory_id "+
            "  JOIN film ON inventory.film_id = film.film_id "+
            "GROUP BY film.title "+
            "ORDER BY COUNT(film.title) DESC "+
            "LIMIT 10"
          )) {

      while (rs.next()) {

        System.out.println("("+rs.getInt("Conteggio Noleggiamento")+") "+rs.getString("Film"));
      }

      System.out.println();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
