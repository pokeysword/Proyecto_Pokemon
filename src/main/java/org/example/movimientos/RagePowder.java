package org.example.movimientos;

import org.example.Categoria;
import org.example.Pokemon;
import org.example.Tipo;

/**
 * Movimiento de estado sin efecto directo en esta implementacion.
 */
public class RagePowder extends Movimiento {
    /**
     * Crea el movimiento Rage Powder.
     */
    public RagePowder() {
        super("Rage Powder", 0, Tipo.BICHO, Categoria.ESTADO, 0, 100, 20,1);

    }

    /**
     * No aplica efecto directo.
     *
     * @param atacante Pokemon atacante.
     * @param defensor Pokemon defensor.
     */
    @Override
    public void efecto(Pokemon atacante, Pokemon defensor) {
    }
}
