import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws IOException, InterruptedException {
        SistemaRitoGames sistema = new SistemaRitoGamesImpl();
        lecturaCuentas(sistema);
        Thread.sleep(500);
        lecturaPersonajes(sistema);
        Thread.sleep(500);
        lecturaEstadisticas(sistema);
        Thread.sleep(500);
        menus(sistema);
        sistema.sobreEscribirDtos();
    }
    // ===================================LECTURAS===================================

    /**
     * Function that reads the data from the file Cuentas.txt; and then store them in their respective class
     * @param sistema
     * @throws IOException
     */
    private static void lecturaCuentas(SistemaRitoGames sistema) throws IOException {

        BufferedReader buffer = new BufferedReader(new InputStreamReader(new FileInputStream("C://Users//Jota//TalleresPrograAvanzada//Taller1//Cuentas.txt"),
                "utf-8"));
        String linea;
        while((linea = buffer.readLine()) != null) {
            String[] partes = linea.split(",");
            String nombreCuenta = partes[0];
            String contrasenaCuenta = partes[1];
            String nickCuenta = partes[2];
            int nivelCuenta =  Integer.parseInt(partes[3]);
            int rpCuenta = Integer.parseInt(partes[4]);
            int totalPersonajes = Integer.parseInt(partes[5]);
            String skins = "";
            for(int i = 6;i<(partes.length-1);i++) {
                if(i+1 == (partes.length-1)){
                    skins = skins + partes[i];
                }
                else {
                    skins = skins + partes[i] + ",";
                }
            }
            String region = partes[partes.length-1];
            sistema.ingresarCuenta(nombreCuenta,contrasenaCuenta, nickCuenta, nivelCuenta, rpCuenta, totalPersonajes, skins, region);

        }
        System.out.println(White+"["+Cyan+"+"+White+"]"+ Restorer +" Cuentas leidos.");
        buffer.close();
    }

    /**
     * Function that reads the data from the file Personajes.txt; and then store them in their respective class
     * @param sistema
     * @throws IOException
     */
    private static void lecturaPersonajes(SistemaRitoGames sistema) throws IOException {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(new FileInputStream("C://Users//Jota//TalleresPrograAvanzada//Taller1//Personajes.txt"),
                "utf-8"));
        String linea;
        while((linea = buffer.readLine()) != null) {
            String[] partes = linea.split(",");
            String nombreCampeon = partes[0];
            String rolCampeon = partes[1];
            int cantSkins = Integer.parseInt(partes[2]);
            String datosSkins = "";
            for(int i = 3; i<partes.length;i++){
                if(i+1 == (partes.length)){
                    datosSkins = datosSkins + partes[i];
                }
                else {
                    datosSkins = datosSkins + partes[i] + ",";
                }
            }
            sistema.ingresarPersonajes(nombreCampeon,rolCampeon,cantSkins,datosSkins);
        }
        System.out.println(White+"["+Cyan+"+"+White+"]"+ Restorer +" Personajes leidos.");
        buffer.close();
    }

    /**
     * Function that reads the data from the file Estadisticas.txt; and then store them in their respective class
     * @param sistema
     * @throws IOException
     */
    private static void lecturaEstadisticas(SistemaRitoGames sistema) throws IOException {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(new FileInputStream("C://Users//Jota//TalleresPrograAvanzada//Taller1//Estadisticas.txt"),
                "utf-8"));
        String linea;
        while((linea = buffer.readLine()) != null) {
            String[] partes = linea.split(",");
            String nombreCampeon = partes[0];
            int totalRecaudado = Integer.parseInt(partes[1]);

            sistema.ingresarEstadisticas(nombreCampeon,totalRecaudado);

        }
        System.out.println(White+"["+Cyan+"+"+White+"]"+ Restorer +" Estadisticas leidos.");
        buffer.close();
    }

    // ===============================================================================
    // ===================================UTILIDADES===================================

    /***
     * Print a 2D array
     * @param matrix The 2D array to be printed.
     * @param sumaLineaX The number of elements that the array has
     */
    public static void print2D(String matrix[][], int sumaLineaX)
    {
        for (int i = 0; i < sumaLineaX; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if(matrix[i][j] != null) {
                    System.out.print(matrix[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    /***
     * Clean the console after the indicated time
     * @param time how long will i wait
     * @throws InterruptedException
     */
    private static void limpiarConsola(int time) throws InterruptedException {
        Thread.sleep(100);
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }
    public static final String Black = "\u001B[30m";
    public static final String Red = "\u001B[31m";
    public static final String Green = "\u001B[32m";
    public static final String Yellow = "\u001B[33m";
    public static final String Blue = "\u001B[34m";
    public static final String Purple = "\u001B[35m";
    public static final String Cyan = "\u001B[36m";
    public static final String White = "\u001B[37m";
    public static final String Restorer = "\u001B[00m";

    public static String[] Regiones = {"LAS","LAN","EUW","KR","NA","RU"};
    // ===============================================================================

    //======================================SCANNERS=========================================

    /**
     * Basic function not to be using creating a Scanner every time you want to request an option
     * @return return the int
     */
    private static int ScannerInt() {
        Scanner input = new Scanner(System.in);
        int valor = 0;
        boolean complete = false;
        do{
            try{
                valor = input.nextInt();
                complete = true;
            }catch (InputMismatchException ex){
                System.out.print(Red + "ERROR: " + Restorer + "Carácter no válido. Ingrese nuevamente -> ");
                input.nextLine();
            }
        }while(!complete);
        return valor;
    }

    /**
     * Function that asks the user to enter a character. In case of being more than a single character,
     * it will be requested again.
     * @return return the String
     */
    public static String ScannerChar(){
        Scanner input = new Scanner(System.in);
        String valor ="";
        boolean complete = false;
        do {
            if(input.hasNext()){
                valor = input.next();
                input.nextLine();
                if(stringCharCheck(valor)){
                    if(valor.length() == 1){
                        complete = true;
                    }
                    else {
                        System.out.print(Red + "ERROR: " + Restorer + "Ingresa un sólo carácter por favor -> ");
                    }
                }
                else {
                    System.out.print("Por favor ingresa un carácter");
                }
            }
            else {
                System.out.println("Carácter inválido");
            }
        }while(!complete);
        valor = valor.toUpperCase();
        return valor;
    }

    /**
     * Here it is verified that a single character has been entered.
     * @param str String that will be verified within the function
     * @return true or false
     */
    public static boolean stringCharCheck(String str){
        return ((str!=null) && (!str.equals("")) && (str.matches("^[a-zA-Z]+$")));
    }
    //=======================================================================================

    // ===================================MENUS===================================

    /**
     * Function whose only utility is to forward to the option entered by the user.
     * @param sistema
     * @throws InterruptedException
     */
    public static void menus(SistemaRitoGames sistema) throws InterruptedException {
        System.out.println("===========================================");
        System.out.println("               Sistema" + Yellow + "   RitoGames               " + Restorer);
        System.out.println("-------------------------------------------");
        System.out.println("1)" + Cyan + " Iniciar Sesión." + Restorer);
        System.out.println("2)" + Cyan + " Registrarse." + Restorer);
        System.out.println("3)" + Cyan + " Salir." + Restorer);
        System.out.println("===========================================");
        System.out.print("Ingrese alguna opción -> ");
        int opcion = ScannerInt();
        Scanner entrada = new Scanner(System.in);
        while (opcion != 3) {
            if (opcion == 1) {
                limpiarConsola(3);
                System.out.println("===========================================");
                System.out.println(Yellow + "             Inicio de Sesión              " + Restorer);
                System.out.println("-------------------------------------------");
                System.out.print("Ingrese su " + Cyan + "USUARIO" + Restorer + " -> ");
                String usuario = entrada.nextLine();
                System.out.print("Ingrese su " + Cyan + "CLAVE" + Restorer + " -> ");
                String pass = entrada.nextLine();
                int inicio = sistema.inicioSesion(usuario, pass);
                switch (inicio) {
                    case 2:
                        System.out.println("Administrador");
                        menuAdmin(sistema, usuario);
                        break;
                    case 1:
                        System.out.println("Usuario");
                        menuUsuario(sistema, usuario);
                        break;
                    default:
                        System.out.println(Red + "ERROR: " + Restorer + "No se encuentras las credenciales ingresadas.");
                }

            }else{
                registro(sistema);
            }
            limpiarConsola(3);
            System.out.println("===========================================");
            System.out.println("               Sistema" + Yellow + " RitoGames                 " + Restorer);
            System.out.println("-------------------------------------------");
            System.out.println("1)" + Cyan + " Iniciar Sesión." + Restorer);
            System.out.println("2)" + Cyan + " Registrarse." + Restorer);
            System.out.println("3)" + Cyan + " Salir." + Restorer);
            System.out.println("===========================================");
            System.out.print("Ingrese alguna opción -> ");
            opcion = ScannerInt();
        }




    }

    /**
     * Function that allows creating new accounts for new users.
     * @param sistema
     * @throws InterruptedException
     */
    public static void registro(SistemaRitoGames sistema) throws InterruptedException{
        limpiarConsola(3);
        Scanner entrada = new Scanner(System.in);
        System.out.println("===========================================");
        System.out.println(Yellow+"             Registro "+ Red + "Usuario" + Restorer);
        System.out.println("-------------------------------------------");
        System.out.print("Ingrese " + Cyan + " nombre de usuario: " + Restorer);
        String nombreUsuario = entrada.nextLine();
        System.out.print("Ingrese " + Cyan + " contraseña: " + Restorer);
        String contrasena = entrada.nextLine();
        System.out.print("Ingrese " + Cyan + " nick del juego: " + Restorer);
        String nickCuenta = entrada.nextLine();
        System.out.print("Ingresa " + Cyan + " region: " + Restorer);
        String region = entrada.nextLine();
        while(!verificarRegion(region)){
            System.out.println(Red + "[ERROR] " + Restorer + "Region ingresada no valida.");
            System.out.print("Ingresa " + Cyan + " region: " + Restorer);
            region = entrada.nextLine();
        }
        try {
            sistema.ingresarCuenta(nombreUsuario, contrasena, nickCuenta, 0, 0, 0, "", region);
            System.out.println(Green + "[EXITO] " + Restorer + "Te has registrado correctamente!");
        }
        catch (Exception e){
            System.out.println(Red + "[ERROR] " + Restorer + "No has podido registrate!");
        }
    }

    /**
     * Function that is in charge of verifying if the entered String corresponds to a valid region.
     * @param region String data which will be verified
     * @return true or false
     */
    private static boolean verificarRegion(String region) {
        int cont_verify = 0;
        for(int i = 0; i< Regiones.length;i++){
            if(region.equalsIgnoreCase(Regiones[i])){
                cont_verify++;
            }
        }
        return cont_verify>0;

    }

    /**
     * The main menu of users, where they can be redirected to multiple options as indicated
     * @param sistema
     * @param nombreUsuario String where the name of the user's account is saved.
     * @throws InterruptedException
     */
    private static void menuUsuario(SistemaRitoGames sistema, String nombreUsuario) throws InterruptedException {
        Scanner entrada = new Scanner(System.in);
        limpiarConsola(3);
        System.out.println("===========================================");
        System.out.println("               Menú" + Yellow + " CLIENTE                " + Restorer);
        System.out.println("-------------------------------------------");
        System.out.println("1)" + Cyan + " Comprar Skin." + Restorer);
        System.out.println("2)" + Cyan + " Comprar Personaje." + Restorer);
        System.out.println("3)" + Cyan + " Skins Disponibles." + Restorer);
        System.out.println("4)" + Cyan + " Mostrar Inventario." + Restorer);
        System.out.println("5)" + Cyan + " Recargar RP." + Restorer);
        System.out.println("6)" + Cyan + " Mostrar Datos." + Restorer);
        System.out.println("7)" + Cyan + " Salir." + Restorer);
        System.out.println("===========================================");
        System.out.print("Ingrese una opción: ");
        int opcion = ScannerInt();
        while(opcion != 7){
            switch (opcion){
                case 1:
                    limpiarConsola(3);
                    System.out.println("==============================================================="+
                            "============================");
                    var textos = Red+"Comprar Skin"+Restorer;
                    System.out.println(String.format("%63s",textos));
                    System.out.println("---------------------------------------------------------------"+
                            "----------------------------");
                    sistema.comprarSkin(nombreUsuario);
                    System.out.println("==============================================================="+
                            "============================");
                    Thread.sleep(5000);
                    break;
                case 2:
                    limpiarConsola(3);
                    System.out.println("==============================================================="+
                            "============================");
                    var textoss = Red+"Comprar Personaje"+Restorer;
                    System.out.println(String.format("%63s",textoss));
                    System.out.println("---------------------------------------------------------------"+
                            "----------------------------");
                    sistema.comprarPersonaje(nombreUsuario);
                    System.out.println("==============================================================="+
                            "============================");
                    Thread.sleep(5000);
                    break;
                case 3:
                    limpiarConsola(3);
                    //System.out.println("Skins disponibles");
                    System.out.println("==============================================================="+
                            "============================");
                    var texto = Red+"Skins Disponibles"+Restorer;
                    System.out.println(String.format("%63s",texto));
                    System.out.println("---------------------------------------------------------------"+
                            "----------------------------");
                    sistema.skinsDisponibles(nombreUsuario);
                    System.out.println("==============================================================="+
                            "============================");
                    Thread.sleep(5000);
                    break;
                case 4:
                    limpiarConsola(3);
                    sistema.mostrarInventario(nombreUsuario);
                    Thread.sleep(5000);
                    break;
                case 5:
                    limpiarConsola(3);
                    System.out.println("Tu saldo actual es de: " + Green + sistema.mostrarRP(nombreUsuario) + Restorer);
                    System.out.print("Ingresa la cantidad de saldo que deseas agregar: ");
                    int cantidadAgregar = ScannerInt();
                    while(cantidadAgregar<=0){
                        System.out.println(Red + "[ERROR] " + Restorer + "La cantidad no puede ser menor que 0.");
                        System.out.print("Ingresa la cantidad de saldo que deseas agregar: ");
                        cantidadAgregar=ScannerInt();
                    }
                    sistema.agregarRP(nombreUsuario,cantidadAgregar);
                    System.out.println(Green + "EXITO! " + "El monto fue sumado a su cuenta."+Restorer);
                    System.out.println("Tu nuevo saldo es de $" + Green + sistema.mostrarRP(nombreUsuario) + Restorer);
                    Thread.sleep(1000);
                    break;
                case 6:
                    limpiarConsola(3);
                    System.out.println("==============================================================="+
                            "============================");
                    var texto1 = Red+"Informacion de Usuario"+Restorer;
                    System.out.println(String.format("%63s",texto1));
                    System.out.println("---------------------------------------------------------------"+
                            "----------------------------");
                    sistema.inforUsuario(nombreUsuario);
                    System.out.println("==============================================================="+
                            "============================");

                    Thread.sleep(1000);
                    break;
                default:
                    System.out.println(Red+"[ERROR] "+Restorer+"Opcion no valida");
                    break;
            }
            limpiarConsola(3000);
            System.out.println("===========================================");
            System.out.println("               Menú" + Yellow + " CLIENTE                " + Restorer);
            System.out.println("-------------------------------------------");
            System.out.println("1)" + Cyan + " Comprar Skin." + Restorer);
            System.out.println("2)" + Cyan + " Comprar Personaje." + Restorer);
            System.out.println("3)" + Cyan + " Skins Disponibles." + Restorer);
            System.out.println("4)" + Cyan + " Mostrar Inventario." + Restorer);
            System.out.println("5)" + Cyan + " Recargar RP." + Restorer);
            System.out.println("6)" + Cyan + " Mostrar Datos." + Restorer);
            System.out.println("7)" + Cyan + " Salir." + Restorer);
            System.out.println("===========================================");
            System.out.print("Ingrese una opción:");
            opcion = ScannerInt();
        }

    }

    /**
     * Administrators main menu, where according to the option they induen they will be redirected to other functions.
     * @param sistema
     * @param nombreCuenta String where the name of the administrators account is saved.
     * @throws InterruptedException
     */
    private static void menuAdmin(SistemaRitoGames sistema, String nombreCuenta) throws InterruptedException {
        Scanner entrada = new Scanner(System.in);
        limpiarConsola(3);
        System.out.println("===========================================");
        System.out.println("               Menú" + Yellow + " ADMIN                " + Restorer);
        System.out.println("-------------------------------------------");
        System.out.println("1)" + Cyan + " Recaudacion de venta por rol." + Restorer);
        System.out.println("2)" + Cyan + " Recaudacion total de ventas por región." + Restorer);
        System.out.println("3)" + Cyan + " Recaudacion por personajes." + Restorer);
        System.out.println("4)" + Cyan + " Cantidad de personajes por rol." + Restorer);
        System.out.println("5)" + Cyan + " Agregar personaje [No Hecho]." + Restorer);
        System.out.println("6)" + Cyan + " Agregar Skin." + Restorer);
        System.out.println("7)" + Cyan + " Bloquear jugador." + Restorer);
        System.out.println("8)" + Cyan + " Desplegar cuentas." + Restorer);
        System.out.println("9)" + Cyan + " Salir." + Restorer);
        System.out.println("===========================================");
        System.out.print("Ingrese alguna opción -> ");
        int opcion = ScannerInt();
        while(opcion != 9){
            switch (opcion){
                case 1:
                    limpiarConsola(3);
                    System.out.println("Recaudacion de venta por rol.");
                    sistema.recaudacionRol(nombreCuenta);
                    Thread.sleep(5000);
                    break;
                case 2:
                    limpiarConsola(3);
                    System.out.println("Recaudacion total de ventas por región.");
                    sistema.recaudacionTotal(nombreCuenta);
                    Thread.sleep(5000);
                    break;
                case 3:
                    limpiarConsola(3);
                    System.out.println("Recaudacion por personajes.");
                    sistema.recaudacionPersonajes(nombreCuenta);
                    Thread.sleep(5000);
                    break;
                case 4:
                    limpiarConsola(3);
                    System.out.println("Cantidad de personajes por rol.");
                    sistema.personajesXRol(nombreCuenta);
                    Thread.sleep(5000);
                    break;
                case 5:
                    limpiarConsola(3);
                    System.out.println("Agregar personaje.");
                    sistema.agregarPersonajes(nombreCuenta);
                    Thread.sleep(3000);
                    break;
                case 6:
                    limpiarConsola(3);
                    System.out.println("Agregar skin.");
                    sistema.agregarSkins(nombreCuenta);
                    Thread.sleep(3000);
                    break;
                case 7:
                    limpiarConsola(3);
                    System.out.println("Bloquear jugador.");
                    sistema.blockPlayer(nombreCuenta);
                    Thread.sleep(3000);
                    break;
                case 8:
                    limpiarConsola(3);
                    System.out.println("Desplegar cuentas.");
                    sistema.infoCuentas(nombreCuenta);
                    Thread.sleep(5000);
                    break;
                default:
                    System.out.println(Red+"[ERROR] "+Restorer+"Opción no valida");
                    break;

        }
        limpiarConsola(3000);
        System.out.println("===========================================");
        System.out.println("               Menú" + Yellow + " ADMIN                " + Restorer);
        System.out.println("-------------------------------------------");
        System.out.println("1)" + Cyan + " Recaudacion de ventas por rol." + Restorer);
        System.out.println("2)" + Cyan + " Recaudacion total de ventas por región." + Restorer);
        System.out.println("3)" + Cyan + " Recaudacion por personajes." + Restorer);
        System.out.println("4)" + Cyan + " Cantidad de personajes por rol." + Restorer);
        System.out.println("5)" + Cyan + " Agregar personaje." + Restorer);
        System.out.println("6)" + Cyan + " Agregar Skin." + Restorer);
        System.out.println("7)" + Cyan + " Bloquear jugador." + Restorer);
        System.out.println("8)" + Cyan + " Desplegar cuentas." + Restorer);
        System.out.println("9)" + Cyan + " Salir." + Restorer);
        System.out.println("===========================================");
        System.out.print("Ingrese alguna opción -> ");
        opcion = ScannerInt();
        }
    // ===============================================================================
    }
}