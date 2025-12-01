package Dominio;

/**
 * Enemigo del tipo troll que patrulla el perímetro de un rectángulo
 * definido por filas y columnas mínimas y máximas.
 * Autores: Santiago Andres Gomez Rojas y Miguel Angel Sandoval
 */
public class Troll extends Enemigo {

    private Direccion direccionActual;
    private final int filaMin;
    private final int filaMax;
    private final int colMin;
    private final int colMax;

    private boolean sentidoHorario = true;

    /**
     * Crea un troll en una posición inicial y con un área rectangular de patrullaje.
     *
     * @param posicionInicial posición inicial del troll.
     * @param filaMin         fila mínima del rectángulo de patrullaje.
     * @param filaMax         fila máxima del rectángulo de patrullaje.
     * @param colMin          columna mínima del rectángulo de patrullaje.
     * @param colMax          columna máxima del rectángulo de patrullaje.
     */
    public Troll(Posicion posicionInicial,
                 int filaMin, int filaMax,
                 int colMin, int colMax) {
        super(posicionInicial);
        this.filaMin = filaMin;
        this.filaMax = filaMax;
        this.colMin = colMin;
        this.colMax = colMax;
        this.direccionActual = Direccion.DERECHA;
    }

    /**
     * Actualiza la posición del troll siguiendo el contorno del rectángulo
     * definido, cambiando de dirección cuando alcanza los límites y
     * reaccionando ante bloques de jugador.
     *
     * @param nivel nivel en el que se encuentra el troll.
     */
    @Override
    public void actualizar(Nivel nivel) {
        int f = posicion.getFila();
        int c = posicion.getColumna();
        int nf = f;
        int nc = c;

        if (sentidoHorario) {
            switch (direccionActual) {
                case DERECHA -> {
                    nc++;
                    if (nc > colMax) {
                        nc = colMax;
                        direccionActual = Direccion.ARRIBA;
                        return;
                    }
                }
                case ARRIBA -> {
                    nf--;
                    if (nf < filaMin) {
                        nf = filaMin;
                        direccionActual = Direccion.IZQUIERDA;
                        return;
                    }
                }
                case IZQUIERDA -> {
                    nc--;
                    if (nc < colMin) {
                        nc = colMin;
                        direccionActual = Direccion.ABAJO;
                        return;
                    }
                }
                case ABAJO -> {
                    nf++;
                    if (nf > filaMax) {
                        nf = filaMax;
                        direccionActual = Direccion.DERECHA;
                        return;
                    }
                }
            }
        } else {
            switch (direccionActual) {
                case DERECHA -> {
                    nc++;
                    if (nc > colMax) {
                        nc = colMax;
                        direccionActual = Direccion.ABAJO;
                        return;
                    }
                }
                case ABAJO -> {
                    nf++;
                    if (nf > filaMax) {
                        nf = filaMax;
                        direccionActual = Direccion.IZQUIERDA;
                        return;
                    }
                }
                case IZQUIERDA -> {
                    nc--;
                    if (nc < colMin) {
                        nc = colMin;
                        direccionActual = Direccion.ARRIBA;
                        return;
                    }
                }
                case ARRIBA -> {
                    nf--;
                    if (nf < filaMin) {
                        nf = filaMin;
                        direccionActual = Direccion.DERECHA;
                        return;
                    }
                }
            }
        }

        if (hayBloqueJugadorEn(nivel, nf, nc)) {
            invertirDireccion();
            sentidoHorario = !sentidoHorario;
            return;
        }

        posicion.setFila(nf);
        posicion.setColumna(nc);
    }

    /**
     * Verifica si existe un bloque de hielo creado por el jugador
     * en la posición indicada.
     *
     * @param nivel nivel en el que se encuentra el troll.
     * @param fila  fila a evaluar.
     * @param col   columna a evaluar.
     * @return {@code true} si hay un bloque de jugador en la posición, {@code false} en caso contrario.
     */
    private boolean hayBloqueJugadorEn(Nivel nivel, int fila, int col) {
        for (Posicion p : nivel.getBloquesJugador()) {
            if (p.getFila() == fila && p.getColumna() == col) {
                return true;
            }
        }
        return false;
    }

    /**
     * Invierte la dirección actual del troll.
     */
    private void invertirDireccion() {
        switch (direccionActual) {
            case DERECHA -> direccionActual = Direccion.IZQUIERDA;
            case IZQUIERDA -> direccionActual = Direccion.DERECHA;
            case ARRIBA -> direccionActual = Direccion.ABAJO;
            case ABAJO -> direccionActual = Direccion.ARRIBA;
        }
    }
}
