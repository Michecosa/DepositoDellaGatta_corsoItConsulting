package GestioneOrdini.observer;

import GestioneOrdini.context.Ordine;
import GestioneOrdini.decorator.AssicurazioneDecorator;
import GestioneOrdini.decorator.EcoPackDecorator;
import GestioneOrdini.strategy.StrategiaEvasione;
import GestioneOrdini.strategy.implementazioni.EvasioneControllata;
import GestioneOrdini.strategy.implementazioni.EvasioneNormale;
import GestioneOrdini.strategy.implementazioni.EvasionePrioritaria;

public class GestoreStatoOrdini implements Observer {
  private Ordine ordineDaGestire;

  public GestoreStatoOrdini(Ordine ordine) {
    this.ordineDaGestire = ordine;
  }

  @Override
  public void update(String nuovoStato) {
    StrategiaEvasione strategiaScelta;

    switch (nuovoStato.toUpperCase()) {
        case "PRIORITA" -> {
          // es. se è prioritaria, aggiungo l'imballaggio eco di default
          strategiaScelta = new EcoPackDecorator(new EvasionePrioritaria());
        }
        case "CONTROLLO" -> {
          // es. se è sotto controllo, aggiungo l'assicurazione obbligatoria
          strategiaScelta = new AssicurazioneDecorator(new EvasioneControllata());
        }
        default -> {
          strategiaScelta = new EvasioneNormale();
        }
    }
    
    ordineDaGestire.setStrategia(strategiaScelta);
    System.out.println("[OBSERVER]: Strategia (e possibili decoratori) aggiornati per: " + nuovoStato);
  }
}