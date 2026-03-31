import java.util.ArrayList;

// Classe base Camera
class Camera {
  private int numero;
  private float prezzo;

  public Camera(int numero, float prezzo) {
    this.numero = numero;
    this.prezzo = prezzo;
  }

  // Getter e Setter
  public int getNumero() { return numero; }
  public void setNumero(int numero) { this.numero = numero; }

  public float getPrezzo() { return prezzo; }
  public void setPrezzo(float prezzo) { this.prezzo = prezzo; }

  // Overload dettagli() senza parametri: stampa tutto
  public void dettagli() {
    System.out.println("Camera n." + numero + " - Prezzo: " + prezzo + " euro/notte");
  }

  // Overload dettagli(boolean conPrezzo)
  public void dettagli(boolean conPrezzo) {
    if (conPrezzo) {
      System.out.println("Camera n." + numero + " - Prezzo: " + prezzo + " euro/notte");
    } else {
      System.out.println("Camera n." + numero);
    }
  }
}

// Sottoclasse Suite
class Suite extends Camera {
  private String serviziExtra;

  public Suite(int numero, float prezzo, String serviziExtra) {
    super(numero, prezzo);
    this.serviziExtra = serviziExtra;
  }

  public String getServiziExtra() { return serviziExtra; }
  public void setServiziExtra(String serviziExtra) { this.serviziExtra = serviziExtra; }

  // Override dettagli() senza parametri
  @Override
  public void dettagli() {
    System.out.println("Suite n." + getNumero() + " - Prezzo: " + getPrezzo() + " euro/notte - Servizi extra: " + serviziExtra);
  }

  // Override dettagli(boolean conPrezzo)
  @Override
  public void dettagli(boolean conPrezzo) {
    if (conPrezzo) {
      System.out.println("Suite n." + getNumero() + " - Prezzo: " + getPrezzo() + " euro/notte - Servizi extra: " + serviziExtra);
    } else {
      System.out.println("Suite n." + getNumero() + " - Servizi extra: " + serviziExtra);
    }
  }
}

// Classe Hotel
class Hotel {
  private String nome;
  private ArrayList<Camera> camere;

  public Hotel(String nome) {
    this.nome = nome;
    this.camere = new ArrayList<>();
  }

  public String getNome() { return nome; }

  public void aggiungiCamera(Camera camera) {
    camere.add(camera);
  }

  // Metodo statico: conta quante Suite ci sono nella lista
  public static int contaSuite(ArrayList<Camera> lista) {
    int count = 0;
    for (Camera c : lista) {
      if (c instanceof Suite) count++;
    }
    return count;
  }

  public ArrayList<Camera> getCamere() { return camere; }
}

// Main
public class EsercizioGestioneHotel {
  public static void main(String[] args) {
    Hotel hotel = new Hotel("Hotel Bellavista");
    System.out.println("=== " + hotel.getNome() + " ===\n");

    // 2 camere normali
    Camera c1 = new Camera(101, 80.0f);
    Camera c2 = new Camera(102, 95.0f);

    // 2 suite
    Suite s1 = new Suite(201, 150.0f, "Jacuzzi, minibar");
    Suite s2 = new Suite(202, 200.0f, "Vista mare, colazione inclusa");

    hotel.aggiungiCamera(c1);
    hotel.aggiungiCamera(c2);
    hotel.aggiungiCamera(s1);
    hotel.aggiungiCamera(s2);

    // dettagli() senza parametri
    System.out.println("-- dettagli() senza parametri --");
    for (Camera c : hotel.getCamere()) {
      c.dettagli();
    }

    System.out.println();

    // dettagli(boolean) con true
    System.out.println("-- dettagli(true) --");
    for (Camera c : hotel.getCamere()) {
      c.dettagli(true);
    }

    System.out.println();

    // dettagli(boolean) con false
    System.out.println("-- dettagli(false) --");
    for (Camera c : hotel.getCamere()) {
      c.dettagli(false);
    }

    System.out.println();

    // Metodo statico contaSuite
    int numSuite = Hotel.contaSuite(hotel.getCamere());
    System.out.println("Numero di suite presenti: " + numSuite);
  }
}