package org.example;

import org.example.movimientos.Movimiento;
import java.util.ArrayList;
import java.util.Scanner;

public class Battle {
    private Persona jugador1;
    private Persona jugador2;
    private Pokemon pokemonActual1;
    private Pokemon pokemonActual2;
    private boolean battleFinished;

    public Battle(Persona jugador1, Persona jugador2) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.battleFinished = false;
    }

    public void iniciarBattle() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║        ¡COMIENZA LA BATALLA!           ║");
        System.out.println("║ " + jugador1.getNombre() + " vs " + jugador2.getNombre());
        System.out.println("╚════════════════════════════════════════╝\n");

        
        pokemonActual1 = jugador1.getListaPokemon().get(0);
        pokemonActual2 = jugador2.getListaPokemon().get(0);

        System.out.println(jugador1.getNombre() + " envía a " + pokemonActual1.getNombre() + "!");
        System.out.println(jugador2.getNombre() + " envía a " + pokemonActual2.getNombre() + "!\n");

        
        int turno = 1;
        while (!battleFinished && !pokemonActual1.estaDebilitado() && !pokemonActual2.estaDebilitado()) {
            System.out.println("\n═══════════════════════════════════════");
            System.out.println("TURNO " + turno);
            System.out.println("═══════════════════════════════════════");

            ejecutarTurno();
            turno++;

            
            if (pokemonActual1.estaDebilitado() || pokemonActual2.estaDebilitado()) {
                terminarBattle();
                break;
            }
        }
    }

    private void ejecutarTurno() {
        Scanner scanner = new Scanner(System.in);

        // Resetear protección del turno anterior
        pokemonActual1.resetProtection();
        pokemonActual2.resetProtection();

        mostrarEstadoBattle();

        System.out.println("\n--- TURNO DE " + jugador1.getNombre() + " ---");
        Movimiento movimiento1 = seleccionarMovimiento(scanner, pokemonActual1, jugador1.getNombre());

        System.out.println("\n--- TURNO DE " + jugador2.getNombre() + " ---");
        Movimiento movimiento2 = seleccionarMovimiento(scanner, pokemonActual2, jugador2.getNombre());

        boolean jugador1Ataca1 = (pokemonActual1.getSpeed() + movimiento1.getPrioridad() * 100) >= (pokemonActual2.getSpeed() + movimiento2.getPrioridad() * 100);

        if (jugador1Ataca1) {
            atacar(pokemonActual1, pokemonActual2, movimiento1);
            if (!pokemonActual2.estaDebilitado()) {
                atacar(pokemonActual2, pokemonActual1, movimiento2);
            }
        } else {
            atacar(pokemonActual2, pokemonActual1, movimiento2);
            if (!pokemonActual1.estaDebilitado()) {
                atacar(pokemonActual1, pokemonActual2, movimiento1);
            }
        }
        
        // Mostrar estado actualizado después de los ataques
        mostrarEstadoBattle();
    }

    private void atacar(Pokemon atacante, Pokemon defensor, Movimiento movimiento) {
        // Mostrar el movimiento usado
        if (movimiento.getCategoria() == Categoria.ESTADO) {
            System.out.println("\n" + atacante.getNombre() + " usa " + movimiento.getNombre() + "!");
        }
        
        // Verificar si el defensor está protegido
        if (defensor.isProtegido() && movimiento.getCategoria() != Categoria.ESTADO) {
            System.out.println(defensor.getNombre() + " está protegido y evitó el ataque!");
            return;
        }

        // El efecto del movimiento se encarga del daño
        movimiento.efecto(atacante, defensor);
        
        // Solo mostrar PS si es un movimiento que hace daño (no de estado)
        if (movimiento.getCategoria() != Categoria.ESTADO) {
            int psActuales = Math.max(0, defensor.getModPs());
            System.out.println(defensor.getNombre() + " ahora tiene " + psActuales + " PS");
        }
    }

    private Movimiento seleccionarMovimiento(Scanner scanner, Pokemon pokemon, String nombreJugador) {
        ArrayList<Movimiento> movimientos = pokemon.getMovimientos();

        System.out.println(nombreJugador + ", elige un movimiento para " + pokemon.getNombre() + ":");
        for (int i = 0; i < movimientos.size(); i++) {
            Movimiento m = movimientos.get(i);
            System.out.println((i + 1) + ". " + m.getNombre() + " (Potencia: " + m.getPotencia() + ", Precisión: " + m.getPrecision() + "%)");
        }

        int opcion = scanner.nextInt();
        scanner.nextLine();

        if (opcion < 1 || opcion > movimientos.size()) {
            System.out.println("Opción inválida. Seleccionando primer movimiento...");
            return movimientos.get(0);
        }

        return movimientos.get(opcion - 1);
    }

    private void mostrarEstadoBattle() {
        String nombre1 = pokemonActual1.getNombre();
        String nombre2 = pokemonActual2.getNombre() + " (rival)";
        int ps1 = Math.max(0, pokemonActual1.getModPs());
        int ps2 = Math.max(0, pokemonActual2.getModPs());
        
        System.out.println("\n┌─────────────────────────────────────────┐");
        System.out.println("│ " + String.format("%-18s | %-18s", nombre1, nombre2));
        System.out.println("│ " + String.format("PS: %-14d | PS: %-14d", ps1, ps2));
        System.out.println("│ " + String.format("Nivel: %-10d | Nivel: %-10d", pokemonActual1.getNivel(), pokemonActual2.getNivel()));
        System.out.println("│ Estado: " + String.format("%-9s | Estado: %-8s", pokemonActual1.getEstado(), pokemonActual2.getEstado()));
        System.out.println("└─────────────────────────────────────────┘");
    }

    private void terminarBattle() {
        battleFinished = true;
        
        System.out.println("\n╔════════════════════════════════════════╗");
        if (pokemonActual1.estaDebilitado()) {
            System.out.println("║        ¡" + pokemonActual1.getNombre() + " ha sido derrotado!        ║");
            System.out.println("║ ¡" + jugador2.getNombre() + " ha ganado la batalla!");
        } else {
            System.out.println("║        ¡" + pokemonActual2.getNombre() + " ha sido derrotado!        ║");
            System.out.println("║ ¡" + jugador1.getNombre() + " ha ganado la batalla!");
        }
        System.out.println("╚════════════════════════════════════════╝\n");
    }

    public boolean isBattleFinished() {
        return battleFinished;
    }
}
