package GestioneAttacco.context;

import GestioneAttacco.strategies.TipoAttacco;

public class Personaggio {
  private TipoAttacco armaEquipaggiata;

  // Metodo per cambiare arma (strategia) a runtime
  public void setArma(TipoAttacco nuovaArma) {
    this.armaEquipaggiata = nuovaArma;
    System.out.println("Hai cambiato equipaggiamento!");
  }

  public void attacca() {
    if (armaEquipaggiata != null) {
      armaEquipaggiata.eseguiAttacco();
    } else {
      System.out.println("Il personaggio è disarmato!");
    }
  }
}