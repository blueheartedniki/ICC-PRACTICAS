public class Llanta {
    private int tamañoRin;
    private String frenos;
    private String materialRin;
    private String marcaLlanta;

    // creacion del constructor de objetos llanta
    public Llanta(int tamañoRin, String frenos, String materialRin, String marcaLlanta) {
        this.tamañoRin = tamañoRin;
        this.frenos = frenos;
        this.materialRin = materialRin;
        this.marcaLlanta = marcaLlanta;
    }

    // metodo que nos dará la suma total de los precios de las caractaerísticas de las llantas
    public int precioTotalLlanta() {
        int pbLlanta = 0;
        
        // Costo de Frenos
        if(frenos.equalsIgnoreCase("Disco")) {
            pbLlanta += 3200;
        } else if(frenos.equalsIgnoreCase("Tambor")) {
            pbLlanta += 2100;
        }
        
        // Costo de Material del Rin
        if(materialRin.equalsIgnoreCase("Aluminio")) {
            pbLlanta += 8000;
        } else if(materialRin.equalsIgnoreCase("Acero")) {
            pbLlanta += 3000;
        }
        
        // Costo de Marca de Llantas
        if(marcaLlanta.equalsIgnoreCase("Yokohama")) {
            pbLlanta += 5000;
        } else if(marcaLlanta.equalsIgnoreCase("Firestone")) {
            pbLlanta += 8000;
        } else if(marcaLlanta.equalsIgnoreCase("Pirelli")) {
            pbLlanta += 6500;
        } else if(marcaLlanta.equalsIgnoreCase("Goodyear")) {
            pbLlanta += 6000;
        } else if(marcaLlanta.equalsIgnoreCase("Michelin")) {
            pbLlanta += 10000;
        }
        return pbLlanta;
    }

    // metodos para obtener 
    public int obtenerTamañoRin() {
        return this.tamañoRin;
    }
    public String obtenFrenos () {
        return this.frenos;
    }
    public String obtenMaterialRin() {
        return this.materialRin;
    }
    public String obtenMarcaLlanta() {
        return this.marcaLlanta;
    }
    
    @Override
    public String toString() {
        // Obteniendo precios para el toString
        int costoRin = materialRin.equalsIgnoreCase("Aluminio") ? 8000 : (materialRin.equalsIgnoreCase("Acero") ? 3000 : 0);
        int costoFrenos = frenos.equalsIgnoreCase("Disco") ? 3200 : (frenos.equalsIgnoreCase("Tambor") ? 2100 : 0);
        int costoMarca = 0;
        if(marcaLlanta.equalsIgnoreCase("Yokohama")) costoMarca = 5000;
        else if(marcaLlanta.equalsIgnoreCase("Firestone")) costoMarca = 8000;
        else if(marcaLlanta.equalsIgnoreCase("Pirelli")) costoMarca = 6500;
        else if(marcaLlanta.equalsIgnoreCase("Goodyear")) costoMarca = 6000;
        else if(marcaLlanta.equalsIgnoreCase("Michelin")) costoMarca = 10000;

        return "--- Configuración de Llantas y Frenos ---" +
               "\nMarca de Llanta: " + this.marcaLlanta + " (+$" + costoMarca + ")" +
               "\nTamaño del Rin: " + this.tamañoRin + " pulgadas (Gratis)" + 
               "\nMaterial del Rin: " + this.materialRin + " (+$" + costoRin + ")" +
               "\nTipo de Frenos: " + this.frenos + " (+$" + costoFrenos + ")" +
               "\nCosto Total Llantas/Frenos: $" + this.precioTotalLlanta();
    }
}