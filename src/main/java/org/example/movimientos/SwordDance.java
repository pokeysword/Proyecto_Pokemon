package org.example.movimientos;


import org.example.Categoria;
import org.example.Pokemon;
import org.example.Tipo;

/**
 * Movimiento de estado que sube ataque.
 */
public class SwordDance extends Movimiento {
    /**
     * Crea el movimiento Sword Dance.
     */
    public SwordDance() {
        super("Sword Dance", 0, Tipo.NORMAL, Categoria.ESTADO, 0, 100, 20,1);
    }

    /**
     * Aumenta el ataque del atacante.
     *
     * @param atacante Pokemon atacante.
     * @param defensor Pokemon defensor.
     */
    @Override
    public void efecto(Pokemon atacante, Pokemon defensor) {
        atacante.modificarAtk(2);
    }
}