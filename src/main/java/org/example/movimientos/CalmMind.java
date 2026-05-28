package org.example.movimientos;

import org.example.Categoria;
import org.example.Pokemon;
import org.example.Tipo;

/**
 * Movimiento de estado que sube ataque especial y defensa especial.
 */
public class CalmMind extends Movimiento {
    /**
     * Crea el movimiento Calm Mind.
     */
    public CalmMind() {
        super("Calm Mind", 0, Tipo.PSÍQUICO, Categoria.ESTADO, 0, 100, 20,1);
    }

    /**
     * Aumenta las estadisticas del atacante.
     *
     * @param atacante Pokemon atacante.
     * @param defensor Pokemon defensor.
     */
    @Override
    public void efecto(Pokemon atacante, Pokemon defensor) {
        atacante.modificarSpAtk(1);
        atacante.modificarSpDef(1);
    }
}
