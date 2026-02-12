package org.example.movimientos;

import org.example.Categoria;
import org.example.Pokemon;
import org.example.Tipo;

public class Earthquake extends Movimiento {
    public Earthquake() {
        super("Earthquake", 0, Tipo.TIERRA, Categoria.FISICO, 100, 100, 10);}
    @Override
    public void efecto(Pokemon atacante, Pokemon defensor) {
    }
}