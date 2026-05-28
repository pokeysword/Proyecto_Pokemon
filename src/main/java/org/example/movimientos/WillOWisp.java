package org.example.movimientos;


import org.example.Categoria;
import org.example.Estado;
import org.example.Pokemon;
import org.example.Tipo;


/**
 * Movimiento de estado que provoca quemadura.
 */
public class WillOWisp extends Movimiento {
    /**
     * Crea el movimiento Will-O-Wisp.
     */
    public WillOWisp() {
        super("Will-O-Wisp", 0, Tipo.FUEGO, Categoria.ESTADO, 0, 85, 15,1);
    }
    /**
     * Aplica el estado quemado al defensor.
     *
     * @param atacante Pokemon atacante.
     * @param defensor Pokemon defensor.
     */
    @Override
    public void efecto(Pokemon atacante, Pokemon defensor) {
        if (!defensor.estaDebilitado()) {
            defensor.setEstado(Estado.QUEMADO);
        }
    }
}