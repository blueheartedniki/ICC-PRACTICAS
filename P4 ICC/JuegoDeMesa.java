public class JuegoDeMesa {

    private String nombre;
    private int calificacion;
    private String genero;

    //constructor de la clase

    public JuegoDeMesa(String nombre, int calificacion, String genero) {
        this.nombre = nombre;
        this.calificacion = calificacion;
        this.genero = genero;
    }

    //getters para acceder a los atributos
    public String getNombre() {
        return nombre;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public String getGenero() {
        return genero;
    }

 
    @Override
    public String toString() {
        return "Juego: " + nombre + " | Calificación: " + calificacion + " | Género: " + genero;
    }
}