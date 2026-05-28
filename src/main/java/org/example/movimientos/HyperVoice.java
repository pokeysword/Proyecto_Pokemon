package org.example.movimientos;

import org.example.Categoria;
import org.example.DamageCalculator;
import org.example.Pokemon;
import org.example.Tipo;

/**
 * Movimiento especial de tipo Normal.
 */
public class HyperVoice extends Movimiento {
    /**
     * Crea el movimiento Hyper Voice.
     */
    public HyperVoice() {
        super("Hyper Voice", 0, Tipo.NORMAL, Categoria.ESPECIAL, 90, 100, 10,1);

    }
    /**
     * Aplica dano al defensor.
     *
     * @param atacante Pokemon atacante.
     * @param defensor Pokemon defensor.
     */
    @Override
    public void efecto(Pokemon atacante, Pokemon defensor) {
        int daño = DamageCalculator.calculateDamage(atacante, defensor, this);
        DamageCalculator.applyDamage(defensor, daño, atacante, this);
    }
}