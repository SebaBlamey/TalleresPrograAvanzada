public interface SistemaRitoGames {
    public int inicioSesion(String nombreCuenta, String contrasenaCuenta);
    public boolean ingresarCuenta(String nombreCuenta, String contrasenaCuenta, String nickCuenta, int nivelCuenta, int rpCuenta, int totalPersonajes, String skinsCuenta, String regionCuenta);
    public boolean ingresarPersonajes(String nombreCampeon, String rol, int cantSkins, String skins);
    public boolean ingresarEstadisticas(String nombreCampeon, int totalRecaudado);
}
