package com.juego.patrones.strategy;

import com.juego.model.Personaje;
import java.util.Random;

public class AtaqueDefensivo implements EstrategiaAtaque {
    @Override
    public void ejecutarAtaque(Personaje atacante, Personaje oponente) {
        Random rand = new Random();
        int danoBase = rand.nextInt((atacante.getMaxDano() - atacante.getMinDano()) + 1) + atacante.getMinDano();
        int danoReducido = (int) (danoBase * 0.5);
        oponente.recibirDano(danoReducido);
        System.out.println("[Defensivo] " + atacante.getNombre() + " ataca cautelosamente a "
                + oponente.getNombre() + " causando " + danoReducido + " puntos de dano.");
    }
}