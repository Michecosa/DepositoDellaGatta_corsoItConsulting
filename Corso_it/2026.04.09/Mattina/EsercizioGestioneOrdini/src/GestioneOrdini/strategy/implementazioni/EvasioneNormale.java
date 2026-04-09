package GestioneOrdini.strategy.implementazioni;

import GestioneOrdini.strategy.StrategiaEvasione;

public class EvasioneNormale implements StrategiaEvasione {
  @Override
  public void eseguiEvasione(double prezzoBase) {
    System.out.println("ORDINE EVASO CON PROGEDURA STANDARD\nPrezzo finale: "+(prezzoBase*1.05)+" EUR");
  }
}
