import java.util.Random;
import java.util.Scanner;

public class BatallaNaval {
    
   
    
    private static void mostrarTableros(Tablero humano, Tablero ia) {
        System.out.println("\n--- TU TABLERO (PROPIO) ---");
        humano.mostrarTableroPropio();
        
        System.out.println("\n--- TABLERO ENEMIGO (DISPAROS) ---");
        ia.mostrarTableroDisparos();
    }
    
    // esto cononvierte una coordenada de a fila y columna.
    
    private static int[] parsearCoordenada(String input) {
        int fila = -1;
        int col = -1;
        String upperInput = input.toUpperCase();
        
        if (upperInput.length() >= 2) { 
            char colChar = upperInput.charAt(0);
            int tamaño = upperInput.length();
            
            //connvertir columna de A-J a 0-9
            if (colChar >= 'A' && colChar <= 'J') {
                col = colChar - 'A';
            }
            
            //obtiene la fila
            int num = 0;
            int factor = 1;
            int i = tamaño - 1;
            
            while (i > 0) { 
                char c = upperInput.charAt(i);
                if (c >= '0' && c <= '9') {
                    num = num + (c - '0') * factor;
                    factor = factor * 10;
                } else {
                    return new int[]{-1, -1}; 
                }
                i--;
            }
            
            //fila de 0 a 9
            if (num >= 0 && num < 10) { 
                fila = num;
            }
        }
        
        if (fila >= 0 && col >= 0) {
            return new int[]{fila, col};
        } else {
            return new int[]{-1, -1};
        }
    }

    private static int[] pedirCoordenada(Scanner scanner) {
        int[] coords;
        
        while (true) {
            System.out.print("Ingresa tu coordenada (ejemplo: A0 o J9): ");
            String input = scanner.nextLine();
            coords = parsearCoordenada(input);
            
            if (coords[0] != -1) {
                return coords;
            }
            System.out.println("escribe coordenadas validas");
        }
    }

    private static void colocarBarcosIA(Tablero tableroIA, Random random) {
        
        for (int i = 0; i < tableroIA.getBarcos().length; i++) {
            Barco barco = tableroIA.getBarco(i);
            boolean colocado = false;
            
            while (!colocado) {
                int fila = random.nextInt(tableroIA.getTamaño());
                int col = random.nextInt(tableroIA.getTamaño());
                boolean horizontal = random.nextBoolean();
                
                if (tableroIA.puedeColocarBarco(fila, col, barco.getLongitud(), horizontal)) {
                    tableroIA.colocarBarco(i, fila, col, horizontal);
                    colocado = true;
                }
            }
        }
    }

    // comienza el main
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("Bienvenido al juego de batalla naval");

        //prepara los tableros de los jugadores
        Tablero tableroHumano = new Tablero();
        Tablero tableroIA = new Tablero();
        
        //se ponen los barcos de la ia
        colocarBarcosIA(tableroIA, random);
        
        //el usuario elige donde poner los barcos en su tablero
        System.out.println("\nColocar barcos del jugador");
        for (int i = 0; i < tableroHumano.getBarcos().length; i++) {
            Barco barco = tableroHumano.getBarco(i);
            boolean colocado = false;
            
            while (!colocado) {
                tableroHumano.mostrarTableroPropio();
                System.out.println("Elige donde colocar tu barco de longitud " + barco.getLongitud() + ".");
                
                int[] coords = pedirCoordenada(scanner);
                int fila = coords[0];
                int col = coords[1];

                //aqui se decide si el barco se acomoda vertical o horizontal
                boolean horizontal = false;
                boolean orientacionValida = false;
                while (!orientacionValida) {
                    System.out.print("Verical u horizontal? (H/V): ");
                    String orientacion = scanner.nextLine().toUpperCase();
                    if (orientacion.equals("H")) {
                        horizontal = true;
                        orientacionValida = true;
                    } else {
                        if (orientacion.equals("V")) {
                            horizontal = false;
                            orientacionValida = true;
                        } else {
                            System.out.println("Escribe una orientacion valida");
                        }
                    }
                }

                //intentar colocar
                if (tableroHumano.puedeColocarBarco(fila, col, barco.getLongitud(), horizontal)) {
                    tableroHumano.colocarBarco(i, fila, col, horizontal);
                    colocado = true;
                    System.out.println("Has puesto tu barquito");
                } else {
                    System.out.println("No puedes poner tus barcos fuera del tablero que se muestra, intentalo otra vez");
                }
            }
        }
        tableroHumano.mostrarTableroPropio();

        //se decide quien inicia el juegp
        boolean esTurnoHumano = random.nextBoolean();
        if (esTurnoHumano) {
            System.out.println("\nTu inicias el juego");
        } else {
            System.out.println("\nLa IA inicia el juego");
        }
        
        //bucle principal del juego
        boolean juegoTerminado = false;
        while (!juegoTerminado) {
            if (esTurnoHumano) {
                
                System.out.println("\nTE TOCA");
                
                mostrarTableros(tableroHumano, tableroIA);

                int resultadoDisparo = -1;
                int fila = -1; 
                int col = -1;  
                
                while (resultadoDisparo == -1) {
                    int[] coords = pedirCoordenada(scanner);
                    fila = coords[0];
                    col = coords[1];
                    
                    resultadoDisparo = tableroIA.disparar(fila, col);
                    
                    if (resultadoDisparo == -1) {
                        System.out.println("No puedes disparar en esta coordenada, intenta con otra");
                    }
                }

                String coordenadaStr = "" + (char)('A' + col) + fila;
                System.out.print("Elegiste disparar aqui: " + coordenadaStr + ": ");
                if (resultadoDisparo == 1) {
                    System.out.println("LE DISTE");
                } else {
                    System.out.println("FALLASTE");
                }
                mostrarTableros(tableroHumano, tableroIA);
                
                if (tableroIA.todosHundidos()) {
                    System.out.println("\nHas hundido todos los barcos de la IA, eres el ganador!");
                    juegoTerminado = true;
                }
                
            } else {
                //juego de la ia
                System.out.println("\nEs turno de la IA");
                
                int fila = -1; 
                int col = -1;  
                int resultadoDisparo;
                
                while (true) {
                    fila = random.nextInt(tableroHumano.getTamaño());
                    col = random.nextInt(tableroHumano.getTamaño());
                    
                    resultadoDisparo = tableroHumano.disparar(fila, col); 
                    
                    if (resultadoDisparo != -1) {
                        break;
                    }
                }
                
                String coordenadaStr = "" + (char)('A' + col) + fila;
                System.out.println("La IA disparó a " + coordenadaStr + " en tu tablero.");

                System.out.print("Resultado del impacto: ");
                if (resultadoDisparo == 1) {
                    System.out.println("Le dieron a tu barcoo TnT");
                } else {
                    System.out.println("AGUA");
                }
                mostrarTableros(tableroHumano, tableroIA);
                
                if (tableroHumano.todosHundidos()) {
                    System.out.println("\nTodos tus barcos han sido eliminados, has perdido!! ");
                    juegoTerminado = true;
                }
            }
            
            if (!juegoTerminado) {
                esTurnoHumano = !esTurnoHumano;
            }
        }
        
        scanner.close();
    }
}