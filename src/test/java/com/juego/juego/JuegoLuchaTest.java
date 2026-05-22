package com.juego.juego;

import com.juego.model.*;
import com.juego.patrones.strategy.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class JuegoLuchaTest {

    private Personaje jugador1;
    private Personaje jugador2;
    private JuegoLucha juego;

    @BeforeEach
    void setUp() {
        jugador1 = new Ninja("Ryu");
        jugador2 = new Samurai("Ken");
        juego = new JuegoLucha(jugador1, jugador2);
    }

    @Test
    @DisplayName("JuegoLucha debe inicializarse con dos jugadores")
    void testInicializacion() {
        assertNotNull(juego);
        assertEquals("Ryu", juego.getJugador1().getNombre());
        assertEquals("Ken", juego.getJugador2().getNombre());
    }

    @Test
    @DisplayName("Estrategia por defecto debe ser AtaqueNormal")
    void testEstrategiaDefecto() {
        assertNotNull(juego.getEstrategia());
        assertInstanceOf(AtaqueNormal.class, juego.getEstrategia());
    }

    @Test
    @DisplayName("Debe poder cambiar la estrategia de ataque")
    void testCambiarEstrategia() {
        juego.setEstrategia(new AtaqueCritico());
        assertInstanceOf(AtaqueCritico.class, juego.getEstrategia());

        juego.setEstrategia(new AtaqueDefensivo());
        assertInstanceOf(AtaqueDefensivo.class, juego.getEstrategia());
    }

    @Test
    @DisplayName("Al inicio ambos jugadores deben estar vivos")
    void testJugadoresVivosAlInicio() {
        assertTrue(juego.getJugador1().estaVivo());
        assertTrue(juego.getJugador2().estaVivo());
    }

    @Test
    @DisplayName("La pelea debe terminar con un ganador")
    void testPeleaTerminaConGanador() {
        juego.iniciarPelea();
        boolean hayGanador = juego.getJugador1().estaVivo() || juego.getJugador2().estaVivo();
        assertTrue(hayGanador);
    }

    @Test
    @DisplayName("JuegoLucha con estrategia critica debe funcionar")
    void testPeleaConAtaqueCritico() {
        juego.setEstrategia(new AtaqueCritico());
        assertDoesNotThrow(() -> juego.iniciarPelea());
    }

    @Test
    @DisplayName("JuegoLucha con estrategia defensiva debe funcionar")
    void testPeleaConAtaqueDefensivo() {
        juego.setEstrategia(new AtaqueDefensivo());
        assertDoesNotThrow(() -> juego.iniciarPelea());
    }
}