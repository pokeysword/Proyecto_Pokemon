package org.example.movimientos;

import org.example.Categoria;
import org.example.DamageCalculator;
import org.example.Pokemon;
import org.example.Tipo;

/**
 * Movimiento especial de tipo Hada.
 */
public class DazzlingGleam extends Movimiento {
    /**
     * Crea el movimiento Dazzling Gleam.
     */
    public DazzlingGleam() {
        super("Dazzling Gleam", 0, Tipo.HADA, Categoria.ESPECIAL, 80, 100, 10,1);
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