import java.util.ArrayList;

// Definizione della classe Auto che farà da "modello" per i nostri oggetti
class Auto {
    String marca;
    int anno;

    // Costruttore: inizializzo l'oggetto quando viene creato con 'new'
    Auto(String marca, int anno) {
        this.marca = marca; // 'this' serve a distinguere il campo della classe dal parametro
        this.anno = anno;
    }

    // Metodo toString (implicito dell'oggetto) 
    @Override // Buona pratica
    public String toString() {
        return marca+" - "+anno;
    }
}

public class ProvaOggettiArrayList {
    public static void main(String[] args) {
        // Creazione di un ArrayList che può contenere solo oggetti di tipo Auto (Generics)
        ArrayList<Auto> autoList = new ArrayList<>();

        /* Aggiunta di oggetti alla lista. 
           Nota: 'new Auto(...)' crea l'oggetto in memoria, 
           e l'ArrayList memorizza solo il RIFERIMENTO (l'indirizzo) a quell'oggetto.
        */
        autoList.add(new Auto("Tesla", 2023));
        autoList.add(new Auto("Ford", 2020));

        // Ciclo "for-each": serve a scorrere la lista elemento per elemento
        for (Auto auto : autoList) {
            /* Accediamo ai campi (marca e anno) dell'oggetto corrente 
               usando l'operatore punto (.) e stampiamo i valori.
            */
            // System.out.println(auto.marca + " - " + auto.anno);
            System.out.println(auto);
        }
    }
}