package org.example.movimientos;

import org.example.Categoria;
import org.example.DamageCalculator;
import org.example.Pokemon;
import org.example.Tipo;

import java.util.Random;

public class EnergyBall extends Movimiento {
    public EnergyBall() {
        super("Energy Ball", 0, Tipo.PLANTA, Categoria.ESPECIAL, 90, 100, 10,1);
        }
    @Override
    public void efecto(Pokemon atacante, Pokemon defensor) {
        int daño = DamageCalculator.calculateDamage(atacante, defensor, this);
        DamageCalculator.applyDamage(defensor, daño, atacante, this);
        
        if (!defensor.estaDebilitado()) {
            if (new Random().nextInt(100) < 10) {
                defensor.modificarSpDef(-1);
                }
            }
        }
    }