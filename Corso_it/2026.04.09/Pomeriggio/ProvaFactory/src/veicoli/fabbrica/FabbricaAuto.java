package veicoli.fabbrica;

import veicoli.prodotto.Auto;
import veicoli.prodotto.Veicolo;

// CONCRETE CREATOR A
public class FabbricaAuto extends FabbricaVeicoli{
  @Override
  public Veicolo creaVeicolo() {return new Auto();}
}
