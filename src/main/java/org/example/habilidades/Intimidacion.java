package org.example.habilidades;

import org.example.GameView;
import org.example.Pokemon;

/**
 * Habilidad que baja el ataque del rival al entrar.
 */
public class Intimidacion extends Habilidad {
    /**
     * Crea la habilidad Intimidacion.
     */
    public Intimidacion() {
        super("Intimidate");
    }

    /**
     * Aplica la bajada de ataque al rival.
     *
     * @param portador Pokemon portador.
     * @param rival Pokemon rival.
     */
    @Override
    public void efectoAlEntrar(Pokemon portador, Pokemon rival) {
        rival.modificarAtk(-1);
        GameView.mostrarIntimidacion(portador.getNombre(), rival.getNombre());
    }

}