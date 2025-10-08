public class MarcaAutomovil { // Nombre ajustado al requisito de la práctica
    
    private String nombre;
    private int precioBase;
    private CostoFabricacion costoPorPais; // Objeto como atributo

    // Constructor 
    public MarcaAutomovil(String nombre, int precioBase, CostoFabricacion costoPorPais) {
        this.nombre = nombre;
        this.precioBase = precioBase;
        this.costoPorPais = costoPorPais;
    }

    // Métodos para establecer (Setters)
    public void establecerNombre(String nombre) {
        this.nombre = nombre;
    }

    public void establecerPrecioBase(int precioBase) {
        this.precioBase = precioBase;
    }
    
    public void establecerCostoPorPais(CostoFabricacion costoPorPais) {
        this.costoPorPais = costoPorPais;
    }

    // Métodos para obtener (Getters)
    public String obtenerNombre() {
        return this.nombre;
    }

    public int obtenerPrecioBase() {
        return this.precioBase;
    }
    
    public CostoFabricacion obtenerCostoPorPais() {
        return this.costoPorPais;
    }

    // Método para calcular el precio total de la marca
    public int ptMarca() {
        return this.precioBase + this.costoPorPais.obtenerCostoImportacion();
    }
    
    public String obtenerLugarFabricacion() {
        return this.costoPorPais.obtenerPais();
    }


    @Override
    public String toString() {
        
        String output = 
            "Nombre: " + this.nombre + 
            "\nBase (Ficticio/Modelo): $" + this.precioBase + 
            "\n--- Detalles de Fabricación ---\n" + 
            this.costoPorPais + 
            "\n--- Precio Total Marca ---\n" +
            "Total: $" + this.ptMarca(); 
        
        return output;
    }
}