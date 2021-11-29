package Clases;

public class AsignaturasInscritas {
    private String codigo;
    private String codigoParalelo;

    public AsignaturasInscritas(String codigo, String codigoParalelo) {
        this.codigo = codigo;
        this.codigoParalelo = codigoParalelo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigoParalelo() {
        return codigoParalelo;
    }

    public void setCodigoParalelo(String codigoParalelo) {
        this.codigoParalelo = codigoParalelo;
    }

    @Override
    public String toString() {
        return "AsignaturasInscritas{" +
                "codigo='" + codigo + '\'' +
                ", codigoParalelo='" + codigoParalelo + '\'' +
                '}';
    }
}
