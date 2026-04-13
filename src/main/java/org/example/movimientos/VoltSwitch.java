package org.example.movimientos;
import org.example.Categoria;
import org.example.DamageCalculator;
import org.example.Pokemon;
import org.example.Tipo;


    public class VoltSwitch extends Movimiento {
        public VoltSwitch() {
            super("U-turn", 0, Tipo.BICHO, Categoria.FISICO, 70, 100, 20,1);
        }
        @Override
        public void efecto(Pokemon atacante, Pokemon defensor) {
            int daño = DamageCalculator.calculateDamage(atacante, defensor, this);
            DamageCalculator.applyDamage(defensor, daño, atacante, this);
            
            if (!defensor.estaDebilitado()) {
                atacante.cambio();
            }
        }
    }
