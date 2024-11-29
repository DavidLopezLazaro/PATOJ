import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPrincipal extends JFrame {
    private Color colorJugador;

    public VentanaPrincipal() {
        setTitle("MENÚ PRINCIPAL");
        setSize(600, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        colorJugador = Color.RED;

        setLayout(new BorderLayout());

        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
        panelCentral.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel titulo = new JLabel("DUCKFINITY MAZE");
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelCentral.add(titulo);

        panelCentral.add(Box.createVerticalStrut(10));

        JPanel panelColores = new JPanel();
        panelColores.setLayout(new GridLayout(2, 4));

        Color[] colores = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW,
                Color.ORANGE, Color.PINK, Color.CYAN, Color.BLACK};
        String[] nombresColores = {"ROJO", "AZUL", "VERDE", "AMARILLO",
                "NARANJA", "ROSA", "TURQUESA", "NEGRO"};

        JButton btnIniciarJuego = new JButton("INICIAR JUEGO");
        btnIniciarJuego.setEnabled(false);

        for (int i = 0; i < colores.length; i++) {
            final Color colorSeleccionado = colores[i];
            JButton botonColor = new JButton(nombresColores[i]);
            botonColor.setBackground(colores[i]);
            botonColor.setForeground(colorSeleccionado.equals(Color.BLACK) ? Color.WHITE : Color.BLACK);
            botonColor.setFocusPainted(false);
            botonColor.setOpaque(true);
            botonColor.setBorderPainted(false);

            botonColor.addActionListener(e -> {
                colorJugador = colorSeleccionado;
                btnIniciarJuego.setEnabled(true); // Habilitar el botón al seleccionar un color
                JOptionPane.showMessageDialog(
                        null,
                        "EL COLOR SELECCIONADO ES: " + botonColor.getText(),
                        "SELECCIÓN DE COLOR",
                        JOptionPane.INFORMATION_MESSAGE
                );
            });

            panelColores.add(botonColor);
        }

        panelCentral.add(panelColores);

        btnIniciarJuego.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnIniciarJuego.addActionListener(e -> {
            new LaberintoJuego(colorJugador);
            dispose();
        });
        panelCentral.add(btnIniciarJuego);

        add(panelCentral, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        new VentanaPrincipal();
    }
}
