package org.example.habilidades;
import org.example.GameView;
import org.example.Pokemon;
import org.example.Tipo;
import org.example.movimientos.Movimiento;

/**
 * Habilidad que otorga inmunidad a movimientos de tipo Tierra.
 */
public class Levitate extends Habilidad {
    /**
     * Crea la habilidad Levitate.
     */
    public Levitate() {
        super("Levitate");
    }

    /**
     * Determina inmunidad frente a movimientos de tipo Tierra.
     *
     * @param def Pokemon defensor.
     * @param move movimiento recibido.
     * @return true si es inmune.
     */
    @Override
    public boolean esInmune(Pokemon def,Movimiento move) {
        if (move.getTipo().equals(Tipo.TIERRA)) {
            GameView.mostrarLevitateInmune(def.getNombre());
            return true;
        }
        return false;
    }
}
