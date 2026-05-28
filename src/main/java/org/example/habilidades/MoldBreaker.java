package org.example.habilidades;

import org.example.GameView;
import org.example.Pokemon;

/**
 * Habilidad que muestra mensaje al activarse.
 */
public class MoldBreaker extends Habilidad {
    /**
     * Crea la habilidad MoldBreaker.
     */
    public MoldBreaker() {
        super("MoldBreaker");
    }

    /**
     * Muestra el mensaje de habilidad.
     *
     * @param def Pokemon portador.
     */
    @Override
    public void efecto(Pokemon def) {
        GameView.mostrarMoldBreaker(def.getNombre());
    }
}
