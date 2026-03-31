import java.util.ArrayList;

// CLASSE PADRE
class Vehicle {
  private String brand;
  private int year;

  public Vehicle(String brand, int year) {
    this.brand = brand;
    this.year = year;
  }

  public void honk() {
    System.out.println("Il veicolo " + brand + " emette un suono: miao");
  }

  public String getBrand() {
    return brand;
  }

  public int getYear() {
    return year;
  }
}

// CLASSE FIGLIA: CAR
class Car extends Vehicle {
  private String modelName;

  public Car(String brand, int year, String modelName) {
    super(brand, year); // Richiamo obbligatorio al costruttore padre
    this.modelName = modelName;
  }

  public String getModelName() {
    return modelName;
  }
}

// CLASSE FIGLIA: MOTORCYCLE (per mostrare il polimorfismo)
class Motorcycle extends Vehicle {
  private int cc; // Cilindrata

  public Motorcycle(String brand, int year, int cc) {
    super(brand, year);
    this.cc = cc;
  }

  public int getCc() {
    return cc;
  }
}

// CLASSE PRINCIPALE
public class ProvaEreditarieta {
  public static void main(String[] args) {

    // ----------------------------------------------------
    // CREAZIONE OGGETTI DI CLASSI FIGLIE
    // ----------------------------------------------------
    Car myCar = new Car("Ford", 2022, "Mustang");
    Motorcycle myMoto = new Motorcycle("Ducati", 2023, 1100);


    // ----------------------------------------------------
    // USO METODI EREDITATI
    // ----------------------------------------------------
    myCar.honk();
    myMoto.honk();


    // ----------------------------------------------------
    // METODI SPECIFICI DELLE CLASSI FIGLIE
    // ----------------------------------------------------
    System.out.println("Modello auto: " + myCar.getModelName());
    System.out.println("Cilindrata moto: " + myMoto.getCc());


    // ----------------------------------------------------
    // ARRAYLIST DI TIPO GENERICO (POLIMORFISMO)
    // ----------------------------------------------------
    ArrayList<Vehicle> flotta = new ArrayList<>();

    flotta.add(myCar);  // Car è un Vehicle
    flotta.add(myMoto); // Motorcycle è un Vehicle

    System.out.println("\n--- Elenco Flotta ---");
    // Ciclo for per stampa generale (Polimorfismo)
    for (int i = 0; i < flotta.size(); i++) {
      // Anche se sono oggetti diversi, posso usare i metodi del padre
      System.out.print("Marca: " + flotta.get(i).getBrand() + " -> ");
      flotta.get(i).honk(); 
    }
  }
}