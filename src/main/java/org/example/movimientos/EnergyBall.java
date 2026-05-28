package org.example.movimientos;

import org.example.Categoria;
import org.example.DamageCalculator;
import org.example.Pokemon;
import org.example.Tipo;

import java.util.Random;

/**
 * Movimiento especial de tipo Planta con posible bajada de defensa especial.
 */
public class EnergyBall extends Movimiento {
    /**
     * Crea el movimiento Energy Ball.
     */
    public EnergyBall() {
        super("Energy Ball", 0, Tipo.PLANTA, Categoria.ESPECIAL, 90, 100, 10,1);
        }
    /**
     * Aplica dano y puede bajar la defensa especial.
     *
     * @param atacante Pokemon atacante.
     * @param defensor Pokemon defensor.
     */
    @Override
    public void efecto(Pokemon atacante, Pokemon defensor) {
        int daño = DamageCalculator.calculateDamage(atacante, defensor, this);
        DamageCalculator.applyDamage(defensor, daño, atacante, this);

        if (!defensor.estaDebilitado()) {
            if (new Random().nextInt(100) < 10) {
                defensor.modificarSpDef(-1);
            }
        }
    }
}
