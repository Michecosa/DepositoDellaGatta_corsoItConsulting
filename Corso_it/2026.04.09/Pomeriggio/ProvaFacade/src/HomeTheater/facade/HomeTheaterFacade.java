package HomeTheater.facade;

import HomeTheater.devices.*;

public class HomeTheaterFacade {
  // Il Facade mantiene i riferimenti agli oggetti del sottosistema
  private Televisore tv;
  private Amplificatore audio;
  private LettoreBluRay player;

  public HomeTheaterFacade() {
    this.tv = new Televisore();
    this.audio = new Amplificatore();
    this.player = new LettoreBluRay();
  }

  // Metodo semplificato esposto al client
  public void guardaFilm() {
    System.out.println("\n--- Preparazione sistema in corso ---");
    tv.accendi();
    tv.setInputHDMI();
    audio.accendi();
    audio.setVolume(20);
    player.riproduci();
    System.out.println("--- Buona visione! ---\n");
  }
}