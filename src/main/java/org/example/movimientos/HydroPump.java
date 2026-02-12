package org.example.movimientos;

import org.example.Categoria;
import org.example.Pokemon;
import org.example.Tipo;

public class HydroPump extends Movimiento {
    public HydroPump() {
        super("Hydro Pump", 0, Tipo.AGUA, Categoria.ESPECIAL, 110, 80, 5);

    }
    @Override
    public void efecto(Pokemon atacante, Pokemon defensor) {

    }
}