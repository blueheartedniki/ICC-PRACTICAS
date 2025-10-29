import java.util.Random;

public class TableroMemorama {

    //tamaño del tablero
    private static final int TAMANO = 4;
    private static final int NUMERO_PARES = 8;
    
    //caracteres especiales
    private static final char CUBIERTO = '#';
    private static final char[] SIMBOLOS = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};

    //arreglo bidimensional para el tablero donde estan los pares
    private char[][] tableroReal;
    
    //arreglo para el tablero visible para el usuario
    private char[][] tableroVisible;
    
    
    private int paresEncontrados;

    //constructor de la clase
    public TableroMemorama() {
        this.tableroReal = new char[TAMANO][TAMANO];
        this.tableroVisible = new char[TAMANO][TAMANO];
        this.paresEncontrados = 0;
        
        inicializarTableroReal();
        inicializarTableroVisible();
    }

    //inicis el tablero con los simbolos en lugares aleatorios
    public void inicializarTableroReal() {
        //un arreglo temporal para guardar los pares de simbolos
        char[] simbolosAColocar = new char[TAMANO * TAMANO];
        int indice = 0;
        for (int i = 0; i < NUMERO_PARES; i++) {
            simbolosAColocar[indice++] = SIMBOLOS[i];
            simbolosAColocar[indice++] = SIMBOLOS[i];
        }


        Random rand = new Random();
        for (int i = simbolosAColocar.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            char temp = simbolosAColocar[i];
            simbolosAColocar[i] = simbolosAColocar[j];
            simbolosAColocar[j] = temp;
        }

        //poner los simbolos mezclados en el arreglo 
        indice = 0;
        for (int i = 0; i < TAMANO; i++) {
            for (int j = 0; j < TAMANO; j++) {
                this.tableroReal[i][j] = simbolosAColocar[indice++];
            }
        }
    }

    // inicia el tablero que ve el jugador cuando esta tapado
    public void inicializarTableroVisible() {
        for (int i = 0; i < TAMANO; i++) {
            for (int j = 0; j < TAMANO; j++) {
                this.tableroVisible[i][j] = CUBIERTO;
            }
        }
    }

    //muestra las coordenadas y el tablero actual para el jugador
    public void imprimirTablero() {
        System.out.print("  ");
        for (int k = 0; k < TAMANO; k++) {
            System.out.print(" " + k); //coordenadas de la columna
        }
        System.out.println();
        System.out.println("  " + "---------"); //separador
        
        for (int i = 0; i < TAMANO; i++) {
            System.out.print(i + "|"); //coordenadas de las filas
            for (int j = 0; j < TAMANO; j++) {
                System.out.print(" " + this.tableroVisible[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    //marca un aposicion en el tablero y lo descubre para ver su simbolo
    public void descubrir(int fila, int columna) {
        this.tableroVisible[fila][columna] = this.tableroReal[fila][columna];
    }

    //vuelve a cubrir por si no coincidieron
    public void cubrir(int fila1, int col1, int fila2, int col2) {
        this.tableroVisible[fila1][col1] = CUBIERTO;
        this.tableroVisible[fila2][col2] = CUBIERTO;
    }

    //compara si dos posiciones tienen el mismo simbolo
    public boolean hayCoincidencia(int fila1, int col1, int fila2, int col2) {
        if (this.tableroReal[fila1][col1] == this.tableroReal[fila2][col2]) {
            this.paresEncontrados++;
            return true;
        }
        return false;
    }

    //checar si el juego ya termino
    public boolean juegoTerminado() {
        //se termina el juego si todos los pares fueron encontrados
        return this.paresEncontrados == NUMERO_PARES;
    }

    //checa si una posicion esta descubierta 
    public boolean yaDescubierta(int fila, int columna) {
        return this.tableroVisible[fila][columna] != CUBIERTO;
    }

    //checa que las coordenadas esten dentro de los limites del tablero
    public boolean coordenadasValidas(int fila, int columna) {
        boolean filaValida = fila >= 0 && fila < TAMANO;
        boolean columnaValida = columna >= 0 && columna < TAMANO;
        return filaValida && columnaValida;
    }
}