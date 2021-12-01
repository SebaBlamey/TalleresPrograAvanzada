package Clases;

import java.util.Arrays;

public class listaParalelo {
    private int max;
    private int cant;
    private Paralelo[] lp;

    public listaParalelo(int max) {
        this.max = max;
        cant = 0;
        lp = new Paralelo[max];
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getCant() {
        return cant;
    }

    public void setCant(int cant) {
        this.cant = cant;
    }

    public Paralelo[] getLp() {
        return lp;
    }

    public void setLp(Paralelo[] lp) {
        this.lp = lp;
    }

    public Paralelo getParaleloX(int i){
        if(i<cant){
            return lp[i];
        }
        else{
            return null;

        }
    }
    public Paralelo buscarParaleloCodigo(String codigo){
        for(int i = 0; i<cant;i++){
            if(lp[i].getAsignaturas().getCodigoAsignatura().equalsIgnoreCase(codigo)){
                return lp[i];
            }
        }return null;
    }

    public Paralelo buscarParaleloProfesor(String correo){
        for(int i = 0; i<cant;i++){
            if(lp[i].getProfesor().getCorreo().equalsIgnoreCase(correo)){
                return lp[i];
            }
        }return null;
    }

    public boolean anadirParalelo(Paralelo paralelo){
        if(cant<max){
            lp[cant] = paralelo;
            cant++;
            return true;
        }else{
            return false;
        }
    }

    @Override
    public String toString() {
        return "listaParalelo{" +
                "max=" + max +
                ", cant=" + cant +
                ", lp=" + Arrays.toString(lp) +
                '}';
    }
}

