package org.example.habilidades;

import org.example.Pokemon;
import org.example.Tipo;
import org.example.movimientos.Movimiento;

public abstract class Habilidad {
    protected String nombre;

    public Habilidad(String nombre) {
        this.nombre = nombre;

    }
    public void efecto(Pokemon portador){}
    public void PuedenBajarStats(Pokemon portador){}
    public void efectoalCambiar(Pokemon portador){}
    public void efectoAlEntrar(Pokemon portador, Pokemon rival){}
    public void efectoAlRecibirDaño(Pokemon rival, int daño, Movimiento move) {}
    public boolean esInmune(Pokemon def,Movimiento move){return false;}
    public Tipo antesDeCalcularDaño(Pokemon def, Movimiento move){return move.getTipo();}
    public int antesDeCalcularefecto(Pokemon def, Movimiento move){return move.getMultcrit();}
    public void efectoAlBajarStats(Pokemon portador, Pokemon rival){}
    public void alRecibirBajadaDeStat(Pokemon portador){}
}

