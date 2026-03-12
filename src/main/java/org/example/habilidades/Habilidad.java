package org.example.habilidades;

import org.example.Pokemon;
import org.example.movimientos.Movimiento;

public abstract class Habilidad {
    protected String nombre;

    public Habilidad(String nombre) {
        this.nombre = nombre;

    }

    public void efecto(Pokemon objetivo){}
    public void efectoAlEntrar(Pokemon portador, Pokemon rival){}
    public void efectoAlRecibirDaño(Pokemon rival, int daño, Movimiento move) {}
}

