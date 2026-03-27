import java.sql.*;

public class App {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/sakila";
        String user = "root";
        String password = "root";

        try {

            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connesso al database!\n");
            // conn.close();
            // System.out.println("Configurazione JDBC ok!");

            // 1. Creo lo Statement
            Statement stmt = conn.createStatement();

            // 2. Eseguo la query
            String sql = "SELECT actor_id, first_name, last_name FROM actor LIMIT 10";
            ResultSet rs = stmt.executeQuery(sql);


            // 3. Itero sui risultati
            while (rs.next()) {
                int id = rs.getInt("actor_id");
                String nome = rs.getString("first_name");
                String cognome = rs.getString("last_name");

                System.out.println(id + ": " + nome + " " + cognome);
            }

            conn.close();

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }
}
