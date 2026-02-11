package org.example;

import java.util.Random;

public class Recover extends Movimiento {
    public Recover() {
        super("Recover", 0, Tipo.NORMAL, Categoria.ESTADO, 0, 100, 10);

    }
    @Override
    public void efecto(Pokemon atacante, Pokemon defensor) {
        atacante.setModPs(atacante.getModPs()+(atacante.getPS()/2));
    }
}
