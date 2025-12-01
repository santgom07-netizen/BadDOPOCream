package Dominio;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Representa un nivel del juego, incluyendo el helado principal,
 * las frutas, los enemigos, los bloques y el estado general del nivel.
 * Gestiona la lógica de victoria, derrota, puntaje, oleadas de frutas
 * y el avance del tiempo.
 * Autores: Santiago Andres Gomez Rojas y Miguel Angel Sandoval
 */
public class Nivel {

    /**
     * Número de filas del tablero del nivel.
     */
    public static final int FILAS = 12;

    /**
     * Número de columnas del tablero del nivel.
     */
    public static final int COLUMNAS = 14;

    private Helado helado;
    private List<Fruta> frutas;
    private List<Enemigo> enemigos;
    private List<BloqueHielo> bloques;
    private List<Posicion> bloquesJugador;

    private int tiempoRestanteSegundos;
    private boolean ganado;
    private boolean perdido;

    private ModalidadJuego modalidad;
    private TipoControl controlHelado;
    private TipoControl controlEnemigos;

    /**
     * Puntaje acumulado en el nivel.
     */
    private int puntajeActual;

    /**
     * Oleada actual de frutas.
     * 1 = primera oleada, 2 = segunda oleada.
     */
    private int oleadaActual;

    private final Random random = new Random();

    /**
     * Crea un nuevo nivel con un único helado y una modalidad específica.
     * Inicializa el nivel 1 y configura un tiempo máximo de 3 minutos.
     *
     * @param saborHelado sabor del helado principal.
     * @param modalidad   modalidad de juego seleccionada.
     */
    public Nivel(String saborHelado, ModalidadJuego modalidad) {
        this.frutas = new ArrayList<>();
        this.enemigos = new ArrayList<>();
        this.bloques = new ArrayList<>();
        this.bloquesJugador = new ArrayList<>();
        this.tiempoRestanteSegundos = 3 * 60;
        this.ganado = false;
        this.perdido = false;
        this.puntajeActual = 0;
        this.oleadaActual = 1;

        this.modalidad = modalidad;
        configurarControlesSegunModalidad();
        inicializarNivel1(saborHelado);
    }

    /**
     * Configura el tipo de control asignado al helado y a los enemigos,
     * según la modalidad de juego seleccionada.
     */
    private void configurarControlesSegunModalidad() {
        switch (modalidad) {
            case PvsP -> {
                controlHelado = TipoControl.JUGADOR;
                controlEnemigos = TipoControl.JUGADOR;
            }
            case PvsM -> {
                controlHelado = TipoControl.JUGADOR;
                controlEnemigos = TipoControl.MAQUINA;
            }
            case MvsM -> {
                controlHelado = TipoControl.MAQUINA;
                controlEnemigos = TipoControl.MAQUINA;
            }
        }
    }

    /**
     * Inicializa el contenido del nivel 1, incluyendo la posición del helado,
     * la distribución de frutas, la configuración de oleadas y la ubicación
     * de los enemigos.
     *
     * @param saborHelado sabor del helado principal.
     */
    private void inicializarNivel1(String saborHelado) {
        bloquesJugador.clear();
        frutas.clear();
        enemigos.clear();

        helado = new Helado(new Posicion(1, 7), saborHelado);

        frutas.add(new Uva(new Posicion(1, 2)));
        frutas.add(new Uva(new Posicion(1, 11)));
        frutas.add(new Uva(new Posicion(2, 1)));
        frutas.add(new Uva(new Posicion(2, 12)));
        frutas.add(new Uva(new Posicion(9, 1)));
        frutas.add(new Uva(new Posicion(9, 12)));
        frutas.add(new Uva(new Posicion(10, 2)));
        frutas.add(new Uva(new Posicion(10, 11)));

        frutas.add(new Platano(new Posicion(4, 6)));
        frutas.add(new Platano(new Posicion(4, 7)));
        frutas.add(new Platano(new Posicion(5, 5)));
        frutas.add(new Platano(new Posicion(5, 8)));
        frutas.add(new Platano(new Posicion(6, 5)));
        frutas.add(new Platano(new Posicion(6, 8)));
        frutas.add(new Platano(new Posicion(7, 6)));
        frutas.add(new Platano(new Posicion(7, 7)));

        for (Fruta f : frutas) {
            if (f instanceof Platano) {
                f.activar();
            } else if (f instanceof Uva) {
                f.desactivar();
            }
        }
        oleadaActual = 1;

        enemigos.add(new Troll(new Posicion(10, 6), 1, 10, 1, 12));
        enemigos.add(new Troll(new Posicion(7, 4), 4, 7, 4, 9));
    }

    public Helado getHelado() {
        return helado;
    }

    public List<Fruta> getFrutas() {
        return frutas;
    }

    public List<Enemigo> getEnemigos() {
        return enemigos;
    }

    public int getTiempoRestanteSegundos() {
        return tiempoRestanteSegundos;
    }

    public boolean isGanado() {
        return ganado;
    }

    public boolean isPerdido() {
        return perdido;
    }

    public ModalidadJuego getModalidad() {
        return modalidad;
    }

    /**
     * Obtiene el puntaje acumulado en el nivel.
     *
     * @return puntaje actual del nivel.
     */
    public int getPuntajeActual() {
        return puntajeActual;
    }

    public List<Posicion> getBloquesJugador() {
        return bloquesJugador;
    }

    /**
     * Registra un nuevo bloque de hielo creado por el jugador
     * en la posición dada, evitando duplicados.
     *
     * @param fila    fila donde se ubica el bloque.
     * @param columna columna donde se ubica el bloque.
     */
    public void registrarBloqueJugador(int fila, int columna) {
        for (Posicion p : bloquesJugador) {
            if (p.getFila() == fila && p.getColumna() == columna) {
                return;
            }
        }
        bloquesJugador.add(new Posicion(fila, columna));
    }

    /**
     * Elimina de la lista el bloque de hielo del jugador que se encuentre
     * en la posición indicada.
     *
     * @param fila    fila del bloque a eliminar.
     * @param columna columna del bloque a eliminar.
     */
    public void eliminarBloqueJugador(int fila, int columna) {
        bloquesJugador.removeIf(p -> p.getFila() == fila && p.getColumna() == columna);
    }

    /**
     * Mueve el helado controlado por el jugador en la dirección indicada,
     * si el nivel no ha terminado y el helado no está controlado por máquina.
     *
     * @param dir dirección hacia la que se desea mover el helado.
     */
    public void moverHeladoJugador(Direccion dir) {
        if (ganado || perdido) {
            return;
        }
        if (controlHelado != TipoControl.JUGADOR) {
            return;
        }
        moverHelado(dir);
    }

    /**
     * Mueve un enemigo cuando la modalidad implica que un jugador
     * puede controlarlo directamente.
     *
     * @param indiceEnemigo índice del enemigo dentro de la lista de enemigos.
     * @param dir           dirección hacia la que se desea mover el enemigo.
     */
    public void moverEnemigoJugador(int indiceEnemigo, Direccion dir) {
        if (ganado || perdido) {
            return;
        }
        if (controlEnemigos != TipoControl.JUGADOR) {
            return;
        }
        if (indiceEnemigo < 0 || indiceEnemigo >= enemigos.size()) {
            return;
        }

        enemigos.get(indiceEnemigo).moverControlado(dir, FILAS, COLUMNAS);
        verificarColisionEnemigos();
    }

    /**
     * Lógica interna para mover el helado en la dirección indicada,
     * recolectando frutas, manejando oleadas, verificando colisiones
     * y evaluando victoria.
     *
     * @param dir dirección hacia la que se mueve el helado.
     */
    private void moverHelado(Direccion dir) {
        helado.mover(dir, FILAS, COLUMNAS);
        verificarRecoleccionFrutas();
        verificarCambioDeOleada();
        verificarColisionEnemigos();
        verificarVictoria();
    }

    /**
     * Actualiza el estado del nivel en un paso de tiempo,
     * avanzando la IA de enemigos y/o helado según el tipo de control,
     * verificando colisiones y descontando tiempo.
     */
    public void actualizarUnTick() {
        if (ganado || perdido) {
            return;
        }

        if (controlEnemigos == TipoControl.MAQUINA) {
            for (Enemigo e : enemigos) {
                e.actualizar(this);
            }
        }

        if (controlHelado == TipoControl.MAQUINA) {
            moverHeladoIA();
        }

        verificarColisionEnemigos();

        tiempoRestanteSegundos--;
        if (tiempoRestanteSegundos <= 0 && !ganado) {
            perdido = true;
        }
    }

    /**
     * Mueve el helado utilizando una decisión aleatoria de dirección,
     * utilizada cuando el helado está controlado por máquina.
     */
    private void moverHeladoIA() {
        Direccion[] dirs = Direccion.values();
        Direccion dir = dirs[random.nextInt(dirs.length)];
        moverHelado(dir);
    }

    /**
     * Verifica si el helado ha recolectado alguna fruta activa en su posición
     * actual y actualiza el puntaje del nivel con los puntos correspondientes.
     */
    private void verificarRecoleccionFrutas() {
        for (Fruta f : frutas) {
            if (!f.estaRecolectada()
                    && f.estaActiva()
                    && f.getPosicion().mismaPosicion(helado.getPosicion())) {
                f.recolectar();
                puntajeActual += f.getPuntos();
            }
        }
    }

    /**
     * Activa la siguiente oleada de frutas cuando todas las frutas
     * de la oleada actual han sido recolectadas.
     */
    private void verificarCambioDeOleada() {
        if (oleadaActual == 1) {
            boolean quedanPlatanos = false;
            for (Fruta f : frutas) {
                if (f instanceof Platano && !f.estaRecolectada()) {
                    quedanPlatanos = true;
                    break;
                }
            }
            if (!quedanPlatanos) {
                for (Fruta f : frutas) {
                    if (f instanceof Uva) {
                        f.activar();
                    }
                }
                oleadaActual = 2;
            }
        }
    }

    /**
     * Verifica si algún enemigo ha tocado al helado
     * y marca el nivel como perdido si ocurre una colisión.
     */
    private void verificarColisionEnemigos() {
        for (Enemigo e : enemigos) {
            if (e.tocaHelado(helado)) {
                perdido = true;
                break;
            }
        }
    }

    /**
     * Verifica si se han recolectado todas las uvas y plátanos requeridos
     * para considerar el nivel como ganado.
     */
    private void verificarVictoria() {
        long uvas = frutas.stream()
                .filter(f -> f instanceof Uva && f.estaRecolectada())
                .count();
        long platanos = frutas.stream()
                .filter(f -> f instanceof Platano && f.estaRecolectada())
                .count();

        if (uvas >= 8 && platanos >= 8) {
            ganado = true;
        }
    }
}
