package org.example;

import java.util.Scanner;

public class GameView {
    private Scanner scanner;

    public static void mostrarLinea(String mensaje) {
        System.out.println(mensaje);
    }

    public static void mostrar(String mensaje) {
        System.out.print(mensaje);
    }

    public static void saltoLinea() {
        System.out.println();
    }

    public static void mostrarRivalFormado() { mostrarLinea("\n¡Rival ha formado su equipo!"); }
    public static void mostrarPokemonRetrocedio(String nombre) { mostrarLinea(nombre + " retrocedió (flinch)!"); }
    public static void mostrarPokemonQuemado(String nombre) { mostrarLinea(nombre + " está quemado!"); }
    public static void mostrarPokemonCongelado(String nombre) { mostrarLinea(nombre + " está congelado!"); }
    public static void mostrarRecover(String nombre, int curado) { mostrarLinea(nombre + " recuperó " + curado + " PS!"); }
    public static void mostrarProtectFallo(String nombre) { mostrarLinea(nombre + " intentó protegerse, pero falló!"); }
    public static void mostrarProtectExito(String nombre) { mostrarLinea(nombre + " se protegió del siguiente ataque!"); }
    public static void mostrarCompetitive(String nombre) { mostrarLinea(nombre + " activó Competitive! ¡Su Atk. Esp. subió mucho!"); }
    public static void mostrarIntimidacion(String portador, String rival) { mostrarLinea(portador + " intimidó a " + rival + "! ¡Su Ataque bajó!"); }
    public static void mostrarLevitateInmune(String nombre) { mostrarLinea(nombre + " es inmune"); }
    public static void mostrarMoldBreaker(String nombre) { mostrarLinea(nombre + " uso Mold Breaker"); }
    public static void mostrarRoughSkin(String nombre, int dano) { mostrarLinea(nombre + " recibió " + dano + " de daño por RoughSkin!"); }
    public static void mostrarDormidoNoAtaca(String nombre) { mostrarLinea(nombre + " está dormido y no puede atacar!"); }
    public static void mostrarSeDescongelo(String nombre) { mostrarLinea(nombre + " se descongeló!"); }
    public static void mostrarCongeladoNoAtaca(String nombre) { mostrarLinea(nombre + " está congelado y no puede atacar!"); }
    public static void mostrarParalizadoNoSeMueve(String nombre) { mostrarLinea(nombre + " está paralizado y no puede moverse!"); }
    public static void mostrarInmunePorHabilidad(String defensor, String movimiento, String habilidad) { mostrarLinea(defensor + " es inmune a " + movimiento + " gracias a su habilidad " + habilidad + "!"); }
    public static void mostrarInmuneMovimiento(String defensor, String movimiento) { mostrarLinea(defensor + " es inmune a " + movimiento + "! ¡No afecta!"); }
    public static void mostrarQuemadoMitadDanio(String nombre) { mostrarLinea(nombre + " está quemado y su daño se reduce a la mitad!"); }
    public static void mostrarInfoAtaque(String mensaje) { mostrarLinea(mensaje); }
    public static void mostrarDanioRecibido(String nombre, int dano) { mostrarLinea(nombre + " recibió " + dano + " de daño!"); }
    public static void mostrarDanioEnvenenamiento(String nombre, int dano) { mostrarLinea(nombre + " recibió " + dano + " de daño por envenenamiento!"); }
    public static void mostrarDanioQuemadura(String nombre, int dano) { mostrarLinea(nombre + " recibió " + dano + " de daño por quemadura!"); }
    public static void mostrarSeDesperto(String nombre) { mostrarLinea(nombre + " se despertó!"); }
    public static void mostrarSaleDelCampo(String nombre) { mostrarLinea(nombre + " sale del campo."); }
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

    public static void mostrarMenuCrearEquipo() { mostrarLinea("\n1. Elegir pokemon\n2. Ver equipo\n3. Combatir con el equipo seleccionado"); }
    public static void mostrarMenuElegirPokemon() { mostrarLinea("\nElige un pokemon:\n1.RotomWash\n2.Garchomp\n3.Togekiss\n4.Metagross\n5.Milotic\n6.Arcanine\n7.Amoonguss\n8.Dragapult\n9.Excadrill\n10.Sylveon"); }
    public static void mostrarPokemonAgregado(int cont) { mostrarLinea("✓ Pokémon agregado. (" + cont + "/4)"); }
    public static void mostrarPokemonInexistente() { mostrarLinea("✗ ¡Ese Pokémon no existe! Elige un número del 1 al 10."); }
    public static void mostrarEquipoCompleto() { mostrarLinea("✓ Equipo completo (4/4)"); }
    public static void mostrarNecesitaPokemon(int cont) { mostrarLinea("✗ Necesitas al menos 1 Pokémon para combatir. Tienes " + cont); }
    public static void mostrarOpcionNoValida() { mostrarLinea("✗ Opción no válida"); }
    public static void mostrarNoElegistePokemon() { mostrarLinea("No elegiste ningún pokemon"); }
    public static void mostrarTuEquipoHeader() { mostrarLinea("\n=== Tu Equipo ==="); }
    public static void mostrarPokemonEquipo(String nombre) { mostrarLinea("- " + nombre); }

    public static void mostrarBatallaNoValidaCaja() {
        mostrarLinea("\n╔════════════════════════════════════════╗");
        mostrarLinea("║        ¡BATALLA NO VÁLIDA!             ║");
        mostrarLinea("╚════════════════════════════════════════╝\n");
    }
    public static void mostrarSinPokemonMensaje1(String nombre) { mostrarLinea("¡" + nombre + ", qué genio! Decidiste entrar a una batalla sin Pokémon."); }
    public static void mostrarSinPokemonMensaje2() { mostrarLinea("Tu inteligencia es como una variable null: no contiene nada útil y aun así consume memoria."); }
    public static void mostrarSinPokemonMensaje3() { mostrarLinea("Pero ahí estás, ejecutando código mental que ni siquiera compila...\n"); }
    public static void mostrarInicioBatallaCaja(String j1, String j2) {
        mostrarLinea("\n╔════════════════════════════════════════╗");
        mostrarLinea("║        ¡COMIENZA LA BATALLA!           ║");
        mostrarLinea("║ " + j1 + " vs " + j2);
        mostrarLinea("╚════════════════════════════════════════╝\n");
    }
    public static void mostrarEnviaPokemon(String entrenador, String pokemon) { mostrarLinea(entrenador + " envía a " + pokemon + "!"); }
    public static void mostrarSeparadorTurno(int turno) {
        mostrarLinea("\n═══════════════════════════════════════");
        mostrarLinea("TURNO " + turno);
        mostrarLinea("═══════════════════════════════════════");
    }
    public static void mostrarPokemonDerrotado(String nombre) { mostrarLinea("\n¡" + nombre + " ha sido derrotado!"); }
    public static void mostrarTurnoJugador(String nombre) { mostrarLinea("\n--- TURNO DE " + nombre + " ---"); }
    public static void mostrarNoPuedeAtacarPorFlinch(String nombre) { mostrarLinea(nombre + " no puede atacar debido a que retrocedió!"); }
    public static void mostrarMovimientoSinPp(String nombre) { mostrarLinea(nombre + " no tiene PP y no se puede usar."); }
    public static void mostrarUsoMovimientoEstado(String atacante, String movimiento) { mostrarLinea("\n" + atacante + " usa " + movimiento + "!"); }
    public static void mostrarAtaqueBloqueadoPorProtect(String defensor) { mostrarLinea(defensor + " está protegido y evitó el ataque!"); }
    public static void mostrarPsActual(String defensor, int ps) { mostrarLinea(defensor + " ahora tiene " + ps + " PS"); }
    public static void mostrarDebeEnviarOtroPokemon(String entrenador) { mostrarLinea("\n¡" + entrenador + " debe enviar otro Pokémon!"); }
    public static void mostrarElegirAccion(String jugador, String pokemon) { mostrarLinea(jugador + ", elige una acción para " + pokemon + ":"); }
    public static void mostrarMovimientoDisponible(int idx, String nombre, int potencia, int precision, int pp, int ppMax) {
        mostrarLinea(idx + ". " + nombre + " (Potencia: " + potencia + ", Precisión: " + precision + "%, PP: " + pp + "/" + ppMax + ")");
    }
    public static void mostrarOpcionCambiarPokemon(int opcion) { mostrarLinea(opcion + ". Cambiar Pokémon"); }
    public static void mostrarCambiandoPokemon() { mostrarLinea("CAMBIANDO_POKEMON"); }
    public static void mostrarOpcionInvalidaIntentaDeNuevo() { mostrarLinea("Opción inválida. Intenta de nuevo."); }
    public static void mostrarMovimientoSinPpElegirOtro(String nombre) { mostrarLinea(nombre + " no tiene PP. Elige otro movimiento."); }
    public static void mostrarEstadoBattle(String nombre1, String nombre2, int ps1, int ps2, int nivel1, int nivel2, Estado e1, Estado e2) {
        mostrarLinea("\n┌─────────────────────────────────────────┐");
        mostrarLinea("│ " + String.format("%-18s | %-18s", nombre1, nombre2));
        mostrarLinea("│ " + String.format("PS: %-14d | PS: %-14d", ps1, ps2));
        mostrarLinea("│ " + String.format("Nivel: %-10d | Nivel: %-10d", nivel1, nivel2));
        mostrarLinea("│ Estado: " + String.format("%-9s | Estado: %-8s", e1, e2));
        mostrarLinea("└─────────────────────────────────────────┘");
    }
    public static void mostrarFinalBatallaCaja(String derrotado, String ganador) {
        mostrarLinea("\n╔════════════════════════════════════════╗");
        mostrarLinea("║        ¡" + derrotado + " ha sido derrotado!        ");
        mostrarLinea("║ ¡" + ganador + " ha ganado la batalla!");
        mostrarLinea("╚════════════════════════════════════════╝\n");
    }
    public static void mostrarNoHayMasPokemonVivos() { mostrarLinea("\n¡No hay más Pokémon vivos para cambiar!"); }
    public static void mostrarElegirPokemonPersona(String nombrePersona) { mostrarLinea("\n" + nombrePersona + ", elige un pokémon:"); }
    public static void mostrarPokemonConPsOpcion(int idx, String nombre, int ps) { mostrarLinea(idx + ". " + nombre + " (PS: " + ps + ")"); }
    public static void mostrarOpcionInvalidaSeleccionaPrimerPokemon() { mostrarLinea("Opción inválida. Seleccionando primer pokémon..."); }

    public GameView() {
        scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        mostrarLinea("Bienvenido al juego Pokémon");
    }

    public void mostrarEquipo(Persona persona) {
        mostrarLinea("\n=== Equipo de " + persona.getNombre() + " ===");
        for (Pokemon p : persona.getListaPokemon()) {
            mostrarLinea("- " + p.getNombre());
        }
        saltoLinea();
    }


    public void iniciarSeleccionEquipo(Persona persona) {
        mostrarLinea("\n--- Selección de Equipo ---");
        persona.crearEquipo();
        mostrarEquipo(persona);
        mostrarLinea("¡Equipo confirmado! Preparándose para la batalla...");
    }
    public void seleccionarnombre(Persona persona) {
        mostrar("¿Cuál es tu nombre, entrenador?\n ");
        String nombre = scanner.nextLine();
        persona.setNombre(nombre);
        mostrarLinea("¡Suerte, " + persona.getNombre() + "!");
    }

}