package org.example;

public abstract class Habilidad {
    protected String nombre;
    protected int prioridad;

    public Habilidad(String nombre, int prioridad) {
        this.nombre = nombre;
        this.prioridad = prioridad;
    }

    public abstract void efecto(Pokemon objetivo);
}

