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
        // Validar que ambos jugadores tengan Pokémon
        if (jugador1.getListaPokemon().isEmpty() || jugador2.getListaPokemon().isEmpty()) {
            System.out.println("\n╔════════════════════════════════════════╗");
            System.out.println("║        ¡BATALLA NO VÁLIDA!             ║");
            System.out.println("╚════════════════════════════════════════╝\n");
            
            if (jugador1.getListaPokemon().isEmpty()) {
                System.out.println("¡" + jugador1.getNombre() + ", qué genio! Decidiste entrar a una batalla sin Pokémon.");
                System.out.println("Tu estrategia de luchar con aire fresco es verdaderamente revolucionaria.");
                System.out.println("No sé si admire tu valentía o tu capacidad para la toma de decisiones...\n");
            }
            if (jugador2.getListaPokemon().isEmpty()) {
                System.out.println("¡" + jugador2.getNombre() + ", qué genio! Decidiste entrar a una batalla sin Pokémon.");
                System.out.println("Tu estrategia de luchar con aire fresco es verdaderamente revolucionaria.");
                System.out.println("No sé si admire tu valentía o tu capacidad para la toma de decisiones...\n");
            }
            this.battleFinished = true;
            return;
        }
        
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║        ¡COMIENZA LA BATALLA!           ║");
        System.out.println("║ " + jugador1.getNombre() + " vs " + jugador2.getNombre());
        System.out.println("╚════════════════════════════════════════╝\n");

        pokemonActual1 = jugador1.getListaPokemon().get(0);
        pokemonActual2 = jugador2.getListaPokemon().get(0);
        
        // Preparar pokémons para la batalla
        pokemonActual1.prepararParaBatalla();
        pokemonActual2.prepararParaBatalla();

        System.out.println(jugador1.getNombre() + " envía a " + pokemonActual1.getNombre() + "!");
        System.out.println(jugador2.getNombre() + " envía a " + pokemonActual2.getNombre() + "!\n");

        // Activar efectos de entrada de habilidades
        pokemonActual1.getHabilidad().efectoAlEntrar(pokemonActual1, pokemonActual2);
        pokemonActual2.getHabilidad().efectoAlEntrar(pokemonActual2, pokemonActual1);

        Scanner scanner = new Scanner(System.in);
        int turno = 1;
        while (!battleFinished) {
            // Verificar si hay pokémons vivos
            if (!hayPokemonVivo(jugador1) || !hayPokemonVivo(jugador2)) {
                terminarBattle();
                break;
            }

            System.out.println("\n═══════════════════════════════════════");
            System.out.println("TURNO " + turno);
            System.out.println("═══════════════════════════════════════");

            ejecutarTurno();
            turno++;

            // Cambios de pokémon
            if (pokemonActual1.estaDebilitado()) {
                System.out.println("\n¡" + pokemonActual1.getNombre() + " ha sido derrotado!");
                if (hayPokemonVivo(jugador1)) {
                    Pokemon anterior = pokemonActual1;
                    pokemonActual1 = cambiarPokemon(jugador1, anterior, scanner);
                    if (pokemonActual1 == null) {
                        terminarBattle();
                        break;
                    }
                    pokemonActual1.volverAEntrar();
                    pokemonActual1.getHabilidad().efectoAlEntrar(pokemonActual1, pokemonActual2);
                    System.out.println(jugador1.getNombre() + " envía a " + pokemonActual1.getNombre() + "!");
                } else {
                    terminarBattle();
                    break;
                }
            }


            if (pokemonActual2.estaDebilitado()) {
                System.out.println("\n¡" + pokemonActual2.getNombre() + " ha sido derrotado!");
                if (hayPokemonVivo(jugador2)) {
                    Pokemon anterior = pokemonActual2;
                    pokemonActual2 = cambiarPokemon(jugador2, anterior, scanner);
                    if (pokemonActual2 == null) {
                        terminarBattle();
                        break;
                    }
                    pokemonActual2.volverAEntrar();
                    pokemonActual2.getHabilidad().efectoAlEntrar(pokemonActual2, pokemonActual1);
                    System.out.println(jugador2.getNombre() + " envía a " + pokemonActual2.getNombre() + "!");
                } else {
                    terminarBattle();
                    break;
                }
            }
        }
        
        scanner.close();
    }

    private void ejecutarTurno() {
        Scanner scanner = new Scanner(System.in);

        pokemonActual1.resetProtection();
        pokemonActual2.resetProtection();

        mostrarEstadoBattle();

        System.out.println("\n--- TURNO DE " + jugador1.getNombre() + " ---");
        Movimiento movimiento1 = null;
        boolean jugador1CambiaPokemon = false;

        if (pokemonActual1.flinchActive()) {
            System.out.println(pokemonActual1.getNombre() + " no puede atacar debido a que retrocedió!");
            pokemonActual1.clearFlinch();
            movimiento1 = null;
        } else {
            movimiento1 = seleccionarMovimiento(scanner, pokemonActual1, jugador1.getNombre());
            if (movimiento1 == null && hayPokemonVivo(jugador1)) {
                Pokemon nuevoPokemon = cambiarPokemon(jugador1, pokemonActual1, scanner);
                if (nuevoPokemon != null && !nuevoPokemon.equals(pokemonActual1)) {
                    pokemonActual1 = nuevoPokemon;
                    pokemonActual1.volverAEntrar();
                    pokemonActual1.getHabilidad().efectoAlEntrar(pokemonActual1, pokemonActual2);
                    System.out.println(jugador1.getNombre() + " envía a " + pokemonActual1.getNombre() + "!");
                    jugador1CambiaPokemon = true;
                }
            }
        }

        System.out.println("\n--- TURNO DE " + jugador2.getNombre() + " ---");
        Movimiento movimiento2 = null;
        boolean jugador2CambiaPokemon = false;
        
        if (pokemonActual2.flinchActive()) {
            System.out.println(pokemonActual2.getNombre() + " no puede atacar debido a que retrocedió!");
            pokemonActual2.clearFlinch();
            movimiento2 = null;
        } else {
            movimiento2 = seleccionarMovimiento(scanner, pokemonActual2, jugador2.getNombre());
            if (movimiento2 == null && hayPokemonVivo(jugador2)) {
                Pokemon nuevoPokemon = cambiarPokemon(jugador2, pokemonActual2, scanner);
                if (nuevoPokemon != null && !nuevoPokemon.equals(pokemonActual2)) {
                    pokemonActual2 = nuevoPokemon;
                    pokemonActual2.volverAEntrar();
                    pokemonActual2.getHabilidad().efectoAlEntrar(pokemonActual2, pokemonActual1);
                    System.out.println(jugador2.getNombre() + " envía a " + pokemonActual2.getNombre() + "!");
                    jugador2CambiaPokemon = true;
                }
            }
        }

        // Solo ejecutar ataques si ambos tienen movimientos válidos y no cambiaron Pokémon
        if (movimiento1 != null && movimiento2 != null && !jugador1CambiaPokemon && !jugador2CambiaPokemon) {
            boolean jugador1Ataca1 = (pokemonActual1.getSpeed() + movimiento1.getPrioridad() * 100) >= (pokemonActual2.getSpeed() + movimiento2.getPrioridad() * 100);

            if (jugador1Ataca1) {
                // Ataque de jugador1
                atacar(pokemonActual1, pokemonActual2, movimiento1);
                // Manejar cambio inmediato si jugador1 usó VoltSwitch/UTurn
                manejarCambioInmediato(1);
                
                // Ataque de jugador2 (si no está debilitado)
                if (!pokemonActual2.estaDebilitado()) {
                    atacar(pokemonActual2, pokemonActual1, movimiento2);
                    // Manejar cambio inmediato si jugador2 usó VoltSwitch/UTurn
                    manejarCambioInmediato(2);
                }
            } else {
                // Ataque de jugador2
                atacar(pokemonActual2, pokemonActual1, movimiento2);
                // Manejar cambio inmediato si jugador2 usó VoltSwitch/UTurn
                manejarCambioInmediato(2);
                
                // Ataque de jugador1 (si no está debilitado)
                if (!pokemonActual1.estaDebilitado()) {
                    atacar(pokemonActual1, pokemonActual2, movimiento1);
                    // Manejar cambio inmediato si jugador1 usó VoltSwitch/UTurn
                    manejarCambioInmediato(1);
                }
            }
        } else if (movimiento1 != null && !jugador1CambiaPokemon) {
            atacar(pokemonActual1, pokemonActual2, movimiento1);
            manejarCambioInmediato(1);
        } else if (movimiento2 != null && !jugador2CambiaPokemon) {
            atacar(pokemonActual2, pokemonActual1, movimiento2);
            manejarCambioInmediato(2);
        }
        
        mostrarEstadoBattle();
        
        if (pokemonActual1.estaDebilitado() && pokemonActual2.estaDebilitado()) {
            terminarBattle();
        }
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

        movimiento.efecto(atacante, defensor);
        
        // Solo mostrar PS si es un movimiento que hace daño (no de estado)
        if (movimiento.getCategoria() != Categoria.ESTADO) {
            int psActuales = Math.max(0, defensor.getModPs());
            System.out.println(defensor.getNombre() + " ahora tiene " + psActuales + " PS");
        }
    }
    
    private void manejarCambioInmediato(int jugador) {
        Scanner scanner = new Scanner(System.in);
        
        if (jugador == 1) {
            // Verificar si pokemonActual1 pidió cambio
            if (pokemonActual1.needsSwitch() && hayPokemonVivo(jugador1)) {
                Pokemon anterior = pokemonActual1;
                pokemonActual1.resetSwitch();
                System.out.println("\n¡" + jugador1.getNombre() + " debe enviar otro Pokémon!");
                Pokemon nuevoPokemon = cambiarPokemon(jugador1, anterior, scanner);
                if (nuevoPokemon != null) {
                    pokemonActual1 = nuevoPokemon;
                    pokemonActual1.volverAEntrar();
                    pokemonActual1.getHabilidad().efectoAlEntrar(pokemonActual1, pokemonActual2);
                    System.out.println(jugador1.getNombre() + " envía a " + pokemonActual1.getNombre() + "!");
                }
            }
        } else if (jugador == 2) {
            // Verificar si pokemonActual2 pidió cambio
            if (pokemonActual2.needsSwitch() && hayPokemonVivo(jugador2)) {
                Pokemon anterior = pokemonActual2;
                pokemonActual2.resetSwitch();
                System.out.println("\n¡" + jugador2.getNombre() + " debe enviar otro Pokémon!");
                Pokemon nuevoPokemon = cambiarPokemon(jugador2, anterior, scanner);
                if (nuevoPokemon != null) {
                    pokemonActual2 = nuevoPokemon;
                    pokemonActual2.volverAEntrar();
                    pokemonActual2.getHabilidad().efectoAlEntrar(pokemonActual2, pokemonActual1);
                    System.out.println(jugador2.getNombre() + " envía a " + pokemonActual2.getNombre() + "!");
                }
            }
        }
    }

    private Movimiento seleccionarMovimiento(Scanner scanner, Pokemon pokemon, String nombreJugador) {
        ArrayList<Movimiento> movimientos = pokemon.getMovimientos();

        System.out.println(nombreJugador + ", elige una acción para " + pokemon.getNombre() + ":");
        for (int i = 0; i < movimientos.size(); i++) {
            Movimiento m = movimientos.get(i);
            System.out.println((i + 1) + ". " + m.getNombre() + " (Potencia: " + m.getPotencia() + ", Precisión: " + m.getPrecision() + "%)");
        }
        System.out.println((movimientos.size() + 1) + ". Cambiar Pokémon");

        int opcion = scanner.nextInt();
        scanner.nextLine();

        // Si elige cambiar Pokémon
        if (opcion == movimientos.size() + 1) {
            System.out.println("CAMBIANDO_POKEMON"); // Marcador especial
            return null;
        }

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
            System.out.println("║        ¡" + pokemonActual1.getNombre() + " ha sido derrotado!        ");
            System.out.println("║ ¡" + jugador2.getNombre() + " ha ganado la batalla!");
        } else {
            System.out.println("║        ¡" + pokemonActual2.getNombre() + " ha sido derrotado!        ");
            System.out.println("║ ¡" + jugador1.getNombre() + " ha ganado la batalla!");
        }
        System.out.println("╚════════════════════════════════════════╝\n");
    }

    public boolean isBattleFinished() {
        return battleFinished;
    }

    /**
     * Verifica si una persona tiene al menos un pokémon vivo
     */
    private boolean hayPokemonVivo(Persona persona) {
        for (Pokemon p : persona.getListaPokemon()) {
            if (!p.estaDebilitado()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Permite cambiar de pokémon en batalla
     */
    private Pokemon cambiarPokemon(Persona persona, Pokemon pokemonActual, Scanner scanner) {
        ArrayList<Pokemon> pokemones = persona.getListaPokemon();
        ArrayList<Pokemon> pokemonesVivos = new ArrayList<>();

        // Buscar pokémons vivos EXCEPTO el actual
        for (Pokemon p : pokemones) {
            if (!p.estaDebilitado() && !p.equals(pokemonActual)) {
                pokemonesVivos.add(p);
            }
        }

        if (pokemonesVivos.isEmpty()) {
            System.out.println("\n¡No hay más Pokémon vivos para cambiar!");
            return null;
        }

        System.out.println("\n" + persona.getNombre() + ", elige un pokémon:");
        for (int i = 0; i < pokemonesVivos.size(); i++) {
            Pokemon p = pokemonesVivos.get(i);
            System.out.println((i + 1) + ". " + p.getNombre() + " (PS: " + Math.max(0, p.getModPs()) + ")");
        }

        int opcion = scanner.nextInt();
        scanner.nextLine();

        if (opcion < 1 || opcion > pokemonesVivos.size()) {
            System.out.println("Opción inválida. Seleccionando primer pokémon...");
            return pokemonesVivos.get(0);
        }

        return pokemonesVivos.get(opcion - 1);
    }
}
