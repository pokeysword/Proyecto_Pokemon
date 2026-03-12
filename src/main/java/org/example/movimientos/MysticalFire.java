package org.example.movimientos;

import org.example.Categoria;
import org.example.Pokemon;
import org.example.Tipo;

public class MysticalFire extends Movimiento {
    public MysticalFire() {
        super("Mystical Fire", 0, Tipo.FUEGO, Categoria.ESPECIAL, 75, 100, 10,1);

    }
    @Override
    public void efecto(Pokemon atacante, Pokemon defensor) {
        if (!defensor.estaDebilitado()) {
                defensor.modificarSpAtk(-1);
        }
    }
}
