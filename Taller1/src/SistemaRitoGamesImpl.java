import java.util.Scanner;

public class SistemaRitoGamesImpl implements SistemaRitoGames{
    listaPersonajes lpersonajes;
    listaCuentas lcuentas;
    listaEstadistica lestadistica;

    public SistemaRitoGamesImpl(){
        lpersonajes = new listaPersonajes(155);
        lcuentas = new listaCuentas(1000);
        lestadistica = new listaEstadistica(1000);
    }

    @Override
    public int inicioSesion(String nombreCuenta, String contrasenaCuenta) {
        Cuentas c = lcuentas.searchC(nombreCuenta);
        if(nombreCuenta.equals("ADMIN") && contrasenaCuenta.equals("ADMIN")){
            return 2;
        }
        else if(c != null && c.getContrasenaCuenta().equals(contrasenaCuenta)){
            return 1;
        }
        else{
            return -1;
        }
    }

    @Override
    public boolean ingresarCuenta(String nombreCuenta, String contrasenaCuenta, String nickCuenta, int nivelCuenta, int rpCuenta, int totalPersonajes, String skinsCuenta, String regionCuenta) {
        Cuentas cuenta = new Cuentas(nombreCuenta,contrasenaCuenta,nickCuenta,nivelCuenta,rpCuenta,totalPersonajes,skinsCuenta,regionCuenta);
        lcuentas.addCuentas(cuenta);
        return false;
    }

    @Override
    public boolean ingresarPersonajes(String nombreCampeon, String rol, int cantSkins, String skins) {
        Personajes personaje = new Personajes(nombreCampeon, rol, cantSkins,skins);
        lpersonajes.addPersonajes(personaje);
        return false;
    }

    @Override
    public boolean ingresarEstadisticas(String nombreCampeon, int totalRecaudado) {
        Estadisticas estadistica = new Estadisticas(nombreCampeon, totalRecaudado);
        lestadistica.addEstadistica(estadistica);
        return false;
    }

    @Override
    public String mostrarInventario(String nombreCuenta) {
        Cuentas cuenta = lcuentas.searchC(nombreCuenta);
        String text = "";
        String skins = cuenta.getSkins();
        System.out.println("==============================================================="+
                "============================");
        var texto = App.Red+"Skins de " +nombreCuenta+App.Restorer;
        System.out.println(String.format("%63s",texto));
        System.out.println("---------------------------------------------------------------"+
                "----------------------------");

        try {
            String[] partes = skins.split(",");
            for (int i = 0; i < partes.length; i++) {
                if (VerificarInt(partes[i])) {
                    System.out.println("Campeon: " + App.Purple + partes[i - 1] + App.Restorer);
                    System.out.println("Skins: ");
                    for (int j = 0; j < Integer.parseInt(partes[i]); j++) {
                        System.out.println("  - " + App.Purple + partes[i + (j + 1)] + App.Restorer);
                    }
                    System.out.println("----------------------------");
                }
            }
        }
        catch(Exception e){
            System.out.println(App.Red + "No posees Skins" + App.Restorer);
        }


        System.out.println("==============================================================="+
                "============================");
        return text;
    }

    @Override
    public void agregarRP(String nombreCuenta, int monto) {
        Cuentas c = lcuentas.searchC(nombreCuenta);
        int saldo = c.getRpCuenta()+monto;
        c.setRpCuenta(saldo);
    }

    @Override
    public int mostrarRP(String nombreCuenta) {
        Cuentas c = lcuentas.searchC(nombreCuenta);
        return c.getRpCuenta();
    }

    @Override
    public String skinsDisponibles(String nombreCuenta) {
        String texto = "";
        for(int i = 0; i<lpersonajes.getCant();i++){
            Personajes p = lpersonajes.getPersonajesX(i);
            System.out.println("Campeon: " +App.Cyan+ p.getNombreCampeon()+App.Restorer);
            System.out.println("Skins: ");
            String[] partes = p.getDatosSkins().split(",");
            for(int j = 0;j<=(100);j++){
                try {
                    if (!App.stringCharCheck(partes[j])) {
                        if(!usuarioTieneSkin(partes[j], nombreCuenta)) {
                            System.out.println("  - " + conversionCalidadSkin(partes[j + 1]) + partes[j]);
                        }
                    }
                }catch(Exception e){

                }
            }
            if(i != (lpersonajes.getCant()-1)) {
                System.out.println("-------------------------------------");
            }
        }


        return texto;
    }

    @Override
    public void inforUsuario(String nombreCuenta) {
        Cuentas c = lcuentas.searchC(nombreCuenta);
        System.out.println("Nobre de Cuenta: " + App.Cyan + c.getNombreCuenta() + App.Restorer);
        System.out.println("Nick de Cuenta: " + App.Cyan + c.getNickCuenta() + App.Restorer);

        String contrasena = c.getContrasenaCuenta();
        String contrasenaCensurada ="";
        for(int i = 0;i<contrasena.length();i++) {
            if(i!=contrasena.length()-1 && i!=contrasena.length()-2 && i!=contrasena.length()-3){
                contrasenaCensurada += "*";
            }else {
                contrasenaCensurada += contrasena.charAt(i);
            }
        }
        System.out.println("Contraseña de Cuenta: " + App.Cyan + contrasenaCensurada + App.Restorer);
        System.out.print("Desea hacer un cambio de contrasena? (Y/N):  ");
        String opcion = App.ScannerChar();
        while(!opcion.equalsIgnoreCase("Y") && !opcion.equalsIgnoreCase("N")){
            System.out.println(App.Red + "[ERROR] " + App.Restorer + "Opcion ingresada no valida.");
            System.out.print("Desea hacer un cambio de contrasena? (Y/N):  ");
            opcion = App.ScannerChar();
        }
        if(opcion.equalsIgnoreCase("Y")){
            cambiarcontrasena(c);
        }
    }

    private void cambiarcontrasena(Cuentas cuenta) {
        Scanner entrada = new Scanner(System.in);
        String contrasenaActual, contrasenaNueva, contrasenaNueva2;
        System.out.print("Ingrese su contrasena actual: ");
        contrasenaActual = entrada.nextLine();
        if(contrasenaActual.equals(cuenta.getContrasenaCuenta())){
            System.out.print("Ingrese su nueva contraseña: ");
            contrasenaNueva = entrada.nextLine();
            System.out.print("Ingrese otra vez su contraseña nueva: ");
            contrasenaNueva2 = entrada.nextLine();
            if(contrasenaNueva.equals(contrasenaNueva2)){
                cuenta.setContrasenaCuenta(contrasenaNueva2);
                System.out.println("Su contrasena ha sido cambiada con exito!");
            }else{
                System.out.println("Las contraseñas no coinciden. Intente mas tarde");
            }
        }
        else{
            System.out.println("Su contraseña no coincide con la actual. Intente mas tarde.");
        }
    }

    private boolean usuarioTieneSkin(String parte, String nombreCuenta) {
        Cuentas c = lcuentas.searchC(nombreCuenta);
        String skins = c.getSkins();
        String[] partes = skins.split(",");
        int conti = 0;
        for(int i = 0; i<partes.length; i++){
            if(partes[i].equalsIgnoreCase(parte)){
                conti++;
            }
        }
        return conti>0;
    }

    private static String conversionCalidadSkin(String letra){
        switch (letra){
            case "M":
                return (App.Purple +"[MITICA] "+App.Restorer);
            case "D":
                return (App.Yellow +"[DEFINITIVA] "+App.Restorer);
            case "L":
                return (App.Red +"[LEGENDARIA] "+App.Restorer);
            case "E":
                return (App.Cyan +"[EPICA] "+App.Restorer);
            case "N":
                return (App.Green +"[NORMAL] "+App.Restorer);
            default:
                return "[XDDDDD] ";
        }
        //return null;
    }

    private static boolean VerificarInt(String numero){
        try{
            int nuevoNumero = Integer.parseInt(numero);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
