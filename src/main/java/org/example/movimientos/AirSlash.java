package org.example.movimientos;
import org.example.DamageCalculator;
import org.example.Categoria;
import org.example.GameView;
import org.example.Pokemon;
import org.example.Tipo;

import java.util.Random;

/**
 * Movimiento especial de tipo Volador con probabilidad de retroceso.
 */
public class AirSlash extends Movimiento {

    /**
     * Crea el movimiento Air Slash.
     */
    public AirSlash() {
        super("Air Slash", 0, Tipo.VOLADOR, Categoria.ESPECIAL, 75, 95, 15,1);
    }

    /**
     * Aplica dano y puede causar retroceso.
     *
     * @param atacante Pokemon atacante.
     * @param defensor Pokemon defensor.
     */
    @Override
    public void efecto(Pokemon atacante, Pokemon defensor) {
        int daño = DamageCalculator.calculateDamage(atacante, defensor, this);
        DamageCalculator.applyDamage(defensor, daño, atacante, this);
        
        if (!defensor.estaDebilitado()) {
            if (new Random().nextInt(100) < 30) {
                defensor.setFlinch(true);
                GameView.mostrarPokemonRetrocedio(defensor.getNombre());
            }
        }
    }
}
