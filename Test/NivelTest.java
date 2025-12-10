package Test;

import Dominio.*;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Pruebas unitarias para la clase Nivel.
 * Evalúan la recolección de frutas, el cálculo de puntaje,
 * la condición de victoria y la pérdida por tiempo.
 * Autores: Santiago Andres Gomez Rojas y Miguel Angel Sandoval 
 */
public class NivelTest {

    /**
     * Verifica que al moverse sobre una fruta se marque como recolectada
     * y se sumen los puntos correspondientes al puntaje del nivel.
     */
    @Test
    public void moverHeladoDebeRecolectarFrutasYSumarPuntos() {
        Nivel nivel = new Nivel("vainilla", ModalidadJuego.PvsM);

        int puntajeInicial = nivel.getPuntajeActual();
        assertEquals(0, puntajeInicial);

        nivel.getHelado().getPosicion().setFila(1);
        nivel.getHelado().getPosicion().setColumna(1);
        nivel.moverHeladoJugador(Direccion.IZQUIERDA);

        boolean algunaRecolectada = false;
        for (Fruta f : nivel.getFrutas()) {
            if (f.estaRecolectada()
                    && f.getPosicion().mismaPosicion(new Posicion(1, 2))) {
                algunaRecolectada = true;
                break;
            }
        }
        assertTrue(algunaRecolectada);

        assertTrue(nivel.getPuntajeActual() > 0);
    }

    /**
     * Verifica que la condición de victoria se cumpla cuando
     * se recolectan todas las uvas y plátanos requeridos.
     */
    @Test
    public void nivelDebeMarcarseGanadoCuandoSeRecolectanTodasLasFrutas() {
        Nivel nivel = new Nivel("fresa", ModalidadJuego.PvsM);

        for (Fruta f : nivel.getFrutas()) {
            f.recolectar();
        }

        nivel.moverHeladoJugador(Direccion.ABAJO);

        assertTrue(nivel.isGanado());
    }

    /**
     * Verifica que el tiempo se descuente en cada tick y que,
     * al llegar a cero sin haber ganado, el nivel se marque como perdido.
     */
    @Test
    public void nivelDebePerderseCuandoSeAgotaElTiempo() {
        Nivel nivel = new Nivel("chocolate", ModalidadJuego.PvsM);

        int pasos = nivel.getTiempoRestanteSegundos() + 1;
        for (int i = 0; i < pasos; i++) {
            nivel.actualizarUnTick();
        }

        assertTrue(nivel.isPerdido());
        assertTrue(nivel.getTiempoRestanteSegundos() <= 0);
    }
}
