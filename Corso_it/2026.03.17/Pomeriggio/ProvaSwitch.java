public class ProvaSwitch {
  public static void main(String[] args) {
    // Dichiarazione e inizializzazione della variabile intera 'day'
    int day = 4;

    // Inizio della struttura di controllo switch che valuta il valore di 'day'
    switch (day) {
      // Se day è uguale a 1, stampa Lunedì
      case 1:
        System.out.println("Monday");
        break; // Esci dallo switch
      
      // Se day è uguale a 2, stampa Martedì
      case 2:
        System.out.println("Tuesday");
        break;

      // Se day è uguale a 3, stampa Mercoledì
      case 3:
        System.out.println("Wednesday");
        break;

      // Se day è uguale a 4 (il valore corrente), stampa Giovedì
      case 4:
        System.out.println("Thursday");
        break;

      // Se day è uguale a 5, stampa Venerdì
      case 5:
        System.out.println("Friday");
        break;

      // Se day è uguale a 6, stampa Sabato
      case 6:
        System.out.println("Saturday");
        break;

      // Se day è uguale a 7, stampa Domenica
      case 7:
        System.out.println("Sunday");
        break;
    }
  }
}

/* 
L'istruzione break: 
È fondamentale perché interrompe l'esecuzione una volta trovato il caso corrispondente. 
Senza di essa, Java continuerebbe a eseguire anche i casi successivi (fall-through) 
*/