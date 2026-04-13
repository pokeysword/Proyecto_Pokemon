package org.example.movimientos;

import org.example.Categoria;
import org.example.Estado;
import org.example.Pokemon;
import org.example.Tipo;

public class Spore extends Movimiento {
    public Spore() {
        super("Spore", 0, Tipo.PLANTA, Categoria.ESTADO, 0, 100, 15,1);
    }
    @Override
    public void efecto(Pokemon atacante, Pokemon defensor) {
        if (!defensor.estaDebilitado()) {
                defensor.setEstado(Estado.DORMIDO);
                }

        }
}