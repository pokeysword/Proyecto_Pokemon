package org.example;

import java.util.Random;

public class RockSlide extends Movimiento {

    public RockSlide() {
        super("Rock Slide", 0, Tipo.ROCA, Categoria.FISICO, 75, 90, 10);

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
