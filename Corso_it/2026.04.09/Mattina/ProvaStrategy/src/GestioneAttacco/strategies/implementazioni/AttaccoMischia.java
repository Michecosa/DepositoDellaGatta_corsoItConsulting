package GestioneAttacco.strategies.implementazioni;

import GestioneAttacco.strategies.TipoAttacco;

// Strategia 1: Attacco con Spada
public class AttaccoMischia implements TipoAttacco {
  @Override
  public void eseguiAttacco() {
    System.out.println("Sferra un fendente ravvicinato con la spada! -15 HP");
  }
}