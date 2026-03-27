import java.sql.*;

public class JDBC_Connection {
    public static void main(String[] args) {
    String url = "jdbc:mysql://localhost:3306/sakila";
    String user = "root";
    String pwd = "root";

    try {
      Connection conn = DriverManager.getConnection(url, user, pwd);
      System.out.println("Tutto ok");

      conn.close();

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
