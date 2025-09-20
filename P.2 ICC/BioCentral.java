import java.util.Scanner;

public class BioCentral {
    public static void main(String[] args) {

     System.out.println(TextoColor.daColorTexto("Bienvenido a la Biblioteca central de la Universidad Nacional Autónoma de México", TextoColor.CYAN));
        //Creación de la biblioteca y de libros
        Biblioteca Central = new Biblioteca();
        Libro Karenina = new Libro("9788437606323","León Tolsói","Anna Karenina","Romance");
        Libro Soledad = new Libro("9788420471839","Gabriel García Márquez","Cien años de soledad","Fantasía");
        Libro Robot = new Libro("9788445071607","Isaac Asimov","Yo, robot","Ciencia Ficción");
        Libro Orgullo = new Libro("97841051572","Jane Austen","Orgullo y prejuicio","Romance");
        Libro Crimen = new Libro("9788420677569","Fiódor Dostoievski","Crimen y castigo","Filosofía");
        Libro Frank = new Libro("9784520658421","Mary Shelley","Frankenstein","Ciencia Ficción");
        Libro Capital = new Libro("9789682302061","Karl Marx","El Capital","Historia");
        Libro Sentimientos = new Libro("9789681685362","José María Morelos y Pavón","El sentimiento de las naciones","Historia");
        Libro Superior = new Libro("9789682410708","Carmen Laveaga","Álgebra Superior, curso completo","Académico");
        Libro Discretas = new Libro("9786070280955","Miranda Viso","Matemáticas Discretas","Académico");

        //meter libros en biblioteca 
        Central.agregaLibro(Karenina);
        Central.agregaLibro(Soledad);
        Central.agregaLibro(Robot);
        Central.agregaLibro(Orgullo);
        Central.agregaLibro(Crimen);
        Central.agregaLibro(Frank);
        Central.agregaLibro(Capital);
        Central.agregaLibro(Sentimientos);
        Central.agregaLibro(Superior);
        Central.agregaLibro(Discretas);

        Scanner Lector = new Scanner(System.in);
        String Peticion = "";

        while (1<2) {

            // instrucciones al usuario
            System.out.println(TextoColor.daColorTexto("Por favor, escriba lo que desea hacer", TextoColor.AMARILLO));
            System.out.println(TextoColor.daColorTexto("Si desea registrar un nuevo libro, escriba REGISTRO", TextoColor.AZUL));
            System.out.println(TextoColor.daColorTexto("Si desea buscar un libro por su ISBN, escriba BUSCAR POR ISBN", TextoColor.MORADO));
            System.out.println(TextoColor.daColorTexto("Si desea buscar un libro por autor, escriba BUSCAR POR AUTOR", TextoColor.VERDE));
            System.out.println("Si desea buscar un libro por título, escriba BUSCAR POR TITULO");
            System.out.println(TextoColor.daColorTexto("Si desea buscar un libro por género, escriba BUSCAR POR GENERO", TextoColor.CYAN));
            System.out.println(TextoColor.daColorTexto("Si desea ver todos los libros en la biblioteca, escriba VER LIBROS", TextoColor.AMARILLO));
            System.out.println(TextoColor.daColorTexto("Para salir del programa, escriba SALIR", TextoColor.ROJO));
            Peticion = Lector.nextLine();

            if(Peticion.equalsIgnoreCase("REGISTRO")) {
                System.out.println("Escriba el ISBN del libro que deseas registrar");
                String IsbnR = Lector.nextLine();
        
                if(IsbnR.equals("9788437606323") || IsbnR.equals("9788420471839") || IsbnR.equals("9788445071607") || IsbnR.equals("9788491051572") || IsbnR.equals("9788420677569") || IsbnR.equals("9788420658421") || IsbnR.equals("9789681685362") || IsbnR.equals("9789682410708") || IsbnR.equals("9786070280955")) {
                    System.out.println(TextoColor.daColorTexto("Este ISBN ya está registrado", TextoColor.ROJO));
                } else {
                    System.out.println("Escriba el Autor de su libro");
                    String AutoR = Lector.nextLine();
                    System.out.println("Escriba el título de su libro");
                    String TituloR = Lector.nextLine();
                    System.out.println("Escriba el género de su libro");
                    String GeneroR = Lector.nextLine();
                    
                    Libro LibroNuevo = new Libro (IsbnR,AutoR,TituloR,GeneroR);
                    Central.agregaLibro(LibroNuevo);
                    System.out.println(TextoColor.daColorTexto("Registraste este libro con éxito:", TextoColor.CYAN));
                    System.out.println(LibroNuevo);
                }
            } else if(Peticion.equalsIgnoreCase("BUSCAR POR ISBN")) {
                System.out.println("Escriba el ISBN del libro que deseas buscar");
                String BuscarIsbn = Lector.nextLine();
                Libro libroEncontrado = Central.obtenLibroPorISBN(BuscarIsbn);
                System.out.println(libroEncontrado);
                
            } else if(Peticion.equalsIgnoreCase("BUSCAR POR AUTOR")) {
                System.out.println("Escriba el autor del libro que busca");
                String BuscAutor = Lector.nextLine();
                String librAutor = Central.filtraPor("AUTOR",BuscAutor,"______________________");
                System.out.println("Este autor tiene estos libros");
                System.out.println(librAutor);
                
            } else if(Peticion.equalsIgnoreCase("BUSCAR POR TITULO")) {
                System.out.println("Escriba el título del libro que buscas"); 
                String BuscaTit = Lector.nextLine();
                String LibTit = Central.filtraPor("TITULO",BuscaTit,"_______________________");
                System.out.println("Se encontró los siguiente");
                System.out.println(LibTit);




            } else if(Peticion.equalsIgnoreCase("BUSCAR POR GENERO")) {
                System.out.println("Escriba el género del libro que busca");
                String BuscaGenero = Lector.nextLine();
                String GenBusca = Central.filtraPor("GENERO",BuscaGenero,"_________________");
                System.out.println("Se encontró esto");
                System.out.println(GenBusca);
               



            } else if(Peticion.equalsIgnoreCase("VER LIBROS")) {
                String muestra = Central.obtenTodosLosLibros("----------------------------------------");
                System.out.println(muestra);
            } else if (Peticion.equalsIgnoreCase("SALIR")) {
                System.exit(0);
            } else {
                System.out.println(TextoColor.daColorTexto("Por favor, escribe una orden válida", TextoColor.ROJO));
            }
        }
        
    }
}