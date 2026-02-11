package org.example;

public class DazzlingGleam extends Movimiento {
    public DazzlingGleam() {
        super("Dazzling Gleam", 0, Tipo.HADA, Categoria.ESPECIAL, 80, 100, 10);
    }

    @Override
    public void efecto(Pokemon atacante, Pokemon defensor) {
    }
}