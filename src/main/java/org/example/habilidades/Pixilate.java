package org.example.habilidades;

import org.example.Pokemon;
import org.example.Tipo;
import org.example.movimientos.Movimiento;

/**
 * Habilidad que convierte movimientos normales en tipo Hada.
 */
public class Pixilate extends Habilidad {
    /**
     * Crea la habilidad Pixilate.
     */
    public Pixilate() {
        super("Pixilate");
    }

    /**
     * Convierte el tipo antes de calcular dano si aplica.
     *
     * @param def Pokemon defensor.
     * @param move movimiento usado.
     * @return tipo efectivo.
     */
    @Override
    public Tipo antesDeCalcularDaño(Pokemon def, Movimiento move) {
        if (move.getTipo().equals(Tipo.NORMAL)) {
            return Tipo.HADA;
        } else {
            return move.getTipo();
        }
    }
}
