package org.example.movimientos;

import org.example.Categoria;
import org.example.DamageCalculator;
import org.example.Pokemon;
import org.example.Tipo;


/**
 * Movimiento especial de tipo Siniestro que baja ataque especial.
 */
public class Snarl extends Movimiento {
    /**
     * Crea el movimiento Snarl.
     */
    public Snarl() {
        super("Snarl", 0, Tipo.SINIESTRO, Categoria.ESPECIAL, 55, 95, 15,1);
    }

    /**
     * Aplica dano y baja el ataque especial del defensor.
     *
     * @param atacante Pokemon atacante.
     * @param defensor Pokemon defensor.
     */
    @Override
    public void efecto(Pokemon atacante, Pokemon defensor) {
        int daño = DamageCalculator.calculateDamage(atacante, defensor, this);
        DamageCalculator.applyDamage(defensor, daño, atacante, this);
        
        if (!defensor.estaDebilitado()) {
            defensor.modificarSpAtk(-1);
        }
    }
}