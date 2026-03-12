package org.example.habilidades;
import org.example.Categoria;
import org.example.Pokemon;
import org.example.movimientos.Movimiento;

public class RoughSkin extends Habilidad {
    public RoughSkin() {
        super("RoughSkin");
    }

    @Override
    public void efectoAlRecibirDaño(Pokemon rival, int daño, Movimiento move) {
        if (move.getCategoria().equals(Categoria.FISICO)){
        rival.setModPs((daño)/(rival.getModPs())-(daño/16));
        System.out.println(rival.getNombre() + " se hizo daño al golpear");
    }}

}
