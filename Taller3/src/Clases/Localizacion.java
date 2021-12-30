package Clases;

import Herencias.Envio;

import java.util.LinkedList;

public class Localizacion {
    private String nombre;
    private int cantEnvios;
    private int cantRecibidos;
    private double ganancias;

    public Localizacion(String nombre) {
        this.nombre = nombre;
        this.cantEnvios = 0;
        this.cantRecibidos = 0;
        this.ganancias = 0.0;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantEnvios() {
        return cantEnvios;
    }

    public void setCantEnvios(int cantEnvios) {
        this.cantEnvios = cantEnvios;
    }

    public int getCantRecibidos() {
        return cantRecibidos;
    }

    public void setCantRecibidos(int cantRecibidos) {
        this.cantRecibidos = cantRecibidos;
    }

    public double getGanancias() {
        return ganancias;
    }

    public void setGanancias(double ganancias) {
        this.ganancias = ganancias;
    }

}
