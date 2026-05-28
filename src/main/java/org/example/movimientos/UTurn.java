package org.example.movimientos;

import org.example.Categoria;
import org.example.DamageCalculator;
import org.example.Pokemon;
import org.example.Tipo;


/**
 * Movimiento fisico de tipo Bicho que permite cambiar.
 */
public class UTurn extends Movimiento {
    /**
     * Crea el movimiento U-turn.
     */
    public UTurn() {
        super("U-turn", 0, Tipo.BICHO, Categoria.FISICO, 70, 100, 20,1);
    }

    /**
     * Aplica dano y marca cambio del atacante.
     *
     * @param atacante Pokemon atacante.
     * @param defensor Pokemon defensor.
     */
    @Override
    public void efecto(Pokemon atacante, Pokemon defensor) {
        int daño = DamageCalculator.calculateDamage(atacante, defensor, this);
        DamageCalculator.applyDamage(defensor, daño, atacante, this);
        
        if (!defensor.estaDebilitado()) {
            atacante.cambio();
        }
    }
}