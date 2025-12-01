package Dominio;

/**
 * Representa una fruta del tipo plátano, estática en el tablero.
 * Autores: Santiago Andres Gomez Rojas y Miguel Angel Sandoval
 */
public class Platano extends Fruta {

    /**
     * Crea un plátano en la posición indicada.
     *
     * @param pos posición inicial del plátano.
     */
    public Platano(Posicion pos) {
        super(pos);
    }

    /**
     * Devuelve los puntos que otorga el plátano al ser recolectado.
     *
     * @return valor fijo de 100 puntos.
     */
    @Override
    public int getPuntos() {
        return 100;
    }
}
