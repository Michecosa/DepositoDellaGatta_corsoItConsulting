public class ProvaStringhe {
  public static void main(String[] args) {
    String greeting = "Hello";
    String txt = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    System.out.println("("+greeting.length()+") "+greeting);
    System.out.println("("+txt.length()+") "+txt);


    String prova = "Hello World";
    System.out.println(prova+" -> toUpperCase(): "+prova.toUpperCase()); // Outputs "HELLO WORLD"
    System.out.println(prova+" -> toLowerCase(): "+prova.toLowerCase()); // Outputs "hello world"

    String findLocateString = "Please locate where 'locate' occurs!";
    System.out.println("'locate' occurs at index: "+findLocateString.indexOf("locate")); // Outputs 7
  }
}
