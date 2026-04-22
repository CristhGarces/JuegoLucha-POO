import java.util.Random;
import java.util.Scanner;


// CLASE BASE ABSTRACTA

abstract class Personaje {
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
}


// SUBCLASES (HERENCIAS)


// Ninja: rapido y agresivo, pero fragil (80 HP, dano 15-35)
class Ninja extends Personaje {
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

// Samurai: resistente y equilibrado (120 HP, dano 10-25)
class Samurai extends Personaje {
    public Samurai(String nombre) {
        super(nombre, 120, 10, 25);
    }

    @Override
    public void atacar(Personaje oponente) {
        Random rand = new Random();
        int dano = rand.nextInt((MAX_DANO - MIN_DANO) + 1) + MIN_DANO;
        oponente.recibirDano(dano);
        System.out.println("[Samurai] " + getNombre() + " ejecuta un corte de katana sobre "
                + oponente.getNombre() + " causando " + dano + " puntos de dano.");
    }
}

// Invocador: fragil pero muy poderoso (70 HP, dano 20-40)
class Invocador extends Personaje {
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


// PATRON DECORATOR


// Decorator base: envuelve un Personaje y hereda de el
abstract class PersonajeDecorator extends Personaje {
    protected Personaje personajeDecorado;

    public PersonajeDecorator(Personaje personaje) {
        super(personaje.getNombre(), personaje.getPuntosDeVida(), personaje.MIN_DANO, personaje.MAX_DANO);
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

// Armadura: reduce el dano recibido en un 20%
class ArmaduraDecorator extends PersonajeDecorator {
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

// Furia: aumenta el dano causado en un 20%
class FuriaDecorator extends PersonajeDecorator {
    public FuriaDecorator(Personaje personaje) {
        super(personaje);
        System.out.println("[Furia] " + personaje.getNombre()
                + " ha activado furia. Causara 20% mas de dano.");
    }

    @Override
    public void atacar(Personaje oponente) {
        Random rand = new Random();
        int danoBase = rand.nextInt((MAX_DANO - MIN_DANO) + 1) + MIN_DANO;
        int danoFuria = (int) (danoBase * 1.2);
        oponente.recibirDano(danoFuria);
        System.out.println("[Furia] " + getNombre() + " ataca con furia a "
                + oponente.getNombre() + " causando " + danoFuria + " puntos de dano.");
    }
}


// FACTORY METHOD — Fabrica abstracta

abstract class PersonajeFactory {
    public abstract Personaje crearPersonaje(String nombre);
}


// FABRICAS

class NinjaFactory extends PersonajeFactory {
    @Override
    public Personaje crearPersonaje(String nombre) {
        return new Ninja(nombre);
    }
}

class SamuraiFactory extends PersonajeFactory {
    @Override
    public Personaje crearPersonaje(String nombre) {
        return new Samurai(nombre);
    }
}

class InvocadorFactory extends PersonajeFactory {
    @Override
    public Personaje crearPersonaje(String nombre) {
        return new Invocador(nombre);
    }
}


// CLASE JUEGO — usa las fabricas, no instancia personajes directo

class JuegoLucha {
    private Personaje jugador1;
    private Personaje jugador2;

    public JuegoLucha(Personaje jugador1, Personaje jugador2) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
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
        atacante.atacar(defensor);
        System.out.println(defensor.getNombre() + " queda con "
                + defensor.getPuntosDeVida() + " HP.");
    }


    // MAIN
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== JUEGO DE LUCHA ANIME ===");
        System.out.println("Tipos disponibles: 1) Ninja  2) Samurai  3) Invocador");
        System.out.println();

        // Jugador 1
        System.out.print("Nombre del jugador 1: ");
        String nombre1 = scanner.nextLine();
        System.out.print("Tipo del jugador 1 (1/2/3): ");
        int tipo1 = Integer.parseInt(scanner.nextLine());
        System.out.print("Habilidad jugador 1 - 1) Armadura  2) Furia  3) Ninguna: ");
        int habilidad1 = Integer.parseInt(scanner.nextLine());

        // Jugador 2
        System.out.print("Nombre del jugador 2: ");
        String nombre2 = scanner.nextLine();
        System.out.print("Tipo del jugador 2 (1/2/3): ");
        int tipo2 = Integer.parseInt(scanner.nextLine());
        System.out.print("Habilidad jugador 2 - 1) Armadura  2) Furia  3) Ninguna: ");
        int habilidad2 = Integer.parseInt(scanner.nextLine());

        // Crear personajes con las fabricas
        PersonajeFactory fabrica1 = obtenerFabrica(tipo1);
        PersonajeFactory fabrica2 = obtenerFabrica(tipo2);

        Personaje jugador1 = fabrica1.crearPersonaje(nombre1);
        Personaje jugador2 = fabrica2.crearPersonaje(nombre2);

        // Aplicar decorator segun la habilidad elegida
        jugador1 = aplicarDecorator(jugador1, habilidad1);
        jugador2 = aplicarDecorator(jugador2, habilidad2);

        System.out.println();

        JuegoLucha juego = new JuegoLucha(jugador1, jugador2);
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
}
