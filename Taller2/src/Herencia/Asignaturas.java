package Herencia;
public class Asignaturas{
    private String CodigoAsignatura;
    private String NombreAsignatura;
    private int CreditoAsignatura;
    private String TipoAsignatura;

    public Asignaturas(String codigoAsignatura, String nombreAsignatura, int creditoAsignatura, String tipoAsignatura){
        CodigoAsignatura = codigoAsignatura;
        NombreAsignatura = nombreAsignatura;
        CreditoAsignatura = creditoAsignatura;
        TipoAsignatura = tipoAsignatura;
    }

    public String getCodigoAsignatura() {
        return CodigoAsignatura;
    }

    public void setCodigoAsignatura(String codigoAsignatura) {
        CodigoAsignatura = codigoAsignatura;
    }

    public String getNombreAsignatura() {
        return NombreAsignatura;
    }

    public void setNombreAsignatura(String nombreAsignatura) {
        NombreAsignatura = nombreAsignatura;
    }

    public int getCreditoAsignatura() {
        return CreditoAsignatura;
    }

    public void setCreditoAsignatura(int creditoAsignatura) {
        CreditoAsignatura = creditoAsignatura;
    }

    public String getTipoAsignatura() {
        return TipoAsignatura;
    }

    public void setTipoAsignatura(String tipoAsignatura) {
        TipoAsignatura = tipoAsignatura;
    }
}