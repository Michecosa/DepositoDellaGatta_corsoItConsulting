// Base Decorator
public abstract class MessaggioDecorator implements Messaggio{
  protected Messaggio messaggioReferenziato;

  public MessaggioDecorator(Messaggio m) {
    this.messaggioReferenziato = m;
  }
}
