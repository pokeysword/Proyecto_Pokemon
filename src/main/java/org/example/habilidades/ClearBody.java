package org.example.habilidades;
import org.example.Pokemon;

/**
 * Habilidad que impide bajar stats.
 */
public class ClearBody extends Habilidad {
    /**
     * Crea la habilidad ClearBody.
     */
    public ClearBody() {
        super("ClearBody");
    }

    /**
     * Bloquea la bajada de stats del portador.
     *
     * @param portador Pokemon portador.
     */
    @Override
    public void PuedenBajarStats(Pokemon portador) {
        portador.setBajarStats(false);
    }
}