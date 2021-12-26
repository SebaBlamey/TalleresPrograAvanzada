import java.io.*;

public class App {
    private static String windowsSeba="E:/Programacion/TalleresPrograAvanzada/Taller3/";
    public static void main(String[] args) throws IOException {
        Sistema sistema = new SistemaImpl();
        leerLocalizacion(sistema);
        leerCliente(sistema);
        leerEntregas(sistema);
        System.out.println(sistema.desplegarEntregas("1.111.111-1"));
    }

    private static void leerCliente(Sistema sistema) throws IOException {
        BufferedReader buffer = new BufferedReader(new InputStreamReader
                (new FileInputStream(windowsSeba+"Cliente.txt"),"utf-8"));
        String linea;
        while ((linea = buffer.readLine()) != null){
            String[] partes = linea.split(",");
            String rut = partes[0];
            String nombre = partes[1];
            String apellido = partes[2];
            Double saldo = Double.parseDouble(partes[3]);
            String ciudad = partes[4];
            sistema.ingresarCliente(rut,nombre,apellido,saldo,ciudad);
        }
    }
    private static void leerEntregas(Sistema sistema) throws IOException{
        BufferedReader buffer = new BufferedReader(new InputStreamReader
                (new FileInputStream(windowsSeba+"Entregas.txt"),"utf-8"));
        String linea;
        while((linea = buffer.readLine()) != null){
            String[] partes = linea.split(",");
            String codigo = partes[0];
            String tipo = partes[1];
            String rutRemitente = partes[2];
            String rutDestinatario = partes[3];
            if(tipo.equalsIgnoreCase("D")){
                Double peso = Double.parseDouble(partes[4]);
                Double grosor = Double.parseDouble(partes[5]);
                sistema.ingresarEnvioD(codigo,tipo,rutRemitente,rutDestinatario,peso,grosor);
            }
            else if(tipo.equalsIgnoreCase("E")){
                Double peso = Double.parseDouble(partes[4]);
                Double largo = Double.parseDouble(partes[5]);
                Double ancho = Double.parseDouble(partes[6]);
                Double profundidad = Double.parseDouble(partes[7]);
                sistema.ingresarEnvioE(codigo,tipo,rutRemitente,rutDestinatario,peso,largo,ancho,profundidad);
            }
            else if(tipo.equalsIgnoreCase("V")){
                String material = partes[4];
                Double peso = Double.parseDouble(partes[5]);
                sistema.ingresarEnvioV(codigo,tipo,rutRemitente,rutDestinatario,peso,material);
            }
        }
    }
    private static void leerLocalizacion(Sistema sistema) throws IOException{
        BufferedReader buffer = new BufferedReader(new InputStreamReader
                (new FileInputStream(windowsSeba+"localizacion.txt"),"utf-8"));
        String linea;
        while ((linea = buffer.readLine()) != null){
            sistema.ingresarLocalizacion(linea);
        }
    }
}
