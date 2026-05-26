package org.example;

import java.util.Map;

public class Main {

    static void main(String[] args) {
        // Crear la vista
        GameView gameView = new GameView();
        gameView.showWelcome();

        // Crear el jugador
        Persona jugador = new Persona();
        gameView.seleccionarnombre(jugador);
        gameView.iniciarSeleccionEquipo(jugador);

        // Crear rival automáticamente
        Persona rival = crearRival(jugador);

        // Iniciar batalla
        Battle batalla = new Battle(jugador, rival);
        batalla.iniciarBattle();
    }

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