package BarApp.main;

import java.util.Scanner;

import BarApp.decorator.Bevanda;
import BarApp.decorator.Caffe;
import BarApp.decorator.Cannella;
import BarApp.decorator.Cioccolata;
import BarApp.decorator.Latte;
import BarApp.decorator.Panna;
import BarApp.decorator.Te;
import BarApp.decorator.Zucchero;
import BarApp.observer.LoggerOrdini;
import BarApp.singleton.GestoreOrdini;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GestoreOrdini gestore = GestoreOrdini.getInstance();
        gestore.aggiungiObserver(new LoggerOrdini());
        Bevanda bevandaCorrente = null;

        boolean continua = true;
        while (continua) {
            System.out.println("\n--- MENU BAR ---");
            System.out.println("1. CREA NUOVA BEVANDA");
            System.out.println("2. AGGIUNGI INGREDIENTE EXTRA");
            System.out.println("3. VISUALIZZA BEVANDA CORRENTE");
            System.out.println("4. CONFERMA ORDINE");
            System.out.println("5. VISUALIZZA STORICO ORDINI");
            System.out.println("6. ESCI");
            System.out.print("Scelta: ");
            int scelta = scanner.nextInt();

            switch (scelta) {
                case 1:
                    System.out.println("Scegli base: 1.Caffè, 2.Tè, 3.Cioccolata");
                    int b = scanner.nextInt();
                    if (b == 1) bevandaCorrente = new Caffe();
                    else if (b == 2) bevandaCorrente = new Te();
                    else bevandaCorrente = new Cioccolata();
                    break;

                case 2:
                    if (bevandaCorrente == null) {
                        System.out.println("Errore: Crea prima una bevanda!");
                    } else {
                        System.out.println("Extra: 1.Latte, 2.Zucchero, 3.Panna, 4.Cannella");
                        int e = scanner.nextInt();
                        if (e == 1) bevandaCorrente = new Latte(bevandaCorrente);
                        else if (e == 2) bevandaCorrente = new Zucchero(bevandaCorrente);
                        else if (e == 3) bevandaCorrente = new Panna(bevandaCorrente);
                        else if (e == 4) bevandaCorrente = new Cannella(bevandaCorrente);
                    }
                    break;

                case 3:
                    if (bevandaCorrente != null) {
                        System.out.println("\nORDINE ATTUALE:");
                        System.out.println("Dettaglio: " + bevandaCorrente.getDescrizione());
                        System.out.printf("Costo: %.2f EUR%n", bevandaCorrente.getCosto());
                    } else System.out.println("Nessuna bevanda in preparazione");
                    break;

                case 4:
                    if (bevandaCorrente != null) {
                        gestore.confermaOrdine(bevandaCorrente);
                        bevandaCorrente = null; // Reset per nuovo ordine
                    } else System.out.println("Crea una bevanda prima di confermare!");
                    break;

                case 5:
                    gestore.visualizzaStorico();
                    break;

                case 6:
                    continua = false;
                    break;
            }
        }
        scanner.close();
        System.out.println("\nArrivederci!\n");
    }
}
