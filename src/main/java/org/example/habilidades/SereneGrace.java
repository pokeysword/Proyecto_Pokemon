package org.example.habilidades;

import org.example.Pokemon;
import org.example.movimientos.Movimiento;

/**
 * Habilidad que mejora la probabilidad de efectos secundarios.
 */
public class SereneGrace extends Habilidad {
    /**
     * Crea la habilidad SereneGrace.
     */
    public SereneGrace() {
        super("SereneGrace");
    }

    /**
     * Ajusta el multiplicador de critico/efecto.
     *
     * @param def Pokemon defensor.
     * @param move movimiento usado.
     * @return multiplicador.
     */
    @Override
    public int antesDeCalcularefecto(Pokemon def, Movimiento move){
        return 2;
    }
}
