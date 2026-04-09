package ConfigurazioneAcquisto.decorator.concrete_decorators;

import ConfigurazioneAcquisto.decorator.abstract_decorator.ComponenteExtraDecorator;
import ConfigurazioneAcquisto.decorator.base.Computer;

public class RAMExtra extends ComponenteExtraDecorator{
  public RAMExtra(Computer c) {
    super(c);
  }

  public String getDescrizione() {
    return computerReferenziato.getDescrizione()+" +16GB RAM Extra";
  }

  public double getPrezzo() {
    return computerReferenziato.getPrezzo()+80.00;
  }
}
