import java.awt.*;

/**
 * La clase Objeto representa el objeto del laberinto que puede ser recogido por el jugador
 */

public class Objeto {
    private final int x;
    private final int y;
    private boolean recogido;

    /**
     * Crea un nuevo objeto en una posición específica del laberinto
     * @param x es la posición en el eje X del objeto.
     * @param y representala posición en el eje Y del objeto.
     */

    public Objeto(int x, int y) {
        this.x = x;
        this.y = y;
        this.recogido = false;
    }

    /**
     * Dibuja el objeto en el laberinto
     * @param g es el gráfico que dibuja el objeto
     * @param tileSize el tamaño de cada celda
     */

    public void dibujar(Graphics g, int tileSize) {
        if (!recogido) {
            g.setColor(Color.BLUE);
            g.fillRect(x * tileSize + 10, y * tileSize + 10, tileSize - 20, tileSize - 20);
        }
    }

    /**
     * Comprueba si el objeto está en la misma posición que el jugador
     * @param jugadorX la posición en el eje X del jugador.
     * @param jugadorY la posición en el eje Y del jugador.
     * @return True si el objeto está en la misma posición que el jugador, en caso contrario return False
     */

    public boolean estaEn(int jugadorX, int jugadorY) {
        return x == jugadorX && y == jugadorY;
    }

    /**
     * Marca el objeto como recogido
     */

    public void recoger() {
        this.recogido = true;
    }

    /**
     * Indica si el objeto ya ha sido recogido.
     * @return True si el objeto ha sido recogido, y false si no ha sido recogido en caso contrario
     */

    public boolean estaRecogido() {
        return recogido;
    }

    /**
     * Indica la posición X del objeto
     * @return X
     */

    public int getX() {
        return x;
    }

    /**
     * Indica la posición Y del objeto
     * @return y
     */

    public int getY() {
        return y;
    }
}
