package sistemaordini.database;

import sistemaordini.model.Ordine;
import sistemaordini.model.StatoOrdine;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


//Data Access Object – gestisce tutte le operazioni CRUD sulla tabella Ordini
//Usa il Singleton DBConnection per ottenere la connessione al database

public class OrdineDAO {

    // Costanti SQL 

    private static final String SQL_INSERT = "INSERT INTO Ordini (cliente, prodotto, quantita, stato) VALUES (?, ?, ?, ?)";
    private static final String SQL_UPDATE_STATO = "UPDATE Ordini SET stato = ?  WHERE id = ?";
    private static final String SQL_SELECT_ALL = "SELECT id, cliente, prodotto, quantita, stato FROM Ordini ORDER BY id";
    private static final String SQL_SELECT_BY_ID = "SELECT id, cliente, prodotto, quantita, stato FROM Ordini WHERE id = ?";

    
    // Riferimento alla connessione (Singleton) 

    private Connection getConn() {
        return DBConnection.getInstance().getConnection();
    }


    // ** OPERAZIONI CRUD ** 


    // Inserisce un nuovo ordine nel database e aggiorna l'ID dell'oggetto
    public Ordine inserisci(Ordine ordine) {
        try (PreparedStatement ps = getConn().prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, ordine.getCliente());
            ps.setString(2, ordine.getProdotto());
            ps.setInt(3, ordine.getQuantita());
            ps.setString(4, ordine.getStato().name());

            int righe = ps.executeUpdate();
            if (righe == 0) {
                throw new SQLException("[DB] [ERRORE]: Inserimento fallito: nessuna riga creata");
            }

            try (ResultSet chiavi = ps.getGeneratedKeys()) {
                if (chiavi.next()) {
                    ordine.setId(chiavi.getInt(1));
                }
            }
            return ordine; // ritorna l'ordine con l'ID assegnato dal db

        } catch (SQLException e) {
            throw new RuntimeException("[DB] [ERRORE] "+ e.getMessage(), e);
        }
    }


    // Aggiorna lo stato di un ordine nel database.
    public boolean aggiornaStato(int idOrdine, StatoOrdine nuovoStato) {
        try (PreparedStatement ps = getConn().prepareStatement(SQL_UPDATE_STATO)) {

            ps.setString(1, nuovoStato.name());
            ps.setInt(2, idOrdine);

            return ps.executeUpdate() > 0; // true se l'aggiornamento ha avuto successo

        } catch (SQLException e) {
            throw new RuntimeException("[DB] [ERRORE] Aggiornamento dello stato: "+ e.getMessage(), e);
        }
    }


    // Recupera tutti gli ordini presenti nel database
    public List<Ordine> trovaTutti() {
        List<Ordine> lista = new ArrayList<>();

        try (PreparedStatement ps = getConn().prepareStatement(SQL_SELECT_ALL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mappaRiga(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("[ERRORE] durante la lettura degli ordini: "
                    + e.getMessage(), e);
        }
        return lista; // ritorna la lista (anche vuota) di tutti gli ordini
    }

    // Cerca un ordine per ID
    public Optional<Ordine> trovaPerID(int id) {
        try (PreparedStatement ps = getConn().prepareStatement(SQL_SELECT_BY_ID)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mappaRiga(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("[ERRORE] durante la ricerca dell'ordine: " + e.getMessage(), e);
        }
        return Optional.empty(); // Optional contenente l'ordine, o vuoto se non esiste
    }

    // Metodo di mapping ResultSet -> Ordine 
    private Ordine mappaRiga(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String cliente = rs.getString("cliente");
        String prodotto = rs.getString("prodotto");
        int quantita = rs.getInt("quantita");
        StatoOrdine stato = StatoOrdine.fromString(rs.getString("stato"));

        return new Ordine(id, cliente, prodotto, quantita, stato);
    }
}