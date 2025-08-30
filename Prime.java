import java.util.Scanner;

public class Prime {
  public static void main(String[] args) {
    //Bienvenida y petición al usuario
    System.out.println("Hola, por favor, ingresa un dígito");
    //Declaración del scanner y se ocupa para reconocer cual fue el numero que puso el usuario
    Scanner lector = new Scanner(System.in);
    int numH = lector.nextInt();
    //Saca valor absoluto del num
    int Vabs = Math.abs(numH);
    int primerValor = 1;
    //Se solicita al usuario no ocupar 0
    if(numH == 0) {
      System.out.println("Este dígito no es válido, por favor, escribe otro");
    } 
    //Si el dígito ingresado por el usuario es válido, se inicia un bucle con while
  while(primerValor <= numH) {
    //Se usa la clase de suma y se le asignan valores a la fórmula
    SumaN suma = new SumaN(1,2);
    Primo primo = new Primo();
    int evaluado = suma.evalua(primerValor);
    boolean primix = primo.esPrimo(evaluado);
    //Si el resultado de la evaluacion es primo, se le informa al usuario
    if(primix == true) {
      System.out.println(evaluado + " Es primo");

    }else{
      System.out.println(evaluado);
    }
    //el valor inicial aumenta en 1 
    primerValor ++;
    

  }
  
  }
      
    
}







  


