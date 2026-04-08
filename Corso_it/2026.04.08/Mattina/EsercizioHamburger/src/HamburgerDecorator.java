public abstract class HamburgerDecorator implements Hamburger{
    protected Hamburger hamburgerReferenziato;

    public HamburgerDecorator(Hamburger h) {
        this.hamburgerReferenziato = h;
    }
}
