package org.example.movimientos;

import org.example.Categoria;
import org.example.Pokemon;
import org.example.Tipo;

public class Protect extends Movimiento {
    public Protect() {
        super("Protect", 3, Tipo.NORMAL, Categoria.ESTADO, 0, 100, 10,1);
    }
    @Override
    public void efecto(Pokemon atacante, Pokemon defensor) {
        atacante.setProtected(true);
        System.out.println(atacante.getNombre() + " se protegió del siguiente ataque!");
    }
}
