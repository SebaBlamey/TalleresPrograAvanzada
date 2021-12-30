import java.io.*;
import java.util.Scanner;

public class App {
    private static String windowsSeba="D:/Programacion/Java/Universidad/TalleresPrograAvanzada/Taller3/";

    /**
     * Main function;
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        Sistema sistema = new SistemaImpl();
        leerLocalizacion(sistema);
        leerCliente(sistema);
        leerEntregas(sistema);
        menu(sistema);
    }

    /**
     * Function that displays the main menu
     * @param sistema
     */
    public static void menu(Sistema sistema){
        Scanner entrada = new Scanner(System.in);
        Scanner entrada2 = new Scanner(System.in);
        System.out.println("[1] Iniciar Sesion.\n[2] Registro.\n[3] Salir");
        System.out.print("Ingrese su opcion: ");
        int opcion = entrada.nextInt();
        while (opcion != 3) {
            switch (opcion) {
                case 1 -> {
                    System.out.print("Ingrese su RUT: ");
                    String rut = entrada2.nextLine();
                    String contrasean;
                    if (rut.equalsIgnoreCase("Admin")) {
                        System.out.print("Ingrese su contrasena: ");
                        contrasean = entrada2.nextLine();
                    } else {
                        contrasean = "";
                    }
                    int opc = sistema.inicioSesion(rut, contrasean);
                    switch (opc) {
                        case 1: //ADMIN
                            menuAdmin(sistema);
                            break;
                        case 2: // CLIENTE
                            menuCliente(sistema, rut);
                            break;
                        default:
                            System.out.println("Usuario invalido");
                    }
                }
                case 2 -> {
                    System.out.print("Ingrese su rut: ");
                    String rut2 = entrada2.nextLine();
                    System.out.print("Ingrese su nombre: ");
                    String nombre = entrada2.nextLine();
                    System.out.print("Ingrese su apellido: ");
                    String apellido = entrada2.nextLine();
                    System.out.print("Ingrese su localizacion: ");
                    String localizacion = entrada2.nextLine();
                    if (!sistema.registro(rut2, nombre, apellido, 0.0, localizacion))
                        System.out.println("Registrado con exito");
                    else System.out.println("Error al registrar");
                }
            }
            System.out.println("[1] Iniciar Sesion.\n[2] Registro.\n[3] Salir");
            System.out.print("Ingrese su opcion: ");
            opcion = entrada.nextInt();
        }
    }

    /**
     * Function that shows the administrator menu
     * @param sistema
     */
    public static void menuAdmin(Sistema sistema){
        Scanner entrada = new Scanner(System.in);
        System.out.println("[1] Entregas por tipo (No Terminado).\n[2] Entregas por localizacion.\n[3] Entregas por cliente.\n[4] Registro ganancias.\n[5] Salir.");
        System.out.print("Ingrese su opcion: ");
        int opcion = entrada.nextInt();
        while(opcion != 5){
            switch (opcion) {
                case 1 -> System.out.println(sistema.entregasPorTipo());
                case 2 -> System.out.println(sistema.entregasPorLocalizacion());
                case 3 -> System.out.println(sistema.entregasPorCliente());
                case 4 -> System.out.println(sistema.registroGanancias());
                default -> System.out.println("Opcion no valida");
            }
            System.out.println("[1] Entregas por tipo (No Terminado).\n[2] Entregas por localizacion.\n[3] Entregas por cliente.\n[4] Registro ganancias.\n[5] Salir.");
            System.out.print("Ingrese su opcion: ");
            opcion = entrada.nextInt();
        }
    }

    /**
     * Function that shows the client menu
     * @param sistema
     * @param rut
     */
    public static void menuCliente(Sistema sistema, String rut){
        Scanner entrada = new Scanner(System.in);
        System.out.println("[1] Realizar una entrega (No terminado).\n[2] Recargar saldo.\n[3] Ver tus entregas.\n[4] Salir.");
        System.out.print("Ingrese su opcion: ");
        int opcion = entrada.nextInt();
        while (opcion !=4){
            switch (opcion) {
                case 1 -> System.out.println("a");
                case 2 -> {
                    System.out.print("Ingrese al cantidad de saldo para sumar a su cuenta: ");
                    double cant = entrada.nextDouble();
                    if (cant <= 0) System.out.println("El monto no puede ser igual o menor que 0.");
                    else {
                        if (sistema.recargarSaldo(rut, cant)) System.out.println("Has recargado con exito");
                        else System.out.println("No has podido recargar");
                    }
                }
                case 3 -> System.out.println(sistema.desplegarEntregas(rut));
                default -> System.out.println("Opcion ingresada no valida");
            }
            System.out.println("[1] Realizar una entrega (No terminado).\n[2] Recargar saldo.\n[3] Ver tus entregas.\n[4] Salir.");
            System.out.print("Ingrese su opcion: ");
            opcion = entrada.nextInt();
        }
    }

    /**
     * Function that reads the data from the file Cliente.txt; and then store them in their respective class
     * @param sistema
     * @throws IOException
     */
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
    /**
     * Function that reads the data from the file Entregas.txt; and then store them in their respective class
     * @param sistema
     * @throws IOException
     */
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
    /**
     * Function that reads the data from the file localizacion.txt; and then store them in their respective class
     * @param sistema
     * @throws IOException
     */
    private static void leerLocalizacion(Sistema sistema) throws IOException{
        BufferedReader buffer = new BufferedReader(new InputStreamReader
                (new FileInputStream(windowsSeba+"localizacion.txt"),"utf-8"));
        String linea;
        while ((linea = buffer.readLine()) != null){
            sistema.ingresarLocalizacion(linea);
        }
    }
}
