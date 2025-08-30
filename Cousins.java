import java.util.Scanner;

public class Cousins {
  public static void main(String[] args) {
    System.out.println("Hola, por favor, ingresa un dígito");
    Scanner lector = new Scanner(System.in);
    int numH = lector.nextInt();
    int Vabs = Math.abs(numH);
    int primerValor = 1;
    if(numH == 0) {
      System.out.println("Este dígito no es válido, por favor, escribe otro");
    } 
  while(primerValor <= numH) {
    SumaN suma = new SumaN(1,2);
    Primo primo = new Primo();
    int evaluado = suma.evalua(primerValor);
    boolean primix = primo.esPrimo(evaluado);
    if(primix == true) {
      System.out.println(evaluado + " Es primo");

    }else{
      System.out.println(evaluado);
    }
    primerValor ++;
    

  }
  
  }
      
    
}







  


