package scuola.persone;
import scuola.base.*;
import java.util.ArrayList;

// ============================================================
// CLASSE Docente (extends Persona, implements Registrabile)
// ============================================================

public class Docente extends Persona implements Registrabile {
    private String materia;
    private ArrayList<Studente> studentiMateria;
 
    public Docente(String nome, int eta, String materia) {
        super(nome, eta);
        this.materia = materia;
        this.studentiMateria = new ArrayList<Studente>();
    }
 
    public String getMateria() { return materia; }
    public void setMateria(String materia) { this.materia = materia; }
    public ArrayList<Studente> getStudentiMateria() { return studentiMateria; }
 
    public void aggiungiStudente(Studente s) {
        studentiMateria.add(s);
    }
 
    public void assegnaVoto(Studente s, int voto) {
        if (studentiMateria.contains(s)) {
            s.aggiungiVoto(voto);
            System.out.println("Voto " + voto + " assegnato a " + s.getNome() + " dal docente " + getNome());
        } else {
            System.out.println("[ERRORE] " + s.getNome() + " non e' nella lista del docente " + getNome());
        }
    }
 
    @Override
    public void descriviRuolo() {
        System.out.println("Sono un docente di " + materia);
    }
 
    @Override
    public void registrazione() {
        System.out.println(getNome() + " - Registrazione tramite segreteria didattica");
    }
}