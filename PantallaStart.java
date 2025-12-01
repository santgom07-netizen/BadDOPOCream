package Presentacion;

import javax.swing.*;
import java.awt.*;

/**
 * Pantalla de menú principal que usa la imagen EleccionStart
 * y define zonas clicables transparentes sobre las opciones.
 * Autores: Santiago Andres Gomez Rojas y Miguel Angel Sandoval
 */
public class PantallaStart extends JPanel {

    private VentanaJuego ventana;
    private Image imagenFondo;
    private final boolean debugZonas = false;

    /**
     * Crea la pantalla de menú principal,
     * cargando la imagen de fondo y configurando los botones transparentes.
     *
     * @param ventana ventana principal del juego.
     */
    public PantallaStart(VentanaJuego ventana) {
        this.ventana = ventana;

        setPreferredSize(new Dimension(800, 600));
        setLayout(null);
        setBackground(Color.BLACK);

        cargarImagen();
        crearBotonesTransparentes();
    }

    /**
     * Carga la imagen EleccionStart desde los recursos.
     */
    private void cargarImagen() {
        try {
            imagenFondo = new ImageIcon("recursos/imagenes/EleccionStart.png").getImage();
        } catch (Exception e) {
        }
    }

    /**
     * Crea los botones transparentes alineados con las opciones
     * PLAY, SCORES, HELP y CREDITS visibles en la imagen.
     */
    private void crearBotonesTransparentes() {
        int anchoPanel = 800;
        int anchoBoton = 260;
        int altoBoton = 36;

        int x = (anchoPanel - anchoBoton) / 2;

        int yBase = 297;
        int separacion = 50;

        JButton btnPlay = crearBotonTransparente(() -> ventana.mostrarPantallaModalidad());
        btnPlay.setBounds(x, yBase, anchoBoton, altoBoton);

        JButton btnScores = crearBotonTransparente(() -> mostrarMensajeNoImplementado("Puntajes"));
        btnScores.setBounds(x, yBase + separacion, anchoBoton, altoBoton);

        JButton btnHelp = crearBotonTransparente(() -> mostrarMensajeNoImplementado("Ayuda"));
        btnHelp.setBounds(x, yBase + 2 * separacion, anchoBoton, altoBoton);

        JButton btnCredits = crearBotonTransparente(() -> System.exit(0));
        btnCredits.setBounds(x, yBase + 3 * separacion, anchoBoton, altoBoton);

        add(btnPlay);
        add(btnScores);
        add(btnHelp);
        add(btnCredits);
    }

    /**
     * Crea un botón totalmente transparente que actúa como zona clicable
     * sobre la imagen de fondo.
     *
     * @param accion acción a ejecutar al presionar el botón.
     * @return botón transparente configurado.
     */
    private JButton crearBotonTransparente(Runnable accion) {
        JButton boton = new JButton();
        boton.setOpaque(false);
        boton.setContentAreaFilled(false);
        boton.setBorderPainted(false);
        boton.setFocusPainted(false);
        boton.addActionListener(e -> accion.run());
        return boton;
    }

    /**
     * Muestra un mensaje informando que una opción del menú
     * aún no se encuentra implementada.
     *
     * @param nombreOpcion nombre de la opción seleccionada.
     */
    private void mostrarMensajeNoImplementado(String nombreOpcion) {
        JOptionPane.showMessageDialog(
                this,
                "La opción \"" + nombreOpcion + "\" aún no está implementada.",
                "Información",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    /**
     * Dibuja la imagen de fondo y, opcionalmente, las zonas clicables
     * utilizadas para depuración.
     *
     * @param g contexto gráfico utilizado para pintar la pantalla.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagenFondo != null) {
            g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
        }

        if (debugZonas) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(new Color(0, 0, 255, 60));
            for (Component c : getComponents()) {
                if (c instanceof JButton) {
                    Rectangle r = c.getBounds();
                    g2d.fillRect(r.x, r.y, r.width, r.height);
                }
            }
        }
    }
}
