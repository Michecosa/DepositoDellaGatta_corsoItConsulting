package BarApp.decorator;

public class Zucchero extends IngedienteDecorator {
    public Zucchero(Bevanda b) {
        super(b);
    }

    public String getDescrizione() {
        return bevandaReferenziata.getDescrizione() + " + Zucchero";
    }

    public double getCosto() {
        return bevandaReferenziata.getCosto() + 0.10;
    }
}
