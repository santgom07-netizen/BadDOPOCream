package Test;

import Dominio.Posicion;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Pruebas unitarias para la clase link Posicion.
 * Verifican la correcta gestión de coordenadas y la comparación de posiciones.
 * Autores: Santiago Andres Gomez Rojas y Miguel Angel Sandoval
 */
public class PosicionTest {

    /**
     * Verifica que el constructor y los getters inicialicen
     * correctamente la fila y la columna.
     */
    @Test
    public void debeCrearPosicionConFilaYColumnaIndicadas() {
        Posicion p = new Posicion(3, 5);
        assertEquals(3, p.getFila());
        assertEquals(5, p.getColumna());
    }

    /**
     * Verifica que los setters actualicen correctamente
     * la fila y la columna.
     */
    @Test
    public void debePermitirActualizarFilaYColumna() {
        Posicion p = new Posicion(0, 0);
        p.setFila(7);
        p.setColumna(9);
        assertEquals(7, p.getFila());
        assertEquals(9, p.getColumna());
    }

    /**
     * Verifica que dos posiciones con las mismas coordenadas
     * sean consideradas iguales por {@code mismaPosicion}.
     */
    @Test
    public void mismaPosicionDebeSerVerdaderoParaCoordenadasIguales() {
        Posicion p1 = new Posicion(2, 4);
        Posicion p2 = new Posicion(2, 4);
        assertTrue(p1.mismaPosicion(p2));
    }

    /**
     * Verifica que dos posiciones distintas no se consideren iguales.
     */
    @Test
    public void mismaPosicionDebeSerFalsoParaCoordenadasDiferentes() {
        Posicion p1 = new Posicion(2, 4);
        Posicion p2 = new Posicion(3, 4);
        assertFalse(p1.mismaPosicion(p2));
    }
}
