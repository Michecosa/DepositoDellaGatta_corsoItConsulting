package veicoli.fabbrica;

import veicoli.prodotto.Barca;
import veicoli.prodotto.Veicolo;

// CONCRETE CREATOR B
public class FabbricaBarche extends FabbricaVeicoli{
  @Override
  public Veicolo creaVeicolo() {return new Barca();}
}
