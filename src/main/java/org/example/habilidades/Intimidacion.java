package org.example.habilidades;

import org.example.Pokemon;

public class Intimidacion extends Habilidad {
    public Intimidacion() {
        super("Intimidate");
    }

    @Override
    public void efectoAlEntrar(Pokemon portador, Pokemon rival) {
        rival.modificarAtk(-1);
        System.out.println(portador.getNombre() + " intimidó a " + rival.getNombre()
                + "! ¡Su Ataque bajó!");
    }

}