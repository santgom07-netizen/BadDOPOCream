package Dominio;

/**
 * Representa un bloque de hielo en el tablero del juego.
 * Puede ser destruible o no, según su configuración.
 * Autores: Santiago Andres Gomez Rojas y Miguel Angel Sandoval
 */
public class BloqueHielo {

    private Posicion posicion;
    private boolean destruible;

    /**
     * Crea un bloque de hielo con una posición y una propiedad de destrucción.
     *
     * @param posicion   posición del bloque en el tablero.
     * @param destruible valor que indica si el bloque puede ser destruido.
     */
    public BloqueHielo(Posicion posicion, boolean destruible) {
        this.posicion = posicion;
        this.destruible = destruible;
    }

    /**
     * Obtiene la posición del bloque de hielo.
     *
     * @return posición del bloque.
     */
    public Posicion getPosicion() {
        return posicion;
    }

    /**
     * Indica si el bloque puede ser destruido.
     *
     * @return {@code true} si el bloque es destruible, {@code false} en caso contrario.
     */
    public boolean esDestruible() {
        return destruible;
    }
}
