package Clases;

import Herencia.Asignaturas;

import java.util.Arrays;

public class listaAsignaturasI {
    private int cant;
    private int max;
    public AsignaturasInscritas[] lai;

    public listaAsignaturasI(int max) {
        this.max = max;
        cant = 0;
        lai = new AsignaturasInscritas[max];
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

    public AsignaturasInscritas[] getLai() {
        return lai;
    }

    public void setLai(AsignaturasInscritas[] lai) {
        this.lai = lai;
    }

    public AsignaturasInscritas getAsignaturasIX(int i){
        if(i<cant){
            return lai[i];
        }
        else{
            return null;

        }
    }
    public boolean anadirAsignaturaI(AsignaturasInscritas ai){
        if(cant<max){
            lai[cant] = ai;
            cant++;
            return true;
        }else{
            return false;
        }
    }
    public AsignaturasInscritas buscarAsignaturaI(String codigo){
        for(int i = 0; i<cant;i++){
            if(lai[i].getCodigo().equalsIgnoreCase(codigo)){
                return lai[i];
            }
        }return null;
    }

    public boolean borrarAsignaturaI(String codigo){
        int i = 0;
        for(int x = 0; x < cant ; x++){
            if(lai[x].getCodigo().equals(codigo)){
                i = x;
                break;
            }
        }
        if(i == cant){
            return false;
        }
        else{
            lai[i] = null;
            for(int j = i ; i < cant - 1 ; j++){
                lai[j] = lai[j + 1];
            }
            cant--;
            return true;
        }
    }

    @Override
    public String toString() {
        return "listaAsignaturasI{" +
                "cant=" + cant +
                ", max=" + max +
                ", lai=" + Arrays.toString(lai) +
                '}';
    }
}
