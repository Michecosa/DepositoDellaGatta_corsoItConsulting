package BarApp.decorator;
public class Cannella extends IngedienteDecorator {
    public Cannella(Bevanda b) {
        super(b);
    }

    public String getDescrizione() {
        return bevandaReferenziata.getDescrizione()+" + Cannella";
    }

    public double getCosto() {
        return bevandaReferenziata.getCosto()+0.30;
    }
}
