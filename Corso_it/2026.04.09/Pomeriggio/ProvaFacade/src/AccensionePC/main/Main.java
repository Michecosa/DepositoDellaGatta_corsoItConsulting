package AccensionePC.main;

import AccensionePC.facade.ComputerFacade;
import AccensionePC.interfaccia.ComputerFacadeInterface;

public class Main {
  public static void main(String[] args) {
    // Il client interagisce solo con l'interfaccia del Facade
    ComputerFacadeInterface pc = new ComputerFacade();
    
    // Un'operazione di alto livello nasconde tutta la complessità interna
    pc.avviaComputer();
  }
}