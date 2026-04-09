package veicoli.main;

import veicoli.fabbrica.*;

public class Main {
  public static void main(String[] args) {
    // Il client decide quale "produttore" usare, ma riceve un "Veicolo" generico
    FabbricaVeicoli miaFabbrica = new FabbricaAuto();
    miaFabbrica.consegnaVeicolo(); // Produce e muove un'auto

    miaFabbrica = new FabbricaBarche();
    miaFabbrica.consegnaVeicolo(); // Produce e muove una barca
  }
}