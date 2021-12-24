public interface Sistema {
    void ingresarCliente(String rut,String nombre, String apellido, Double saldo, String localizacion);
    void ingresarLocalizacion(String nombre);
    boolean ingresarEnvioD(String codigo,String tipo, String rutRemitente, String rutDestinatario, Double peso, Double grosor);
    boolean ingresarEnvioE(String codigo, String tipo, String rutRemitente, String rutDestinatario, Double peso, Double largo, Double ancho, Double profundo);
    boolean ingresarEnvioV(String codigo, String tipo, String rutRemitente, String rutDestinatario, Double peso, String material);

    int inicioSesion(String rut, String contrasena);
    boolean registro(String rut, String nombre, String apellido, Double saldo, String localizacion);

    boolean verificarDatos(String tipo);
    boolean realizarEntrega(String tipo,String rutRemitente, String rutDestinatario, Double total);
    boolean recargarSaldo(String rut,Double montoSumado);
    String desplegarEntregas(String rut);

    String entregasPorTipo();
    String entregasPorLocalizacion();
    String registroGanancias();
}
