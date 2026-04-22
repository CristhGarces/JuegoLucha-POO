# JuegoLucha-POO
juego de lucha en Java implementando patrones de diseño Factory Method y Decorator

# Juego de Lucha - Patrones de Diseño

Juego de lucha por turnos desarrollado en Java, implementando los patrones de diseño **Factory Method** (creacional) y **Decorator** (estructural).

## Descripcion

Dos jugadores eligen un personaje y una habilidad especial. Luego se enfrentan en una batalla por turnos hasta que uno quede sin puntos de vida.

## Patrones de Diseño Implementados

### Factory Method
Permite crear distintos tipos de personajes (Ninja, Samurai, Invocador) sin instanciarlos directamente en la clase JuegoLucha. Cada fabrica concreta decide que objeto crear.

### Decorator
Permite agregar habilidades especiales a un personaje en tiempo de ejecucion sin modificar su clase base:
- **Armadura:** reduce el dano recibido en un 20%
- **Furia:** aumenta el dano causado en un 20%

## Personajes

| Personaje | HP | Dano minimo | Dano maximo |
|---|---|---|---|
| Ninja | 80 | 15 | 35 |
| Samurai | 120 | 10 | 25 |
| Invocador | 70 | 20 | 40 |

## Como ejecutar

```bash
javac JuegoLucha.java
java JuegoLucha
```

## Estructura del proyecto

- `Personaje` - Clase base abstracta
- `Ninja`, `Samurai`, `Invocador` - Subclases (herencia)
- `PersonajeFactory` - Fabrica abstracta
- `NinjaFactory`, `SamuraiFactory`, `InvocadorFactory` - Fabricas concretas
- `PersonajeDecorator` - Decorator base abstracto
- `ArmaduraDecorator`, `FuriaDecorator` - Decorators concretos
- `JuegoLucha` - Clase principal con el metodo main

## Diagrama de Clases

```mermaid
classDiagram
    direction TB

    class Personaje {
        <<abstract>>
        -String nombre
        -int puntosDeVida
        +int MIN_DANO
        +int MAX_DANO
        +Personaje(nombre: String, hp: int, min: int, max: int)
        +atacar(oponente: Personaje) void
        +recibirDano(dano: int) void
        +estaVivo() boolean
        +getNombre() String
        +getPuntosDeVida() int
    }

    class Ninja {
        +Ninja(nombre: String)
        +atacar(oponente: Personaje) void
    }

    class Samurai {
        +Samurai(nombre: String)
        +atacar(oponente: Personaje) void
    }

    class Invocador {
        +Invocador(nombre: String)
        +atacar(oponente: Personaje) void
    }

    class PersonajeDecorator {
        <<abstract>>
        #Personaje personajeDecorado
        +PersonajeDecorator(personaje: Personaje)
        +atacar(oponente: Personaje) void
        +recibirDano(dano: int) void
        +estaVivo() boolean
        +getPuntosDeVida() int
    }

    class ArmaduraDecorator {
        +ArmaduraDecorator(personaje: Personaje)
        +recibirDano(dano: int) void
    }

    class FuriaDecorator {
        +FuriaDecorator(personaje: Personaje)
        +atacar(oponente: Personaje) void
    }

    class PersonajeFactory {
        <<abstract>>
        +crearPersonaje(nombre: String) Personaje
    }

    class NinjaFactory {
        +crearPersonaje(nombre: String) Personaje
    }

    class SamuraiFactory {
        +crearPersonaje(nombre: String) Personaje
    }

    class InvocadorFactory {
        +crearPersonaje(nombre: String) Personaje
    }

    class JuegoLucha {
        -Personaje jugador1
        -Personaje jugador2
        +JuegoLucha(jugador1: Personaje, jugador2: Personaje)
        +iniciarPelea() void
        -turno(atacante: Personaje, defensor: Personaje) void
        -obtenerFabrica(tipo: int) PersonajeFactory
        -aplicarDecorator(personaje: Personaje, habilidad: int) Personaje
    }

    Personaje <|-- Ninja
    Personaje <|-- Samurai
    Personaje <|-- Invocador
    Personaje <|-- PersonajeDecorator

    PersonajeDecorator <|-- ArmaduraDecorator
    PersonajeDecorator <|-- FuriaDecorator
    PersonajeDecorator o-- Personaje : envuelve

    PersonajeFactory <|-- NinjaFactory
    PersonajeFactory <|-- SamuraiFactory
    PersonajeFactory <|-- InvocadorFactory

    PersonajeFactory ..> Personaje : creates
    JuegoLucha --> Personaje : gestiona
    JuegoLucha --> PersonajeFactory : usa
```

## Autores
Desarrollado como actividad academica para la materia de Programacion Orientada a Objetos.

- Cristhian Garces
- Jhojan Carabali
- Juan Pablo Vasquez
