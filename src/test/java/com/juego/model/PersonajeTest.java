package com.juego.model;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class PersonajeTest {

    private Personaje ninja;
    private Personaje samurai;

    @BeforeEach
    void setUp() {
        ninja = new Ninja("Ryu");
        samurai = new Samurai("Ken");
    }

    @Test
    @DisplayName("Ninja debe crearse con 80 HP")
    void testCreacionNinja() {
        assertEquals("Ryu", ninja.getNombre());
        assertEquals(80, ninja.getPuntosDeVida());
        assertTrue(ninja.estaVivo());
    }

    @Test
    @DisplayName("Samurai debe crearse con 120 HP")
    void testCreacionSamurai() {
        assertEquals("Ken", samurai.getNombre());
        assertEquals(120, samurai.getPuntosDeVida());
        assertTrue(samurai.estaVivo());
    }

    @Test
    @DisplayName("Invocador debe crearse con 70 HP")
    void testCreacionInvocador() {
        Personaje invocador = new Invocador("Zed");
        assertEquals("Zed", invocador.getNombre());
        assertEquals(70, invocador.getPuntosDeVida());
        assertTrue(invocador.estaVivo());
    }

    @Test
    @DisplayName("Debe reducir HP al recibir dano")
    void testRecibirDano() {
        ninja.recibirDano(30);
        assertEquals(50, ninja.getPuntosDeVida());
    }

    @Test
    @DisplayName("HP no debe ser negativo")
    void testHpNoNegativo() {
        ninja.recibirDano(200);
        assertEquals(0, ninja.getPuntosDeVida());
        assertFalse(ninja.estaVivo());
    }

    @Test
    @DisplayName("Dano negativo no debe afectar HP")
    void testDanoNegativoIgnorado() {
        ninja.recibirDano(-10);
        assertEquals(80, ninja.getPuntosDeVida());
    }

    @Test
    @DisplayName("Personaje muere con exactamente su HP en dano")
    void testMuerteExacta() {
        ninja.recibirDano(80);
        assertEquals(0, ninja.getPuntosDeVida());
        assertFalse(ninja.estaVivo());
    }

    @Test
    @DisplayName("Ataque de Ninja debe causar dano entre 15 y 35")
    void testRangoAtaqueNinja() {
        for (int i = 0; i < 20; i++) {
            Personaje objetivo = new Samurai("objetivo");
            int vidaAntes = objetivo.getPuntosDeVida();
            ninja.atacar(objetivo);
            int dano = vidaAntes - objetivo.getPuntosDeVida();
            assertTrue(dano >= 15 && dano <= 35,
                "El dano del Ninja debe estar entre 15 y 35, fue: " + dano);
        }
    }

    @Test
    @DisplayName("getTipo debe retornar el nombre de la clase")
    void testGetTipo() {
        assertEquals("Ninja", ninja.getTipo());
        assertEquals("Samurai", samurai.getTipo());
    }

    @Test
    @DisplayName("getMinDano y getMaxDano deben retornar valores correctos")
    void testGettersMinMaxDano() {
        assertEquals(15, ninja.getMinDano());
        assertEquals(35, ninja.getMaxDano());
        assertEquals(10, samurai.getMinDano());
        assertEquals(25, samurai.getMaxDano());
    }
}