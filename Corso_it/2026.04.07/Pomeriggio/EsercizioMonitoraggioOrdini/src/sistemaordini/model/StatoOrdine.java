package sistemaordini.model;

public enum StatoOrdine {
    CREATO("Creato"),
    IN_PREPARAZIONE("IN PREPARAZIONE"),
    SPEDITO("Spedito"),
    CONSEGNATO("Consegnato");

    private final String descrizione;

    StatoOrdine(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getDescrizione() {
        return descrizione;
    }

    /**
     * Converte una stringa (dal DB) nel corrispondente enum
     */
    public static StatoOrdine fromString(String valore) {
        for (StatoOrdine s : values()) {
            if (s.name().equalsIgnoreCase(valore) || s.descrizione.equalsIgnoreCase(valore)) {
                return s;
            }
        }
        throw new IllegalArgumentException("[ERRORE] Stato sconosciuto: " + valore);
    }

    @Override
    public String toString() {
        return descrizione;
    }
}