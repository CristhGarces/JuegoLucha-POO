package com.juego.patrones.strategy;

import com.juego.model.Personaje;
import java.util.Random;

public class AtaqueCritico implements EstrategiaAtaque {
    @Override
    public void ejecutarAtaque(Personaje atacante, Personaje oponente) {
        Random rand = new Random();
        int danoBase = rand.nextInt((atacante.getMaxDano() - atacante.getMinDano()) + 1) + atacante.getMinDano();
        int danoCritico = (int) (danoBase * 1.5);
        oponente.recibirDano(danoCritico);
        System.out.println("[CRITICO] " + atacante.getNombre() + " ejecuta un golpe critico a "
                + oponente.getNombre() + " causando " + danoCritico + " puntos de dano.");
    }
}