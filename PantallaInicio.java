package Presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Pantalla inicial del juego que muestra una imagen de fondo
 * y un cuadro interactivo para pasar al menú principal.
 * Autores: Santiago Andres Gomez Rojas y Miguel Angel Sandoval
 */
public class PantallaInicio extends JPanel {

    /**
     * Ventana principal que contiene la pantalla.
     */
    private VentanaJuego ventana;

    /**
     * Imagen de fondo mostrada en la pantalla de inicio.
     */
    private Image imagenFondo;

    /**
     * Indica si el ratón está dentro del cuadro interactivo.
     */
    private boolean mouseEnCuadro = false;

    /**
     * Crea la pantalla de inicio y configura su aspecto visual
     * y comportamiento del ratón.
     *
     * @param ventana ventana principal a la que pertenece esta pantalla.
     */
    public PantallaInicio(VentanaJuego ventana) {
        this.ventana = ventana;

        setPreferredSize(new Dimension(800, 600));
        setBackground(new Color(200, 220, 255));

        cargarImagen();
        configurarMouseListener();
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    /**
     * Carga la imagen de fondo desde los recursos.
     */
    private void cargarImagen() {
        try {
            imagenFondo = new ImageIcon("recursos/imagenes/InicioJuego.png").getImage();
        } catch (Exception e) {
        }
    }

    /**
     * Configura los listeners de ratón para detectar clics y
     * cambios cuando el puntero entra o sale del cuadro interactivo.
     */
    private void configurarMouseListener() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ventana.mostrarPantallaStart();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                mouseEnCuadro = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                mouseEnCuadro = false;
                repaint();
            }
        });
    }

    /**
     * Dibuja la pantalla de inicio, incluyendo la imagen de fondo
     * y el cuadro principal interactivo.
     *
     * @param g contexto gráfico utilizado para pintar la pantalla.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        dibujarFondo(g2d);
        dibujarCuadroPrincipal(g2d);
    }

    /**
     * Dibuja la imagen de fondo escalada al tamaño del panel.
     *
     * @param g2d contexto gráfico 2D.
     */
    private void dibujarFondo(Graphics2D g2d) {
        if (imagenFondo != null) {
            g2d.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
        }
    }

    /**
     * Dibuja el cuadro principal que contiene el texto interactivo.
     *
     * @param g2d contexto gráfico 2D.
     */
    private void dibujarCuadroPrincipal(Graphics2D g2d) {
        int cuadroX = 150;
        int cuadroY = getHeight() - 100;
        int cuadroAncho = getWidth() - 300;
        int cuadroAlto = 60;

        dibujarBordeDorado(g2d, cuadroX, cuadroY, cuadroAncho, cuadroAlto);
        dibujarFondoAnimado(g2d, cuadroX, cuadroY, cuadroAncho, cuadroAlto);
        dibujarTexto(g2d, cuadroX, cuadroY, cuadroAncho, cuadroAlto);
    }

    /**
     * Dibuja el borde dorado del cuadro interactivo.
     *
     * @param g2d   contexto gráfico 2D.
     * @param x     coordenada X del cuadro.
     * @param y     coordenada Y del cuadro.
     * @param ancho ancho del cuadro.
     * @param alto  alto del cuadro.
     */
    private void dibujarBordeDorado(Graphics2D g2d, int x, int y, int ancho, int alto) {
        g2d.setColor(new Color(184, 134, 11));
        g2d.setStroke(new BasicStroke(mouseEnCuadro ? 6 : 4));
        g2d.drawRoundRect(x - 2, y - 2, ancho + 4, alto + 4, 15, 15);
    }

    /**
     * Dibuja el fondo animado interno del cuadro principal.
     *
     * @param g2d   contexto gráfico 2D.
     * @param x     coordenada X del cuadro.
     * @param y     coordenada Y del cuadro.
     * @param ancho ancho del cuadro.
     * @param alto  alto del cuadro.
     */
    private void dibujarFondoAnimado(Graphics2D g2d, int x, int y, int ancho, int alto) {
        g2d.setColor(mouseEnCuadro ? new Color(120, 200, 255) : new Color(100, 180, 255));
        g2d.fillRoundRect(x, y, ancho, alto, 15, 15);

        g2d.setColor(new Color(50, 150, 220));
        g2d.setStroke(new BasicStroke(3));
        g2d.drawRoundRect(x, y, ancho, alto, 15, 15);
    }

    /**
     * Dibuja el texto interactivo centrado dentro del cuadro principal.
     *
     * @param g2d   contexto gráfico 2D.
     * @param x     coordenada X del cuadro.
     * @param y     coordenada Y del cuadro.
     * @param ancho ancho del cuadro.
     * @param alto  alto del cuadro.
     */
    private void dibujarTexto(Graphics2D g2d, int x, int y, int ancho, int alto) {
        g2d.setFont(new Font("Monospaced", Font.BOLD, mouseEnCuadro ? 26 : 24));
        g2d.setColor(mouseEnCuadro ? new Color(255, 255, 100) : Color.WHITE);

        String texto = "Click to lick";
        FontMetrics fm = g2d.getFontMetrics();
        int posX = x + (ancho - fm.stringWidth(texto)) / 2;
        int posY = y + ((alto - fm.getHeight()) / 2) + fm.getAscent();

        g2d.drawString(texto, posX, posY);
    }
}
