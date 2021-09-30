import java.io.*;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class App {
    // ======================================MAIN======================================

    public static void main(String[] args) throws IOException, InterruptedException {
        leerArchivos("clientes.txt",1);
        System.out.println(White+"["+Cyan+"+"+White+"]"+ Restorer +" Clientes leidos.");
        Thread.sleep(700);
        leerArchivos("status.txt",2);
        System.out.println(White+"["+Cyan+"+"+White+"]"+ Restorer +" Status leidos.");
        Thread.sleep(700);
        leerArchivos("peliculas.txt",3);
        System.out.println(White+"["+Cyan+"+"+White+"]"+ Restorer +" Peliculas leidas.");
        Thread.sleep(700);
        rellenarMatrizAsientos(matrizAsientos);
        for(int i = 0; i<matrizAsientosOcupados.length;i++){
            if(matrizAsientosOcupados[i][0] != null){
                sumaLinea3++;
            }
        }

        inicioSesion();
        guardarArchivos(1);
        guardarArchivos(2);
        guardarArchivos(3);
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
    private static final String Black = "\u001B[30m";
    private static final String Red = "\u001B[31m";
    private static final String Green = "\u001B[32m";
    private static final String Yellow = "\u001B[33m";
    private static final String Blue = "\u001B[34m";
    private static final String Purple = "\u001B[35m";
    private static final String Cyan = "\u001B[36m";
    private static final String White = "\u001B[37m";
    private static final String Restorer = "\u001B[00m";
    // ===============================================================================

    // ====================================LECTURAS====================================

    /***
     * Read and store the data extracted from the given file
     * @param ubicion name of the file from which it will get the data
     * @param almacenar number to separate between the different files
     * @throws IOException
     */
    private static void leerArchivos(String ubicion, int almacenar ) throws IOException
    {

        BufferedReader buffer = new BufferedReader(new InputStreamReader(new FileInputStream(ubicion),"utf-8"));
        String linea;
        while((linea = buffer.readLine())!=null){
            String[] partes = linea.split(",");
            switch(almacenar){
                case 1: // Clientes
                    String nombreCliente = partes[0];
                    String apellidoCliente = partes[1];
                    String rutCliente = partes[2];
                    String contrasenaCliente = partes[3];
                    String saldoCliente = partes[4];

                    matrizClientes[sumaLinea0][0] = nombreCliente;
                    matrizClientes[sumaLinea0][1] = apellidoCliente;
                    matrizClientes[sumaLinea0][2] = rutCliente;
                    matrizClientes[sumaLinea0][3] = contrasenaCliente;
                    matrizClientes[sumaLinea0][4] = saldoCliente;

                    sumaLinea0++;
                    break;

                case 2: // Status
                    String rutClienteStatus = partes[0];
                    String estadoCliente = partes[1];

                    matrizStatus[sumaLinea1][0] = rutClienteStatus;
                    matrizStatus[sumaLinea1][1] = estadoCliente;

                    sumaLinea1++;
                    break;

                case 3: // Peliculas
                    String nombrePelicula = partes[0];
                    String tipoPelicula = partes[1];
                    String recaudacionPelicula = partes[2];

                    matrizPelicula[sumaLinea2][0] = nombrePelicula;
                    matrizPelicula[sumaLinea2][1] = tipoPelicula;
                    matrizPelicula[sumaLinea2][2] = recaudacionPelicula;
                    matrizPelicula[sumaLinea2][3] = "";

                    for(int i = 3; i < partes.length; i++) {

                        String[] partesPeliculas = linea.split(",");
                        String peliculas = partesPeliculas[i];
                        if(!peliculas.equals("")){
                            if(i+1 == partes.length){
                                peliculas = peliculas;
                            }else{
                                peliculas = peliculas + ",";
                            }
                        }
                        matrizPelicula[sumaLinea2][3] += peliculas;
                    }
                    sumaLinea2++;
                    break;

            }
        }
        buffer.close();
    }

    /***
     * update the information that changed during the execution of the program
     * @param num number to separate between the different files
     * @throws IOException
     */
    private static void guardarArchivos(int num) throws IOException{
        FileWriter fichero = null;
        BufferedWriter bw;
        switch (num){
            case 1: // CLIENTES
                fichero = new FileWriter("clientes.txt");
                bw = new BufferedWriter(fichero);
                for(int i =0;i<sumaLinea0-1;i++){
                    bw.write(matrizClientes[i][0]+","+matrizClientes[i][1]+","+
                            matrizClientes[i][2]+","+matrizClientes[i][3]+","+
                            matrizClientes[i][4]);
                    bw.newLine();
                }
                bw.write(matrizClientes[sumaLinea0-1][0]+","+matrizClientes[sumaLinea0-1][1]+","+
                        matrizClientes[sumaLinea0-1][2]+","+matrizClientes[sumaLinea0-1][3]+","+
                        matrizClientes[sumaLinea0-1][4]);
                bw.close();
                break;
            case 2: // STATUS
                fichero = new FileWriter("status.txt");
                bw = new BufferedWriter(fichero);
                for(int i =0;i<sumaLinea1-1;i++){
                    bw.write(matrizStatus[i][0]+","+matrizStatus[i][1]);
                    bw.newLine();
                }
                bw.write(matrizStatus[sumaLinea1-1][0]+","+matrizStatus[sumaLinea1-1][1]);
                bw.close();
                break;
            case 3: // PELICULAS
                fichero = new FileWriter("peliculas.txt");
                bw = new BufferedWriter(fichero);
                for(int i =0;i<sumaLinea2-1;i++){
                    bw.write(matrizPelicula[i][0]+","+matrizPelicula[i][1]+","+
                            matrizPelicula[i][2]+","+matrizPelicula[i][3]);
                    bw.newLine();
                }
                bw.write(matrizPelicula[sumaLinea2-1][0]+","+matrizPelicula[sumaLinea2-1][1]+","+
                        matrizPelicula[sumaLinea2-1][2]+","+matrizPelicula[sumaLinea2-1][3]);
                bw.close();
                break;
        }
    }
    //=======================================================================================

    //=======================================CONSULTAS=======================================
    private static String rut = "";
    private static String rutAux = "";

    /***
     * Function that depending on the indicated parameter will return
     * between 3 options as necessary. It basically asks for the user's RUT.
     * @param mensaje depending on the number of the message directs to one or another option
     * @return return the Rut
     */
    private static String consultaRut(int mensaje){
        Scanner entrada = new Scanner(System.in);
        switch (mensaje){
            case 1:
                System.out.print("Ingresa tu RUT -> ");
                rutAux = entrada.nextLine();
                rut = (rutAux.replaceAll("\\.","").replaceAll("\\-",""));
                break;
            case 2:
                System.out.print(Red + "ERROR: " + Restorer + "Ingrese correctamente su RUT -> ");
                rutAux = entrada.nextLine();
                rut = (rutAux.replaceAll("\\.","").replaceAll("\\-",""));
                break;
            case 3:
                System.out.print(Red + "ERROR: " + Restorer + "Ese usuario ya está registrado. Ingrese nuevamente el RUT -> ");
                rutAux = entrada.nextLine();
                rut = (rutAux.replaceAll("\\.","").replaceAll("\\-",""));
                break;
            default:
                break;
        }
        return rut;
    }
    private static String clave = "";

    /***
     * Function that depending on the indicated parameter will return between 2
     * options. It basically asks the user for the password
     * @param mensaje depending on the number of the message directs to one or another option
     * @return return the password
     */
    private static String consultaClave(int mensaje){
        Scanner entrada = new Scanner(System.in);
        switch (mensaje) {
            case 1:
                System.out.print("Ingresa tu clave -> ");
                clave = entrada.nextLine();
                break;
            case 2:
                System.out.print(Red + "ERROR: " + Restorer + "Ingrese correctamente su clave -> ");
                clave = entrada.nextLine();
                break;
        }
        return clave;
    }

    /***
     * This function is only used when the user submitted is not in the TXT file,
     * in which case the user will be asked if he wants to create a new account
     */
    private static void crearCuenta() {
        String name = "", apellido = "", elRut = "", pass = "", saldo = "";
        Scanner entrada = new Scanner(System.in);
        System.out.print("Ingresa tu nombre -> ");
        name = entrada.nextLine();
        System.out.print("Ingresa tu apellido -> ");
        apellido = entrada.nextLine();
        boolean aux = buscarEnMatriz(consultaRut(1), matrizClientes, sumaLinea1);
        while (aux) {
            aux = buscarEnMatriz(consultaRut(3), matrizClientes, sumaLinea1);
        }
        elRut = rut;
        System.out.print("Ingresa tu contraseña -> ");
        pass = entrada.nextLine();
        System.out.print("Ingresa tu saldo -> ");
        saldo = entrada.nextLine();
        matrizClientes[sumaLinea0][0] = name;
        matrizClientes[sumaLinea0][1] = apellido;
        matrizClientes[sumaLinea0][2] = elRut;
        matrizClientes[sumaLinea0][3] = pass;
        matrizClientes[sumaLinea0][4] = saldo;
        sumaLinea0++;
        System.out.println(Green + "¡Cuenta creada con éxito! " + Restorer + "A continuación, ingrese su contraseña para acceder.");
        buscarEnMatriz(elRut,matrizClientes,sumaLinea0);
    }
    //=======================================================================================

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
    private static String ScannerChar(){
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
    private static boolean stringCharCheck(String str){
        return ((str!=null) && (!str.equals("")) && (str.matches("^[a-zA-Z]+$")));
    }
    //=======================================================================================

    //=======================================BUSQUEDAS=======================================
    private static int pos_a = 0,pos_b = 0;
    private static String elRut = "",matrizAUx = "",matrizUWU = "";

    /**
     * This function searches within the array for the requested data, once it finds the data,
     * it will return true indicating that it did find it. It will also store the position in
     * which the data is in the variables pos_a and pos_b. If nothing is found, it will return false.
     * @param dato String that will be searched within the 2D Array
     * @param matriz 2D Array where the search will be done
     * @param linea Number of rows in the matrix
     * @return true or false
     */
    private static boolean buscarEnMatriz(String dato,String[][] matriz, int linea){
        int cont = 0;
        if(dato.equals("ADMIN") || dato.equalsIgnoreCase("SALIR")){
            return true;
        }else{
            for(int i = 0; i<linea;i++){
                for(int j = 0;j<matriz[i].length;j++){
                    matrizAUx = matriz[i][j];
                    matrizUWU = (matrizAUx.replaceAll("\\.","").replaceAll("\\-",""));
                    if(dato.equals(matrizUWU)){
                        elRut = matrizAUx;
                        cont++;
                        pos_a = i;
                        pos_b = j;
                        rutGlobal = matrizClientes[pos_a][pos_b];
                    }
                }
            }
            if(cont>0){
                return true;
            }else{
                return false;
            }
        }
    }

    /**
     * This function searches for a specific data, using as parameters the Data, the matrix where it should be searched,
     * the position a and b. In the case of enonctrase that data, a true will be returned, otherwise a false
     * @param dato String that will be searched within the 2D Array
     * @param matriz 2D Array where the search will be done
     * @param posa number of the row where it will be searched
     * @param posb number of the column where it will be searched
     * @return true or false
     */
    private static boolean buscarEspecifico(String dato, String[][] matriz,int posa,int posb){
        if(dato.equals("ADMIN") || dato.equalsIgnoreCase("SALIR")){
            return true;
        }else{
            if(dato.equals(matriz[posa][posb+1])){
                return true;
            }else {
                return false;
            }
        }
    }
    //=======================================================================================

    //=========================================Menus=========================================

    /**
     * Main Menu function. Depending on the data entered, it will take you to
     * the User Menu or the Administrator Menu
     * @throws InterruptedException
     * @throws IOException
     */
    private static void inicioSesion() throws InterruptedException, IOException {
        boolean terminarPrograma = false;
        do{
            System.out.println("===========================================");
            System.out.println(Yellow+"             Inicio de Sesión              "+Restorer);
            System.out.println("-------------------------------------------");
            System.out.println("Para cerrar el programa: " +
                    "\nRUT = " + Red + "SALIR" + Restorer +
                    "\nClave = " + Red + "SALIR" + Restorer);
            System.out.println("-------------------------------------------");
            boolean aux = buscarEnMatriz(consultaRut(1),matrizClientes,sumaLinea1);
            int a = 0;
            while(!aux && a==0){
                System.out.print("El Rut ingresado no corresponde a ninguno en nuestra base datos, ¿Desea crear una nueva cuenta?(y/n) -> ");
                String charsito = ScannerChar();
                switch (charsito){
                    case "Y":
                        crearCuenta();
                        a = 1;
                        break;
                    case "N":
                        aux = buscarEnMatriz(consultaRut(2),matrizClientes,sumaLinea1);
                        break;
                }

            }
            boolean aux2 = buscarEspecifico(consultaClave(1),matrizClientes,pos_a,pos_b);
            while(!aux2){
                aux2 = buscarEspecifico(consultaClave(2),matrizClientes,pos_a,pos_b);
            }
            limpiarConsola(3);
            if(rut.equalsIgnoreCase("SALIR") && clave.equalsIgnoreCase("SALIR")){
                imprimirWaifu();
                terminarPrograma = true;
            }
            else if(rut.equals("ADMIN") && clave.equals("ADMIN")){
                menuAdmin();
            }
            else{
                menuCliente();
            }
        }while (!terminarPrograma);

    }

    /**
     * Menu that will be displayed if the data entered in the Main Menu correspond to that of an Admin.
     * You will be asked to enter a data and depending on your choice you will be taken to the corresponding
     * sub Menu.
     * @throws InterruptedException
     */
    private static void menuAdmin() throws InterruptedException {
        boolean cerrarMenu = false;
        do{
            System.out.println("===========================================");
            System.out.println("               Menú"+ Yellow +" Admin                "+Restorer);
            System.out.println("-------------------------------------------");
            System.out.println("1)" + Cyan + " Taquilla."+Restorer);
            System.out.println("2)" + Cyan + " Informacion cliente."+Restorer);
            System.out.println("3)" + Cyan + " Salir."+Restorer);
            System.out.println("===========================================");
            System.out.print("Ingrese alguna opción -> ");
            int input = ScannerInt();
            switch (input){
                case 1: // COMPRAR ENTRADA
                    taquilla();
                    break;
                case 2: // INFORMACION USUARIO
                    limpiarConsola(100);
                    infoCliente();
                    break;
                case 3: // CERRAR MENU
                    limpiarConsola(100);
                    cerrarMenu = true;
                    break;
                default:
                    System.out.println("Intenta de nuevo");
            }
        }while(!cerrarMenu);
    }

    /**
     * Submenu where the information of a client given by the administrator is displayed
     * @throws InterruptedException
     */
    private static void infoCliente() throws InterruptedException {
        System.out.println("===========================================");
        System.out.println("               Informacion"+ Yellow +" Cliente                "+Restorer);
        System.out.println("-------------------------------------------");
        for(int i = 0; i<sumaLinea0;i++){
            System.out.println(matrizClientes[i][2]);
        }
        System.out.println("-------------------------------------------");
        Scanner entrada = new Scanner(System.in);
        System.out.print("Ingrese el rut del cliente -> ");
        String rutCliente = entrada.nextLine();
        while(!verificarRut(rutCliente)){
            System.out.println("[ERROR] El rut ingresado no es valido.");
            System.out.print("Ingrese el rut del cliente -> ");
            rutCliente = entrada.nextLine();
        }
        System.out.println("-------------------------------------------");

        System.out.println("Nombre: " + Cyan + matrizClientes[posa][0] + " " + matrizClientes[posa][1] + Restorer);
        System.out.println("Saldo : $" + Green + matrizClientes[posa][4] + Restorer);
        System.out.println("Entradas compradas: ");
        entradasCompradas(matrizClientes[posa][2]);

        Thread.sleep(5000);
        limpiarConsola(300);
    }

    /**
     * Displays the purchased tickets owned by the user whose rut is entered.
     * @param s variable that corresponds to the person's rut
     */
    private static void entradasCompradas(String s) {
        for(int i = 0; i<sumaLinea3;i++){
            if(matrizAsientosOcupados[i][1].equalsIgnoreCase(s)){
                System.out.println(Cyan + " Pelicula: " + Restorer + matrizAsientosOcupados[i][0]);
                System.out.println(Cyan + "     Horario: " + Restorer + matrizAsientosOcupados[i][2]);
                System.out.println(Cyan + "     Asiento: " + Restorer + matrizAsientosOcupados[i][3]);
                System.out.println("------------------------------------------");
            }
        }
    }

    private static int posa;

    /**
     * Rut of the client which you want to verify if it is valid or not.
     * @param rutCliente
     * @return true or false
     */
    private static boolean verificarRut(String rutCliente) {
        String rutAux = rutCliente;
        int contadortemp = 0;
        String rutVer = (rutAux.replaceAll("\\.","").replaceAll("\\-",""));
        for(int i = 0; i<sumaLinea0;i++){
            if((matrizClientes[i][2].replaceAll("\\.","").replaceAll("\\-","")).equals(rutVer)) {
                contadortemp++;
                posa = i;
                break;
            }
        }
        return contadortemp>0;

    }

    /**
     * Print the box office collected for each movie at its different times.
     * @throws InterruptedException
     */
    private static void taquilla() throws InterruptedException {
        System.out.println("===========================================");
        System.out.println("             Informacion"+ Yellow +" Taquilla                "+Restorer);
        System.out.println("-------------------------------------------");
        for(int i = 0; i<sumaLinea2;i++){
            System.out.println("Pelicula: " + Cyan + matrizPelicula[i][0] + Restorer);
            System.out.println("Monto total recaudado: $" + Green + matrizPelicula[i][2] + Restorer);
            //System.out.println("Monto recaudado en el dia: $" + Green + recuadacionDiaria + Restorer);
            String[] horarios = matrizPelicula[i][3].split(",");
            for(int j = 0; j<horarios.length;j++){
                if(verificarInt(horarios[j])){
                    String tempHorario = horarios[j]+horarios[j+1];
                    System.out.println("Recaudado por " + Cyan + tempHorario + Restorer + ": $" + Green + String.valueOf(totalRecaudado(matrizPelicula[i][0],
                            tempHorario)+Restorer));
                }
            }
            if(sumaLinea2==i+1){
                System.out.println("===========================================");
            }else{
                System.out.println("-------------------------------------------");
            }
        }
        Thread.sleep(5000);
        limpiarConsola(300);
    }

    /**
     * Returns the amount of money that the movie raised at a specific time
     * @param nombrePelicula Name of the movie from which you want to consult its collection
     * @param horario schedule in which the film is presented
     * @return returns the amount of money collected by the film at that time
     */
    private static int totalRecaudado(String nombrePelicula, String horario) {
        int recaudacion = 0;
        for(int i = 0; i<sumaLinea3;i++){
            if(matrizAsientosOcupados[i][0].equalsIgnoreCase(nombrePelicula) && matrizAsientosOcupados[i][2].equalsIgnoreCase(horario)){
                recaudacion = recaudacion + Integer.parseInt(matrizAsientosOcupados[i][4]);
            }
        }
        return recaudacion;
    }

    /**
     Menu that will be displayed if the data entered in the Main Menu correspond to that of a user.
     * You will be asked to enter a data and depending on your choice you will be taken to the corresponding
     * sub Menu.
     * @throws InterruptedException
     */
    public static void menuCliente() throws InterruptedException {
        boolean cerrarMenu = false;
        do{
            System.out.println("===========================================");
            System.out.println("               Menú"+ Yellow +" Cliente                "+Restorer);
            System.out.println("-------------------------------------------");
            System.out.println("1)" + Cyan + " Comprar entradas."+Restorer);
            System.out.println("2)" + Cyan + " Informacion usuario."+Restorer);
            System.out.println("3)" + Cyan + " Devolucion entrada."+Restorer);
            System.out.println("4)" + Cyan + " Cartelera."+Restorer);
            System.out.println("5)" + Cyan + " Salir."+Restorer);
            System.out.println("===========================================");
            System.out.print("Ingrese alguna opción -> ");
            int input = ScannerInt();
            switch (input){
                case 1: // COMPRAR ENTRADA
                    comprarEntrada();
                    break;
                case 2: // INFORMACION USUARIO
                    inforUsuario();
                    break;
                case 3: // DEVOLUCION ENTRADA
                    devolucionEntrada();
                    break;
                case 4: // CARTELERA
                    limpiarConsola(100);
                    mostrarCartelera();
                    break;
                case 5: // CERRAR MENU
                    limpiarConsola(100);
                    cerrarMenu = true;
                    break;
                default:
                    System.out.println("Intenta de nuevo");
            }
        }while(!cerrarMenu);
    }

    /**
     * The process for the return of a purchased ticket is carried out.
     */
    private static void devolucionEntrada() {
        Scanner entrada = new Scanner(System.in);
        for(int i = 0; i<sumaLinea3;i++){
            if(matrizAsientosOcupados[i][1].equalsIgnoreCase(rutGlobal)){
                System.out.println(Cyan + "Pelicula: " + Restorer + matrizAsientosOcupados[i][0]);
                System.out.println(Cyan + " Horario: " + Restorer + matrizAsientosOcupados[i][2]);
                System.out.println(Cyan + " Asiento: " + Restorer + matrizAsientosOcupados[i][3]);
                System.out.println(Cyan + " Valor entrada: $" + Restorer + matrizAsientosOcupados[i][4]);
            }
        }
        System.out.print("Elige la pelicula que deseas reembolzar-> ");
        String pelicula = entrada.nextLine();
        while(!poseePelicula(pelicula)){
            System.out.println("[ERROR] La pelicula ingresada no es valida.");
            System.out.print("Elige la pelicula que deseas reembolzar-> ");
            pelicula = entrada.nextLine();
        }
        System.out.println("Usted posee " + Cyan + contadorPosee + Restorer + " entradas para esa pelicula");
        System.out.print("Ingrese la cantidad que quiere que se le reembolsar-> ");
        int cantidadreembolso = ScannerInt();
        while(cantidadreembolso>contadorPosee && cantidadreembolso<0){
            System.out.println("[ERROR] Cantidad ingresada no valida.");
            System.out.print("Ingrese la cantidad que quiere que se le reembolse-> ");
            cantidadreembolso = ScannerInt();
        }
        verificarPelicula(pelicula);
        String estadoPelicula = matrizPelicula[pos1][pos2+1];
        int reembolo;
        if(estadoPelicula.equals("estreno")){
            reembolo = 5500;
        }else{
            reembolo = 4000;
        }
        int reembolosoValor = ((80*reembolo)/100);
        matrizClientes[pos_a][4] = String.valueOf(Integer.parseInt(matrizClientes[pos_a][4]) + (cantidadreembolso * (reembolosoValor)));
        //retirarPelicula(rutGlobal, cantidadreembolso, pelicula);
        System.out.println("Se ha reembolsado el dinero");

    }
    /*private static void retirarPelicula(String rutUsuario, int cantidad, String pelicula){

        print2D(matrizAsientosOcupados,sumaLinea3);
        for(int a = 0; a<cantidad;a++){
            for(int i = 0; i<sumaLinea3;i++){
                if(matrizAsientosOcupados[a][0].equals(pelicula) && matrizAsientosOcupados[a][1].equals(rutUsuario)){
                    if(matrizAsientosOcupados[a+1][0] != null){
                        matrizAsientosOcupados[a][0] = matrizAsientosOcupados[a+1][0];
                        matrizAsientosOcupados[a][1] = matrizAsientosOcupados[a+1][1];
                        matrizAsientosOcupados[a][2] = matrizAsientosOcupados[a+1][2];
                        matrizAsientosOcupados[a][3] = matrizAsientosOcupados[a+1][3];
                        matrizAsientosOcupados[a][4] = matrizAsientosOcupados[a+1][4];
                    }
                }
            }
        }
    }*/

    private static int contadorPosee = 0;

    /**
     * Check if the user has a ticket for the movie they want to refund
     * @param pelicula name of the movie from which you want to verify
     * @return true or false
     */
    private static boolean poseePelicula(String pelicula) {
        int contids = 0;
        for(int i = 0; i<sumaLinea3;i++){
            if(matrizAsientosOcupados[i][1].equalsIgnoreCase(rutGlobal) && matrizAsientosOcupados[i][0].equalsIgnoreCase(pelicula)){
                contadorPosee++;
                contids++;
            }
        }
        return contids>0;
    }

    /**
     * Shows the billboard of the movies that can be bought tickets.
     * @throws InterruptedException
     */
    private static void mostrarCartelera() throws InterruptedException {
        System.out.println("===========================================");
        System.out.println("           Mostrar"+Green+" Cartelera"+Restorer);
        System.out.println("-------------------------------------------");
        for(int i = 0; i<sumaLinea2;i++){
            System.out.println("Pelicula: " + Cyan + matrizPelicula[i][0] + Restorer);
            System.out.println("Estado: "+ (matrizPelicula[i][1].equalsIgnoreCase("estreno") ? (Yellow + matrizPelicula[i][1] + Restorer):
                    Green + matrizPelicula[i][1]) + Restorer);
            System.out.println("Horarios: ");
            String[] horarios = matrizPelicula[i][3].split(",");
            for(int j = 0; j<horarios.length;j++){
                if(verificarInt(horarios[j])){
                    System.out.println("\t- Sala " + Cyan + horarios[j] + Restorer + " horario " + Cyan + cambiarLetra(horarios[j + 1]) + Restorer);
                }
            }
            if(sumaLinea2==i+1){
                System.out.println("===========================================");
            }else{
                System.out.println("-------------------------------------------");
            }
        }
        Thread.sleep(5000);
        limpiarConsola(300);
    }

    /**
     * Check if a data data corresponds to a String or an int
     * @param dato String which you want to verify if it is an int or String
     * @return true or false
     */
    private static boolean verificarInt(String dato){
        try {
            int auxNumero = parseInt(dato);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * Function to obtain the transformation of the letter "M" and "T".
     * @param dato Letter which you want to change
     * @return returns "Mañana" or "Tarde"
     */
    private static String cambiarLetra(String dato){
        switch(dato){
            case "M":
                return "Mañana";
            case "T":
                return "Tarde";
        }
        return "-1";
    }

    /**
     * It ensures that the data entered complies with an established format.
     * @param dato String of which you want to verify if the format is valid or not
     * @return true or false
     */
    private static boolean verificarFormato(String dato){
        try {
            String[] datoAux = dato.split("-");
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * Make sure that the data entered is a valid time within those that have the movies
     * @param entrada String that corresponds to the time
     * @return true or false
     */
    private static boolean horarioValido(String entrada){
        String[] nuevaEntradita = entrada.split("-");
        String[] horarios = matrizPelicula[pos1][3].split(",");
        int contadoour = 0;
        for(int i = 0; i<horarios.length;i++){
            if(verificarInt(horarios[i])){
                String tempHorario = horarios[i]+horarios[i+1];
                if(tempHorario.equalsIgnoreCase((nuevaEntradita[0]+nuevaEntradita[1]))){
                    contadoour++;
                }
            }
        }
        return contadoour>0;
    }

    /**
     * Function to transform the entered letter into its corresponding number
     * @param letra String which you want to transform
     * @return The number that corresponds to the letter entered
     */
    private static String conversionAlfaNumerica(String letra){
        switch (letra){
            case "A":
                return "1";
            case "B":
                return "2";
            case "C":
                return "3";
            case "D":
                return "4";
            case "E":
                return "5";
            case "F":
                return "6";
            case "G":
                return "7";
            case "H":
                return "8";
            case "I":
                return "9";
            case "J":
                return "10";
            default:
                return "Invalido";
        }
    }

    /**
     * It is in charge of doing the entire ticket purchase process
     * @throws InterruptedException
     */
    private static void comprarEntrada() throws InterruptedException {
        limpiarConsola(100);
        System.out.println("===========================================");
        System.out.println("           Comprar"+Green+" Entrada"+Restorer);
        System.out.println("-------------------------------------------");
        Scanner entrada = new Scanner(System.in);
        System.out.print("Ingrese el nombre de la pelicula -> ");
        String pelicula = entrada.nextLine();
        while(!verificarPelicula(pelicula)){
            System.out.println("ERROR: La pelicula no se encuentra.");
            System.out.print("Ingrese el nombre de la pelicula -> ");
            pelicula = entrada.nextLine();
        }
        System.out.println("Pelicula: " + Cyan + matrizPelicula[pos1][pos2] + Restorer);
        System.out.println("Estado: " + Cyan + matrizPelicula[pos1][pos2+1] + Restorer);
        String[] horarios = (matrizPelicula[pos1][3]).split(",");
        System.out.println("Horarios disponibles: ");
        for(int i = 0; i<horarios.length;i++){
            if(verificarInt(horarios[i])){
                System.out.println("\t-Sala " + Cyan + horarios[i] + Restorer + " horario " + Cyan + cambiarLetra(horarios[i + 1]) + Restorer);
            }
        }
        System.out.println("-------------------------------------------");
        System.out.println("Formato ejemplo: "+Cyan+"2-M (Sala 2 horario mañana)"+Restorer);
        System.out.println("------------------------------------------");
        System.out.print("Ingresa el horario que deseas comprar ->");
        String nuevaEntrada = entrada.nextLine();
        while(!verificarFormato(nuevaEntrada) || !horarioValido(nuevaEntrada)){
            System.out.println("[ERROR] El formato ingresado o el horario es incorecto.");
            System.out.print("Ingresa el horario que deseas comprar ->");
            nuevaEntrada = entrada.nextLine();
        }
        System.out.println("Asientos para " + pelicula + " horario de " + nuevaEntrada);
        imprimirMatrizAsientos(matrizAsientos,pelicula,nuevaEntrada);
        System.out.print("Elige la columna donde deseas comprar la entrada (1-30)-> ");
        String columna = entrada.nextLine();
        while(parseInt(columna)<1 || parseInt(columna)>30){
            System.out.println("[ERROR] Columna no valida!");
            System.out.print("Elige la columna donde deseas comprar la entrada (1-30)-> ");
            columna = entrada.nextLine();
        }
        System.out.print("Elige la fila donde deseas comprar la entrada (A-J)-> ");
        String fila = entrada.nextLine();
        while(conversionAlfaNumerica(fila).equals("Invalido")){
            System.out.println("[ERROR] La fila no es valida!");
            System.out.print("Elige la fila donde deseas comprar la entrada (A-J)-> ");
            fila = entrada.nextLine();
        }
        int newColumna = parseInt(columna);
        int newFila = parseInt(conversionAlfaNumerica(fila));

        if(matrizAsientos[newFila][newColumna].equals(Red + " x "+Restorer)){
            System.out.println("El asiento ingresado ya se encuentra ocupado.");
        }
        else if(matrizAsientos[newFila][newColumna+1].equals(Green +" o "+Restorer) &&
                matrizAsientos[newFila][newColumna-1].equals(Green +" o "+Restorer)){
            float precioEntrada = 0;
            if(matrizPelicula[pos1][1].equalsIgnoreCase("estreno")){
                precioEntrada = 5500;
            }else{
                precioEntrada = 4000;
            }
            if(tieneDescuento(rutGlobal)){
                precioEntrada = precioEntrada - ((15*precioEntrada)/1000);
            }
            System.out.print("El valor de la entrada es de $"+Green+precioEntrada+Restorer+". Desea comprar? (S/N)-> ");
            String opcion = ScannerChar();
            while(!opcion.equalsIgnoreCase("S") && !opcion.equalsIgnoreCase("N")){
                System.out.println("[ERROR] Opcion invalida.");
                System.out.print("El valor de la entrada es de $"+Green+precioEntrada+Restorer+". Desea comprar? (S/N)-> ");
                opcion = ScannerChar();
            }
            if(opcion.equalsIgnoreCase("S")){
                if(parseInt(matrizClientes[pos_a][4])>precioEntrada){
                    matrizClientes[pos_a][4] = String.valueOf(parseInt(matrizClientes[pos_a][4])-precioEntrada);
                    matrizAsientosOcupados[sumaLinea3][0] = pelicula;
                    matrizAsientosOcupados[sumaLinea3][1] = rutGlobal;
                    String[] entradita = nuevaEntrada.split("-");
                    matrizAsientosOcupados[sumaLinea3][2] = entradita[0]+entradita[1] ;
                    matrizAsientosOcupados[sumaLinea3][3] = columna+"-"+fila;
                    matrizAsientosOcupados[sumaLinea3][4] = String.valueOf(precioEntrada);
                    for(int i = 0; i<sumaLinea2;i++){
                        if(matrizPelicula[i][0].equalsIgnoreCase(pelicula)){
                            matrizPelicula[i][2] = String.valueOf(Integer.parseInt(matrizPelicula[i][0])+precioEntrada);
                        }
                    }
                    System.out.println("Su compra se ha realizado con exito!.");
                    sumaLinea3++;
                    print2D(matrizAsientosOcupados,sumaLinea3);
                }else{
                    System.out.println("No tienes saldo suficiente para realizar la compra");
                    System.out.print("Deseas cargar mas saldo? (S/N)-> ");
                    String opcionSaldo = ScannerChar();
                    while(!opcionSaldo.equalsIgnoreCase("S") && !opcionSaldo.equalsIgnoreCase("N")){
                        System.out.println("[ERROR] Opcion  ingresada no valida.");
                        System.out.print("Deseas cargar mas saldo? (S/N)-> ");
                        opcionSaldo = ScannerChar();
                    }
                    if(opcionSaldo.equalsIgnoreCase("S")){
                        System.out.print("Ingresa la cantidad que deseas agregar-> ");
                        int cantidad = ScannerInt();
                        while(cantidad<0){
                            System.out.println("[ERROR] No se admiten numeros negativos");
                            System.out.print("Ingresa la cantidad que deseas agregar-> ");
                            cantidad = ScannerInt();
                        }
                        matrizClientes[pos_a][4] = String.valueOf(parseInt(matrizClientes[pos_a][4])+cantidad);
                        System.out.println("Se han agregado $" + Green + cantidad + Restorer + " a su saldo.");
                    }

                }
            }
            else if(opcion.equalsIgnoreCase("N")){
                System.out.println("Rechazaste la compra");
            }

        }else{
            System.out.println("No puedes comprar, este asiento");
        }
        rellenarMatrizAsientos(matrizAsientos);
        Thread.sleep(1000);
        limpiarConsola(300);

    }

    /**
     * Check if the entered rout has a mobility pass or not, if it does, a discount is applied when buying a ticket
     * @param rutUsuario the user rut
     * @return true or false
     */
    private static boolean tieneDescuento(String rutUsuario) {
        int contDescuento = 0;
        for(int i = 0; i<sumaLinea1;i++){
            if(matrizStatus[i][0].equals(rutUsuario)){
                if(matrizStatus[i][1].equalsIgnoreCase("HABILITADO")){
                    contDescuento++;
                }
            }
        }
        return contDescuento>0;
    }

    private static int pos1, pos2;

    /**
     * Check if the entered movie is valid or not
     * @param pelicula name of the movie to be verified
     * @return true or false
     */
    private static boolean verificarPelicula(String pelicula) {
        int contador = 0;
        for(int i = 0; i<sumaLinea2; i++){
            for(int j = 0; j<matrizPelicula[i].length;j++){
                if(pelicula.equalsIgnoreCase(matrizPelicula[i][j])){
                    contador++;
                    pos1 = i;
                    pos2 = j;
                    break;
                }
            }
        }
        return contador>0;
    }

    /**
     * Print the matrix, where the "o" correspond to the empty seats and the "x" are the occupied seats
     * @param matriz 2D array to print
     * @param nombrePelicula Name of the movie
     * @param horario movie schedule
     */
    private static void imprimirMatrizAsientos(String[][] matriz, String nombrePelicula, String horario){
        String[] nuevaEntradita = horario.split("-");
        for(int k = 0; k<sumaLinea3;k++){
            for(int l =0;l<matrizAsientosOcupados[k].length;l++) {
                if (matrizAsientosOcupados[k][l].equalsIgnoreCase(nombrePelicula) && matrizAsientosOcupados[k][2].equals((nuevaEntradita[0] + nuevaEntradita[1]))) {
                    String[] partes = matrizAsientosOcupados[k][3].split("-");
                    int columna = parseInt(partes[0]);
                    int fila = parseInt(conversionAlfaNumerica(partes[1]));
                    matriz[fila][columna] = (Red + " x " + Restorer);
                }
            }
        }
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * The 2D array is filled in so that it can be used in the future. It is only aesthetic
     * @param matriz 2D array of occupied seats to be filled. It is only aesthetic.
     */
    private static void rellenarMatrizAsientos(String[][] matriz){
        for(int i = 0; i<matriz.length; i++){
            for(int j = 0; j<matriz[i].length; j++){
                matriz[i][j] = (Green+" o "+Restorer);
                if(i == 0){
                    if(j==0){
                        matriz[i][j] = "   ";
                    }else if(j==1){
                        matriz[i][j] = " 1 ";
                    }else if(j==2){
                        matriz[i][j] = " 2 ";
                    }else if(j==3){
                        matriz[i][j] = " 3 ";
                    }else if(j==4){
                        matriz[i][j] = " 4 ";
                    }else if(j==5){
                        matriz[i][j] = " 5 ";
                    }else if(j==6){
                        matriz[i][j] = " 6 ";
                    }else if(j==7){
                        matriz[i][j] = " 7 ";
                    }else if(j==8){
                        matriz[i][j] = " 8 ";
                    }else if(j==9){
                        matriz[i][j] = " 9 ";
                    }else if(j==10){
                        matriz[i][j] = " 10";
                    }else if(j==11){
                        matriz[i][j] = " 11";
                    }else if(j==12){
                        matriz[i][j] = " 12";
                    }else if(j==13){
                        matriz[i][j] = " 13";
                    }else if(j==14){
                        matriz[i][j] = " 14";
                    }else if(j==15){
                        matriz[i][j] = " 15";
                    }else if(j==16){
                        matriz[i][j] = " 16";
                    }else if(j==17){
                        matriz[i][j] = " 17";
                    }else if(j==18){
                        matriz[i][j] = " 18";
                    }else if(j==19){
                        matriz[i][j] = " 19";
                    }else if(j==20){
                        matriz[i][j] = " 20";
                    }else if(j==21){
                        matriz[i][j] = " 21";
                    }else if(j==22){
                        matriz[i][j] = " 22";
                    }else if(j==23){
                        matriz[i][j] = " 23";
                    }else if(j==24){
                        matriz[i][j] = " 24";
                    }else if(j==25){
                        matriz[i][j] = " 25";
                    }else if(j==26){
                        matriz[i][j] = " 26";
                    }else if(j==27){
                        matriz[i][j] = " 27";
                    }else if(j==28){
                        matriz[i][j] = " 28";
                    }else if(j==29){
                        matriz[i][j] = " 29";
                    }else if(j==30){
                        matriz[i][j] = " 30";
                    }
                }else if(i==1){
                    if(j==0) matriz[i][j] = " A ";
                }else if(i==2){
                    if(j==0) matriz[i][j] = " B ";
                }else if(i==3){
                    if(j==0) matriz[i][j] = " C ";
                }else if(i==4){
                    if(j==0) matriz[i][j] = " D ";
                }else if(i==5){
                    if(j==0) matriz[i][j] = " E ";
                }else if(i==6){
                    if(j==0) matriz[i][j] = " F ";
                }else if(i==7){
                    if(j==0) matriz[i][j] = " G ";
                }else if(i==8){
                    if(j==0) matriz[i][j] = " H ";
                }else if(i==9){
                    if(j==0) matriz[i][j] = " I ";
                }else if(i==10){
                    if(j==0) matriz[i][j] = " J ";
                }
                if((i == 1 || i ==2 || i ==3 || i ==4 ) && (j==1 || j==2 || j==3 || j==4 || j==5 || j==26 || j==27 || j==28 || j==29 || j==30)){
                    matriz[i][j] = "   ";
                }

            }
        }
    }

    /**
     * Print user information
     * @throws InterruptedException
     */
    private static void inforUsuario() throws InterruptedException {
        limpiarConsola(100);
        System.out.println("===========================================");
        System.out.println("           Información"+Green+" Usuario"+Restorer);
        System.out.println("-------------------------------------------");
        System.out.println(Cyan + "Nombre: " + Restorer +
                matrizClientes[pos_a][pos_b-2]+" "+
                matrizClientes[pos_a][pos_b-1]);
        System.out.println(Cyan + "Saldo: " + Restorer + "$"+
                matrizClientes[pos_a][pos_b + 2]);
        System.out.println(Cyan + "Entradas compradas: " + Restorer);
        for(int i = 0; i<sumaLinea3;i++){
            if(matrizAsientosOcupados[i][1].equalsIgnoreCase(rutGlobal)){
                System.out.println(Cyan + " Pelicula: " + Restorer + matrizAsientosOcupados[i][0]);
                System.out.println(Cyan + " Horario: " + Restorer + matrizAsientosOcupados[i][2]);
                System.out.println(Cyan + " Asiento: " + Restorer + matrizAsientosOcupados[i][3]);
            }
        }
        System.out.println("===========================================");

        Thread.sleep(3000);
        limpiarConsola(300);
        System.out.println("\n");
    }
    //=======================================================================================

    //========================================WAIFUSS========================================

    /**
     * Simple function that only has the ascii drawing of different anime waifus
     */
    private static void MaiSan(){
        System.out.println(Purple + "⠄⠄⠄⣰⣿⠄⠄⠄⠄⠄⢠⠄⠄⢀⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄\n" +
                "⠄⠄⢰⣿⠿⠄⡀⠄⠄⠄⠘⣷⡀⠄⠢⣄⠄⠄⠄⠄⠄⠄⠄⣠⠖⠁⠄⠄⠄⠄\n" +
                "⠄⣤⢸⣿⣿⣆⠣⠄⠄⠄⠄⠸⣿⣦⡀⠙⢶⣦⣄⡀⠄⡠⠞⠁⢀⡴⠄⠄⠄⠄\n" +
                "⢰⣿⣎⣿⣿⣿⣦⣀⠄⠄⠄⠄⠹⣿⣿⣦⢄⡙⠻⠿⠷⠶⠤⢐⣋⣀⠄⠄⠄⠄\n" +
                "⢸⣿⠛⠛⠻⠿⢿⣿⣧⢤⣤⣄⣠⡘⣿⣿⣿⡟⠿⠛⠂⠈⠉⠛⢿⣿⠄⠄⠄⠄\n" +
                "⠄⡇⢰⣿⣇⡀⠄⠄⣝⣿⣿⣿⣿⣿⣿⣿⣿⣶⣿⡄⠄⠈⠄⣷⢠⡆⠄⠄⠄⠄\n" +
                "⢹⣿⣼⣿⣯⢁⣤⣄⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡄⣴⠶⣲⣵⠟⠄⠄⠄⠄⠸\n" +
                "⠄⢿⣿⣿⣿⣷⣮⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣶⣾⣟⣡⡴⠄⠄⠄⠄⠁\n" +
                "⠄⠰⣭⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠁⡀⠄⠄⠄⠄\n" +
                "⠄⠄⠻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣭⣶⡞⠄⠄⠄⠄⠄\n" +
                "⠄⠄⠐⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡟⠄⠄⠄⠄⠄⠄\n" +
                "⠄⠄⠄⠈⠻⣿⣿⣿⣿⣿⣿⣯⣿⣯⣿⣾⣿⣿⣿⣿⣿⡿⠋⠄⠄⠄⠄⠄⠄⠄\n" +
                "⠄⠄⠄⠄⠄⠄⠙⠻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣵⠄⠄⠄⠄⠄⠄⠄⠄⠄\n" +
                "⠄⠄⠄⠄⠄⠄⠄⢀⣿⣯⣟⣿⣿⣿⡿⣟⣯⣷⣿⣿⡏⣤⠄⠄⠄⠄⠄⠄⠄⠄\n" +
                "⠄⠄⠄⠄⠄⠄⠄⣞⢸⣿⣿⣿⣾⣷⣿⣿⣿⣿⣿⣿⣇⣿⡆⠄⠄⠄⠄⠄⠄⠄");
    }
    private static void ChikaFujiwara(){
        System.out.println(Purple + "⢸⣿⣿⣿⣿⠃⠄⢀⣴⡾⠃⠄⠄⠄⠄⠄⠈⠺⠟⠛⠛⠛⠛⠻⢿⣿⣿⣿⣿⣶⣤⡀⠄\n" +
                "⢸⣿⣿⣿⡟⢀⣴⣿⡿⠁⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⣸⣿⣿⣿⣿⣿⣿⣿⣷\n" +
                "⢸⣿⣿⠟⣴⣿⡿⡟⡼⢹⣷⢲⡶⣖⣾⣶⢄⠄⠄⠄⠄⠄⢀⣼⣿⢿⣿⣿⣿⣿⣿⣿⣿\n" +
                "⢸⣿⢫⣾⣿⡟⣾⡸⢠⡿⢳⡿⠍⣼⣿⢏⣿⣷⢄⡀⠄⢠⣾⢻⣿⣸⣿⣿⣿⣿⣿⣿⣿\n" +
                "⡿⣡⣿⣿⡟⡼⡁⠁⣰⠂⡾⠉⢨⣿⠃⣿⡿⠍⣾⣟⢤⣿⢇⣿⢇⣿⣿⢿⣿⣿⣿⣿⣿\n" +
                "⣱⣿⣿⡟⡐⣰⣧⡷⣿⣴⣧⣤⣼⣯⢸⡿⠁⣰⠟⢀⣼⠏⣲⠏⢸⣿⡟⣿⣿⣿⣿⣿⣿\n" +
                "⣿⣿⡟⠁⠄⠟⣁⠄⢡⣿⣿⣿⣿⣿⣿⣦⣼⢟⢀⡼⠃⡹⠃⡀⢸⡿⢸⣿⣿⣿⣿⣿⡟\n" +
                "⣿⣿⠃⠄⢀⣾⠋⠓⢰⣿⣿⣿⣿⣿⣿⠿⣿⣿⣾⣅⢔⣕⡇⡇⡼⢁⣿⣿⣿⣿⣿⣿⢣\n" +
                "⣿⡟⠄⠄⣾⣇⠷⣢⣿⣿⣿⣿⣿⣿⣿⣭⣀⡈⠙⢿⣿⣿⡇⡧⢁⣾⣿⣿⣿⣿⣿⢏⣾\n" +
                "⣿⡇⠄⣼⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠟⢻⠇⠄⠄⢿⣿⡇⢡⣾⣿⣿⣿⣿⣿⣏⣼⣿\n" +
                "⣿⣷⢰⣿⣿⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⢰⣧⣀⡄⢀⠘⡿⣰⣿⣿⣿⣿⣿⣿⠟⣼⣿⣿\n" +
                "⢹⣿⢸⣿⣿⠟⠻⢿⣿⣿⣿⣿⣿⣿⣿⣶⣭⣉⣤⣿⢈⣼⣿⣿⣿⣿⣿⣿⠏⣾⣹⣿⣿\n" +
                "⢸⠇⡜⣿⡟⠄⠄⠄⠈⠙⣿⣿⣿⣿⣿⣿⣿⣿⠟⣱⣻⣿⣿⣿⣿⣿⠟⠁⢳⠃⣿⣿⣿\n" +
                "⠄⣰⡗⠹⣿⣄⠄⠄⠄⢀⣿⣿⣿⣿⣿⣿⠟⣅⣥⣿⣿⣿⣿⠿⠋⠄⠄⣾⡌⢠⣿⡿⠃\n" +
                "⠜⠋⢠⣷⢻⣿⣿⣶⣾⣿⣿⣿⣿⠿⣛⣥⣾⣿⠿⠟⠛⠉⠄⠄");
    }
    private static void Fubuki(){
        System.out.println(Purple + "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⢛⡋⠙⡩⠵⢒⠯⡉⠙⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡟⣤⢀⡄⢀⢠⢈⠈⠀⠩⠹⡀⡀⢻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                "⣿⣿⣿⣿⣿⣿⣿⣿⣿⠯⠈⡁⠑⠁⡃⠉⠋⠉⠳⠛⠏⠁⠁⡀⠐⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                "⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠁⠆⡀⢠⠀⠀⡀⠀⠁⠩⠱⠄⡘⠗⢈⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                "⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠤⡐⠠⠘⠈⠅⣹⠀⡇⠀⢀⠈⡀⢧⠄⢰⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                "⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⣰⣠⡀⠀⣡⡟⣸⣣⣆⢸⠀⠩⣥⠀⢼⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                "⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⣳⣏⣐⣬⣿⣧⣖⣴⣴⣾⣾⣿⣿⡄⢘⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣅⡄⠀⠓⠻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡄⡉⡚⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                "⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠅⠰⠀⠂⣿⣿⡻⣿⣿⣿⣿⣿⣿⢉⠁⡖⡷⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                "⣿⣿⣿⣿⣿⣿⣿⣿⡿⣦⢰⠀⠀⠀⠝⠿⣿⣞⢯⣽⡟⠿⢱⠀⢸⠁⣟⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣤⡾⠋⠀⠀⠀⠀⠈⠉⠁⢨⡄⠺⣸⣬⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                "⣿⣿⣿⣿⣿⡛⣩⢀⢶⠈⠢⠀⣀⠀⠀⠀⠀⠀⡄⡀⠘⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                "⣿⣿⣿⡿⠑⢠⡖⠼⠟⣳⠇⣄⠄⡞⢀⣀⠀⢀⠈⣃⡀⠉⠙⡛⠛⠟⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                "⣿⣿⣿⣄⠧⢙⠀⣀⠔⢇⣇⡤⠣⢂⠐⠒⡰⠘⠡⢿⣷⣔⡓⠋⠐⠀⠊⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                "⣿⣿⢯⠀⠀⠘⢛⣍⢫⡂⠓⡄⠚⠐⠥⢽⡊⠄⠁⢿⣿⣿⣿⣮⣴⠒⠘⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                "⣿⡋⠀⠀⠀⢤⠈⠢⣀⠃⡀⠊⠂⠐⠈⣤⣬⣶⣶⣿⣯⣟⠷⡿⣻⣿⣶⣤⡛⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                "⠟⠀⠀⠀⣤⠼⠂⠃⠀⠀⠀⠀⠀⠀⠀⠘⢿⣿⣿⣟⣿⣿⣷⣟⠍⠛⠻⢿⣽⣶⣽⣻⢿⣿⣿⣿⣿⣿⣿⣿\n" +
                "⠉⡽⣴⡪⢹⠫⢁⣴⣷⠀⠀⠀⠀⠀⠀⠀⠈⠛⠻⡟⠛⠋⠉⠀⠀⠀⠀⠈⠋⠛⠋⠛⠷⡽⣿⣿⣿⣿⣿⣿\n" +
                "⢎⡔⠀⢹⠥⢂⣷⣿⣿⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⣿⣿⣿⣿\n" +
                "⢄⢃⣀⣗⣡⣿⣿⣿⣿⣿⣦⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⣿⣿⣿⣿⣿⣿\n" +
                "⡗⡡⣔⣼⣿⣿⣿⣿⣿⣿⣿⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣴⣿⣿⣿⣿⣿⣿⣿\n" +
                "⠀⣠⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                "⠀⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                "⢮⠁⠻⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                "⡔⡛⡖⠟⢿⣿⣿⣿⣿⣿⣿⡟⠁⠀⠀⢀⠀⠀⠀⠠⠀⠀⠀⠀⠀⠀⠀⢰⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿");
    }
    private static void ZeroTwo(){
        System.out.println(Purple + "⣿⣿⣿⣿⣯⣿⣿⠄⢠⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡟⠈⣿⣿⣿⣿⣿⣿⣆⠄\n" +
                "⢻⣿⣿⣿⣾⣿⢿⣢⣞⣿⣿⣿⣿⣷⣶⣿⣯⣟⣿⢿⡇⢃⢻⣿⣿⣿⣿⣿⢿⡄\n" +
                "⠄⢿⣿⣯⣏⣿⣿⣿⡟⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣧⣾⢿⣮⣿⣿⣿⣿⣾⣷\n" +
                "⠄⣈⣽⢾⣿⣿⣿⣟⣄⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣝⣯⢿⣿⣿⣿⣿\n" +
                "⣿⠟⣫⢸⣿⢿⣿⣾⣿⢿⣿⣿⢻⣿⣿⣿⢿⣿⣿⣿⢸⣿⣼⣿⣿⣿⣿⣿⣿⣿\n" +
                "⡟⢸⣟⢸⣿⠸⣷⣝⢻⠘⣿⣿⢸⢿⣿⣿⠄⣿⣿⣿⡆⢿⣿⣼⣿⣿⣿⣿⢹⣿\n" +
                "⡇⣿⡿⣿⣿⢟⠛⠛⠿⡢⢻⣿⣾⣞⣿⡏⠖⢸⣿⢣⣷⡸⣇⣿⣿⣿⢼⡿⣿⣿\n" +
                "⣡⢿⡷⣿⣿⣾⣿⣷⣶⣮⣄⣿⣏⣸⣻⣃⠭⠄⠛⠙⠛⠳⠋⣿⣿⣇⠙⣿⢸⣿\n" +
                "⠫⣿⣧⣿⣿⣿⣿⣿⣿⣿⣿⣿⠻⣿⣾⣿⣿⣿⣿⣿⣿⣿⣷⣿⣿⣹⢷⣿⡼⠋\n" +
                "⠄⠸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣦⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡟⣿⣿⣿⠄⠄\n" +
                "⠄⠄⢻⢹⣿⠸⣿⣿⣿⣿⣿⣷⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⣼⣿⣿⣿⣿⡟⠄⠄\n" +
                "⠄⠄⠈⢸⣿⠄⠙⢿⣿⣿⣹⣿⣿⣿⣿⣟⡃⣽⣿⣿⡟⠁⣿⣿⢻⣿⣿⢿⠄⠄\n" +
                "⠄⠄⠄⠘⣿⡄⠄⠄⠙⢿⣿⣿⣾⣿⣷⣿⣿⣿⠟⠁⠄⠄⣿⣿⣾⣿⡟⣿⠄⠄\n" +
                "⠄⠄⠄⠄⢻⡇⠸⣆⠄⠄⠈⠻⣿⡿⠿⠛⠉⠄⠄⠄⠄⢸⣿⣇⣿⣿⢿⣿⠄⠄");
    }
    private static void Umaru(){
        System.out.println(Purple + "⠄⡀⠄⠄⠠⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠠⠄⠄⠄⠄⠄⠄⠄⠄⠠⠄⠄⠄⣀⣀⣀⣀⡀⠄⠄⠄\n" +
                "⠐⠄⠄⠤⠅⠐⠄⠄⠄⠠⠄⠄⠄⡼⣻⢳⣀⣤⣤⣴⣶⣶⣶⣶⣦⣤⣤⣄⣀⠄⠄⠄⢀⠄⠄⠄⠈⠱⠙⠋⠒⠄⠄⠅\n" +
                "⢀⠄⠒⠪⢤⡠⡡⢀⡄⢁⢓⠠⠄⣑⣿⣿⣿⣿⣿⣿⣿⣿⣿⣯⣿⣿⣿⣿⣿⣿⣦⣼⢯⡝⡆⠄⠁⠄⠄⡠⡀⠠⢈⠄\n" +
                "⠄⠂⢚⣀⡁⢐⣄⡂⠚⣈⠠⣠⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣾⣥⡐⠅⠈⠄⠂⠐⠆⠄⢐\n" +
                "⠖⠢⢔⠒⠐⣒⠰⠆⢐⢀⣼⣿⣿⣿⢿⣻⣿⣭⣍⣭⣭⣭⣽⣿⣛⠿⢿⣿⣿⣿⣿⣿⣿⣿⣿⣷⡄⠄⠐⠄⠂⠄⢂⠃\n" +
                "⢢⣬⡠⢤⣠⡬⣐⡀⡀⣾⣿⢿⣽⣾⡿⣿⣟⣿⣿⢻⣿⣿⣿⣿⣿⣿⣿⣾⣿⡿⣿⣿⣿⣿⣿⣿⣷⣐⢥⡶⠲⢰⢄⠆\n" +
                "⣺⠣⣖⣤⣯⢵⢁⢭⢸⡟⢱⠿⠟⢙⣓⠛⠽⣿⣿⣸⡿⣿⣿⣿⣿⣿⡽⣻⣿⣿⣾⣿⣿⣿⣿⣿⣿⡇⠉⠳⠷⠷⠹⠺\n" +
                "⣿⣹⡻⣯⣵⢧⠩⠟⣾⢡⣿⠄⠔⠳⠄⠄⠄⠄⠈⠙⠿⠽⠏⢛⠛⠻⠿⠻⣾⢿⣿⣿⣮⢿⣿⣿⣿⣇⡪⢅⢑⣬⡄⠑\n" +
                "⣩⣘⢓⡒⣩⣷⣾⡇⣿⣾⣿⣡⡀⠄⠄⠄⠄⠄⠄⣰⣶⡶⠄⠲⠄⠁⠄⠄⠈⠛⢿⣿⣿⡟⣿⣿⣿⣏⣩⠧⣠⡊⣝⡪\n" +
                "⣽⣾⣿⣿⣷⣝⠿⣸⡇⣿⣿⣿⣿⣿⣶⣶⣶⣶⣾⣿⣿⣧⡀⠄⠄⠄⠄⠄⢀⡴⣾⣿⣿⡗⣽⣿⣿⡏⣿⣿⣶⣴⣲⢶\n" +
                "⣿⣿⣿⣿⣿⣿⢧⣿⡇⣿⣿⣽⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣶⣤⣤⣴⣿⡻⣫⣿⣿⣙⣛⣿⣿⡷⣿⣿⣿⣿⣿⣷\n" +
                "⣿⣿⣿⣿⣿⣏⣿⣿⡇⣿⡿⢿⢹⡻⣿⣿⣿⣿⣭⣿⣿⣿⣽⣿⣿⣿⣿⣿⣧⣽⣿⣿⢯⣿⠿⠿⠿⠇⢿⣋⣽⣷⣶⣿\n" +
                "⣿⣿⣿⣿⡟⣼⣿⣿⣿⠹⡏⢿⢸⢿⠱⣦⣧⢑⠾⠻⠿⠿⠿⠿⠿⠿⢛⢛⢡⣿⣿⣏⢞⣥⣿⣿⣿⣿⣶⣽⣛⡿⢿⣿\n" +
                "⡿⣿⣿⣿⢷⣿⣿⢟⣥⣾⣦⣧⣾⣿⣿⣾⣮⣷⣶⣔⣤⣀⣳⣤⣼⣷⣾⢏⣾⣿⢏⣴⣿⣨⢱⣾⣿⣶⡆⠉⣛⣛⣡⣿\n" +
                "⣿⣮⡻⣿⢸⡿⡃⢸⣿⣧⣿⣻⣷⡻⣼⣿⣿⣿⣿⣿⣿⣾⣭⣭⣭⣯⣍⣮⢜⣵⡟⣭⣽⣿⡞⣿⣿⣿⠃⣴⣿⣿⣿⣿\n" +
                "⣿⣿⣿⣮⣸⠁⠃⢸⣿⢿⣟⣿⣿⣿⣿⣿⣿⣿⡿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⣿⣿⣿⣧⢻⣿⣟⣶⣾⣿⣷⣶⣾\n" +
                "⣿⣿⣿⣿⣧⠈⣀⣴⣶⣿⣿⣿⣿⣿⣟⣿⡿⢿⣃⣻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣯⠃⢸⣫⣾⣿⣿⣿⣿⣿⣿\n" +
                "⡿⣛⢽⣶⣾⣾⣿⣿⣿⣿⡿⠜⢿⣿⣿⣿⣿⣿⣿⣿⣟⣛⣿⢿⣿⣿⣿⣿⣿⡿⣾⣿⣿⢿⣠⣾⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                "⣾⣿⣿⣿⣿⣻⣿⣿⣿⣿⣦⣤⣈⣻⣿⡿⣻⣿⣿⡿⠿⠏⠻⣿⣾⡿⣿⠿⠿⣓⢻⣽⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣟⣿⣿⡿⣟⣽⣾⣿⣷⣶⣶⣷⣶⣿⣿⣿⣿⣿⣮⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣏⣾⣿⣽⣟⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣮⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n");
    }
    private static void DripGoku(){
        System.out.println(Purple + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠰⣤⣤⢀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⣀⣀⣀⠀⠀⢿⣿⣦⠈⢄⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠈⠻⣿⣶⣦⣍⠐⢼⣿⣿⣧⠈⡄⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢿⣿⣿⣷⣄⣿⣿⣿⣦⣴⣦⣀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⢀⢠⣄⣒⣚⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡧⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠰⠿⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡧⣤⣤⣤⣄⡀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠉⠻⢿⣿⣿⣿⡽⣿⠟⢃⣿⠟⡉⣿⣿⣿⣿⣿⠿⠛⠉⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠠⠶⢯⣭⣿⢹⡟⢶⣗⠀⢸⠇⢁⣠⠫⠟⣹⣿⣯⡭⠶⠂⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢙⣄⠣⠁⠈⠓⠊⠐⠃⠀⠀⣰⣿⣏⡀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⢉⣽⠀⠀⠀⠀⠀⠀⠀⣿⣯⠉⠉⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣿⣿⡀⠀⠀⠀⠀⠀⠀⣿⣿⣷⡀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⣾⣿⣿⣿⣷⡀⠀⠀⠀⠀⢠⣿⣿⣿⣷⣦⣄⡀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⣰⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣶⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣦⡀⠀\n" +
                "⠀⠀⠀⠀⢀⠘⢛⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠿⠿⢿⣿⣿⣿⠗⠀⠀\n" +
                "⠀⠀⠀⢀⣧⣀⡀⠶⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣏⣠⣀⣀⣿⡿⠃⢢⣠⡆\n" +
                "⠀⠀⠀⢸⠿⣿⡿⣷⡿⠿⣿⢿⡿⠩⢹⣿⡟⠉⠉⠉⢹⣿⢻⠛⠛⠻⢿⣿⣷⣿⣿⠉⠟⠀\n" +
                "⠀⠀⠀⡾⢶⣿⣦⣿⣷⡇⢁⠚⡇⠐⢀⣿⣷⣤⣤⣤⣿⣧⣀⣠⣇⡰⢀⡿⣿⣿⣷⣾⣶⡀\n" +
                "⠀⠀⢰⣵⡎⠹⣿⣿⣿⣁⣾⢻⣿⢻⣟⣩⣿⣿⣿⣿⣿⣿⣿⣿⣿⣥⣾⣿⡿⠛⠛⢛⡟⢇⠀\n" +
                "⠀⠀⣾⣿⣞⣰⠞⣩⣿⣻⡇⢠⠃⠸⠉⠙⣿⣿⣿⣿⢈⡁⠤⠁⠌⢉⢙⣿⣐⣧⣤⣾⢇⢸⠀\n" +
                "⠀⠀⢹⡿⢿⢋⡍⢇⠛⣿⣧⣼⣦⣴⣔⣠⣿⣿⣿⣯⣦⣤⣦⣤⣦⣄⣸⡻⢛⠋⠉⠻⠿⡇⠀\n" +
                "⠀⠀⠀⡰⠀⣆⣷⣴⣶⠿⣿⡿⣿⢿⣿⣿⣿⣿⣿⢿⡿⢿⣿⢉⠉⣿⣿⣷⣾⣤⣭⡁⠂⠀⠀⠀\n" +
                "⠀⠀⠀⢱⣾⣿⣿⣿⣿⠐⣉⣌⠇⠐⠁⡄⣿⣿⣿⠈⡠⢾⡇⠠⠠⣼⣽⡟⢣⠄⣹⣿⡖⠀⠀\n" +
                "⠀⠀⠀⠀⢿⣉⠁⣿⣷⣿⡟⢿⡵⣾⣶⣦⣿⣿⣿⣴⣷⣾⣷⠖⡴⠿⣿⣿⠷⠚⢛⡻⠁⠀\n" +
                "⠀⠀⠀⠀⠈⢿⣆⣿⣿⣿⣧⠐⡅⢘⠃⣿⣿⣿⣟⠿⡿⣿⣧⣈⠴⢃⠘⣻⣿⣿⡟⠀⠀\n" +
                "⠀⠀⠀⠀⠀⢰⢻⣿⠃⢸⣿⣶⣿⡿⡟⢉⣿⢿⣻⣼⣧⠂⠛⢿⣷⣟⣀⣥⡼⠋⠀⠀\n" +
                "⠀⠀⠀⠀⠀⣾⣏⡙⣿⣾⣿⣿⠿⢄⠉⣸⡿⠁⠉⠉⠟⢬⢛⢚⠻⣿⣿⠛⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⣿⣷⣦⣅⣙⣿⣧⣮⣬⣽⡟⠀⠀⠀⠀⢶⣶⣮⢧⢠⠙⣰⢹⣦⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠿⢿⣿⣿⣿⣿⣿⣿⣿⣿⡀⠀⠀⠀⠀⠀⣈⣽⣶⣿⣿⣷⣼⠟⠆\n" +
                "⠀⠀⠀⠀⠀⠘⢥⣏⢿⢯⣿⡟⠀⣿⣿⣿⣶⣄⣠⢈⠲⠟⣛⠏⣍⣏⠬⣄⡺⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⢸⣿⣿⣾⣿⣃⣧⣿⣿⣿⣿⣿⣧⣒⣴⣦⣾⣶⣾⣶⣿⡇⠀⠀");
    }

    /**
     * Function to have a random number
     * @param min minimum number that can be obtained
     * @param max maximum number that can be obtained
     * @return a random number between the ranges of min and max
     */
    private static double getRandomDoubleBetweenRange(double min, double max){
        double x = (Math.random()*((max-min)+1))+min;
        return x;
    }

    /**
     * Depending on the number obtained, it will print a waifu
     */
    private static void imprimirWaifu(){
        int num = (int) getRandomDoubleBetweenRange(0.0,6.0);
        switch (num){
            case 0:
                MaiSan();
                break;
            case 1:
                ZeroTwo();
                break;
            case 2:
                ChikaFujiwara();
                break;
            case 3:
                DripGoku();
                break;
            case 4:
                Fubuki();
                break;
            case 5:
                Umaru();
                break;
            default:
                System.out.println("Esta vez no hay waifus :C");
                break;
        }
    }
    //=======================================================================================
    public static String[][] matrizAsientos = new String[11][31];
    public static String[][] matrizClientes = new String[1000][5];
    public static String[][] matrizStatus = new String[1000][2];
    public static String[][] matrizPelicula = new String[1000][4];
    public static String[][] matrizAsientosOcupados = new String[1000][5];
    public static String rutGlobal = "";
    public static int sumaLinea0 = 0;
    public static int sumaLinea1 = 0;
    public static int sumaLinea2 = 0;
    public static int sumaLinea3 = 0;

}
