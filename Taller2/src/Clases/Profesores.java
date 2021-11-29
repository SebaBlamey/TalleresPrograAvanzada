package Clases;

public class Profesores {
    private String rut;
    private String correo;
    private String contrasena;
    private int salario;

    public Profesores(String rut, String correo, String contrasena, int salario) {
        this.rut = rut;
        this.correo = correo;
        this.contrasena = contrasena;
        this.salario = salario;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public int getSalario() {
        return salario;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "Profesores{" +
                "rut='" + rut + '\'' +
                ", correo='" + correo + '\'' +
                ", contrasena='" + contrasena + '\'' +
                ", salario=" + salario +
                '}';
    }
}
