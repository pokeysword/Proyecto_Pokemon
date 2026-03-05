package org.example.habilidades;

import org.example.Pokemon;

public class Intimidacion extends Habilidad {
    public Intimidacion() {
        super("Intimidate", 0);
    }

    @Override
    public void efectoAlEntrar(Pokemon portador, Pokemon rival) {
        rival.modificarAtk(-1);
        System.out.println(portador.getNombre() + " intimidó a " + rival.getNombre()
                + "! ¡Su Ataque bajó!");
    }

    @Override
    public void efecto(Pokemon objetivo) {}
}