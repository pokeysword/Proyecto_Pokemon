package org.example;

public class HydroPump extends Movimiento {
    public HydroPump() {
        super("Hydro Pump", 0, Tipo.AGUA, Categoria.ESPECIAL, 110, 80, 5);

    }
    @Override
    public void efecto(Pokemon atacante, Pokemon defensor) {

    }
}