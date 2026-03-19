import java.util.Scanner;

public class EsercizioGestioneNomiMenuArrayMultiDim {
  public static void main(String[] args) {
    // Definiscoo una capacità massima per l'array statico
    int MAX_PERSONE = 100;
    
    // Array multidimensionale: 100 righe e 2 colonne
    // Colonna 0: Nome, Colonna 1: Cognome
    String[][] database = new String[MAX_PERSONE][2];
    
    // Tengo traccia di quante persone vengono inserite
    int contatorePersone = 0;

    Scanner input = new Scanner(System.in);
    String risposta;

    // 1. Inserimento dati
    System.out.println("--- Inserimento Dati ---");
    while (contatorePersone < MAX_PERSONE) {
      System.out.print("\nInserisci Nome (o 'fine'): ");
      risposta = input.nextLine();
      
      if (risposta.equalsIgnoreCase("fine")) break;
      
      database[contatorePersone][0] = risposta; // Nome

      System.out.print("Inserisci Cognome: ");
      database[contatorePersone][1] = input.nextLine(); // Cognome
      
      contatorePersone++;
    }

    // 2. Ordinamento alfabetico (per cognome) con Bubble Sort
    for (int i = 0; i < contatorePersone - 1; i++) {
      for (int j = 0; j < contatorePersone - i - 1; j++) {
        
        // Confronto i cognomi (colonna 1)
        String attuale = database[j][1].toLowerCase();
        String successivo = database[j + 1][1].toLowerCase();

        if (attuale.compareTo(successivo) > 0) {
          // Scambio l'intera riga (Nome e Cognome)
          // Scambio Nome
          String tempNome = database[j][0];
          database[j][0] = database[j + 1][0];
          database[j + 1][0] = tempNome;

          // Scambio Cognome
          String tempCognome = database[j][1];
          database[j][1] = database[j + 1][1];
          database[j + 1][1] = tempCognome;
        }
      }
    }

    // 3. Visualizzazione
    System.out.println("\n--- Database finale Ordinato (per Cognome) ---");
    if (contatorePersone == 0) {
      System.out.println("La lista è vuota");
    } else {
      for (int i = 0; i < contatorePersone; i++) {
        System.out.println(i + ") " + database[i][1] + " " + database[i][0]);
      }
    }
    
    System.out.println();
    input.close();
  }
}