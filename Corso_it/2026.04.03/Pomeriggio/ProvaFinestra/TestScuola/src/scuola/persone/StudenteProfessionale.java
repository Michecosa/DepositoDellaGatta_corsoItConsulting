package scuola.persone;

public class StudenteProfessionale extends Studente {
    private String specializzazione;
 
    public StudenteProfessionale(String nome, int eta, String classeFrequentata, String specializzazione) {
        super(nome, eta, classeFrequentata);
        this.specializzazione = specializzazione;
    }
 
    public String getSpecializzazione() { return specializzazione; }
 
    @Override
    public void descriviRuolo() {
        System.out.println("Sono uno studente dell'Istituto Professionale (" + specializzazione + "), classe " + getClasseFrequentata());
    }
}