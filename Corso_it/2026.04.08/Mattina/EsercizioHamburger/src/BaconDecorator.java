public class BaconDecorator extends HamburgerDecorator{
    public BaconDecorator(Hamburger h) {
        super(h);
    }

    public String getDescrizione() {
        return hamburgerReferenziato.getDescrizione()+", Bacon";
    }

    public double getPrezzo() {
        return hamburgerReferenziato.getPrezzo() + 0.50;
    }
}
