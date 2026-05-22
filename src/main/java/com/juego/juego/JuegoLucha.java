package com.juego.juego;

import com.juego.model.Personaje;
import com.juego.patrones.factory.*;
import com.juego.patrones.decorator.*;
import com.juego.patrones.strategy.*;
import java.util.Scanner;

public class JuegoLucha {
    private Personaje jugador1;
    private Personaje jugador2;
    private EstrategiaAtaque estrategia;

    public JuegoLucha(Personaje jugador1, Personaje jugador2) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.estrategia = new AtaqueNormal();
    }

    public void setEstrategia(EstrategiaAtaque estrategia) {
        this.estrategia = estrategia;
    }

    public EstrategiaAtaque getEstrategia() {
        return this.estrategia;
    }

    public Personaje getJugador1() {
        return jugador1;
    }

    public Personaje getJugador2() {
        return jugador2;
    }

    public void iniciarPelea() {
        System.out.println("\n======================================");
        System.out.println("    COMIENZA LA BATALLA  ");
        System.out.println("  " + jugador1.getNombre()
                + " [" + jugador1.getTipo() + "] "
                + "vs " + jugador2.getNombre()
                + " [" + jugador2.getTipo() + "]");
        System.out.println("======================================\n");

        int ronda = 1;
        while (jugador1.estaVivo() && jugador2.estaVivo()) {
            System.out.println("--- Ronda " + ronda + " ---");
            turno(jugador1, jugador2);
            if (jugador2.estaVivo()) {
                turno(jugador2, jugador1);
            }
            ronda++;
            System.out.println();
        }

        System.out.println("======================================");
        if (jugador1.estaVivo()) {
            System.out.println(" !" + jugador1.getNombre() + " ha ganado la batalla!");
        } else {
            System.out.println(" !" + jugador2.getNombre() + " ha ganado la batalla!");
        }
        System.out.println("======================================");
    }

    private void turno(Personaje atacante, Personaje defensor) {
        System.out.println("Turno de " + atacante.getNombre()
                + " (HP: " + atacante.getPuntosDeVida() + ")"
                + " -> " + defensor.getNombre()
                + " (HP: " + defensor.getPuntosDeVida() + ")");
        estrategia.ejecutarAtaque(atacante, defensor);
        System.out.println(defensor.getNombre() + " queda con "
                + defensor.getPuntosDeVida() + " HP.");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== JUEGO DE LUCHA ANIME ===");
        System.out.println("Tipos disponibles: 1) Ninja  2) Samurai  3) Invocador");
        System.out.println();

        System.out.print("Nombre del jugador 1: ");
        String nombre1 = scanner.nextLine();
        System.out.print("Tipo del jugador 1 (1/2/3): ");
        int tipo1 = Integer.parseInt(scanner.nextLine());
        System.out.print("Habilidad jugador 1 - 1) Armadura  2) Furia  3) Ninguna: ");
        int habilidad1 = Integer.parseInt(scanner.nextLine());

        System.out.print("Nombre del jugador 2: ");
        String nombre2 = scanner.nextLine();
        System.out.print("Tipo del jugador 2 (1/2/3): ");
        int tipo2 = Integer.parseInt(scanner.nextLine());
        System.out.print("Habilidad jugador 2 - 1) Armadura  2) Furia  3) Ninguna: ");
        int habilidad2 = Integer.parseInt(scanner.nextLine());

        System.out.print("Estrategia de ataque - 1) Normal  2) Critico  3) Defensivo: ");
        int estrategiaOpcion = Integer.parseInt(scanner.nextLine());

        PersonajeFactory fabrica1 = obtenerFabrica(tipo1);
        PersonajeFactory fabrica2 = obtenerFabrica(tipo2);

        Personaje jugador1 = fabrica1.crearPersonaje(nombre1);
        Personaje jugador2 = fabrica2.crearPersonaje(nombre2);

        jugador1 = aplicarDecorator(jugador1, habilidad1);
        jugador2 = aplicarDecorator(jugador2, habilidad2);

        JuegoLucha juego = new JuegoLucha(jugador1, jugador2);
        juego.setEstrategia(obtenerEstrategia(estrategiaOpcion));

        System.out.println();
        juego.iniciarPelea();

        scanner.close();
    }

    private static PersonajeFactory obtenerFabrica(int tipo) {
        switch (tipo) {
            case 1: return new NinjaFactory();
            case 2: return new SamuraiFactory();
            case 3: return new InvocadorFactory();
            default:
                System.out.println("Tipo invalido, se asignara Ninja por defecto.");
                return new NinjaFactory();
        }
    }

    private static Personaje aplicarDecorator(Personaje personaje, int habilidad) {
        switch (habilidad) {
            case 1: return new ArmaduraDecorator(personaje);
            case 2: return new FuriaDecorator(personaje);
            default: return personaje;
        }
    }

    private static EstrategiaAtaque obtenerEstrategia(int opcion) {
        switch (opcion) {
            case 2: return new AtaqueCritico();
            case 3: return new AtaqueDefensivo();
            default: return new AtaqueNormal();
        }
    }
}