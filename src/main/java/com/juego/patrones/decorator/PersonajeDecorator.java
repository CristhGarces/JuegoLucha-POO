package com.juego.patrones.decorator;

import com.juego.model.Personaje;

public abstract class PersonajeDecorator extends Personaje {
    protected Personaje personajeDecorado;

    public PersonajeDecorator(Personaje personaje) {
        super(personaje.getNombre(), personaje.getPuntosDeVida(), personaje.getMinDano(), personaje.getMaxDano());
        this.personajeDecorado = personaje;
    }

    @Override
    public void atacar(Personaje oponente) {
        personajeDecorado.atacar(oponente);
    }

    @Override
    public void recibirDano(int dano) {
        personajeDecorado.recibirDano(dano);
    }

    @Override
    public boolean estaVivo() {
        return personajeDecorado.estaVivo();
    }

    @Override
    public int getPuntosDeVida() {
        return personajeDecorado.getPuntosDeVida();
    }

    @Override
    public String getTipo() {
        return personajeDecorado.getTipo();
    }
}