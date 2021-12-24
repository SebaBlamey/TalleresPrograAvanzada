package Clases;

public class Clientes {
    private String rut;
    private String nombre;
    private String apellido;
    private double saldo;
    private String localizacion;
    private Localizacion loca;
    private ListaEnvios listaEnviosR;
    private ListaEnvios listaEnviosE;

    public Clientes(String rut, String nombre, String apellido, double saldo, String localizacion) {
        this.rut = rut;
        this.nombre = nombre;
        this.apellido = apellido;
        this.saldo = saldo;
        this.localizacion = localizacion;
        loca = null;
        listaEnviosR = null;
        listaEnviosE = null;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    public Localizacion getLoca() {
        return loca;
    }

    public void setLoca(Localizacion loca) {
        this.loca = loca;
    }

    public ListaEnvios getListaEnviosR() {
        return listaEnviosR;
    }

    public void setListaEnviosR(ListaEnvios listaEnviosR) {
        this.listaEnviosR = listaEnviosR;
    }

    public ListaEnvios getListaEnviosE() {
        return listaEnviosE;
    }

    public void setListaEnviosE(ListaEnvios listaEnviosE) {
        this.listaEnviosE = listaEnviosE;
    }

    @Override
    public String toString() {
        return "Clientes{" +
                "rut='" + rut + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", saldo=" + saldo +
                ", localizacion='" + localizacion + '\'' +
                ", loca=" + loca +
                ", listaEnviosR=" + listaEnviosR +
                ", listaEnviosE=" + listaEnviosE +
                '}';
    }
}
