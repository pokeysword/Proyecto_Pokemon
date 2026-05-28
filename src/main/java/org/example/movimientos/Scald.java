package org.example.movimientos;

import org.example.*;

import java.util.Random;

/**
 * Movimiento especial de tipo Agua con probabilidad de quemar.
 */
public class Scald extends Movimiento {
    /**
     * Crea el movimiento Scald.
     */
    public Scald() {
        super("Scald", 0, Tipo.AGUA, Categoria.ESPECIAL, 80, 100, 15,1);}
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
                if (new Random().nextInt(100) < 30) {
                    defensor.setEstado(Estado.QUEMADO);
                    GameView.mostrarPokemonQuemado(defensor.getNombre());
                }
            }
        }
    }
}
