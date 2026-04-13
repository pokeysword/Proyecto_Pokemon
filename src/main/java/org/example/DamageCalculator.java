package org.example;

import org.example.movimientos.Movimiento;
import java.util.ArrayList;
import java.util.Random;


public class DamageCalculator {
    private static final Random random = new Random();
    private static final double VARIABILITY_MIN = 0.85;
    private static final double VARIABILITY_MAX = 1.0;

   
    public static int calculateDamage(Pokemon atacante, Pokemon defensor, Movimiento movimiento) {
        
        if (movimiento.getPotencia() <= 0) {
            return 0;
        }

        Tipo tipoAtaque = atacante.getHabilidad().antesDeCalcularDaño(defensor, movimiento);

        double efectividad = TypeEffectiveness.getTotalEffectiveness(tipoAtaque, defensor.getTipos());
        
        if (efectividad == 0.0) {
            return 0;
        }
        
        boolean esCritico = isCriticalHit(atacante, movimiento);
        double multiplicadorCritico = esCritico ? 1.5 : 1.0;

        int statsAtacante;
        int statsDefensor;

        if (movimiento.getCategoria() == Categoria.FISICO) {
            statsAtacante = aplicarModificador(atacante.getAtack(), atacante.getModAtack());
            statsDefensor = aplicarModificador(defensor.getDefense(), defensor.getModDefense());
        } else { 
            statsAtacante = aplicarModificador(atacante.getSpAtack(), atacante.getModSpAtack());
            statsDefensor = aplicarModificador(defensor.getSpDefense(), defensor.getModSpDefense());
        }
       
        double daño = ((2.0 * atacante.getNivel() / 5.0 + 2.0) * movimiento.getPotencia() * statsAtacante / statsDefensor / 50.0 + 2.0);

        
        daño = daño * multiplicadorCritico * efectividad;

       
        double variabilidad = VARIABILITY_MIN + (VARIABILITY_MAX - VARIABILITY_MIN) * random.nextDouble();
        daño = daño * variabilidad;

        
        mostrarInfoAtaque(movimiento.getNombre(), atacante.getNombre(), defensor.getNombre(), esCritico, efectividad);

        return Math.max(1, (int) daño);
    }

    
    private static int aplicarModificador(int statBase, int modificador) {
        double multiplicador;
        if (modificador >= 0) {
            multiplicador = (2.0 + modificador) / 2.0;
        } else {
            multiplicador = 2.0 / (2.0 - modificador);
        }
        return (int) (statBase * multiplicador);
    }

    
    private static boolean isCriticalHit(Pokemon atacante, Movimiento movimiento) {
        int probabilidadBase = 16;

        int probabilidad = probabilidadBase / movimiento.getMultcrit();

        return random.nextInt(probabilidad) == 0;
    }

    
    private static void mostrarInfoAtaque(String nombreMovimiento, String atacante, String defensor, boolean esCritico, double efectividad) {
        StringBuilder mensaje = new StringBuilder();
        mensaje.append(atacante).append(" usa ").append(nombreMovimiento).append("!");

        if (esCritico) {
            mensaje.append(" ¡Golpe crítico!");
        }

        if (efectividad != 1.0) {
            mensaje.append(" ").append(TypeEffectiveness.getEffectivenessDescription(efectividad));
        }

        System.out.println(mensaje.toString());
    }

    
    public static void applyDamage(Pokemon defensor, int daño, Pokemon atacante, Movimiento movimiento) {
        if (daño > 0) {
            defensor.sufrirDaño(daño);
            System.out.println(defensor.getNombre() + " recibió " + daño + " de daño!");

        
            defensor.getHabilidad().efectoAlRecibirDaño(atacante, daño, movimiento);
        }
    }
}
