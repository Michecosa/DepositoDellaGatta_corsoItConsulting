package fabbrica.personale;
import fabbrica.produzione.Macchina;

public class OperaioDirigente extends Operaio {
    public OperaioDirigente(String nome) {
        super(nome);
    }

    public void controllaMacchina(Macchina m) {
        System.out.print("Il Dirigente " + nome + " effettua un controllo: ");
        m.stampaStato();
    }
}