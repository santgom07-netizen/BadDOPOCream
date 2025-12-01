    package Test;
    
    import Dominio.*;
    import org.junit.Test;
    import static org.junit.Assert.*;
    
    /**
     * Pruebas unitarias para la clase Juego.
     * Evalúan el comportamiento de pausa, avance de tick
     * y el reinicio de nivel.
     * Autores: Santiago Andres Gomez Rojas y Miguel Angel Sandoval
     */
    public class JuegoTest {
    
        /**
         * Verifica que el juego alterne correctamente entre estados
         * de pausa y ejecución.
         */
        @Test
        public void alternarPausaDebeCambiarEstadoDePausa() {
            Juego juego = new Juego("vainilla", ModalidadJuego.PvsM);
            assertFalse(juego.isPausado());
    
            juego.alternarPausa();
            assertTrue(juego.isPausado());
    
            juego.alternarPausa();
            assertFalse(juego.isPausado());
        }
    
        /**
         * Verifica que cuando el juego está en pausa, las llamadas
         * a {@code tick} no actualicen el estado del nivel.
         */
        @Test
        public void tickNoDebeActualizarNivelCuandoEstaPausado() {
            Juego juego = new Juego("fresa", ModalidadJuego.PvsM);
            Nivel nivel = juego.getNivel();
            int tiempoInicial = nivel.getTiempoRestanteSegundos();
    
            juego.alternarPausa();
            juego.tick();
    
            assertEquals(tiempoInicial, nivel.getTiempoRestanteSegundos());
        }
    
        /**
         * Verifica que al reiniciar el nivel se cree una nueva instancia
         * de {@link Nivel} con el tiempo reiniciado.
         */
        @Test
        public void reiniciarNivelDebeCrearNuevoNivelYReiniciarTiempo() {
            Juego juego = new Juego("chocolate", ModalidadJuego.PvsM);
            Nivel nivelOriginal = juego.getNivel();
    
            juego.tick();
            assertTrue(nivelOriginal.getTiempoRestanteSegundos() < 3 * 60);
    
            juego.reiniciarNivel();
            Nivel nuevoNivel = juego.getNivel();
    
            assertNotSame(nivelOriginal, nuevoNivel);
            assertEquals(3 * 60, nuevoNivel.getTiempoRestanteSegundos());
        }
    
        /**
         * Verifica que los métodos de movimiento deleguen la acción
         * al nivel cuando el juego no está en pausa.
         */
        @Test
        public void moverHeladoJugadorDebeDelegarEnNivelCuandoNoEstaPausado() {
            Juego juego = new Juego("vainilla", ModalidadJuego.PvsM);
            Nivel nivel = juego.getNivel();
    
            int filaInicial = nivel.getHelado().getPosicion().getFila();
            juego.moverHeladoJugador(Direccion.ABAJO);
    
            assertNotEquals(filaInicial, nivel.getHelado().getPosicion().getFila());
        }
    }
