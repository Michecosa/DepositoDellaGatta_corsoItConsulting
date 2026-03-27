import java.sql.*;

public class JDBC_MetaData {
  public static void main(String[] args) {
    String url = "jdbc:mysql://localhost:3306/sakila";
    String user = "root";
    String pwd = "root";

    try (Connection conn = DriverManager.getConnection(url, user, pwd);
      Statement stmt = conn.createStatement();
      ResultSet rs  = stmt.executeQuery("SELECT * FROM film LIMIT 10")) {

        ResultSetMetaData meta = rs.getMetaData();
        int columnCount = meta.getColumnCount();
        while(rs.next()) {
          for(int i=1; i<=columnCount; i++) {
            String nomeColonna = meta.getColumnName(i);
            Object valore = rs.getObject(i);

            System.out.println(nomeColonna + ": "+valore);
            
          }
          System.out.print("\n\n\n");
        }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
