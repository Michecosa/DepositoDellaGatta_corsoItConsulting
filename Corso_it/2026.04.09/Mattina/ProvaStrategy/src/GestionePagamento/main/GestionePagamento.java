package GestionePagamento.main;

import GestionePagamento.context.Carrello;
import GestionePagamento.strategies.implementazioni.CartaDiCreditoStrategy;
import GestionePagamento.strategies.implementazioni.PayPalStrategy;

public class GestionePagamento {
  public static void main(String[] args) {
    Carrello carrello = new Carrello();

    // L'utente sceglie PayPal
    carrello.setStrategiaPagamento(new PayPalStrategy("utente@esempio.it"));
    carrello.eseguiPagamento(100);

    // L'utente cambia idea e decide di usare la Carta di Credito
    carrello.setStrategiaPagamento(new CartaDiCreditoStrategy("1234-5678-9012"));
    carrello.eseguiPagamento(50);
  }
}