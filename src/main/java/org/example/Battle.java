package org.example;

import org.example.data.BattleLogger;
import org.example.movimientos.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Orquesta la batalla por turnos entre dos jugadores.
 */
public class Battle {
    private Persona jugador1;
    private Persona jugador2;
    private Pokemon pokemonActual1;
    private Pokemon pokemonActual2;
    private boolean battleFinished;
    private final BattleLogger battleLogger;
    private final AudioManager audioManager;

    /**
     * Crea una batalla entre dos jugadores con soporte de audio.
     *
     * @param jugador1 primer jugador.
     * @param jugador2 segundo jugador.
     * @param audioManager gestor de audio para musica de batalla.
     */
    public Battle(Persona jugador1, Persona jugador2, AudioManager audioManager) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.battleFinished = false;
        this.battleLogger = new BattleLogger("battle-log.txt");
        this.audioManager = audioManager;
    }

    /**
     * Inicia el flujo principal de la batalla y procesa turnos hasta el final.
     */
    public void iniciarBattle() {
        audioManager.playLoop("/audio/battle.wav");

        // Validar que ambos jugadores tengan Pokémon
        if (jugador1.getListaPokemon().isEmpty() || jugador2.getListaPokemon().isEmpty()) {
            GameView.mostrarBatallaNoValidaCaja();

            if (jugador1.getListaPokemon().isEmpty()) {
                GameView.mostrarSinPokemonMensaje1(jugador1.getNombre());
                GameView.mostrarSinPokemonMensaje2();
                GameView.mostrarSinPokemonMensaje3();

            }
            if (jugador2.getListaPokemon().isEmpty()) {
                GameView.mostrarSinPokemonMensaje1(jugador2.getNombre());
                GameView.mostrarSinPokemonMensaje2();
                GameView.mostrarSinPokemonMensaje3();
            }
            this.battleFinished = true;
            audioManager.stop();
            return;
        }
        
        GameView.mostrarInicioBatallaCaja(jugador1.getNombre(), jugador2.getNombre());

        pokemonActual1 = jugador1.getListaPokemon().get(0);
        pokemonActual2 = jugador2.getListaPokemon().get(0);

        battleLogger.logInicio(jugador1.getNombre(), jugador2.getNombre(), pokemonActual1, pokemonActual2);
        
        // Preparar pokémons para la batalla
        pokemonActual1.prepararParaBatalla();
        pokemonActual2.prepararParaBatalla();

        GameView.mostrarEnviaPokemon(jugador1.getNombre(), pokemonActual1.getNombre());
        GameView.mostrarEnviaPokemon(jugador2.getNombre(), pokemonActual2.getNombre());
        GameView.saltoLinea();

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

            GameView.mostrarSeparadorTurno(turno);

            ejecutarTurno(turno);
            turno++;

            // Cambios de pokémon
            if (pokemonActual1.estaDebilitado()) {
                GameView.mostrarPokemonDerrotado(pokemonActual1.getNombre());
                if (hayPokemonVivo(jugador1)) {
                    Pokemon anterior = pokemonActual1;
                    pokemonActual1 = cambiarPokemon(jugador1, anterior, scanner);
                    if (pokemonActual1 == null) {
                        terminarBattle();
                        break;
                    }
                    pokemonActual1.volverAEntrar();
                    pokemonActual1.getHabilidad().efectoAlEntrar(pokemonActual1, pokemonActual2);
                    GameView.mostrarEnviaPokemon(jugador1.getNombre(), pokemonActual1.getNombre());
                } else {
                    terminarBattle();
                    break;
                }
            }


            if (pokemonActual2.estaDebilitado()) {
                GameView.mostrarPokemonDerrotado(pokemonActual2.getNombre());
                if (hayPokemonVivo(jugador2)) {
                    Pokemon anterior = pokemonActual2;
                    pokemonActual2 = cambiarPokemon(jugador2, anterior, scanner);
                    if (pokemonActual2 == null) {
                        terminarBattle();
                        break;
                    }
                    pokemonActual2.volverAEntrar();
                    pokemonActual2.getHabilidad().efectoAlEntrar(pokemonActual2, pokemonActual1);
                    GameView.mostrarEnviaPokemon(jugador2.getNombre(), pokemonActual2.getNombre());
                } else {
                    terminarBattle();
                    break;
                }
            }
        }
        
        audioManager.stop();
        scanner.close();
    }

    /**
     * Ejecuta un turno completo de ambos jugadores.
     *
     * @param turno numero de turno.
     */
    private void ejecutarTurno(int turno) {
        Scanner scanner = new Scanner(System.in);

        pokemonActual1.resetProtection();
        pokemonActual2.resetProtection();

        Pokemon pokemonTurno1 = pokemonActual1;
        Pokemon pokemonTurno2 = pokemonActual2;
        boolean pokemon1Ataco = false;
        boolean pokemon2Ataco = false;

        mostrarEstadoBattle();

        GameView.mostrarTurnoJugador(jugador1.getNombre());
        Movimiento movimiento1 = null;
        boolean jugador1CambiaPokemon = false;

        if (pokemonActual1.flinchActive()) {
            GameView.mostrarNoPuedeAtacarPorFlinch(pokemonActual1.getNombre());
            movimiento1 = null;
        } else {
            movimiento1 = seleccionarMovimiento(scanner, pokemonActual1, jugador1.getNombre());
            if (movimiento1 == null && hayPokemonVivo(jugador1)) {
                Pokemon nuevoPokemon = cambiarPokemon(jugador1, pokemonActual1, scanner);
                if (nuevoPokemon != null && !nuevoPokemon.equals(pokemonActual1)) {
                    pokemonActual1 = nuevoPokemon;
                    pokemonActual1.volverAEntrar();
                    pokemonActual1.getHabilidad().efectoAlEntrar(pokemonActual1, pokemonActual2);
                    GameView.mostrarEnviaPokemon(jugador1.getNombre(), pokemonActual1.getNombre());
                    jugador1CambiaPokemon = true;
                }
            }
        }

        GameView.mostrarTurnoJugador(jugador2.getNombre());
        Movimiento movimiento2 = null;
        boolean jugador2CambiaPokemon = false;
        
        if (pokemonActual2.flinchActive()) {
            GameView.mostrarNoPuedeAtacarPorFlinch(pokemonActual2.getNombre());
            movimiento2 = null;
        } else {
            movimiento2 = seleccionarMovimiento(scanner, pokemonActual2, jugador2.getNombre());
            if (movimiento2 == null && hayPokemonVivo(jugador2)) {
                Pokemon nuevoPokemon = cambiarPokemon(jugador2, pokemonActual2, scanner);
                if (nuevoPokemon != null && !nuevoPokemon.equals(pokemonActual2)) {
                    pokemonActual2 = nuevoPokemon;
                    pokemonActual2.volverAEntrar();
                    pokemonActual2.getHabilidad().efectoAlEntrar(pokemonActual2, pokemonActual1);
                    GameView.mostrarEnviaPokemon(jugador2.getNombre(), pokemonActual2.getNombre());
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
                pokemon1Ataco = true;
                // Manejar cambio inmediato si jugador1 usó VoltSwitch/UTurn
                manejarCambioInmediato(1);
                
                // Ataque de jugador2 (si no está debilitado)
                if (!pokemonActual2.estaDebilitado()) {
                    atacar(pokemonActual2, pokemonActual1, movimiento2);
                    pokemon2Ataco = true;
                    // Manejar cambio inmediato si jugador2 usó VoltSwitch/UTurn
                    manejarCambioInmediato(2);
                }
            } else {
                // Ataque de jugador2
                atacar(pokemonActual2, pokemonActual1, movimiento2);
                pokemon2Ataco = true;
                // Manejar cambio inmediato si jugador2 usó VoltSwitch/UTurn
                manejarCambioInmediato(2);
                
                // Ataque de jugador1 (si no está debilitado)
                if (!pokemonActual1.estaDebilitado()) {
                    atacar(pokemonActual1, pokemonActual2, movimiento1);
                    pokemon1Ataco = true;
                    // Manejar cambio inmediato si jugador1 usó VoltSwitch/UTurn
                    manejarCambioInmediato(1);
                }
            }
        } else if (movimiento1 != null && !jugador1CambiaPokemon) {
            atacar(pokemonActual1, pokemonActual2, movimiento1);
            pokemon1Ataco = true;
            manejarCambioInmediato(1);
        } else if (movimiento2 != null && !jugador2CambiaPokemon) {
            atacar(pokemonActual2, pokemonActual1, movimiento2);
            pokemon2Ataco = true;
            manejarCambioInmediato(2);
        }

        if (!pokemon1Ataco) {
            pokemonTurno1.setUsoProtectTurnoAnterior(false);
        }
        if (!pokemon2Ataco) {
            pokemonTurno2.setUsoProtectTurnoAnterior(false);
        }

        // Limpiar flinch al final de la ronda
        pokemonActual1.clearFlinch();
        pokemonActual2.clearFlinch();
        
        mostrarEstadoBattle();

        String accion1 = construirAccion(movimiento1, jugador1CambiaPokemon);
        String accion2 = construirAccion(movimiento2, jugador2CambiaPokemon);
        battleLogger.logTurno(turno, jugador1.getNombre(), jugador2.getNombre(), pokemonActual1, pokemonActual2, accion1, accion2);

        if (pokemonActual1.estaDebilitado() && pokemonActual2.estaDebilitado()) {
            terminarBattle();
        }
    }

    /**
     * Construye el texto de accion para el registro.
     *
     * @param movimiento movimiento elegido o null.
     * @param cambioPokemon indica si hubo cambio de Pokemon.
     * @return descripcion de la accion.
     */
    private String construirAccion(Movimiento movimiento, boolean cambioPokemon) {
        if (cambioPokemon) {
            return "Cambio";
        }
        if (movimiento == null) {
            return "Sin accion";
        }
        return movimiento.getNombre();
    }

    /**
     * Ejecuta el ataque de un Pokemon contra otro.
     *
     * @param atacante Pokemon que ataca.
     * @param defensor Pokemon que recibe el ataque.
     * @param movimiento movimiento a ejecutar.
     */
    private void atacar(Pokemon atacante, Pokemon defensor, Movimiento movimiento) {
        if (!movimiento.consumirPp()) {
            atacante.setUsoProtectTurnoAnterior(false);
            GameView.mostrarMovimientoSinPp(movimiento.getNombre());
            return;
        }

        atacante.setUsoProtectTurnoAnterior(movimiento instanceof Protect);

        // Mostrar el movimiento usado
        if (movimiento.getCategoria() == Categoria.ESTADO) {
            GameView.mostrarUsoMovimientoEstado(atacante.getNombre(), movimiento.getNombre());
        }
        
        // Verificar si el defensor está protegido
        if (defensor.isProtegido() && movimiento.getCategoria() != Categoria.ESTADO) {
            GameView.mostrarAtaqueBloqueadoPorProtect(defensor.getNombre());
            return;
        }

        movimiento.efecto(atacante, defensor);
        
        // Solo mostrar PS si es un movimiento que hace daño (no de estado)
        if (movimiento.getCategoria() != Categoria.ESTADO) {
            int psActuales = Math.max(0, defensor.getModPs());
            GameView.mostrarPsActual(defensor.getNombre(), psActuales);
        }
    }

    /**
     * Gestiona el cambio inmediato tras movimientos de salida.
     *
     * @param jugador numero de jugador (1 o 2).
     */
    private void manejarCambioInmediato(int jugador) {
        Scanner scanner = new Scanner(System.in);

        if (jugador == 1) {
            // Verificar si pokemonActual1 pidió cambio
            if (pokemonActual1.needsSwitch() && hayPokemonVivo(jugador1)) {
                Pokemon anterior = pokemonActual1;
                pokemonActual1.resetSwitch();
                GameView.mostrarDebeEnviarOtroPokemon(jugador1.getNombre());
                Pokemon nuevoPokemon = cambiarPokemon(jugador1, anterior, scanner);
                if (nuevoPokemon != null) {
                    pokemonActual1 = nuevoPokemon;
                    pokemonActual1.volverAEntrar();
                    pokemonActual1.getHabilidad().efectoAlEntrar(pokemonActual1, pokemonActual2);
                    GameView.mostrarEnviaPokemon(jugador1.getNombre(), pokemonActual1.getNombre());
                }
            }
        } else if (jugador == 2) {
            // Verificar si pokemonActual2 pidió cambio
            if (pokemonActual2.needsSwitch() && hayPokemonVivo(jugador2)) {
                Pokemon anterior = pokemonActual2;
                pokemonActual2.resetSwitch();
                GameView.mostrarDebeEnviarOtroPokemon(jugador2.getNombre());
                Pokemon nuevoPokemon = cambiarPokemon(jugador2, anterior, scanner);
                if (nuevoPokemon != null) {
                    pokemonActual2 = nuevoPokemon;
                    pokemonActual2.volverAEntrar();
                    pokemonActual2.getHabilidad().efectoAlEntrar(pokemonActual2, pokemonActual1);
                    GameView.mostrarEnviaPokemon(jugador2.getNombre(), pokemonActual2.getNombre());
                }
            }
        }
    }

    /**
     * Permite al jugador elegir un movimiento o cambiar Pokemon.
     *
     * @param scanner lector de entrada.
     * @param pokemon Pokemon activo.
     * @param nombreJugador nombre del jugador.
     * @return movimiento elegido o null si cambia Pokemon.
     */
    private Movimiento seleccionarMovimiento(Scanner scanner, Pokemon pokemon, String nombreJugador) {
        ArrayList<Movimiento> movimientos = pokemon.getMovimientos();

        GameView.mostrarElegirAccion(nombreJugador, pokemon.getNombre());
        for (int i = 0; i < movimientos.size(); i++) {
            Movimiento m = movimientos.get(i);
            GameView.mostrarMovimientoDisponible(i + 1, m.getNombre(), m.getPotencia(), m.getPrecision(), m.getPp(), m.getPpMax());
        }
        GameView.mostrarOpcionCambiarPokemon(movimientos.size() + 1);

        while (true) {
            int opcion = scanner.nextInt();
            scanner.nextLine();

            // Si elige cambiar Pokémon
            if (opcion == movimientos.size() + 1) {
                GameView.mostrarCambiandoPokemon(); // Marcador especial
                return null;
            }

            if (opcion < 1 || opcion > movimientos.size()) {
                GameView.mostrarOpcionInvalidaIntentaDeNuevo();
                continue;
            }

            Movimiento elegido = movimientos.get(opcion - 1);
            if (!elegido.tienePpDisponible()) {
                GameView.mostrarMovimientoSinPpElegirOtro(elegido.getNombre());
                continue;
            }

            return elegido;
        }
    }

    /**
     * Muestra el estado actual de la batalla.
     */
    private void mostrarEstadoBattle() {
        String nombre1 = pokemonActual1.getNombre();
        String nombre2 = pokemonActual2.getNombre() + " (rival)";
        int ps1 = Math.max(0, pokemonActual1.getModPs());
        int ps2 = Math.max(0, pokemonActual2.getModPs());
        int max1 = Math.max(1, pokemonActual1.getPS());
        int max2 = Math.max(1, pokemonActual2.getPS());

        GameView.mostrarEstadoBattle(nombre1, nombre2, ps1, max1, ps2, max2, pokemonActual1.getNivel(), pokemonActual2.getNivel(), pokemonActual1.getEstado(), pokemonActual2.getEstado());
    }

    /**
     * Finaliza la batalla y registra el ganador.
     */
    private void terminarBattle() {
        battleFinished = true;

        String ganador;
        if (pokemonActual1.estaDebilitado() && pokemonActual2.estaDebilitado()) {
            ganador = "Empate";
        } else if (pokemonActual1.estaDebilitado()) {
            ganador = jugador2.getNombre();
        } else {
            ganador = jugador1.getNombre();
        }

        battleLogger.logFin(ganador);

        if (pokemonActual1.estaDebilitado()) {
            GameView.mostrarFinalBatallaCaja(pokemonActual1.getNombre(), jugador2.getNombre());
        } else {
            GameView.mostrarFinalBatallaCaja(pokemonActual2.getNombre(), jugador1.getNombre());
        }
    }

    /**
     * Indica si la batalla ha terminado.
     *
     * @return true si la batalla finalizo.
     */
    public boolean isBattleFinished() {
        return battleFinished;
    }

    /**
     * Verifica si una persona tiene al menos un Pokemon vivo.
     *
     * @param persona jugador a evaluar.
     * @return true si tiene Pokemon disponibles.
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
     * Cambia el Pokemon activo por otro disponible.
     *
     * @param persona jugador que cambia.
     * @param pokemonActual Pokemon actual.
     * @param scanner lector de entrada.
     * @return nuevo Pokemon o null si no hay disponibles.
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
            GameView.mostrarNoHayMasPokemonVivos();
            return null;
        }

        GameView.mostrarElegirPokemonPersona(persona.getNombre());
        for (int i = 0; i < pokemonesVivos.size(); i++) {
            Pokemon p = pokemonesVivos.get(i);
            GameView.mostrarPokemonConPsOpcion(i + 1, p.getNombre(), Math.max(0, p.getModPs()));
        }

        int opcion = scanner.nextInt();
        scanner.nextLine();

        if (opcion < 1 || opcion > pokemonesVivos.size()) {
            GameView.mostrarOpcionInvalidaSeleccionaPrimerPokemon();
            return pokemonesVivos.get(0);
        }

        return pokemonesVivos.get(opcion - 1);
    }
}
