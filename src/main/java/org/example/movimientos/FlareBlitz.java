package org.example.movimientos;

import org.example.*;

import java.util.Random;

public class FlareBlitz extends Movimiento {
    public FlareBlitz() {
        super("Flare Blitz", 0, Tipo.FUEGO, Categoria.FISICO, 100, 100, 15,1);}
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

