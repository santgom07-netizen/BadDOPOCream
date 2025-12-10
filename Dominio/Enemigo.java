package Dominio;

/**
 * Clase base abstracta para todos los enemigos del juego.
 * Un enemigo tiene una posición y puede actualizar su comportamiento
 * de forma automática o controlada por el jugador.
 * Autores: Santiago Andres Gomez Rojas y Miguel Angel Sandoval
 */
public abstract class Enemigo {

    protected Posicion posicion;

    /**
     * Crea un enemigo en la posición inicial dada.
     *
     * @param posicionInicial posición inicial del enemigo.
     */
    public Enemigo(Posicion posicionInicial) {
        this.posicion = posicionInicial;
    }

    /**
     * Obtiene la posición actual del enemigo.
     *
     * @return posición del enemigo.
     */
    public Posicion getPosicion() {
        return posicion;
    }

    /**
     * Actualiza el estado interno del enemigo según su IA
     * y el contexto del nivel.
     *
     * @param nivel nivel en el que se encuentra el enemigo.
     */
    public abstract void actualizar(Nivel nivel);

    /**
     * Mueve al enemigo una celda en la dirección indicada,
     * respetando los límites del tablero.
     *
     * @param dir         dirección hacia la que se desea mover al enemigo.
     * @param filasMax    número máximo de filas permitidas.
     * @param columnasMax número máximo de columnas permitidas.
     */
    public void moverControlado(Direccion dir, int filasMax, int columnasMax) {
        int f = posicion.getFila();
        int c = posicion.getColumna();

        switch (dir) {
            case ARRIBA -> f--;
            case ABAJO -> f++;
            case IZQUIERDA -> c--;
            case DERECHA -> c++;
        }

        if (f >= 0 && f < filasMax && c >= 0 && c < columnasMax) {
            posicion.setFila(f);
            posicion.setColumna(c);
        }
    }

    /**
     * Verifica si el enemigo se encuentra en la misma posición que el helado.
     *
     * @param helado helado con el que se compara la posición.
     * @return {@code true} si el enemigo y el helado ocupan la misma celda,
     *         {@code false} en caso contrario.
     */
    public boolean tocaHelado(Helado helado) {
        return posicion.mismaPosicion(helado.getPosicion());
    }
}
