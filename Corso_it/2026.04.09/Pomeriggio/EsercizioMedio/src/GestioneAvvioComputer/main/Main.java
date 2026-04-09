package GestioneAvvioComputer.main;

import GestioneAvvioComputer.facade.ComputerFacade;

public class Main {
  public static void main(String[] args) {
    ComputerFacade cf = new ComputerFacade();
  
    System.out.println("[COMPUTER] OFF -> ON\n");
    cf.accendiComputer();
    System.out.println("[COMPUTER] ACCESO\n");
  }
}
