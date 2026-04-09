package GestionePagamento.context;

import GestionePagamento.strategies.PagamentoStrategy;

public class Carrello {
  private PagamentoStrategy strategia;

  // Permette di cambiare la strategia a runtime
  public void setStrategiaPagamento(PagamentoStrategy strategia) {
    this.strategia = strategia;
  }

  public void eseguiPagamento(int importo) {
    strategia.paga(importo);
  }
}
