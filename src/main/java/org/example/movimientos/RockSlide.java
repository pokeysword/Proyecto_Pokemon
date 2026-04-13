package org.example.movimientos;

import org.example.Categoria;
import org.example.Pokemon;
import org.example.Tipo;

import java.util.Random;

public class RockSlide extends Movimiento {

    public RockSlide() {
        super("Rock Slide", 0, Tipo.ROCA, Categoria.FISICO, 75, 90, 10,1);

    }

    @Override
    public void efecto(Pokemon atacante, Pokemon defensor) {
        int daño = DamageCalculator.calculateDamage(atacante, defensor, this);
        DamageCalculator.applyDamage(defensor, daño, atacante, this);
        
        if (!defensor.estaDebilitado()) {
            if (new Random().nextInt(100) < 30) {
                defensor.setFlinch(true);
                System.out.println(defensor.getNombre() + " retrocedió (flinch)!");
            }
        }
    }
}
