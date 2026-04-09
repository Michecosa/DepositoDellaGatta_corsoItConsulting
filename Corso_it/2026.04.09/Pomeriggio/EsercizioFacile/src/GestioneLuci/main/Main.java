package GestioneLuci.main;

import GestioneLuci.facade.GestioneLuciFacade;

public class Main {
  public static void main(String[] args) {
    GestioneLuciFacade alexaHome = new GestioneLuciFacade();

    alexaHome.accendiTutte();
  }
}
