package com.juego.patrones.decorator;

import com.juego.model.Personaje;
import java.util.Random;

public class FuriaDecorator extends PersonajeDecorator {
    public FuriaDecorator(Personaje personaje) {
        super(personaje);
        System.out.println("[Furia] " + personaje.getNombre()
                + " ha activado furia. Causara 20% mas de dano.");
    }

    @Override
    public void atacar(Personaje oponente) {
        Random rand = new Random();
        int danoBase = rand.nextInt((MAX_DANO - MIN_DANO) + 1) + MIN_DANO;
        int danoFuria = (int) (danoBase * 1.2);
        oponente.recibirDano(danoFuria);
        System.out.println("[Furia] " + getNombre() + " ataca con furia a "
                + oponente.getNombre() + " causando " + danoFuria + " puntos de dano.");
    }
}