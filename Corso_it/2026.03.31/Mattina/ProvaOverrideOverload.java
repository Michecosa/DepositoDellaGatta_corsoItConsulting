// 1. Classe Genitore
class Veicolo {
    // Metodo base (quello che verrà sovrascritto)
    void run() {
        System.out.println("Il veicolo è in marcia");
    }

    // --- ESEMPIO DI OVERLOAD---
    // Stesso nome "run", ma parametri diversi (accetta la velocità)
    void run(int velocita) {
        System.out.println("Il veicolo corre a " + velocita + " km/h");
    }
}

// 2. Classe Figlia senza Override
class Bike extends Veicolo {
    // Eredita sia run() che run(int velocita)
}

// 3. Classe Figlia con Override e Overload
class Bike2 extends Veicolo {
    
    // OVERRIDE: Cambio il comportamento del metodo senza parametri
    @Override 
    void run() {
        System.out.println("La bici sta correndo in sicurezza");
    }

    // --- ULTERIORE OVERLOAD ---
    // Questo metodo esiste solo in Bike2 e gestisce due parametri
    void run(int velocita, String marcia) {
        System.out.println("La bici corre a " + velocita + " km/h in " + marcia);
    }
}

// CLASSE PRINCIPALE PER IL TEST
public class ProvaOverrideOverload {
    public static void main(String[] args) {
        
        System.out.println("--- Test Overload (nella classe Bike) ---");
        Bike obj1 = new Bike();
        obj1.run(); // Metodo base ereditato
        obj1.run(50); // Metodo in overload ereditato

        System.out.println("\n--- Test Override e Overload (in Bike2) ---");
        Bike2 obj2 = new Bike2();
        obj2.run(); // Override: versione specifica di Bike2
        obj2.run(25); // Overload: ereditato dal padre
        obj2.run(30, "quarta"); // Overload: specifico di Bike2
        
        System.out.println("\n--- Test Polimorfismo ---");
        Veicolo v = new Bike2(); 
        v.run(); // Esegue l'Override di Bike2
        v.run(80); // Esegue l'Overload definito nel Padre
        // v.run(10, "terza"); // ERRORE! Il tipo 'Veicolo' non conosce la versione a 2 parametri
    }
}