package Dominio;

/**
 * Representa una posición discreta dentro del tablero del juego,
 * utilizando coordenadas de fila y columna.
 * Autores: Santiago Andres Gomez Rojas y Miguel Angel Sandoval
 */
public class Posicion {

    private int fila;
    private int columna;

    /**
     * Crea una posición a partir de una fila y una columna.
     *
     * @param fila    índice de la fila.
     * @param columna índice de la columna.
     */
    public Posicion(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
    }

    /**
     * Obtiene la fila de la posición.
     *
     * @return índice de la fila.
     */
    public int getFila() {
        return fila;
    }

    /**
     * Obtiene la columna de la posición.
     *
     * @return índice de la columna.
     */
    public int getColumna() {
        return columna;
    }

    /**
     * Actualiza la fila de la posición.
     *
     * @param fila nueva fila.
     */
    public void setFila(int fila) {
        this.fila = fila;
    }

    /**
     * Actualiza la columna de la posición.
     *
     * @param columna nueva columna.
     */
    public void setColumna(int columna) {
        this.columna = columna;
    }

    /**
     * Verifica si esta posición coincide con otra posición dada.
     *
     * @param otra posición a comparar.
     * @return {@code true} si ambas posiciones tienen la misma fila y columna,
     *         {@code false} en caso contrario.
     */
    public boolean mismaPosicion(Posicion otra) {
        return this.fila == otra.fila && this.columna == otra.columna;
    }
}
