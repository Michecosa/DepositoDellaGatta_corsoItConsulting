import java.util.ArrayList;

// --- CLASSE AEREO ---
class Aereo {
	private String modello;
	private int numeroPosti;
	private String codice;

	public Aereo(String modello, int numeroPosti, String codice) {
		this.modello = modello;
		setNumeroPosti(numeroPosti); // Uso il setter per validare anche nel costruttore
		this.codice = codice;
	}

	// Getter e Setter
	public String getModello() { return modello; }
	public void setModello(String modello) { this.modello = modello; }

	public int getNumeroPosti() { return numeroPosti; }
	public void setNumeroPosti(int numeroPosti) {
		if (numeroPosti > 0) {
			this.numeroPosti = numeroPosti;
		} else {
			System.out.println("Errore: numero posti deve essere positivo.");
		}
	}

	public String getCodice() { return codice; }
	public void setCodice(String codice) { this.codice = codice; }
}

// --- CLASSE PILOTA ---
class Pilota {
	private String nome;
	private String numeroBrevetto;
	private int oreVolo;

	public Pilota(String nome, String numeroBrevetto, int oreVolo) {
		this.nome = nome;
		this.numeroBrevetto = numeroBrevetto;
		setOreVolo(oreVolo);
	}

	// Getter e Setter
	public String getNome() { return nome; }
	public void setNome(String nome) { this.nome = nome; }

	public String getNumeroBrevetto() { return numeroBrevetto; }
	public void setNumeroBrevetto(String numeroBrevetto) { this.numeroBrevetto = numeroBrevetto; }

	public int getOreVolo() { return oreVolo; }
	public void setOreVolo(int oreVolo) {
		if (oreVolo > 0) {
			this.oreVolo = oreVolo;
		} else {
			System.out.println("Errore: le ore di volo devono essere maggiori di zero");
		}
	}
}

// --- CLASSE COMPAGNIA AEREA ---
class CompagniaAerea {
	private String nome;
	private ArrayList<Aereo> flotta;
	private ArrayList<Pilota> piloti;

	public CompagniaAerea(String nome) {
		this.nome = nome;
		this.flotta = new ArrayList<>();
		this.piloti = new ArrayList<>();
	}

	public void aggiungiAereo(Aereo a) {
		flotta.add(a);
	}

	public void aggiungiPilota(Pilota p) {
		piloti.add(p);
	}

	public void stampaInfo() {
		System.out.println("=== COMPAGNIA AEREA: " + nome + " ===");
		
		System.out.println("\nFLOTTA:");
		for (Aereo a : flotta) {
			System.out.println("- Modello: " + a.getModello() + " | Posti: " + a.getNumeroPosti() + " | Codice: " + a.getCodice());
		}

		System.out.println("\nPILOTI:");
		for (Pilota p : piloti) {
			System.out.println("- Nome: " + p.getNome() + " | Brevetto: " + p.getNumeroBrevetto() + " | Ore volo: " + p.getOreVolo());
		}
	}
}

// --- MAIN ---
public class EsercizioGestioneFlotta {
	public static void main(String[] args) {
		// Creazione Compagnia
		CompagniaAerea compagnia = new CompagniaAerea("Miche Airlines");

		// Creazione e aggiunta Aerei
		Aereo a1 = new Aereo("Boeing 747", 416, "B747-01");
		Aereo a2 = new Aereo("Airbus A320", 150, "A320-99");
		compagnia.aggiungiAereo(a1);
		compagnia.aggiungiAereo(a2);

		// Creazione e aggiunta Piloti
		Pilota p1 = new Pilota("Capitan Miche", "IT12345", 5000);
		Pilota p2 = new Pilota("Mario Rossi", "IT67890", 1200);
		compagnia.aggiungiPilota(p1);
		compagnia.aggiungiPilota(p2);

		// Stampa finale
		compagnia.stampaInfo();
	}
}