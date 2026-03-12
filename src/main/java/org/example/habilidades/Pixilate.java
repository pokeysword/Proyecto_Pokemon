package org.example.habilidades;
import org.example.Pokemon;
import org.example.Tipo;
import org.example.movimientos.Movimiento;

public class Pixilate extends Habilidad {
    public Pixilate() {
        super("Pixilate");
    }

    @Override
    public Tipo antesDeCalcularDaño(Pokemon def,Movimiento move) {
        if (move.getTipo().equals(Tipo.NORMAL)){
            return Tipo.HADA;
        }else {
            return move.getTipo();
        }
}}
