package Herencias;

import Clases.Clientes;

public abstract class Envio {
    private String codigo;
    private String tipo;
    private Clientes clienteRemitente;
    private Clientes clienteDestinatario;
    private Double peso;

    public Envio(String codigo, String tipo, Double peso) {
        this.codigo = codigo;
        this.tipo = tipo;
        this.peso = peso;
        this.clienteRemitente = null;
        this.clienteDestinatario = null;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Clientes getClienteRemitente() {
        return clienteRemitente;
    }

    public void setClienteRemitente(Clientes clienteRemitente) {
        this.clienteRemitente = clienteRemitente;
    }

    public Clientes getClienteDestinatario() {
        return clienteDestinatario;
    }

    public void setClienteDestinatario(Clientes clienteDestinatario) {
        this.clienteDestinatario = clienteDestinatario;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    abstract Double valor();

    @Override
    public String toString() {
        return "Envio{" +
                "codigo='" + codigo + '\'' +
                ", tipo='" + tipo + '\'' +
                ", clienteRemitente=" + clienteRemitente +
                ", clienteDestinatario=" + clienteDestinatario +
                ", peso=" + peso +
                '}';
    }
}
