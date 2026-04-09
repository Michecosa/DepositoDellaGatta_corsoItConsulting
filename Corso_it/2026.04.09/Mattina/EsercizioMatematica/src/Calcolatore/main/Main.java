package Calcolatore.main;

import Calcolatore.context.Calcolatore;
import Calcolatore.strategies.implementazioni.Addizione;
import Calcolatore.strategies.implementazioni.Moltiplicazione;

public class Main {
  public static void main(String[] args) {
    Calcolatore calcolatrice = new Calcolatore();
    int x = 3;
    int y = 5;

    calcolatrice.setOperazione(new Addizione());
    System.out.println("[SOMMA]");
    calcolatrice.eseguiOperazione(x, y);
    
    calcolatrice.setOperazione(new Moltiplicazione());
    System.out.println("[MOLTIPLICAZIONE]");
    calcolatrice.eseguiOperazione(x, y);
  }
}
