package org.example.movimientos;

import org.example.Categoria;
import org.example.DamageCalculator;
import org.example.Pokemon;
import org.example.Tipo;

public class Earthquake extends Movimiento {
    public Earthquake() {
        super("Earthquake", 0, Tipo.TIERRA, Categoria.FISICO, 100, 100, 10,1);}
    @Override
    public void efecto(Pokemon atacante, Pokemon defensor) {
        int daño = DamageCalculator.calculateDamage(atacante, defensor, this);
        DamageCalculator.applyDamage(defensor, daño, atacante, this);
    }
}