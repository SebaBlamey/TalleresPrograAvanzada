public interface Sistema {
    /**
     * Function used to register new client or when storing the data read from the txt file.
     * @param rut String containing the client's rut
     * @param nombre String containing the client's name
     * @param apellido String containing the client's lastname
     * @param saldo double containing the client's balance
     * @param localizacion String containing the client's localization
     */
    void ingresarCliente(String rut,String nombre, String apellido, Double saldo, String localizacion);

    /**
     * Function used to register new localization or when storing the data read from the txt file.
     * @param nombre String containing the localization's name
     */
    void ingresarLocalizacion(String nombre);

    /**
     * Function used to register a new EnvioD
     * @param codigo String containing the EnvioD's code
     * @param tipo String containing the EnvioD's type
     * @param rutRemitente String that contains the sender's rut
     * @param rutDestinatario String that contains the recipient's rut
     * @param peso double that contains the EnvioD's weight
     * @param grosor double that contains the EnvioD's thickness
     * @return true or false
     */
    boolean ingresarEnvioD(String codigo,String tipo, String rutRemitente, String rutDestinatario, Double peso, Double grosor);

    /**
     * Function used to register a new EnvioE
     * @param codigo String containing the EnvioE's code
     * @param tipo String containing the EnvioE's type
     * @param rutRemitente String that contains the sender's rut
     * @param rutDestinatario String that contains the recipient's rut
     * @param peso double that contains the EnvioE's weight
     * @param largo double that contains the EnvioE's long
     * @param ancho double that contains the EnvioE's broad
     * @param profundo double that contains the EnvioE's depth
     * @return true or false
     */
    boolean ingresarEnvioE(String codigo, String tipo, String rutRemitente, String rutDestinatario, Double peso, Double largo, Double ancho, Double profundo);

    /**
     * Function used to register a new EnvioV
     * @param codigo String containing the EnvioV's code
     * @param tipo String containing the EnvioV's type
     * @param rutRemitente String that contains the sender's rut
     * @param rutDestinatario String that contains the recipient's rut
     * @param peso double that contains the EnvioV's weight
     * @param material String that contains the EnvioV's material
     * @return true or false
     */
    boolean ingresarEnvioV(String codigo, String tipo, String rutRemitente, String rutDestinatario, Double peso, String material);

    /**
     * Function that is used to enter the session, from here it is forwarded to the Cliente Menu or Menu Administrator
     * @param rut String containing the rut of the account entered by the user.
     * @param contrasena String containing the password entered by the user.
     * @return it can return -1, 1 or 2 depending on the result.
     */
    int inicioSesion(String rut, String contrasena);

    /**
     * Function to register new users
     * @param rut String containing the client's rut
     * @param nombre String containing the client's name
     * @param apellido String containing the client's lastname
     * @param saldo double containing the customer's balance (starts at 0)
     * @param localizacion String containing the client's localization
     * @return true or false
     */
    boolean registro(String rut, String nombre, String apellido, Double saldo, String localizacion);

    /**
     * Function that verifies if the type of delivery entered by the client is valid.
     * @param tipo String containing the delivery type.
     * @return
     */
    boolean verificarDatos(String tipo);

    /**
     * Function that makes the delivery with the data entered by the client.
     * @param tipo String containing the delivery type.
     * @param rutRemitente String containing the sender's rut
     * @param rutDestinatario String containing the recipient's rut
     * @param total double that contains the total value of the shipment.
     * @return true or false
     */
    boolean realizarEntrega(String tipo,String rutRemitente, String rutDestinatario, Double total);

    /**
     * Function so that the client can add money to their balance.
     * @param rut String that contains the client's rut.
     * @param montoSumado double containing the total amount to be added
     * @return true or false
     */
    boolean recargarSaldo(String rut,Double montoSumado);

    /**
     * Function that returns a string with all client deliveries
     * @param rut String that contains the client's rut.
     * @return an String
     */
    String desplegarEntregas(String rut);

    /**
     * Function that returns a string that contains all the deliveries classified by their type.
     * @return an String
     */
    String entregasPorTipo();

    /**
     * Function that returns a string that contains all the deliveries classified by their location
     * @return an String
     */
    String entregasPorLocalizacion();

    /**
     * Function that returns all deliveries of all customers registered in the system.
     * @return an String
     */
    String entregasPorCliente();

    /**
     * Function that returns a string that contains the earnings by city and the total earnings of the system.
     * @return an String
     */
    String registroGanancias();

}
