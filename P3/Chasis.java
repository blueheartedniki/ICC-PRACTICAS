public class Chasis {
    private String color;
    private String acabado;
    // Se utiliza el constructor original sin Película Anti-asalto para respetar tu implementación inicial
    // Nota: El costo de la película anti-asalto fue manejado en la clase Automovil según tu diseño.

    // creacion del constructor Chasis 
    public Chasis(String color, String acabado) {
        this.color = color;
        this.acabado = acabado;
    }
    
    // Método que calcula el costo extra del chasis (acabado)
    public int ptChasis() {
        int pbChasis = 0;

        if(acabado.equalsIgnoreCase("Metálico")) { // Corregido a "Metálico"
           pbChasis = pbChasis + 15000;
        } else if(acabado.equalsIgnoreCase("Brillante")) {
            pbChasis = pbChasis + 10000;
        }
        return pbChasis;
    }
    
    // metodos para obtener
    public String obtenerColor() {
        return this.color;
    }
    public String obtenerAcabado() {
        return this.acabado;
    }

    @Override
    public String toString() {
         String costoAcabado = this.ptChasis() > 0 ? ("+$" + this.ptChasis()) : "(Gratis)";
         
         return "--- Configuración del Chasis ---" +
                "\nColor del chasis: " + this.color + " (Gratis)" +
                "\nAcabado: " + this.acabado + " " + costoAcabado +
                "\nCosto Total Chasis: $" + this.ptChasis();
    }
}