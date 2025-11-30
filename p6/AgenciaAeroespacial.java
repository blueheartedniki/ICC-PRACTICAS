import java.util.Scanner;

//excepciones

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

//interfaz

interface Validable {
    void validar() throws DatoInvalidoException, FormatoIncorrectoException, LogicaVueloException;
}

//primera clase abstracta

abstract class VueloBase implements Validable {
    protected String origen;
    protected String nombre;
    protected String identificacion;
    protected String fechaVencimientoID;
    protected String destino;
    protected String fechaPartida;

    protected final String FECHA_ACTUAL = "3050-01-01";

    protected boolean esFechaValida(String fecha) {
        return fecha.matches("\\d{4}-\\d{2}-\\d{2}");
    }
}

//clase abstracta dos

abstract class VueloConSalud extends VueloBase {
    protected String certificadoSalud;
    protected int edad;
}

// vuwlo internacional

class VueloInternacional extends VueloBase {

    private String nacionalidad;

    public VueloInternacional(String origen, String nombre, String nacionalidad,
                              String fechaVencID, String numID, String destino, String fechaPartida)
            throws DatoInvalidoException, FormatoIncorrectoException, LogicaVueloException {

        this.origen = origen;
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.fechaVencimientoID = fechaVencID;
        this.identificacion = numID;
        this.destino = destino;
        this.fechaPartida = fechaPartida;

        validar();
    }

    @Override
    public void validar() throws DatoInvalidoException, FormatoIncorrectoException, LogicaVueloException {

        if (!origen.matches("[A-Z]{2}")) {
            throw new FormatoIncorrectoException("El país de origen debe ser de 2 letras mayúsculas");
        }

        if (!nombre.trim().contains(" ")) {
            throw new DatoInvalidoException("Debes ingresar nombre y apellido");
        }

        if (!nacionalidad.matches("[A-Z]{2}")) {
            throw new FormatoIncorrectoException("Nacionalidad incorrecta");
        }

        if (!esFechaValida(fechaVencimientoID)) {
            throw new FormatoIncorrectoException("Formato fecha vencimiento inválido");
        }

        if (fechaVencimientoID.compareTo(FECHA_ACTUAL) <= 0) {
            throw new LogicaVueloException("La identificación está vencida");
        }

        if (origen.equals(destino)) {
            throw new LogicaVueloException("El destino no puede ser igual al origen");
        }

        if (!esFechaValida(fechaPartida)) {
            throw new FormatoIncorrectoException("Fecha de partida invalida");
        }

        if (fechaPartida.compareTo(FECHA_ACTUAL) <= 0) {
            throw new LogicaVueloException("La fecha de partida debe ser mayor a la actual");
        }
    }
}

// vuelo estratosferico

class VueloEstratosferico extends VueloConSalud {

    private boolean esInterplanetario;

    public VueloEstratosferico(String origen, String nombre, String fechaVencID,
                               String numID, String destino, String fechaPartida,
                               String certificadoSalud, int edad, boolean esInterplanetario)
            throws DatoInvalidoException, FormatoIncorrectoException, LogicaVueloException {

        this.origen = origen;
        this.nombre = nombre;
        this.fechaVencimientoID = fechaVencID;
        this.identificacion = numID;
        this.destino = destino;
        this.fechaPartida = fechaPartida;
        this.certificadoSalud = certificadoSalud;
        this.edad = edad;
        this.esInterplanetario = esInterplanetario;

        validar();
    }

    @Override
    public void validar() throws FormatoIncorrectoException, LogicaVueloException {

        if (edad < 18 || edad > 75) {
            throw new LogicaVueloException("Edad no permitida");
        }

        String[] partes = certificadoSalud.split("-");
        if (partes.length != 8) {
            throw new FormatoIncorrectoException("Certificado mal formado");
        }

        String fechaCert = partes[3] + "-" + partes[4] + "-" + partes[5];

        if (fechaCert.compareTo(fechaPartida) < 0) {
            throw new LogicaVueloException("Certificado vencido");
        }

        if (!partes[6].equals("A")) {
            throw new LogicaVueloException("El pasajero no es apto");
        }

        if (esInterplanetario && !partes[7].equals("P")) {
            throw new LogicaVueloException("Certificado no interplanetario");
        }

        if (!esInterplanetario && !partes[7].equals("E")) {
            throw new LogicaVueloException("Certificado no estratosférico");
        }
    }
}

// vuelo interplanetario

class VueloInterplanetario extends VueloConSalud {

    private static final String[] PLANETAS = {
            "MERCURIO", "VENUS", "TIERRA", "MARTE",
            "JUPITER", "SATURNO", "URANO", "NEPTUNO"
    };

    public VueloInterplanetario(String origen, String destino, String nombre,
                                String fechaVencID, String numID, String fechaPartida,
                                String certificadoSalud, int edad)
            throws DatoInvalidoException, LogicaVueloException {

        this.origen = origen.toUpperCase();
        this.destino = destino.toUpperCase();
        this.nombre = nombre;
        this.fechaVencimientoID = fechaVencID;
        this.identificacion = numID;
        this.fechaPartida = fechaPartida;
        this.certificadoSalud = certificadoSalud;
        this.edad = edad;

        validar();
    }

    @Override
    public void validar() throws DatoInvalidoException, LogicaVueloException {

        boolean validoO = false;
        boolean validoD = false;

        for (String p : PLANETAS) {
            if (p.equals(origen)) validoO = true;
            if (p.equals(destino)) validoD = true;
        }

        if (!validoO) {
            throw new DatoInvalidoException("Planeta origen inválido");
        }

        if (!validoD) {
            throw new DatoInvalidoException("Planeta destino inválido");
        }

        if (origen.equals(destino)) {
            throw new LogicaVueloException("No se puede viajar al mismo planeta");
        }

        if (edad < 18) {
            throw new LogicaVueloException("Menor de edad");
        }
    }
}

//main

public class AgenciaAeroespacial {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean ejecutar = true;

        System.out.println("AGENCIA AEROESPACIAL");

        while (ejecutar) {
            System.out.println("\nSelecciona el tipo de viaje:");
            System.out.println("1. Vuelo Internacional");
            System.out.println("2. Vuelo Estratosférico");
            System.out.println("3. Vuelo Interplanetario");
            System.out.println("4. Salir");
            System.out.print("Opción: ");

            int opcion;

            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Debes ingresar un número");
                continue;
            }

            if (opcion == 4) {
                System.out.println("Adiós");
                break;
            }

            boolean datosCorrectos = false;

            while (!datosCorrectos) {
                try {
                    procesarVuelo(opcion, scanner);
                    datosCorrectos = true;
                    System.out.println("\n--------------------------------");
                    System.out.println("tu vuelo se registró correctamente");
                    System.out.println("--------------------------------");
                } catch (Exception e) {
                    System.out.println("\n[!] " + e.getMessage());
                }
            }
        }
    }

    public static void procesarVuelo(int tipo, Scanner sc)
            throws DatoInvalidoException, FormatoIncorrectoException, LogicaVueloException {

        String nombre, fechaVencID, numID, fechaPartida;

        System.out.print("Nombre completo: ");
        nombre = sc.nextLine();

        System.out.print("Número de ID: ");
        numID = sc.nextLine();

        System.out.print("Fecha Vencimiento ID: ");
        fechaVencID = sc.nextLine();

        System.out.print("Fecha Partida: ");
        fechaPartida = sc.nextLine();

        switch (tipo) {

            case 1:
                System.out.print("País Origen: ");
                String pais = sc.nextLine();

                System.out.print("Nacionalidad: ");
                String nac = sc.nextLine();

                System.out.print("Destino: ");
                String dest = sc.nextLine();

                new VueloInternacional(pais, nombre, nac,
                        fechaVencID, numID, dest, fechaPartida);
                break;

            case 2:
                System.out.print("País Origen: ");
                String po = sc.nextLine();

                System.out.print("Destino: ");
                String de = sc.nextLine();

                System.out.print("Edad: ");
                int edadE = leerEntero(sc);

                System.out.print("Certificado: ");
                String cert = sc.nextLine();

                new VueloEstratosferico(po, nombre, fechaVencID,
                        numID, de, fechaPartida, cert, edadE, false);
                break;

            case 3:
                System.out.print("Planeta Origen: ");
                String pO = sc.nextLine();

                System.out.print("Planeta Destino: ");
                String pD = sc.nextLine();

                System.out.print("Edad: ");
                int edadI = leerEntero(sc);

                System.out.print("Certificado: ");
                String certI = sc.nextLine();

                new VueloInterplanetario(
                        pO, pD, nombre, fechaVencID,
                        numID, fechaPartida, certI, edadI);
                break;
        }
    }

    public static int leerEntero(Scanner sc) throws DatoInvalidoException {
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            throw new DatoInvalidoException("Debe ser un número entero");
        }
    }
}
