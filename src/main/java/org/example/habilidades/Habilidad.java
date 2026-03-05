package org.example.habilidades;

import org.example.Pokemon;

public abstract class Habilidad {
    protected String nombre;
    protected int prioridad;

    public Habilidad(String nombre, int prioridad) {
        this.nombre = nombre;
        this.prioridad = prioridad;
    }

    public void efecto(Pokemon objetivo){}
    public void efectoAlEntrar(Pokemon portador, Pokemon rival){}
}

