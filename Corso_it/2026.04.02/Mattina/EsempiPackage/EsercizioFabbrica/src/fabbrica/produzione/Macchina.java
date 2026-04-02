package fabbrica.produzione;

public class Macchina {
    private String nome;
    private boolean accesa;

    public Macchina(String nome) {
        this.nome = nome;
        this.accesa = false;
    }

    public void accendi() {
        this.accesa = true;
        System.out.println("Macchina " + nome + " ACCESA");
    }

    public void spegni() {
        this.accesa = false;
        System.out.println("Macchina " + nome + " SPENTA");
    }

    public void creaProdotto(String tipo) {
        if (accesa) {
            System.out.println("Produzione avviata: Creato prodotto della famiglia " + tipo);
        } else {
            System.out.println("[ERRORE] La macchina è spenta. Impossibile produrre");
        }
    }

    public void stampaStato() {
        String stato = accesa ? "ATTIVA" : "NON ATTIVA";
        System.out.println("[STATO MACCHINA] " + nome + ": " + stato);
    }
}