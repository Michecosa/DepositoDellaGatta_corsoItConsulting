package GestioneOrdini.subject;

import java.util.ArrayList;
import java.util.List;

import GestioneOrdini.observer.Observer;

// SUBJECT
public class CentroPriorita {
  private List<Observer> observers = new ArrayList<>();
  private String statoCorrente;

  public void aggiungi(Observer o) {
    observers.add(o);
  }

  public void setStato(String nuovoStato) {
    this.statoCorrente = nuovoStato;
    System.out.println("\n[SISTEMA] Stato cambiato in: "+nuovoStato);
    notifica();
  }

  private void notifica() {
    for (Observer o : observers) {
      o.update(statoCorrente);
    }
  }
}
