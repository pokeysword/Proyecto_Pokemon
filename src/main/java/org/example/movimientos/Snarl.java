package org.example.movimientos;

import org.example.Categoria;
import org.example.DamageCalculator;
import org.example.Pokemon;
import org.example.Tipo;


public class Snarl extends Movimiento {
    public Snarl() {
        super("Snarl", 0, Tipo.SINIESTRO, Categoria.ESPECIAL, 55, 95, 15,1);
    }

    @Override
    public void efecto(Pokemon atacante, Pokemon defensor) {
        int daño = DamageCalculator.calculateDamage(atacante, defensor, this);
        DamageCalculator.applyDamage(defensor, daño, atacante, this);
        
        if (!defensor.estaDebilitado()) {
            defensor.modificarSpAtk(-1);
        }
    }
}