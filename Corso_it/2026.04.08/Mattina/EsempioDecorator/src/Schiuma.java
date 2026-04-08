// Concrete Decorator B
public class Schiuma extends CondimentoDecorator{
    public Schiuma(Bevanda b) {
        super(b);
    }

    public String getDescrizione() {
        return bevandaReferenziata.getDescrizione() + ", Schiuma";
    }

    public double getCosto() {
        return bevandaReferenziata.getCosto() + 0.30;
    }
}
