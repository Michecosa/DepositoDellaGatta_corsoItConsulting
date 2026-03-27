import java.sql.*;

public class JDBC_Statement {
  public static void main(String[] args) {
    String url = "jdbc:mysql://localhost:3306/sakila";
    String user = "root";
    String pwd = "root";

    try (Connection conn = DriverManager.getConnection(url, user, pwd)) {
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT title, release_year, length FROM film LIMIT 10");
      
      while(rs.next()) {
        System.out.println(rs.getString("title")+" ("+rs.getInt("release_year")+") - "+rs.getInt("length")+"min");
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
