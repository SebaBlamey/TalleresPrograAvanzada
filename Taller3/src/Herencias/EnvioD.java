package Herencias;

public class EnvioD extends Envio{
    private Double grosor;

    public EnvioD(String codigo, String tipo, Double peso, Double grosor) {
        super(codigo, tipo, peso);
        this.grosor = grosor;
    }

    public Double getGrosor() {
        return grosor;
    }

    public void setGrosor(Double grosor) {
        this.grosor = grosor;
    }



    @Override
    Double valor() {
        return (double) getPeso() * getGrosor() *100;
    }
}
