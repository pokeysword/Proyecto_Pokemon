package org.example.movimientos;

import org.example.Categoria;
import org.example.DamageCalculator;
import org.example.Pokemon;
import org.example.Tipo;

public class HydroPump extends Movimiento {
    public HydroPump() {
        super("Hydro Pump", 0, Tipo.AGUA, Categoria.ESPECIAL, 110, 80, 5,1);

    }
    @Override
    public void efecto(Pokemon atacante, Pokemon defensor) {
        int daño = DamageCalculator.calculateDamage(atacante, defensor, this);
        DamageCalculator.applyDamage(defensor, daño, atacante, this);
    }
}