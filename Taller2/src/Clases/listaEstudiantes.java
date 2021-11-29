package Clases;

import java.util.Arrays;

public class listaEstudiantes {
    private int max;
    private int cant;
    private Estudiante[] le;

    public listaEstudiantes(int max) {
        this.max = max;
        cant = 0;
        le = new Estudiante[max];
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

    public Estudiante[] getLe() {
        return le;
    }

    public void setLe(Estudiante[] le) {
        this.le = le;
    }

    public Estudiante getEstudianteX(int i){
        if(i<cant){
            return le[i];
        }
        else{
            return null;

        }
    }
    public boolean anadirEstudiante(Estudiante estudiante){
        if(cant<max){
            le[cant] = estudiante;
            cant++;
            return true;
        }else{
            return false;
        }
    }
    public Estudiante buscarEstudiante(String rut){
        for(int i = 0; i<cant;i++){
            if(le[i].getRut().equalsIgnoreCase(rut)){
                return le[i];
            }
        }return null;
    }
    public Estudiante buscarEstudianteCorreo(String correo){
        for(int i = 0; i<cant;i++){
            if(le[i].getCorreo().equalsIgnoreCase(correo)){
                return le[i];
            }
        }return null;
    }

    public boolean borrarEstudiante(String rutEstudiante){
        int i = 0;
        for(int x = 0; x < cant ; x++){
            if(le[x].getRut().equals(rutEstudiante)){
                i = x;
                break;
            }
        }
        if(i == cant){
            return false;
        }
        else{
            le[i] = null;
            for(int j = i ; i < cant - 1 ; j++){
                le[j] = le[j + 1];
            }
            cant++;
            return true;
        }
    }

    @Override
    public String toString() {
        return "listaEstudiantes{" +
                "max=" + max +
                ", cant=" + cant +
                ", le=" + Arrays.toString(le) +
                '}';
    }
}
