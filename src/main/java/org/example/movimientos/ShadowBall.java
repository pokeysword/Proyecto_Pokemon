package org.example.movimientos;

import org.example.Categoria;
import org.example.Pokemon;
import org.example.Tipo;

import java.util.Random;

public class ShadowBall extends Movimiento {
    public ShadowBall() {
        super("Shadow Ball", 0, Tipo.FANTASMA, Categoria.ESPECIAL, 80, 100, 15,1);
    }
    @Override
    public void efecto(Pokemon atacante, Pokemon defensor) {
        if (!defensor.estaDebilitado()) {
            if (new Random().nextInt(100) < 20) {
                defensor.modificarSpDef(-1);
            }
        }
    }
}