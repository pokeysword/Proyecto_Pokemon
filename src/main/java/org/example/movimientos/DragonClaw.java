package org.example.movimientos;

import org.example.Categoria;
import org.example.Pokemon;
import org.example.Tipo;

public class DragonClaw extends Movimiento {
    public DragonClaw() {
        super("Dragon Claw", 0, Tipo.DRAGÓN, Categoria.FISICO, 80, 100, 15,1); }

    @Override
    public void efecto(Pokemon atacante, Pokemon defensor) {
    }
}