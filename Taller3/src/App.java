import java.io.*;

public class App {
    private static String windowsSeba="D:/Programacion/Java/Universidad/TalleresPrograAvanzada/Taller3/";
    public static void main(String[] args) throws IOException {
        Sistema sistema = new SistemaImpl();
        leerCliente(sistema);
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
}
