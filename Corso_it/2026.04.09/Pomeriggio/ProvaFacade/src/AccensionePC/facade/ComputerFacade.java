package AccensionePC.facade;

import AccensionePC.classiSottosistema.*;
import AccensionePC.interfaccia.ComputerFacadeInterface;

public class ComputerFacade implements ComputerFacadeInterface {
  private CPU cpu;
  private HardDisk hd;
  private RAM ram;

  public ComputerFacade() {
    this.cpu = new CPU();
    this.hd = new HardDisk();
    this.ram = new RAM();
  }

  @Override
  public void avviaComputer() {
    System.out.println("--- Procedura di avvio iniziata ---");
    // Il Facade coordina le classi del sottosistema nell'ordine corretto
    cpu.avvia();
    hd.leggiDatiBoot();
    ram.caricaKernel();
    System.out.println("--- PC Pronto all'uso ---");
  }
}