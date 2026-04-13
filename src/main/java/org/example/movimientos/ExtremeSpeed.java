package org.example.movimientos;

import org.example.Categoria;
import org.example.DamageCalculator;
import org.example.Pokemon;
import org.example.Tipo;

public class ExtremeSpeed extends Movimiento {
    public ExtremeSpeed() {
        super("Extreme Speed", 2, Tipo.NORMAL, Categoria.FISICO, 80, 100, 5,1);}
    @Override
    public void efecto(Pokemon atacante, Pokemon defensor) {
        int daño = DamageCalculator.calculateDamage(atacante, defensor, this);
        DamageCalculator.applyDamage(defensor, daño, atacante, this);
    }
}
