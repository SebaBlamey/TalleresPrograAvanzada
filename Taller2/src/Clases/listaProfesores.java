package Clases;

import Herencia.Asignaturas;

import java.util.Arrays;

public class listaProfesores {
    private int max;
    private int cant;
    private Profesores[] lprofesores;

    public listaProfesores(int max) {
        this.max = max;
        cant = 0;
        lprofesores = new Profesores[max];
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

    public Profesores[] getLprofesores() {
        return lprofesores;
    }

    public void setLprofesores(Profesores[] lprofesores) {
        this.lprofesores = lprofesores;
    }

    public Profesores getProfesorX(int i){
        if(i<cant){
            return lprofesores[i];
        }
        else{
            return null;

        }
    }
    public boolean anadirProfesor(Profesores profesor){
        if(cant<max){
            lprofesores[cant] = profesor;
            cant++;
            return true;
        }else{
            return false;
        }
    }

    public Profesores buscarProfesor(String rut){
        for(int i = 0; i<cant;i++){
            if(lprofesores[i].getRut().equalsIgnoreCase(rut)){
                return lprofesores[i];
            }
        }return null;
    }
    public Profesores buscarProfesorCorreo(String correo){
        for(int i = 0; i<cant;i++){
            if(lprofesores[i].getCorreo().equalsIgnoreCase(correo)){
                return lprofesores[i];
            }
        }return null;
    }

    @Override
    public String toString() {
        return "listaProfesores{" +
                "max=" + max +
                ", cant=" + cant +
                ", lprofesores=" + Arrays.toString(lprofesores) +
                '}';
    }
}
