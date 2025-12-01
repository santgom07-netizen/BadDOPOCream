package Dominio;

/**
 * Clase base abstracta para todas las frutas del juego.
 * Una fruta tiene una posición, puede ser recolectada por el helado,
 * aporta una cantidad fija de puntos al puntaje del nivel
 * y puede estar activa o inactiva para manejar oleadas.
 */
public abstract class Fruta {

    private Posicion posicion;
    private boolean recolectada;
    private boolean activa;

    /**
     * Crea una fruta en la posición indicada.
     *
     * @param posicion posición inicial de la fruta.
     */
    public Fruta(Posicion posicion) {
        this.posicion = posicion;
        this.recolectada = false;
        this.activa = true;
    }

    /**
     * Obtiene la posición actual de la fruta.
     *
     * @return posición de la fruta.
     */
    public Posicion getPosicion() {
        return posicion;
    }

    /**
     * Indica si la fruta ya fue recolectada.
     *
     * @return true si la fruta ha sido recolectada, false en caso contrario.
     */
    public boolean estaRecolectada() {
        return recolectada;
    }

    /**
     * Marca la fruta como recolectada.
     */
    public void recolectar() {
        this.recolectada = true;
    }

    /**
     * Indica si la fruta está activa en el nivel.
     * Solo las frutas activas se dibujan y se pueden recolectar.
     *
     * @return true si la fruta está activa, false en caso contrario.
     */
    public boolean estaActiva() {
        return activa;
    }

    /**
     * Activa la fruta para que sea visible y recolectable.
     */
    public void activar() {
        this.activa = true;
    }

    /**
     * Desactiva la fruta para que no sea visible ni recolectable.
     */
    public void desactivar() {
        this.activa = false;
    }

    /**
     * Devuelve la cantidad de puntos que aporta esta fruta cuando se recolecta.
     *
     * @return puntos que otorga la fruta.
     */
    public abstract int getPuntos();

    /**
     * Actualiza el estado de la fruta dentro de un nivel.
     * Puede ser sobrescrita por frutas con comportamiento dinámico.
     *
     * @param nivel nivel en el que se encuentra la fruta.
     */
    public void actualizar(Nivel nivel) {
    }
}
