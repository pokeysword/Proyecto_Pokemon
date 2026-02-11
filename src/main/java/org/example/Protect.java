package org.example;

public class Protect extends Movimiento {
    public Protect() {
        super("Protect", 3, Tipo.NORMAL, Categoria.ESTADO, 0, 100, 10);
    }
    @Override
    public void efecto(Pokemon atacante, Pokemon defensor) {
    }
}
