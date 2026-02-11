package org.example;

import java.util.Random;

public class EnergyBall extends Movimiento {
    public EnergyBall() {
        super("Energy Ball", 0, Tipo.PLANTA, Categoria.ESPECIAL, 90, 100, 10);
        }
    @Override
    public void efecto(Pokemon atacante, Pokemon defensor) {
        if (!defensor.estaDebilitado()) {
            if (new Random().nextInt(100) < 10) {
                defensor.modificarSpDef(-1);
                }
            }
        }
    }