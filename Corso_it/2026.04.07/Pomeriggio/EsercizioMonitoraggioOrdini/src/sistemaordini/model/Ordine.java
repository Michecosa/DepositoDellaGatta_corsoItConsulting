package sistemaordini.model;

public class Ordine {

    private int id;
    private String cliente;
    private String prodotto;
    private int quantita;
    private StatoOrdine stato;

    // Costruttore per nuovo ordine
    public Ordine(String cliente, String prodotto, int quantita, String statoStringa) {
        this.cliente = cliente;
        this.prodotto = prodotto;
        this.quantita = quantita;
        this.stato = StatoOrdine.fromString("CREATO");
    }

    // Costruttore per ordine dal DB
    public Ordine(int id, String cliente, String prodotto, int quantita, StatoOrdine stato) {
        this.id = id;
        this.cliente = cliente;
        this.prodotto = prodotto;
        this.quantita = quantita;
        this.stato = stato;
    }

    // Getter e Setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getCliente() { return cliente; }
    public void setCliente(String cliente) { this.cliente = cliente; }

    public String getProdotto() { return prodotto; }
    public void setProdotto(String prodotto) { this.prodotto = prodotto; }

    public int getQuantita() { return quantita; }
    public void setQuantita(int quantita) { this.quantita = quantita; }

    public StatoOrdine getStato() { return stato; }
    public void setStato(StatoOrdine stato) { this.stato = stato; }

    // toString
    @Override
    public String toString() {
        return "ORDINE #" + id + "\n" +
               "Cliente: " + cliente + "\n" +
               "Prodotto: " + prodotto + "\n" +
               "Quantità: " + quantita + "\n" +
               "Stato: " + stato + "\n" +
               "--------------------------";
    }

    // toString semplice per le liste
    public String toStringBreve() {
        return String.format("ID: %d | %s | %s (x%d) | %s", id, cliente, prodotto, quantita, stato);
    }
}