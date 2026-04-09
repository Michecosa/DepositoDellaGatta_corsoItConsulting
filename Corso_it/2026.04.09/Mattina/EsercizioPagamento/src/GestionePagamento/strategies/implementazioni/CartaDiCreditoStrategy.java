package GestionePagamento.strategies.implementazioni;

import GestionePagamento.strategies.PagamentoStrategy;

// Strategia 1: Pagamento con Carta di Credito
public class CartaDiCreditoStrategy implements PagamentoStrategy {
  private String numeroCarta;

  public CartaDiCreditoStrategy (String numero) {
    this.numeroCarta = numero;
  }

  @Override
  public void paga(int importo) {
    System.out.println(importo+" EUR pagati con Carta di Credito ("+numeroCarta+")");
  }
}
