package org.example.movimientos;

import org.example.Categoria;
import org.example.Pokemon;
import org.example.Tipo;

public class Recover extends Movimiento {
    public Recover() {
        super("Recover", 0, Tipo.NORMAL, Categoria.ESTADO, 0, 100, 10);

    }
    @Override
    public void efecto(Pokemon atacante, Pokemon defensor) {
        atacante.setModPs(atacante.getModPs()+(atacante.getPS()/2));
    }
}
