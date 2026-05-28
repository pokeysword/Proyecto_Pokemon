package org.example.movimientos;

import org.example.Categoria;
import org.example.DamageCalculator;
import org.example.Pokemon;
import org.example.Tipo;

/**
 * Movimiento fisico de alta prioridad.
 */
public class ExtremeSpeed extends Movimiento {
    /**
     * Crea el movimiento Extreme Speed.
     */
    public ExtremeSpeed() {
        super("Extreme Speed", 2, Tipo.NORMAL, Categoria.FISICO, 80, 100, 5,1);
    }

    /**
     * Aplica dano fisico al defensor.
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
