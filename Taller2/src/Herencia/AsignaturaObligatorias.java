package Herencia;

public class AsignaturaObligatorias extends  Asignaturas{
    private int nivel;
    private int cantidadPreRequisitos;
    private String codigo;

    public AsignaturaObligatorias(String codigoAsignatura, String nombreAsignatura, int creditoAsignatura, String tipoAsignatura, int nivel, int cantidadPreRequisitos, String codigo) {
        super(codigoAsignatura, nombreAsignatura, creditoAsignatura, tipoAsignatura);
        this.nivel = nivel;
        this.cantidadPreRequisitos = cantidadPreRequisitos;
        this.codigo = codigo;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getCantidadPreRequisitos() {
        return cantidadPreRequisitos;
    }

    public void setCantidadPreRequisitos(int cantidadPreRequisitos) {
        this.cantidadPreRequisitos = cantidadPreRequisitos;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return "AsignaturaObligatorias{" +
                "nivel=" + nivel +
                ", cantidadPreRequisitos=" + cantidadPreRequisitos +
                ", codigo='" + codigo + '\'' +
                '}';
    }
}
