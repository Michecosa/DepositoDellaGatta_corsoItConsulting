package convertitore.main;

import convertitore.facade.VideoConverterFacade;
import convertitore.facade.VideoConverterInterface;

public class Main {
  public static void main(String[] args) {
    // Il client comunica con il sottosistema solo tramite il Facade
    VideoConverterInterface convertitore = new VideoConverterFacade();
    
    // Operazione semplice che nasconde la complessità
    convertitore.converti("vacanze.mp4", "AVI");
  }
}