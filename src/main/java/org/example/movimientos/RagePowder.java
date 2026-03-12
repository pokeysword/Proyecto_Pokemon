package org.example.movimientos;

import org.example.Categoria;
import org.example.Pokemon;
import org.example.Tipo;

public class RagePowder extends Movimiento {
    public RagePowder() {
        super("Rage Powder", 0, Tipo.BICHO, Categoria.ESTADO, 0, 100, 20,1);

    }

    @Override
    public void efecto(Pokemon atacante, Pokemon defensor) {
    }
}
