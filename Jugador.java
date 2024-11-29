import java.awt.*;

/**
 * La clase Jugador representa el propio jugador que hay dentro del juego del laberinto
 * El jugador se moverá por el laberinto hasta llegar a la salida
 */

public class Jugador {
    private int x;
    private int y;
    private final Laberinto laberinto;
    private Color color;

    /**
     * Crea al jugador en el laberinto
     * @param startX  representa la posición inicial en el eje X
     * @param startY  es la posición inicial en el eje Y
     * @param laberinto el laberinto exacto en el que se encuentra el jugador
     * @param color    el color del jugador que se elige
     */

    public Jugador(int startX, int startY, Laberinto laberinto, Color color) {
        this.x = startX;
        this.y = startY;
        this.laberinto = laberinto;
        this.color = color;
    }

    /**
     * Dibuja al jugador en el laberinto
     * @param g el contexto gráfico en el que se dibuja el jugador
     */

    public void dibujar(Graphics g) {
        int tileSize = laberinto.getTileSize();
        g.setColor(color);
        g.fillOval(x * tileSize + 5, y * tileSize + 5, tileSize - 10, tileSize - 10);
    }

    /**
     Para mover al jugador en el laberinto
     * @param dx es el desplazamiento en el eje X
     * @param dy como se mueve por el eje Y
     */

    public void mover(int dx, int dy) {
        int nuevoX = x + dx;
        int nuevoY = y + dy;

        if (laberinto.esTransitable(nuevoX, nuevoY)) {
            x = nuevoX;
            y = nuevoY;
        }
    }

    /**
     * Verifica si el jugador ha llegado a la salida
     * @return true si el jugador está en la posición de salida y false en caso contrario
     */

    public boolean haLlegadoALaSalida() {
        return x == Laberinto.COLUMNAS - 2 && y == Laberinto.FILAS - 1;
    }

    /**
     * Obtiene la posición actual del jugador en el eje X
     * @return retorna la posición X del jugador
     */

    public int getX() {
        return x;
    }

    /**
     * Obtiene la posición actual en el eje Y
     * @return retorna la posición Y del jugador
     */

    public int getY() {
        return y;
    }

    /**
     * Establece el color del jugador
     * @param color indica el nuevo color del jugador.
     */

    public void setColor(Color color) {
        this.color = color;
    }
}
