package com.juego.model;

import java.util.Random;

public class Invocador extends Personaje {
    public Invocador(String nombre) {
        super(nombre, 70, 20, 40);
    }

    @Override
    public void atacar(Personaje oponente) {
        Random rand = new Random();
        int dano = rand.nextInt((MAX_DANO - MIN_DANO) + 1) + MIN_DANO;
        oponente.recibirDano(dano);
        System.out.println("[Invocador] " + getNombre() + " lanza un hechizo devastador sobre "
                + oponente.getNombre() + " causando " + dano + " puntos de dano.");
    }
}