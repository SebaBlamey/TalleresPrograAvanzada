package Clases;

public class AsignaturasCursadas {
    private String Codigo;
    private Double Nota;

    public AsignaturasCursadas(String codigo, Double nota) {
        Codigo = codigo;
        Nota = nota;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String codigo) {
        Codigo = codigo;
    }

    public Double getNota() {
        return Nota;
    }

    public void setNota(Double nota) {
        Nota = nota;
    }

    @Override
    public String toString() {
        return "AsignaturasCursadas{" +
                "Codigo='" + Codigo + '\'' +
                ", Nota=" + Nota +
                '}';
    }
}
