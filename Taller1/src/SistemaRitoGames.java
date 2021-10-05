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
}
