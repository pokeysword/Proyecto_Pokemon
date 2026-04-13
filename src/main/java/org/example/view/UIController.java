package org.example.view;

import org.example.Persona;

/**
 * Controlador de la interfaz de usuario
 * Gestiona las transiciones entre diferentes vistas
 */
public class UIController {
    private GameWindow gameWindow;

    public UIController(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
    }

    /**
     * Inicia el flujo del juego desde la pantalla de bienvenida
     */
    public void iniciarJuego() {
        gameWindow.mostrarSeleccionEquipo();
    }

    /**
     * Navega a la pantalla de batalla
     */
    public void iniciarBatalla(Persona jugador, Persona rival) {
        gameWindow.setRival(rival);
        gameWindow.mostrarBatalla();
    }

    /**
     * Muestra los detalles de un Pokémon
     */
    public void mostrarDetailsPokemon(org.example.Pokemon pokemon) {
        PokemonDetailView detailView = new PokemonDetailView(pokemon);
        detailView.show();
    }
}
