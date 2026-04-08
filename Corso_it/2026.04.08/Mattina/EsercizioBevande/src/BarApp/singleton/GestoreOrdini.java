package BarApp.singleton;

import BarApp.decorator.Bevanda;
import BarApp.observer.OrdineObserver;

import java.util.List;
import java.util.ArrayList;

public class GestoreOrdini {
    private static GestoreOrdini istanza;
    private List<String> storicoOrdini = new ArrayList<>();
    private List<OrdineObserver> observers = new ArrayList<>();  // <-- lista observer

    private double totaleIncassato = 0.0; // <-- variabile per l'incasso complessivo

    private GestoreOrdini() {}

    public static GestoreOrdini getInstance() {
        if (istanza == null) istanza = new GestoreOrdini();
        return istanza;
    }

    // --- Gestione observer ---
    public void aggiungiObserver(OrdineObserver o) {
        observers.add(o);
    }

    public void rimuoviObserver(OrdineObserver o) {
        observers.remove(o);
    }

    private void notificaObservers(Bevanda bevanda) {
        for (OrdineObserver o : observers) {
            o.onOrdineConfermato(bevanda);
        }
    }

    public void confermaOrdine(Bevanda b) {
        totaleIncassato += b.getCosto(); // <-- il totale aumenta ad ogni ordine confermato

        String riepilogo = b.getDescrizione() + "\n- Totale: "
            + String.format("%.2f", b.getCosto()) + " EUR";
        storicoOrdini.add(riepilogo);
        System.out.println("\n[OK] Ordine confermato e salvato nello storico");
        notificaObservers(b);  // <-- notifica dopo conferma
    }

    public void visualizzaStorico() {
        System.out.println("\n\n----------------------------------------");
        System.out.println("------------ STORICO ORDINI ------------");
        System.out.println("----------------------------------------");
        
        if (storicoOrdini.isEmpty()) {
            System.out.println("Nessun ordine effettuato");
        } else {
            for (String s : storicoOrdini) {
                System.out.println("\n- " + s);
            }

            // Resoconto corrente
            System.out.println("\n\n----------------------------------------");
            System.out.printf("INCASSO TOTALE: %.2f EUR%n", totaleIncassato);

        }

        System.out.println("----------------------------------------");
    }
}