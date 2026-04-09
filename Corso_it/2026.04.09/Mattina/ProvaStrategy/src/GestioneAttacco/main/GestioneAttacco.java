package GestioneAttacco.main;

import GestioneAttacco.context.Personaggio;
import GestioneAttacco.strategies.implementazioni.AttaccoDistanza;
import GestioneAttacco.strategies.implementazioni.AttaccoMagico;
import GestioneAttacco.strategies.implementazioni.AttaccoMischia;

public class GestioneAttacco {
  public static void main(String[] args) {
    Personaggio eroe = new Personaggio();

    // L'eroe trova una spada
    eroe.setArma(new AttaccoMischia());
    eroe.attacca();

    // L'eroe trova un arco e decide di cambiare approccio
    eroe.setArma(new AttaccoDistanza());
    eroe.attacca();
    
    // Bonus: L'eroe impara una magia
    eroe.setArma(new AttaccoMagico());
    eroe.attacca();
  }
}