package GestioneOrdini.context;

import GestioneOrdini.strategy.StrategiaEvasione;
import GestioneOrdini.strategy.implementazioni.EvasioneControllata;

public class Ordine {
  private String id, cliente, prodotto;
  private double prezzoBase;
  private StrategiaEvasione strategiaCorrente;

  public Ordine(String id, String cliente, String prodotto, double prezzoBase) {
    this.id=id;
    this.cliente=cliente;
    this.prodotto=prodotto;
    this.prezzoBase=prezzoBase;
    
    this.strategiaCorrente = new EvasioneControllata();
  }

  public void setStrategia(StrategiaEvasione nuovaStrategia) {
    this.strategiaCorrente = nuovaStrategia;
  }

  public void processaOrdine() {
    System.out.println("\n--- Processo l'ordine " + id + " ---");
    System.out.println("Cliente: " + cliente + " | Prodotto: " + prodotto);
    strategiaCorrente.eseguiEvasione(prezzoBase);
  }
}
