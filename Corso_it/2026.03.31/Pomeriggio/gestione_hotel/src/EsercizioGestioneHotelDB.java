import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

// Classe base Camera
class Camera {
    private int numero;
    private float prezzo;

    public Camera(int numero, float prezzo) {
        this.numero = numero;
        this.prezzo = prezzo;
    }

    public int getNumero() { return numero; }
    public void setNumero(int numero) { this.numero = numero; }

    public float getPrezzo() { return prezzo; }
    public void setPrezzo(float prezzo) { this.prezzo = prezzo; }

    public void dettagli() {
        System.out.println("  Camera n." + numero + " - Prezzo: " + prezzo + " euro/notte");
    }

    public void dettagli(boolean conPrezzo) {
        if (conPrezzo)
            System.out.println("  Camera n." + numero + " - Prezzo: " + prezzo + " euro/notte");
        else
            System.out.println("  Camera n." + numero);
    }
}

// Sottoclasse Suite
class Suite extends Camera {
    private String serviziExtra;

    public Suite(int numero, float prezzo, String serviziExtra) {
        super(numero, prezzo);
        this.serviziExtra = serviziExtra;
    }

    public String getServiziExtra() { return serviziExtra; }
    public void setServiziExtra(String s) { this.serviziExtra = s; }

    @Override
    public void dettagli() {
        System.out.println("  Suite n." + getNumero() + " - Prezzo: " + getPrezzo() + " euro/notte - Servizi extra: " + serviziExtra);
    }

    @Override
    public void dettagli(boolean conPrezzo) {
        if (conPrezzo)
            System.out.println("  Suite n." + getNumero() + " - Prezzo: " + getPrezzo() + " euro/notte - Servizi extra: " + serviziExtra);
        else
            System.out.println("  Suite n." + getNumero() + " - Servizi extra: " + serviziExtra);
    }
}

// Classe Hotel
class Hotel {
    private String nome;
    private ArrayList<Camera> camere;

    public Hotel(String nome) {
        this.nome = nome;
        this.camere = new ArrayList<>();
    }

    public String getNome() { return nome; }

    public void aggiungiCamera(Camera camera) { camere.add(camera); }

    public static int contaSuite(ArrayList<Camera> lista) {
        int count = 0;
        for (Camera c : lista)
            if (c instanceof Suite) count++;
        return count;
    }

    public ArrayList<Camera> getCamere() { return camere; }
}

// Main con menu
public class EsercizioGestioneHotelDB {
    static final String URL  = "jdbc:mysql://localhost:3306/gestione_hotel";
    static final String USER = "root";
    static final String PASS = "root";

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {

            boolean esci = false;

            while (!esci) {
                System.out.println("\n\n--- GESTIONE HOTEL MENU ---");
                System.out.println(" 1. Aggiungi hotel");
                System.out.println(" 2. Aggiungi camera normale");
                System.out.println(" 3. Aggiungi suite");
                System.out.println(" 4. Visualizza hotel presenti");
                System.out.println(" 5. Visualizza camere per hotel");
                System.out.println(" 6. Visualizza suite (quante e quali)");
                System.out.println(" 0. Esci");
                System.out.print("Scelta: ");
                int scelta = leggiInt();

                switch (scelta) {
                    case 1 -> aggiungiHotel(conn);
                    case 2 -> aggiungiCamera(conn, "camera");
                    case 3 -> aggiungiCamera(conn, "suite");
                    case 4 -> visualizzaHotel(conn);
                    case 5 -> visualizzaCamerePerHotel(conn);
                    case 6 -> visualizzaSuite(conn);
                    case 0 -> esci = true;
                    default -> System.out.println("Opzione non valida");
                }
            }

            System.out.println("\nArrivederci!");

        } catch (SQLException e) {
            System.err.println("Errore DB: " + e.getMessage());
        }
    }

    // 1. Aggiungi hotel
    static void aggiungiHotel(Connection conn) throws SQLException {
        System.out.print("Nome hotel: ");
        String nome = sc.nextLine().trim();

        String sql = "INSERT INTO hotel (nome) VALUES (?)";
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, nome);
        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next())
            System.out.println("Hotel aggiunto con ID " + rs.getInt(1));
    }

    // 2/3. Aggiungi camera o suite
    static void aggiungiCamera(Connection conn, String tipo) throws SQLException {
        // Prima mostra gli hotel disponibili
        visualizzaHotel(conn);

        System.out.print("ID hotel: ");
        int idHotel = leggiInt();

        if (!hotelEsiste(conn, idHotel)) {
            System.out.println("Hotel non trovato");
            return;
        }

        System.out.print("Numero camera: ");
        int numero = leggiInt();

        System.out.print("Prezzo/notte: ");
        float prezzo = leggiFloat();

        String serviziExtra = null;
        if (tipo.equals("suite")) {
            System.out.print("Servizi extra: ");
            serviziExtra = sc.nextLine().trim();
        }

        String sql = "INSERT INTO camere (numero, prezzo, tipo, servizi_extra, id_hotel) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, numero);
        ps.setFloat(2, prezzo);
        ps.setString(3, tipo);
        if (serviziExtra == null)
            ps.setNull(4, Types.VARCHAR); // metto NULL sulla colonna 
        else
            ps.setString(4, serviziExtra);
        
        ps.setInt(5, idHotel);
        ps.executeUpdate();

        System.out.println((tipo.equals("suite") ? "Suite" : "Camera") + " aggiunta con successo");
    }

    // 4. Visualizza hotel
    static void visualizzaHotel(Connection conn) throws SQLException {
        ResultSet rs = conn.createStatement().executeQuery("SELECT id, nome FROM hotel ORDER BY id");

        System.out.println("\n--- Hotel presenti ---");
        boolean trovati = false;
        while (rs.next()) {
            trovati = true;
            System.out.println("  [" + rs.getInt("id") + "] " + rs.getString("nome"));
        }
        if (!trovati) System.out.println("  Nessun hotel registrato");
    }

    // 5. Visualizza camere per hotel
    static void visualizzaCamerePerHotel(Connection conn) throws SQLException {
        visualizzaHotel(conn);

        System.out.print("ID hotel da visualizzare: ");
        int idHotel = leggiInt();

        // Recupera nome hotel
        PreparedStatement psNome = conn.prepareStatement("SELECT nome FROM hotel WHERE id = ?");
        psNome.setInt(1, idHotel);
        ResultSet rsNome = psNome.executeQuery();

        if (!rsNome.next()) {
            System.out.println("Hotel non trovato");
            return;
        }
        Hotel hotel = new Hotel(rsNome.getString("nome"));

        // Recupera camere
        PreparedStatement psCamere = conn.prepareStatement(
            "SELECT numero, prezzo, tipo, servizi_extra FROM camere WHERE id_hotel = ? ORDER BY numero"
        );
        psCamere.setInt(1, idHotel);
        ResultSet rsCamere = psCamere.executeQuery();

        while (rsCamere.next()) {
            int numero   = rsCamere.getInt("numero");
            float prezzo = rsCamere.getFloat("prezzo");
            String tipo  = rsCamere.getString("tipo");
            String extra = rsCamere.getString("servizi_extra");

            if (tipo.equals("suite"))
                hotel.aggiungiCamera(new Suite(numero, prezzo, extra));
            else
                hotel.aggiungiCamera(new Camera(numero, prezzo));
        }

        System.out.println("\n--- Camere di: " + hotel.getNome() + " ---");
        if (hotel.getCamere().isEmpty()) {
            System.out.println("  Nessuna camera registrata.");
        } else {
            for (Camera c : hotel.getCamere()) c.dettagli();
        }
    }

    // 6. Visualizza suite
    static void visualizzaSuite(Connection conn) throws SQLException {
        String sql = """
            SELECT c.numero, c.prezzo, c.servizi_extra, h.nome AS hotel_nome
            FROM camere c
            JOIN hotel h ON c.id_hotel = h.id
            WHERE c.tipo = 'suite'
            ORDER BY h.id, c.numero
            """;

        ResultSet rs = conn.createStatement().executeQuery(sql);

        System.out.println("\n--- Suite presenti ---");
        int count = 0;
        while (rs.next()) {
            count++;
            Suite s = new Suite(
                rs.getInt("numero"),
                rs.getFloat("prezzo"),
                rs.getString("servizi_extra")
            );
            System.out.print("  [" + rs.getString("hotel_nome") + "] ");
            s.dettagli();
        }

        if (count == 0)
            System.out.println("  Nessuna suite registrata.");
        else
            System.out.println("Totale suite: " + count);
    }

    // Utilità
    static boolean hotelEsiste(Connection conn, int id) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT id FROM hotel WHERE id = ?");
        ps.setInt(1, id);
        return ps.executeQuery().next();
    }

    static int leggiInt() {
        while (true) {
            try {
                int val = Integer.parseInt(sc.nextLine().trim());
                return val;
            } catch (NumberFormatException e) {
                System.out.print("Inserisci un numero intero valido: ");
            }
        }
    }

    static float leggiFloat() {
        while (true) {
            try {
                float val = Float.parseFloat(sc.nextLine().trim().replace(",", "."));
                return val;
            } catch (NumberFormatException e) {
                System.out.print("Inserisci un numero valido: ");
            }
        }
    }
}