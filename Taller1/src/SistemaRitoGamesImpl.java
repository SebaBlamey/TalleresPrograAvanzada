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
        System.out.println("===========================================");
        System.out.println("               Skins de " + App.Cyan + nombreCuenta + App.Restorer);
        System.out.println("-------------------------------------------");

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


        System.out.println("===========================================");
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
            System.out.println("Campeon: " + p.getNombreCampeon());
            System.out.println("Skins: ");
            String[] partes = p.getDatosSkins().split(",");
            System.out.println(p.getDatosSkins());
            System.out.println(p.getCantSkins());
            for(int j = 0;j<=(p.getCantSkins());j++){
                try {
                    if (!App.stringCharCheck(partes[j])) {
                        System.out.println("  - " + conversionCalidadSkin(partes[(2*j)+1]) + partes[2*j]);
                    }
                }catch(Exception e){

                }
            }
            //System.out.println("Skins: " + p.getDatosSkins());
            System.out.println("----------------------------");
        }


        return texto;
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
