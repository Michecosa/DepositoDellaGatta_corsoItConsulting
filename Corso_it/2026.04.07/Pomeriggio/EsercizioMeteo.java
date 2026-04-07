import java.util.ArrayList;
import java.util.List;

// INTERFACCIA Display (Observer)
interface Display {
    void aggiorna(float temperatura);
}

// Il soggetto da osservare (Subject)
class StazioneMeteo {
    private List<Display> registro = new ArrayList<>();
    private float temperatura;

    // Aggiunge un osservatore alla lista
    public void aggiungiDisplay(Display d) {
        registro.add(d);
    }

    // Rimuove un osservatore dalla lista
    public void rimuoviDisplay(Display d) {
        registro.remove(d);
    }

    // Notifica il singolo display
    private void notificaDisplay() {
        for (Display d : registro) {
            d.aggiorna(temperatura);
        }
    }

    // Metodo per aggiornare la temperatura e far scattare la notifica
    public void setTemperatura(float t) {
        this.temperatura = t;
        System.out.println("\n[STAZIONE METEO] Nuova temperatura rilevata: " + t + " °C");
        notificaDisplay();
    }
}

// IMPLEMENTAZIONE: Display Console
class DisplayConsole implements Display {
    @Override
    public void aggiorna(float temperatura) {
        System.out.println("[CONSOLE] Visualizzazione: " + temperatura + " °C");
    }
}

// IMPLEMENTAZIONE: Display Mobile
class DisplayMobile implements Display {
    @Override
    public void aggiorna(float temperatura) {
        System.out.println("[MOBILE] Notifica: La temperatura è ora di " + temperatura + " °C");
    }
}

// CLASSE PRINCIPALE per testare il funzionamento
public class EsercizioMeteo {
    public static void main(String[] args) {
        StazioneMeteo stazione = new StazioneMeteo();

        // Creazione dei display
        Display console = new DisplayConsole();
        Display mobile = new DisplayMobile();

        // Registrazione dei display
        stazione.aggiungiDisplay(console);
        stazione.aggiungiDisplay(mobile);

        // Simulazione cambio temperatura
        stazione.setTemperatura(25.5f);
        stazione.setTemperatura(18.0f);

        // Rimozione di un display e nuovo test
        stazione.rimuoviDisplay(console);
        stazione.setTemperatura(22.3f);
    }
}