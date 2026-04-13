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
            int dañoRetorno = Math.max(1, daño / 8);
            rival.sufrirDaño(dañoRetorno);
            System.out.println(rival.getNombre() + " recibió " + dañoRetorno + " de daño por RoughSkin!");
        }
    }

}
