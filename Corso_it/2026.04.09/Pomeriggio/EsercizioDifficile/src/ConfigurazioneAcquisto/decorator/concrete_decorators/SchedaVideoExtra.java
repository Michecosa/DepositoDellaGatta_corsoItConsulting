package ConfigurazioneAcquisto.decorator.concrete_decorators;

import ConfigurazioneAcquisto.decorator.abstract_decorator.ComponenteExtraDecorator;
import ConfigurazioneAcquisto.decorator.base.Computer;

public class SchedaVideoExtra extends ComponenteExtraDecorator{
  public SchedaVideoExtra(Computer c) {
    super(c);
  }

  public String getDescrizione() {
    return computerReferenziato.getDescrizione()+" +RTX 4070";
  }

  public double getPrezzo() {
    return computerReferenziato.getPrezzo()+600.00;
  }
}
