import java.util.Scanner;

public class VentaAutos {
    
    private static Scanner scanner = new Scanner(System.in);
    private static int costoTotal = 0; 

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("         Bienvenido al programa de Venta de AUtos      ");
        System.out.println("==================================================");
        System.out.println("Tu mismo armarás el auto que quieres comprar");
        System.out.println("Por favor ingresa el numero de la opcion que deseas, recuerda que no se puede retroceder");

        // estado inicial de los objetos
        MarcaAutomovil marcaSeleccionada = null; 
        Automovil autoBase = null; 
        Chasis chasis = null;
        Llanta llantasFrenos = null;
        SistemaElectronico electronicos = null;

        // declaracion de strings que se ocuparan al final cuando se vea el resumen del auto
        
        marcaSeleccionada = seleccionarMarca();
        autoBase = seleccionarConfiguracionBase();
        chasis = seleccionarChasis();
        llantasFrenos = seleccionarLlantasFrenos();
        electronicos = seleccionarSistemaElectronico();

        // con esto al final se muestra el reumen de la compra
        mostrarResumen(marcaSeleccionada, autoBase, chasis, llantasFrenos, electronicos);
        decidirCompra();
        
        
    }
    
// aqui comienza a elegir el usuario

    // marca y pais
    private static MarcaAutomovil seleccionarMarca() {
        System.out.println("\n--- 1. SELECCIÓN DE MARCA y PAÍS ---");
        
        // --- Definición de Precios Base (Ficticios) ---
        int BASE_HONDA = 50000;
        int BASE_BYD = 60000;
        int BASE_WOLSVAGEN = 45000;
        int BASE_TOYOTA = 55000;
        int BASE_NISSAN = 48000;
        
        //creacion de objetos de las clases
        
        // Honda
        CostoFabricacion hMex = new CostoFabricacion("México", 25000);
        CostoFabricacion hJap = new CostoFabricacion("Japón", 105000);
        CostoFabricacion hInd = new CostoFabricacion("India", 55000);

        // BYD
        CostoFabricacion bChi = new CostoFabricacion("China", 30000);

        // Wolswagen
        CostoFabricacion wMex = new CostoFabricacion("México", 15000);
        CostoFabricacion wAle = new CostoFabricacion("Alemania", 80000);
        CostoFabricacion wChi = new CostoFabricacion("China", 32000);
        CostoFabricacion wBra = new CostoFabricacion("Brasil", 42000);

        // Toyota
        CostoFabricacion tMex = new CostoFabricacion("México", 20000);
        CostoFabricacion tEU = new CostoFabricacion("Estados Unidos", 40000);
        CostoFabricacion tBra = new CostoFabricacion("Brasil", 40000);
        CostoFabricacion tFra = new CostoFabricacion("Francia", 90000);

        // Nissan
        CostoFabricacion nJap = new CostoFabricacion("Japón", 100000);
        CostoFabricacion nMex = new CostoFabricacion("México", 18000);
        
        // peticion al usuario

        System.out.println("Elige la Marca (Precio Base del Modelo):");
        System.out.println("1. Honda (+$" + BASE_HONDA + ")");
        System.out.println("2. BYD (+$" + BASE_BYD + ")");
        System.out.println("3. Wolswagen (+$" + BASE_WOLSVAGEN + ")");
        System.out.println("4. Toyota (+$" + BASE_TOYOTA + ")");
        System.out.println("5. Nissan (+$" + BASE_NISSAN + ")");
        
        String opcionMarcaStr = "";
        MarcaAutomovil marca = null;

        while (marca == null) {
            System.out.print("Opción (1-5): ");
            opcionMarcaStr = scanner.nextLine();
            
            // el usuario elige una marca y con esto se le muestran los paises y precios de fabricacion

            if (opcionMarcaStr.equalsIgnoreCase("1")) { // HONDA
                System.out.println("\nElige el país de fabricación para Honda:");
                System.out.println("1. México (Importación: +$25,000)");
                System.out.println("2. Japón (Importación: +$105,000)");
                System.out.println("3. India (Importación: +$55,000)");
                String op = "";
                while ("".equals(op)) {
                    System.out.print("Opción (1-3): ");
                    op = scanner.nextLine();
                    if (op.equalsIgnoreCase("1")) marca = new MarcaAutomovil("Honda", BASE_HONDA, hMex);
                    else if (op.equalsIgnoreCase("2")) marca = new MarcaAutomovil("Honda", BASE_HONDA, hJap);
                    else if (op.equalsIgnoreCase("3")) marca = new MarcaAutomovil("Honda", BASE_HONDA, hInd);
                    else { System.out.println("Opción de país inválida."); op = ""; }
                }

            } else if (opcionMarcaStr.equalsIgnoreCase("2")) { // BYD
                System.out.println("\nBYD solo se fabrica en China (Importación: +$30,000):");
                marca = new MarcaAutomovil("BYD", BASE_BYD, bChi);

            } else if (opcionMarcaStr.equalsIgnoreCase("3")) { // WOLSVAGEN
                System.out.println("\nElige el país de fabricación para Wolswagen:");
                System.out.println("1. México (Importación: +$15,000)");
                System.out.println("2. Alemania (Importación: +$80,000)");
                System.out.println("3. China (Importación: +$32,000)");
                System.out.println("4. Brasil (Importación: +$42,000)");
                String op = "";
                 while ("".equals(op)) {
                    System.out.print("Opción (1-4): ");
                    op = scanner.nextLine();
                    if (op.equalsIgnoreCase("1")) marca = new MarcaAutomovil("Wolswagen", BASE_WOLSVAGEN, wMex);
                    else if (op.equalsIgnoreCase("2")) marca = new MarcaAutomovil("Wolswagen", BASE_WOLSVAGEN, wAle);
                    else if (op.equalsIgnoreCase("3")) marca = new MarcaAutomovil("Wolswagen", BASE_WOLSVAGEN, wChi);
                    else if (op.equalsIgnoreCase("4")) marca = new MarcaAutomovil("Wolswagen", BASE_WOLSVAGEN, wBra);
                    else { System.out.println("Opción de país inválida."); op = ""; }
                }
                
            } else if (opcionMarcaStr.equalsIgnoreCase("4")) { // TOYOTA
                System.out.println("\nElige el país de fabricación para Toyota:");
                System.out.println("1. México (Importación: +$20,000)");
                System.out.println("2. Estados Unidos (Importación: +$40,000)");
                System.out.println("3. Brasil (Importación: +$40,000)");
                System.out.println("4. Francia (Importación: +$90,000)");
                String op = "";
                 while ("".equals(op)) {
                    System.out.print("Opción (1-4): ");
                    op = scanner.nextLine();
                    if (op.equalsIgnoreCase("1")) marca = new MarcaAutomovil("Toyota", BASE_TOYOTA, tMex);
                    else if (op.equalsIgnoreCase("2")) marca = new MarcaAutomovil("Toyota", BASE_TOYOTA, tEU);
                    else if (op.equalsIgnoreCase("3")) marca = new MarcaAutomovil("Toyota", BASE_TOYOTA, tBra);
                    else if (op.equalsIgnoreCase("4")) marca = new MarcaAutomovil("Toyota", BASE_TOYOTA, tFra);
                    else { System.out.println("Opción de país inválida."); op = ""; }
                }
                
            } else if (opcionMarcaStr.equalsIgnoreCase("5")) { // NISSAN
                System.out.println("\nElige el país de fabricación para Nissan:");
                System.out.println("1. Japón (Importación: +$100,000)");
                System.out.println("2. México (Importación: +$18,000)");
                String op = "";
                 while ("".equals(op)) {
                    System.out.print("Opción (1 o 2): ");
                    op = scanner.nextLine();
                    if (op.equalsIgnoreCase("1")) marca = new MarcaAutomovil("Nissan", BASE_NISSAN, nJap);
                    else if (op.equalsIgnoreCase("2")) marca = new MarcaAutomovil("Nissan", BASE_NISSAN, nMex);
                    else { System.out.println("Opción de país inválida."); op = ""; }
                }
                
            } else {
                System.out.println("Opción de marca inválida. Intenta de nuevo.");
            }
        }
        
        // Suma el costo y muestra el acumulado
        costoTotal += marca.ptMarca();
        System.out.println("\n_______ Hasta ahora has elegido esto:_______ ");
        System.out.println(marca.toString());
        System.out.println("Costo acumulado: $" + costoTotal);
        return marca;
    }
    
    // el usuario elige año y
    private static Automovil seleccionarConfiguracionBase() {
        System.out.println("\n--- 2. CONFIGURACIÓN BASE ---");
        
        // AÑO
        System.out.println("\nElige el año de fabricación:");
        System.out.println("1. 2023 (+$30,000)");
        System.out.println("2. 2024 (+$40,000)");
        System.out.println("3. 2025 (+$50,000)");
        int año = 0;
        String opcion = "";
        while (año == 0) {
            System.out.print("Opción (1-3): ");
            opcion = scanner.nextLine(); 
            if (opcion.equalsIgnoreCase("1")) año = 2023;
            else if (opcion.equalsIgnoreCase("2")) año = 2024;
            else if (opcion.equalsIgnoreCase("3")) año = 2025;
            else System.out.println("Por favor escribe el numero que corresponde a lo que deseas.");
        }

        // elegir transmision
        System.out.println("\nElige el tipo de transmisión:");
        System.out.println("1. Manual (+$40,000)");
        System.out.println("2. Automática (+$80,000)");
        String transmision = "";
        while ("".equals(transmision)) { 
            System.out.print("Opción (1 o 2): ");
            opcion = scanner.nextLine();
            if (opcion.equalsIgnoreCase("1")) transmision = "Manual";
            else if (opcion.equalsIgnoreCase("2")) transmision = "Automática";
            else System.out.println("Por favor escribe el numero que corresponde a lo que deseas.");
        }

        // tipo de auto
        System.out.println("\nElige el tipo de auto:");
        System.out.println("1. Compacto (+$40,000)");
        System.out.println("2. Semicompacto (+$80,000)");
        System.out.println("3. Sedán (+$120,000)");
        System.out.println("4. Deportivo (+$200,000)");
        String tipoAuto = "";
        while ("".equals(tipoAuto)) { 
            System.out.print("Opción (1-4): ");
            opcion = scanner.nextLine();
            if (opcion.equalsIgnoreCase("1")) tipoAuto = "Compacto";
            else if (opcion.equalsIgnoreCase("2")) tipoAuto = "Semicompacto";
            else if (opcion.equalsIgnoreCase("3")) tipoAuto = "Sedán";
            else if (opcion.equalsIgnoreCase("4")) tipoAuto = "Deportivo";
            else System.out.println("Por favor escribe el numero que corresponde a lo que deseas.");
        }
        
        // si quiere pelicula antiasalto
        System.out.println("\nDeseas película anti-asalto en ventanas?");
        System.out.println("1. Sí (+$4,000)");
        System.out.println("2. No (Gratis)");
        String ventana = "";
         while ("".equals(ventana)) { 
            System.out.print("Opción (1 o 2): ");
            opcion = scanner.nextLine();
            if (opcion.equalsIgnoreCase("1")) ventana = "Antiasalto";
            else if (opcion.equalsIgnoreCase("2")) ventana = "Normal";
            else System.out.println("Por favor escribe el numero que corresponde a lo que deseas.");
        }


        Automovil auto = new Automovil(transmision, tipoAuto, año, ventana);
        costoTotal += auto.sumaPrecioTotalA();
        System.out.println("\n_______ Hasta ahora has elegido esto:_______ ");
        System.out.println(auto.toString());
        System.out.println("Costo acumulado: $" + costoTotal);
        return auto;
    }

    // eleccion de chasis y sus caracteristicas
    private static Chasis seleccionarChasis() {
        System.out.println("\n--- 3. CONFIGURACIÓN DEL CHASIS ---");
        
        // COLOR
        System.out.println("\nElige el color del chasis (Es gratis!):");
        System.out.println("1. Marrón, 2. Oro, 3. Gris, 4. Blanco, 5. Rojo, 6. Negro, 7. Verde, 8. Azul, 9. Amarillo");
        String color = "";
        String opcion = "";
        while ("".equals(color)) { 
             System.out.print("Opción (1-9): ");
            opcion = scanner.nextLine();
            if (opcion.equalsIgnoreCase("1")) color = "Marrón";
            else if (opcion.equalsIgnoreCase("2")) color = "Oro";
            else if (opcion.equalsIgnoreCase("3")) color = "Gris";
            else if (opcion.equalsIgnoreCase("4")) color = "Blanco";
            else if (opcion.equalsIgnoreCase("5")) color = "Rojo";
            else if (opcion.equalsIgnoreCase("6")) color = "Negro";
            else if (opcion.equalsIgnoreCase("7")) color = "Verde";
            else if (opcion.equalsIgnoreCase("8")) color = "Azul";
            else if (opcion.equalsIgnoreCase("9")) color = "Amarillo";
            else System.out.println("Por favor escribe el numero que corresponde a lo que deseas.");
        }

        // acabado
        System.out.println("\nElige el acabado del chasis:");
        System.out.println("1. Mate (Es gratis!)");
        System.out.println("2. Brillante (+$10,000)");
        System.out.println("3. Metálico (+$15,000)");
        String acabado = "";
        while ("".equals(acabado)) { 
            System.out.print("Opción (1-3): ");
            opcion = scanner.nextLine();
            if (opcion.equalsIgnoreCase("1")) acabado = "Mate";
            else if (opcion.equalsIgnoreCase("2")) acabado = "Brillante";
            else if (opcion.equalsIgnoreCase("3")) acabado = "Metálico";
            else System.out.println("Por favor escribe el numero que corresponde a lo que deseas.");
        }
        
        Chasis chasis = new Chasis(color, acabado); 
        costoTotal += chasis.ptChasis();
        System.out.println("\n_______ Hasta ahora has elegido esto:_______ ");
        System.out.println(chasis.toString());
        System.out.println("Costo acumulado: $" + costoTotal);
        return chasis;
    }

    // elegir llantas y tipo de frenos
    private static Llanta seleccionarLlantasFrenos() {
        System.out.println("\n--- 4. CONFIGURACIÓN DE LLANTAS Y FRENOS ---");
        String opcion = "";

        // MARCA DE LLANTA
        System.out.println("\nElige la marca de llantas:");
        System.out.println("1. Yokohama (+$5,000)");
        System.out.println("2. Firestone (+$8,000)");
        System.out.println("3. Pirelli (+$6,500)");
        System.out.println("4. GoodYear (+$6,000)");
        System.out.println("5. Michelin (+$10,000)");
        String marcaLlanta = "";
        while ("".equals(marcaLlanta)) { 
            System.out.print("Opción (1-5): ");
            opcion = scanner.nextLine();
            if (opcion.equalsIgnoreCase("1")) marcaLlanta = "Yokohama";
            else if (opcion.equalsIgnoreCase("2")) marcaLlanta = "Firestone";
            else if (opcion.equalsIgnoreCase("3")) marcaLlanta = "Pirelli";
            else if (opcion.equalsIgnoreCase("4")) marcaLlanta = "Goodyear";
            else if (opcion.equalsIgnoreCase("5")) marcaLlanta = "Michelin";
            else System.out.println("Por favor escribe el numero que corresponde a lo que deseas.");
        }

        // rines
        System.out.println("\nElige el tamaño del rin (15, 16, 17, 18 pulgadas - Es gratis!):");
        System.out.println("1. 15 | 2. 16 | 3. 17 | 4. 18");
        int tamañoRin = 0;
        while (tamañoRin == 0) {
            System.out.print("Opción (1-4): ");
            opcion = scanner.nextLine();
            if (opcion.equalsIgnoreCase("1")) tamañoRin = 15;
            else if (opcion.equalsIgnoreCase("2")) tamañoRin = 16;
            else if (opcion.equalsIgnoreCase("3")) tamañoRin = 17;
            else if (opcion.equalsIgnoreCase("4")) tamañoRin = 18;
            else System.out.println("Por favor escribe el numero que corresponde a lo que deseas.");
        }

        // material rin
        System.out.println("\nElige el material del rin:");
        System.out.println("1. Aluminio (+$8,000)");
        System.out.println("2. Acero (+$3,000)");
        String materialRin = "";
        while ("".equals(materialRin)) { 
            System.out.print("Opción (1 o 2): ");
            opcion = scanner.nextLine();
            if (opcion.equalsIgnoreCase("1")) materialRin = "Aluminio";
            else if (opcion.equalsIgnoreCase("2")) materialRin = "Acero";
            else System.out.println("Por favor escribe el numero que corresponde a lo que deseas.");
        }
        
        // frenos
        System.out.println("\nElige el tipo de frenos:");
        System.out.println("1. Disco (+$3,200)");
        System.out.println("2. Tambor (+$2,100)");
        String frenos = "";
        while ("".equals(frenos)) { 
            System.out.print("Opción (1 o 2): ");
            opcion = scanner.nextLine();
            if (opcion.equalsIgnoreCase("1")) frenos = "Disco";
            else if (opcion.equalsIgnoreCase("2")) frenos = "Tambor";
            else System.out.println("Por favor escribe el numero que corresponde a lo que deseas.");
        }

        Llanta llantas = new Llanta(tamañoRin, frenos, materialRin, marcaLlanta);
        costoTotal += llantas.precioTotalLlanta();
        System.out.println("\n_______ Hasta ahora has elegido esto:_______ ");
        System.out.println(llantas.toString());
        System.out.println("Costo acumulado: $" + costoTotal);
        return llantas;
    }

    // eleccion de sist. elect
    private static SistemaElectronico seleccionarSistemaElectronico() {
        System.out.println("\n--- 5. SISTEMA ELECTRÓNICO ---");
        
        // PANTALLA
        System.out.println("\nDeseas Pantalla digital en lugar de radio convencional? (+$3,000)");
        boolean pantalla = preguntarSiNo();

        // ESPEJOS
        System.out.println("\nDeseas Espejos electrónicos? (+$5,000)");
        boolean espejos = preguntarSiNo();
        
        // SENSOR DE REVERSA
        System.out.println("\nDeseas Sensor de reversa? (+$4,000)");
        boolean sensor = preguntarSiNo();
        
        // CÁMARA DE REVERSA
        System.out.println("\nDeseas Cámara de reversa? (+$7,000)");
        boolean camara = preguntarSiNo();
        
        
        SistemaElectronico electronicos = new SistemaElectronico(pantalla, espejos, sensor, camara);
        costoTotal += electronicos.ptElectrico();
        System.out.println("\n_______ Hasta ahora has elegido esto:_______ ");
        System.out.println(electronicos.toString());
        System.out.println("Costo acumulado: $" + costoTotal);
        return electronicos;
    }
    
    // metodo para preguntas que se contestan con si o no dependiendo si quiere o no un accesorio
    private static boolean preguntarSiNo() {
        String opcion = "";
        boolean validado = false;
        while (!validado) {
            System.out.println("1. Sí");
            System.out.println("2. No");
            System.out.print("Opción (1 o 2): ");
            opcion = scanner.nextLine(); 
            
            if (opcion.equalsIgnoreCase("1")) {
                validado = true;
                return true;
            } else if (opcion.equalsIgnoreCase("2")) {
                validado = true;
                return false;
            } else {
                System.out.println("Por favor escribe el numero que corresponde a lo que deseas.");
            }
        }
        return false;
    }

    // resumen del carro armado para comprar
    private static void mostrarResumen(MarcaAutomovil marca, Automovil auto, Chasis chasis, Llanta llantas, SistemaElectronico electronicos) {
        System.out.println("\n\n==================================================");
        System.out.println("          R E S U M E N  D E   C O M P R A");
        System.out.println("==================================================");
        
        System.out.println("\n_______ DETALLES Y COSTOS POR SECCIÓN _______ *");
        
        System.out.println("-----------------------------------");
        System.out.println(marca.toString());
        System.out.println("-----------------------------------");
        System.out.println(auto.toString());
        System.out.println("-----------------------------------");
        System.out.println(chasis.toString());
        System.out.println("-----------------------------------");
        System.out.println(llantas.toString());
        System.out.println("-----------------------------------");
        System.out.println(electronicos.toString());
        System.out.println("-----------------------------------");
        
        System.out.println("\n_______  PRECIO TOTAL _______ *");
        System.out.println("Costo Total: $" + costoTotal);
        System.out.println("==================================================");
    }
    
    // Pregunta si desea comprar
    private static void decidirCompra() {
        System.out.println("\nDeseas comprar el automóvil con estas caracteristicas?");
        String opcion = "";
        boolean validado = false;
        while (!validado) {
            System.out.println("1. Sí, deseo comprarlo.");
            System.out.println("2. No comprarlo.");
            System.out.print("Opción (1 o 2): ");
            opcion = scanner.nextLine(); 
            
            if (opcion.equalsIgnoreCase("1")) {
                validado = true;
                System.out.println("\n¡Felicidades por tu nuevo auto!");
            } else if (opcion.equalsIgnoreCase("2")) {
                validado = true;
                System.out.println("\nQue lastima :(");
            } else {
                System.out.println("Por favor escribe el numero que corresponde a lo que deseas.");
            }
        }
    }
}