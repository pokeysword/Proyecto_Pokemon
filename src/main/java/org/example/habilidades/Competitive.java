package org.example.habilidades;

import org.example.GameView;
import org.example.Pokemon;

/**
 * Habilidad que aumenta el ataque especial al bajar stats.
 */
public class Competitive extends Habilidad {
    /**
     * Crea la habilidad Competitive.
     */
    public Competitive() {
        super("Competitive");
    }

    /**
     * Aumenta el ataque especial al recibir bajada de stats.
     *
     * @param portador Pokemon portador.
     */
    @Override
    public void alRecibirBajadaDeStat(Pokemon portador) {
        portador.modificarSpAtk(2);
        GameView.mostrarCompetitive(portador.getNombre());
    }
}
