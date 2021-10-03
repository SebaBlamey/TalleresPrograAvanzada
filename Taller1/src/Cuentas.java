class Cuentas {
    private String nombreCuenta;
    private String contrasenaCuenta;
    private String nickCuenta;
    private int nivelCuenta;
    private int rpCuenta;
    private int totalPersonajes;
    private String Skins;
    private String regionCuenta;

    public Cuentas(String nombreCuenta, String contrasenaCuenta, String nickCuenta, int nivelCuenta, int rpCuenta, int totalPersonajes,  String skins, String region) {
        this.nombreCuenta = nombreCuenta;
        this.contrasenaCuenta = contrasenaCuenta;
        this.nickCuenta = nickCuenta;
        this.nivelCuenta = nivelCuenta;
        this.rpCuenta = rpCuenta;
        this.totalPersonajes = totalPersonajes;
        this.Skins = skins;
        this.regionCuenta = region;
    }

    public String getNombreCuenta() {
        return nombreCuenta;
    }

    public void setNombreCuenta(String nombreCuenta) {
        this.nombreCuenta = nombreCuenta;
    }

    public String getContrasenaCuenta() {
        return contrasenaCuenta;
    }

    public void setContrasenaCuenta(String contrasenaCuenta) {
        this.contrasenaCuenta = contrasenaCuenta;
    }

    public String getNickCuenta() {
        return nickCuenta;
    }

    public void setNickCuenta(String nickCuenta) {
        this.nickCuenta = nickCuenta;
    }

    public int getNivelCuenta() {
        return nivelCuenta;
    }

    public void setNivelCuenta(int nivelCuenta) {
        this.nivelCuenta = nivelCuenta;
    }

    public int getRpCuenta() {
        return rpCuenta;
    }

    public void setRpCuenta(int rpCuenta) {
        this.rpCuenta = rpCuenta;
    }

    public int getTotalPersonajes() {
        return totalPersonajes;
    }

    public void setTotalPersonajes(int totalPersonajes) {
        this.totalPersonajes = totalPersonajes;
    }



    public String getSkins() {
        return Skins;
    }

    public void setSkins(String skins) {
        Skins = skins;
    }

    public String getRegionCuenta() {
        return regionCuenta;
    }

    public void setRegionCuenta(String regionCuenta) {
        this.regionCuenta = regionCuenta;
    }

    @Override
    public String toString() {
        return "Cuentas{" +
                "nombreCuenta='" + nombreCuenta + '\'' +
                ", contrasenaCuenta='" + contrasenaCuenta + '\'' +
                ", nickCuenta='" + nickCuenta + '\'' +
                ", nivelCuenta=" + nivelCuenta +
                ", rpCuenta=" + rpCuenta +
                ", totalPersonajes=" + totalPersonajes +
                ", Skins='" + Skins + '\'' +
                ", regionCuenta='" + regionCuenta + '\'' +
                '}';
    }
}
