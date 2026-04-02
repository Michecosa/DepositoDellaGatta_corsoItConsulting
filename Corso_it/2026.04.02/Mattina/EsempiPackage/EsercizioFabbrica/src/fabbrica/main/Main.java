package fabbrica.main;
import fabbrica.produzione.Macchina;
import fabbrica.personale.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Inizializzazione oggetti richiesti
        Macchina m1 = new Macchina("L0L-2000");
        Operaio base = new Operaio("Mario");
        OperaioSpecial special = new OperaioSpecial("Luigi");
        OperaioDirigente capo = new OperaioDirigente("Dott. Rossi");

        int scelta;

        do {
            System.out.println("\n--- MENU FABBRICA ---");
            System.out.println("1. Accendi macchina (Operaio Special)");
            System.out.println("2. Produci Famiglia A (Smartphone)");
            System.out.println("3. Produci Famiglia B (Tablet)");
            System.out.println("4. Stampa Stato (Solo Dirigente)");
            System.out.println("5. Spegni macchina (Operaio Base)");
            System.out.println("0. Esci");
            System.out.print("Scelta: ");
            scelta = scanner.nextInt();

            switch (scelta) {
                case 1:
                    special.lavora(m1);
                    break;
                case 2:
                    m1.creaProdotto("Elettronica - Smartphone");
                    break;
                case 3:
                    m1.creaProdotto("Elettronica - Tablet");
                    break;
                case 4:
                    capo.controllaMacchina(m1);
                    break;
                case 5:
                    base.ferma(m1);
                    break;
                case 0:
                    System.out.println("Chiusura fabbrica...");
                    break;
                default:
                    System.out.println("Opzione non valida");
            }
        } while (scelta != 0);

        scanner.close();
    }
}