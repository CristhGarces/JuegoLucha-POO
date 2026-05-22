package com.juego.patrones.factory;

import com.juego.model.Ninja;
import com.juego.model.Personaje;

public class NinjaFactory extends PersonajeFactory {
    @Override
    public Personaje crearPersonaje(String nombre) {
        return new Ninja(nombre);
    }
}