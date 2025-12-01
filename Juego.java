package Dominio;

/**
 * Representa una partida de Bad DOPO Cream.
 * Coordina el nivel actual, el estado de pausa y la interacción
 * entre la capa de aplicación y el modelo de dominio.
 * Autores: Santiago Andres Gomez Rojas y Miguel Angel Sandoval
 */
public class Juego {

    private final String saborHelado;
    private final ModalidadJuego modalidad;

    private Nivel nivel;
    private boolean pausado;

    /**
     * Crea un nuevo juego con un sabor de helado y una modalidad específicos,
     * inicializando el primer nivel.
     *
     * @param saborHelado sabor del helado principal de la partida.
     * @param modalidad   modalidad de juego seleccionada.
     */
    public Juego(String saborHelado, ModalidadJuego modalidad) {
        this.saborHelado = saborHelado;
        this.modalidad = modalidad;
        this.nivel = new Nivel(saborHelado, modalidad);
        this.pausado = false;
    }

    /**
     * Obtiene el nivel actual del juego.
     *
     * @return nivel asociado a la partida.
     */
    public Nivel getNivel() {
        return nivel;
    }

    /**
     * Alterna el estado de pausa del juego entre pausado y en ejecución.
     */
    public void alternarPausa() {
        pausado = !pausado;
    }

    /**
     * Indica si el juego está actualmente en pausa.
     *
     * @return {@code true} si el juego está pausado, {@code false} en caso contrario.
     */
    public boolean isPausado() {
        return pausado;
    }

    /**
     * Avanza la lógica del juego en un paso (tick),
     * siempre y cuando la partida no esté pausada.
     * Este método se invoca de manera periódica desde la capa de presentación.
     */
    public void tick() {
        if (pausado) return;
        nivel.actualizarUnTick();
    }

    /**
     * Solicita mover el helado del jugador en la dirección indicada,
     * siempre que el juego no esté en pausa.
     *
     * @param dir dirección hacia la que se desea mover el helado.
     */
    public void moverHeladoJugador(Direccion dir) {
        if (pausado) return;
        nivel.moverHeladoJugador(dir);
    }

    /**
     * Solicita mover un enemigo controlado por jugador en la dirección indicada,
     * siempre que el juego no esté en pausa.
     *
     * @param indice índice del enemigo en la lista del nivel.
     * @param dir    dirección hacia la que se desea mover el enemigo.
     */
    public void moverEnemigoJugador(int indice, Direccion dir) {
        if (pausado) return;
        nivel.moverEnemigoJugador(indice, dir);
    }

    /**
     * Reinicia el nivel actual creando una nueva instancia de {@link Nivel}
     * con el mismo sabor de helado y la misma modalidad de juego.
     */
    public void reiniciarNivel() {
        this.nivel = new Nivel(saborHelado, modalidad);
        this.pausado = false;
    }
}
