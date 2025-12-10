import java.io.Serializable;

/**
 * representa un curso
 * maneja alumnos inscritos con un arreglo fijo
 */
public class Curso implements Serializable {
    private static final long serialVersionUID = 1L;

    private String codigo;
    private String nombre;
    private String nivel;
    private String categoria;
    
    //fechas
    private String fechaInicio;
    private String fechaFin;
    
    private Profesor profesorAsignado;
    
    //arreglo de alumno con capacidad de 50
    private Alumno[] alumnosInscritos;
    private int contadorAlumnos; 

    public Curso(String codigo, String nombre, String nivel, String categoria, String fInicio, String fFin) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.nivel = nivel;
        this.categoria = categoria;
        this.fechaInicio = fInicio;
        this.fechaFin = fFin;
        
        this.alumnosInscritos = new Alumno[50];
        this.contadorAlumnos = 0;
    }

    //metodos de gestion

    public boolean inscribirAlumno(Alumno a) {
        if (contadorAlumnos < 50) {
            alumnosInscritos[contadorAlumnos] = a;
            contadorAlumnos++;
            return true;
        }
        return false; //cuando el curso esta lleno
    }

    public void quitarAlumno(String idAlumno) {
        int pos = -1;
        for (int i = 0; i < contadorAlumnos; i++) {
            if (alumnosInscritos[i].getIdUnico().equals(idAlumno)) {
                pos = i;
                break;
            }
        }
        if (pos != -1) {
            for (int i = pos; i < contadorAlumnos - 1; i++) {
                alumnosInscritos[i] = alumnosInscritos[i + 1];
            }
            alumnosInscritos[contadorAlumnos - 1] = null;
            contadorAlumnos--;
        }
    }

    //getters y setters
    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getNivel() { return nivel; }
    public String getCategoria() { return categoria; }
    public int getContadorAlumnos() { return contadorAlumnos; }
    public Alumno[] getAlumnosInscritos() { return alumnosInscritos; }

    public Profesor getProfesorAsignado() { return profesorAsignado; }
    public void setProfesorAsignado(Profesor p) { this.profesorAsignado = p; }

    @Override
    public String toString() {
        String nProf = (profesorAsignado != null) ? profesorAsignado.getNombre() : "SIN ASIGNAR";
        return "[" + codigo + "] " + nombre + " (" + nivel + ") | Prof: " + nProf + " | Inscritos: " + contadorAlumnos;
    }
}