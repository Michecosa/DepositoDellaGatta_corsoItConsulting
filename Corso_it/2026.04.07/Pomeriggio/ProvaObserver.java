import java.util.ArrayList;
import java.util.List;

// 1. L'INTERFACCIA PER CHI RICEVE (L'Iscritto)
interface Iscritto {
    void riceviNotifica(String titoloVideo);
}

// 2. IL SOGGETTO DA OSSERVARE (Il Canale YouTube)
class CanaleYouTube {
    private List<Iscritto> fan = new ArrayList<>();

    // Metodo per iscriversi
    public void aggiungiFan(Iscritto i) {
        fan.add(i);
    }

    // Quando esce un video, avvisa tutti!
    public void pubblicaVideo(String titolo) {
        System.out.println("Canale: Pubblicazione di '" + titolo + "'...\n");
        for (Iscritto f : fan) {
            f.riceviNotifica(titolo);
        }
    }
}

// 3. LA CLASSE CONCRETA (L'Utente reale)
class Utente implements Iscritto {
    private String nome;

    public Utente(String nome) {
        this.nome = nome;
    }

    @Override
    public void riceviNotifica(String titoloVideo) {
        System.out.println("  -> " + nome + " sta guardando: " + titoloVideo);
    }
}

// 4. IL TEST
public class ProvaObserver {
    public static void main(String[] args) {
        CanaleYouTube ilMioCanale = new CanaleYouTube();

        // Creiamo gli utenti
        Utente marco = new Utente("Marco");
        Utente sofia = new Utente("Sofia");

        // Si iscrivono al canale
        ilMioCanale.aggiungiFan(marco);
        ilMioCanale.aggiungiFan(sofia);

        // Il canale pubblica un video: entrambi ricevono la notifica
        ilMioCanale.pubblicaVideo("Tutorial Java in 5 minuti");
    }
}