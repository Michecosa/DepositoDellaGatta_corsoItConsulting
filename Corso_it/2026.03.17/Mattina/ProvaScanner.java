import java.util.Scanner;

public class ProvaScanner {
  public static void main(String[] args) {
    Scanner myObjString = new Scanner(System.in); // Create a Scanner object
    Scanner myObjNum = new Scanner(System.in);

    System.out.println("Enter username");
    String userName = myObjString.nextLine(); // Read user Input

    System.out.println("Enter age");
    String userAge = myObjNum.nextLine();


    System.out.println("Username is "+ userName+ ", " + userAge + " y/o"); // Output user Input

    myObjString.close();
    myObjNum.close();
  }
}