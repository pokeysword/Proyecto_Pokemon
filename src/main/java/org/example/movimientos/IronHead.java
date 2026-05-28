package org.example.movimientos;
import org.example.DamageCalculator;
import org.example.Categoria;
import org.example.GameView;
import org.example.Pokemon;
import org.example.Tipo;

import java.util.Random;

/**
 * Movimiento fisico de tipo Acero con probabilidad de retroceso.
 */
public class IronHead extends Movimiento {

    /**
     * Crea el movimiento Iron Head.
     */
    public IronHead() {
        super("Iron Head", 0, Tipo.ACERO, Categoria.FISICO, 80, 100, 15,1);
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
