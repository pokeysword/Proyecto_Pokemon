package org.example.movimientos;

import org.example.Pokemon;

/**
 * Contrato para aplicar el efecto de un movimiento.
 */
public interface EfectoMovimiento {
    /**
     * Aplica el efecto del movimiento.
     *
     * @param atacante Pokemon atacante.
     * @param defensor Pokemon defensor.
     */
    void efecto(Pokemon atacante, Pokemon defensor);
}

