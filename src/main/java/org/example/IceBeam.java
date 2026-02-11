package org.example;

import java.util.Random;

public class IceBeam extends Movimiento {
    public IceBeam() {
        super("Ice Beam", 0, Tipo.HIELO, Categoria.ESPECIAL, 90, 100, 10);
    }
    @Override
    public void efecto(Pokemon atacante, Pokemon defensor) {
        if (!defensor.estaDebilitado()) {
            if(defensor.getEstado()==Estado.NORMAL){
                if (new Random().nextInt(100) < 10) {
                    defensor.setEstado(Estado.CONGELADO);
                    System.out.println(defensor.getNombre() + " está quemado!");
                }
            }
        }
    }
}