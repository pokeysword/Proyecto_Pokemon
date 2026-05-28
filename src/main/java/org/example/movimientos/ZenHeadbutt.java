package org.example.movimientos;
import org.example.DamageCalculator;
import org.example.Categoria;
import org.example.GameView;
import org.example.Pokemon;
import org.example.Tipo;

import java.util.Random;


/**
 * Movimiento fisico de tipo Psiquico con probabilidad de retroceso.
 */
public class ZenHeadbutt extends Movimiento {
    /**
     * Crea el movimiento Zen Headbutt.
     */
    public ZenHeadbutt() {
        super("Zen Headbutt", 0, Tipo.PSÍQUICO, Categoria.FISICO, 80, 90, 15,1);
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
            if (new Random().nextInt(100) < 20) {
                defensor.setFlinch(true);
                GameView.mostrarPokemonRetrocedio(defensor.getNombre());
            }
        }
    }
}
