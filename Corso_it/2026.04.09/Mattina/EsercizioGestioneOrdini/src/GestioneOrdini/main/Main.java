package GestioneOrdini.main;

import GestioneOrdini.context.Ordine;
import GestioneOrdini.observer.GestoreStatoOrdini;
import GestioneOrdini.subject.CentroPriorita;

public class Main {
  public static void main(String[] args) {

    // SETUP: Creazione ordine, subject e registrazione observer
    Ordine mioOrdine = new Ordine("ORD-999", "Luigi Bianchi", "Smartphone X", 500.0);

    CentroPriorita centro = new CentroPriorita();
    GestoreStatoOrdini gestore = new GestoreStatoOrdini(mioOrdine);
    centro.aggiungi(gestore); // observer registrato nel subject

    // Stato NORMALE
    centro.setStato("NORMALE");
    mioOrdine.processaOrdine();

    // Stato PRIORITA
    centro.setStato("PRIORITA");
    mioOrdine.processaOrdine();

    // Stato CONTROLLO
    centro.setStato("CONTROLLO");
    mioOrdine.processaOrdine();
  }
}