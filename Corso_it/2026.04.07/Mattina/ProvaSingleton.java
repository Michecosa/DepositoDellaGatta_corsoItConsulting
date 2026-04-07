// Definizione della classe Singleton
class Singleton {
    
    // 1. Istanza privata statica della classe Singleton
    // È l'unico posto dove l'oggetto verrà effettivamente memorizzato
    private static Singleton instance;

    // 2. Costruttore privato
    // Impendisce l'uso di "new Singleton()" dall'esterno
    private Singleton() {
        System.out.println("Istanza creata per la prima volta");
    }

    // 3. Metodo pubblico statico per ottenere l'unica istanza
    public static Singleton getInstance() {
        // Se l'istanza non esiste (è la prima chiamata), viene creata
        if (instance == null) {
            instance = new Singleton();
        }
        // Restituisce l'istanza esistente o quella appena creata
        return instance;
    }

    // Metodo di esempio per testare il funzionamento
    public void doSomething() {
        System.out.println("Singleton: doSomething() chiamato con successo\n");
    }
}

public class ProvaSingleton {
    public static void main(String[] args) {
        
        // 1. Non posso fare: Singleton s = new Singleton(); 
        // Darebbe errore perché il costruttore è privato

        // 2. Ottengo il riferimento all'unica istanza tramite getInstance()
        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();

        // 3. Chiamo un metodo sull'istanza
        s1.doSomething();

        // 4. VERIFICA: s1 e s2 sono lo stesso oggetto?
        if (s1 == s2) {
            System.out.println("S1 e S2 sono la STESSA istanza. Il pattern Singleton funziona");
        } else {
            System.out.println("Errore: S1 e S2 sono diversi");
        }
        
        // Stampiamo gli indirizzi di memoria per conferma visuale
        System.out.println("Memory address S1: " + s1);
        System.out.println("Memory address S2: " + s2);
    }
}