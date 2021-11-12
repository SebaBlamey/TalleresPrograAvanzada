package Clases;

public class Estudiantes {
    private String rutEstudiante;
    private String correoEstudiante;
    private int nivelEstudiante;
    private String contrasenaEstudiante;
    private int asignaturasCursadas;
    private String codigoNota;
    private int asignaturasInscritas;

    public Estudiantes(String rutEstudiante, String correoEstudiante, int nivelEstudiante, String contrasenaEstudiante, int asignaturasCursadas, String codigoNota, int asignaturasInscritas) {
        this.rutEstudiante = rutEstudiante;
        this.correoEstudiante = correoEstudiante;
        this.nivelEstudiante = nivelEstudiante;
        this.contrasenaEstudiante = contrasenaEstudiante;
        this.asignaturasCursadas = asignaturasCursadas;
        this.codigoNota = codigoNota;
        this.asignaturasInscritas = asignaturasInscritas;
    }

    public String getRutEstudiante() {
        return rutEstudiante;
    }

    public void setRutEstudiante(String rutEstudiante) {
        this.rutEstudiante = rutEstudiante;
    }

    public String getCorreoEstudiante() {
        return correoEstudiante;
    }

    public void setCorreoEstudiante(String correoEstudiante) {
        this.correoEstudiante = correoEstudiante;
    }

    public int getNivelEstudiante() {
        return nivelEstudiante;
    }

    public void setNivelEstudiante(int nivelEstudiante) {
        this.nivelEstudiante = nivelEstudiante;
    }

    public String getContrasenaEstudiante() {
        return contrasenaEstudiante;
    }

    public void setContrasenaEstudiante(String contrasenaEstudiante) {
        this.contrasenaEstudiante = contrasenaEstudiante;
    }

    public int getAsignaturasCursadas() {
        return asignaturasCursadas;
    }

    public void setAsignaturasCursadas(int asignaturasCursadas) {
        this.asignaturasCursadas = asignaturasCursadas;
    }

    public String getCodigoNota() {
        return codigoNota;
    }

    public void setCodigoNota(String codigoNota) {
        this.codigoNota = codigoNota;
    }

    public int getAsignaturasInscritas() {
        return asignaturasInscritas;
    }

    public void setAsignaturasInscritas(int asignaturasInscritas) {
        this.asignaturasInscritas = asignaturasInscritas;
    }
}
