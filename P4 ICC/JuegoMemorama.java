import java.util.Scanner;


public class JuegoMemorama {

    private static Scanner scanner = new Scanner(System.in);

    //arranque
    public static void main(String[] args) {
        JuegoMemorama juego = new JuegoMemorama();
        juego.iniciarJuego();
    }
    
    
    public void iniciarJuego() {
        TableroMemorama tablero = new TableroMemorama();
        int movimientos = 0;
        
        //muestra el tablero totalmente cubierto junto con las coordenadas
        System.out.println("¡Bienvenido al Memorama (4x4)!");
        System.out.println("Tablero inicial:");
        tablero.imprimirTablero();

        while (!tablero.juegoTerminado()) {
            System.out.println("--- Turno #" + (movimientos + 1) + " ---");

            //probar si se descubre el primer simbolo
            int[] coord1 = solicitarCoordenadas("Primer símbolo (ejemplo: 1 2): ", tablero);
            tablero.descubrir(coord1[0], coord1[1]);
            System.out.println("\nTablero con el primer símbolo descubierto:");
            tablero.imprimirTablero();

            //prueba para ver si se encuentra el segundo símbolo
            int[] coord2 = solicitarCoordenadas("Segundo símbolo: ", tablero);
            
            //se debe asegurar que la segunda coordenada no sea la misma que la primera
            while (coord1[0] == coord2[0] && coord1[1] == coord2[1]) {
                System.out.println("Elige una coordenada distita");
                coord2 = solicitarCoordenadas("Segundo símbolo: ", tablero);
            }
            
            tablero.descubrir(coord2[0], coord2[1]);
            System.out.println("\nTablero con los dos símbolos descubiertos:");
            tablero.imprimirTablero();
            
            movimientos++; //aqunque no le haya atinado al par, cuenta como un turno 

            //se verifica si hay coincidencia
            if (tablero.hayCoincidencia(coord1[0], coord1[1], coord2[0], coord2[1])) {
                System.out.println("Son iguales, los has encontrado!");
            } else {
                System.out.println("No son iguales unu, el tablero se cubrirá todo de nuevo");
                
                //da el tablero de nuevo pero con los símbolos cubiertos
                tablero.cubrir(coord1[0], coord1[1], coord2[0], coord2[1]);
                tablero.imprimirTablero(); 
            }
        }
        
        //cuando se descubran todos los pares debe decirse la cantidad de movimientos
        System.out.println("==========================================");
        System.out.println("Muchas felicidades, le atinaste a todos!");
        System.out.println("Hiciste esta cantidad de movimientos:" + movimientos);
        System.out.println("=============================================");
    }
    
    //metodo auxiliar para manejar la entrada de coordenadas del usuario
    private int[] solicitarCoordenadas(String mensaje, TableroMemorama tablero) {
        int fila = -1;
        int columna = -1;
        boolean entradaValida = false;

        while (!entradaValida) {
            System.out.print(mensaje);
            try {
                //coordenadas como enteros
                fila = scanner.nextInt();
                columna = scanner.nextInt();
                
                if (!tablero.coordenadasValidas(fila, columna)) {
                    System.out.println("Error: Coordenadas fuera del rango (0 a 3). Intente de nuevo.");
                } else if (tablero.yaDescubierta(fila, columna)) {
                    System.out.println("Error: Esta casilla ya está descubierta. Intente con otra.");
                } else {
                    entradaValida = true;
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Error: Ingrese dos números enteros para la fila y columna (ejemplo: 1 2).");
                scanner.next();
            }
        }
        
        int[] coordenadas = {fila, columna};
        return coordenadas;
    }
}