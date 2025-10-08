public class CostoFabricacion {
    
    private String pais;
    private int costoImportacion; 

    
    public CostoFabricacion(String pais, int costoImportacion) {
        this.pais = pais;
        this.costoImportacion = costoImportacion;
    }
    
    public int obtenerCostoImportacion() {
        return this.costoImportacion;
    }
    
    public String obtenerPais() {
        return this.pais;
    }



    @Override
    public String toString() {
        return "País: " + this.pais + 
               "\nImportación: $" + this.costoImportacion;
    }
}