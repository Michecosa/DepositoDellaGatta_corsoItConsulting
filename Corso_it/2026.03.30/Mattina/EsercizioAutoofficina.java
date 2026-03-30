import java.util.ArrayList;

class Auto {
  String targa;
  String modello;

  Auto(String targa, String modello) {
    this.targa = targa;
    this.modello = modello;
  }
}

class Officina {
  ArrayList<Auto> listaAuto = new ArrayList<Auto>();

  void aggiungiAuto(Auto nuovaAuto) {
    listaAuto.add(nuovaAuto);
    System.out.println("Aggiunta auto: "+nuovaAuto.modello);
  }

  void stampaElenco() {
    System.out.println("\n--- AUTO IN OFFICINA ---");
    for (Auto a : listaAuto) {
      System.out.println(a.modello+" - Targa: "+a.targa);
    }
  }

}

public class EsercizioAutoofficina {
  public static void main(String[] args) {
    Officina miaOfficina = new Officina();

    Auto auto1 = new Auto("AA111BB", "Fiat Panda");
    Auto auto2 = new Auto("BB222CC", "Porsche 718 Cayman");

    miaOfficina.aggiungiAuto(auto1);
    miaOfficina.aggiungiAuto(auto2);

    miaOfficina.stampaElenco();
  }
}
