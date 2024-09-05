import java.util.Scanner;

public class Main {

    public static void main(String [] ars){

        Scanner scanner = new Scanner(System.in);
        BatallaNaval bn = new BatallaNaval();

        char[][] tableroBarcosJugador1 = bn.crearTablero();
        char[][] tableroAtaquesJugador1 = bn.crearTablero();


        char[][] tableroBarcosJugador2 = bn.crearTablero();
        char[][] tableroAtaquesJugador2 = bn.crearTablero();


        System.out.println("Jugador 1: Posiciona tus barcos");
        for (int i = 0; i < bn.NOMBRES_BARCOS.length; i++) {
            bn.imprimirTablero(tableroBarcosJugador1);
            bn.posicionarBarco(tableroBarcosJugador1, bn.NOMBRES_BARCOS[i], bn.TAMANOS_BARCOS[i]);
        }


        System.out.println("\nJugador 2: Posiciona tus barcos");
        for (int i = 0; i < bn.NOMBRES_BARCOS.length; i++) {
            bn.imprimirTablero(tableroBarcosJugador2);
            bn.posicionarBarco(tableroBarcosJugador2, bn.NOMBRES_BARCOS[i], bn.TAMANOS_BARCOS[i]);
        }


        boolean juegoTerminado = false;
        while (!juegoTerminado) {

            System.out.println("\nTurno del Jugador 1");
            bn.imprimirTableroAtaques(tableroAtaquesJugador1, tableroBarcosJugador2);
            System.out.print("Ingresa fila para atacar (A-J): ");
            char filaAtaque1 = scanner.next().toUpperCase().charAt(0);
            System.out.print("Ingresa columna para atacar (0-9): ");
            int colAtaque1 = scanner.nextInt();
            bn.realizarAtaque(tableroAtaquesJugador1, tableroBarcosJugador2, filaAtaque1 - 'A', colAtaque1);

            if (!bn.quedanBarcos(tableroBarcosJugador2)) {
                System.out.println("¡El Jugador 1 ha ganado!");
                juegoTerminado = true;
                break;
            }


            System.out.println("\nTurno del Jugador 2");
            bn.imprimirTableroAtaques(tableroAtaquesJugador2, tableroBarcosJugador1);
            System.out.print("Ingresa fila para atacar (A-J): ");
            char filaAtaque2 = scanner.next().toUpperCase().charAt(0);
            System.out.print("Ingresa columna para atacar (0-9): ");
            int colAtaque2 = scanner.nextInt();
            bn.realizarAtaque(tableroAtaquesJugador2, tableroBarcosJugador1, filaAtaque2 - 'A', colAtaque2);

            if (!bn.quedanBarcos(tableroBarcosJugador1)) {
                System.out.println("¡El Jugador 2 ha ganado!");
                juegoTerminado = true;
                break;
            }

        }
    }
}