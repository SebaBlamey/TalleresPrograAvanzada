package Clases;

import Herencias.Envio;

public class ListaEnvios {
    private NodoEnvios first;

    public ListaEnvios() {
        this.first = null;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void ingresarEnvio(Envio v){
        NodoEnvios e = new NodoEnvios(v);
        if(isEmpty()){
            first = e;
            first.setNext(e);
        }
        else{
            NodoEnvios aux = first;
            while(aux.getNext() != first){
                aux = aux.getNext();
            }
            aux.setNext(e);
            e.setPrev(aux);
            e.setNext(first);
            first.setPrev(e);
        }
    }
    public Envio buscarEnvio(String codigo){
        NodoEnvios aux = first;
        do{
            aux = aux.getNext();
        }while (aux != first && (!(aux.getEnvio().getCodigo().equalsIgnoreCase(codigo))));
        return aux.getEnvio();
    }

    public boolean eliminarEnvio(String codigo){
        if(isEmpty()){
            return false;
        }
        else{
            String dato = codigo;
            NodoEnvios aux = first;
            while (aux.getNext() != first && !aux.getEnvio().getCodigo().equalsIgnoreCase(dato)) {
                aux = aux.getNext();
            }
            if (aux.getEnvio().getCodigo().equalsIgnoreCase(dato)) {
                if (aux.getNext() == first) {
                    aux.getPrev().setNext(first);
                    first.setPrev(aux.getPrev());
                    return true;
                }
                else {
                    if (aux == first) {
                        first.getPrev().setNext(first.getNext());
                        first.getNext().setPrev(first.getPrev());
                        first = first.getNext();
                    }
                    else {
                        aux.getPrev().setNext(aux.getNext());
                        aux.getNext().setPrev(aux.getPrev());
                    }
                    return true;

                }

            }
            else {
                return false;
            }
        }
    }

    @Override
    public String toString() {
        return "ListaEnvios{" +
                "first=" + first +
                '}';
    }
}
