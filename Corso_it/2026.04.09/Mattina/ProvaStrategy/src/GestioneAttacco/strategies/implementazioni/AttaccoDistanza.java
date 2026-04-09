package GestioneAttacco.strategies.implementazioni;

import GestioneAttacco.strategies.TipoAttacco;

// Strategia 2: Attacco con Arco
public class AttaccoDistanza implements TipoAttacco {
  @Override
  public void eseguiAttacco() {
    System.out.println("Scaglia una freccia precisa dalla distanza! -10 HP");
  }
}