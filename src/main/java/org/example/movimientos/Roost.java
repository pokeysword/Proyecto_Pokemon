package org.example.movimientos;

import org.example.Categoria;
import org.example.Pokemon;
import org.example.Tipo;

public class Roost extends Movimiento {
    public Roost() {
        super("Roost", 0, Tipo.VOLADOR, Categoria.ESTADO, 0, 100, 10,1);

    }
    @Override
    public void efecto(Pokemon atacante, Pokemon defensor) {
        atacante.setModPs(atacante.getModPs()+(atacante.getPS()/2));
    }
}