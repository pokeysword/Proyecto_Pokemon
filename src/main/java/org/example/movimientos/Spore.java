package org.example.movimientos;

import org.example.Categoria;
import org.example.Estado;
import org.example.Pokemon;
import org.example.Tipo;

import java.util.Random;
//sin finalizar
public class Spore extends Movimiento {
    public Spore() {
        super("Spore", 0, Tipo.SINIESTRO, Categoria.ESPECIAL, 55, 95, 15,1);
    }
    @Override
    public void efecto(Pokemon atacante, Pokemon defensor) {
        if (!defensor.estaDebilitado()) {
            defensor.setEstado(Estado.DORMIDO);
        }
    }
}