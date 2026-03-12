package org.example.movimientos;

import org.example.Categoria;
import org.example.Pokemon;
import org.example.Tipo;

public class HyperVoice extends Movimiento {
    public HyperVoice() {
        super("Hyper Voice", 0, Tipo.NORMAL, Categoria.ESPECIAL, 90, 100, 10,1);

    }
    @Override
    public void efecto(Pokemon atacante, Pokemon defensor) {

    }
}