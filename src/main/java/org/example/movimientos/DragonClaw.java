package org.example.movimientos;

import org.example.Categoria;
import org.example.DamageCalculator;
import org.example.Pokemon;
import org.example.Tipo;

/**
 * Movimiento fisico de tipo Dragon.
 */
public class DragonClaw extends Movimiento {
    /**
     * Crea el movimiento Dragon Claw.
     */
    public DragonClaw() {
        super("Dragon Claw", 0, Tipo.DRAGÓN, Categoria.FISICO, 80, 100, 15,1); }

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