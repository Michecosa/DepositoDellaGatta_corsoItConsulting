public class Main {
    public static void main(String[] args) {
        // 1. Caffè semplice
        Bevanda mioCaffe = new Caffe();
        System.out.println(mioCaffe.getDescrizione()+" - "+mioCaffe.getCosto()+" EUR\n");
        
        // 2. +Latte 
        mioCaffe = new Latte(mioCaffe);
        System.out.println(mioCaffe.getDescrizione()+" - "+mioCaffe.getCosto()+" EUR\n");
        
        // 3. +Schiuma
        mioCaffe = new Schiuma(mioCaffe);
        System.out.println(mioCaffe.getDescrizione()+" - "+mioCaffe.getCosto()+" EUR\n");

        System.out.println("Ordine finale: "+mioCaffe.getDescrizione());
        System.out.println("Totale da pagare: "+mioCaffe.getCosto()+" EUR\n");
    }
}
