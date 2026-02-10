package org.example;

public class Placaje extends Movimiento {

    public Placaje() {
        super("Placaje", 0, Tipo.NORMAL, Categoria.FISICO, 40, 100);
    }

    @Override
    public void efecto(Pokemon atacante, Pokemon defensor) {
        int damage = (((2*atacante.getNivel())/5)+2*this.potencia*(atacante.getAtack()/defensor.getDefense()))/50;
        System.out.println(atacante + " usó Placaje");
    }
}
