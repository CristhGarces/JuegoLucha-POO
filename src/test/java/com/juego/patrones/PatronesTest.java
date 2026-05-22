package com.juego.patrones;

import com.juego.model.*;
import com.juego.patrones.factory.*;
import com.juego.patrones.decorator.*;
import com.juego.patrones.strategy.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class PatronesTest {

    // ===== FACTORY =====

    @Test
    @DisplayName("NinjaFactory debe crear un Ninja")
    void testNinjaFactory() {
        PersonajeFactory factory = new NinjaFactory();
        Personaje p = factory.crearPersonaje("Ryu");
        assertNotNull(p);
        assertEquals("Ryu", p.getNombre());
        assertEquals(80, p.getPuntosDeVida());
        assertEquals("Ninja", p.getTipo());
    }

    @Test
    @DisplayName("SamuraiFactory debe crear un Samurai")
    void testSamuraiFactory() {
        PersonajeFactory factory = new SamuraiFactory();
        Personaje p = factory.crearPersonaje("Ken");
        assertNotNull(p);
        assertEquals(120, p.getPuntosDeVida());
        assertEquals("Samurai", p.getTipo());
    }

    @Test
    @DisplayName("InvocadorFactory debe crear un Invocador")
    void testInvocadorFactory() {
        PersonajeFactory factory = new InvocadorFactory();
        Personaje p = factory.crearPersonaje("Zed");
        assertNotNull(p);
        assertEquals(70, p.getPuntosDeVida());
        assertEquals("Invocador", p.getTipo());
    }

    // ===== DECORATOR =====

    @Test
    @DisplayName("ArmaduraDecorator debe reducir el dano en 20%")
    void testArmaduraDecorator() {
        Personaje ninja = new Ninja("Ryu");
        Personaje conArmadura = new ArmaduraDecorator(ninja);
        conArmadura.recibirDano(100);
        assertEquals(0, conArmadura.getPuntosDeVida());
        // 100 * 0.8 = 80, ninja tiene 80 HP exactos, queda en 0
    }

    @Test
    @DisplayName("ArmaduraDecorator debe mantener el nombre y tipo del personaje")
    void testArmaduraDecoratorNombreTipo() {
        Personaje samurai = new Samurai("Ken");
        Personaje conArmadura = new ArmaduraDecorator(samurai);
        assertEquals("Ken", conArmadura.getNombre());
        assertEquals("Samurai", conArmadura.getTipo());
    }

    @Test
    @DisplayName("FuriaDecorator debe mantener el personaje vivo inicialmente")
    void testFuriaDecoratorVivo() {
        Personaje ninja = new Ninja("Ryu");
        Personaje conFuria = new FuriaDecorator(ninja);
        assertTrue(conFuria.estaVivo());
        assertEquals(80, conFuria.getPuntosDeVida());
    }

    @Test
    @DisplayName("FuriaDecorator debe mantener nombre y tipo del personaje")
    void testFuriaDecoratorNombreTipo() {
        Personaje invocador = new Invocador("Zed");
        Personaje conFuria = new FuriaDecorator(invocador);
        assertEquals("Zed", conFuria.getNombre());
        assertEquals("Invocador", conFuria.getTipo());
    }

    // ===== STRATEGY =====

    @Test
    @DisplayName("AtaqueNormal debe causar dano al oponente")
    void testAtaqueNormal() {
        Personaje atacante = new Ninja("Ryu");
        Personaje defensor = new Samurai("Ken");
        EstrategiaAtaque estrategia = new AtaqueNormal();
        int vidaAntes = defensor.getPuntosDeVida();
        estrategia.ejecutarAtaque(atacante, defensor);
        assertTrue(defensor.getPuntosDeVida() < vidaAntes);
    }

    @Test
    @DisplayName("AtaqueCritico debe causar mas dano que el minimo normal")
    void testAtaqueCritico() {
        Personaje atacante = new Samurai("Ken");
        Personaje defensor = new Ninja("Ryu");
        EstrategiaAtaque estrategia = new AtaqueCritico();
        int vidaAntes = defensor.getPuntosDeVida();
        estrategia.ejecutarAtaque(atacante, defensor);
        int dano = vidaAntes - defensor.getPuntosDeVida();
        assertTrue(dano >= (int)(atacante.getMinDano() * 1.5));
    }

    @Test
    @DisplayName("AtaqueDefensivo debe causar menos dano que el minimo normal")
    void testAtaqueDefensivo() {
        Personaje atacante = new Samurai("Ken");
        Personaje defensor = new Ninja("Ryu");
        EstrategiaAtaque estrategia = new AtaqueDefensivo();
        int vidaAntes = defensor.getPuntosDeVida();
        estrategia.ejecutarAtaque(atacante, defensor);
        int dano = vidaAntes - defensor.getPuntosDeVida();
        assertTrue(dano <= atacante.getMaxDano());
    }
}