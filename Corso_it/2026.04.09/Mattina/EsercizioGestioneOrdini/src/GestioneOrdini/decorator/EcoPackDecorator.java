package GestioneOrdini.decorator;

import GestioneOrdini.strategy.StrategiaEvasione;

public class EcoPackDecorator extends EvasioneDecorator{
  public EcoPackDecorator(StrategiaEvasione strategia) {
    super(strategia);
  }

  @Override
  public void eseguiEvasione(double prezzoBase) {
    strategiaDecorata.eseguiEvasione(prezzoBase);
    System.out.println("\t+[DECORATOR] Aggiunto imballaggio Eco-Friendly: +5.00 EUR");
  }
}
