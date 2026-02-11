package org.example;

public class Placaje extends Movimiento {

    public Placaje() {
        super("Placaje", 0, Tipo.NORMAL, Categoria.FISICO, 40, 100,40);
    }

    @Override
    public void efecto(Pokemon atacante, Pokemon defensor) {

        System.out.println(atacante + " usó Placaje");

    }
}
