package HomeTheater.devices;

public class Amplificatore {
  public void accendi() {
    System.out.println("Audio Surround attivo");
  }

  public void setVolume(int level) {
    System.out.println("Volume impostato a " + level);
  }
}