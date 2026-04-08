package BarApp.decorator;
public class Panna extends IngedienteDecorator {
  public Panna(Bevanda b) {
    super(b);
  }

  public String getDescrizione() {
    return bevandaReferenziata.getDescrizione() + " + Panna";
  }

  public double getCosto() {
    return bevandaReferenziata.getCosto() + 0.80;
  }
}
