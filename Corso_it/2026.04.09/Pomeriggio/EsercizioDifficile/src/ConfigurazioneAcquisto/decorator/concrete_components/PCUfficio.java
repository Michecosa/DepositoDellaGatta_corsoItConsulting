package ConfigurazioneAcquisto.decorator.concrete_components;

import ConfigurazioneAcquisto.decorator.base.Computer;

public class PCUfficio implements Computer {
  public String getDescrizione() {
    return "PC Base Ufficio (i3, 8GB RAM)";
  }

  public double getPrezzo() {
    return 450.00;
  }
}
