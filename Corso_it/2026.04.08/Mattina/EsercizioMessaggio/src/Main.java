public class Main {
    public static void main(String[] args) {
        Messaggio mioMessaggio = new MessaggioBase();
        System.out.println("[PRIMA] "+mioMessaggio.getContenuto()+"\n");

        System.out.println("\t|\n\tv\n");
        
        // +Decorator
        mioMessaggio = new MaiuscoloDecorator(mioMessaggio);
        System.out.println("[DOPO] "+mioMessaggio.getContenuto()+"\n");
    }
}
