public class SistemaElectronico { 
    private boolean pantallaDigital; 
    private boolean espejosElectronicos; 
    private boolean sensorReversa; 
    private boolean camaraReversa; 

    public SistemaElectronico(boolean pantallaDigital, boolean espejosElectronicos, boolean sensorReversa, boolean camaraReversa) {
        this.pantallaDigital = pantallaDigital;
        this.espejosElectronicos = espejosElectronicos;
        this.sensorReversa = sensorReversa;
        this.camaraReversa = camaraReversa;
    }

    public int ptElectrico() {
        int pbElectrico = 0; 
      
        if(this.pantallaDigital) { 
            pbElectrico += 3000; 
        } 
        if(this.espejosElectronicos) { 
            pbElectrico += 5000; 
        }
        if(this.sensorReversa) { 
            pbElectrico += 4000; 
        }
        if(this.camaraReversa) { 
            pbElectrico += 7000; 
        }
        
        return pbElectrico;
    }

    // Método auxiliar formateado con IF-ELSE
    private String formatOption(boolean selected, int price) {
        if (selected) {
            return "Sí (+$" + price + ")";
        } else {
            return "No";
        }
    }


    @Override
    public String toString() {
        
        return "--- Accesorios Electrónicos ---" +
               "\nPantalla Digital: " + formatOption(this.pantallaDigital, 3000) +
               "\nEspejos Electrónicos: " + formatOption(this.espejosElectronicos, 5000) +
               "\nSensor de Reversa: " + formatOption(this.sensorReversa, 4000) +
               "\nCámara de Reversa: " + formatOption(this.camaraReversa, 7000) +
               "\nCosto Total Electrónicos: $" + this.ptElectrico();
    }
    
    // Métodos para obtener (getters)
    public boolean tienePantallaDigital() {
        return this.pantallaDigital;
    }
    public boolean tieneEspejosElectronicos() {
        return this.espejosElectronicos;
    }
    public boolean tieneSensorReversa() {
        return this.sensorReversa;
    }
    public boolean tieneCamaraReversa() {
        return this.camaraReversa;
    }
}