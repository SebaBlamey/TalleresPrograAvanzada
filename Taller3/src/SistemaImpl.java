import Clases.Clientes;
import Clases.ListaEnvios;
import Clases.Localizacion;
import Herencias.EnvioD;
import Herencias.EnvioE;
import Herencias.EnvioV;

import java.util.ArrayList;
import java.util.LinkedList;

public class SistemaImpl implements Sistema {
    ArrayList<Localizacion> listaLocalizacion;
    LinkedList<Clientes> listaClientes;
    ListaEnvios listaEnvios;

    public SistemaImpl(){
        listaLocalizacion = new ArrayList<Localizacion>();
        listaClientes = new LinkedList<Clientes>();
        listaEnvios = new ListaEnvios();
    }


    @Override
    public void ingresarCliente(String rut, String nombre,String apellido, Double saldo, String localizacions) {
        Clientes c = new Clientes(rut,nombre,apellido,saldo,localizacions);
        for (Localizacion l : listaLocalizacion) {
            if (l.getNombre().equalsIgnoreCase(localizacions)) {
                c.setLoca(l);
            }
        }
        listaClientes.add(c);
    }

    @Override
    public void ingresarLocalizacion(String nombre) {
        Localizacion l = new Localizacion(nombre);
        listaLocalizacion.add(l);
    }

    @Override
    public boolean ingresarEnvioD(String codigo,String tipo, String rutRemitente, String rutDestinatario, Double peso, Double grosor) {
        boolean envioCompleto = false;
        double pesoKG = peso/1000;
        double grosorCM = grosor/10;
        Clientes remitente = null;
        Clientes destinatario = null;
        if(pesoKG <= 1.5 && grosorCM <= 5) {
            for (Clientes r : listaClientes) {
                if (r.getRut().equalsIgnoreCase(rutRemitente)) remitente = r;
                if (r.getRut().equalsIgnoreCase(rutDestinatario)) destinatario = r;
            }
            EnvioD ed = new EnvioD(codigo, tipo, pesoKG, grosorCM);
            if (remitente != null && destinatario != null) {
                ed.setClienteRemitente(remitente);
                ed.setClienteDestinatario(destinatario);
            } else {
                throw new NullPointerException();
            }
            envioCompleto = true;
        }
        return envioCompleto;
    }

    @Override
    public boolean ingresarEnvioE(String codigo, String tipo, String rutRemitente, String rutDestinatario,Double peso, Double largo, Double ancho, Double profundo) {
        boolean envioCompleto = false;
        double pesoKG = peso/1000;
        largo = largo/100;
        ancho = ancho/100;
        profundo = profundo/100;
        Clientes remitente = null;
        Clientes destinatario = null;
        if(peso < 50 && (largo <= 1.2 && ancho <= .8 &&  profundo <=.8)) {
            for (Clientes r : listaClientes) {
                if (r.getRut().equalsIgnoreCase(rutRemitente)) remitente = r;
                if (r.getRut().equalsIgnoreCase(rutDestinatario)) destinatario = r;
            }
            EnvioE ee = new EnvioE(codigo, tipo, pesoKG, largo, ancho, profundo);
            if (remitente != null && destinatario != null) {
                ee.setClienteRemitente(remitente);
                ee.setClienteDestinatario(destinatario);
            } else {
                throw new NullPointerException();
            }
            envioCompleto = true;
        }
        return envioCompleto;
    }

    @Override
    public boolean ingresarEnvioV(String codigo, String tipo, String rutRemitente, String rutDestinatario,Double peso, String material) {
        boolean envioCompleto = false;
        Clientes remitente = null;
        Clientes destinatario = null;
        if(peso <= 2) {
            for (Clientes r : listaClientes) {
                if (r.getRut().equalsIgnoreCase(rutRemitente)) remitente = r;
                if (r.getRut().equalsIgnoreCase(rutDestinatario)) destinatario = r;
            }
            EnvioV ev = new EnvioV(codigo, tipo, peso, material);
            if (remitente != null && destinatario != null) {
                ev.setClienteRemitente(remitente);
                ev.setClienteDestinatario(destinatario);
            } else {
                throw new NullPointerException();
            }
            envioCompleto = true;
        }
        return envioCompleto;
    }

    @Override
    public int inicioSesion(String rut, String contrasena) {
        if(rut.equalsIgnoreCase("Admin") && contrasena.equals("Choripan123")) return 1;

        Clientes cliente = null;
        for (Clientes c : listaClientes) {
            if (c.getRut().equalsIgnoreCase(rut)) cliente = c;
        }
        if(cliente != null){
            return 2;
        }else{
            throw  new NullPointerException();
        }

    }

    @Override
    public boolean registro(String rut, String nombre, String apellido, Double saldo, String localizacions) {
        boolean rutExsite = false;
        for(Clientes cr : listaClientes){
            if (cr.getRut().equalsIgnoreCase(rut)) {
                rutExsite = true;
                break;
            }
        }
        if(!rutExsite) {
            Clientes c = new Clientes(rut, nombre, apellido, saldo, localizacions);
            for (Localizacion l : listaLocalizacion) {
                if (l.getNombre().equalsIgnoreCase(localizacions)) {
                    c.setLoca(l);
                }
            }
            listaClientes.add(c);
        }
        return rutExsite;
    }

    @Override
    public boolean verificarDatos(String tipo) {
        return false;
    }

    @Override
    public boolean realizarEntrega(String tipo,String rutRemitente, String rutDestinatario, Double total) {
        Clientes remitente = null;
        Clientes destinatario = null;
        for (Clientes c : listaClientes) {
            if (c.getRut().equalsIgnoreCase(rutRemitente)) remitente = c;
            else if (c.getRut().equalsIgnoreCase(rutDestinatario)) destinatario = c;
        }
        if(remitente != null && destinatario != null){
            return remitente.getSaldo() >= total;
        }
        return false;
    }

    @Override
    public boolean recargarSaldo(String rut,Double montoSumado) {
        for (Clientes r : listaClientes) {
            if(r.getRut().equalsIgnoreCase(rut)){
                r.setSaldo(r.getSaldo() + montoSumado);
                return true;
            }
        }
        return false;
    }

    @Override
    public String desplegarEntregas(String rut) {
        String texto = "", entregas = "", recibidos = "";
        Clientes micliente = null;
        for (Clientes r : listaClientes) {
            if(r.getRut().equalsIgnoreCase(rut)) micliente = r;
        }
        if(micliente != null){
            entregas = micliente.getListaEnviosE().toString();
            recibidos = micliente.getListaEnviosR().toString();
            texto += "Entregas: \n"+entregas+"\n===========================================" +
                    "\nRecibidos: \n"+recibidos;
        }else{
            throw new NullPointerException();
        }

        return texto;
    }

    @Override
    public String entregasPorTipo() {

        return null;
    }

    @Override
    public String entregasPorLocalizacion() {
        return null;
    }

    @Override
    public String registroGanancias() {
        return null;
    }

}
