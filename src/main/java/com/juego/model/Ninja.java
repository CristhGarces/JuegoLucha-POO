package com.juego.model;

import java.util.Random;

public class Ninja extends Personaje {
    public Ninja(String nombre) {
        super(nombre, 80, 15, 35);
    }

    @Override
    public void atacar(Personaje oponente) {
        Random rand = new Random();
        int dano = rand.nextInt((MAX_DANO - MIN_DANO) + 1) + MIN_DANO;
        oponente.recibirDano(dano);
        System.out.println("[Ninja] " + getNombre() + " lanza un ataque furtivo sobre "
                + oponente.getNombre() + " causando " + dano + " puntos de dano.");
    }
}