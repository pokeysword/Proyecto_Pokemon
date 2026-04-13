package org.example.movimientos;

import org.example.Categoria;
import org.example.Pokemon;
import org.example.Tipo;

import java.util.Random;

public class AirSlash extends Movimiento {

    public AirSlash() {
        super("Air Slash", 0, Tipo.VOLADOR, Categoria.ESPECIAL, 75, 95, 15,1);
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
