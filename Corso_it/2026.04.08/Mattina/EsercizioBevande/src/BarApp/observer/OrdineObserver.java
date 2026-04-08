package BarApp.observer;

import BarApp.decorator.Bevanda;

public interface OrdineObserver {
    void onOrdineConfermato(Bevanda bevanda);
}