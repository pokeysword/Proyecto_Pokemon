package org.example;

public class DragonClaw extends Movimiento {
    public DragonClaw() {
        super("Dragon Claw", 0, Tipo.DRAGÓN, Categoria.FISICO, 80, 100, 15); }

    @Override
    public void efecto(Pokemon atacante, Pokemon defensor) {
    }
}