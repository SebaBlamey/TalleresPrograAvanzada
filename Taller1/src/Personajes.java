class Personajes {
    private String nombreCampeon;
    private String rol;
    private int cantSkins;
    private String datosSkins;

    public Personajes(String nombreCampeon, String rol, int cantSkins, String skins) {
        this.nombreCampeon = nombreCampeon;
        this.rol = rol;
        this.cantSkins = cantSkins;
        this.datosSkins = skins;
    }

    public String getNombreCampeon() {
        return nombreCampeon;
    }

    public void setNombreCampeon(String nombreCampeon) {
        this.nombreCampeon = nombreCampeon;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public int getCantSkins() {
        return cantSkins;
    }

    public void setCantSkins(int cantSkins) {
        this.cantSkins = cantSkins;
    }

    public String getDatosSkins() {
        return datosSkins;
    }

    public void setDatosSkins(String datosSkins) {
        this.datosSkins = datosSkins;
    }

    @Override
    public String toString() {
        return "Personajes{" +
                "nombreCampeon='" + nombreCampeon + '\'' +
                ", rol='" + rol + '\'' +
                ", cantSkins=" + cantSkins +
                ", datosSkins='" + datosSkins + '\'' +
                '}';
    }
}