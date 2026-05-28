package org.example.movimientos;

import org.example.Categoria;
import org.example.DamageCalculator;
import org.example.Pokemon;
import org.example.Tipo;

/**
 * Movimiento especial de tipo Agua.
 */
public class HydroPump extends Movimiento {
    /**
     * Crea el movimiento Hydro Pump.
     */
    public HydroPump() {
        super("Hydro Pump", 0, Tipo.AGUA, Categoria.ESPECIAL, 110, 80, 5,1);

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