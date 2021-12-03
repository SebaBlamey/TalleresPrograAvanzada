import java.io.IOException;
import java.util.Date;

public interface SistemaUCR {
    /**
     * Function used to register new students or when storing the data read from the txt file.
     * @param rut  String containing the rut entered by the new user.
     * @param correo  String containing the email entered by the new user.
     * @param nivel  Int containing the level entered by the new user.
     * @param contrasena  String containing the password entered by the new user.
     * @return True or false depending on whether it could be entered
     */
    boolean ingresarEstudiante(String rut, String correo, int nivel, String contrasena);

    /**
     * Function used to register new subjects or when storing the data read from the txt file.
     * @param rutEstudiante String that contains the rout of the student who took the course
     * @param codigo String that contains the code of the subject
     * @param notafinal String that contains the final note of the subject
     * @return True or false depending on whether it could be entered
     */
    boolean ingresarAsignaturasCursadas(String rutEstudiante,String codigo,Double notafinal);

    /**
     * Function used to register new enrolled subject or when storing the data read from the txt file.
     * @param rutEstudiante String that contains the rout of the student who registered in that subject
     * @param codigoAsignaturaInscrita String that contains the code of the enrolled subject
     * @param paralelo String that contains the number of the parallel where the subject is located
     * @return True or false depending on whether it could be entered
     */
    boolean ingresarAsignaturasInscritas(String rutEstudiante,String codigoAsignaturaInscrita, String paralelo);

    /**
     * Function used to register new optional subjects enrolled subject or when storing the data read from the txt file.
     * @param codigo String that contains the subject code
     * @param nombreAsignatura String that contains the subject name
     * @param cantCreditos Int that contains the number of credits the subject is worth
     * @param tipoAsignatura String that contains the subject type
     * @param cantidadPreRequisitos Int that contains the amount of prerequisite credits needed to enter
     * @return True or false depending on whether it could be entered
     */
    boolean ingresarAsignaturaOpcional(String codigo, String nombreAsignatura, int cantCreditos, String tipoAsignatura, int cantidadPreRequisitos);

    /**
     * Function used to register new optional subjects enrolled subject or when storing the data read from the txt file.
     * @param codigo String that contains the subject code
     * @param nombreAsignatura String that contains the subject name
     * @param cantCreditos Int that contains the number of credits the subject is worth
     * @param tipoAsignatura String that contains the subject type
     * @param cantidadPreRequisitos Int that contains the amount of prerequisite credits needed to enter
     * @param codigos String that contains the codes of the subjects that the student needs to have passed in order to enroll
     * @return True or false depending on whether it could be entered
     */
    boolean ingresarAsignaturaObligatoria(String codigo, String nombreAsignatura, int cantCreditos, String tipoAsignatura, int nivel, int cantidadPreRequisitos, String codigos);

    /**
     * Function used to register new Teachers or when storing the data read from the txt file.
     * @param rut String containing the teacher's RUT
     * @param correo String containing the teacher's email
     * @param contrasena String containing the teacher's password
     * @param salario String containing the teacher's salary
     * @return True or false depending on whether it could be entered
     */
    boolean ingresarProfesores(String rut,String correo,String contrasena, int salario);

    /**
     * Function to associate a parallel with the subject and the corresponding teacher
     * @param paralelo String containing the number of the parallel
     * @param codigo String containing the number of the subject
     * @param rutProfesor String containing the teacher's rut
     * @return True or false depending on whether it could be entered
     */
    boolean acociarParalelo(String paralelo, String codigo, String rutProfesor);

    /**
     * Function that is used to enter the session, from here it is forwarded to the Student Menu, Teacher Menu or Menu Administrator
     * @param correo String containing the email of the account entered by the user.
     * @param pass String containing the password entered by the user.
     * @return it can return 2, 1 or -1 depending on the result.
     */
    int inicioSesion(String correo, String pass);

    /**
     * String that returns the Student's email
     * @param correo String containing the student's email
     * @return a String with the student's email
     */
    String correoCompletoEstudiante(String correo);

    /**
     * String that returns the Teacher's RUT
     * @param correo String containing the Teacher's email
     * @return a String with the Teacher's RUT
     */
    String rutCompletoProfesor(String correo);

    /**
     * Function to obtain the period of the semester in which the user is
     * @param fecha Date containing the date entered from the program
     * @return 0 Almost when the date is at the beginning of the semester. 1 In case the date is in the middle of the
     * semester. 2 In case the date is at the end of the semester. 3 In case the date is at the end of the semester.
     * -1 In case the date entered is not valid or is out of range
     */
    int periodosSemestre(Date fecha);

    // -- ESTUDIANTE --

    /**
     * Function that displays the subjects available to the student.
     * @param corre String containing the student's email
     */
    void DesplegarAsignaturas(String corre);

    /**
     * Function that verifies that the code entered by the student is valid and that it corresponds to a subject that
     * the student can take
     * @param correo String containing the student's email
     * @param codigo String containing the subject's code
     * @return True or False depending on whether the code is valid or not
     */
    boolean codigoValido(String correo, String codigo);

    /**
     * Function that displays the parallels that the entered subject has available
     * @param correo String containing the student's email
     * @param codigo String containing the subject's code
     */
    void DesplegarParalelos(String correo,String codigo);

    /**
     * Function that enrolls the student in the subject and given parallel
     * @param correo String containing the student's email
     * @param codigo String containing the subject's code
     * @param numeroParalelo Int that contains the number of the parallel
     * @return True or False depending on whether the inscription could be made
     */
    boolean InscribirParalelo(String correo, String codigo, int numeroParalelo);

    /**
     * Function that displays that returns a String with the subjects that the Student has registered
     * @param correo String containing the student's email
     * @return String with the enrolled subjects
     */
    String asignaturasInscritas(String correo);

    /**
     * Function to delete the subject selected by the student.
     * @param correo String containing the student's email
     * @param codigo String containing the subject code
     * @return True or False depending on whether the subject could be eliminated or not
     */
    boolean eliminarAsignatura(String correo, String codigo);

    // -- PROFESOR --

    /**
     * Function that returns a String with the parallels where the teacher dictates
     * @param rut String containing the teacher's RUT
     * @return String that has the parallels where the teacher dictates
     */
    String desplegarParalelosProfesor(String rut);

    /**
     * Function that returns a String a list with the students that are enrolled in the parallel entered by the teacher
     * @param numeroParalelo String containing the number of the parallel
     * @param codigoAsignatura String that contains the subject code
     * @return a String that has the list of students who are enrolled in the parallel
     */
    String desplegarChequeoAlumnos(String numeroParalelo, String codigoAsignatura);

    /**
     * Function to enter the final grade that a student obtained in the parallel entered by the teacher
     * @param rutAlumno String containing the student's RUT
     * @param notaFinal Double containing the student's final grade
     * @param numeroParalelo int containing the parallel number
     * @param codigoAsignatura String containing the subject code
     * @return True or False depending on whether the note was entered or not
     */
    boolean ingresarNotaFinal(String rutAlumno, double notaFinal,String numeroParalelo, String codigoAsignatura);

    /**
     * Function that creates a txt file with the list of students who have graduated
     * @return an Int with the number of students who graduated
     * @throws IOException
     */
    int ConsolidarSistema() throws IOException;
}
