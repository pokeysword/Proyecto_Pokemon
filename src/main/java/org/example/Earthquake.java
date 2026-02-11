package org.example;

public class Earthquake extends Movimiento {
    public Earthquake() {
        super("Earthquake", 0, Tipo.TIERRA, Categoria.FISICO, 100, 100, 10);}
    @Override
    public void efecto(Pokemon atacante, Pokemon defensor) {
    }
}