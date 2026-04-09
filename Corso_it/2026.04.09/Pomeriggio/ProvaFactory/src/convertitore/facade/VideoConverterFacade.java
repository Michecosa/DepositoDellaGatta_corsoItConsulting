package convertitore.facade;

import convertitore.sottosistema.BitrateCompressor;
import convertitore.sottosistema.CodecFactory;
import convertitore.sottosistema.LettoreFile;

public class VideoConverterFacade implements VideoConverterInterface {
  // Il Facade mantiene i riferimenti alle classi del sottosistema
  private LettoreFile lettore = new LettoreFile();
  private CodecFactory codec = new CodecFactory();
  private BitrateCompressor compressore = new BitrateCompressor();

  @Override
  public void converti(String nomeFile, String formatoDestinazione) {
    System.out.println("\n--- Avvio conversione in " + formatoDestinazione + " ---");
    // Delega le richieste agli oggetti appropriati
    lettore.carica(nomeFile);
    codec.estraiCodec(nomeFile);
    compressore.comprimi();
    System.out.println("--- Conversione completata con successo! ---\n");
  }
}
