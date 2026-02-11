package org.example;

import java.util.Random;

public class AirSlash extends Movimiento {

    public AirSlash() {
        super("Air Slash", 0, Tipo.VOLADOR, Categoria.ESPECIAL, 75, 95, 15);
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
