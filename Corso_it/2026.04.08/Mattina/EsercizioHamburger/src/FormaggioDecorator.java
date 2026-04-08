public class FormaggioDecorator extends HamburgerDecorator{
    public FormaggioDecorator(Hamburger h) {
        super(h);
    }

    public String getDescrizione() {
        return hamburgerReferenziato.getDescrizione()+", Formaggio";
    }

    public double getPrezzo() {
        return hamburgerReferenziato.getPrezzo() + 0.50;
    }
}
