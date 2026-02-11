package org.example;

import java.util.Random;

public class MeteorMash extends Movimiento {
    public MeteorMash() {
        super("Meteor Mash", 0, Tipo.ACERO, Categoria.FISICO, 90, 90, 10);

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
