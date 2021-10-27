public interface SistemaRitoGames {
    int inicioSesion(String nombreCuenta, String contrasenaCuenta);
    boolean ingresarCuenta(String nombreCuenta, String contrasenaCuenta, String nickCuenta, int nivelCuenta, int rpCuenta, int totalPersonajes, String skinsCuenta, String regionCuenta);
    boolean ingresarPersonajes(String nombreCampeon, String rol, int cantSkins, String skins);
    boolean ingresarEstadisticas(String nombreCampeon, int totalRecaudado);

    String mostrarInventario(String nombreCuenta);
    void agregarRP(String nombreCuenta, int monto);
    int mostrarRP(String nombreCuenta);
    String skinsDisponibles(String nombreCuenta);
    void inforUsuario(String nombreCuenta);
    String comprarSkin(String nombreCuenta);
    String comprarPersonaje(String nombreCuenta);

    String recaudacionRol(String rol);
    int recaudacionTotal(String nombreCuenta);
    String recaudacionPersonajes(String nombreCuenta);
    String personajesVentasXRol (String nombreCuenta);
    String personajesXRol(String nombreCuenta);
    boolean agregarPersonajes (String nombreCampeon, String rol, int cantSkins, String  DatosSkin);
    boolean agregarSkins(String nombreCampeon, String nameSkin,int valor, String calidad);
    String blockPlayer(String nombreCuenta);
    String infoCuentas(String nombreCuenta);

}
