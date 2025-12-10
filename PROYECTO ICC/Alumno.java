import java.io.Serializable;

/**
 * esto epresenta a un alumno
 * se usan arreglos primitivos para gestionar sus cursos
 */
public class Alumno implements Serializable {
    private static final long serialVersionUID = 1L;

    // atributos personales
    private String idUnico;
    private String nombre;
    private String matricula;
    private String carrera;
    private int edad;
    private String escuelaProcedencia;

    // Ccntrol de materias (max 6)
    private String[] codigosCursos; 
    private int cantidadCursos; // contador de cursos inscritos

    public Alumno(String idUnico, String nombre, String matricula, String carrera, int edad, String escuelaProcedencia) {
        this.idUnico = idUnico;
        this.nombre = nombre;
        this.matricula = matricula;
        this.carrera = carrera;
        this.edad = edad;
        this.escuelaProcedencia = escuelaProcedencia;
        
        //arreglo con capacidad para 6 materias
        this.codigosCursos = new String[6];
        this.cantidadCursos = 0;
    }

    //gestion de cursos (arreglos)

    /**
     * intenta registrar un codigo de curso en el arreglo del alumno
     * @return true si se agrego, false si ya tiene 6
     */
    public boolean agregarCurso(String codigo) {
        if (cantidadCursos < 6) {
            codigosCursos[cantidadCursos] = codigo;
            cantidadCursos++;
            return true;
        }
        return false;
    }

    /**
     * elimina un curso del arreglo y recorre los elementos para no dejar huecos
     */
    public void quitarCurso(String codigo) {
        int pos = -1;
        //busca posicion
        for (int i = 0; i < cantidadCursos; i++) {
            if (codigosCursos[i].equals(codigo)) {
                pos = i;
                break;
            }
        }
        //elimina y recorre si existe
        if (pos != -1) {
            for (int i = pos; i < cantidadCursos - 1; i++) {
                codigosCursos[i] = codigosCursos[i + 1];
            }
            codigosCursos[cantidadCursos - 1] = null; // Limpiar el último
            cantidadCursos--;
        }
    }

    public boolean estaInscrito(String codigo) {
        for (int i = 0; i < cantidadCursos; i++) {
            if (codigosCursos[i].equals(codigo)) return true;
        }
        return false;
    }

    //setters y getters
    public String getIdUnico() { return idUnico; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; } // Para editar
    public int getCantidadCursos() { return cantidadCursos; }
    public String getCarrera() { return carrera; }
    public void setCarrera(String carrera) { this.carrera = carrera; } // Para editar

    @Override
    public String toString() {
        return "ID: " + idUnico + " | " + nombre + " | Cursos: " + cantidadCursos + "/6";
    }
}