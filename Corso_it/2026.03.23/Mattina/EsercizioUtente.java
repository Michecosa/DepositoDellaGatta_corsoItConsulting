import java.util.Scanner;

public class EsercizioUtente {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    int annoCorrente = 2026;

    // Richiedo i dati all'utente 
    System.out.print("Inserisci il tuo nome: ");
    String nome = input.nextLine();

    // Validazione per l'anno di nascita
    int annoNascita;
    do {
      System.out.print("Inserisci l'anno di nascita (es. 1900-2026): ");
      annoNascita = input.nextInt();
      if (annoNascita < 1900 || annoNascita > annoCorrente) {
        System.out.println("Errore: Inserisci un anno valido");
      }
    } while (annoNascita < 1900 || annoNascita > annoCorrente);

    // Validazione per il giorno della settimana
    int giornoAttuale;
    do {
      System.out.print("Inserisci che giorno è oggi (1=Lun, 7=Dom): ");
      giornoAttuale = input.nextInt();
      if (giornoAttuale < 1 || giornoAttuale > 7) {
        System.out.println("Errore: Il numero deve essere compreso tra 1 e 7");
      }
    } while (giornoAttuale < 1 || giornoAttuale > 7);

    // Calcolo età
    int eta = annoCorrente - annoNascita;
    
    // Calcolo giorni al weekend (Sabato = 6)
    int giorniAlWeekend = 6 - giornoAttuale;
    
    // Se è già Sabato (0) o Domenica (-1), imposto a 0
    if (giorniAlWeekend < 0) {
      giorniAlWeekend = 0; 
    }

    // Messaggio di Output
    System.out.println("Il tuo nome è " + nome + ", hai " + eta + " anni e mancano " + giorniAlWeekend + " giorni al weekend\n");
    
    // Chiudo le risorse
    input.close();
  }
}