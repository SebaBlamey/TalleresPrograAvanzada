import java.io.IOException;

public interface SistemaRitoGames {
    /**
     * Function that is used to enter the session, from here it is forwarded to the User Menu or the Administrator Menu
     * @param nombreCuenta String containing the name of the account entered by the user.
     * @param contrasenaCuenta String containing the password entered by the user.
     * @return it can return 2, 1 or -1 depending on the result.
     */
    int inicioSesion(String nombreCuenta, String contrasenaCuenta);

    /**
     * Function used to register new users or when storing the data read from the txt file.
     * @param nombreCuenta String containing the name entered by the new user.
     * @param contrasenaCuenta String containing the password entered by the new user.
     * @param nickCuenta String containing the nick entered by the new user.
     * @param nivelCuenta Int that contains the level that the user has, as the account is being created, the level is 0.
     * @param rpCuenta Int that contains the RP that the user has, as the account is being created, the RP is 0.
     * @param totalPersonajes Int that contains the total number of characters that the user has, as the account is being created, the number is 0.
     * @param skinsCuenta String that contains the total number of skins that the user has, as the account is being created, the number is 0.
     * @param regionCuenta String containing the Region entered by the new user.
     * @return true or false
     */
    boolean ingresarCuenta(String nombreCuenta, String contrasenaCuenta, String nickCuenta, int nivelCuenta, int rpCuenta, int totalPersonajes, String skinsCuenta, String regionCuenta);

    /**
     * Function that stores the delivered data and stores it in its respective class list.
     * @param nombreCampeon String containing the champion's name.
     * @param rol String containing the champion's role.
     * @param cantSkins Int that contains the amount of skins of the champion.
     * @param skins String where the skins data is stored.
     * @return true or false
     */
    boolean ingresarPersonajes(String nombreCampeon, String rol, int cantSkins, String skins);

    /**
     * Function that stores the delivered data and stores it in its respective class list.
     * @param nombreCampeon String containing the champion's name.
     * @param totalRecaudado Int that contains the amount of money raised by the character.
     * @return true or fals
     */
    boolean ingresarEstadisticas(String nombreCampeon, int totalRecaudado);

    /**
     * Function that prints the user's complete inventory on screen.
     * @param nombreCuenta String that contains the username that the program is using.
     * @return a String
     */
    String mostrarInventario(String nombreCuenta);

    /**
     * Function used to add RP to the user's account.
     * @param nombreCuenta String that contains the username that the program is using.
     * @param monto Int that indicates the amount that the user wants to add to his account.
     */
    void agregarRP(String nombreCuenta, int monto);

    /**
     * Function that is responsible for showing the amount of RP that the user has.
     * @param nombreCuenta String that contains the username that the program is using.
     * @return an Int
     */
    int mostrarRP(String nombreCuenta);

    /**
     * Function that prints all the available skins that the user can buy on the screen.
     * @param nombreCuenta String that contains the username that the program is using.
     * @return a String
     */
    String skinsDisponibles(String nombreCuenta);

    /**
     * Function that displays user information.
     * @param nombreCuenta String that contains the username that the program is using.
     */
    void inforUsuario(String nombreCuenta);

    /**
     * Function used to buy skins.
     * @param nombreCuenta String that contains the username that the program is using.
     * @return a String.
     */
    String comprarSkin(String nombreCuenta);

    /**
     * Function used to buy Characters.
     * @param nombreCuenta String that contains the username that the program is using.
     * @return a String.
     */
    String comprarPersonaje(String nombreCuenta);

    /**
     * Function used to display the collection by role.
     * @param nombreCuenta String that contains the username that the program is using.
     * @return a String.
     */
    String recaudacionRol(String nombreCuenta);

    /**
     * Function used to display the collection by region.
     * @param nombreCuenta String that contains the username that the program is using.
     * @return a String.
     */
    String recaudacionTotal(String nombreCuenta);

    /**
     * Function used to display the collection per character.
     * @param nombreCuenta String that contains the username that the program is using.
     * @return a String.
     */
    String recaudacionPersonajes(String nombreCuenta);

    /**
     * Function used to display the number of characters per role.
     * @param nombreCuenta String that contains the username that the program is using.
     * @return a String.
     */
    String personajesXRol(String nombreCuenta);

    /**
     * Function used to add a character.
     * @param nombreCuenta String that contains the username that the program is using.
     */
    void agregarPersonajes(String nombreCuenta);

    /**
     * Function used to add a skin.
     * @param nombreCuenta String that contains the username that the program is using.
     */
    void agregarSkins(String nombreCuenta);

    /**
     * Function used to block a player's account.
     * @param nombreCuenta String that contains the username that the program is using.
     * @return a String.
     */
    String blockPlayer(String nombreCuenta);

    /**
     * Function used to display in decreasing order the player's accounts.
     * @param nombreCuenta String that contains the username that the program is using.
     * @return a String.
     */
    String infoCuentas(String nombreCuenta);

    void sobreEscribirDtos() throws IOException;

}
