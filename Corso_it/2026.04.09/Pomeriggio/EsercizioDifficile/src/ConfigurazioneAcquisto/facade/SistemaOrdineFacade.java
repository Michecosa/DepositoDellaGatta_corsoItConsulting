package ConfigurazioneAcquisto.facade;

import ConfigurazioneAcquisto.decorator.base.*;
import ConfigurazioneAcquisto.decorator.concrete_components.*;
import ConfigurazioneAcquisto.decorator.concrete_decorators.*;
import ConfigurazioneAcquisto.strategy.*;

public class SistemaOrdineFacade {
  private Computer pcCorrente;
  private PagamentoStrategy modalitaPagamento;

  // Metodo per scegliere la base
  public void scegliBase(int scelta) {
    if (scelta == 1) pcCorrente = new PCUfficio();
    else pcCorrente = new PCGaming();
    System.out.println("Base selezionata: " + pcCorrente.getDescrizione());
  }

  // Metodo per aggiungere extra
  public void aggiungiComponente(int extra) {
    if (pcCorrente == null) {
      System.out.println("[ERRORE] Scegli prima un PC base");
      return;
    }
    if (extra == 1) pcCorrente = new RAMExtra(pcCorrente);
    else if (extra == 2) pcCorrente = new SSDExtra(pcCorrente);
    else if (extra == 3) pcCorrente = new SchedaVideoExtra(pcCorrente);
    else if (extra == 4) pcCorrente = new RaffreddamentoExtra(pcCorrente);
    System.out.println("Aggiornato: " + pcCorrente.getDescrizione());
  }

  // Metodo per la strategia
  public void impostaPagamento(PagamentoStrategy sp) {
    this.modalitaPagamento = sp;
  }

  // Metodo finale
  public void confermaOrdine() {
    if (pcCorrente == null || modalitaPagamento == null) {
      System.out.println("[ERRORE] Configurazione incompleta");
      return;
    }
    System.out.println("\n--- RIEPILOGO ORDINE ---");
    System.out.println("Configurazione: " + pcCorrente.getDescrizione());
    System.out.println("TOTALE: " + String.format("%.2f", pcCorrente.getPrezzo()) + " EUR");
    modalitaPagamento.eseguiPagamento(pcCorrente.getPrezzo());
    System.out.println("------------------------\n");
  }
}