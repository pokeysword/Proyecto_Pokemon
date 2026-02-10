package org.example;


import java.util.List;

public class Pokemon {
    private String nombre;
    private int nivel;
    private List<Tipo> tipos;
    private Habilidad habilidad;

    private int ps;
    private int atack;
    private int defense;
    private int sAtack;
    private int sDefense;
    private int speed;

    private Estado estado;
    private List<Movimiento> movimientos;

    public String getNombre() {
        return nombre;
    }

    public int getNivel() {
        return nivel;
    }

    public List<Tipo> getTipos() {
        return tipos;
    }

    public Habilidad getHabilidad() {
        return habilidad;
    }

    public int getPs() {
        return ps;
    }

    public int getAtack() {
        return atack;
    }

    public int getDefense() {
        return defense;
    }

    public int getsAtack() {
        return sAtack;
    }

    public int getsDefense() {
        return sDefense;
    }

    public int getSpeed() {
        return speed;
    }
}
