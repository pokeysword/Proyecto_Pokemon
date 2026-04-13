package org.example.movimientos;

import org.example.Categoria;
import org.example.DamageCalculator;
import org.example.Pokemon;
import org.example.Tipo;

public class DazzlingGleam extends Movimiento {
    public DazzlingGleam() {
        super("Dazzling Gleam", 0, Tipo.HADA, Categoria.ESPECIAL, 80, 100, 10,1);
    }

    @Override
    public void efecto(Pokemon atacante, Pokemon defensor) {
        int daño = DamageCalculator.calculateDamage(atacante, defensor, this);
        DamageCalculator.applyDamage(defensor, daño, atacante, this);
    }
}