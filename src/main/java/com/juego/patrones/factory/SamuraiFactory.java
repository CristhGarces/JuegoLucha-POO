package com.juego.patrones.factory;

import com.juego.model.Samurai;
import com.juego.model.Personaje;

public class SamuraiFactory extends PersonajeFactory {
    @Override
    public Personaje crearPersonaje(String nombre) {
        return new Samurai(nombre);
    }
}