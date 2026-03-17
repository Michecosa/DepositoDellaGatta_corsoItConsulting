public class ProvaConcatenazione {
  public static void main(String[] args) {
  
    String firstName = "John";
    String lastName = "Doe";

    // Metodo 1: Operatore "+" (con aggiunta di uno spazio letterale)
    System.out.println("Nome completo (+): " + firstName + " " + lastName);

    // Metodo 2: .concat() (concatena direttamente senza spazi aggiunti)
    System.out.println("Nome completo (concat): " + firstName.concat(lastName));



    int x = 10;
    int y = 20;
    int z = x + y; // Somma matematica: 30
    System.out.println("Somma di interi: " + z);
    
    String a = "10";
    String b = "20";
    String c = a + b; // Concatenazione di stringhe: "1020"
    System.out.println("Concatenazione di stringhe: " + c);




    // 1. Virgolette doppie (\")
    String txt = "Siamo i cosiddetti \"Vichinghi\" del nord.";
    System.out.println(txt);

    // 2. Apice singolo (\') - utile soprattutto con i tipi 'char'
    String apex = "L\'albero è alto.";
    System.out.println(apex);

    // 3. Backslash (\\) - per stampare una barra rovesciata
    String path = "C:\\Utenti\\Documenti";
    System.out.println("Percorso file: " + path);
  }
}