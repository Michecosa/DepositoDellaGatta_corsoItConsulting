import java.util.ArrayList;
import java.util.Scanner;

public class EsercizioGestioneNomiMenuExtra {
	public static void main(String[] args) {
		ArrayList<String> nomi = new ArrayList<>();
		ArrayList<String> cognomi = new ArrayList<>();
		
		// Creo l'ArrayList contenitore che ospiterà le due liste precedenti
		ArrayList<ArrayList<String>> database = new ArrayList<>();
		database.add(nomi);
		database.add(cognomi);

		Scanner input = new Scanner(System.in);
		String risposta;

		// 1. Inserimento dati
		System.out.println("--- Inserimento Dati ---");
		while (true) {
			System.out.print("\nInserisci Nome (o 'fine'): ");
			risposta = input.nextLine();
			if (risposta.toLowerCase().equals("fine")) break;
			nomi.add(risposta);

			System.out.print("Inserisci Cognome: ");
			cognomi.add(input.nextLine());
		}

		// 2. Ordinamento alfabetico (per cognome)
		// Uso un algoritmo di ordinamento semplice (Bubble Sort) per mantenere l'allineamento
		for (int i = 0; i < cognomi.size() - 1; i++) {
			for (int j = 0; j < cognomi.size() - i - 1; j++) {
				
				// Trasformo entrambi in minuscolo per il confronto
				String attuale = cognomi.get(j).toLowerCase();
				String successivo = cognomi.get(j + 1).toLowerCase();

				// Uso compareTo 
				// Se il risultato è > 0, significa che 'attuale' viene dopo 'successivo'
				if (attuale.compareTo(successivo) > 0) {
					
					// Scambio i cognomi 
					String tempCognome = cognomi.get(j);
					cognomi.set(j, cognomi.get(j + 1));
					cognomi.set(j + 1, tempCognome);

					// Scambio anche i nomi per mantenerli allineati
					String tempNome = nomi.get(j);
					nomi.set(j, nomi.get(j + 1));
					nomi.set(j + 1, tempNome);
				}
			}
		}

		// 3. Visualizzazione
		System.out.println("\n--- Elenco Ordinato (per Cognome) ---");
		if (nomi.isEmpty()) {
			System.out.println("La lista è vuota.");
		} else {
			for (int i = 0; i < nomi.size(); i++) {
				// Stampo l'indice i, seguito da Cognome e Nome
				System.out.println(i + ") " + cognomi.get(i) + " " + nomi.get(i));
			}
		}

		// 4. Rimuovo una persona tramite indice
		if (!nomi.isEmpty()) {
			System.out.print("\nInserisci l'indice della persona da rimuovere: ");
			int indiceDaRimuovere = input.nextInt();

			// Controllo se l'indice inserito è valido
			if (indiceDaRimuovere >= 0 && indiceDaRimuovere < nomi.size()) {
				nomi.remove(indiceDaRimuovere);
				cognomi.remove(indiceDaRimuovere);
				System.out.println("Persona rimossa con successo.");
			} else {
				System.out.println("Errore: Indice non valido.");
			}
		}

		System.out.println("\n--- Database finale ---");
		for (int i = 0; i < nomi.size(); i++) {
			// Stampo l'indice i, seguito da Cognome e Nome
			System.out.println(i + ") " + cognomi.get(i) + " " + nomi.get(i));
		}
		System.out.println();
		input.close();
	}
}