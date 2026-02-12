package org.example.movimientos;

import org.example.*;

import java.util.Random;

public class Scald extends Movimiento {
    public Scald() {
        super("Scald", 0, Tipo.AGUA, Categoria.ESPECIAL, 80, 100, 15);}
    @Override
    public void efecto(Pokemon atacante, Pokemon defensor) {
        if (!defensor.estaDebilitado()) {
            if(defensor.getEstado()== Estado.NORMAL){
                if (new Random().nextInt(100) < 30) {
                    defensor.setEstado(Estado.QUEMADO);
                    System.out.println(defensor.getNombre() + " está quemado!");
                }
            }
        }
    }
}
