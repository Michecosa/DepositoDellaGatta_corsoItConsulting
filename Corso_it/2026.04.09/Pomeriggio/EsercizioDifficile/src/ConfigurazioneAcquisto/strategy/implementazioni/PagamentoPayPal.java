package ConfigurazioneAcquisto.strategy.implementazioni;

import ConfigurazioneAcquisto.strategy.PagamentoStrategy;

public class PagamentoPayPal implements PagamentoStrategy{
  public void eseguiPagamento(double importo) {
    System.out.println("Pagamento di " + importo + " EUR effettuato tramite PayPal");
  }
}
