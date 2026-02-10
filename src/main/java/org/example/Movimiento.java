package org.example;

public abstract class Movimiento {
    protected String nombre;
    protected int prioridad;
    protected Tipo tipo;
    protected Categoria categoria;
    protected int potencia;
    protected int precision;

    public Movimiento(String nombre, int prioridad, Tipo tipo,Categoria categoria, int potencia,int precision){
        this.nombre=nombre;
        this.prioridad=prioridad;
        this.tipo=tipo;
        this.categoria=categoria;
        this.potencia=potencia;
        this.precision=precision;
    }
    public abstract void efecto(Pokemon atacante, Pokemon defensor);

}
