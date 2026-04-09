package ConfigurazioneAcquisto.strategy.implementazioni;

import ConfigurazioneAcquisto.strategy.PagamentoStrategy;

public class PagamentoBonifico implements PagamentoStrategy{
  public void eseguiPagamento(double importo) {
    System.out.println("Bonifico bancario ricevuto per l'importo di " + importo + " EUR");
  }
}
