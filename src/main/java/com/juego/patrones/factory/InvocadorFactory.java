package com.juego.patrones.factory;

import com.juego.model.Invocador;
import com.juego.model.Personaje;

public class InvocadorFactory extends PersonajeFactory {
    @Override
    public Personaje crearPersonaje(String nombre) {
        return new Invocador(nombre);
    }
}