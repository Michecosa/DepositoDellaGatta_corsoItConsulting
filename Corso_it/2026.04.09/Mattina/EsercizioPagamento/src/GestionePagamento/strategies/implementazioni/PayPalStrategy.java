package GestionePagamento.strategies.implementazioni;

import GestionePagamento.strategies.PagamentoStrategy;

// Strategia 2: Pagamento con PayPal
public class PayPalStrategy implements PagamentoStrategy{
  private String email;

  public PayPalStrategy(String email) {
    this.email = email;
  }

  @Override
  public void paga(int importo) {
    System.out.println(importo+" EUR pagati con account PayPal: "+email);
  }
}
