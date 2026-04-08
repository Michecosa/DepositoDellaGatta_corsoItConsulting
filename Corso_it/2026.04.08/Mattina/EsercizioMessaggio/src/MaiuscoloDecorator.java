// Concrete Decorator
public class MaiuscoloDecorator extends MessaggioDecorator{
  public MaiuscoloDecorator(Messaggio m) {
    super(m);
  }

  public String getContenuto() {
    return messaggioReferenziato.getContenuto().toUpperCase();
  }
}
