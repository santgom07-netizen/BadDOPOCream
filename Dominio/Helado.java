package Dominio;

/**
 * Representa un helado dentro del tablero del juego.
 * Un helado tiene una posición, una dirección de movimiento actual y un sabor.
 * Autores: Santiago Andres Gomez Rojas y Miguel Angel Sandoval
 */
public class Helado {

    private Posicion posicion;
    private Direccion direccion;
    private String sabor;

    /**
     * Crea un helado con una posición inicial y un sabor específico.
     *
     * @param posicionInicial posición inicial del helado en el tablero.
     * @param sabor           sabor del helado (por ejemplo, "vainilla", "fresa" o "chocolate").
     */
    public Helado(Posicion posicionInicial, String sabor) {
        this.posicion = posicionInicial;
        this.sabor = sabor;
        this.direccion = Direccion.ABAJO;
    }

    /**
     * Obtiene la posición actual del helado.
     *
     * @return posición del helado.
     */
    public Posicion getPosicion() {
        return posicion;
    }

    /**
     * Obtiene la dirección en la que el helado se movió por última vez.
     *
     * @return dirección actual del helado.
     */
    public Direccion getDireccion() {
        return direccion;
    }

    /**
     * Obtiene el sabor actual del helado.
     *
     * @return sabor del helado.
     */
    public String getSabor() {
        return sabor;
    }

    /**
     * Cambia el sabor del helado.
     *
     * @param sabor nuevo sabor que tendrá el helado.
     */
    public void setSabor(String sabor) {
        this.sabor = sabor;
    }

    /**
     * Mueve el helado una celda en la dirección indicada, siempre y cuando
     * la nueva posición se encuentre dentro de los límites del tablero.
     *
     * @param dir         dirección hacia la que se desea mover el helado.
     * @param filasMax    número máximo de filas permitidas en el tablero.
     * @param columnasMax número máximo de columnas permitidas en el tablero.
     */
    public void mover(Direccion dir, int filasMax, int columnasMax) {
        direccion = dir;
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
}
