package org.example.movimientos;

import org.example.Categoria;
import org.example.Estado;
import org.example.Pokemon;
import org.example.Tipo;

/**
 * Movimiento de estado que duerme al rival.
 */
public class Spore extends Movimiento {
    /**
     * Crea el movimiento Spore.
     */
    public Spore() {
        super("Spore", 0, Tipo.PLANTA, Categoria.ESTADO, 0, 100, 15,1);
    }

    /**
     * Aplica el estado dormido al defensor.
     *
     * @param atacante Pokemon atacante.
     * @param defensor Pokemon defensor.
     */
    @Override
    public void efecto(Pokemon atacante, Pokemon defensor) {
        if (!defensor.estaDebilitado()) {
                defensor.setEstado(Estado.DORMIDO);
                }

        }
}