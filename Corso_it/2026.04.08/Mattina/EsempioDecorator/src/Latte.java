// Concrete Decorator A
public class Latte extends CondimentoDecorator{
    public Latte(Bevanda b) {
        super(b);
    }

    public String getDescrizione() {
        return bevandaReferenziata.getDescrizione() + ", Latte";
    }

    public double getCosto() {
        return bevandaReferenziata.getCosto() + 0.50;
    }
}
