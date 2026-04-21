package org.example.habilidades;

import org.example.GameView;
import org.example.Pokemon;

public class Intimidacion extends Habilidad {
    public Intimidacion() {
        super("Intimidate");
    }

    @Override
    public void efectoAlEntrar(Pokemon portador, Pokemon rival) {
        rival.modificarAtk(-1);
        GameView.mostrarIntimidacion(portador.getNombre(), rival.getNombre());
    }

}