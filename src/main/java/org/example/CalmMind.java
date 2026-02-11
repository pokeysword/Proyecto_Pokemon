package org.example;

public class CalmMind extends Movimiento {
    public CalmMind() {
        super("Calm Mind", 0, Tipo.PSÍQUICO, Categoria.ESTADO, 0, 100, 20);
    }

    @Override
    public void efecto(Pokemon atacante, Pokemon defensor) {
        atacante.modificarSpAtk(1);
        atacante.modificarSpDef(1);
    }
}
