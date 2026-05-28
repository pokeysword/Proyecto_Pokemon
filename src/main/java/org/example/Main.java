package org.example;

import java.util.Map;

/**
 * Punto de entrada principal del juego de Pokemon.
 * Se encarga de preparar la vista, crear al jugador y al rival, y lanzar la batalla.
 */
public class Main {

    /**
     * Inicializa el juego, configura el audio y arranca el flujo principal.
     *
     * @param args argumentos de linea de comandos (no se usan).
     */
    static void main(String[] args) {
        AudioManager audioManager = new AudioManager();
        audioManager.setVolume(0.40f);

        // Crear la vista
        GameView gameView = new GameView();
        gameView.showWelcome(audioManager);

        // Crear el jugador
        Persona jugador = new Persona();
        gameView.seleccionarnombre(jugador);
        gameView.iniciarSeleccionEquipo(jugador);

        // Crear rival automáticamente
        Persona rival = crearRival(jugador);

        // Iniciar batalla
        Battle batalla = new Battle(jugador, rival, audioManager);
        batalla.iniciarBattle();
    }

    /**
     * Crea un rival basandose en el catalogo disponible del jugador.
     *
     * @param jugador jugador del que se toma el catalogo base.
     * @return rival con hasta 4 Pokemon copiados del catalogo del jugador.
     */
    static Persona crearRival(Persona jugador) {
        Persona rival = new Persona();
        rival.setNombre("Rival");

        Map<Integer, Pokemon> catalogo = jugador.getCatalogoPokemon();
        if (catalogo.isEmpty()) {
            GameView.mostrarErrorCargaPokemon("No hay datos disponibles para crear el rival.");
            return rival;
        }

        for (Pokemon base : catalogo.values()) {
            if (rival.getListaPokemon().size() >= 4) {
                break;
            }
            rival.getListaPokemon().add(base.crearCopia());
        }

        GameView.mostrarRivalFormado();
        return rival;
    }
}