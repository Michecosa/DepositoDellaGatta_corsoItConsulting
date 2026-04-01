import java.util.ArrayList;

// *** Classe base: CapoPrincipale ***
class CapoPrincipale {
    private String codice;
    private String nome;
    private String tessuto;
    private String colore;
    private String taglia;
    private double prezzo;

    public CapoPrincipale(String codice, String nome, String tessuto, String colore, String taglia, double prezzo) {
        setCodice(codice);
        setNome(nome);
        setTessuto(tessuto);
        setColore(colore);
        setTaglia(taglia);
        setPrezzo(prezzo);
    }

    public String getCodice() { return codice; }
    public String getNome() { return nome; }
    public String getTessuto() { return tessuto; }
    public String getColore() { return colore; }
    public String getTaglia() { return taglia; }
    public double getPrezzo() { return prezzo; }

    public void setCodice(String codice) {
        if (codice == null || codice.trim().isEmpty())
            throw new IllegalArgumentException("Il codice non può essere vuoto");
        this.codice = codice.trim();
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty())
            throw new IllegalArgumentException("Il nome non può essere vuoto");
        this.nome = nome.trim();
    }

    public void setTessuto(String tessuto) {
        if (tessuto == null || tessuto.trim().isEmpty())
            throw new IllegalArgumentException("Il tessuto non può essere vuoto");
        this.tessuto = tessuto.trim();
    }

    public void setColore(String colore) {
        if (colore == null || colore.trim().isEmpty())
            throw new IllegalArgumentException("Il colore non può essere vuoto");
        this.colore = colore.trim();
    }

    public void setTaglia(String taglia) {
        if (taglia == null || taglia.trim().isEmpty())
            throw new IllegalArgumentException("La taglia non può essere vuota");
        this.taglia = taglia.trim();
    }

    public void setPrezzo(double prezzo) {
        if (prezzo <= 0)
            throw new IllegalArgumentException("Il prezzo deve essere maggiore di 0");
        this.prezzo = prezzo;
    }

    public void mostraDettagli() {
        System.out.println("Codice: " + codice + " | Nome: " + nome);
        System.out.println("Tessuto: " + tessuto + " | Colore: " + colore + " | Taglia: " + taglia);
        System.out.printf("Prezzo: %.2f EUR%n", prezzo);
    }
}

// --- Sottoclassi di CapoPrincipale ---

class Giacca extends CapoPrincipale {
    private int numeroBottoni;

    public Giacca(String codice, String nome, String tessuto, String colore, String taglia, double prezzo, int numeroBottoni) {
        super(codice, nome, tessuto, colore, taglia, prezzo);
        setNumeroBottoni(numeroBottoni);
    }

    public int getNumeroBottoni() { return numeroBottoni; }

    public void setNumeroBottoni(int numeroBottoni) {
        if (numeroBottoni < 0)
            throw new IllegalArgumentException("Il numero di bottoni non può essere negativo");
        this.numeroBottoni = numeroBottoni;
    }

    @Override
    public void mostraDettagli() {
        System.out.println("-- Giacca --");
        super.mostraDettagli();
        System.out.println("Numero bottoni: " + numeroBottoni);
    }
}

class Pantalone extends CapoPrincipale {
    private String tipoTaglio;

    public Pantalone(String codice, String nome, String tessuto, String colore, String taglia, double prezzo, String tipoTaglio) {
        super(codice, nome, tessuto, colore, taglia, prezzo);
        setTipoTaglio(tipoTaglio);
    }

    public String getTipoTaglio() { return tipoTaglio; }

    public void setTipoTaglio(String tipoTaglio) {
        if (tipoTaglio == null || tipoTaglio.trim().isEmpty())
            throw new IllegalArgumentException("Il tipo di taglio non può essere vuoto.");
        this.tipoTaglio = tipoTaglio.trim();
    }

    @Override
    public void mostraDettagli() {
        System.out.println("-- Pantalone --");
        super.mostraDettagli();
        System.out.println("Tipo taglio: " + tipoTaglio);
    }
}

class Gilet extends CapoPrincipale {
    private boolean reverPresente;

    public Gilet(String codice, String nome, String tessuto, String colore, String taglia, double prezzo, boolean reverPresente) {
        super(codice, nome, tessuto, colore, taglia, prezzo);
        this.reverPresente = reverPresente;
    }

    public boolean isReverPresente() { return reverPresente; }
    public void setReverPresente(boolean reverPresente) { this.reverPresente = reverPresente; }

    @Override
    public void mostraDettagli() {
        System.out.println("-- Gilet --");
        super.mostraDettagli();
        System.out.println("Rever presente: " + (reverPresente ? "Si" : "No"));
    }
}

// *** Classe base: ComponenteFinitura ***
class ComponenteFinitura {
    private String codice;
    private String nome;
    private String materiale;
    private String colore;
    private double prezzo;

    public ComponenteFinitura(String codice, String nome, String materiale, String colore, double prezzo) {
        setCodice(codice);
        setNome(nome);
        setMateriale(materiale);
        setColore(colore);
        setPrezzo(prezzo);
    }

    public String getCodice() { return codice; }
    public String getNome() { return nome; }
    public String getMateriale() { return materiale; }
    public String getColore() { return colore; }
    public double getPrezzo() { return prezzo; }

    public void setCodice(String codice) {
        if (codice == null || codice.trim().isEmpty())
            throw new IllegalArgumentException("Il codice non può essere vuoto");
        this.codice = codice.trim();
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty())
            throw new IllegalArgumentException("Il nome non può essere vuoto");
        this.nome = nome.trim();
    }

    public void setMateriale(String materiale) {
        if (materiale == null || materiale.trim().isEmpty())
            throw new IllegalArgumentException("Il materiale non può essere vuoto");
        this.materiale = materiale.trim();
    }

    public void setColore(String colore) {
        if (colore == null || colore.trim().isEmpty())
            throw new IllegalArgumentException("Il colore non può essere vuoto");
        this.colore = colore.trim();
    }

    public void setPrezzo(double prezzo) {
        if (prezzo <= 0)
            throw new IllegalArgumentException("Il prezzo deve essere maggiore di 0");
        this.prezzo = prezzo;
    }

    public void mostraDettagli() {
        System.out.println("Codice: " + codice + " | Nome: " + nome);
        System.out.println("Materiale: " + materiale + " | Colore: " + colore);
        System.out.printf("Prezzo: %.2f EUR%n", prezzo);
    }
}

// --- Sottoclassi di ComponenteFinitura ---

class Cravatta extends ComponenteFinitura {
    private double larghezza;

    public Cravatta(String codice, String nome, String materiale, String colore, double prezzo, double larghezza) {
        super(codice, nome, materiale, colore, prezzo);
        setLarghezza(larghezza);
    }

    public double getLarghezza() { return larghezza; }

    public void setLarghezza(double larghezza) {
        if (larghezza <= 0)
            throw new IllegalArgumentException("La larghezza deve essere maggiore di 0");
        this.larghezza = larghezza;
    }

    @Override
    public void mostraDettagli() {
        System.out.println("-- Cravatta --");
        super.mostraDettagli();
        System.out.printf("Larghezza: %.1f cm%n", larghezza);
    }
}

class Papillon extends ComponenteFinitura {
    private String tipoChiusura;

    public Papillon(String codice, String nome, String materiale, String colore, double prezzo, String tipoChiusura) {
        super(codice, nome, materiale, colore, prezzo);
        setTipoChiusura(tipoChiusura);
    }

    public String getTipoChiusura() { return tipoChiusura; }

    public void setTipoChiusura(String tipoChiusura) {
        if (tipoChiusura == null || tipoChiusura.trim().isEmpty())
            throw new IllegalArgumentException("Il tipo di chiusura non può essere vuoto");
        this.tipoChiusura = tipoChiusura.trim();
    }

    @Override
    public void mostraDettagli() {
        System.out.println("-- Papillon --");
        super.mostraDettagli();
        System.out.println("Tipo chiusura: " + tipoChiusura);
    }
}

class Pochette extends ComponenteFinitura {
    private String piegaDecorativa;

    public Pochette(String codice, String nome, String materiale, String colore, double prezzo, String piegaDecorativa) {
        super(codice, nome, materiale, colore, prezzo);
        setPiegaDecorativa(piegaDecorativa);
    }

    public String getPiegaDecorativa() { return piegaDecorativa; }

    public void setPiegaDecorativa(String piegaDecorativa) {
        if (piegaDecorativa == null || piegaDecorativa.trim().isEmpty())
            throw new IllegalArgumentException("La piega decorativa non può essere vuota.");
        this.piegaDecorativa = piegaDecorativa.trim();
    }

    @Override
    public void mostraDettagli() {
        System.out.println("-- Pochette --");
        super.mostraDettagli();
        System.out.println("Piega decorativa: " + piegaDecorativa);
    }
}

// *** Classe Sartoria ***
class Sartoria {
    private ArrayList<CapoPrincipale> capi = new ArrayList<>();
    private ArrayList<ComponenteFinitura> accessori = new ArrayList<>();

    public void aggiungiCapo(CapoPrincipale c) { capi.add(c); }
    public void aggiungiAccessorio(ComponenteFinitura f) { accessori.add(f); }

    public void mostraCapi() {
        System.out.println("=== CAPI PRINCIPALI ===");
        for (CapoPrincipale c : capi) {
            c.mostraDettagli();
            System.out.println();
        }
    }

    public void mostraAccessori() {
        System.out.println("=== COMPONENTI FINITURA ===");
        for (ComponenteFinitura f : accessori) {
            f.mostraDettagli();
            System.out.println();
        }
    }

    public void mostraRiepilogo() {
        double totaleCapi = 0;
        for (CapoPrincipale c : capi) totaleCapi += c.getPrezzo();

        double totaleAccessori = 0;
        for (ComponenteFinitura f : accessori) totaleAccessori += f.getPrezzo();

        System.out.println("=== RIEPILOGO PREZZI ===");
        System.out.printf("Totale capi principali:     %.2f EUR%n", totaleCapi);
        System.out.printf("Totale componenti finitura: %.2f EUR%n", totaleAccessori);
        System.out.printf("Totale suite completa:      %.2f EUR%n", totaleCapi + totaleAccessori);
    }
}

// --- Main ---
public class EsercizioSuite {
    public static void main(String[] args) {

        Sartoria sartoria = new Sartoria();

        sartoria.aggiungiCapo(new Giacca("CP001", "Giacca Smoking", "Lana Merino", "Nero", "50", 480.00, 2));
        sartoria.aggiungiCapo(new Pantalone("CP002", "Pantalone Classico", "Flanella", "Antracite", "48", 220.00, "Taglio Italiano"));
        sartoria.aggiungiCapo(new Gilet("CP003", "Gilet Formale", "Seta", "Bordeaux", "50", 175.00, true));

        sartoria.aggiungiAccessorio(new Cravatta("CF001", "Cravatta Jacquard", "Seta", "Blu notte", 85.00, 7.5));
        sartoria.aggiungiAccessorio(new Papillon("CF002", "Papillon da Cerimonia", "Raso", "Nero", 55.00, "Nodo regolabile"));
        sartoria.aggiungiAccessorio(new Pochette("CF003", "Pochette da Taschino", "Lino", "Bianco", 35.00, "Piega a tre punte"));

        sartoria.mostraCapi();
        sartoria.mostraAccessori();
        sartoria.mostraRiepilogo();
    }
}