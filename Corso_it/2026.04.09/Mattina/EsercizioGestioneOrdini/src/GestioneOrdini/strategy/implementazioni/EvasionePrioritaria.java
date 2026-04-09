package GestioneOrdini.strategy.implementazioni;

import GestioneOrdini.strategy.StrategiaEvasione;

public class EvasionePrioritaria implements StrategiaEvasione{
  @Override
  public void eseguiEvasione(double prezzoBase) {
    System.out.println("ORDINE EVASO CON CORSIA PRIORITARIA\nPrezzo finale: "+(prezzoBase*1.15)+" EUR");
  }
}
