package org.example.movimientos;

import org.example.Categoria;
import org.example.Pokemon;
import org.example.Tipo;

public class CalmMind extends Movimiento {
    public CalmMind() {
        super("Calm Mind", 0, Tipo.PSÍQUICO, Categoria.ESTADO, 0, 100, 20,1);
    }

    @Override
    public void efecto(Pokemon atacante, Pokemon defensor) {
        atacante.modificarSpAtk(1);
        atacante.modificarSpDef(1);
    }
}
