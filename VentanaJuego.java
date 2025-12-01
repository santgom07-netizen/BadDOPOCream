package Presentacion;

import Dominio.Juego;
import Dominio.ModalidadJuego;

import javax.swing.*;

/**
 * Ventana principal de la aplicación Bad DOPO Cream.
 * Coordina el cambio entre pantallas y mantiene la configuración
 * seleccionada por el jugador, así como la instancia del juego.
 * Autores: Santiago Andres Gomez Rojas y Miguel Angel Sandoval
 */
public class VentanaJuego extends JFrame {

    /**
     * Sabor seleccionado para el helado principal.
     */
    private String saborSeleccionado = "vainilla";

    /**
     * Modalidad seleccionada en formato de texto
     * (por ejemplo, "PvsM", "PvsP", "MvsM").
     */
    private String modalidadSeleccionada = "PvsM";

    /**
     * Número de nivel seleccionado.
     */
    private int nivelSeleccionado = 1;

    /**
     * Instancia del juego de dominio que representa la partida actual.
     */
    private Juego juego;

    /**
     * Crea la ventana principal, configura sus propiedades
     * e inicia mostrando la pantalla de inicio.
     */
    public VentanaJuego() {
        setTitle("Bad DOPO Cream");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        mostrarPantallaInicio();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Obtiene el sabor seleccionado para el helado.
     *
     * @return sabor actual seleccionado.
     */
    public String getSaborSeleccionado() {
        return saborSeleccionado;
    }

    /**
     * Establece el sabor seleccionado para el helado.
     *
     * @param saborSeleccionado nuevo sabor seleccionado.
     */
    public void setSaborSeleccionado(String saborSeleccionado) {
        this.saborSeleccionado = saborSeleccionado;
    }

    /**
     * Obtiene la modalidad de juego seleccionada.
     *
     * @return modalidad seleccionada como cadena.
     */
    public String getModalidadSeleccionada() {
        return modalidadSeleccionada;
    }

    /**
     * Establece la modalidad de juego seleccionada.
     *
     * @param modalidadSeleccionada nueva modalidad seleccionada.
     */
    public void setModalidadSeleccionada(String modalidadSeleccionada) {
        this.modalidadSeleccionada = modalidadSeleccionada;
    }

    /**
     * Obtiene el número de nivel seleccionado.
     *
     * @return número de nivel.
     */
    public int getNivelSeleccionado() {
        return nivelSeleccionado;
    }

    /**
     * Establece el número de nivel seleccionado.
     *
     * @param nivelSeleccionado nuevo número de nivel.
     */
    public void setNivelSeleccionado(int nivelSeleccionado) {
        this.nivelSeleccionado = nivelSeleccionado;
    }

    /**
     * Obtiene la instancia actual del juego de dominio.
     *
     * @return juego actual, o {@code null} si aún no se ha iniciado.
     */
    public Juego getJuego() {
        return juego;
    }

    /**
     * Convierte la modalidad seleccionada como texto en el valor
     * correspondiente del enumerado {@link ModalidadJuego}.
     *
     * @return modalidad de juego como enumeración.
     */
    private ModalidadJuego getModalidadSeleccionadaEnum() {
        if (modalidadSeleccionada == null) return ModalidadJuego.PvsM;
        String m = modalidadSeleccionada.toUpperCase();
        if (m.contains("PVSP") || m.contains("JVSJ")) return ModalidadJuego.PvsP;
        if (m.contains("PVSM") || m.contains("JVSM")) return ModalidadJuego.PvsM;
        if (m.contains("MVSM")) return ModalidadJuego.MvsM;
        return ModalidadJuego.PvsM;
    }

    /**
     * Cambia el contenido de la ventana a un nuevo panel.
     *
     * @param nueva nuevo panel que se mostrará en la ventana.
     */
    private void cambiarPantalla(JPanel nueva) {
        setContentPane(nueva);
        revalidate();
        repaint();
        pack();
        setLocationRelativeTo(null);
    }

    /**
     * Muestra la pantalla de inicio.
     */
    public void mostrarPantallaInicio() {
        cambiarPantalla(new PantallaInicio(this));
    }

    /**
     * Muestra la pantalla de menú principal (Start).
     */
    public void mostrarPantallaStart() {
        cambiarPantalla(new PantallaStart(this));
    }

    /**
     * Muestra la pantalla de selección de modalidad.
     */
    public void mostrarPantallaModalidad() {
        cambiarPantalla(new PantallaModalidad(this));
    }

    /**
     * Muestra la pantalla de selección de sabor.
     */
    public void mostrarPantallaSabor() {
        cambiarPantalla(new PantallaSabor(this));
    }

    /**
     * Muestra la pantalla de selección de nivel.
     */
    public void mostrarPantallaNivel() {
        cambiarPantalla(new PantallaNivel(this));
    }

    /**
     * Inicia el nivel 1 y crea el panel de juego correspondiente.
     */
    public void mostrarPantallaNivel1() {
        nivelSeleccionado = 1;
        iniciarJuego();
    }

    /**
     * Inicia el nivel 2 y crea el panel de juego correspondiente.
     */
    public void mostrarPantallaNivel2() {
        nivelSeleccionado = 2;
        iniciarJuego();
    }

    /**
     * Inicia el nivel 3 y crea el panel de juego correspondiente.
     */
    public void mostrarPantallaNivel3() {
        nivelSeleccionado = 3;
        iniciarJuego();
    }

    /**
     * Crea la instancia de {@link Juego} con la configuración actual
     * y muestra el panel de juego asociado al nivel seleccionado.
     */
    public void iniciarJuego() {
        juego = new Juego(saborSeleccionado, getModalidadSeleccionadaEnum());
        PanelJuego panelJuego = new PanelJuego(this, nivelSeleccionado, juego);
        cambiarPantalla(panelJuego);
    }

    /**
     * Punto de entrada de la aplicación.
     *
     * @param args argumentos de línea de comandos, no se utilizan.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(VentanaJuego::new);
    }
}
