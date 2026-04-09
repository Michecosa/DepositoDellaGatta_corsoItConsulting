package GestioneOrdini.strategy.implementazioni;

import GestioneOrdini.strategy.StrategiaEvasione;

public class EvasioneControllata implements StrategiaEvasione {
  @Override
  public void eseguiEvasione(double prezzoBase) {
    System.out.println("ORDINE EVASO CON VERIFICA AGGIUNTIVA\nPrezzo finale: " + (prezzoBase * 0.95) + " EUR");
  }
}
