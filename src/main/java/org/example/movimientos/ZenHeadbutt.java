package org.example.movimientos;
import org.example.DamageCalculator;
import org.example.Categoria;
import org.example.GameView;
import org.example.Pokemon;
import org.example.Tipo;

import java.util.Random;


public class ZenHeadbutt extends Movimiento {
    public ZenHeadbutt() {
        super("Zen Headbutt", 0, Tipo.PSÍQUICO, Categoria.FISICO, 80, 90, 15,1);
    }
    @Override
    public void efecto(Pokemon atacante, Pokemon defensor) {
        int daño = DamageCalculator.calculateDamage(atacante, defensor, this);
        DamageCalculator.applyDamage(defensor, daño, atacante, this);
        
        if (!defensor.estaDebilitado()) {
            if (new Random().nextInt(100) < 20) {
                defensor.setFlinch(true);
                GameView.mostrarPokemonRetrocedio(defensor.getNombre());
            }
        }
    }
}
