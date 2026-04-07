import javax.swing.JOptionPane; // La libreria per le finestre

public class SalutoGrafico {
    public static void main(String[] args) {
        // Al posto di prompt()
        String nome = JOptionPane.showInputDialog("Come ti chiami?");
        
        // Al posto di alert()
        JOptionPane.showMessageDialog(null, "Ciao " + nome + "!");
    }
}