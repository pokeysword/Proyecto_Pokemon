package org.example.movimientos;

import org.example.Categoria;
import org.example.DamageCalculator;
import org.example.Pokemon;
import org.example.Tipo;

import java.util.Random;

/**
 * Movimiento fisico de tipo Acero con posible aumento de ataque.
 */
public class MeteorMash extends Movimiento {
    /**
     * Crea el movimiento Meteor Mash.
     */
    public MeteorMash() {
        super("Meteor Mash", 0, Tipo.ACERO, Categoria.FISICO, 90, 90, 10,1);

    }
    /**
     * Aplica dano y puede subir el ataque del atacante.
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
                atacante.modificarAtk(1);
            }
        }
    }
}
