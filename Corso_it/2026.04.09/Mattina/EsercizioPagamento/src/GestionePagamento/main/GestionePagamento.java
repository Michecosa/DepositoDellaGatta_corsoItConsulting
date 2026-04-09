package GestionePagamento.main;

import GestionePagamento.context.PagamentoContext;
import GestionePagamento.strategies.implementazioni.CartaDiCreditoStrategy;
import GestionePagamento.strategies.implementazioni.PayPalStrategy;
import java.util.Scanner;

public class GestionePagamento {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    PagamentoContext pagamento = new PagamentoContext();

    System.out.println("\n=======================================================");
    System.out.println("=== OFFERTA IMPERDIBILE: IPHONE 20 PRO A SOLI 550€! ===");
    System.out.println("=======================================================");
    System.out.println("Affrettati! Solo 2 pezzi rimasti!!! \nInserisci i dati per l'acquisto");
    System.out.println("\nScegli il metodo \n\t[1] PayPal sicuro al 100%\n\t[2] Carta di Credito (senza commissioni):");
    
    System.out.print("\nScelta: ");
    String scelta = scanner.nextLine();

    if (scelta.equals("1")) {
      System.out.print("Inserisci email PayPal (la useremo per lo spam): ");
      String email = scanner.nextLine();
      pagamento.setStrategiaPagamento(new PayPalStrategy(email));
    } else {
      System.out.print("Inserisci numero carta: ");
      String numero = scanner.nextLine();
      pagamento.setStrategiaPagamento(new CartaDiCreditoStrategy(numero));
    }

    System.out.println("\n--- ELABORAZIONE TRANSAZIONE IN CORSO (NON CHIUDERE!) ---");
    
    // Simulo l'esecuzione del pagamento tramite il pattern Strategy
    // Il metodo eseguiPagamento stamperà i dettagli definiti nelle tue strategie
    pagamento.eseguiPagamento(550);

    System.out.println("\nPagamento ricevuto! Grazie per l'acquisto");
    System.out.println("Il tuo pacco verrà spedito forse mai. Controlla il tuo conto ogni 5 minuti!\n\n");
    
    scanner.close();
  }
}