package BarApp.decorator;
public abstract class IngedienteDecorator implements Bevanda {
    protected Bevanda bevandaReferenziata;

    public IngedienteDecorator(Bevanda b) {
        this.bevandaReferenziata = b;
    }
}
