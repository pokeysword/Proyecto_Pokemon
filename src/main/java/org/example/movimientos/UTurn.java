package org.example.movimientos;

import org.example.Categoria;
import org.example.Pokemon;
import org.example.Tipo;


public class UTurn extends Movimiento {
    public UTurn() {
        super("U-turn", 0, Tipo.BICHO, Categoria.FISICO, 70, 100, 20);
    }
    @Override
    public void efecto(Pokemon atacante, Pokemon defensor) {
        if (!defensor.estaDebilitado()) {
            atacante.cambio();
        }
    }
}