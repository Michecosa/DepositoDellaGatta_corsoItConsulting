import java.util.ArrayList;
import java.util.Scanner;

// INTERFACCIA Tracciabile
interface Tracciabile {
    void tracciaConsegna(String codiceTracking);
}

// CLASSE ASTRATTA VeicoloConsegna
abstract class VeicoloConsegna {
    protected String targa;
    protected float caricoMassimo;

    public VeicoloConsegna(String targa, float caricoMassimo) {
        this.targa = targa;
        this.caricoMassimo = caricoMassimo;
    }

    public abstract void consegnaPacco(String destinazione, float pesoPacco);

    public void stampaInfo() {
        System.out.println("----------------------------------");
        System.out.println("Tipo veicolo : " + this.getClass().getSimpleName());
        System.out.println("Targa        : " + targa);
        System.out.println("Carico max   : " + caricoMassimo + " kg");
        System.out.println("----------------------------------");
    }

    public String getTarga() { return targa; }
}

// CLASSE CONCRETA Furgone
class Furgone extends VeicoloConsegna implements Tracciabile {
    public Furgone(String targa, float caricoMassimo) {
        super(targa, caricoMassimo);
    }

    @Override
    public void consegnaPacco(String destinazione, float pesoPacco) {
        if (pesoPacco > caricoMassimo) {
            System.out.println("[ERRORE] Carico eccessivo per il furgone " + targa);
            return;
        }
        System.out.println("[FURGONE] Consegna su strada a: " + destinazione);
    }

    @Override
    public void tracciaConsegna(String codiceTracking) {
        System.out.println("[TRACKING] " + codiceTracking + " | Veicolo: " + targa + " (Su strada)");
    }
}

// CLASSE CONCRETA Drone
class Drone extends VeicoloConsegna implements Tracciabile {
    private int altitudineMetri;

    public Drone(String targa, float caricoMassimo, int altitudineMetri) {
        super(targa, caricoMassimo);
        this.altitudineMetri = altitudineMetri;
    }

    @Override
    public void consegnaPacco(String destinazione, float pesoPacco) {
        if (pesoPacco > caricoMassimo) {
            System.out.println("[ERRORE] Il drone non può sollevare questo peso!");
            return;
        }
        System.out.println("[DRONE] Volo a " + altitudineMetri + "m verso: " + destinazione);
    }

    @Override
    public void tracciaConsegna(String codiceTracking) {
        System.out.println("[TRACKING] " + codiceTracking + " | Drone: " + targa + " | Alt: " + altitudineMetri + "m");
    }

    @Override
    public void stampaInfo() {
        super.stampaInfo();
        System.out.println("Altitudine   : " + altitudineMetri + " m");
        System.out.println("----------------------------------");
    }
}

// CLASSE PRINCIPALE
public class EsercizioDelivery {

    private static ArrayList<VeicoloConsegna> flotta = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            mostraMenu();
            String scelta = scanner.nextLine();

            switch (scelta) {
                case "1" -> aggiungiVeicolo();
                case "2" -> mostraFlotta();
                case "3" -> effettuaConsegna();
                case "4" -> tracciaSpedizione();
                case "0" -> running = false;
                default -> System.out.println("Scelta non valida");
            }
        }
    }

    private static void mostraMenu() {
        System.out.println("\n--- GESTIONE DELIVERY ---\n1. Aggiungi\n2. Lista\n3. Consegna\n4. Traccia\n0. Esci");
        System.out.print("Scegli: ");
    }

    private static void aggiungiVeicolo() {
        System.out.print("Tipo (1-Furgone, 2-Drone): ");
        String tipo = scanner.nextLine();
        System.out.print("Targa: ");
        String targa = scanner.nextLine();
        System.out.print("Carico Max: ");
        float carico = Float.parseFloat(scanner.nextLine());

        if (tipo.equals("1")) {
            flotta.add(new Furgone(targa, carico));
        } else {
            System.out.print("Altitudine: ");
            int alt = Integer.parseInt(scanner.nextLine());
            flotta.add(new Drone(targa, carico, alt));
        }
    }

    private static void mostraFlotta() {
        for (VeicoloConsegna v : flotta) {
            v.stampaInfo();
        }
    }

    private static void effettuaConsegna() {
        System.out.print("Indice veicolo: ");
        int idx = Integer.parseInt(scanner.nextLine());
        System.out.print("Destinazione: ");
        String dest = scanner.nextLine();
        System.out.print("Peso: ");
        float peso = Float.parseFloat(scanner.nextLine());
        
        flotta.get(idx).consegnaPacco(dest, peso);
    }

    private static void tracciaSpedizione() {
        System.out.print("Indice veicolo: ");
        int idx = Integer.parseInt(scanner.nextLine());
        VeicoloConsegna v = flotta.get(idx);

        if (v instanceof Tracciabile t) {
            System.out.print("Codice tracking: ");
            String codice = scanner.nextLine();
            t.tracciaConsegna(codice);
        } else {
            System.out.println("Veicolo non tracciabile");
        }
    }
}