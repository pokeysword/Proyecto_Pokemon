package org.example.habilidades;
import org.example.GameView;
import org.example.Pokemon;
import org.example.Tipo;
import org.example.movimientos.Movimiento;

public class Levitate extends Habilidad {
    public Levitate() {
        super("Levitate");
    }

    @Override
    public boolean esInmune(Pokemon def,Movimiento move) {
        if (move.getTipo().equals(Tipo.TIERRA)){
            GameView.mostrarLevitateInmune(def.getNombre());
            return true;
        }else{return false;}}

}
