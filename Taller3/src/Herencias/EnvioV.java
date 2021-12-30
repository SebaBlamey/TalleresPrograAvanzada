package Herencias;

public class EnvioV extends Envio{
    private String material;

    public EnvioV(String codigo, String tipo, Double peso, String material) {
        super(codigo, tipo, peso);
        this.material = material;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    @Override
    public Double valor() {
        double precioMaterial;

        if(getMaterial().equalsIgnoreCase("Cuero")) precioMaterial = 200.0;
        else if (getMaterial().equalsIgnoreCase("Plástico")) precioMaterial = 150.0;
        else if (getMaterial().equalsIgnoreCase("Tela")) precioMaterial = 100.0;
        else precioMaterial = 0.0;

        return precioMaterial*getPeso()/1000*150;
    }
}
