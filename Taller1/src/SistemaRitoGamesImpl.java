import java.io.IOException;
import java.util.Scanner;
import ucn.ArchivoSalida;
import ucn.Registro;

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
        System.out.println("Nombre de Cuenta: " + App.Cyan + c.getNombreCuenta() + App.Restorer);
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
    }

    private static boolean VerificarInt(String numero){
        try{
            int nuevoNumero = Integer.parseInt(numero);
            return true;
        }catch (Exception e){
            return false;
        }
    }


    public String recaudacionRol(String nombreCuenta){
        String mensaje="";
        int cont_adc=0;
        int cont_mid=0;
        int cont_jg=0;
        int cont_top=0;
        int cont_sup=0;
        double recaudadoAdc=0,recaudadoSup=0,recaudadoMid=0,recaudadoTop=0,recadudadoJg=0;
        for(int i=0 ; i<lpersonajes.getCant();i++){
            Personajes p = lpersonajes.getPersonajesX(i);
            Estadisticas e = lestadistica.getEstadisticaX(i);
            if(e.getNombreCampeon().equals(p.getNombreCampeon())){
                if(p.getRol().equalsIgnoreCase("ADC")){
                    cont_adc+=e.getTotalRecaudado();
                }if(p.getRol().equalsIgnoreCase("SUP")){
                    cont_sup+=e.getTotalRecaudado();
                }if(p.getRol().equalsIgnoreCase("MID")){
                    cont_mid+=e.getTotalRecaudado();
                }if(p.getRol().equalsIgnoreCase("TOP")){
                    cont_top+=e.getTotalRecaudado();
                }if(p.getRol().equalsIgnoreCase("JG")){
                    cont_jg+=e.getTotalRecaudado();
                }
            }
            recaudadoAdc=conversionRpClp(cont_adc);
            recadudadoJg=conversionRpClp(cont_jg);
            recaudadoMid=conversionRpClp(cont_mid);
            recaudadoSup=conversionRpClp(cont_sup);
            recaudadoTop=conversionRpClp(cont_top);
        }
        System.out.println(App.Cyan +"La cantidad de ventas recaudadas por cada rol fue:"+ App.Restorer+"\n SUP: "+App.Yellow+"$"+App.Restorer+recaudadoSup+"\n ADC: "+App.Yellow+"$"+App.Restorer+recaudadoAdc+
                        "\n MID: "+App.Yellow+"$"+App.Restorer+recaudadoMid+"\n JG : "+App.Yellow+"$"+App.Restorer+recadudadoJg+"\n TOP: "+App.Yellow+"$"+App.Restorer+recaudadoTop);

        return mensaje;
    }
    @Override
    public String recaudacionTotal(String nombreCuenta) {
        String msg ="";
        double recLanClp=0,recLasClp=0,recEuwClp=0,recKrClp=0,recNaClp=0,recRuClp=0;
        for(int i =0 ; i< lcuentas.getCant();i++){
            Cuentas c = lcuentas.getRegionX(i);
            if(c.getRegionCuenta().equalsIgnoreCase("LAN")){
                double recLan=c.getRpCuenta();
                recLanClp += conversionRpClp(recLan);
            }if(c.getRegionCuenta().equalsIgnoreCase("LAS")){
                double recLas=c.getRpCuenta();
                recLasClp += conversionRpClp(recLas);
            }if(c.getRegionCuenta().equalsIgnoreCase("EUW")){
                double recEuw=c.getRpCuenta();
                recEuwClp += conversionRpClp(recEuw);
            }if(c.getRegionCuenta().equalsIgnoreCase("Kr")){
                double recKr=c.getRpCuenta();
                recKrClp += conversionRpClp(recKr);
            }if(c.getRegionCuenta().equalsIgnoreCase("Na")){
                double recNa=c.getRpCuenta();
                recNaClp += conversionRpClp(recNa);
            }if(c.getRegionCuenta().equalsIgnoreCase("Ru")){
                double recRu=c.getRpCuenta();
                recRuClp += conversionRpClp(recRu);
            }
        }
        System.out.println(App.Cyan +"La cantidad de ventas recaudadas por cada región fue"+ App.Restorer+"\nLAN: "+App.Yellow+"$"+App.Restorer+ recLanClp+ "\nLAS: "+App.Yellow+"$"+App.Restorer + recLasClp + "\nEUW: "+App.Yellow+"$"+App.Restorer+
                            recEuwClp +"\nKR: "+App.Yellow+"$"+App.Restorer +recKrClp+ "\nNA: "+App.Yellow+"$"+App.Restorer +recNaClp +"\nRU: "+App.Yellow+"$"+App.Restorer+recRuClp);

        return msg;
    }
    @Override
    public String recaudacionPersonajes(String nombreCuenta) {
        String msg ="";
        double montoCampeon,montoCampeonClp;
        String nombreCampeon;
        for (int i=0; i< lestadistica.getCant();i++){
            Estadisticas e = lestadistica.getEstadisticaX(i);
            nombreCampeon=e.getNombreCampeon();
            montoCampeon=e.getTotalRecaudado();
            montoCampeonClp=conversionRpClp(montoCampeon);
            System.out.printf(App.Cyan+nombreCampeon +App.Restorer+App.Yellow+" $"+App.Restorer + montoCampeonClp +"\n");
        }return msg;
    }

    public String personajesXRol(String nombreCuenta){
        String msg ="";
        int adc=0,supp=0,mid=0,top=0,jg=0;
        for(int i =0 ; i<lpersonajes.getCant();i++){
            Personajes p = lpersonajes.getPersonajesX(i);
            if(p.getRol().equalsIgnoreCase("Adc")){
                adc++;
            }if(p.getRol().equalsIgnoreCase("supp")){
                supp++;
            }if(p.getRol().equalsIgnoreCase("mid")){
                mid++;
            }if(p.getRol().equalsIgnoreCase("top")){
                top++;
            }if(p.getRol().equalsIgnoreCase("jg")){
                jg++;
            }
        }
        System.out.println(App.Cyan +"La cantidad de personajes por rol son: "+ App.Restorer+"\nAdc: "+adc+"\nSupp: "+supp+"\nMid: "+mid+"\nTop: "+top+"\nJg: "+jg);
        return msg;
    }

    private String nombreCampeon;
    private String rol;
    private int cantSkins;
    private String datosSkins;
    @Override
    public void agregarPersonajes(String nombreCuenta) {

        Scanner s = new Scanner(System.in);
        System.out.print(App.Cyan+"Ingrese nombre del personaje: "+App.Restorer);
        String name = s.nextLine();
        for(int i=0; i<lpersonajes.getCant();i++){
            while(!existepersonaje(name)==false){
                System.out.println(App.Red + "[ERROR]" +App.Restorer +"Este personaje ya se encuentra.");
                System.out.print(App.Cyan+"Ingrese nombre del personaje: "+App.Restorer);
                name = s.nextLine();
            }
        }
        System.out.print("Ingrese el rol de "+name+": ");
        String rol = s.nextLine();
        while(!verificarRol(rol)){
            System.out.println(App.Red + "[ERROR]" + App.Restorer + " Rol ingresado no valido.");
            System.out.print("Ingrese el rol de "+name+": ");
            rol = s.nextLine();
        }
        System.out.print("Ingrese el numero de skins que posee " + name + ": ");
        int cantSkinsP = s.nextInt();
        while(cantSkinsP<1){
            System.out.println(App.Red + "[ERROR]" + App.Restorer + " El personaje debe poseer al menos una skin.");
            System.out.print("Ingrese el numero de skins que posee " + name + ": ");
            cantSkinsP = s.nextInt();
        }
        String datosSkins = "";
        String nameSkin,calidadSkin;
        for(int i =0;i<cantSkinsP;i++){
            System.out.print("Ingrese el nombre de la skin n"+App.Cyan+(i+1)+App.Restorer+": ");
            if(i==0) {
                s.nextLine();
            }
            nameSkin = s.nextLine();
            System.out.print("Ingrese la calidad de la skin n" + App.Cyan + (i+1) + App.Restorer + ": ");
            calidadSkin = App.ScannerChar();
            while(!verificarCalidad(calidadSkin)){
                System.out.println(App.Red + "[ERROR]" + App.Restorer + " Calidad ingresada no valida.");
                System.out.print("Ingrese la calidad de la skin n" + App.Cyan + (i+1) + App.Restorer + ": ");
                calidadSkin = App.ScannerChar();
            }
            System.out.println(nameSkin + ", " + calidadSkin);
            if((i+1)==cantSkinsP){
                datosSkins += nameSkin+","+calidadSkin;
            }else{
                datosSkins += nameSkin+ ","+calidadSkin+",";
            }
        }
        try {
            ingresarPersonajes(name,rol,cantSkinsP,datosSkins);
            Personajes p = lpersonajes.searchP(name);
            Personajes e = lpersonajes.searchP("Jinx");
            System.out.println(p.toString());
            System.out.println(e.toString());
            System.out.println(App.Green + "Se ha ingresado el personaje " + App.Cyan + name + App.Green + " con exito!" + App.Restorer);
        }catch (Exception e){
            System.out.println(App.Red + "[ERROR] " + App.Restorer + "No se ha podido registrar su personaje.");
            System.out.println(e);
        }

    }
    private static boolean verificarRol(String rol){
        String[] roles = {"TOP","JG","MID","ADC","SUPP"};
        boolean result = false;
        for(int i = 0;i<roles.length;i++){
            if(rol.equalsIgnoreCase(roles[i])){
                result = true;
            }
        }
        return result;
    }
    private static boolean verificarCalidad(String calidad){
        String[] calidades = {"M","D","L","E","N"};
        boolean result = false;
        for(int i =0;i<calidades.length;i++){
            if(calidad.equalsIgnoreCase(calidades[i])){
                result = true;
            }
        }
        return result;
    }
    public void agregarSkins(String nombreCuenta) {
        Scanner sk = new Scanner(System.in);
        System.out.print(App.Cyan+"Ingrese nombre del personaje: "+App.Restorer);
        String name = sk.nextLine();
        for(int i=0; i<lpersonajes.getCant();i++){
            while(!existepersonaje(name)){
                System.out.println(App.Red + "[ERROR]" +App.Restorer +" Este personaje no se encuentra.");
                System.out.print(App.Cyan+"Ingrese nombre del personaje: "+App.Restorer);
                name = sk.nextLine();
            }
        }
        String Rol="";
        for(int i=0; i<lpersonajes.getCant();i++){
            if(name.equals(lpersonajes.getPersonajesX(i))){
                Personajes p = lpersonajes.getRolX(i);
                Rol = p.getRol();
            }
        }
        int contSkins = 1;
        String calidadSkin="",nameSkin="";
        String Skins = "";
        System.out.print(App.Cyan+"Ingrese el nombre de la skin del personaje y la calidad: "+App.Restorer);
        String nameSkins = sk.nextLine();
        String [] partes = nameSkins.split(",");

        nameSkin=partes[0];
        calidadSkin=partes[1];
        Skins = nameSkin+ ", "+calidadSkin;



        nombreCampeon=name;
        rol = Rol;
        cantSkins += 1;
        datosSkins=Skins;
        Personajes Np = new Personajes(nombreCampeon,rol,cantSkins,datosSkins);
        lpersonajes.addPersonajes(Np);
        System.out.println(App.Green+"[EXITO]"+App.Restorer+"La skin ha sido agregada.");
    }

    @Override
    public String blockPlayer(String nombreCuenta) {
        String mensaje="";
        Cuentas c = lcuentas.searchC(nombreCuenta);
        Scanner s = new Scanner (System.in);
        System.out.print(App.Cyan+"Nombre de cuenta a bloquear: "+App.Restorer);
        String NombreCuenta= s.nextLine();
        while(!existeCuenta(NombreCuenta)){
            System.out.println(App.Red + "[ERROR] " + App.Restorer + "La cuenta ingresada no es válida.");
            System.out.print(App.Cyan+"Nombre de cuenta a bloquear: "+App.Restorer);
            NombreCuenta = s.nextLine();
        }
        System.out.println("Cuenta seleccionada: " + App.Cyan + NombreCuenta + App.Restorer);
        System.out.print("¿Desea realizar el bloqueo? (Y/N): ");
        String charr = App.ScannerChar();
        while (!charr.equalsIgnoreCase("Y") && !charr.equalsIgnoreCase("N")) {
            System.out.println(App.Red + "[ERROR] " + App.Restorer + "La opcion indicada no es valida.");
            System.out.print("¿Desea realizar el bloqueo? (Y/N): ");
            charr = App.ScannerChar();
        }
        if(charr.equalsIgnoreCase("Y")){
            lcuentas.deleteC(NombreCuenta);
            System.out.println(App.Green+"[EXITO]"+App.Restorer+ "La cuenta" + NombreCuenta + "ha sido bloqueada.");
        }
        return mensaje;
    }

    @Override
    public String infoCuentas(String nombreCuenta) {
        String msg="";
        int aux,cuentaMenor,cuentaMayor;
        for(int i =0 ; i < lcuentas.getCant();i++) {
            Cuentas c = lcuentas.getCuentaX(i);
            for (int j = 0; j < lcuentas.getCant() - 1; j++) {
                if (c.getNivelCuenta() < c.getNivelCuenta() + 1) {
                    aux = c.getRpCuenta();
                    cuentaMenor = c.getNivelCuenta();
                    cuentaMayor = c.getNivelCuenta() + 1;
                    cuentaMenor = cuentaMayor;
                    cuentaMayor = aux;
                }
            }
            for (int k = 0; k < lcuentas.getCant()-1; k++) {
                System.out.println(App.Cyan+c.getNickCuenta()+App.Restorer +" "+ c.getNivelCuenta());
            }
        }
        return msg;
    }


    private double conversionRpClp(double monto){
        double val_clp= 6.15;
        return (monto*val_clp);
    }
    private boolean validarRol(String rolPersonaje){
        for (int i=0; i<lpersonajes.getCant();i++){
            Personajes p = lpersonajes.getRolX(i);
            if(rolPersonaje.equalsIgnoreCase(p.getRol())){
                return true;
            }
        }
        return false;
    }
    private boolean existeCuenta(String nombreCuenta) {
        for(int i = 0; i<lcuentas.getCant(); i++){
            Cuentas c = lcuentas.getCuentaX(i);
            if(nombreCuenta.equalsIgnoreCase(c.getNombreCuenta())){
                return true;
            }
        }
        return false;
    }
    @Override
    public void sobreEscribirDtos() throws IOException {
        ArchivoSalida arch = new ArchivoSalida("Cuentas.txt");
        for(int i = 0; i<lcuentas.getCant();i++){
            Cuentas c = lcuentas.getCuentaX(i);
            Registro reg = new Registro(8);
            reg.agregarCampo(c.getNombreCuenta());
            reg.agregarCampo(c.getContrasenaCuenta());
            reg.agregarCampo(c.getNickCuenta());
            reg.agregarCampo(c.getNivelCuenta());
            reg.agregarCampo(c.getRpCuenta());
            reg.agregarCampo(c.getTotalPersonajes());
            reg.agregarCampo(c.getSkins());
            reg.agregarCampo(c.getRegionCuenta());
            arch.writeRegistro(reg);
        }
        arch.close();
        ArchivoSalida arch1 = new ArchivoSalida("Estadisticas.txt");
        for(int i = 0; i<lestadistica.getCant();i++){
            Estadisticas e = lestadistica.getEstadisticaX(i);
            Registro reg = new Registro(2);
            reg.agregarCampo(e.getNombreCampeon());
            reg.agregarCampo(e.getTotalRecaudado());
            arch1.writeRegistro(reg);
        }
        arch1.close();
        ArchivoSalida arch2 = new ArchivoSalida("Personajes.txt");
        for(int i = 0; i<lpersonajes.getCant() ;i++){
            Personajes p = lpersonajes.getPersonajesX(i);
            Registro reg = new Registro(4);
            reg.agregarCampo(p.getNombreCampeon());
            reg.agregarCampo(p.getRol());
            reg.agregarCampo(p.getCantSkins());
            reg.agregarCampo(p.getDatosSkins());
            arch2.writeRegistro(reg);
        }
        arch2.close();

    }

}

