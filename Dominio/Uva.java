package Dominio;

/**
 * Representa una fruta del tipo uva, estática en el tablero.
 * Autores: Santiago Andres Gomez Rojas y Miguel Angel Sandoval
 */
public class Uva extends Fruta {

    /**
     * Crea una uva en la posición indicada.
     *
     * @param pos posición inicial de la uva.
     */
    public Uva(Posicion pos) {
        super(pos);
    }

    /**
     * Devuelve los puntos que otorga la uva al ser recolectada.
     *
     * @return valor fijo de 50 puntos.
     */
    @Override
    public int getPuntos() {
        return 50;
    }
}
