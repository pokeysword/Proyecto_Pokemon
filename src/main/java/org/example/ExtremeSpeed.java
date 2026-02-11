package org.example;

public class ExtremeSpeed extends Movimiento {
    public ExtremeSpeed() {
        super("Extreme Speed", 2, Tipo.NORMAL, Categoria.FISICO, 80, 100, 5);}
    @Override
    public void efecto(Pokemon atacante, Pokemon defensor) {
    }
}
