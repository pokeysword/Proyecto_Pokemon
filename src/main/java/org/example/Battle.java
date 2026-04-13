package org.example;

import org.example.movimientos.Movimiento;
import java.util.ArrayList;
import java.util.Scanner;

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
            } else {
                cambiarPokemon(jugador2);
            }
        } else {
            atacar(pokemonActual2, pokemonActual1, movimiento2);
            if (!pokemonActual1.estaDebilitado()) {
                atacar(pokemonActual1, pokemonActual2, movimiento1);
            } else {
                cambiarPokemon(jugador1);
            }
        }
    }

    private void cambiarPokemon(Persona persona) {
        ArrayList<Pokemon> equipo = persona.getListaPokemon();
        int indiceActual = (persona == jugador1) ? indicePokemon1 : indicePokemon2;
        
        // Buscar el siguiente pokémon no debilitado
        for (int i = indiceActual + 1; i < equipo.size(); i++) {
            if (!equipo.get(i).estaDebilitado()) {
                System.out.println("\n" + persona.getNombre() + " envía a " + equipo.get(i).getNombre() + "!");
                
                if (persona == jugador1) {
                    pokemonActual1 = equipo.get(i);
                    indicePokemon1 = i;
                } else {
                    pokemonActual2 = equipo.get(i);
                    indicePokemon2 = i;
                }
                return;
            }
        }
    }

    private void atacar(Pokemon atacante, Pokemon defensor, Movimiento movimiento) {
        System.out.println("\n" + atacante.getNombre() + " usa " + movimiento.getNombre() + "!");

        // Verificar si el defensor está protegido
        if (defensor.isProtegido() && movimiento.getCategoria() != Categoria.ESTADO) {
            System.out.println(defensor.getNombre() + " está protegido y evitó el ataque!");
            return;
        }

        int daño = DamageCalculator.calculateDamage(atacante, defensor, movimiento);

        if (daño > 0) {
            DamageCalculator.applyDamage(defensor, daño, atacante, movimiento);
        }

        movimiento.efecto(atacante, defensor);
        System.out.println(defensor.getNombre() + " ahora tiene " + defensor.getModPs() + " PS");
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
        System.out.println("\n┌─────────────────────────────────────────┐");
        System.out.println("│ " + String.format("%-18s | %-18s", pokemonActual1.getNombre(), pokemonActual2.getNombre()));
        System.out.println("│ " + String.format("PS: %-14d | PS: %-14d", pokemonActual1.getModPs(), pokemonActual2.getModPs()));
        System.out.println("│ " + String.format("Nivel: %-10d | Nivel: %-10d", pokemonActual1.getNivel(), pokemonActual2.getNivel()));
        System.out.println("│ Estado: " + String.format("%-9s | Estado: %-8s", pokemonActual1.getEstado(), pokemonActual2.getEstado()));
        System.out.println("└─────────────────────────────────────────┘");
    }

    private void terminarBattle(Persona ganador) {
        battleFinished = true;
        
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║   ¡" + ganador.getNombre() + " ha ganado la batalla!    ║");
        System.out.println("╚════════════════════════════════════════╝\n");
    }

    public boolean isBattleFinished() {
        return battleFinished;
    }
}
