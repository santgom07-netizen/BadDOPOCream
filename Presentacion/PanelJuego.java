package Presentacion;

import Dominio.Direccion;
import Dominio.Enemigo;
import Dominio.Fruta;
import Dominio.Helado;
import Dominio.Juego;
import Dominio.Nivel;
import Dominio.Platano;
import Dominio.Posicion;
import Dominio.Uva;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Panel principal donde se representa el mapa del nivel,
 * los helados, frutas, enemigos y demás elementos del juego.
 * También gestiona la entrada por teclado y el avance del tiempo.
 * Autores: Santiago Andres Gomez Rojas y Miguel Angel Sandoval
 */
public class PanelJuego extends JPanel {

    /**
     * Número de filas del tablero lógico.
     */
    private static final int FILAS = 12;

    /**
     * Número de columnas del tablero lógico.
     */
    private static final int COLUMNAS = 14;

    /**
     * Tamaño (ancho y alto) de cada celda en píxeles.
     */
    private static final int TAM_CELDA = 40;

    /**
     * Altura reservada para la barra superior de información.
     */
    private static final int OFFSET_Y = 60;

    /**
     * Grosor del borde del tablero, medido en celdas.
     */
    private static final int BORDE = 1;

    /**
     * Tiempo mínimo, en milisegundos, entre dos movimientos del helado.
     */
    private static final int COOLDOWN_MOVIMIENTO_MS = 230;

    /**
     * Código de carácter para representar un bloque de muro en el mapa.
     */
    private static final char BLOQUE_MURO = 'B';

    /**
     * Código de carácter para representar la base del iglú en el mapa.
     */
    private static final char BLOQUE_IGLU = 'I';

    /**
     * Código de carácter para representar un bloque generado por el jugador.
     */
    private static final char BLOQUE_JUGADOR = 'X';

    /**
     * Ventana principal que contiene este panel.
     */
    private final VentanaJuego ventana;

    /**
     * Sabor seleccionado para el helado del jugador.
     */
    private final String saborActual;

    /**
     * Instancia del modelo de juego asociada a este panel.
     */
    private final Juego juego;

    /**
     * Nivel actual que se está representando en el panel.
     */
    private final Nivel nivel;

    /**
     * Mapa lógico del nivel, donde cada celda se representa por un carácter.
     */
    private final char[][] mapa;

    /**
     * Imagen para representar bloques de hielo (muros y bloques jugador).
     */
    private Image imgBloqueHielo;

    /**
     * Imagen para representar los bordes laterales del tablero.
     */
    private Image imgBloqueLateral;

    /**
     * Imagen para representar el iglú.
     */
    private Image imgIglu;

    /**
     * Imagen para representar el plátano.
     */
    private Image imgPlatano;

    /**
     * Imagen para representar la uva.
     */
    private Image imgUva;

    /**
     * Imagen para representar al enemigo (troll).
     */
    private Image imgTroll;

    /**
     * Imagen para representar el helado de vainilla.
     */
    private Image imgHeladoVainilla;

    /**
     * Imagen para representar el helado de fresa.
     */
    private Image imgHeladoFresa;

    /**
     * Imagen para representar el helado de chocolate.
     */
    private Image imgHeladoChocolate;

    /**
     * Temporizador que controla el avance del juego en el tiempo.
     */
    private Timer timer;

    /**
     * Marca de tiempo del último movimiento del helado del jugador.
     */
    private long ultimoMovimientoHeladoMs = 0;

    /**
     * Crea un nuevo panel de juego para un nivel y configuración específicos.
     *
     * @param ventana     ventana principal que contiene el panel.
     * @param nivelNumero número del nivel que se está jugando.
     * @param juego       instancia del modelo de dominio que representa la partida.
     */
    public PanelJuego(VentanaJuego ventana, int nivelNumero, Juego juego) {
        this.ventana = ventana;
        this.saborActual = ventana.getSaborSeleccionado();
        this.juego = juego;
        this.nivel = juego.getNivel();
        this.mapa = new char[FILAS][COLUMNAS];

        int ancho = (COLUMNAS + 2 * BORDE) * TAM_CELDA;
        int alto = (FILAS + 2 * BORDE) * TAM_CELDA + OFFSET_Y;

        setPreferredSize(new Dimension(ancho, alto));
        setBackground(new Color(173, 216, 230));
        setFocusable(true);

        cargarImagenes();
        inicializarMapaNivel1();

        agregarKeyListener();
        iniciarTimer();
    }

    /**
     * Se llama cuando el componente es añadido a un contenedor.
     * Se aprovecha para solicitar el foco de teclado.
     */
    @Override
    public void addNotify() {
        super.addNotify();
        requestFocusInWindow();
    }

    /**
     * Carga las imágenes necesarias para dibujar bloques,
     * frutas, enemigos e iglú desde los recursos del proyecto.
     */
    private void cargarImagenes() {
        imgBloqueHielo = new ImageIcon("recursos/imagenesniveles/bloquehielo.png").getImage();
        imgBloqueLateral = new ImageIcon("recursos/imagenesniveles/bloquelaterales.png").getImage();
        imgIglu = new ImageIcon("recursos/imagenesniveles/iglu.png").getImage();
        imgPlatano = new ImageIcon("recursos/imagenesniveles/platano.png").getImage();
        imgUva = new ImageIcon("recursos/imagenesniveles/uva.png").getImage();
        imgTroll = new ImageIcon("recursos/imagenesniveles/troll.png").getImage();
        imgHeladoVainilla = new ImageIcon("recursos/imagenesniveles/heladovainilla.png").getImage();
        imgHeladoFresa = new ImageIcon("recursos/imagenesniveles/heladofresa.png").getImage();
        imgHeladoChocolate = new ImageIcon("recursos/imagenesniveles/heladochocolate.png").getImage();
    }

    /**
     * Inicializa la matriz del mapa para el nivel 1, colocando muros
     * en las celdas indicadas y la posición base del iglú.
     */
    private void inicializarMapaNivel1() {
        for (int f = 0; f < FILAS; f++) {
            for (int c = 0; c < COLUMNAS; c++) {
                mapa[f][c] = ' ';
            }
        }

        int[][] bloques = {
                {0, 0}, {0, 1}, {0, 2}, {0, 3}, {0, 4}, {0, 5}, {0, 6}, {0, 7},
                {0, 8}, {0, 9}, {0, 10}, {0, 11}, {0, 12}, {0, 13},
                {1, 0}, {1, 13},
                {2, 0}, {2, 13},
                {3, 0}, {3, 3}, {3, 4}, {3, 5}, {3, 8}, {3, 9}, {3, 10}, {3, 13},
                {4, 0}, {4, 3}, {4, 10}, {4, 13},
                {5, 0}, {5, 3}, {5, 10}, {5, 13},
                {6, 0}, {6, 3}, {6, 10}, {6, 13},
                {7, 0}, {7, 3}, {7, 10}, {7, 13},
                {8, 0}, {8, 3}, {8, 4}, {8, 5}, {8, 8}, {8, 9}, {8, 10}, {8, 13},
                {9, 0}, {9, 13},
                {10, 0}, {10, 13},
                {11, 0}, {11, 1}, {11, 2}, {11, 3}, {11, 4}, {11, 5}, {11, 6}, {11, 7},
                {11, 8}, {11, 9}, {11, 10}, {11, 11}, {11, 12}, {11, 13}
        };
        for (int[] b : bloques) {
            mapa[b[0]][b[1]] = BLOQUE_MURO;
        }

        mapa[5][6] = BLOQUE_IGLU;
    }

    /**
     * Registra un {@link KeyAdapter} en el panel para capturar
     * las teclas de movimiento, espacio y pausa.
     */
    private void agregarKeyListener() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int code = e.getKeyCode();

                if (code == KeyEvent.VK_UP || code == KeyEvent.VK_W) {
                    intentarMoverHelado(Direccion.ARRIBA);
                } else if (code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) {
                    intentarMoverHelado(Direccion.ABAJO);
                } else if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A) {
                    intentarMoverHelado(Direccion.IZQUIERDA);
                } else if (code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_D) {
                    intentarMoverHelado(Direccion.DERECHA);
                } else if (code == KeyEvent.VK_SPACE) {
                    manejarEspacio();
                } else if (code == KeyEvent.VK_P || code == KeyEvent.VK_ESCAPE) {
                    juego.alternarPausa();
                    repaint();
                }
            }
        });
    }

    /**
     * Intenta mover el helado del jugador en la dirección indicada,
     * respetando el cooldown y las celdas bloqueadas.
     *
     * @param dir dirección hacia la que se desea mover el helado.
     */
    private void intentarMoverHelado(Direccion dir) {
        long ahora = System.currentTimeMillis();
        if (ahora - ultimoMovimientoHeladoMs < COOLDOWN_MOVIMIENTO_MS) {
            return;
        }

        if (!puedeMoverA(dir)) {
            return;
        }

        juego.moverHeladoJugador(dir);
        ultimoMovimientoHeladoMs = ahora;
        verificarFinDeNivel();
        repaint();
    }

    /**
     * Verifica si el helado del jugador puede moverse a una dirección dada,
     * comprobando que la celda de destino esté dentro del mapa y no bloqueada.
     *
     * @param dir dirección propuesta de movimiento.
     * @return {@code true} si el movimiento es válido, {@code false} en caso contrario.
     */
    private boolean puedeMoverA(Direccion dir) {
        Posicion p = nivel.getHelado().getPosicion();
        int f = p.getFila();
        int c = p.getColumna();

        switch (dir) {
            case ARRIBA -> f--;
            case ABAJO -> f++;
            case IZQUIERDA -> c--;
            case DERECHA -> c++;
        }

        if (f < 0 || f >= FILAS || c < 0 || c >= COLUMNAS) {
            return false;
        }
        return !esCeldaBloqueada(f, c);
    }

    /**
     * Gestiona la acción asociada a la barra espaciadora:
     * destruir bloques en línea o construir una fila de bloques de jugador.
     */
    private void manejarEspacio() {
        Helado h = nivel.getHelado();
        Direccion dir = h.getDireccion();
        Posicion p = h.getPosicion();

        int df = 0;
        int dc = 0;
        switch (dir) {
            case ARRIBA -> df = -1;
            case ABAJO -> df = 1;
            case IZQUIERDA -> dc = -1;
            case DERECHA -> dc = 1;
        }

        int f = p.getFila() + df;
        int c = p.getColumna() + dc;

        if (!dentro(f, c)) {
            return;
        }

        char primero = mapa[f][c];

        if (primero == BLOQUE_MURO || primero == BLOQUE_JUGADOR) {
            while (dentro(f, c)
                    && (mapa[f][c] == BLOQUE_MURO || mapa[f][c] == BLOQUE_JUGADOR)) {

                if (mapa[f][c] == BLOQUE_JUGADOR) {
                    nivel.eliminarBloqueJugador(f, c);
                }
                mapa[f][c] = ' ';
                f += df;
                c += dc;
            }
        } else if (primero == ' ') {
            while (dentro(f, c)
                    && mapa[f][c] == ' '
                    && !esCeldaIglu(f, c)
                    && !hayEnemigoEn(f, c)) {

                mapa[f][c] = BLOQUE_JUGADOR;
                nivel.registrarBloqueJugador(f, c);
                f += df;
                c += dc;
            }
        }

        repaint();
    }

    /**
     * Indica si una coordenada (fila, columna) está dentro de los límites del tablero.
     *
     * @param f fila a comprobar.
     * @param c columna a comprobar.
     * @return {@code true} si la celda está dentro del tablero, {@code false} en caso contrario.
     */
    private boolean dentro(int f, int c) {
        return f >= 0 && f < FILAS && c >= 0 && c < COLUMNAS;
    }

    /**
     * Determina si una celda pertenece al área reservada para el iglú.
     *
     * @param fila fila de la celda.
     * @param col  columna de la celda.
     * @return {@code true} si la celda está dentro del área del iglú, {@code false} en caso contrario.
     */
    private boolean esCeldaIglu(int fila, int col) {
        return (fila == 5 || fila == 6) && (col == 6 || col == 7);
    }

    /**
     * Comprueba si existe algún enemigo ubicado en la celda dada.
     *
     * @param fila fila de la celda.
     * @param col  columna de la celda.
     * @return {@code true} si hay un enemigo en esa posición, {@code false} en caso contrario.
     */
    private boolean hayEnemigoEn(int fila, int col) {
        for (Enemigo e : nivel.getEnemigos()) {
            Posicion p = e.getPosicion();
            if (p.getFila() == fila && p.getColumna() == col) {
                return true;
            }
        }
        return false;
    }

    /**
     * Indica si una celda está bloqueada para el movimiento del helado,
     * ya sea por ser muro, bloque jugador o parte del iglú.
     *
     * @param fila fila de la celda.
     * @param col  columna de la celda.
     * @return {@code true} si la celda está bloqueada, {@code false} en caso contrario.
     */
    private boolean esCeldaBloqueada(int fila, int col) {
        char tipo = mapa[fila][col];
        if (tipo == BLOQUE_MURO || tipo == BLOQUE_JUGADOR) {
            return true;
        }
        return esCeldaIglu(fila, col);
    }

    /**
     * Inicializa y arranca el {@link Timer} que avanza el estado del juego
     * cada segundo, actualizando el tiempo, verificando el fin de nivel y repintando.
     */
    private void iniciarTimer() {
        timer = new Timer(1000, e -> {
            juego.tick();
            verificarFinDeNivel();
            repaint();
        });
        timer.start();
    }

    /**
     * Verifica si el nivel actual ha sido ganado o perdido y, en tal caso,
     * muestra los diálogos correspondientes y cambia de pantalla.
     */
    private void verificarFinDeNivel() {
        if (nivel.isPerdido()) {
            timer.stop();
            int opcion = JOptionPane.showConfirmDialog(
                    this,
                    "Perdiste. ¿Reintentar el nivel 1?",
                    "Fin del nivel",
                    JOptionPane.YES_NO_OPTION
            );
            if (opcion == JOptionPane.YES_OPTION) {
                ventana.mostrarPantallaNivel1();
            } else {
                ventana.mostrarPantallaNivel();
            }
        } else if (nivel.isGanado()) {
            timer.stop();

            int puntaje = nivel.getPuntajeActual();

            JOptionPane.showMessageDialog(
                    this,
                    "¡Ganaste! Puntaje: " + puntaje,
                    "Victoria",
                    JOptionPane.INFORMATION_MESSAGE
            );

            ventana.mostrarPantallaNivel();
        }
    }

    /**
     * Método de dibujo principal del panel.
     * Limpia el fondo y dibuja la barra superior, el tablero,
     * los objetos del dominio y, si procede, el overlay de pausa.
     *
     * @param g contexto gráfico proporcionado por Swing.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        dibujarBarraSuperior(g2d);
        dibujarFondoYGrid(g2d);
        dibujarBordes(g2d);
        dibujarCeldas(g2d);
        dibujarObjetosDominio(g2d);

        if (juego.isPausado()) {
            dibujarOverlayPausa(g2d);
        }
    }

    /**
     * Dibuja la barra superior con información de frutas recogidas,
     * tiempo restante y puntaje actual.
     *
     * @param g2d contexto gráfico 2D utilizado para dibujar.
     */
    private void dibujarBarraSuperior(Graphics2D g2d) {
        g2d.setColor(new Color(102, 126, 234));
        g2d.fillRect(0, 0, getWidth(), OFFSET_Y);

        int uvasTotal = 8;
        int platanosTotal = 8;
        int uvasRecolectadas = 0;
        int platanosRecolectados = 0;

        for (Fruta f : nivel.getFrutas()) {
            if (f instanceof Uva && f.estaRecolectada()) {
                uvasRecolectadas++;
            }
            if (f instanceof Platano && f.estaRecolectada()) {
                platanosRecolectados++;
            }
        }

        int tiempo = nivel.getTiempoRestanteSegundos();
        int minutos = tiempo / 60;
        int segundos = tiempo % 60;
        String textoTiempoValor = String.format("%d:%02d", minutos, segundos);

        int puntaje = nivel.getPuntajeActual();

        String textoUvas = "Uvas: " + uvasRecolectadas + "/" + uvasTotal;
        String textoPlatanos = "Platanos: " + platanosRecolectados + "/" + platanosTotal;
        String textoTiempo = "Tiempo: " + textoTiempoValor;
        String textoPuntaje = "Puntaje: " + puntaje;

        g2d.setColor(Color.WHITE);
        Font fuente = new Font("Monospaced", Font.BOLD, 18);
        g2d.setFont(fuente);
        FontMetrics fm = g2d.getFontMetrics();

        int yLinea1 = 32;
        int yLinea2 = 54;

        int xUvas = 20;
        g2d.drawString(textoUvas, xUvas, yLinea1);

        int anchoPlatanos = fm.stringWidth(textoPlatanos);
        int xPlatanos = (getWidth() - anchoPlatanos) / 2;
        g2d.drawString(textoPlatanos, xPlatanos, yLinea1);

        int anchoTiempo = fm.stringWidth(textoTiempo);
        int xTiempo = getWidth() - 20 - anchoTiempo;
        g2d.drawString(textoTiempo, xTiempo, yLinea1);

        int anchoPuntaje = fm.stringWidth(textoPuntaje);
        int xPuntaje = getWidth() - 20 - anchoPuntaje;
        g2d.drawString(textoPuntaje, xPuntaje, yLinea2);
    }

    /**
     * Dibuja el fondo del área de juego y la cuadrícula
     * que delimita las celdas del tablero.
     *
     * @param g2d contexto gráfico 2D utilizado para dibujar.
     */
    private void dibujarFondoYGrid(Graphics2D g2d) {
        int offsetX = BORDE * TAM_CELDA;
        int offsetY = OFFSET_Y + BORDE * TAM_CELDA;

        g2d.setColor(new Color(173, 216, 230));
        g2d.fillRect(offsetX, offsetY, COLUMNAS * TAM_CELDA, FILAS * TAM_CELDA);

        g2d.setColor(new Color(150, 190, 220));
        for (int c = 0; c <= COLUMNAS; c++) {
            int x = offsetX + c * TAM_CELDA;
            g2d.drawLine(x, offsetY, x, offsetY + FILAS * TAM_CELDA);
        }
        for (int f = 0; f <= FILAS; f++) {
            int y = OFFSET_Y + BORDE * TAM_CELDA + f * TAM_CELDA;
            g2d.drawLine(offsetX, y, offsetX + COLUMNAS * TAM_CELDA, y);
        }
    }

    /**
     * Dibuja los bloques de borde que rodean el tablero de juego.
     *
     * @param g2d contexto gráfico 2D utilizado para dibujar.
     */
    private void dibujarBordes(Graphics2D g2d) {
        int totalCols = COLUMNAS + 2 * BORDE;
        int totalRows = FILAS + 2 * BORDE;
        int startY = OFFSET_Y;

        for (int c = 0; c < totalCols; c++) {
            int x = c * TAM_CELDA;
            g2d.drawImage(imgBloqueLateral, x, startY, TAM_CELDA, TAM_CELDA, this);
            g2d.drawImage(imgBloqueLateral, x,
                    startY + (totalRows - 1) * TAM_CELDA, TAM_CELDA, TAM_CELDA, this);
        }
        for (int r = 1; r < totalRows - 1; r++) {
            int y = startY + r * TAM_CELDA;
            g2d.drawImage(imgBloqueLateral, 0, y, TAM_CELDA, TAM_CELDA, this);
            g2d.drawImage(imgBloqueLateral,
                    (totalCols - 1) * TAM_CELDA, y, TAM_CELDA, TAM_CELDA, this);
        }
    }

    /**
     * Recorre la matriz del mapa y dibuja muros, bloques de jugador
     * e iglú en las posiciones correspondientes.
     *
     * @param g2d contexto gráfico 2D utilizado para dibujar.
     */
    private void dibujarCeldas(Graphics2D g2d) {
        int offsetX = BORDE * TAM_CELDA;
        int offsetY = OFFSET_Y + BORDE * TAM_CELDA;

        for (int f = 0; f < FILAS; f++) {
            for (int c = 0; c < COLUMNAS; c++) {
                int x = offsetX + c * TAM_CELDA;
                int y = offsetY + f * TAM_CELDA;
                char tipo = mapa[f][c];

                if (tipo == BLOQUE_MURO || tipo == BLOQUE_JUGADOR) {
                    dibujarEscalado(g2d, imgBloqueHielo, x, y);
                } else if (tipo == BLOQUE_IGLU) {
                    int ancho = 2 * TAM_CELDA;
                    int alto = 2 * TAM_CELDA;
                    g2d.drawImage(imgIglu, x, y, ancho, alto, this);
                }
            }
        }
    }

    /**
     * Dibuja todos los objetos provenientes del dominio:
     * frutas activas, enemigos y el helado del jugador.
     *
     * @param g2d contexto gráfico 2D utilizado para dibujar.
     */
    private void dibujarObjetosDominio(Graphics2D g2d) {
        int offsetX = BORDE * TAM_CELDA;
        int offsetY = OFFSET_Y + BORDE * TAM_CELDA;

        for (Fruta f : nivel.getFrutas()) {
            if (f.estaRecolectada() || !f.estaActiva()) {
                continue;
            }
            Posicion p = f.getPosicion();
            int x = offsetX + p.getColumna() * TAM_CELDA;
            int y = offsetY + p.getFila() * TAM_CELDA;

            if (f instanceof Uva) {
                dibujarEscalado(g2d, imgUva, x, y);
            } else if (f instanceof Platano) {
                dibujarEscalado(g2d, imgPlatano, x, y);
            }
        }

        for (Enemigo e : nivel.getEnemigos()) {
            Posicion p = e.getPosicion();
            int x = offsetX + p.getColumna() * TAM_CELDA;
            int y = offsetY + p.getFila() * TAM_CELDA;
            dibujarEscalado(g2d, imgTroll, x, y);
        }

        Posicion ph = nivel.getHelado().getPosicion();
        int xh = offsetX + ph.getColumna() * TAM_CELDA;
        int yh = OFFSET_Y + BORDE * TAM_CELDA + ph.getFila() * TAM_CELDA;
        dibujarHeladoJugador(g2d, xh, yh);
    }

    /**
     * Dibuja una imagen escalada al tamaño de una celda del tablero
     * en la posición indicada.
     *
     * @param g2d contexto gráfico 2D utilizado para dibujar.
     * @param img imagen a dibujar (puede ser {@code null}).
     * @param x   coordenada X (en píxeles) donde se dibuja.
     * @param y   coordenada Y (en píxeles) donde se dibuja.
     */
    private void dibujarEscalado(Graphics2D g2d, Image img, int x, int y) {
        if (img != null) {
            g2d.drawImage(img, x, y, TAM_CELDA, TAM_CELDA, this);
        }
    }

    /**
     * Selecciona la imagen de helado adecuada según el sabor actual
     * y la dibuja en la posición indicada.
     *
     * @param g2d contexto gráfico 2D utilizado para dibujar.
     * @param x   coordenada X del helado en píxeles.
     * @param y   coordenada Y del helado en píxeles.
     */
    private void dibujarHeladoJugador(Graphics2D g2d, int x, int y) {
        Image img = imgHeladoVainilla;
        String sabor = saborActual == null ? "" : saborActual.toLowerCase();

        if (sabor.contains("fresa")) {
            img = imgHeladoFresa;
        } else if (sabor.contains("chocolate")) {
            img = imgHeladoChocolate;
        }

        dibujarEscalado(g2d, img, x, y);
    }

    /**
     * Dibuja una capa semitransparente sobre todo el panel
     * con el texto "Pausado" centrado, para indicar que el juego
     * está en estado de pausa.
     *
     * @param g2d contexto gráfico 2D utilizado para dibujar.
     */
    private void dibujarOverlayPausa(Graphics2D g2d) {
        g2d.setColor(new Color(0, 0, 0, 150));
        g2d.fillRect(0, 0, getWidth(), getHeight());

        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Monospaced", Font.BOLD, 36));
        String texto = "Pausado";
        FontMetrics fm = g2d.getFontMetrics();
        int x = (getWidth() - fm.stringWidth(texto)) / 2;
        int y = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();
        g2d.drawString(texto, x, y);
    }
}
