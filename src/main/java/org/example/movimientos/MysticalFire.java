package org.example.movimientos;

import org.example.Categoria;
import org.example.DamageCalculator;
import org.example.Pokemon;
import org.example.Tipo;

/**
 * Movimiento especial de tipo Fuego que baja ataque especial.
 */
public class MysticalFire extends Movimiento {
    /**
     * Crea el movimiento Mystical Fire.
     */
    public MysticalFire() {
        super("Mystical Fire", 0, Tipo.FUEGO, Categoria.ESPECIAL, 75, 100, 10,1);

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
