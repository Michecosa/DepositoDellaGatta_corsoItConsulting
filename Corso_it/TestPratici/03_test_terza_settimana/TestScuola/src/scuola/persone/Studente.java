package scuola.persone;
import scuola.base.*;
import java.util.ArrayList;

// ============================================================
// CLASSE Studente (extends Persona, implements Registrabile)
// ============================================================
public class Studente extends Persona implements Registrabile {
    private String classeFrequentata;
    private ArrayList<Integer> voti;
 
    public Studente(String nome, int eta, String classeFrequentata) {
        super(nome, eta);
        this.classeFrequentata = classeFrequentata;
        this.voti = new ArrayList<Integer>();
    }
 
    public String getClasseFrequentata() { return classeFrequentata; }
    public void setClasseFrequentata(String c) { this.classeFrequentata = c; }
    public ArrayList<Integer> getVoti() { return voti; }
 
    public void aggiungiVoto(int voto) {
        voti.add(voto);
    }
 
    public void stampaVoti() {
        if (voti.isEmpty()) {
            System.out.println(getNome() + " non ha ancora ricevuto voti");
        } else {
            System.out.println("Voti di " + getNome() + ": " + voti);
            int somma = 0;
            for (int v : voti) somma += v;
            double media = (double) somma / voti.size();
            System.out.printf("Media: %.2f%n", media);
        }
    }
 
    @Override
    public void descriviRuolo() {
        System.out.println("Sono uno studente della classe " + classeFrequentata);
    }
 
    @Override
    public void registrazione() {
        System.out.println(getNome() + " - Registrazione tramite modulo online");
    }
}