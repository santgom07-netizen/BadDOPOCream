package Presentacion;

import javax.swing.*;
import java.awt.*;

/**
 * Pantalla para la selección del sabor del helado
 * (vainilla, fresa, chocolate).
 * Autores: Santiago Andres Gomez Rojas y Miguel Angel Sandoval
 */
public class PantallaSabor extends JPanel {

    /**
     * Ventana principal que contiene esta pantalla.
     */
    private VentanaJuego ventana;

    /**
     * Imagen de fondo asociada a esta pantalla.
     */
    private Image imagenFondo;

    /**
     * Imagen utilizada para el patrón de fondo de la zona central.
     */
    private Image fondoHelados;

    /**
     * Crea la pantalla de selección de sabor.
     *
     * @param ventana ventana principal que contiene esta pantalla.
     */
    public PantallaSabor(VentanaJuego ventana) {
        this.ventana = ventana;

        setPreferredSize(new Dimension(900, 700));
        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(200, 220, 255));
        setBorder(BorderFactory.createLineBorder(new Color(184, 134, 11), 8));

        cargarImagenes();
        crearInterfaz();
    }

    /**
     * Carga las imágenes de fondo desde los recursos.
     */
    private void cargarImagenes() {
        try {
            imagenFondo = new ImageIcon("recursos/imagenes/EleccionSaborHelados.png").getImage();
            fondoHelados = new ImageIcon("recursos/imagenes/fondohelados.png").getImage();
        } catch (Exception e) {
        }
    }

    /**
     * Crea la interfaz gráfica de la pantalla,
     * con la imagen superior, botones para cada sabor
     * y el botón para regresar a la selección de modalidad.
     */
    private void crearInterfaz() {
        add(crearPanelImagen(), BorderLayout.NORTH);

        JPanel panelCentro = crearPanelCentro();
        panelCentro.add(Box.createVerticalStrut(20));

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10));
        panelBotones.setOpaque(false);

        panelBotones.add(ConstructorBotones.crearBotonSabor(
                "VAINILLA",
                new Color(240, 220, 130),
                () -> seleccionarSabor("vainilla")
        ));

        panelBotones.add(ConstructorBotones.crearBotonSabor(
                "FRESA",
                new Color(238, 100, 120),
                () -> seleccionarSabor("fresa")
        ));

        panelBotones.add(ConstructorBotones.crearBotonSabor(
                "CHOCOLATE",
                new Color(163, 109, 43),
                () -> seleccionarSabor("chocolate")
        ));

        panelCentro.add(panelBotones);
        panelCentro.add(Box.createVerticalStrut(20));

        panelCentro.add(ConstructorBotones.crearBotonAtras(() -> ventana.mostrarPantallaModalidad()));
        panelCentro.add(Box.createVerticalGlue());

        add(panelCentro, BorderLayout.CENTER);
    }

    /**
     * Guarda el sabor elegido en la ventana principal
     * y avanza a la selección de nivel.
     *
     * @param sabor sabor del helado seleccionado.
     */
    private void seleccionarSabor(String sabor) {
        ventana.setSaborSeleccionado(sabor);
        ventana.mostrarPantallaNivel();
    }

    /**
     * Crea un panel destinado a mostrar la imagen de cabecera,
     * centrada y escalada manteniendo la proporción.
     *
     * @return panel configurado para dibujar la imagen superior.
     */
    private JPanel crearPanelImagen() {
        JPanel panelImagen = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (imagenFondo != null) {
                    int anchoImagen = imagenFondo.getWidth(this);
                    int altoImagen = imagenFondo.getHeight(this);

                    double escala = Math.min(
                        (double) getWidth() / anchoImagen,
                        (double) (getHeight() - 20) / altoImagen
                    );

                    int nuevoAncho = (int) (anchoImagen * escala);
                    int nuevoAlto = (int) (altoImagen * escala);
                    int x = (getWidth() - nuevoAncho) / 2;
                    int y = (getHeight() - nuevoAlto) / 2;

                    g.drawImage(imagenFondo, x, y, nuevoAncho, nuevoAlto, this);
                }
            }
        };
        panelImagen.setPreferredSize(new Dimension(900, 350));
        panelImagen.setBackground(new Color(240, 240, 240));
        panelImagen.setBorder(BorderFactory.createLineBorder(new Color(218, 165, 32), 3));
        return panelImagen;
    }

    /**
     * Crea el panel central de la pantalla, utilizando la imagen de helados
     * como patrón repetido de fondo y una disposición vertical para los componentes.
     *
     * @return panel central configurado.
     */
    private JPanel crearPanelCentro() {
        JPanel panelCentro = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (fondoHelados != null) {
                    int anchoHelado = fondoHelados.getWidth(this);
                    int altoHelado = fondoHelados.getHeight(this);

                    for (int y = 0; y < getHeight(); y += altoHelado) {
                        for (int x = 0; x < getWidth(); x += anchoHelado) {
                            g.drawImage(fondoHelados, x, y, anchoHelado, altoHelado, this);
                        }
                    }
                }
            }
        };
        panelCentro.setLayout(new BoxLayout(panelCentro, BoxLayout.Y_AXIS));
        panelCentro.setBackground(new Color(200, 220, 255));
        return panelCentro;
    }
}
