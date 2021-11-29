package Clases;


public class Estudiante {
    private String rut;
    private String correo;
    private int Nivel;
    private String contrasena;
    int Credito;
    private listaAsignaturasC lAcursadas;
    private listaAsignaturasI lAinscritas;

    public Estudiante(String rut, String correo, int nivel, String contrasena) {
        this.rut = rut;
        this.correo = correo;
        Nivel = nivel;
        this.contrasena = contrasena;
        Credito = 0;
        lAcursadas = new listaAsignaturasC(1000);
        lAinscritas = new listaAsignaturasI(1000);
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

    public int getNivel() {
        return Nivel;
    }

    public void setNivel(int nivel) {
        Nivel = nivel;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public int getCredito() {
        return Credito;
    }

    public void setCredito(int credito) {
        Credito = credito;
    }

    public listaAsignaturasC getlAcursadas() {
        return lAcursadas;
    }

    public void setlAcursadas(listaAsignaturasC lAcursadas) {
        this.lAcursadas = lAcursadas;
    }

    public listaAsignaturasI getlAinscritas() {
        return lAinscritas;
    }

    public void setlAinscritas(listaAsignaturasI lAinscritas) {
        this.lAinscritas = lAinscritas;
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "rut='" + rut + '\'' +
                ", correo='" + correo + '\'' +
                ", Nivel=" + Nivel +
                ", contrasena='" + contrasena + '\'' +
                ", Credito=" + Credito +
                ", lAcursadas=" + lAcursadas +
                ", lAinscritas=" + lAinscritas +
                '}';
    }
}
