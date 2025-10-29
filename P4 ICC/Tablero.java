import java.util.Random;

public class Tablero {
    private final char[][] tablero;
    private final char[][] disparos;
    private final Barco[] barcos;
    private final int tamaño;
    
    public Tablero() {
        tamaño = 10;
        tablero = new char[tamaño][tamaño];
        disparos = new char[tamaño][tamaño];
        barcos = new Barco[4];
        barcos[0] = new Barco(4);
        barcos[1] = new Barco(3);
        barcos[2] = new Barco(3);
        barcos[3] = new Barco(2);
        inicializarTableros();
    }
    
    private void inicializarTableros() {
        for (int i = 0; i < tamaño; i++) {
            for (int j = 0; j < tamaño; j++) {
                tablero[i][j] = '~'; //esto es el agua
                disparos[i][j] = '~'; //lo que el contrincante ve 
            }
        }
    }
    
    
    public Barco[] getBarcos() {
        return barcos;
    }
    
    public boolean puedeColocarBarco(int fila, int col, int longitud, boolean horizontal) {
        if (fila < 0 || fila >= tamaño || col < 0 || col >= tamaño) {
            return false;
        }
        
        if (horizontal) {
            if (col + longitud > tamaño) {
                return false;
            }
            for (int j = col; j < col + longitud; j++) {
                if (tablero[fila][j] != '~') {
                    return false;
                }
            }
        } else {
            if (fila + longitud > tamaño) {
                return false;
            }
            for (int i = fila; i < fila + longitud; i++) {
                if (tablero[i][col] != '~') {
                    return false;
                }
            }
        }
        return true;
    }
    
    public void colocarBarco(int indice, int fila, int col, boolean horizontal) {
        barcos[indice].colocar(fila, col, horizontal);
        int longitud = barcos[indice].getLongitud();
        
        if (horizontal) {
            for (int j = col; j < col + longitud; j++) {
                tablero[fila][j] = 'B';
            }
        } else {
            for (int i = fila; i < fila + longitud; i++) {
                tablero[i][col] = 'B';
            }
        }
    }
    
    // procesa un disparo, da 1 si se le atina a algo (X), 0 si fallo ('O') o da -1 si ya se disparo antes ahí o no es valida la entrada
    
    public int disparar(int fila, int col) {
        if (fila < 0 || fila >= tamaño || col < 0 || col >= tamaño) {
            return -1;
        }
        if (disparos[fila][col] != '~') {
            return -1;
        }
        
        if (tablero[fila][col] == 'B') {
            tablero[fila][col] = 'X';
            disparos[fila][col] = 'X';
            registrarImpactoEnBarco(fila, col);
            return 1;
        } else {
            disparos[fila][col] = 'O';
            return 0;
        }
    }
    
    private void registrarImpactoEnBarco(int fila, int col) {
        for (int i = 0; i < barcos.length; i++) {
            Barco barco = barcos[i];
            int filaIni = barco.getFilaInicial();
            int colIni = barco.getColInicial();
            int longitud = barco.getLongitud();
            
            if (barco.esHorizontal()) {
                if (fila == filaIni) {
                    if (col >= colIni) {
                        if (col < colIni + longitud) {
                            barco.registrarImpacto();
                            return;
                        }
                    }
                }
            } else {
                if (col == colIni) {
                    if (fila >= filaIni) {
                        if (fila < filaIni + longitud) {
                            barco.registrarImpacto();
                            return;
                        }
                    }
                }
            }
        }
    }
    
    public boolean todosHundidos() {
        for (int i = 0; i < barcos.length; i++) {
            if (!barcos[i].estaHundido()) {
                return false;
            }
        }
        return true;
    }
    
    public void mostrarTableroPropio() {
        System.out.println("\n  A B C D E F G H I J");
        for (int i = 0; i < tamaño; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < tamaño; j++) {
                System.out.print(tablero[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    public void mostrarTableroDisparos() {
        System.out.println("\n  A B C D E F G H I J");
        for (int i = 0; i < tamaño; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < tamaño; j++) {
                System.out.print(disparos[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    public int getTamaño() {
        return tamaño;
    }
    
    public Barco getBarco(int indice) {
        return barcos[indice];
    }
}