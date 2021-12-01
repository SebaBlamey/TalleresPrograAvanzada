import Clases.AsignaturasCursadas;
import Clases.AsignaturasInscritas;
import Clases.Estudiante;

import java.io.*;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    private static String linuxFilePath="/home/sebaarch/ProyectosU/TalleresPrograAvanzada/Taller2/";
    private static String windowsSeba="D:/Programacion/Java/Universidad/TalleresPrograAvanzada/Taller2/";
    public static void main(String[] args) throws Exception {
        SistemaUCR sistema = new SistemaUCRImpl();
        lecturaEstudiantes(sistema);
        lecturaAsignaturas(sistema);
        lecturaProfesores(sistema);
        lecturaParalelos(sistema);
        Menu(sistema);

    }

    //-------------------------------------------------------------------------------------------------------------

    //-------------------------------------------------- MENUS -----------------------------------------------
    public static void Menu(SistemaUCR sistema) throws InterruptedException, IOException {
        System.out.println("===========================================");
        System.out.println("               Sistema" + Yellow + " UCR                 " + Restorer);
        System.out.println("-------------------------------------------");
        System.out.println("1)" + Cyan + " Iniciar Sesión." + Restorer);
        System.out.println("2)" + Cyan + " Registrarse." + Restorer);
        System.out.println("3)" + Cyan + " Salir." + Restorer);
        System.out.println("===========================================");
        System.out.print("Ingrese alguna opción -> ");
        int opcion = ScannerInt();
        Scanner entrada = new Scanner(System.in);
        while(opcion != 3){
            switch (opcion) {
                case 1:
                    limpiarConsola(3);
                    System.out.println("===========================================");
                    System.out.println(Yellow + "             Inicio de Sesión              " + Restorer);
                    System.out.println("-------------------------------------------");
                    System.out.print("Ingrese su " + Cyan + "CORREO" + Restorer + " -> ");
                    String correo = entrada.nextLine();
                    System.out.print("Ingrese su " + Cyan + "CLAVE" + Restorer + " -> ");
                    String pass = entrada.nextLine();
                    int inicio = sistema.inicioSesion(correo, pass);
                    String fecha = "";
                    boolean fechaValida = false;
                    int Periodo = -1;
                    switch (inicio) {
                        case 0: // Menu administrador
                            System.out.print("Ingrese la fecha en la que se encuetra (yyyy-MM-dd) -> ");
                            fecha = entrada.nextLine();

                            try {
                                String[] partesFecha = fecha.split("-");
                                int ano = Integer.parseInt(partesFecha[0]);
                                int mes = Integer.parseInt(partesFecha[1]);
                                int dia = Integer.parseInt(partesFecha[2]);
                                Date fechita = new Date(ano,mes,dia);
                                Periodo = sistema.periodosSemestre(fechita);
                                fechaValida = true;
                            } catch (Exception e) {
                                System.out.println("Has ingresado un foramto incorrecto");
                            }

                            if(fechaValida){
                                menuAdmin(sistema, Periodo);
                            }
                            break;
                        case 1: // Menu Estudiante
                            correo = sistema.correoCompletoEstudiante(correo);
                            System.out.print("Ingrese la fecha en la que se encuetra (yyyy-MM-dd) -> ");
                            fecha = entrada.nextLine();

                            try {
                                String[] partesFecha = fecha.split("-");
                                int ano = Integer.parseInt(partesFecha[0]);
                                int mes = Integer.parseInt(partesFecha[1]);
                                int dia = Integer.parseInt(partesFecha[2]);
                                Date fechita = new Date(ano,mes,dia);
                                Periodo = sistema.periodosSemestre(fechita);
                                fechaValida = true;
                            } catch (Exception e) {
                                System.out.println("Has ingresado un foramto incorrecto");
                            }

                            if(fechaValida){
                                menuEstudiante(sistema,correo,Periodo);
                            }
                            break;
                        case 2: // Menu profesor
                            String rut = sistema.rutCompletoProfesor(correo);
                            System.out.print("Ingrese la fecha en la que se encuetra (yyyy-MM-dd) -> ");
                            fecha = entrada.nextLine();

                            try {
                                String[] partesFecha = fecha.split("-");
                                int ano = Integer.parseInt(partesFecha[0]);
                                int mes = Integer.parseInt(partesFecha[1]);
                                int dia = Integer.parseInt(partesFecha[2]);
                                Date fechita = new Date(ano,mes,dia);
                                Periodo = sistema.periodosSemestre(fechita);
                                fechaValida = true;
                            } catch (Exception e) {
                                System.out.println("Has ingresado un formato incorrecto");
                            }
                            if(fechaValida){
                                menuProfesor(sistema,rut,Periodo);
                            }
                            break;
                        default:
                            System.out.println(Red + "ERROR: " + Restorer +
                                    "No se encuentras las credenciales ingresadas.");
                    }
                    break;

            }
            limpiarConsola(3000);
            System.out.println("===========================================");
            System.out.println("               Sistema" + Yellow + " UCR                 " + Restorer);
            System.out.println("-------------------------------------------");
            System.out.println("1)" + Cyan + " Iniciar Sesión." + Restorer);
            System.out.println("2)" + Cyan + " Registrarse." + Restorer);
            System.out.println("3)" + Cyan + " Salir." + Restorer);
            System.out.println("===========================================");
            System.out.print("Ingrese alguna opción -> ");
            opcion = ScannerInt();
        }
    }

    private static void menuAdmin(SistemaUCR sistema, int periodo) throws IOException {
        Scanner entrada = new Scanner(System.in);
        int opcion;
        switch (periodo){
            case 0:
                System.out.println("No puedes realizar acciones a inicio de sesmetre");
                break;
            case 1:
                System.out.println("No puedes realizar acciones a mitad de sesmetre");
                break;
            case 2:
                System.out.println("No puedes realizar acciones a final de sesmetre");
                break;
            case 3:
                System.out.println("Un total de " + sistema.ConsolidarSistema() + " han egresados");
                break;
        }
    }

    private static void menuEstudiante(SistemaUCR sistema, String corre, int periodo) {
        Scanner entrada = new Scanner(System.in);
        int opcion;
        switch(periodo){
            case 0:
                System.out.println("===========================================");
                System.out.println("           Inicio Semestre" + Yellow + " Alumno                 " + Restorer);
                System.out.println("-------------------------------------------");;
                System.out.println("1)" + Cyan + " Inscribir Asignatura." + Restorer);
                System.out.println("2)" + Cyan + " Eliminar Asignatura." + Restorer);
                System.out.println("3)" + Cyan + " Salir." + Restorer);
                System.out.println("===========================================");
                System.out.print("Ingrese una opcion: ");
                opcion = ScannerInt();
                String titulo = "";
                String codigo ="";
                while ( opcion != 3){
                    switch (opcion){
                        case 1:
                            System.out.println("==============================================================="+
                                    "=====");
                            titulo = "Asignaturas "+Cyan+"Disponibles"+Restorer;
                            System.out.println(String.format("%55s",titulo));
                            System.out.println("---------------------------------------------------------------"+
                                    "-----");
                            sistema.DesplegarAsignaturas(corre);
                            System.out.println("---------------------------------------------------------------"+
                                    "-----");
                            System.out.print("Ingrese el codigo de la Asignatura que desea: ");
                            codigo = entrada.nextLine();
                            var codigoValido = sistema.codigoValido(corre,codigo);
                            if(codigoValido){
                                System.out.println("==============================================================="+
                                        "=====");
                                titulo = "Paralelos "+Cyan+"Disponibles"+Restorer;
                                System.out.println(String.format("%55s",titulo));
                                System.out.println("---------------------------------------------------------------"+
                                        "-----");
                                sistema.DesplegarParalelos(corre,codigo);
                                System.out.println("---------------------------------------------------------------"+
                                        "-----");
                                System.out.print("Ingrese el numero del paralelo: ");
                                int nParalelo = ScannerInt();
                                if(sistema.InscribirParalelo(corre, codigo, nParalelo)){
                                    System.out.println("Te has inscrito con exito!");
                                }else{
                                    System.out.println("No te has inscrito!");
                                }


                            }else{
                                System.out.println("Codigo invalido o no cuentas con creditos suficientes");
                            }
                            break;
                        case 2:
                            System.out.println("==============================================================="+
                                    "=====");
                            titulo = "Asignaturas "+Cyan+"Inscritas"+Restorer;
                            System.out.println(String.format("%55s",titulo));
                            System.out.println("---------------------------------------------------------------"+
                                    "-----");
                            System.out.println(sistema.asignaturasInscritas(corre));
                            System.out.println("---------------------------------------------------------------"+
                                    "-----");
                            if(!sistema.asignaturasInscritas(corre).equals("No tienes asignaturas inscritas")) {
                                System.out.print("Ingrese el codigo de la asignatura a eliminar: ");
                                codigo = entrada.nextLine();
                                if (sistema.eliminarAsignatura(corre, codigo)) {
                                    System.out.println("Has eliminado la asignatura!");
                                } else {
                                    System.out.println("No has podido eliminar la asignatura");
                                }
                            }
                            break;
                        default:
                            System.out.println(Red + "ERROR: " + Restorer +
                                    "No se encuentras las credenciales ingresadas.");
                            break;
                    }
                    System.out.println("===========================================");
                    System.out.println("           Inicio Semestre" + Yellow + " Alumno                 " + Restorer);
                    System.out.println("-------------------------------------------");;
                    System.out.println("1)" + Cyan + " Inscribir Asignatura." + Restorer);
                    System.out.println("2)" + Cyan + " Eliminar Asignatura." + Restorer);
                    System.out.println("3)" + Cyan + " Salir." + Restorer);
                    System.out.println("===========================================");
                    System.out.print("Ingrese una opcion: ");
                    opcion = ScannerInt();
                }
                break;
            case 1:
                System.out.println("===========================================");
                System.out.println("           Mitad Semestre" + Yellow + " Alumno                 " + Restorer);
                System.out.println("-------------------------------------------");;
                System.out.println("1)" + Cyan + " Eliminar Asignatura." + Restorer);
                System.out.println("2)" + Cyan + " Salir." + Restorer);
                System.out.println("===========================================");
                System.out.print("Ingrese una opcion: ");
                opcion = ScannerInt();
                while(opcion != 2){
                    switch(opcion) {
                        case 1:
                            System.out.println("===============================================================" +
                                    "=====");
                            titulo = "Asignaturas " + Cyan + "Inscritas" + Restorer;
                            System.out.println(String.format("%55s", titulo));
                            System.out.println("---------------------------------------------------------------" +
                                    "-----");
                            System.out.println(sistema.asignaturasInscritas(corre));
                            System.out.println("---------------------------------------------------------------" +
                                    "-----");
                            if (!sistema.asignaturasInscritas(corre).equals("No tienes asignaturas inscritas")) {
                                System.out.print("Ingrese el codigo de la asignatura a eliminar: ");
                                codigo = entrada.nextLine();
                                if (sistema.eliminarAsignatura(corre, codigo)) {
                                    System.out.println("Has eliminado la asignatura!");
                                } else {
                                    System.out.println("No has podido eliminar la asignatura");
                                }
                            }
                            System.out.println("===========================================");
                            System.out.println("           Mitad Semestre" + Yellow + " Alumno                 " + Restorer);
                            System.out.println("-------------------------------------------");
                            ;
                            System.out.println("1)" + Cyan + " Eliminar Asignatura." + Restorer);
                            System.out.println("2)" + Cyan + " Salir." + Restorer);
                            System.out.println("===========================================");
                            System.out.print("Ingrese una opcion: ");
                            opcion = ScannerInt();
                            break;
                        default:
                            System.out.println(Red + "ERROR: " + Restorer +
                                    "No se encuentras las credenciales ingresadas.");
                            break;
                    }
                }

                break;
            case 2:
                System.out.println("No puede realizar acciones al final del semestre");
                break;
            case 3:
                System.out.println("No puede realizar acciones en el cierre del semestre");
                break;
            case -1:
                System.out.println("Disfrutes sus Vacaciones");
        }
    }

    private static void menuProfesor(SistemaUCR sistema, String rut, int periodo){
        Scanner entrada = new Scanner(System.in);
        String titulo = "";
        switch(periodo){
            case 0: //Inicio
                System.out.println("===========================================");
                System.out.println("           Inicio Semestre" + Yellow + " Profesor                 " + Restorer);
                System.out.println("-------------------------------------------");;
                System.out.println("1)" + Cyan + " Chequeo Alumnos." + Restorer);
                System.out.println("2)" + Cyan + " Salir." + Restorer);
                System.out.println("===========================================");
                System.out.print("Ingrese una opcion: ");
                int opcion = ScannerInt();
                while(opcion != 2){
                    switch(opcion){
                        case 1:
                            System.out.println("==============================================================="+
                                    "=====");
                            titulo = "Paralelos "+Cyan+"Dictados"+Restorer;
                            System.out.println(String.format("%55s",titulo));
                            System.out.println("---------------------------------------------------------------"+
                                    "-----");
                            System.out.println(sistema.desplegarParalelosProfesor(rut));
                            System.out.println("---------------------------------------------------------------"+
                                    "-----");
                            System.out.print("Ingrese el numero del paralelo: ");
                            String numeroParalelo = entrada.nextLine();
                            System.out.print("Ingrese el codigo de la asignatura: ");
                            String codigoAsignatura = entrada.nextLine();
                            System.out.println("==============================================================="+
                                    "=====");
                            titulo = "Lista de "+Cyan+"Alumnos"+Restorer;
                            System.out.println(String.format("%55s",titulo));
                            System.out.println("---------------------------------------------------------------"+
                                    "-----");
                            System.out.println(sistema.desplegarChequeoAlumnos(numeroParalelo, codigoAsignatura));
                            System.out.println("---------------------------------------------------------------"+
                                    "-----");
                            break;
                        default:
                            System.out.println(Red + "ERROR: " + Restorer +
                                    "No se encuentras las credenciales ingresadas.");
                            break;
                    }
                    System.out.println("===========================================");
                    System.out.println("           Inicio Semestre" + Yellow + " Profesor                 " + Restorer);
                    System.out.println("-------------------------------------------");;
                    System.out.println("1)" + Cyan + " Chequeo Alumnos." + Restorer);
                    System.out.println("2)" + Cyan + " Salir." + Restorer);
                    System.out.println("===========================================");
                    System.out.print("Ingrese una opcion: ");
                    opcion = ScannerInt();
                }
                break;
            case 1:
                System.out.println("No puedes realizar acciones a mitad de semestre");
                break;
            case 2: // Final
                System.out.println("===========================================");
                System.out.println("           Final Semestre" + Yellow + " Profesor                 " + Restorer);
                System.out.println("-------------------------------------------");;
                System.out.println("1)" + Cyan + " Ingreso nota Final." + Restorer);
                System.out.println("2)" + Cyan + " Salir." + Restorer);
                System.out.println("===========================================");
                System.out.print("Ingrese una opcion: ");
                opcion = ScannerInt();
                while(opcion != 2){
                    if(opcion == 1){
                        System.out.println("==============================================================="+
                                "=====");
                        titulo = "Paralelos "+Cyan+"Dictados"+Restorer;
                        System.out.println(String.format("%55s",titulo));
                        System.out.println("---------------------------------------------------------------"+
                                "-----");
                        System.out.println(sistema.desplegarParalelosProfesor(rut));
                        System.out.println("---------------------------------------------------------------"+
                                "-----");
                        System.out.print("Ingrese el numero del paralelo: ");
                        String numeroParalelo = entrada.nextLine();
                        System.out.print("Ingrese el codigo de la asignatura: ");
                        String codigoAsignatura = entrada.nextLine();
                        System.out.println("==============================================================="+
                                "=====");
                        titulo = "Lista de "+Cyan+"Alumnos"+Restorer;
                        System.out.println(String.format("%55s",titulo));
                        System.out.println("---------------------------------------------------------------"+
                                "-----");
                        System.out.println(sistema.desplegarChequeoAlumnos(numeroParalelo, codigoAsignatura));
                        System.out.println("---------------------------------------------------------------"+
                                "-----");
                        System.out.print("Ingrese el rut del alumno: ");
                        String rutAlumno = entrada.nextLine();
                        System.out.print("Ingrese la nota final del alumnos: ");
                        Double notaFinal = entrada.nextDouble();
                        if(notaFinal < 0.0 || notaFinal > 7.0){
                            System.out.println("La nota no puede ser inferior a 0.0 o superior a 7.0");
                        }
                        else{
                            if(sistema.ingresarNotaFinal(rutAlumno,notaFinal,numeroParalelo,codigoAsignatura)){
                                System.out.println("Nota Ingresada");
                            }else {
                                System.out.println("Nota no ingresada");
                            }
                        }

                    }else {
                        System.out.println(Red + "ERROR: " + Restorer +
                                "Opcion ingresadas no valida.");
                    }
                    System.out.println("===========================================");
                    System.out.println("           Final Semestre" + Yellow + " Profesor                 " + Restorer);
                    System.out.println("-------------------------------------------");;
                    System.out.println("1)" + Cyan + " Ingreso nota Final." + Restorer);
                    System.out.println("2)" + Cyan + " Salir." + Restorer);
                    System.out.println("===========================================");
                    System.out.print("Ingrese una opcion: ");
                    opcion = ScannerInt();
                }
                break;
            case 3:
                System.out.println("No puede realizar acciones en el cierre del semestre");
                break;

            default:
                System.out.println(Red + "ERROR: " + Restorer +
                        "No se encuentras las credenciales ingresadas.");
                break;
        }
    }


    public static void lecturaEstudiantes(SistemaUCR sistema) throws IOException{
        Scanner entrada = new Scanner(new File(windowsSeba+"estudiantes.txt"));
        while(entrada.hasNextLine()){
            String partes[] = entrada.nextLine().split(",");
            String rut = partes[0];
            String correo = partes[1];
            int nivel = Integer.parseInt(partes[2]);
            String contrasena = partes[3];
            String partes2[] = entrada.nextLine().split("");
            sistema.ingresarEstudiante(rut,correo,nivel,contrasena);
            int asignaturasCursadas = Integer.parseInt(partes2[0]);
            for (int i = 0; i < asignaturasCursadas; i++) {
                String partes3[] = entrada.nextLine().split(",");
                String codigoAsignatura = partes3[0];
                Double notaFinal = Double.valueOf((partes3[1]));
                sistema.ingresarAsignaturasCursadas(rut,codigoAsignatura,notaFinal);
            }
            String partes4[] = entrada.nextLine().split(",");
            int asignaturasInscritas = Integer.parseInt(partes4[0]);
            for (int j = 0; j < asignaturasInscritas; j++) {
                String partes5[] = entrada.nextLine().split(",");
                String codigoAsignaturaInscrita = partes5[0];
                String paralelo = ((partes5[1]));
                sistema.ingresarAsignaturasInscritas(rut,codigoAsignaturaInscrita,paralelo);
            }

        }
    }
    private static void lecturaAsignaturas(SistemaUCR sistema) throws IOException{
        BufferedReader buffer = new BufferedReader(new InputStreamReader
                (new FileInputStream("asignaturas.txt"),"utf-8"));
        String linea;
        while((linea=buffer.readLine()) != null){
            String partes[] = linea.split(",");
            String codigo = partes[0];
            String nombreAsignatura = partes[1];
            int cantCreditos = Integer.parseInt(partes[2]);
            String tipoAsignatura = partes[3];
            if(tipoAsignatura.equalsIgnoreCase("Obligatoria")){
                int nivel = Integer.parseInt(partes[4]);
                int cantidadPreRequisitos = Integer.parseInt(partes[5]);
                String codigos = "";
                for(int i = 0 ; i < cantidadPreRequisitos ; i++){
                    if(i+1 == (cantidadPreRequisitos)){
                        codigos = codigos + partes[i+6];
                    }else{
                        codigos = codigos + partes[i+6] + ",";
                    }
                }
                sistema.ingresarAsignaturaObligatoria(codigo,nombreAsignatura,cantCreditos,tipoAsignatura,nivel,cantidadPreRequisitos,codigos);

            }else{
                int cantidadPreRequisitos = Integer.parseInt(partes[4]);
                sistema.ingresarAsignaturaOpcional(codigo,nombreAsignatura,cantCreditos,tipoAsignatura,cantidadPreRequisitos);
            }
        }
    }
    private static void lecturaProfesores(SistemaUCR sistema) throws Exception{
        BufferedReader buffer = new BufferedReader(new InputStreamReader
                (new FileInputStream("profesores.txt"),"utf-8"));
        String linea;
        while((linea = buffer.readLine()) != null) {
            String partes[] = linea.split(",");
            String rut = partes[0];
            String correo = partes[1];
            String contrasena = partes[2];
            int salario = Integer.parseInt(partes[3]);
            sistema.ingresarProfesores(rut,correo,contrasena,salario);
        }
    }
    private static void lecturaParalelos(SistemaUCR sistema) throws Exception{
        BufferedReader buffer = new BufferedReader(new InputStreamReader
                (new FileInputStream("paralelos.txt"),"utf-8"));
        String linea;
        while((linea = buffer.readLine()) != null) {
            String partes[] = linea.split(",");
            String numeroParalelo = partes[0];
            String paralelo = partes[1];
            String rutProfesor = partes[2];
            sistema.acociarParalelo(numeroParalelo,paralelo,rutProfesor);
        }
    }

    //-------------------------------------------------- UTILIDADES -----------------------------------------------

    //LIMPIAR CONSOLA
    private static void limpiarConsola(int time) throws InterruptedException {
        /**
         * This function gives several line breaks to "clean" the console.
         */
        Thread.sleep(time);
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }

    //COLORES
    private static final String Black = "\u001B[30m";
    private static final String Red = "\u001B[31m";
    private static final String Green = "\u001B[32m";
    private static final String Yellow = "\u001B[33m";
    private static final String Blue = "\u001B[34m";
    private static final String Purple = "\u001B[35m";
    private static final String Cyan = "\u001B[36m";
    private static final String White = "\u001B[37m";
    private static final String Restorer = "\u001B[00m";

    //SCANNERS
    private static int ScannerInt() {
        /**
         * Basic function not to be using creating a Scanner every time you want to request an option
         */
        Scanner input = new Scanner(System.in);
        int valor = 0;
        boolean complete = false;
        do {
            try {
                valor = input.nextInt();
                complete = true;
            } catch (InputMismatchException ex) {
                System.out.print(Red + "ERROR: " + Restorer + "Carácter no válido. Ingrese nuevamente -> ");
                input.nextLine();
            }
        } while (!complete);
        return valor;
    }

    public static String ScannerChar() {
        /**
         * Function that asks the user to enter a character. In case of being more than a single character,
         * it will be requested again.
         */
        Scanner input = new Scanner(System.in);
        String valor = "";
        boolean complete = false;
        do {
            if (input.hasNext()) {
                valor = input.next();
                input.nextLine();
                if (stringCharCheck(valor)) {
                    if (valor.length() == 1) {
                        complete = true;
                    } else {
                        System.out.print(Red + "ERROR: " + Restorer + "Ingresa un sólo carácter por favor -> ");
                    }
                } else {
                    System.out.print("Por favor ingresa un carácter");
                }
            } else {
                System.out.println("Carácter inválido");
            }
        } while (!complete);
        valor = valor.toUpperCase();
        return valor;
    }

    private static boolean stringCharCheck(String str) {
        /**
         * Here it is verified that a single character has been entered.
         */
        return ((str != null) && (!str.equals("")) && (str.matches("^[a-zA-Z]+$")));
    }
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
    private static double getRandomDoubleBetweenRange(double min, double max){
        double x = (Math.random()*((max-min)+1))+min;
        return x;
    }
    private static void imprimirWaifu(){
        int num = (int) getRandomDoubleBetweenRange(0.0,4.0);
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
            default:
                System.out.println("Esta vez no hay waifus :C");
                break;
        }
    }

    //--------------------------------------------------------------------------------------------------------------
}
