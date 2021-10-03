class listaEstadistica {
    private int cant;
    private int max;
    private Estadisticas[] le;

    public listaEstadistica(int max) {
        this.cant = cant;
        this.max = max;
        le = new Estadisticas[max];
    }

    public int getCant() {
        return cant;
    }

    public void setCant(int cant) {
        this.cant = cant;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public Estadisticas[] getLe() {
        return le;
    }

    public void setLe(Estadisticas[] le) {
        this.le = le;
    }
    public boolean addEstadistica(Estadisticas estadisticas){
        if(cant<max){
            le[cant] = estadisticas;
            cant++;
            return true;
        }
        else{
            return false;
        }
    }
    @Override
    public String toString(){
        return "listaEstadistica{}";
    }
}
