package Presentacion;

import javax.swing.*;
import java.awt.*;

/**
 * Pantalla para la selección de modalidad de juego
 * (Jugador vs Jugador, Jugador vs Máquina, Máquina vs Máquina).
 * Autores: Santiago Andres Gomez Rojas y Miguel Angel Sandoval
 */
public class PantallaModalidad extends JPanel {

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
     * Crea la pantalla de selección de modalidad.
     *
     * @param ventana ventana principal que contiene esta pantalla.
     */
    public PantallaModalidad(VentanaJuego ventana) {
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
            imagenFondo = new ImageIcon("recursos/imagenes/EleccionModalidad.png").getImage();
            fondoHelados = new ImageIcon("recursos/imagenes/fondohelados.png").getImage();
        } catch (Exception e) {
        }
    }

    /**
     * Crea la interfaz gráfica de la pantalla,
     * incluyendo la imagen superior, los botones de modalidad
     * y el botón para regresar atrás.
     */
    private void crearInterfaz() {
        add(crearPanelImagen(), BorderLayout.NORTH);

        JPanel panelCentro = crearPanelCentro();
        panelCentro.add(Box.createVerticalStrut(20));

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panelBotones.setOpaque(false);

        panelBotones.add(ConstructorBotones.crearBotonModalidad(
            "Jugador vs Jugador",
            new Color(80, 160, 240),
            () -> seleccionarModalidad("PvsP")
        ));

        panelBotones.add(ConstructorBotones.crearBotonModalidad(
            "Jugador vs Máquina",
            new Color(50, 130, 220),
            () -> seleccionarModalidad("PvsM")
        ));

        panelBotones.add(ConstructorBotones.crearBotonModalidad(
            "Máquina vs Máquina",
            new Color(30, 100, 200),
            () -> seleccionarModalidad("MvsM")
        ));

        panelCentro.add(panelBotones);
        panelCentro.add(Box.createVerticalStrut(20));
        panelCentro.add(ConstructorBotones.crearBotonAtras(() -> ventana.mostrarPantallaStart()));
        panelCentro.add(Box.createVerticalGlue());

        add(panelCentro, BorderLayout.CENTER);
    }

    /**
     * Registra la modalidad seleccionada y avanza a la pantalla de selección de sabor.
     *
     * @param modalidad modalidad seleccionada en formato de texto.
     */
    private void seleccionarModalidad(String modalidad) {
        ventana.mostrarPantallaSabor();
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
