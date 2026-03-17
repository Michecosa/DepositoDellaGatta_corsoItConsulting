public class ProvaCaratteriSpeciali {
  public static void main(String[] args) {
    // 1. Newline (\n): Inserisce una nuova riga (va a capo)
    String newline = "Prima riga\nSeconda riga";
    System.out.println("--- Test \\n ---");
    System.out.println(newline);

    // 2. Tab (\t): Inserisce uno spazio di tabulazione (allineamento)
    String tab = "Nome:\tJohn\tCognome:\tDoe";
    System.out.println("\n--- Test \\t ---");
    System.out.println(tab);

    // 3. Backspace (\b): Cancella il carattere precedente
    // Nota: Il comportamento può variare a seconda del terminale/IDE usato
    String backspace = "Erroree\b"; 
    System.out.println("\n--- Test \\b ---");
    System.out.println("Parola corretta: " + backspace);

    // 4. Carriage Return (\r): Porta il cursore all'inizio della riga corrente
    // Il testo che segue sovrascrive quello precedente sulla stessa riga
    String carriage = "Testo vecchio\rNuovo";
    System.out.println("\n--- Test \\r ---");
    System.out.println(carriage); // Vedrai solo "Nuovo" (o "Nuovovecc")

    // 5. Form Feed (\f): Storicamente usato per espellere la pagina nelle stampanti
    // Nei terminali moderni spesso appare come un simbolo strano o un salto pagina
    String formFeed = "Pagina 1\fPagina 2";
    System.out.println("\n--- Test \\f ---");
    System.out.println(formFeed);
  }
}