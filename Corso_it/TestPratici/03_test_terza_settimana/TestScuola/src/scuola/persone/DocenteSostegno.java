package scuola.persone;

public class DocenteSostegno extends Docente {
    private String tipoSupporto;
 
    public DocenteSostegno(String nome, int eta, String materia, String tipoSupporto) {
        super(nome, eta, materia);
        this.tipoSupporto = tipoSupporto;
    }
 
    public String getTipoSupporto() { return tipoSupporto; }
 
    @Override
    public void descriviRuolo() {
        System.out.println("Sono un docente di sostegno (" + tipoSupporto + ") per la materia " + getMateria());
    }
}