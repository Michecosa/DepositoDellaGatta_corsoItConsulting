import java.util.ArrayList; // Importa la classe ArrayList dal pacchetto utility di Java
import java.util.Collections; // Necessario per ordinare la lista

public class ProvaArrayList {
  public static void main(String[] args) {
    // Dichiarazione e inizializzazione di un ArrayList di stringhe
    // L'ArrayList è una struttura dati dinamica che può cambiare dimensione
    ArrayList<String> nomi = new ArrayList<>();

    // Aggiunta di elementi alla lista tramite il metodo add()
    nomi.add("Alice");
    nomi.add("Bob");
    nomi.add("Carlo");

    // Recupero di un elemento specifico tramite l'indice (il conteggio parte da 0)
    // nomi.get(0) restituisce il primo elemento inserito ("Alice")
    System.out.println("Primo nome: " + nomi.get(0));




    /* ----------------------------------------------------------------------------------- */




    // Creazione di un ArrayList di numeri interi (usiamo la classe wrapper Integer)
    ArrayList<Integer> numeri = new ArrayList<>();

    // Ciclo for per riempire la lista con 10 numeri
    for(int i = 0; i < 10; i++) {
        // Generazione di un numero casuale tra 1 e 100
        // Math.random() genera un double tra 0.0 e 0.99... 
        // Moltiplichiamo per 100 e aggiungiamo 1, poi facciamo il cast a (int)
        numeri.add((int) (Math.random() * 100) + 1);
    }

    // Stampa della lista così come è stata generata (ordine di inserimento)
    System.out.println("Lista originale: " + numeri);

    // Utilizzo del metodo statico sort della classe Collections 
    // per ordinare i numeri in ordine crescente
    Collections.sort(numeri);

    // Stampa della lista dopo l'ordinamento
    System.out.println("Lista ordinata: " + numeri+"\n");




    /* ----------------------------------------------------------------------------------- */




    
  }
}