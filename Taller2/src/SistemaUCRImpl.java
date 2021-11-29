import Clases.*;
import Herencia.*;
public class SistemaUCRImpl implements SistemaUCR{
    listaEstudiantes lEstudiantes;
    listaAsignaturas lAsignaturas;
    listaParalelo lParalelo;
    listaProfesores lProfesores;

    public SistemaUCRImpl(){
        lEstudiantes = new listaEstudiantes(1000);
        lAsignaturas = new listaAsignaturas(1000);
        lParalelo = new listaParalelo(1000);
        lProfesores = new listaProfesores(1000);
    }

    @Override
    public boolean ingresarEstudiante(String rut, String correo, int nivel, String contrasena) {
        Estudiante estudiante = new Estudiante(rut,correo,nivel,contrasena);
        lEstudiantes.anadirEstudiante(estudiante);
        return false;
    }

    @Override
    public boolean ingresarAsignaturasCursadas(String rutEstudiante, String codigo, Double notafinal) {
        Estudiante estudiante = lEstudiantes.buscarEstudiante(rutEstudiante);
        if(estudiante != null){
            AsignaturasCursadas ac = new AsignaturasCursadas(codigo,notafinal);
            estudiante.getlAcursadas().anadirAsignaturaC(ac);
        }
        return false;

    }

    @Override
    public boolean ingresarAsignaturasInscritas(String rutEstudiante, String codigoAsignaturaInscrita, String paralelo) {
        Estudiante estudiante = lEstudiantes.buscarEstudiante(rutEstudiante);
        if(estudiante != null){
            AsignaturasInscritas ai = new AsignaturasInscritas(codigoAsignaturaInscrita,paralelo);
            estudiante.getlAinscritas().anadirAsignaturaI(ai);
        }
        return false;
    }

    @Override
    public boolean ingresarAsignaturaOpcional(String codigo, String nombreAsignatura, int cantCreditos, String tipoAsignatura, int cantidadPreRequisitos) {
        AsignaturasOpcionales asignaturaOpcionales = new AsignaturasOpcionales(codigo,nombreAsignatura,cantCreditos,tipoAsignatura,cantidadPreRequisitos);
        lAsignaturas.anadirAsignatura(asignaturaOpcionales);
        return false;
    }

    @Override
    public boolean ingresarAsignaturaObligatoria(String codigo, String nombreAsignatura, int cantCreditos, String tipoAsignatura, int nivel, int cantidadPreRequisitos, String codigos) {
        AsignaturaObligatorias asignaturaObligatorias = new AsignaturaObligatorias(codigo,nombreAsignatura,cantCreditos,tipoAsignatura,nivel,cantidadPreRequisitos,codigos);
        return false;
    }

    @Override
    public boolean ingresarProfesores(String rut, String correo, String contrasena, int salario) {
        Profesores profesor = new Profesores(rut,correo,contrasena,salario);
        lProfesores.anadirProfesor(profesor);
        return false;
    }

    @Override
    public boolean acociarParalelo(String paralelo, String codigo, String rutProfesor) {
        Paralelo paralelos = new Paralelo(paralelo);
        Asignaturas asignatura = lAsignaturas.buscarAsignatura(codigo);
        Profesores profesor = lProfesores.buscarProfesor(rutProfesor);
        if(asignatura != null && profesor != null){
            paralelos.setAsignaturas(asignatura);
            paralelos.setProfesor(profesor);
        }
        lParalelo.anadirParalelo(paralelos);
        return false;
    }

    @Override
    public int inicioSesion(String correo, String pass) {
        Estudiante estudiante = lEstudiantes.buscarEstudianteCorreo(correo);
        Profesores profesor = lProfesores.buscarProfesorCorreo(correo);
        if(correo.equals("Admin") && pass.equals("GHI_789")){
            return 0;
        }else if(correo.equals(estudiante.getCorreo()) && pass.equals(estudiante.getContrasena())){
            return 1;
        }else if(correo.equals(profesor.getCorreo()) && pass.equals(profesor.getContrasena())){
            return 2;
        }else{
            return -1;
        }
    }

}
