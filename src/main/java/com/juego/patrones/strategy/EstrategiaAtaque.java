package com.juego.patrones.strategy;

import com.juego.model.Personaje;

public interface EstrategiaAtaque {
    void ejecutarAtaque(Personaje atacante, Personaje oponente);
}