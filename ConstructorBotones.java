    package Presentacion;
    
    import javax.swing.*;
    import java.awt.*;
    
    /**
     * Clase utilitaria para construir botones con estilos consistentes
     * en la interfaz gráfica del juego.
     * Autores: Santiago Andres Gomez Rojas y Miguel Angel Sandoval
     */
    public class ConstructorBotones {
    
        /**
         * Crea un botón genérico de opción con texto, color y acción asociados.
         *
         * @param texto  texto que se mostrará en el botón.
         * @param color  color de fondo del botón.
         * @param accion acción a ejecutar cuando el botón sea presionado.
         * @return instancia de {@link JButton} configurada.
         */
        public static JButton crearBotonOpcion(String texto, Color color, Runnable accion) {
            JButton btn = new JButton(texto);
            btn.setFont(new Font("Monospaced", Font.BOLD, 13));
            btn.setBackground(color);
            btn.setForeground(Color.WHITE);
            btn.setFocusPainted(false);
            btn.setBorder(BorderFactory.createLineBorder(new Color(184, 134, 11), 3));
            btn.addActionListener(e -> accion.run());
            return btn;
        }
    
        /**
         * Crea un botón para seleccionar modalidad de juego.
         *
         * @param texto  texto del botón.
         * @param color  color de fondo del botón.
         * @param accion acción a ejecutar al presionar el botón.
         * @return botón de modalidad configurado.
         */
        public static JButton crearBotonModalidad(String texto, Color color, Runnable accion) {
            JButton btn = crearBotonOpcion(texto, color, accion);
            btn.setPreferredSize(new Dimension(210, 70));
            return btn;
        }
    
        /**
         * Crea un botón para seleccionar sabor del helado.
         *
         * @param texto  texto del botón.
         * @param color  color de fondo del botón.
         * @param accion acción a ejecutar al presionar el botón.
         * @return botón de sabor configurado.
         */
        public static JButton crearBotonSabor(String texto, Color color, Runnable accion) {
            JButton btn = crearBotonOpcion(texto, color, accion);
            btn.setPreferredSize(new Dimension(190, 100));
            return btn;
        }
    
        /**
         * Crea un botón para seleccionar nivel de dificultad.
         *
         * @param texto  texto del botón.
         * @param color  color de fondo del botón.
         * @param accion acción a ejecutar al presionar el botón.
         * @return botón de nivel configurado.
         */
        public static JButton crearBotonNivel(String texto, Color color, Runnable accion) {
            JButton btn = crearBotonOpcion(texto, color, accion);
            btn.setPreferredSize(new Dimension(190, 100));
            return btn;
        }
    
        /**
         * Crea un botón estándar para regresar a la pantalla anterior.
         *
         * @param accion acción a ejecutar al presionar el botón.
         * @return botón configurado para volver atrás.
         */
        public static JButton crearBotonAtras(Runnable accion) {
            JButton btnAtras = new JButton("ATRÁS");
            btnAtras.setFont(new Font("Monospaced", Font.BOLD, 18));
            btnAtras.setBackground(new Color(220, 200, 160));
            btnAtras.setForeground(new Color(100, 80, 50));
            btnAtras.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnAtras.setMaximumSize(new Dimension(200, 50));
            btnAtras.setFocusPainted(false);
            btnAtras.addActionListener(e -> accion.run());
            return btnAtras;
        }
    }
