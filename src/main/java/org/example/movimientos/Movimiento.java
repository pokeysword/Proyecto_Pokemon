package org.example.movimientos;

import org.example.Categoria;
import org.example.Pokemon;
import org.example.Tipo;

public abstract class Movimiento {
    protected String nombre;
    protected int prioridad;
    protected Tipo tipo;
    protected Categoria categoria;
    protected int potencia;
    protected int precision;
    protected int pp;

    public Movimiento(String nombre, int prioridad, Tipo tipo, Categoria categoria, int potencia, int precision, int pp) {
        this.nombre = nombre;
        this.prioridad = prioridad;
        this.tipo = tipo;
        this.categoria = categoria;
        this.potencia = potencia;
        this.precision = precision;
        this.pp = pp;
    }

    public abstract void efecto(Pokemon atacante, Pokemon defensor);

}
