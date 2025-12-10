import java.util.Scanner;

//main para interaccion con el usuario en la terminal
public class Main {

    public static void main(String[] args) {
        //se instancia el sistema
        Sistema sistema = new Sistema();
        
        //se intentan cargar datos previos en caso de que haya
        System.out.println("Buscando datos previos..");
        sistema.cargarDatos();

        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        //bucle prinicpal para el menu
        while (!salir) {
            imprimirMenu();
            String opcion = scanner.nextLine(); // Leemos todo como texto para evitar errores

            switch (opcion) {
                case "1":
                    registrarAlumno(scanner, sistema);
                    break;
                case "2":
                    registrarProfesor(scanner, sistema);
                    break;
                case "3":
                    registrarCurso(scanner, sistema);
                    break;
                case "4":
                    asignarProfesorACurso(scanner, sistema);
                    break;
                case "5":
                    inscribirAlumno(scanner, sistema);
                    break;
                case "6":
                    bajaAlumnoDeCurso(scanner, sistema);
                    break;
                case "7":
                    eliminarRegistros(scanner, sistema);
                    break;
                case "8":
                    sistema.mostrarTodo();
                    break;
                case "9":
                    System.out.println("Guardando datos..");
                    sistema.guardarDatos();
                    salir = true;
                    break;
                default:
                    System.out.println("Opcion invalida");
            }
        }
        scanner.close();
    }

   //se muestran las opciones
    private static void imprimirMenu() {
        System.out.println("\n____ S I S T E M A    D E    G E S T I Ó N    E S C O L A R _____");
        System.out.println("1 Registrar alumno");
        System.out.println("2 Registrar profesor");
        System.out.println("3 Registrar curso");
        System.out.println("4 Asignar profesor a curso");
        System.out.println("5 Inscribir alumno a curso");
        System.out.println("6 Dar de baja alumno de curso");
        System.out.println("7 Eliminar (alumno o curso)");
        System.out.println("8 Ver reporte general");
        System.out.println("9 Guardar y salir");
        System.out.print("Selecciona una opción: ");
    }

    //metodos aux para pedir datos

    private static void registrarAlumno(Scanner sc, Sistema sis) {
        System.out.println("\n___ Nuevo Alumno ___");
        System.out.print("Nombre completo: ");
        String nombre = sc.nextLine();
        System.out.print("Matrícula: ");
        String matricula = sc.nextLine();
        System.out.print("Carrera: ");
        String carrera = sc.nextLine();
        System.out.print("Escuela de Procedencia: ");
        String procedencia = sc.nextLine();
        
        int edad = pedirEntero(sc, "Edad: ");

        sis.registrarAlumno(nombre, matricula, carrera, edad, procedencia);
    }

    private static void registrarProfesor(Scanner sc, Sistema sis) {
        System.out.println("\n___ Nuevo Profesor ___");
        System.out.print("Nombre completo: ");
        String nombre = sc.nextLine();
        System.out.print("Número de Empleado: ");
        String numEmp = sc.nextLine();
        System.out.print("Departamento: ");
        String depto = sc.nextLine();
        
        System.out.print("Niveles que imparte: ");
        String niveles = sc.nextLine();
        
        System.out.print("Categoría: ");
        String cat = sc.nextLine();
        
        System.out.print("Título: ");
        String titulo = sc.nextLine();
        
        int anio = pedirEntero(sc, "Año de titulación: ");

        sis.registrarProfesor(nombre, numEmp, depto, niveles, cat, titulo, anio);
    }

    private static void registrarCurso(Scanner sc, Sistema sis) {
        System.out.println("\n___ Nuevo Curso ___");
        System.out.print("Código del curso (ejemplo: MAT-101): ");
        String codigo = sc.nextLine();
        System.out.print("Nombre de la materia: ");
        String nombre = sc.nextLine();
        
        System.out.println("recuerda que el nivel y categoría deben coincidir con un profesor");
        System.out.print("Nivel (ej. Licenciatura): ");
        String nivel = sc.nextLine();
        System.out.print("Categoría requerida: ");
        String cat = sc.nextLine();
        
        System.out.print("Fecha inicio: ");
        String fIni = sc.nextLine();
        System.out.print("Fecha fin: ");
        String fFin = sc.nextLine();

        sis.registrarCurso(codigo, nombre, nivel, cat, fIni, fFin);
    }

    private static void asignarProfesorACurso(Scanner sc, Sistema sis) {
        System.out.print("\nID del profesor (ejemplo: PROF-1): ");
        String idProf = sc.nextLine();
        System.out.print("Código del curso: ");
        String codCurso = sc.nextLine();
        
        sis.asignarProfesor(idProf, codCurso);
    }

    private static void inscribirAlumno(Scanner sc, Sistema sis) {
        System.out.print("\nID del alumno: ");
        String idAlu = sc.nextLine();
        System.out.print("Código del curso: ");
        String codCurso = sc.nextLine();

        sis.inscribirAlumno(idAlu, codCurso);
    }

    private static void bajaAlumnoDeCurso(Scanner sc, Sistema sis) {
        System.out.print("\nID del Alumno: ");
        String idAlu = sc.nextLine();
        System.out.print("Código del curso a dar de baja: ");
        String codCurso = sc.nextLine();

        sis.desinscribirAlumno(idAlu, codCurso);
    }

    private static void eliminarRegistros(Scanner sc, Sistema sis) {
        System.out.println("\n1 Eliminar alumno");
        System.out.println("2 Eliminar curso");
        System.out.print("Opción: ");
        String op = sc.nextLine();

        if (op.equals("1")) {
            System.out.print("ID del Alumno a eliminar: ");
            String id = sc.nextLine();
            sis.eliminarAlumno(id);
        } else if (op.equals("2")) {
            System.out.print("Código del Curso a eliminar: ");
            String cod = sc.nextLine();
            sis.eliminarCurso(cod);
        } else {
            System.out.println("Opción no valida");
        }
    }

    /**
     * mtodo auxiliar para asegurar que el usuario ponga un numero valido
     * sin arruinar el flujo del programa
     */
    private static int pedirEntero(Scanner sc, String mensaje) {
        int numero = 0;
        boolean valido = false;
        while (!valido) {
            System.out.print(mensaje);
            try {
                String entrada = sc.nextLine();
                numero = Integer.parseInt(entrada);
                valido = true;
            } catch (NumberFormatException e) {
                System.out.println("Ingresa un número válido");
            }
        }
        return numero;
    }
}