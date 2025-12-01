package Test;

import Dominio.Platano;
import Dominio.Posicion;
import Dominio.Uva;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Pruebas unitarias para las clases de frutas Uva y Platano.
 * Verifican la posición, el estado de recolección y los puntos otorgados.
 * Autores: Santiago Andres Gomez Rojas y Miguel Angel Sandoval
 */
public class FrutaTest {

    /**
     * Verifica que una uva se cree sin recolectar y con 50 puntos.
     */
    @Test
    public void uvaDebeInicializarseSinRecolectarYCon50Puntos() {
        Uva uva = new Uva(new Posicion(2, 3));

        assertFalse(uva.estaRecolectada());
        assertEquals(50, uva.getPuntos());
    }

    /**
     * Verifica que un plátano se cree sin recolectar y con 100 puntos.
     */
    @Test
    public void platanoDebeInicializarseSinRecolectarYCon100Puntos() {
        Platano platano = new Platano(new Posicion(4, 5));

        assertFalse(platano.estaRecolectada());
        assertEquals(100, platano.getPuntos());
    }

    /**
     * Verifica que al llamar a {@code recolectar} la fruta queda marcada
     * como recolectada.
     */
    @Test
    public void recolectarDebeMarcarFrutaComoRecolectada() {
        Uva uva = new Uva(new Posicion(1, 1));
        assertFalse(uva.estaRecolectada());
        uva.recolectar();
        assertTrue(uva.estaRecolectada());
    }
}
