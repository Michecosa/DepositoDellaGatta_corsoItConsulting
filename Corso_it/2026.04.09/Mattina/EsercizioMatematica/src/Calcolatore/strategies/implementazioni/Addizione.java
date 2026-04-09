package Calcolatore.strategies.implementazioni;

import Calcolatore.strategies.Operazione;

// Strategia 1: Addizione tra due interi
public class Addizione implements Operazione {
  @Override
  public int esegui(int a, int b) {
    return a+b;
  }
}
