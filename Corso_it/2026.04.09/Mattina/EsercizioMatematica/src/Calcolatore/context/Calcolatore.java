package Calcolatore.context;

import Calcolatore.strategies.Operazione;

public class Calcolatore {
  private Operazione operazioneSelezionata;

  // Metodo per testare il cambio di strategia
  public void setOperazione(Operazione o) {
    this.operazioneSelezionata = o;
    System.out.print("\n\nOPERAZIONE SELEZIONATA -> ");
  }

  public void eseguiOperazione(int a, int b) {
    if(operazioneSelezionata != null) {
      int risultato = operazioneSelezionata.esegui(a, b);

      System.out.printf("Dati gli interi [%d] e [%d]\nil risultato dell'operazione è: %d", a, b, risultato);
    } else {
      System.out.println("Seleziona un'operazione da eseguire");
    }
  }
}
