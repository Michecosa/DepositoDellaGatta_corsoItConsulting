package BarApp.observer;

import BarApp.decorator.Bevanda;

public class LoggerOrdini implements OrdineObserver {
    @Override
    public void onOrdineConfermato(Bevanda bevanda) {
        System.out.println("[LOG] Nuovo ordine registrato: "
            + bevanda.getDescrizione()
            + " | Costo: " + String.format("%.2f", bevanda.getCosto()) + " EUR");
    }
}