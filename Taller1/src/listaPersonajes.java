class listaPersonajes {
    private int cant;
    private int max;
    private Personajes[] lp;

    public listaPersonajes(int max){
        cant = 0;
        this.max = max;
        lp = new Personajes[max];
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


    public void setLp(Personajes[] lp) {
        this.lp = lp;
    }

    public boolean addPersonajes(Personajes personaje){
        if(cant<max){
            lp[cant] = personaje;
            cant++;
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public String toString(){
        return "listaPersonajes{}";
    }
}
