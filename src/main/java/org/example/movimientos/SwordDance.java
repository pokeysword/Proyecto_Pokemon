package org.example.movimientos;


import org.example.Categoria;
import org.example.Pokemon;
import org.example.Tipo;

public class SwordDance extends Movimiento {
    public SwordDance() {
        super("Sword Dance", 0, Tipo.NORMAL, Categoria.ESTADO, 0, 100, 20);
    }

    @Override
    public void efecto(Pokemon atacante, Pokemon defensor) {
        atacante.modificarAtk(2);
    }
}