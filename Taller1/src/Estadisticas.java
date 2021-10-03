class Estadisticas {
    private String nombreCampeon;
    private int totalRecaudado;

    public Estadisticas(String nombreCampeon, int totalRecaudado) {
        this.nombreCampeon = nombreCampeon;
        this.totalRecaudado = totalRecaudado;
    }

    public String getNombreCampeon() {
        return nombreCampeon;
    }

    public void setNombreCampeon(String nombreCampeon) {
        this.nombreCampeon = nombreCampeon;
    }

    public int getTotalRecaudado() {
        return totalRecaudado;
    }

    public void setTotalRecaudado(int totalRecaudado) {
        this.totalRecaudado = totalRecaudado;
    }

    @Override
    public String toString() {
        return "Estadisticas{" +
                "nombreCampeon='" + nombreCampeon + '\'' +
                ", totalRecaudado=" + totalRecaudado +
                '}';
    }
}
