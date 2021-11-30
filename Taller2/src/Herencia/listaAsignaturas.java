package Herencia;

import Clases.Estudiante;
import Clases.Paralelo;

import java.util.Arrays;

public class listaAsignaturas {
    private int max;
    private int cant;
    protected Asignaturas[] la;

    public listaAsignaturas(int max) {
        this.max = max;
        cant = 0;
        la = new Asignaturas[max];
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

    public Asignaturas[] getLa() {
        return la;
    }

    public void setLa(Asignaturas[] la) {
        this.la = la;
    }

    public boolean anadirAsignatura(Asignaturas asignatura){
        if(cant<max){
            la[cant] = asignatura;
            cant++;
            return true;
        }else{
            return false;
        }
    }

    public Asignaturas buscarAsignatura(String codigo){
        for(int i = 0; i<cant;i++){
            if(la[i].getCodigoAsignatura().equalsIgnoreCase(codigo)){
                return la[i];
            }
        }return null;
    }

    public Asignaturas getAsignaturasX(int i){
        if(i<cant){
            return la[i];
        }
        else{
            return null;

        }
    }

    @Override
    public String toString() {
        return "listaAsignaturas{" +
                "max=" + max +
                ", cant=" + cant +
                ", la=" + Arrays.toString(la) +
                '}';
    }
}
