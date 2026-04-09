package ConfigurazioneAcquisto.strategy.implementazioni;

import ConfigurazioneAcquisto.strategy.PagamentoStrategy;

public class PagamentoCarta implements PagamentoStrategy{
  public void eseguiPagamento(double importo) {
    System.out.println("Pagamento di " + importo + " EUR effettuato con Carta di Credito");
  }
}
