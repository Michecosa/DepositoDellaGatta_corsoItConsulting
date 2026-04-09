package ConfigurazioneAcquisto.decorator.concrete_components;

import ConfigurazioneAcquisto.decorator.base.Computer;

public class PCGaming implements Computer {
  public String getDescrizione() {
    return "PC Base Gaming (i7, 16GB RAM)";
  }

  public double getPrezzo() {
    return 950.00;
  }
}
