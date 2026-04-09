package GestioneAttacco.strategies.implementazioni;

import GestioneAttacco.strategies.TipoAttacco;

// Strategia 3: Attacco Magico
public class AttaccoMagico implements TipoAttacco {
  @Override
  public void eseguiAttacco() {
    System.out.println("Lancia una palla di fuoco esplosiva! -25 HP");
  }
}