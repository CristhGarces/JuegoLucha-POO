package com.juego.patrones.strategy;

import com.juego.model.Personaje;
import java.util.Random;

public class AtaqueNormal implements EstrategiaAtaque {
    @Override
    public void ejecutarAtaque(Personaje atacante, Personaje oponente) {
        Random rand = new Random();
        int dano = rand.nextInt((atacante.getMaxDano() - atacante.getMinDano()) + 1) + atacante.getMinDano();
        oponente.recibirDano(dano);
        System.out.println("[Normal] " + atacante.getNombre() + " ataca a "
                + oponente.getNombre() + " causando " + dano + " puntos de dano.");
    }
}