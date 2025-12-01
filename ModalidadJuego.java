package Dominio;

/**
 * Enumeración que describe las modalidades posibles del juego
 * en términos de quién controla a los helados y los enemigos.
 * Autores: Santiago Andres Gomez Rojas y Miguel Angel Sandoval
 */
public enum ModalidadJuego {
    /**
     * Jugador contra jugador.
     */
    PvsP,

    /**
     * Jugador contra máquina.
     */
    PvsM,

    /**
     * Máquina contra máquina.
     */
    MvsM
}
