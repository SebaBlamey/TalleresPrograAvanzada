import java.util.Arrays;

class listaCuentas {
    private int cant;
    private int max;
    private Cuentas[] lc;

    public listaCuentas(int max){
        cant = 0;
        this.max = max;
        lc = new Cuentas[max];
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

    public Cuentas[] getLc() {
        return lc;
    }

    public void setLc(Cuentas[] lc) {
        this.lc = lc;
    }

    public boolean addCuentas(Cuentas cuenta){
        if(cant<max){
            lc[cant] = cuenta;
            cant++;
            return true;
        }
        else{
            return false;
        }
    }

    public Cuentas searchC(String nombreCuenta){
        for(int i =0;i<cant;i++){
            if(lc[i].getNombreCuenta().equals(nombreCuenta)){
                return lc[i];
            }
        }
        return null;
    }

    public boolean deleteC(String nombreCuenta){
        int i=0;
        for(int x =0 ; x<cant;x++){
            if(lc[x].getNombreCuenta().equals(nombreCuenta)){
                i=x;
                break;
            }
        }
        if(i==cant){
            return false;
        }
        else{
            lc[i]=null;
            for(int j=i; j<cant-1;j++){
                lc[j]=lc[j+1];
            }
            cant--;
            return true;
        }
    }

    public Cuentas getRegionX(int x){
        if(x<cant){
            return lc[x];
        }else{
            return null;
        }
    }

    public Cuentas getCuentaX(int x){
        if(x<cant){
            return lc[x];
        }else{
            return null;
        }
    }

    @Override
    public String toString() {
        return "listaCuentas{}";
    }
}
