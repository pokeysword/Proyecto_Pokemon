package org.example;

import java.util.Random;

public class IronHead extends Movimiento {

    public IronHead() {
        super("Iron Head", 0, Tipo.ACERO, Categoria.FISICO, 80, 100, 15);
    }

    @Override
    public void efecto(Pokemon atacante, Pokemon defensor) {
        if (!defensor.estaDebilitado()) {
            if (new Random().nextInt(100) < 30) {
                defensor.setFlinch(true);
                System.out.println(defensor.getNombre() + " retrocedió (flinch)!");
            }
        }
    }
}
