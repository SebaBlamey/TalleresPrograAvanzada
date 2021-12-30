package Herencias;

public class EnvioE extends Envio{
    private Double largo;
    private Double ancho;
    private Double profundidad;

    public EnvioE(String codigo, String tipo, Double peso, Double largo, Double ancho, Double profundidad) {
        super(codigo, tipo, peso);
        this.largo = largo;
        this.ancho = ancho;
        this.profundidad = profundidad;
    }

    public Double getLargo() {
        return largo;
    }

    public void setLargo(Double largo) {
        this.largo = largo;
    }

    public Double getAncho() {
        return ancho;
    }

    public void setAncho(Double ancho) {
        this.ancho = ancho;
    }

    public Double getProfundidad() {
        return profundidad;
    }

    public void setProfundidad(Double profundidad) {
        this.profundidad = profundidad;
    }


    @Override
    public Double valor() {
        return (double) getPeso()*(getLargo()*getAncho()*getProfundidad())*50;
    }
}
