package GestioneOrdini.decorator;

import GestioneOrdini.strategy.StrategiaEvasione;

public class AssicurazioneDecorator extends EvasioneDecorator {
  public AssicurazioneDecorator(StrategiaEvasione strategia) {
    super(strategia);
  }

  @Override
  public void eseguiEvasione(double prezzoBase) {
    strategiaDecorata.eseguiEvasione(prezzoBase);
    System.out.println("\t+[DECORATOR] Aggiunta Assicurazione: +20.00 EUR");
  }
}
