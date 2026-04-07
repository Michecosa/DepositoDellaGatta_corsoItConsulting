package scuola.persone;

// ==================
// FIGLI DI Docente
// ==================
public class DocenteOrdinario extends Docente {
    private int anniEsperienza;
 
    public DocenteOrdinario(String nome, int eta, String materia, int anniEsperienza) {
        super(nome, eta, materia);
        this.anniEsperienza = anniEsperienza;
    }
 
    public int getAnniEsperienza() { return anniEsperienza; }
 
    @Override
    public void descriviRuolo() {
        System.out.println("Sono un docente ordinario di " + getMateria() + " con " + anniEsperienza + " anni di esperienza");
    }
}