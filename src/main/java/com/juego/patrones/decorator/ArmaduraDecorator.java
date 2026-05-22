package com.juego.patrones.decorator;

import com.juego.model.Personaje;

public class ArmaduraDecorator extends PersonajeDecorator {
    public ArmaduraDecorator(Personaje personaje) {
        super(personaje);
        System.out.println("[Armadura] " + personaje.getNombre()
                + " ha equipado armadura. Recibira 20% menos de dano.");
    }

    @Override
    public void recibirDano(int dano) {
        int danoReducido = (int) (dano * 0.8);
        System.out.println("[Armadura] El dano fue reducido de " + dano
                + " a " + danoReducido + " puntos.");
        personajeDecorado.recibirDano(danoReducido);
    }
}