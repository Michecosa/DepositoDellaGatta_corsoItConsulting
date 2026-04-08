// base Decorator - la classe che "avvolge" una bevanda esistente
public abstract class CondimentoDecorator implements Bevanda {
    protected Bevanda bevandaReferenziata; // matrioska interna

    public CondimentoDecorator(Bevanda b) {
        this.bevandaReferenziata = b;
    }
}