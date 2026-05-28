package org.example.movimientos;

import org.example.*;

import java.util.Random;

/**
 * Movimiento especial de tipo Hielo con probabilidad de congelar.
 */
public class IceBeam extends Movimiento {
    /**
     * Crea el movimiento Ice Beam.
     */
    public IceBeam() {
        super("Ice Beam", 0, Tipo.HIELO, Categoria.ESPECIAL, 90, 100, 10,1);
    }
    /**
     * Aplica dano y puede congelar al defensor.
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
                    defensor.setEstado(Estado.CONGELADO);
                    GameView.mostrarPokemonCongelado(defensor.getNombre());
                }
            }
        }
    }
}