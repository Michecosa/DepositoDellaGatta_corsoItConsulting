package ConfigurazioneAcquisto.decorator.concrete_decorators;

import ConfigurazioneAcquisto.decorator.abstract_decorator.ComponenteExtraDecorator;
import ConfigurazioneAcquisto.decorator.base.Computer;

public class SSDExtra extends ComponenteExtraDecorator{
  public SSDExtra(Computer c) {
    super(c);
  }

  public String getDescrizione() {
    return computerReferenziato.getDescrizione()+" +SSD 1TB";
  }

  public double getPrezzo() {
    return computerReferenziato.getPrezzo()+60.00;
  }
}
