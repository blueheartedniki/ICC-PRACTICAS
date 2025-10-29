import java.util.Scanner;
import java.util.Arrays;

public class CatalogoJuegos {

    // Algoritmo utilizado: bubble sort
    // Es un algoritmo que funciona comparando pares de números que estan juntos y los intercambia si están en el orden incorrecto, asi se repite una y otra vez hasta que todos quedan en orden

   
    private static final int CAPACIDAD_MAXIMA = 100;
    private JuegoDeMesa[] catalogo;
    private int contadorJuegos;
    private Scanner scanner;

    public CatalogoJuegos() {
        this.catalogo = new JuegoDeMesa[CAPACIDAD_MAXIMA];
        this.contadorJuegos = 0;
        this.scanner = new Scanner(System.in);
    }
    
    // con esto se inicia el programa del catalogo
    public static void main(String[] args) {
        CatalogoJuegos app = new CatalogoJuegos();
        app.mostrarMenu();
    }
    

// se piden los datos necesarios para hacer un juego con las caracteristicas necesarias para meterlos al catalogo
    private void guardarJuego() {

        System.out.print("Ingrese el nombre del juego: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese la calificación del juego (1-10): ");
        int calificacion = 0;
        // bucle de validación para la calificación
        while (1<2) {
            if (scanner.hasNextInt()) {
                calificacion = scanner.nextInt();
                if (calificacion >= 1 && calificacion <= 10) {
                    break;
                }
                System.out.print("Por favor ingresa una calificación válida (1-10): ");
            } 
            scanner.nextLine(); // empezar de cero (nada)
        }
        scanner.nextLine(); 

        System.out.print("Ingresa el género del juego: ");
        String genero = scanner.nextLine();

        catalogo[contadorJuegos] = new JuegoDeMesa(nombre, calificacion, genero);
        contadorJuegos++;
        System.out.println("Has guardade tu juego correctamente");
    }
    
    // enseña los juegos que hay en el catalogo hasta el momento
    private void mostrarCatalogo() {
        if (contadorJuegos == 0) {
            System.out.println("El catálogo está vacío.");
            return;
        }

        System.out.println("\n--- Catálogo Actual ---");
        for (int i = 0; i < contadorJuegos; i++) {
            System.out.println((i + 1) + ". " + catalogo[i].toString());
        }
        System.out.println("-----------------------");
    }

  //ordena el catalogo por nombre usando bubble sort
    private void ordenarPorNombre() {
        int n = contadorJuegos;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                // se cpompara el nombre usando String.compareTo()
                if (catalogo[j].getNombre().compareTo(catalogo[j + 1].getNombre()) > 0) {
                    // I
                    JuegoDeMesa temp = catalogo[j];
                    catalogo[j] = catalogo[j + 1];
                    catalogo[j + 1] = temp;
                }
            }
        }
        System.out.println("Catálogo ordenado por Nombre:");
        mostrarCatalogo();
    }
    
  // ordena los juegos por su calificacion de menor a mayor
    private void ordenarPorCalificacion() {
        int n = contadorJuegos;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                // Para orden descendente: si el actual (j) es menor que el siguiente (j+1), intercambia
                if (catalogo[j].getCalificacion() < catalogo[j + 1].getCalificacion()) {
                    // Intercambio
                    JuegoDeMesa temp = catalogo[j];
                    catalogo[j] = catalogo[j + 1];
                    catalogo[j + 1] = temp;
                }
            }
        }
        System.out.println("Catálogo ordenado por Calificación (mejor al peor):");
        mostrarCatalogo();
    }

    // orden ell catalogo por genero, aqui se usa el algoritmo bubble sort
    private void ordenarPorGenero() {
        int n = contadorJuegos;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                // Compara el género usando String.compareTO
                if (catalogo[j].getGenero().compareTo(catalogo[j + 1].getGenero()) > 0) {
                    // Intercambio
                    JuegoDeMesa temp = catalogo[j];
                    catalogo[j] = catalogo[j + 1];
                    catalogo[j + 1] = temp;
                }
            }
        }
        System.out.println("Catálogo ordenado por género:");
        mostrarCatalogo();
    }

    //encuentra y muestra el juego con la calificación más alta.
    
    private void mejorCalificado() {
        if (contadorJuegos == 0) {
            System.out.println("No hay nada en el catalogo");
            return;
        }

        JuegoDeMesa mejor = catalogo[0];
        for (int i = 1; i < contadorJuegos; i++) {
            if (catalogo[i].getCalificacion() > mejor.getCalificacion()) {
                mejor = catalogo[i];
            }
        }
        System.out.println("\n Juego mejor calificado <3: " + mejor.toString());
    }

    // el juego con la calificación más baja
     
    private void peorCalificado() {
        if (contadorJuegos == 0) {
            System.out.println("El catálogo está vacío.");
            return;
        }

        JuegoDeMesa peor = catalogo[0];
        for (int i = 1; i < contadorJuegos; i++) {
            if (catalogo[i].getCalificacion() < peor.getCalificacion()) {
                peor = catalogo[i];
            }
        }
        System.out.println("\nJuego peor calificado: " + peor.toString());
    }

  // todo lo  que tiene que ver con lo que va a ver el usuario en la terminal    
     // se muestra el menu de opciones
    private void mostrarMenu() {
        int opcion = -1;

        while (opcion != 0) {
            System.out.println("\n==== Catálogo de Juegos de Mesa ====");
            System.out.println("1. Guardar nuevo juego");
            System.out.println("2. Mostrar catálogo completo");
            System.out.println("3. Ordenar por Nombre");
            System.out.println("4. Ordenar por Calificación");
            System.out.println("5. Ordenar por Género");
            System.out.println("6. Mostrar el juego MEJOR calificado");
            System.out.println("7. Mostrar el juego PEOR calificado");
            System.out.println("0. Salir");
            System.out.println("=====================================");
            System.out.print("Seleccione una opción: ");

            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                scanner.nextLine(); 
                
                switch (opcion) {
                    case 1:
                        guardarJuego();
                        break;
                    case 2:
                        mostrarCatalogo();
                        break;
                    case 3:
                        ordenarPorNombre();
                        break;
                    case 4:
                        ordenarPorCalificacion();
                        break;
                    case 5:
                        ordenarPorGenero();
                        break;
                    case 6:
                        mejorCalificado();
                        break;
                    case 7:
                        peorCalificado();
                        break;
                    case 0:
                        System.out.println("gracias por usar el catálogo");
                        break;
                    default:
                        System.out.println("Escribe un opcion valida");
                }
            } else {
                System.out.println("Escribe el numero de la opción que deseas");
                scanner.nextLine(); 
            }
        }
    }
}