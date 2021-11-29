package Clases;

import java.util.Arrays;

public class listaAsignaturasC {
    private int cant;
    private int max;
    public AsignaturasCursadas[] lac;

    public listaAsignaturasC(int max) {
        this.max = max;
        cant = 0;
        lac = new AsignaturasCursadas[max];
    }

    public int getCant() {
        return cant;
    }

    public void setCant(int cant) {
        this.cant = cant;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public AsignaturasCursadas[] getLac() {
        return lac;
    }

    public void setLac(AsignaturasCursadas[] lac) {
        this.lac = lac;
    }

    public AsignaturasCursadas getAsignaturasCX(int i){
        if(i<cant){
            cant++;
            return lac[i];
        }
        else{
            return null;
        }
    }
    public boolean anadirAsignaturaC(AsignaturasCursadas ac){
        //System.out.println(cant);
        if(cant<max){
            lac[cant] = ac;
            cant++;
            return true;
        }else{
            return false;
        }
    }

    @Override
    public String toString() {
        return "listaAsignaturasC{" +
                "cant=" + cant +
                ", max=" + max +
                ", lac=" + Arrays.toString(lac) +
                '}';
    }
}
