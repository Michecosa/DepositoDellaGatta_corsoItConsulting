package GestioneLuci.facade;

import GestioneLuci.sottoclassi.LuceCamera;
import GestioneLuci.sottoclassi.LuceCucina;

public class GestioneLuciFacade {
  private LuceCamera lcam;
  private LuceCucina lcuc;

  public GestioneLuciFacade() {
    this.lcam = new LuceCamera();
    this.lcuc = new LuceCucina();
  }

  public void accendiTutte() {
    System.out.println("--- Welcome Home ---");
    lcam.accendi();
    lcuc.accendi();
    System.out.println("\nAll lights are on");
  }
}
