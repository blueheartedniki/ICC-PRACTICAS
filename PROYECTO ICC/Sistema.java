import java.io.*; 

//sistema principal
public class Sistema {

    //constante para el tamaño maximo de las bases de datos 
    private static final int MAX = 100;

    //arreglos principales
    private Alumno[] listaAlumnos;
    private int totalAlumnos;

    private Profesor[] listaProfesores;
    private int totalProfesores;

    private Curso[] listaCursos;
    private int totalCursos;

    public Sistema() {
        listaAlumnos = new Alumno[MAX];
        totalAlumnos = 0;

        listaProfesores = new Profesor[MAX];
        totalProfesores = 0;

        listaCursos = new Curso[MAX];
        totalCursos = 0;
    }

    //aqui se genera el id
    private String generarId(String prefijo, int total) {
        return prefijo + (total + 1);
    }

    //metodos de registro

    public void registrarAlumno(String nom, String mat, String carr, int edad, String proc) {
        if (totalAlumnos < MAX) {
            String id = generarId("ALU", totalAlumnos);
            listaAlumnos[totalAlumnos] = new Alumno(id, nom, mat, carr, edad, proc);
            totalAlumnos++;
            System.out.println("Alumno registrado: " + id);
        } else {
            System.out.println("La base de datos esta llena");
        }
    }

    public void registrarProfesor(String nom, String emp, String dep, String niv, String cat, String tit, int anio) {
        if (totalProfesores < MAX) {
            String id = generarId("PROF", totalProfesores);
            listaProfesores[totalProfesores] = new Profesor(id, nom, emp, dep, niv, cat, tit, anio);
            totalProfesores++;
            System.out.println("Profesor registrado: " + id);
        } else {
            System.out.println("Base de datos de profes llena");
        }
    }

    public void registrarCurso(String cod, String nom, String niv, String cat, String fIni, String fFin) {
        if (totalCursos < MAX) {
            listaCursos[totalCursos] = new Curso(cod, nom, niv, cat, fIni, fFin);
            totalCursos++;
            System.out.println("Curso creado: " + cod);
        } else {
            System.out.println("La basse de datos de cursos esta llena");
        }
    }

    

    //reasignacion de profesor
    public void asignarProfesor(String idProf, String codCurso) {
        Profesor p = buscarProfesor(idProf);
        Curso c = buscarCurso(codCurso);

        if (p == null || c == null) {
            System.out.println("No se encontró");
            return;
        }

        //se valida nivel y categoria comparando strings

        boolean nivelOk = p.getNiveles().equalsIgnoreCase(c.getNivel());
        boolean catOk = p.getCategoria().equalsIgnoreCase(c.getCategoria());

        if (nivelOk && catOk) {
            c.setProfesorAsignado(p);
            System.out.println("Profesor asignado correctamente");
        } else {
            System.out.println("El profesor no cumple con el nivel o categoría");
        }
    }

    //inscripcion
    public void inscribirAlumno(String idAlum, String codCurso) {
        Alumno a = buscarAlumno(idAlum);
        Curso c = buscarCurso(codCurso);

        if (a != null && c != null) {
            //se valida si ya esta inscrito
            if (a.estaInscrito(codCurso)) {
                System.out.println("El alumno ya está en este curso.");
                return;
            }
            //validar maximo de materias (6)
            if (a.getCantidadCursos() >= 6) {
                System.out.println("El alumno ya tiene 6 materias.");
                return;
            }
            
            //se realiza la inscripcion en ambos lados
            boolean exitoCurso = c.inscribirAlumno(a);
            if (exitoCurso) {
                a.agregarCurso(codCurso);
                System.out.println("Alumno inscrito con éxito.");
            } else {
                System.out.println("El curso está lleno.");
            }
        }
    }

    //desinscripcion
    public void desinscribirAlumno(String idAlum, String codCurso) {
        Alumno a = buscarAlumno(idAlum);
        Curso c = buscarCurso(codCurso);

        if (a != null && c != null) {
            c.quitarAlumno(idAlum);
            a.quitarCurso(codCurso);
            System.out.println("Alumno retirado del curso.");
        }
    }

    //edicion y eliminacion

    public void eliminarAlumno(String id) {
        int pos = -1;
        for (int i = 0; i < totalAlumnos; i++) {
            if (listaAlumnos[i].getIdUnico().equals(id)) {
                pos = i; 
                break;
            }
        }

        if (pos != -1) {
            Alumno a = listaAlumnos[pos];
            //validacion, no eliminar si tiene cursos
            if (a.getCantidadCursos() > 0) {
                System.out.println("No se puede eliminar porque el alumno tiene cursos inscritos");
                return;
            }

            //se elimina y recorre el arreglo
            for (int i = pos; i < totalAlumnos - 1; i++) {
                listaAlumnos[i] = listaAlumnos[i + 1];
            }
            listaAlumnos[totalAlumnos - 1] = null;
            totalAlumnos--;
            System.out.println("Alumno eliminado");
        }
    }

    public void eliminarCurso(String codigo) {
        int pos = -1;
        for (int i = 0; i < totalCursos; i++) {
            if (listaCursos[i].getCodigo().equals(codigo)) {
                pos = i;
                break;
            }
        }
        
        if (pos != -1) {
            Curso c = listaCursos[pos];
            //se valida y se elimina solo si no tiene alumnos insxcritos
            if (c.getContadorAlumnos() > 0) {
                System.out.println("No se puede eliminar el curso porque hay alumnos inscritos");
                return;
            }

            for (int i = pos; i < totalCursos - 1; i++) {
                listaCursos[i] = listaCursos[i + 1];
            }
            listaCursos[totalCursos - 1] = null;
            totalCursos--;
            System.out.println("Curso eliminado");
        }
    }

    //busquedas
    private Alumno buscarAlumno(String id) {
        for (int i = 0; i < totalAlumnos; i++) {
            if (listaAlumnos[i].getIdUnico().equals(id)) return listaAlumnos[i];
        }
        return null;
    }

    private Profesor buscarProfesor(String id) {
        for (int i = 0; i < totalProfesores; i++) {
            if (listaProfesores[i].getIdUnico().equals(id)) return listaProfesores[i];
        }
        return null;
    }

    private Curso buscarCurso(String cod) {
        for (int i = 0; i < totalCursos; i++) {
            if (listaCursos[i].getCodigo().equals(cod)) return listaCursos[i];
        }
        return null;
    }

    //mostrar datos
    public void mostrarTodo() {
        System.out.println("\n____ LISTA DE ALUMNOS ____");
        for (int i = 0; i < totalAlumnos; i++) System.out.println(listaAlumnos[i]);
        
        System.out.println("\n____ LISTA DE PROFESORES ____");
        for (int i = 0; i < totalProfesores; i++) System.out.println(listaProfesores[i]);

        System.out.println("\n ____LISTA DE CURSOS ____");
        for (int i = 0; i < totalCursos; i++) System.out.println(listaCursos[i]);
    }
    
    //persistencia: se guardan los arreglos completos
    
    public void guardarDatos() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("escuela.dat"))) {
            oos.writeObject(listaAlumnos);
            oos.writeObject(listaProfesores);
            oos.writeObject(listaCursos);
            //se guardan los contadores para saber cuantos son validos al cargar
            oos.writeInt(totalAlumnos);
            oos.writeInt(totalProfesores);
            oos.writeInt(totalCursos);
            System.out.println("Datos guardados.");
        } catch (IOException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }

    public void cargarDatos() {
        File f = new File("escuela.dat");
        if (!f.exists()) return;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            listaAlumnos = (Alumno[]) ois.readObject();
            listaProfesores = (Profesor[]) ois.readObject();
            listaCursos = (Curso[]) ois.readObject();
            
            // Recuperamos los contadores
            totalAlumnos = ois.readInt();
            totalProfesores = ois.readInt();
            totalCursos = ois.readInt();
            
            System.out.println("Los datos se han cargado correctamente");
        } catch (Exception e) {
            System.out.println("No se pudieron cargar los datos");
        }
    }
}