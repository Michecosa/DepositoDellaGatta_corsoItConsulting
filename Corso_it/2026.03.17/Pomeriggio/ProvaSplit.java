import java.util.Arrays;

public class ProvaSplit {
  public static void main(String[] args) {
    String str = "Hello World";

    /*
     * Il metodo split() divide una stringa in un array di sottostringhe.
     * "\\s" è una Regex (espressione regolare) che identifica gli "spazi bianchi".
     * Include: spazio standard, tabulazione (\t), nuova riga (\n), ecc.
     */
    String[] words = str.split("\\s");

    /*
     * Poiché 'words' è un array, non possiamo stamparlo direttamente con 
     * System.out.println(words) altrimenti vedremmo solo l'indirizzo di memoria.
     * Usiamo Arrays.toString() per visualizzare il contenuto in modo leggibile.
     */
    System.out.println(Arrays.toString(words)); // Output: [Hello, World]
  }
}