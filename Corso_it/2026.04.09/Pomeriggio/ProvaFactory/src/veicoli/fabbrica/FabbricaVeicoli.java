package veicoli.fabbrica;

import veicoli.prodotto.Veicolo;

// CREATOR: La classe astratta con il Factory Method
public abstract class FabbricaVeicoli {
  // Questo è il Factory Method
  public abstract Veicolo creaVeicolo();

  // Logica che usa il prodotto senza sapere cos'è esattamente
  public void consegnaVeicolo() {
    Veicolo v = creaVeicolo();
    v.muoviti();
  }
}
