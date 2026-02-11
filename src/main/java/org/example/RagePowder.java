package org.example;

public class RagePowder extends Movimiento {
    public RagePowder() {
        super("Rage Powder", 0, Tipo.BICHO, Categoria.ESTADO, 0, 100, 20);

    }

    @Override
    public void efecto(Pokemon atacante, Pokemon defensor) {
    }
}
