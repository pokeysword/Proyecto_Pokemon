package org.example.habilidades;

import org.example.Categoria;
import org.example.GameView;
import org.example.Pokemon;
import org.example.movimientos.Movimiento;

/**
 * Habilidad que devuelve dano al recibir ataque fisico.
 */
public class RoughSkin extends Habilidad {
    /**
     * Crea la habilidad RoughSkin.
     */
    public RoughSkin() {
        super("RoughSkin");
    }

    /**
     * Aplica dano de retorno por ataques fisicos.
     *
     * @param rival Pokemon rival.
     * @param daño dano recibido.
     * @param move movimiento que causa dano.
     */
    @Override
    public void efectoAlRecibirDaño(Pokemon rival, int daño, Movimiento move) {
        if (move.getCategoria().equals(Categoria.FISICO)){
            int dañoRetorno = Math.max(1, daño / 8);
            rival.sufrirDaño(dañoRetorno);
            GameView.mostrarRoughSkin(rival.getNombre(), dañoRetorno);
        }
    }

}
