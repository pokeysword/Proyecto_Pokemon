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
    protected int multcrit;

    public Movimiento(String nombre, int prioridad, Tipo tipo, Categoria categoria, int potencia, int precision, int pp, int multcrit) {
        this.nombre = nombre;
        this.prioridad = prioridad;
        this.tipo = tipo;
        this.categoria = categoria;
        this.potencia = potencia;
        this.precision = precision;
        this.pp = pp;
        this.multcrit=multcrit;
    }

    public abstract void efecto(Pokemon atacante, Pokemon defensor);

    public Categoria getCategoria() {
        return categoria;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public int getMultcrit() {
        return multcrit;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPotencia() {
        return potencia;
    }

    public int getPrecision() {
        return precision;
    }

    public int getPp() {
        return pp;
    }

    public int getPrioridad() {
        return prioridad;
    }
}
