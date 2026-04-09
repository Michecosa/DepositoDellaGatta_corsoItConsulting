package GestioneOrdini.decorator;

import GestioneOrdini.strategy.StrategiaEvasione;

public abstract class EvasioneDecorator implements StrategiaEvasione{
  protected StrategiaEvasione strategiaDecorata;

  public EvasioneDecorator(StrategiaEvasione s) {
    this.strategiaDecorata = s;
  }

  @Override
  public void eseguiEvasione(double prezzoBase) {
    strategiaDecorata.eseguiEvasione(prezzoBase);
  }
}
