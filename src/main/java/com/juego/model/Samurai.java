package com.juego.model;

import java.util.Random;

public class Samurai extends Personaje {
    public Samurai(String nombre) {
        super(nombre, 120, 10, 25);
    }

    @Override
    public void atacar(Personaje oponente) {
        Random rand = new Random();
        int dano = rand.nextInt((MAX_DANO - MIN_DANO) + 1) + MIN_DANO;
        oponente.recibirDano(dano);
        System.out.println("[Samurai] " + getNombre() + " ejecuta un corte de katana sobre "
                + oponente.getNombre() + " causando " + dano + " puntos de dano.");
    }
}