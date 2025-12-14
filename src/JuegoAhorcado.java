
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class JuegoAhorcado {

    private static final Scanner scanner = new Scanner(System.in);
    private static String jugador1;
    private static String jugador2;
    private static int victoriasJ1 = 0;
    private static int victoriasJ2 = 0;
    private static int partida = 1;

    public static void main(String[] args) {

        System.out.println("========================================");
        System.out.println("   BIENVENIDOS AL AHORCADO (MEJOR DE 5)");
        System.out.println("========================================");

        System.out.println("Introduce el nombre del Jugador 1: ");
        jugador1 = scanner.nextLine();
        System.out.println("Introduce el nombre del Jugador 2: ");
        jugador2 = scanner.nextLine();

        while (victoriasJ1 < 3 && victoriasJ2 < 3) {
            System.out.println("\n--- PARTIDA " + partida + " ---");
            System.out.println("Marcador Global:" + jugador1 + "[" + victoriasJ1 + "] - " + jugador2 +"[" + victoriasJ2 + "]");

            String ponedor = (partida % 2 != 0) ? jugador1 : jugador2;
            String adivinador = (partida % 2 == 0) ? jugador1 : jugador2;

            System.out.println("TURNO: " + ponedor + " escribe la palabra secreta.");
            System.out.println(adivinador + ", NO MIRES LA PANTALLA.");

            String palabraSecreta = "";
            while (true) {
                System.out.print("Escribe la palabra a adivinar (sin espacios): ");
                palabraSecreta = scanner.nextLine().toUpperCase().trim();
                if (!palabraSecreta.isEmpty() && !palabraSecreta.contains(" ")) {
                    break;
                }
                System.out.println("Palabra invalida. Asegurate de que no tenga espacios.");
            }
            System.out.print("\033[H\033[2J");
            System.out.flush();
            
            boolean ganoAdivinador = jugarRonda(palabraSecreta, adivinador);

            if (ganoAdivinador) {
                System.out.println(adivinador + " gana esta ronda!");
                if (adivinador.equals(jugador1)) {
                    victoriasJ1++;
                } else {
                    victoriasJ2++;
                }
            } else {
                System.out.println(adivinador + " ha sido ahorcado! Punto para " + ponedor + ".");
                if (ponedor.equals(jugador1)) {
                    victoriasJ1++;
                } else {
                    victoriasJ2++;
                }
            }

            partida++;
        }
        System.out.println("\n========================================");
        System.out.println("             FIN DEL JUEGO");
        System.out.println("========================================");
        if (victoriasJ1 > victoriasJ2) {
            System.out.println("¡FELICIDADES JUGADOR 1! ERES EL CAMPEON.");
        } else {
            System.out.println("¡FELICIDADES JUGADOR 2! ERES EL CAMPEON.");
        }
    }
    
    public static boolean jugarRonda(String palabra, String nombreJugador) {
        int vidas = 6;
        char[] progreso = new char[palabra.length()];
        List<Character> letrasUsadas = new ArrayList<>();
        boolean palabraAdivinada = false;
        
        for (int i = 0; i < palabra.length(); i++) {
            progreso[i] = '_';
        }

        while (vidas > 0 && !palabraAdivinada) {
            imprimirAhorcado(vidas);
            System.out.println("Palabra: " + String.valueOf(progreso));
            System.out.println("Intentos restantes: " + vidas);
            System.out.println("Letras usadas: " + letrasUsadas);
            System.out.print(nombreJugador + ", introduce una letra o numero: ");

            String input = scanner.nextLine().toUpperCase();

            
            if (input.length() != 1) {
                System.out.println("Escribe solo un caracter.");
                continue;
            }

            char letra = input.charAt(0);

            if (letrasUsadas.contains(letra)) {
                System.out.println("Ya has usado esa letra. Intenta otra.");
                continue;
            }

            letrasUsadas.add(letra);

            boolean acierto = false;
            for (int i = 0; i < palabra.length(); i++) {
                if (palabra.charAt(i) == letra) {
                    progreso[i] = letra;
                    acierto = true;
                }
            }

            if (acierto) {
                System.out.println("¡Correcto!");
                if (String.valueOf(progreso).equals(palabra)) {
                    palabraAdivinada = true;
                }
            } else {
                System.out.println("Incorrecto. Pierdes una vida.");
                vidas--;
            }
        }

        if (!palabraAdivinada) {
            imprimirAhorcado(0);
            System.out.println("La palabra era: " + palabra);
        }

        return palabraAdivinada;
    }
    
    
    public static  void imprimirAhorcado(int vidas) {
        System.out.println("\n");
        switch (vidas) {
            case 6:
                System.out.println("  +---+");
                System.out.println("  |   |");
                System.out.println("      |");
                System.out.println("      |");
                System.out.println("      |");
                System.out.println("      |");
                System.out.println("=========");
                break;
            case 5:
                System.out.println("  +---+");
                System.out.println("  |   |");
                System.out.println("  O   |");
                System.out.println("      |");
                System.out.println("      |");
                System.out.println("      |");
                System.out.println("=========");
                break;
            case 4:
                System.out.println("  +---+");
                System.out.println("  |   |");
                System.out.println("  O   |");
                System.out.println("  |   |");
                System.out.println("      |");
                System.out.println("      |");
                System.out.println("=========");
                break;
            case 3:
                System.out.println("  +---+");
                System.out.println("  |   |");
                System.out.println("  O   |");
                System.out.println(" /|   |");
                System.out.println("      |");
                System.out.println("      |");
                System.out.println("=========");
                break;
            case 2:
                System.out.println("  +---+");
                System.out.println("  |   |");
                System.out.println("  O   |");
                System.out.println(" /|\\  |");
                System.out.println("      |");
                System.out.println("      |");
                System.out.println("=========");
                break;
            case 1:
                System.out.println("  +---+");
                System.out.println("  |   |");
                System.out.println("  O   |");
                System.out.println(" /|\\  |");
                System.out.println(" /    |");
                System.out.println("      |");
                System.out.println("=========");
                break;
            case 0:
                System.out.println("  +---+");
                System.out.println("  |   |");
                System.out.println("  O   |");
                System.out.println(" /|\\  |");
                System.out.println(" / \\  |");
                System.out.println("      |");
                System.out.println("=========");
                System.out.println("GAME OVER");
                break;
        }
        System.out.println();
    }
}
