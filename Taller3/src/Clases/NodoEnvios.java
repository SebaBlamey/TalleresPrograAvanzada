package Clases;

import Herencias.Envio;

public class NodoEnvios {
    private Envio envio;
    private NodoEnvios next;
    private NodoEnvios prev;

    public NodoEnvios(Envio envio) {
        this.envio = envio;
        this.next = null;
        this.prev = null;
    }

    public Envio getEnvio() {
        return envio;
    }

    public void setEnvio(Envio envio) {
        this.envio = envio;
    }

    public NodoEnvios getNext() {
        return next;
    }

    public void setNext(NodoEnvios next) {
        this.next = next;
    }

    public NodoEnvios getPrev() {
        return prev;
    }

    public void setPrev(NodoEnvios prev) {
        this.prev = prev;
    }

    @Override
    public String toString() {
        return "NodoEnvios{" +
                "envio=" + envio +
                ", next=" + next +
                ", prev=" + prev +
                '}';
    }
}
