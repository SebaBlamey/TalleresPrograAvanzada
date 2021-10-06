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

    public Personajes[] getLp() {
        return lp;
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

    public Personajes searchP(String personaje){
        for(int i=0; i<cant;i++){
            if ((lp[i].getNombreCampeon()).equals(personaje)){
                return lp[i];
            }
        }return null;
    }

    public Personajes searchR(String rol){
        for(int i =0 ; i< cant; i++){
            if(lp[i].getRol().equals(rol)){
                return lp[i];
            }
        }
        return null;
    }

    public Personajes getPersonajesX(int x){
        if(x<cant){
            return lp[x];
        }else{
            return null;
        }
    }

    @Override
    public String toString(){
        return "listaPersonajes{}";
    }
}
