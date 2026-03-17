import java.util.Scanner;

public class EsercizioMath {
    public static void main(String[] args) {
        // Scanner per leggere quello che scrivo sulla tastiera
        Scanner tastiera = new Scanner(System.in);
        System.out.println("--- Esercizio Menu ---");

        // Chiedo i numeri 
        System.out.print("Inserisci il primo numero: ");
        double a = tastiera.nextDouble();
        System.out.print("Inserisci il secondo numero: ");
        double b = tastiera.nextDouble();



        System.out.println("\n--- Risultati ---");

        // 1. Somma
        double somma = a + b;
        System.out.println("La somma è: " + somma);

        // 2. Sottrazione
        double sottrazione = a - b;
        System.out.println("La differenza è: " + sottrazione);

        // 3. Moltiplicazione
        double moltiplicazione = a * b;
        System.out.println("Il prodotto è: " + moltiplicazione);

        // 4. Divisione
        double divisione = a / b;
        System.out.println("Il quoziente è: " + divisione);

        System.out.println("\nE una parte è fatta");
        


        // Chiudo lo scanner
        tastiera.close();
    }
}