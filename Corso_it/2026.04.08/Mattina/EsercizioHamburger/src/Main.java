public class Main {
    public static void main(String[] args) {
        System.out.print("\nNUOVO ORDINE IN ARRIVO: ");
        Hamburger mioHamburger = new BaseBurger();
        System.out.println(mioHamburger.getDescrizione() +" ("+mioHamburger.getPrezzo()+" EUR)");

        mioHamburger = new FormaggioDecorator(mioHamburger);
        System.out.println("\n[AGGIORNAMENTO ORDINE] "+mioHamburger.getDescrizione());
        
        mioHamburger = new BaconDecorator(mioHamburger);
        System.out.println("[AGGIORNAMENTO ORDINE] "+mioHamburger.getDescrizione());

        System.out.println("\n------------------------------");
        System.out.println("\t   RICEVUTA");
        System.out.println("------------------------------");
        System.out.println(mioHamburger.getDescrizione());
        System.out.println("\nTOTALE: " + String.format("%.2f", mioHamburger.getPrezzo()) + " EUR");
        System.out.println("------------------------------\n");
    }
}
