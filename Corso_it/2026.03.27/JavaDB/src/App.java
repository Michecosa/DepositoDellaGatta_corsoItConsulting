import java.sql.*;

public class App {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/sakila";
        String user = "root";
        String password = "root";

        try {

            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connesso al database!");
            conn.close();
            System.out.println("Configurazione JDBC ok!");

        } catch (SQLException e) {

            e.printStackTrace();
            
        }
    }
}
