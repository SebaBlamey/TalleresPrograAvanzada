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

    @Override
    public String comprarSkin(String nombreCuenta) {
        String text = "";
        Cuentas c = lcuentas.searchC(nombreCuenta);
        Scanner entrada = new Scanner(System.in);
        System.out.print("Ingresa el nombre del personaje del cual quieres la skin: ");
        String nombrePersonaje = entrada.nextLine();
        while(!existepersonaje(nombrePersonaje) && !tienePersonaje(nombrePersonaje, nombreCuenta)){
            System.out.println(App.Red + "[ERROR] " + App.Restorer + "El personaje ingresado no es valido o no tienes al personaje.");
            System.out.print("Ingresa el nombre del personaje del cual quieres la skin: ");
            nombrePersonaje = entrada.nextLine();
        }
        System.out.print("Ingrese el nombre de la skin que desea comprar: ");
        String nombreSkin = entrada.nextLine();
        while(!existeSkin(nombrePersonaje, nombreSkin,nombreCuenta)){
            System.out.println(App.Red + "[ERROR] " + App.Restorer + "La skin no es valida o ya la tienes.");
            System.out.print("Ingrese el nombre de la skin que desea comprar: ");
            nombreSkin = entrada.nextLine();
        }
        Personajes p = lpersonajes.searchP(nombrePersonaje);
        String Skins = p.getDatosSkins();
        String[] partes = Skins.split(",");
        String nombredeLaSkin = partes[pos];
        String calidadDeSkin = conversionCalidadSkin(partes[pos+1]);
        int precioDeLaSkin = obtenerPrecioSkin(partes[pos+1]);
        System.out.println("Nombre de la skin: " + nombredeLaSkin);
        System.out.println("Calidad de la skin: " + calidadDeSkin);
        System.out.println("Precio de la skin: " + precioDeLaSkin+" RP");
        System.out.print("Desea realizar la compra? (Y/N): ");
        String charr = App.ScannerChar();
        while (!charr.equalsIgnoreCase("Y") && !charr.equalsIgnoreCase("N")) {
            System.out.println(App.Red + "[ERROR] " + App.Restorer + "La opcion indicada no es valida.");
            System.out.print("Desea realizar la compra? (Y/N): ");
            charr = App.ScannerChar();
        }
        if(charr.equalsIgnoreCase("Y")){
            int cantRp = c.getRpCuenta();
            if(cantRp<precioDeLaSkin){
                System.out.println(App.Red + "[ERROR] "+"No tienes saldo suficiente para realizar la compra!");
            }else{
                int total = cantRp-precioDeLaSkin;
                c.setRpCuenta(total);
                int nivelCuenta = c.getNivelCuenta();
                c.setNivelCuenta(nivelCuenta+1);
                String skinsactuales = c.getSkins();
                String[] partess = skinsactuales.split(",");
                String actales = "";
                for(int i = 0; i<partess.length; i++){

                    if(partess[i].equalsIgnoreCase(nombrePersonaje)){
                        int numSkinActual = Integer.parseInt(partess[i+1]);
                        partess[i+1] = String.valueOf(numSkinActual+1);
                        partess[i+1] += (","+nombreSkin);
                    }
                    if(i==partess.length-1) {
                        actales += partess[i];
                    }else{
                        actales += (partess[i] + ",");
                    }
                }
                c.setSkins(actales);
                System.out.println(App.Green + "[EXITO] " + App.Restorer + "Has comprado la skin!");
            }

        }


        return text;
    }

    @Override
    public String comprarPersonaje(String nombreCuenta) {
        String text = "";
        Cuentas c = lcuentas.searchC(nombreCuenta);
        Scanner entrada = new Scanner(System.in);
        System.out.print("Ingrese el nombre del personaje que desea comprar: ");
        String nombrePersonaje = entrada.nextLine();
        while(!existepersonaje(nombrePersonaje) && !tienePersonaje(nombrePersonaje, nombreCuenta)){
            System.out.println(App.Red + "[ERROR] " + App.Restorer + "El personaje ingresado no es valido o no tienes al personaje.");
            System.out.print("Ingrese el nombre del personaje que desea comprar: ");
            nombrePersonaje = entrada.nextLine();
        }
        System.out.println("Personaje seleccionado: " + App.Cyan + nombrePersonaje + App.Restorer);
        System.out.print("Desea realizar la compra? (Y/N): ");
        String charr = App.ScannerChar();
        while (!charr.equalsIgnoreCase("Y") && !charr.equalsIgnoreCase("N")) {
            System.out.println(App.Red + "[ERROR] " + App.Restorer + "La opcion indicada no es valida.");
            System.out.print("Desea realizar la compra? (Y/N): ");
            charr = App.ScannerChar();
        }
        if(charr.equalsIgnoreCase("Y")){
            int cantRP = c.getRpCuenta();
            if(cantRP<975){
                System.out.println(App.Red + "[ERROR] "+"No tienes saldo suficiente para realizar la compra!");
            }else{
                int total = cantRP-975;
                c.setRpCuenta(total);
                int nivelCuenta = c.getNivelCuenta();
                c.setNivelCuenta(nivelCuenta+1);
                String skinsactuales = c.getSkins();
                c.setSkins(skinsactuales+(","+nombrePersonaje+",0"));
                System.out.println(App.Green + "[EXITO] " + App.Restorer + "Has comprado el personaje!");
            }
        }
        return text;
    }


    private int obtenerPrecioSkin(String letra) {
        switch (letra){
            case "M":
                return 3250;
            case "D":
                return 2750;
            case "L":
                return 1820;
            case "E":
                return 1350;
            case "N":
                return 975;
            default:
                return 9999999;
        }
    }

    public int pos = 0;
    private boolean existeSkin(String nombrePersonaje, String nombreSkin, String nombreCuenta) {
        Personajes p = lpersonajes.searchP(nombrePersonaje);
        String Skins = p.getDatosSkins();
        String[] partes = Skins.split(",");
        for(int i = 0; i<partes.length;i++){
            if(partes[i].equalsIgnoreCase(nombreSkin) && !usuarioTieneSkin(partes[i],nombreCuenta)){
                pos = i;
                return true;
            }
        }
        return false;
    }

    private boolean existepersonaje(String nombrePersonaje) {
        for(int i = 0; i<lpersonajes.getCant(); i++){
            Personajes p = lpersonajes.getPersonajesX(i);
            if(nombrePersonaje.equalsIgnoreCase(p.getNombreCampeon())){
                return true;
            }
        }
        return false;
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
    private boolean tienePersonaje(String nombrePersonaje, String nombreCuenta) {
        Cuentas c = lcuentas.searchC(nombreCuenta);
        String personajes = c.getSkins();
        String[] partes = personajes.split(",");
        for(int i = 0; i<partes.length; i++){
            if(partes[i].equalsIgnoreCase(nombrePersonaje)){
                return true;
            }
        }
        return false;
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


    //ADMIN
    public String recaudacionRol(String rol){
        String mensaje="";
        /*

        Personajes personajes = lpersonajes.searchR(rol);
        String roles = personajes.getRol();
        String [] partes = roles.split(",");
        int cont_adc=0;
        int cont_mid=0;
        int cont_jg=0;
        int cont_top=0;
        int cont_sup=0;

        for(int i=0; i< partes.length;i++) {
            if (partes[i].equalsIgnoreCase("adc")) {
                cont_adc++;
            }
            if (partes[i].equalsIgnoreCase("mid")) {
                cont_mid++;
            }
            if (partes[i].equalsIgnoreCase("jg")) {
                cont_jg++;
            }
            if (partes[i].equalsIgnoreCase("sup")) {
                cont_sup++;
            }
            if(partes[i].equalsIgnoreCase("top")){
                cont_top++;
            }
        }
        var texto = ("La cantidad de ventas recaudadas por cada rol fue:\n SUP: "+cont_sup+"\n ADC:\n MID:\n JG:\n TOP: ");
    | */
        return mensaje;
    }

    /*
    public int mostrarRP(String nombreCuenta) {
        Cuentas c = lcuentas.searchC(nombreCuenta);
        return c.getRpCuenta();
    }
     */
    @Override
    public int recaudacionTotal(String nombreCuenta) {

        return 0;
    }

    @Override
    public String recaudacionPersonajes(String nombreCuenta) {
        String msg ="";

        return null;
    }

    @Override
    public String personajesVentasXRol(String nombreCuenta) {
        return null;
    }

    public String personajesXRol(String nombreCuenta){
        return null;
    }
    /*
    public void agregarRP(String nombreCuenta, int monto) {
        Cuentas c = lcuentas.searchC(nombreCuenta);
        int saldo = c.getRpCuenta()+monto;
        c.setRpCuenta(saldo);
    }

     */

    /*
    public boolean agregarPersonajes(String nombre )
    private String nombreCampeon;
    private String rol;
    private int cantSkins;
    private String datosSkins;
     */
    @Override
    public boolean agregarPersonajes(String nombreCampeon, String rol, int cantSkins, String DatosSkin ) {
        Personajes p = lpersonajes.searchP(nombreCampeon);
        if(p!=null){
            Personajes nP = new Personajes(nombreCampeon,rol,cantSkins,DatosSkin);
            lpersonajes.addPersonajes(nP);
            return true;
        }else{
            return false;
        }

    }
    public boolean agregarSkins(String nombreCampeon, String nameSkin, int valor, String calidad) {
        Personajes p = lpersonajes.searchP(nombreCampeon);
        if(p!=null){
            Personajes Skin = new Personajes(nombreCampeon, nameSkin,valor, calidad);
            lpersonajes.addPersonajes(Skin);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public String blockPlayer(String nombreCuenta) {
        String mensaje="";
        Cuentas c = lcuentas.searchC(nombreCuenta);
        System.out.println("Nombre de Cuenta: "+ c.getNombreCuenta());
        lcuentas.deleteC(nombreCuenta);
        System.out.println("La cuenta "+nombreCuenta+" ha sido bloqueada.");
        return mensaje;
    }

    @Override
    public String infoCuentas(String nombreCuenta) {
        Cuentas c = lcuentas.searchC(nombreCuenta);
        int nivelMayor=0, nivelMenor=0;
        if(c.getNivelCuenta()<c.getNivelCuenta()+1){
            nivelMayor=c.getNivelCuenta()+1;
        }
        return null;
    }


}
