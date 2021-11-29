public interface SistemaUCR {
    boolean ingresarEstudiante(String rut, String correo, int nivel, String contrasena);
    boolean ingresarAsignaturasCursadas(String rutEstudiante,String codigo,Double notafinal);
    boolean ingresarAsignaturasInscritas(String rutEstudiante,String codigoAsignaturaInscrita, String paralelo);
    boolean ingresarAsignaturaOpcional(String codigo, String nombreAsignatura, int cantCreditos, String tipoAsignatura, int cantidadPreRequisitos);
    boolean ingresarAsignaturaObligatoria(String codigo, String nombreAsignatura, int cantCreditos, String tipoAsignatura, int nivel, int cantidadPreRequisitos, String codigos);
    boolean ingresarProfesores(String rut,String correo,String contrasena, int salario);
    boolean acociarParalelo(String paralelo, String codigo, String rutProfesor);

    int inicioSesion(String correo, String pass);
}
