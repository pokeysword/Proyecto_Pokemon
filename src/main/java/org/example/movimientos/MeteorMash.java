package org.example.movimientos;

import org.example.Categoria;
import org.example.Pokemon;
import org.example.Tipo;

import java.util.Random;

public class MeteorMash extends Movimiento {
    public MeteorMash() {
        super("Meteor Mash", 0, Tipo.ACERO, Categoria.FISICO, 90, 90, 10,1);

    }
    @Override
    public void efecto(Pokemon atacante, Pokemon defensor) {
        if (!defensor.estaDebilitado()) {
            if (new Random().nextInt(100) < 20) {
                atacante.modificarAtk(1);
            }
        }
    }
}
