public class ProvaMath {
  public static void main(String[] args) {
    // --- 1. Math.max(a, b) ---
    // Restituisce il valore più alto tra i due argomenti passati.
    int massimo = Math.max(10, 20);
    System.out.println("Il valore massimo tra 10 e 20 è: " + massimo);

    // --- 2. Math.min(a, b) ---
    // Restituisce il valore più basso tra i due argomenti passati.
    double minimo = Math.min(15.5, 7.2);
    System.out.println("Il valore minimo tra 15.5 e 7.2 è: " + minimo);

    // --- 3. Math.sqrt(x) ---
    // Calcola la radice quadrata (Square Root). 
    // Restituisce sempre un tipo 'double'.
    double radice = Math.sqrt(64);
    System.out.println("La radice quadrata di 64 è: " + radice);

    // Esempio combinato:
    double risultatoComplesso = Math.sqrt(Math.max(50, 100));
    System.out.println("Radice del massimo tra 50 e 100: " + risultatoComplesso);



    
    // --- 4. Math.abs(x) ---
    // Restituisce il valore assoluto (senza il segno meno).
    // Trasforma un numero negativo in positivo; se è già positivo, non fa nulla.
    int valoreNegativo = -47;
    int assoluto = Math.abs(valoreNegativo);
    System.out.println("Il valore assoluto di -47 è: " + assoluto);

    // --- 5. Math.random() ---
    // Genera un numero decimale (double) casuale tra 0.0 (incluso) e 1.0 (escluso).
    double numeroCasuale = Math.random();
    System.out.println("Numero casuale tra 0.0 e 1.0: " + numeroCasuale);

    // --- TRUCCO: Generare un numero intero tra 0 (incluso) e 101 (escluso) ---
    // Moltiplichiamo per 101 (per includere il 100) e facciamo il casting a (int).
    int interoCasuale = (int)(Math.random() * 101);
    System.out.println("Numero intero casuale tra 0 e 100: " + interoCasuale);
    
    // Esempio combinato: Calcolare la distanza tra due punti senza segni negativi
    int posizioneA = 10;
    int posizioneB = 25;
    int distanza = Math.abs(posizioneA - posizioneB); 
    System.out.println("Distanza tra A e B: " + distanza);
  }
}