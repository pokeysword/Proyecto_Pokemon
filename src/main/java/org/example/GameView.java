package org.example;

import java.util.Map;
import java.util.Scanner;

/**
 * Vista de consola para mostrar mensajes del juego y solicitar entradas.
 */
public class GameView {
    private Scanner scanner;
    //para la interfáz gráfica
    private static BattleStatusView battleStatusView;

    /**
     * Registra una vista de estado para actualizar barras de vida.
     *
     * @param view implementacion de la vista de estado.
     */
    public static void setBattleStatusView(BattleStatusView view) {
        battleStatusView = view;
    }

    /**
     * Muestra un mensaje en la salida.
     *
     * @param mensaje texto a imprimir.
     */
    public static void mostrarLinea(String mensaje) {
        System.out.println(mensaje);
    }

    /**
     * Imprime una linea en blanco.
     */
    public static void saltoLinea() {
        System.out.println();
    }

    /**
     * Informa que el rival ya tiene equipo.
     */
    public static void mostrarRivalFormado() { mostrarLinea("\n¡Rival ha formado su equipo!"); }

    /**
     * Informa que el Pokemon retrocedio.
     *
     * @param nombre nombre del Pokemon.
     */
    public static void mostrarPokemonRetrocedio(String nombre) { mostrarLinea(nombre + " retrocedió (flinch)!"); }

    /**
     * Informa que el Pokemon esta quemado.
     *
     * @param nombre nombre del Pokemon.
     */
    public static void mostrarPokemonQuemado(String nombre) { mostrarLinea(nombre + " está quemado!"); }

    /**
     * Informa que el Pokemon esta congelado.
     *
     * @param nombre nombre del Pokemon.
     */
    public static void mostrarPokemonCongelado(String nombre) { mostrarLinea(nombre + " está congelado!"); }

    /**
     * Informa recuperacion de PS.
     *
     * @param nombre nombre del Pokemon.
     * @param curado cantidad curada.
     */
    public static void mostrarRecover(String nombre, int curado) { mostrarLinea(nombre + " recuperó " + curado + " PS!"); }

    /**
     * Informa fallo de Protect.
     *
     * @param nombre nombre del Pokemon.
     */
    public static void mostrarProtectFallo(String nombre) { mostrarLinea(nombre + " intentó protegerse, pero falló!"); }

    /**
     * Informa exito de Protect.
     *
     * @param nombre nombre del Pokemon.
     */
    public static void mostrarProtectExito(String nombre) { mostrarLinea(nombre + " se protegió del siguiente ataque!"); }

    /**
     * Informa activacion de Competitive.
     *
     * @param nombre nombre del Pokemon.
     */
    public static void mostrarCompetitive(String nombre) { mostrarLinea(nombre + " activó Competitive! ¡Su Atk. Esp. subió mucho!"); }

    /**
     * Informa la activacion de Intimidacion.
     *
     * @param portador Pokemon con la habilidad.
     * @param rival Pokemon afectado.
     */
    public static void mostrarIntimidacion(String portador, String rival) { mostrarLinea(portador + " intimidó a " + rival + "! ¡Su Ataque bajó!"); }

    /**
     * Informa inmunidad por Levitate.
     *
     * @param nombre nombre del Pokemon.
     */
    public static void mostrarLevitateInmune(String nombre) { mostrarLinea(nombre + " es inmune"); }

    /**
     * Informa uso de Mold Breaker.
     *
     * @param nombre nombre del Pokemon.
     */
    public static void mostrarMoldBreaker(String nombre) { mostrarLinea(nombre + " uso Mold Breaker"); }

    /**
     * Informa danio por Rough Skin.
     *
     * @param nombre nombre del Pokemon.
     * @param dano cantidad de dano.
     */
    public static void mostrarRoughSkin(String nombre, int dano) { mostrarLinea(nombre + " recibió " + dano + " de daño por RoughSkin!"); }

    /**
     * Informa que el Pokemon duerme y no ataca.
     *
     * @param nombre nombre del Pokemon.
     */
    public static void mostrarDormidoNoAtaca(String nombre) { mostrarLinea(nombre + " está dormido y no puede atacar!"); }

    /**
     * Informa que el Pokemon se descongelo.
     *
     * @param nombre nombre del Pokemon.
     */
    public static void mostrarSeDescongelo(String nombre) { mostrarLinea(nombre + " se descongeló!"); }

    /**
     * Informa que el Pokemon esta congelado y no ataca.
     *
     * @param nombre nombre del Pokemon.
     */
    public static void mostrarCongeladoNoAtaca(String nombre) { mostrarLinea(nombre + " está congelado y no puede atacar!"); }

    /**
     * Informa que el Pokemon esta paralizado y no se mueve.
     *
     * @param nombre nombre del Pokemon.
     */
    public static void mostrarParalizadoNoSeMueve(String nombre) { mostrarLinea(nombre + " está paralizado y no puede moverse!"); }

    /**
     * Informa inmunidad por habilidad.
     *
     * @param defensor Pokemon defensor.
     * @param movimiento movimiento usado.
     * @param habilidad habilidad que otorga inmunidad.
     */
    public static void mostrarInmunePorHabilidad(String defensor, String movimiento, String habilidad) { mostrarLinea(defensor + " es inmune a " + movimiento + " gracias a su habilidad " + habilidad + "!"); }

    /**
     * Informa inmunidad a un movimiento.
     *
     * @param defensor Pokemon defensor.
     * @param movimiento movimiento usado.
     */
    public static void mostrarInmuneMovimiento(String defensor, String movimiento) { mostrarLinea(defensor + " es inmune a " + movimiento + "! ¡No afecta!"); }

    /**
     * Informa reduccion de danio por quemadura.
     *
     * @param nombre nombre del Pokemon.
     */
    public static void mostrarQuemadoMitadDanio(String nombre) { mostrarLinea(nombre + " está quemado y su daño se reduce a la mitad!"); }

    /**
     * Muestra informacion de ataque.
     *
     * @param mensaje texto a imprimir.
     */
    public static void mostrarInfoAtaque(String mensaje) { mostrarLinea(mensaje); }

    /**
     * Informa danio directo recibido.
     *
     * @param nombre nombre del Pokemon.
     * @param dano cantidad de dano.
     */
    public static void mostrarDanioRecibido(String nombre, int dano) { mostrarLinea(nombre + " recibió " + dano + " de daño!"); }

    /**
     * Informa danio por envenenamiento.
     *
     * @param nombre nombre del Pokemon.
     * @param dano cantidad de dano.
     */
    public static void mostrarDanioEnvenenamiento(String nombre, int dano) { mostrarLinea(nombre + " recibió " + dano + " de daño por envenenamiento!"); }

    /**
     * Informa danio por quemadura.
     *
     * @param nombre nombre del Pokemon.
     * @param dano cantidad de dano.
     */
    public static void mostrarDanioQuemadura(String nombre, int dano) { mostrarLinea(nombre + " recibió " + dano + " de daño por quemadura!"); }

    /**
     * Informa que el Pokemon se desperto.
     *
     * @param nombre nombre del Pokemon.
     */
    public static void mostrarSeDesperto(String nombre) { mostrarLinea(nombre + " se despertó!"); }

    /**
     * Informa que el Pokemon sale del campo.
     *
     * @param nombre nombre del Pokemon.
     */
    public static void mostrarSaleDelCampo(String nombre) { mostrarLinea(nombre + " sale del campo."); }

    /**
     * Informa cambios de estadisticas.
     *
     * @param nombre nombre del Pokemon.
     * @param stat estadistica afectada.
     * @param num cambios en niveles.
     */
    public static void mostrarCambioStats(String nombre, String stat, int num) {
        if (num > 1) {
            mostrarLinea(nombre + " aumentó mucho su " + stat + "!");
        } else if (num == 1) {
            mostrarLinea(nombre + " aumentó su " + stat + ".");
        } else if (num == -1) {
            mostrarLinea(nombre + " bajó su " + stat + ".");
        } else if (num < -1) {
            mostrarLinea(nombre + " bajó mucho su " + stat + "!");
        }
    }

    /**
     * Muestra el menu de creacion de equipo.
     */
    public static void mostrarMenuCrearEquipo() { mostrarLinea("\n1. Elegir pokemon\n2. Ver equipo\n3. Combatir con el equipo seleccionado"); }

    /**
     * Muestra el menu para elegir Pokemon.
     *
     * @param disponibles catalogo de Pokemon disponibles.
     */
    public static void mostrarMenuElegirPokemon(Map<Integer, Pokemon> disponibles) {
        StringBuilder builder = new StringBuilder();
        builder.append("\nElige un pokemon:");
        for (Map.Entry<Integer, Pokemon> entry : disponibles.entrySet()) {
            builder.append("\n").append(entry.getKey()).append(".").append(entry.getValue().getNombre());
        }
        mostrarLinea(builder.toString());
    }

    /**
     * Informa que se agrego un Pokemon al equipo.
     *
     * @param cont total actual en el equipo.
     */
    public static void mostrarPokemonAgregado(int cont) { mostrarLinea("✓ Pokémon agregado. (" + cont + "/4)"); }

    /**
     * Informa que se selecciono un Pokemon inexistente.
     */
    public static void mostrarPokemonInexistente() { mostrarLinea("✗ ¡Ese Pokémon no existe! Elige un número válido."); }

    /**
     * Informa que el equipo esta completo.
     */
    public static void mostrarEquipoCompleto() { mostrarLinea("✓ Equipo completo (4/4)"); }

    /**
     * Informa que se necesita al menos un Pokemon.
     *
     * @param cont cantidad actual.
     */
    public static void mostrarNecesitaPokemon(int cont) { mostrarLinea("✗ Necesitas al menos 1 Pokémon para combatir. Tienes " + cont); }

    /**
     * Informa que la opcion no es valida.
     */
    public static void mostrarOpcionNoValida() { mostrarLinea("✗ Opción no válida"); }

    /**
     * Informa que no se eligio ningun Pokemon.
     */
    public static void mostrarNoElegistePokemon() { mostrarLinea("No elegiste ningún pokemon"); }

    /**
     * Muestra el encabezado del equipo del jugador.
     */
    public static void mostrarTuEquipoHeader() { mostrarLinea("\n=== Tu Equipo ==="); }

    /**
     * Muestra un Pokemon del equipo.
     *
     * @param nombre nombre del Pokemon.
     */
    public static void mostrarPokemonEquipo(String nombre) { mostrarLinea("- " + nombre); }

    /**
     * Muestra el mensaje de batalla no valida.
     */
    public static void mostrarBatallaNoValidaCaja() {
        mostrarLinea("\n╔════════════════════════════════════════╗");
        mostrarLinea("║        ¡BATALLA NO VÁLIDA!             ║");
        mostrarLinea("╚════════════════════════════════════════╝\n");
    }

    /**
     * Muestra mensaje cuando un jugador no tiene Pokemon.
     *
     * @param nombre nombre del jugador.
     */
    public static void mostrarSinPokemonMensaje1(String nombre) { mostrarLinea("¡" + nombre + ", qué genio! Decidiste entrar a una batalla sin Pokémon."); }

    /**
     * Muestra mensaje adicional de falta de Pokemon.
     */
    public static void mostrarSinPokemonMensaje2() { mostrarLinea("Tu inteligencia es como una variable null: no contiene nada útil y aun así consume memoria."); }

    /**
     * Muestra mensaje final de falta de Pokemon.
     */
    public static void mostrarSinPokemonMensaje3() { mostrarLinea("Pero ahí estás, ejecutando código mental que ni siquiera compila...\n"); }

    /**
     * Muestra el encabezado de inicio de batalla.
     *
     * @param j1 nombre del jugador 1.
     * @param j2 nombre del jugador 2.
     */
    public static void mostrarInicioBatallaCaja(String j1, String j2) {
        mostrarLinea("\n╔════════════════════════════════════════╗");
        mostrarLinea("║        ¡COMIENZA LA BATALLA!           ║");
        mostrarLinea("║ " + j1 + " vs " + j2);
        mostrarLinea("╚════════════════════════════════════════╝\n");
    }

    /**
     * Informa que un entrenador envia un Pokemon.
     *
     * @param entrenador nombre del entrenador.
     * @param pokemon nombre del Pokemon.
     */
    public static void mostrarEnviaPokemon(String entrenador, String pokemon) { mostrarLinea(entrenador + " envía a " + pokemon + "!"); }

    /**
     * Muestra separador de turno.
     *
     * @param turno numero de turno.
     */
    public static void mostrarSeparadorTurno(int turno) {
        mostrarLinea("\n═══════════════════════════════════════");
        mostrarLinea("TURNO " + turno);
        mostrarLinea("═══════════════════════════════════════");
    }

    /**
     * Informa que un Pokemon fue derrotado.
     *
     * @param nombre nombre del Pokemon.
     */
    public static void mostrarPokemonDerrotado(String nombre) { mostrarLinea("\n¡" + nombre + " ha sido derrotado!"); }

    /**
     * Muestra el turno del jugador.
     *
     * @param nombre nombre del jugador.
     */
    public static void mostrarTurnoJugador(String nombre) { mostrarLinea("\n--- TURNO DE " + nombre + " ---"); }

    /**
     * Informa que un Pokemon no puede atacar por flinch.
     *
     * @param nombre nombre del Pokemon.
     */
    public static void mostrarNoPuedeAtacarPorFlinch(String nombre) { mostrarLinea(nombre + " no puede atacar debido a que retrocedió!"); }

    /**
     * Informa que el movimiento no tiene PP.
     *
     * @param nombre nombre del movimiento.
     */
    public static void mostrarMovimientoSinPp(String nombre) { mostrarLinea(nombre + " no tiene PP y no se puede usar."); }

    /**
     * Informa el uso de un movimiento de estado.
     *
     * @param atacante nombre del atacante.
     * @param movimiento nombre del movimiento.
     */
    public static void mostrarUsoMovimientoEstado(String atacante, String movimiento) { mostrarLinea("\n" + atacante + " usa " + movimiento + "!"); }

    /**
     * Informa que un ataque fue bloqueado por Protect.
     *
     * @param defensor nombre del Pokemon defensor.
     */
    public static void mostrarAtaqueBloqueadoPorProtect(String defensor) { mostrarLinea(defensor + " está protegido y evitó el ataque!"); }

    /**
     * Informa los PS actuales de un Pokemon.
     *
     * @param defensor nombre del Pokemon.
     * @param ps puntos de salud actuales.
     */
    public static void mostrarPsActual(String defensor, int ps) { mostrarLinea(defensor + " ahora tiene " + ps + " PS"); }

    /**
     * Informa que un entrenador debe enviar otro Pokemon.
     *
     * @param entrenador nombre del entrenador.
     */
    public static void mostrarDebeEnviarOtroPokemon(String entrenador) { mostrarLinea("\n¡" + entrenador + " debe enviar otro Pokémon!"); }

    /**
     * Solicita elegir una accion.
     *
     * @param jugador nombre del jugador.
     * @param pokemon nombre del Pokemon.
     */
    public static void mostrarElegirAccion(String jugador, String pokemon) { mostrarLinea(jugador + ", elige una acción para " + pokemon + ":"); }

    /**
     * Muestra un movimiento disponible.
     *
     * @param idx indice del movimiento.
     * @param nombre nombre del movimiento.
     * @param potencia potencia del movimiento.
     * @param precision precision del movimiento.
     * @param pp PP actual.
     * @param ppMax PP maximo.
     */
    public static void mostrarMovimientoDisponible(int idx, String nombre, int potencia, int precision, int pp, int ppMax) {
        mostrarLinea(idx + ". " + nombre + " (Potencia: " + potencia + ", Precisión: " + precision + "%, PP: " + pp + "/" + ppMax + ")");
    }

    /**
     * Muestra la opcion de cambiar Pokemon.
     *
     * @param opcion indice mostrado.
     */
    public static void mostrarOpcionCambiarPokemon(int opcion) { mostrarLinea(opcion + ". Cambiar Pokémon"); }

    /**
     * Muestra el marcador especial de cambio de Pokemon.
     */
    public static void mostrarCambiandoPokemon() { mostrarLinea("CAMBIANDO_POKEMON"); }

    /**
     * Informa que la opcion es invalida.
     */
    public static void mostrarOpcionInvalidaIntentaDeNuevo() { mostrarLinea("Opción inválida. Intenta de nuevo."); }

    /**
     * Informa que el movimiento elegido no tiene PP.
     *
     * @param nombre nombre del movimiento.
     */
    public static void mostrarMovimientoSinPpElegirOtro(String nombre) { mostrarLinea(nombre + " no tiene PP. Elige otro movimiento."); }

    /**
     * Muestra el estado resumido de la batalla y actualiza la vista grafica.
     *
     * @param nombre1 nombre del Pokemon 1.
     * @param nombre2 nombre del Pokemon 2.
     * @param ps1 PS actuales del Pokemon 1.
     * @param max1 PS maximos del Pokemon 1.
     * @param ps2 PS actuales del Pokemon 2.
     * @param max2 PS maximos del Pokemon 2.
     * @param nivel1 nivel del Pokemon 1.
     * @param nivel2 nivel del Pokemon 2.
     * @param e1 estado del Pokemon 1.
     * @param e2 estado del Pokemon 2.
     */
    public static void mostrarEstadoBattle(String nombre1, String nombre2, int ps1, int max1, int ps2, int max2, int nivel1, int nivel2, Estado e1, Estado e2) {
        mostrarLinea("\n┌─────────────────────────────────────────┐");
        mostrarLinea("│ " + String.format("%-18s | %-18s", nombre1, nombre2));
        mostrarLinea("│ " + String.format("PS: %-14d | PS: %-14d", ps1, ps2));
        mostrarLinea("│ " + String.format("Nivel: %-10d | Nivel: %-10d", nivel1, nivel2));
        mostrarLinea("│ Estado: " + String.format("%-9s | Estado: %-8s", e1, e2));
        mostrarLinea("└─────────────────────────────────────────┘");
//para la interfaz gráfica, se actualizarían las barras de vida y etiquetas en lugar de imprimir texto
        if (battleStatusView != null) {
            battleStatusView.updateBattleStatus(nombre1, ps1, max1, nombre2, ps2, max2);
        }
    }

    /**
     * Muestra la caja final de batalla.
     *
     * @param derrotado Pokemon derrotado.
     * @param ganador nombre del ganador.
     */
    public static void mostrarFinalBatallaCaja(String derrotado, String ganador) {
        mostrarLinea("\n╔════════════════════════════════════════╗");
        mostrarLinea("║        ¡" + derrotado + " ha sido derrotado!        ");
        mostrarLinea("║ ¡" + ganador + " ha ganado la batalla!");
        mostrarLinea("╚════════════════════════════════════════╝\n");
    }

    /**
     * Informa que no hay mas Pokemon vivos.
     */
    public static void mostrarNoHayMasPokemonVivos() { mostrarLinea("\n¡No hay más Pokémon vivos para cambiar!"); }

    /**
     * Solicita elegir un Pokemon del jugador.
     *
     * @param nombrePersona nombre del jugador.
     */
    public static void mostrarElegirPokemonPersona(String nombrePersona) { mostrarLinea("\n" + nombrePersona + ", elige un pokémon:"); }

    /**
     * Muestra una opcion de Pokemon con PS.
     *
     * @param idx indice de la opcion.
     * @param nombre nombre del Pokemon.
     * @param ps puntos de salud.
     */
    public static void mostrarPokemonConPsOpcion(int idx, String nombre, int ps) { mostrarLinea(idx + ". " + nombre + " (PS: " + ps + ")"); }

    /**
     * Informa que la opcion fue invalida y selecciona el primero.
     */
    public static void mostrarOpcionInvalidaSeleccionaPrimerPokemon() { mostrarLinea("Opción inválida. Seleccionando primer pokémon..."); }

    /**
     * Muestra error al cargar datos de Pokemon.
     *
     * @param mensaje detalle del error.
     */
    public static void mostrarErrorCargaPokemon(String mensaje) {
        mostrarLinea("✗ Error al cargar datos de pokemon: " + mensaje);
    }

    /**
     * Crea una vista de consola con entrada por Scanner.
     */
    public GameView() {
        scanner = new Scanner(System.in);
    }

    /**
     * Muestra el mensaje de bienvenida e inicia audio.
     *
     * @param audioManager gestor de audio.
     */
    public void showWelcome(AudioManager audioManager) {
        audioManager.playLoop("/audio/center.wav");
        mostrarLinea("Bienvenido al juego Pokémon");
    }

    /**
     * Muestra el equipo actual de un jugador.
     *
     * @param persona jugador del equipo.
     */
    public void mostrarEquipo(Persona persona) {
        mostrarLinea("\n=== Equipo de " + persona.getNombre() + " ===");
        for (Pokemon p : persona.getListaPokemon()) {
            mostrarLinea("- " + p.getNombre());
        }
        saltoLinea();
    }

    /**
     * Inicia el flujo de seleccion de equipo.
     *
     * @param persona jugador.
     */
    public void iniciarSeleccionEquipo(Persona persona) {
        mostrarLinea("\n--- Selección de Equipo ---");
        persona.crearEquipo();
        mostrarEquipo(persona);
        mostrarLinea("¡Equipo confirmado! Preparándose para la batalla...");
    }

    /**
     * Solicita el nombre del jugador.
     *
     * @param persona jugador a configurar.
     */
    public void seleccionarnombre(Persona persona) {
        mostrarLinea("¿Cuál es tu nombre, entrenador?\n ");
        String nombre = scanner.nextLine();
        persona.setNombre(nombre);
        mostrarLinea("¡Suerte, " + persona.getNombre() + "!");
    }

}