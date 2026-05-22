package com.juego.model;

import java.util.Random;

public abstract class Personaje {
    private String nombre;
    private int puntosDeVida;
    protected int MIN_DANO;
    protected int MAX_DANO;

    public Personaje(String nombre, int puntosDeVida, int minDano, int maxDano) {
        this.nombre = nombre;
        this.puntosDeVida = puntosDeVida;
        this.MIN_DANO = minDano;
        this.MAX_DANO = maxDano;
    }

    public void atacar(Personaje oponente) {
        Random rand = new Random();
        int dano = rand.nextInt((MAX_DANO - MIN_DANO) + 1) + MIN_DANO;
        oponente.recibirDano(dano);
        System.out.println(this.nombre + " ataca a " + oponente.getNombre()
                + " causando " + dano + " puntos de dano.");
    }

    public void recibirDano(int dano) {
        if (dano < 0) return;
        this.puntosDeVida -= dano;
        if (this.puntosDeVida < 0) {
            this.puntosDeVida = 0;
        }
    }

    public boolean estaVivo() {
        return this.puntosDeVida > 0;
    }

    public String getNombre() {
        return this.nombre;
    }

    public int getPuntosDeVida() {
        return this.puntosDeVida;
    }

    public String getTipo() {
        return getClass().getSimpleName();
    }

    public int getMinDano() {
        return this.MIN_DANO;
    }

    public int getMaxDano() {
        return this.MAX_DANO;
    }
}