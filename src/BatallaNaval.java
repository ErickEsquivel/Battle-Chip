import java.util.Scanner;

public class BatallaNaval {

    static final int TAMANO = 10;

    static final String[] NOMBRES_BARCOS = {"Portaaviones", "Acorazado", "Submarino"};
    static final int[] TAMANOS_BARCOS = {5, 4, 3};

    public static char[][] crearTablero() {
        char[][] tablero = new char[TAMANO][TAMANO];
        for (int i = 0; i < TAMANO; i++) {
            for (int j = 0; j < TAMANO; j++) {
                tablero[i][j] = '-';
            }
        }
        return tablero;
    }


    public static void imprimirTablero(char[][] tablero) {
        System.out.print("  ");
        for (int i = 0; i < TAMANO; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < TAMANO; i++) {
            char letraFila = (char) ('A' + i);
            System.out.print(letraFila + " ");
            for (int j = 0; j < TAMANO; j++) {
                System.out.print(tablero[i][j] + " ");
            }
            System.out.println();
        }
    }





    public static void posicionarBarco(char[][] tablero, String nombre, int tamano) {
        Scanner scanner = new Scanner(System.in);
        boolean colocado = false;

        while (!colocado) {
            System.out.println("Posicionando " + nombre + " (Tamaño: " + tamano + ")");
            System.out.print("Ingrese fila inicial (A-J): ");
            char filaInicial = scanner.next().toUpperCase().charAt(0);
            System.out.print("Ingrese columna inicial (0-9): ");
            int colInicial = scanner.nextInt();
            System.out.print("Ingrese orientación (H para horizontal, V para vertical): ");
            char orientacion = scanner.next().toUpperCase().charAt(0);

            int filaIndex = filaInicial - 'A';
            boolean valido = true;

            if (orientacion == 'H') {
                if (colInicial + tamano > TAMANO) {
                    valido = false;
                } else {
                    for (int i = 0; i < tamano; i++) {
                        if (tablero[filaIndex][colInicial + i] != '-') {
                            valido = false;
                            break;
                        }
                    }
                }
            } else if (orientacion == 'V') {
                if (filaIndex + tamano > TAMANO) {
                    valido = false;
                } else {
                    for (int i = 0; i < tamano; i++) {
                        if (tablero[filaIndex + i][colInicial] != '-') {
                            valido = false;
                            break;
                        }
                    }
                }
            }

            if (valido) {
                for (int i = 0; i < tamano; i++) {
                    if (orientacion == 'H') {
                        tablero[filaIndex][colInicial + i] = 'B';
                    } else if (orientacion == 'V') {
                        tablero[filaIndex + i][colInicial] = 'B';
                    }
                }
                colocado = true;
                System.out.println("Barco colocado con éxito!");
            } else {
                System.out.println("Posición inválida. Inténtelo de nuevo.");
            }
        }
    }

    public static void imprimirTableroAtaques(char[][] tableroAtaques, char[][] tableroBarcos) {
        System.out.print("  ");
        for (int i = 0; i < TAMANO; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < TAMANO; i++) {
            char letraFila = (char) ('A' + i);
            System.out.print(letraFila + " ");
            for (int j = 0; j < TAMANO; j++) {
                if (tableroAtaques[i][j] == 'X') {
                    System.out.print("X ");
                } else if (tableroAtaques[i][j] == 'O') {
                    System.out.print("O ");
                } else {
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
    }

    public static boolean realizarAtaque(char[][] tableroAtaques, char[][] tableroOponente, int fila, int columna) {
        if (tableroOponente[fila][columna] == 'B') {
            tableroAtaques[fila][columna] = 'X';
            tableroOponente[fila][columna] = 'X';
            System.out.println("¡Impacto!");
            return true;
        } else if (tableroAtaques[fila][columna] == '-') {
            tableroAtaques[fila][columna] = 'O';
            System.out.println("Agua.");
            return false;
        } else {
            System.out.println("Ya has atacado esa posición.");
            return false;
        }
    }


    public static boolean quedanBarcos(char[][] tablero) {
        for (int i = 0; i < TAMANO; i++) {
            for (int j = 0; j < TAMANO; j++) {
                if (tablero[i][j] == 'B') {
                    return true;
                }
            }
        }
        return false;
    }
}


