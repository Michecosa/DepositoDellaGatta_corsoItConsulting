package ConfigurazioneAcquisto.decorator.concrete_decorators;

import ConfigurazioneAcquisto.decorator.abstract_decorator.ComponenteExtraDecorator;
import ConfigurazioneAcquisto.decorator.base.Computer;

public class RaffreddamentoExtra extends ComponenteExtraDecorator {
  public RaffreddamentoExtra(Computer c) {
    super(c);
  }

  public String getDescrizione() {
    return computerReferenziato.getDescrizione() + " +Raffreddamento Liquido";
  }

  public double getPrezzo() {
    return computerReferenziato.getPrezzo() + 120.00;
  }
}