public class Barco {
    private final int longitud;
    private int impactosRecibidos;
    private boolean hundido;
    private int filaInicial;
    private int colInicial;
    private boolean horizontal;

    public Barco(int longitud) {
        this.longitud = longitud;
        this.impactosRecibidos = 0;
        this.hundido = false;
        this.filaInicial = -1; 
        this.colInicial = -1;
        this.horizontal = false;
    }

    public int getLongitud() {
        return longitud;
    }

    public int getFilaInicial() {
        return filaInicial;
    }

    public int getColInicial() {
        return colInicial;
    }

    public boolean esHorizontal() {
        return horizontal;
    }

    public void colocar(int fila, int col, boolean horizontal) {
        this.filaInicial = fila;
        this.colInicial = col;
        this.horizontal = horizontal;
    }

    public void registrarImpacto() {
        impactosRecibidos++;
        if (impactosRecibidos >= longitud) {
            hundido = true;
        }
    }

    public boolean estaHundido() {
        return hundido;
    }
}