package Test;

import Dominio.Direccion;
import Dominio.Helado;
import Dominio.Posicion;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Pruebas unitarias para la clase Helado.
 * Evalúan el movimiento dentro de los límites y la gestión del sabor.
 * Autores: Santiago Andres Gomez Rojas y Miguel Angel Sandoval
 */
public class HeladoTest {

    /**
     * Verifica que el helado se inicialice con posición, sabor
     * y dirección hacia abajo por defecto.
     */
    @Test
    public void constructorDebeInicializarCamposBasicos() {
        Posicion inicial = new Posicion(1, 1);
        Helado h = new Helado(inicial, "vainilla");

        assertEquals(inicial, h.getPosicion());
        assertEquals("vainilla", h.getSabor());
        assertEquals(Direccion.ABAJO, h.getDireccion());
    }

    /**
     * Verifica que el método {@code mover} actualice la posición
     * y la dirección cuando el movimiento es válido.
     */
    @Test
    public void moverDebeActualizarPosicionYDireccionDentroDeLimites() {
        Helado h = new Helado(new Posicion(5, 5), "fresa");

        h.mover(Direccion.ARRIBA, 12, 14);
        assertEquals(4, h.getPosicion().getFila());
        assertEquals(5, h.getPosicion().getColumna());
        assertEquals(Direccion.ARRIBA, h.getDireccion());

        h.mover(Direccion.DERECHA, 12, 14);
        assertEquals(4, h.getPosicion().getFila());
        assertEquals(6, h.getPosicion().getColumna());
        assertEquals(Direccion.DERECHA, h.getDireccion());
    }

    /**
     * Verifica que el helado no salga de los límites dados por
     * filas y columnas máximas.
     */
    @Test
    public void moverNoDebeSalirDeLimites() {
        Helado h = new Helado(new Posicion(0, 0), "chocolate");

        h.mover(Direccion.ARRIBA, 12, 14);
        assertEquals(0, h.getPosicion().getFila());
        assertEquals(0, h.getPosicion().getColumna());

        h.mover(Direccion.IZQUIERDA, 12, 14);
        assertEquals(0, h.getPosicion().getFila());
        assertEquals(0, h.getPosicion().getColumna());
    }

    /**
     * Verifica que el sabor pueda actualizarse correctamente.
     */
    @Test
    public void setSaborDebeCambiarElSaborDelHelado() {
        Helado h = new Helado(new Posicion(1, 1), "vainilla");
        h.setSabor("fresa");
        assertEquals("fresa", h.getSabor());
    }
}
