package Herencia;

public class AsignaturasOpcionales extends Asignaturas{
    private int cantidadPreRequisitos;

    public AsignaturasOpcionales(String codigoAsignatura, String nombreAsignatura, int creditoAsignatura, String tipoAsignatura, int cantidadPreRequisitos) {
        super(codigoAsignatura, nombreAsignatura, creditoAsignatura, tipoAsignatura);
        this.cantidadPreRequisitos = cantidadPreRequisitos;
    }

    public int getCantidadPreRequisitos() {
        return cantidadPreRequisitos;
    }

    public void setCantidadPreRequisitos(int cantidadPreRequisitos) {
        this.cantidadPreRequisitos = cantidadPreRequisitos;
    }

    @Override
    public String toString() {
        return "AsignaturasOpcionales{" +
                "cantidadPreRequisitos=" + cantidadPreRequisitos +
                '}';
    }
}
