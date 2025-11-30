import java.util.Scanner;

// -------------------------------------------------------------------------
// EXCEPCIONES PERSONALIZADAS
// -------------------------------------------------------------------------

class DatoInvalidoException extends Exception {
    public DatoInvalidoException(String mensaje) {
        super("Error de Datos: " + mensaje);
    }
}

class FormatoIncorrectoException extends Exception {
    public FormatoIncorrectoException(String mensaje) {
        super("Error de Formato: " + mensaje);
    }
}

class LogicaVueloException extends Exception {
    public LogicaVueloException(String mensaje) {
        super("Error de Logica: " + mensaje);
    }
}

// -------------------------------------------------------------------------
// CLASE BASE: VUELO INTERNACIONAL
// -------------------------------------------------------------------------

class VueloInternacional {
    protected String paisOrigen;
    protected String nombrePasajero;
    protected String nacionalidad;
    protected String fechaVencimientoID;
    protected String numeroID;
    protected String destino;
    protected String fechaPartida;
    
    // Fecha actual hipotética del sistema (Año 3050)
    protected final String FECHA_ACTUAL = "3050-01-01";

    public VueloInternacional(String paisOrigen, String nombre, String nacionalidad, 
                              String fechaVencID, String numID, String destino, String fechaPartida) 
                              throws DatoInvalidoException, FormatoIncorrectoException, LogicaVueloException {
        
        // 1. Validar País Origen (2 letras mayúsculas)
        if (!paisOrigen.matches("[A-Z]{2}")) {
            throw new FormatoIncorrectoException("El país de origen debe ser de 2 letras mayúsculas (ej. MX).");
        }
        this.paisOrigen = paisOrigen;

        // 2. Validar Nombre (Nombre y Apellido)
        if (!nombre.trim().contains(" ")) {
            throw new DatoInvalidoException("Debe ingresar al menos un nombre y un apellido.");
        }
        this.nombrePasajero = nombre;

        // 3. Validar Nacionalidad (2 letras mayúsculas) - Si es "NA" (Interplanetario) se salta
        if (!nacionalidad.equals("NA")) {
             if (!nacionalidad.matches("[A-Z]{2}")) {
                throw new FormatoIncorrectoException("La nacionalidad debe ser de 2 letras mayúsculas.");
            }
        }
        this.nacionalidad = nacionalidad;

        // 4. Validar Fecha Vencimiento ID (> Fecha Actual)
        // Usamos comparación lexicográfica de cadenas formato AAAA-MM-DD
        if (!esFechaValida(fechaVencID)) {
            throw new FormatoIncorrectoException("Formato de fecha vencimiento inválido. Use AAAA-MM-DD.");
        }
        if (fechaVencID.compareTo(FECHA_ACTUAL) <= 0) {
            throw new LogicaVueloException("La identificación está vencida (debe ser mayor a " + FECHA_ACTUAL + ").");
        }
        this.fechaVencimientoID = fechaVencID;

        // 5. Número ID
        if (numID == null || numID.trim().isEmpty()) {
            throw new DatoInvalidoException("El número de identificación no puede estar vacío.");
        }
        this.numeroID = numID;

        // 6. Validar Destino y Origen
        if (destino.equals(paisOrigen)) {
            throw new LogicaVueloException("El destino no puede ser igual al lugar de partida.");
        }
        this.destino = destino;

        // 7. Fecha Partida (> Fecha Actual)
        if (!esFechaValida(fechaPartida)) {
            throw new FormatoIncorrectoException("Formato de fecha partida inválido. Use AAAA-MM-DD.");
        }
        if (fechaPartida.compareTo(FECHA_ACTUAL) <= 0) {
            throw new LogicaVueloException("La fecha de partida debe ser futura (mayor a " + FECHA_ACTUAL + ").");
        }
        this.fechaPartida = fechaPartida;
    }

    // Método auxiliar para validar formato AAAA-MM-DD sin importar librerías
    protected boolean esFechaValida(String fecha) {
        return fecha.matches("\\d{4}-\\d{2}-\\d{2}");
    }
}

// -------------------------------------------------------------------------
// CLASE DERIVADA: VUELO ESTRATOSFÉRICO
// -------------------------------------------------------------------------

class VueloEstratosferico extends VueloInternacional {
    protected String certificadoSalud;
    protected int edad;

    public VueloEstratosferico(String pais, String nombre, String nacionalidad, 
                               String fechaVencID, String numID, String destino, String fechaPartida,
                               String certificadoSalud, int edad, boolean esInterplanetario) 
                               throws DatoInvalidoException, FormatoIncorrectoException, LogicaVueloException {
        
        super(pais, nombre, nacionalidad, fechaVencID, numID, destino, fechaPartida);

        // 1. Validar Edad (18 - 75)
        if (edad < 18 || edad > 75) {
            throw new LogicaVueloException("Edad no permitida para vuelos estratosféricos/interplanetarios (18-75 años).");
        }
        this.edad = edad;

        // 2. Validar Certificado
        // Formato: CS-NOMBRE-APELLIDO-ANO-MES-DIA-CLAVESALUD-TIPOVIAJE
        String[] partes = certificadoSalud.split("-");
        if (partes.length != 8) {
            throw new FormatoIncorrectoException("El certificado de salud no tiene el formato correcto (8 partes separadas por guiones).");
        }

        if (!partes[0].equals("CS")) {
            throw new FormatoIncorrectoException("El certificado debe iniciar con 'CS'.");
        }

        // Validar fecha del certificado >= Fecha Viaje
        String fechaCert = partes[3] + "-" + partes[4] + "-" + partes[5];
        if (!esFechaValida(fechaCert)) {
             throw new FormatoIncorrectoException("Fecha en el certificado inválida.");
        }
        
        // La fecha del certificado debe ser IGUAL o MAYOR (posterior) a la fecha de viaje
        if (fechaCert.compareTo(fechaPartida) < 0) {
             throw new LogicaVueloException("El certificado de salud expiró antes de la fecha de viaje.");
        }

        String claveSalud = partes[6];
        if (!claveSalud.equals("A")) {
             throw new LogicaVueloException("El pasajero NO es APTO (Clave Salud: " + claveSalud + ").");
        }

        String tipoViajeCert = partes[7];
        
        if (esInterplanetario) {
            if (!tipoViajeCert.equals("P")) {
                throw new LogicaVueloException("El certificado no es válido para viajes Interplanetarios (Requiere tipo P).");
            }
        } else {
            // Es estratosférico puro
            if (!tipoViajeCert.equals("E")) {
                 throw new LogicaVueloException("El certificado no corresponde al tipo de viaje Estratosférico (Requiere tipo E).");
            }
        }

        this.certificadoSalud = certificadoSalud;
    }
}

// -------------------------------------------------------------------------
// CLASE DERIVADA: VUELO INTERPLANETARIO (CORREGIDA)
// -------------------------------------------------------------------------

class VueloInterplanetario extends VueloEstratosferico {
    
    // Lista simple de planetas para validación (STATIC para evitar problemas de inicialización)
    private static final String[] PLANETAS = {"MERCURIO", "VENUS", "TIERRA", "MARTE", "JUPITER", "SATURNO", "URANO", "NEPTUNO"};

    public VueloInterplanetario(String planetaOrigen, String nombre, String fechaVencID, 
                                String numID, String planetaDestino, String fechaPartida,
                                String certificadoSalud, int edad) 
                                throws DatoInvalidoException, FormatoIncorrectoException, LogicaVueloException {
        
        // 1. LLAMADA AL PADRE PRIMERO
        // Pasamos "XX" para cumplir la regex de 2 letras de VueloInternacional temporalmente
        // El booleano 'true' indica que es modo Interplanetario para la validación del certificado.
        super("XX", nombre, "NA", fechaVencID, numID, "XX", fechaPartida, certificadoSalud, edad, true);

        // 2. VALIDACIONES DE PLANETAS (Después del super)
        boolean origenValido = false;
        boolean destinoValido = false;

        for (String p : PLANETAS) {
            if (p.equalsIgnoreCase(planetaOrigen)) origenValido = true;
            if (p.equalsIgnoreCase(planetaDestino)) destinoValido = true;
        }

        if (!origenValido) {
            throw new DatoInvalidoException("El origen debe ser un planeta del sistema solar válido (" + planetaOrigen + ").");
        }
        if (!destinoValido) {
            throw new DatoInvalidoException("El destino debe ser un planeta del sistema solar válido (" + planetaDestino + ").");
        }

        if (planetaOrigen.equalsIgnoreCase(planetaDestino)) {
            throw new LogicaVueloException("No se puede volar hacia el mismo planeta del cual se parte.");
        }

        // 3. ACTUALIZACIÓN DE CAMPOS
        // Sobreescribimos los campos del padre con la información real de los planetas
        this.paisOrigen = planetaOrigen; 
        this.destino = planetaDestino;
    }
}

// -------------------------------------------------------------------------
// CLASE PRINCIPAL (Main)
// -------------------------------------------------------------------------

public class AgenciaAeroespacial {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean ejecutar = true;

        System.out.println("=== AGENCIA AEROESPACIAL: AÑO 3050 ===");

        while (ejecutar) {
            System.out.println("\nSeleccione el tipo de viaje:");
            System.out.println("1. Vuelo Internacional (Tierra)");
            System.out.println("2. Vuelo Estratosférico");
            System.out.println("3. Vuelo Interplanetario");
            System.out.println("4. Salir");
            System.out.print("Opción: ");

            int opcion = -1;
            try {
                String entrada = scanner.nextLine();
                opcion = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un número entero.");
                continue;
            }

            if (opcion == 4) {
                ejecutar = false;
                System.out.println("Cerrando sistema...");
                break;
            }

            if (opcion < 1 || opcion > 3) {
                System.out.println("Opción no válida.");
                continue;
            }

            boolean datosCorrectos = false;
            
            // Bucle de reintento hasta que los datos sean correctos
            while (!datosCorrectos) {
                try {
                    procesarVuelo(opcion, scanner);
                    datosCorrectos = true; // Si no lanza excepción, es exitoso
                    System.out.println("\n------------------------------------------------");
                    System.out.println("¡ÉXITO! Su vuelo ha sido registrado correctamente.");
                    System.out.println("------------------------------------------------");
                } catch (DatoInvalidoException e) {
                    System.out.println("\n[!] " + e.getMessage());
                    System.out.println("Por favor, intente ingresar los datos nuevamente.\n");
                } catch (FormatoIncorrectoException e) {
                    System.out.println("\n[!] " + e.getMessage());
                    System.out.println("Por favor, intente ingresar los datos nuevamente.\n");
                } catch (LogicaVueloException e) {
                    System.out.println("\n[!] " + e.getMessage());
                    System.out.println("Por favor, intente ingresar los datos nuevamente.\n");
                } catch (Exception e) {
                    System.out.println("\n[!] Error inesperado: " + e.getMessage());
                    e.printStackTrace();
                    System.out.println("Intente nuevamente.");
                }
            }
        }
        scanner.close();
    }

    public static void procesarVuelo(int tipo, Scanner sc) 
            throws DatoInvalidoException, FormatoIncorrectoException, LogicaVueloException {
        
        // Variables comunes
        String nombre, fechaVencID, numID, fechaPartida;
        
        System.out.println("--- Ingrese los datos del pasajero ---");
        
        System.out.print("Nombre completo (Nombre y Apellido): ");
        nombre = sc.nextLine();

        System.out.print("Número de Identificación: ");
        numID = sc.nextLine();

        System.out.print("Fecha Vencimiento ID (AAAA-MM-DD): ");
        fechaVencID = sc.nextLine();

        System.out.print("Fecha de Partida (AAAA-MM-DD): ");
        fechaPartida = sc.nextLine();

        switch (tipo) {
            case 1: // Internacional
                System.out.print("País de Origen (2 letras mayúsculas, ej. MX): ");
                String pais = sc.nextLine();
                
                System.out.print("Nacionalidad (2 letras mayúsculas, ej. MX): ");
                String nacionalidad = sc.nextLine();

                System.out.print("Destino (2 letras mayúsculas): ");
                String destino = sc.nextLine();

                // Instanciación
                new VueloInternacional(pais, nombre, nacionalidad, fechaVencID, numID, destino, fechaPartida);
                break;

            case 2: // Estratosférico
                System.out.print("País de Origen (2 letras mayúsculas): ");
                String paisE = sc.nextLine();
                
                System.out.print("Nacionalidad (2 letras mayúsculas): ");
                String nacE = sc.nextLine();

                System.out.print("Destino (2 letras mayúsculas): ");
                String destE = sc.nextLine();

                System.out.print("Edad: ");
                int edadE = leerEntero(sc);

                System.out.println("Certificado Salud (CS-NOM-APE-AAAA-MM-DD-CLAVE-TIPO)");
                System.out.print("Ejemplo (CS-JUAN-PEREZ-3050-12-06-A-E): ");
                String certE = sc.nextLine();

                // Instanciación (false indica que NO es interplanetario)
                new VueloEstratosferico(paisE, nombre, nacE, fechaVencID, numID, destE, fechaPartida, certE, edadE, false);
                break;

            case 3: // Interplanetario
                System.out.print("Planeta de Origen (Sistema Solar): ");
                String planetaOrigen = sc.nextLine();

                System.out.print("Planeta de Destino (Sistema Solar): ");
                String planetaDestino = sc.nextLine();

                System.out.print("Edad: ");
                int edadI = leerEntero(sc);

                System.out.println("Certificado Salud (CS-NOM-APE-AAAA-MM-DD-CLAVE-TIPO)");
                System.out.print("Ejemplo (CS-JUAN-PEREZ-3050-12-06-A-P): ");
                String certI = sc.nextLine();

                // Instanciación
                new VueloInterplanetario(planetaOrigen, nombre, fechaVencID, numID, planetaDestino, fechaPartida, certI, edadI);
                break;
        }
    }

    // Método auxiliar para leer enteros de forma robusta
    public static int leerEntero(Scanner sc) throws DatoInvalidoException {
        try {
            String input = sc.nextLine();
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new DatoInvalidoException("Se esperaba un número entero válido.");
        }
    }
}