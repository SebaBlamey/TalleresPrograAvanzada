package Clases;

public class Profesores {
    private String rutProfesor;
    private String correoProfesor;
    private String contraseñaProfesor;
    private float salario;

    public Profesores (String rutProfesor, String correoProfesor, String contraseñaProfesor, float salario){
        this.rutProfesor=rutProfesor;
        this.correoProfesor=correoProfesor;
        this.contraseñaProfesor=contraseñaProfesor;
        this.salario=salario;
    }

    public String getRutProfesor() {
        return rutProfesor;
    }

    public void setRutProfesor(String rutProfesor) {
        this.rutProfesor = rutProfesor;
    }

    public String getCorreoProfesor() {
        return correoProfesor;
    }

    public void setCorreoProfesor(String correoProfesor) {
        this.correoProfesor = correoProfesor;
    }

    public String getContraseñaProfesor() {
        return contraseñaProfesor;
    }

    public void setContraseñaProfesor(String contraseñaProfesor) {
        this.contraseñaProfesor = contraseñaProfesor;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }
}
