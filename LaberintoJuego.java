import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

    /**
     * La clase LaberintoJuego representa el juego del laberinto, en el que un jugador debe recoger objetos y llegar a la salida, donde se convertirá en un pato
     */

public class LaberintoJuego extends JFrame {
    private final Laberinto laberinto;
    private final Jugador jugador;
    private final List<Objeto> objetos;
    private final RegistroObjetos registroObjetos;
    private boolean todosObjetosRecogidos;
    private int puntos;

    /**
     * Crea una nueva instancia del juego de laberinto
     * @param colorJugador el color del jugador que se ha elegido en la VentanaPrincipal en el laberinto.
     */

    public LaberintoJuego(Color colorJugador) {
        setTitle("LABERINTO 20x20");
        setSize(610, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        laberinto = new Laberinto();
        jugador = new Jugador(1, 1, laberinto, colorJugador);
        objetos = generarObjetos(3);
        registroObjetos = new RegistroObjetos();
        todosObjetosRecogidos = false;
        puntos = 0;

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                laberinto.dibujar(g);
                jugador.dibujar(g);

                for (Objeto objeto : objetos) {
                    objeto.dibujar(g, laberinto.getTileSize());
                }

                g.setColor(Color.WHITE);
                g.setFont(new Font("Arial", Font.BOLD, 16));
                g.drawString("Puntos: " + puntos, 10, 20);

                if (todosObjetosRecogidos && jugador.haLlegadoALaSalida()) {
                    g.setColor(Color.GREEN);
                    g.setFont(new Font("Arial", Font.BOLD, 30));
                    g.drawString("YOU WIN!", 200, 200);
                }
            }
        };

        add(panel);

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                if (!todosObjetosRecogidos || !jugador.haLlegadoALaSalida()) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_UP -> jugador.mover(0, -1);
                        case KeyEvent.VK_DOWN -> jugador.mover(0, 1);
                        case KeyEvent.VK_LEFT -> jugador.mover(-1, 0);
                        case KeyEvent.VK_RIGHT -> jugador.mover(1, 0);
                    }
                    verificarRecoleccion();
                    panel.repaint();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        });

        setFocusable(true);
        setVisible(true);
    }

    /**
     * Genera una lista de objetos en posiciones aleatorias siempre dentro del camino blanco del laberinto
     * @param cantidad la cantidad de objetos que se generan
     * @return una lista de objetos generados
     */

    private List<Objeto> generarObjetos(int cantidad) {
        List<Objeto> objetos = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < cantidad; i++) {
            int x, y;
            do {
                x = random.nextInt(Laberinto.COLUMNAS);
                y = random.nextInt(Laberinto.FILAS);
            } while (!laberinto.esTransitable(x, y) || hayObjetoEn(x, y, objetos));

            objetos.add(new Objeto(x, y));
        }

        return objetos;
    }

    /**
     * Verifica que no haya un objeto en las coordenadas dadas
     * @param x para verificar que no hay ningún objeto en esa posición X
     * @param y para verificar que no hay ningún objeto en la posición Y
     * @param objetos  la lista de objetos
     * @return returna True si hay un objeto en esa posición, en caso contrario se returna False
     */

    private boolean hayObjetoEn(int x, int y, List<Objeto> objetos) {
        for (Objeto objeto : objetos) {
            if (objeto.estaEn(x, y)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Verifica que el jugador haya recogido el objeto, y aumenta el contador
     */

    private void verificarRecoleccion() {
        for (Objeto objeto : objetos) {
            if (!objeto.estaRecogido() && objeto.estaEn(jugador.getX(), jugador.getY())) {
                objeto.recoger();
                registroObjetos.registrarObjeto(objeto);
                puntos++;
            }
        }

        todosObjetosRecogidos = objetos.stream().allMatch(Objeto::estaRecogido);

        if (todosObjetosRecogidos && jugador.haLlegadoALaSalida()) {
            JOptionPane.showMessageDialog(
                    null,
                    "¡HAS RECOGIDO TODOS LOS OBJETOS!"
            );
        }
    }
}
