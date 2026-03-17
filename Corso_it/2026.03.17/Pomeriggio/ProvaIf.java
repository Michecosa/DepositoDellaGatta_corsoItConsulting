public class ProvaIf {
  public static void main(String[] args) {
    // Definiamo una variabile intera per l'orario (formato 24 ore)
    int time = 22;

    // Inizio della struttura condizionale:
    // Java controlla le condizioni una dopo l'altra dall'alto verso il basso.
    if (time < 10) {
        // Se la condizione (time < 10) è vera, esegue questo:
        System.out.println("Good morning.");
    } 
    else if (time < 18) {
        // Se la prima era falsa, ma questa (time < 18) è vera, esegue questo:
        System.out.println("Good day.");
    } 
    else {
        // Se nessuna delle condizioni precedenti era vera, esegue l'ultima opzione:
        System.out.println("Good evening.");
    }

    // Siccome 22 non è minore di 10 né di 18, 
    // il programma stamperà "Good evening."
  }
}