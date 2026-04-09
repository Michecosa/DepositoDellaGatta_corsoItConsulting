package GestioneAvvioComputer.facade;

import GestioneAvvioComputer.sottoclassi.Bios;
import GestioneAvvioComputer.sottoclassi.HardDisk;
import GestioneAvvioComputer.sottoclassi.SistemaOperativo;

public class ComputerFacade {
  private Bios b;
  private HardDisk hd;
  private SistemaOperativo so;

  public ComputerFacade() {
    this.b = new Bios();
    this.hd = new HardDisk();
    this.so = new SistemaOperativo();
  }

  public void accendiComputer() {
    // BIOS primo ad essere avviato
    b.inizializza();
    b.carica();
    b.avvia();
    
    // Hard Disk per secondo
    hd.inizializza();
    hd.carica();
    hd.avvia();
    
    // Sistema Operativo ultimo elemento avviato
    so.inizializza();
    so.carica();
    so.avvia();
  }
}
