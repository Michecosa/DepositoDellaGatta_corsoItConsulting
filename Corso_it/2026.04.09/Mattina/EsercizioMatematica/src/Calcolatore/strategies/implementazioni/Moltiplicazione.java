package Calcolatore.strategies.implementazioni;

import Calcolatore.strategies.Operazione;

// Strategia 2: Moltiplicazione tra due interi
public class Moltiplicazione implements Operazione{
  @Override
  public int esegui(int a, int b) {
    return a*b;
  }
}
