package org.example.movimientos;


import org.example.Categoria;
import org.example.Estado;
import org.example.Pokemon;
import org.example.Tipo;


public class WillOWisp extends Movimiento {
    public WillOWisp() {
        super("Will-O-Wisp", 0, Tipo.FUEGO, Categoria.ESTADO, 0, 85, 15,1);
    }
    @Override
    public void efecto(Pokemon atacante, Pokemon defensor) {
        if (!defensor.estaDebilitado()) {
            defensor.setEstado(Estado.QUEMADO);
        }
    }
}