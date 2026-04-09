package ConfigurazioneAcquisto.main;

import ConfigurazioneAcquisto.facade.SistemaOrdineFacade;
import ConfigurazioneAcquisto.strategy.implementazioni.*;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    // Inizializzazione della Facade
    SistemaOrdineFacade negozio = new SistemaOrdineFacade();
    Scanner scanner = new Scanner(System.in);
    boolean running = true;

    System.out.println("========================================");
    System.out.println("      BENVENUTO NEL PC CONFIGURATOR     ");
    System.out.println("========================================");

    // Ciclo principale del programma
    while (running) {
      System.out.println("\n--- MENU PRINCIPALE ---");
      System.out.println("1. Scegli configurazione base");
      System.out.println("2. Aggiungi componente extra");
      System.out.println("3. Scegli metodo di pagamento");
      System.out.println("4. Conferma ordine");
      System.out.println("0. Esci");
      System.out.print("\nScelta: ");

      int scelta = scanner.nextInt();
      scanner.nextLine(); 

      switch (scelta) {
      case 1 -> menuBase(negozio, scanner);
      case 2 -> menuExtra(negozio, scanner);
      case 3 -> menuPagamento(negozio, scanner);
      case 4 -> negozio.confermaOrdine();
      case 0 -> {
        System.out.println("\nArrivederci!");
        running = false;
      }
      default -> System.out.println("[!] Opzione non valida");
      }
    }
    scanner.close();
  }

  /**
   * Gestisce la selezione della configurazione hardware di partenza
   */
  private static void menuBase(SistemaOrdineFacade negozio, Scanner scanner) {
    System.out.println("\n--- SCEGLI CONFIGURAZIONE BASE ---");
    System.out.println("1. PC Ufficio  (450,00 EUR)");
    System.out.println("2. PC Gaming   (950,00 EUR)");
    System.out.print("\nScelta: ");
    int scelta = scanner.nextInt();
    scanner.nextLine();

    switch (scelta) {
      case 1, 2 -> negozio.scegliBase(scelta);
      default -> System.out.println("[!] Opzione non valida");
    }
  }

  /**
   * Gestisce l'aggiunta iterativa di componenti opzionali al PC
   */
  private static void menuExtra(SistemaOrdineFacade negozio, Scanner scanner) {
    boolean aggiungi = true;
    while (aggiungi) {
      System.out.println("\n--- AGGIUNGI COMPONENTE EXTRA ---");
      System.out.println("1. RAM Extra (+80,00 EUR)");
      System.out.println("2. SSD Extra (+60,00 EUR)");
      System.out.println("3. Scheda Video (+600,00 EUR)");
      System.out.println("4. Raffreddamento (+120,00 EUR)");
      System.out.println("0. Torna al menu principale");
      System.out.print("\nScelta: ");
      int scelta = scanner.nextInt();
      scanner.nextLine();

      switch (scelta) {
        case 1, 2, 3, 4 -> negozio.aggiungiComponente(scelta);
        case 0 -> aggiungi = false;
        default -> System.out.println("[!] Opzione non valida");
      }
    }
  }

  /**
   * Configura la strategia di pagamento desiderata per l'ordine
   */
  private static void menuPagamento(SistemaOrdineFacade negozio, Scanner scanner) {
    System.out.println("\n--- SCEGLI METODO DI PAGAMENTO ---");
    System.out.println("1. Carta di Credito");
    System.out.println("2. PayPal");
    System.out.println("3. Bonifico Bancario");
    System.out.print("\nScelta: ");
    int scelta = scanner.nextInt();
    scanner.nextLine();

    // Iniezione della strategia di pagamento specifica nella Facade
    switch (scelta) {
      case 1 -> negozio.impostaPagamento(new PagamentoCarta());
      case 2 -> negozio.impostaPagamento(new PagamentoPayPal());
      case 3 -> negozio.impostaPagamento(new PagamentoBonifico());
      default -> System.out.println("[!] Opzione non valida");
    }
 }
}