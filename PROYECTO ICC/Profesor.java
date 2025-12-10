import java.io.Serializable;

/**
 * representa a un profesor
 */
public class Profesor implements Serializable {
    private static final long serialVersionUID = 1L;

    private String idUnico;
    private String nombre;
    private String numEmpleado;
    private String departamento;
    
    private String niveles;    
    private String categoria;  
    private String titulo;     
    private int anioTitulacion;

    public Profesor(String idUnico, String nombre, String numEmpleado, String departamento, 
                    String niveles, String categoria, String titulo, int anioTitulacion) {
        this.idUnico = idUnico;
        this.nombre = nombre;
        this.numEmpleado = numEmpleado;
        this.departamento = departamento;
        this.niveles = niveles;
        this.categoria = categoria;
        this.titulo = titulo;
        this.anioTitulacion = anioTitulacion;
    }

    public String getIdUnico() { return idUnico; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getNiveles() { return niveles; }
    public String getCategoria() { return categoria; }

    @Override
    public String toString() {
        return "ID: " + idUnico + " | " + nombre + " | Cat: " + categoria;
    }
}