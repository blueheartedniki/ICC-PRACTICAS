public class Automovil {
    private String transmision;
    private String tipoAuto;
    private int año;
    private String ventana; // Película anti-asalto

    // creacion de constructor de automovil 
    public Automovil(String transmision, String tipoAuto, int año, String ventana) {
        this.transmision = transmision;
        this.tipoAuto = tipoAuto;
        this.año = año;
        this.ventana = ventana;
    }
    
     // sumaPrecioTotalA calcula el precio de la configuración base
    public int sumaPrecioTotalA() {
        int precioBase = 0; 
     
        // Costo por Transmisión
        if (this.transmision.equalsIgnoreCase("Manual")) { 
            precioBase += 40000;
        } else if(this.transmision.equalsIgnoreCase("Automática")) { 
            precioBase += 80000;
        }

        // Costo por Tipo de Auto
        if (this.tipoAuto.equalsIgnoreCase("Compacto")) {
            precioBase += 40000;
        } else if(this.tipoAuto.equalsIgnoreCase("Semicompacto")) {
            precioBase += 80000;
        } else if(this.tipoAuto.equalsIgnoreCase("Sedán")) { 
            precioBase += 120000;
        } else if(this.tipoAuto.equalsIgnoreCase("Deportivo")) {
            precioBase += 200000;
        }

        // Costo por Año
        if (this.año == 2023) {
            precioBase += 30000;
        } else if(this.año == 2024) {
            precioBase += 40000;
        } else if(this.año == 2025) {
            precioBase += 50000;
        }

        // Costo por Película Anti-asalto
        if (this.ventana.equalsIgnoreCase("Antiasalto")) {
            precioBase += 4000;
        }
        return precioBase;
    }  
    
    // metodos para obtener 
    public String obtenTransmision() {
        return this.transmision;
    }
    public String obtenTipoAuto() {
        return this.tipoAuto;
    }
    public int obtenAño() {
        return this.año;
    }
    public String obtenVentana() {
        return this.ventana;
    }

    @Override
    public String toString() {
        String precioTransmision = this.transmision.equalsIgnoreCase("Manual") ? "$40,000" : "$80,000";
        
        String precioTipo = "";
        if (this.tipoAuto.equalsIgnoreCase("Compacto")) precioTipo = "$40,000";
        else if (this.tipoAuto.equalsIgnoreCase("Semicompacto")) precioTipo = "$80,000";
        else if (this.tipoAuto.equalsIgnoreCase("Sedán")) precioTipo = "$120,000";
        else if (this.tipoAuto.equalsIgnoreCase("Deportivo")) precioTipo = "$200,000";

        int precioAño = 0;
        if (this.año == 2023) precioAño = 30000;
        else if (this.año == 2024) precioAño = 40000;
        else if (this.año == 2025) precioAño = 50000;


        return "--- Configuración Básica del Vehículo ---" +
               "\nAño: " + this.año + " (+$" + precioAño + ")" +
               "\nTipo: " + this.tipoAuto + " (+$" + precioTipo + ")" +
               "\nTransmisión: " + this.transmision + " (+$" + precioTransmision + ")" +
               "\nVentanas (Película): " + this.ventana + " (+" + (this.ventana.equalsIgnoreCase("Antiasalto") ? "$4,000" : "Gratis") + ")" +
               "\nCosto Total Base: $" + this.sumaPrecioTotalA();
    }
}