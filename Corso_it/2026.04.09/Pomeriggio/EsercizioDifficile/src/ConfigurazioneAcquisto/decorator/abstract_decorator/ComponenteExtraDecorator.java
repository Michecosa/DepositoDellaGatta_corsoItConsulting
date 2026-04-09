package ConfigurazioneAcquisto.decorator.abstract_decorator;

import ConfigurazioneAcquisto.decorator.base.Computer;

public abstract class ComponenteExtraDecorator implements Computer {
  protected Computer computerReferenziato;

  public ComponenteExtraDecorator(Computer c) {
    this.computerReferenziato = c;
  }
}
