import java.util.ArrayList;
import java.util.List;

// INTERFACCIA Investitore (Observer)
interface Investitore {
    void notifica(String azione, double valore);
}

// Il Soggetto (Subject)
class AgenziaBorsa {
    private List<Investitore> investitori = new ArrayList<>();

    // Aggiungo un investitore alla lista
    public void aggiungiInvestitore(Investitore i) {
        investitori.add(i);
    }

    // Rimuovo un investitore dalla lista
    public void rimuoviInvestitore(Investitore i) {
        investitori.remove(i);
    }

    // Metodo per richiamare per ciascun investitore il sistema di notifica
    public void notificaInvestitori(String azione, double valore) {
        for (Investitore i : investitori) {
            i.notifica(azione, valore);
        }
    }

    // Metodo che scatena l'evento di notifica per ogni investitore
    public void aggiornaValoreAzione(String nome, double valore) {
        System.out.println("\n[BORSA] Azione " + nome + " variata a: " + valore + " EUR\n");
        notificaInvestitori(nome, valore);
    }
}

// IMPLEMENTAZIONE: Investitore Privato
class InvestitorePrivato implements Investitore {
    private String nome;

    public InvestitorePrivato(String nome) {
        this.nome = nome;
    }

    @Override
    public void notifica(String azione, double valore) {
        System.out.println("   >> [PRIVATO " + nome + "] Analizzo variazione " + azione + ": " + valore + "EUR");
    }
}

// IMPLEMENTAZIONE: Investitore Bancario
class InvestitoreBancario implements Investitore {
    private String nomeBanca;

    public InvestitoreBancario(String nomeBanca) {
        this.nomeBanca = nomeBanca;
    }

    @Override
    public void notifica(String azione, double valore) {
        System.out.println("   >> [BANCA " + nomeBanca + "] Aggiornamento sistemi per " + azione + ". Prezzo: " + valore+" EUR");
    }
}

public class EsercizioBorsa {
    public static void main(String[] args) {
        AgenziaBorsa wallStreet = new AgenziaBorsa();

        Investitore p1 = new InvestitorePrivato("Luca");
        Investitore b1 = new InvestitoreBancario("Unicredit");

        wallStreet.aggiungiInvestitore(p1);
        wallStreet.aggiungiInvestitore(b1);

        // Il metodo chiama internamente notificaInvestitori
        wallStreet.aggiornaValoreAzione("Amazon", 130.80);

        System.err.println("\n\n");
    }
}