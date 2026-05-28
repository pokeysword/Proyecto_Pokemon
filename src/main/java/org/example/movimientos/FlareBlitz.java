package org.example.movimientos;

import org.example.*;

import java.util.Random;

/**
 * Movimiento fisico de tipo Fuego con probabilidad de quemar.
 */
public class FlareBlitz extends Movimiento {
    /**
     * Crea el movimiento Flare Blitz.
     */
    public FlareBlitz() {
        super("Flare Blitz", 0, Tipo.FUEGO, Categoria.FISICO, 100, 100, 15,1);}
    /**
     * Aplica dano y puede causar quemadura.
     *
     * @param atacante Pokemon atacante.
     * @param defensor Pokemon defensor.
     */
    @Override
    public void efecto(Pokemon atacante, Pokemon defensor) {
        int daño = DamageCalculator.calculateDamage(atacante, defensor, this);
        DamageCalculator.applyDamage(defensor, daño, atacante, this);
        
        if (!defensor.estaDebilitado()) {
            if(defensor.getEstado()== Estado.NORMAL){
                if (new Random().nextInt(100) < 10) {
                    defensor.setEstado(Estado.QUEMADO);
                    GameView.mostrarPokemonQuemado(defensor.getNombre());
                }
            }
        }
    }
}
