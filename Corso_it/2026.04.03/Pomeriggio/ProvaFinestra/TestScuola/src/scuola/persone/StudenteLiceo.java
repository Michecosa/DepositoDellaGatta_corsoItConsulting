package scuola.persone;

// ============================================================
// FIGLI DI Studente
// ============================================================
public class StudenteLiceo extends Studente {
    private String indirizzo;
 
    public StudenteLiceo(String nome, int eta, String classeFrequentata, String indirizzo) {
        super(nome, eta, classeFrequentata);
        this.indirizzo = indirizzo;
    }
 
    public String getIndirizzo() { return indirizzo; }
 
    @Override
    public void descriviRuolo() {
        System.out.println("Sono uno studente del Liceo " + indirizzo + ", classe " + getClasseFrequentata());
    }
}