import javax.swing.JOptionPane;

public class GestioneOre {
    public static void main(String[] args) {
        // 1. Input del Nome (come un prompt JS)
        String nome = JOptionPane.showInputDialog(null, "Inserisci il nome del dipendente:", "Controllo Accessi", JOptionPane.QUESTION_MESSAGE);

        if (nome == null || nome.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Operazione annullata o nome vuoto.", "Errore", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 2. Selezione Progetto (Menu a tendina "finto" con testo)
        String progetto = JOptionPane.showInputDialog("Su quale progetto hai lavorato?\n(Sviluppo, Test, Documentazione)");

        // 3. Input Ore con validazione
        String oreStr = JOptionPane.showInputDialog("Quante ore hai lavorato oggi?");
        
        try {
            double ore = Double.parseDouble(oreStr);

            if (ore <= 0 || ore > 12) {
                // Messaggio di avviso (Warning)
                JOptionPane.showMessageDialog(null, "Attenzione: inserite " + ore + " ore. Un turno non può superare le 12 ore.", "Controllo Ore", JOptionPane.WARNING_MESSAGE);
            } else {
                // Conferma finale (Information)
                String riepilogo = String.format("Registrazione completata!\nUtente: %s\nProgetto: %s\nOre: %.1f", nome, progetto, ore);
                
                JOptionPane.showMessageDialog(null, riepilogo, "Successo", JOptionPane.INFORMATION_MESSAGE);
            }
            
        } catch (NumberFormatException e) {
            // Se l'utente scrive "otto" invece di 8
            JOptionPane.showMessageDialog(null, "Errore: Inserisci un numero valido per le ore!", "Errore Formato", JOptionPane.ERROR_MESSAGE);
        }
    }
}