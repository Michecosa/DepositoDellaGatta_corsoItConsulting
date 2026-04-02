package fabbrica.personale;
import fabbrica.produzione.Macchina;

public class Operaio {
    protected String nome;

    public Operaio(String nome) {
        this.nome = nome;
    }

    public void lavora(Macchina m) {
        System.out.println("Operaio " + nome + " sta accendendo la macchina...");
        m.accendi();
    }

    public void ferma(Macchina m) {
        System.out.println("Operaio " + nome + " sta spegnendo la macchina...");
        m.spegni();
    }
}