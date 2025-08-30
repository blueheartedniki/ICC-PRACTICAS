import java.util.Scanner;
import java.util.Random;

public class Tijera {
    public static void main(String[] args) {
        int piedra = 0;
        int papel = 1;
        int tijera = 2;
        int Vhumano = 0;
        int Vcpu = 0;
        int tirohumano = 0;
       
        Scanner lector = new Scanner(System.in);
        Random dadocpu = new Random();
        //Bienvenida y explicación al usuario de como funciona el programa

        System.out.println("¡Hola, bienvenido al juego de piedra papel o tijeras!");
        System.out.println("Serás el ganador si ganas 2 de 3 rondas!");
        System.out.println("Por favor escribe si quieres jugar con piedra, papel o tijera.");
        
      //Bucle que se repetirá hasta que la compu o el usuario gane 2 rondas
        while(Vhumano < 2 && Vcpu < 2) {
            String escritohumano = lector.nextLine();
            System.out.println("Vas a jugar con: " + escritohumano);
          
            //se asignan igualdades al string del usuario para trabajar con ints
            if(escritohumano.equalsIgnoreCase("piedra")) tirohumano = piedra;
            else if(escritohumano.equalsIgnoreCase("papel")) tirohumano = papel;
            else if(escritohumano.equalsIgnoreCase("tijera")) tirohumano = tijera;
            else {
                System.out.println("Elige una opción valida");
                continue;
            }
            
            

            // Se le asigna una jugada a la computadora dependiendo del numero que salió al usar random
            int cpuChoice = dadocpu.nextInt(3);
            String compuElige ="";
            if(cpuChoice == piedra) compuElige = "piedra";
            else if(cpuChoice == papel) compuElige = "papel";
            else if(cpuChoice == tijera) compuElige = "tijera";
            // Se le informa al usuario la jugada de la compu
            System.out.println("La compu ha elegido: " + compuElige);

            // Decidir quién gana la ronda según las reglas de piedra papel o tijeras
            if(cpuChoice == tirohumano) {
                System.out.println("¡Empate!");
            } else if(
                (tirohumano == piedra && cpuChoice == tijera) ||
                (tirohumano == papel && cpuChoice == piedra) ||
                (tirohumano == tijera && cpuChoice == papel)
            ) {
                System.out.println("¡Tú ganas la ronda! :D");
                Vhumano++;
            } else {
                System.out.println("La compu gana la ronda! D:");
                Vcpu++;
            }

            // Informa al usuario de su puntuación y la de la compu y se le solicita volver a escribir su siguiente jugada si es el caso
            System.out.println("Tu puntuación es: " + Vhumano);
            System.out.println("La puntuación de la compu es: " + Vcpu);
            if(tirohumano < 2) {
                System.out.println("Escribe con qué jugarás la siguiente ronda!");
            }
        }

        // Se declara al ganador definitivo
        if(Vhumano == 2) {
            System.out.println("¡Felicidades, ganaste el juego!");
        } else {
            System.out.println("¡Has perdido el juego!");
        }

        
    }
}
