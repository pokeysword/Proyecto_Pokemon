package org.example;

import org.example.movimientos.*;
import java.util.Random;


/**
 * Utilidad para calcular y aplicar dano en batalla.
 */
public class DamageCalculator {
    private static final Random random = new Random();
    private static final double VARIABILITY_MIN = 0.85;
    private static final double VARIABILITY_MAX = 1.0;

   
    /**
     * Calcula el dano base de un movimiento y aplica reglas de estado.
     *
     * @param atacante Pokemon atacante.
     * @param defensor Pokemon defensor.
     * @param movimiento movimiento ejecutado.
     * @return dano calculado (minimo 0).
     */
    public static int calculateDamage(Pokemon atacante, Pokemon defensor, Movimiento movimiento) {
        // Verificar si el atacante está dormido - No puede atacar
        if (atacante.getEstado() == Estado.DORMIDO) {
            GameView.mostrarDormidoNoAtaca(atacante.getNombre());
            despertarPokemon(atacante);
            return 0;
        }
        
        // Verificar si el atacante está congelado - 20% de chance de descongelarse y no atacar
        if (atacante.getEstado() == Estado.CONGELADO) {
            if (random.nextInt(100) < 20) {
                GameView.mostrarSeDescongelo(atacante.getNombre());
                atacante.setEstado(Estado.NORMAL);
            } else {
                GameView.mostrarCongeladoNoAtaca(atacante.getNombre());
                return 0;
            }
        }
        
        // Verificar si el atacante está paralizado - 25% de chance de no poder atacar
        if (atacante.getEstado() == Estado.PARALIZADO) {
            if (random.nextInt(100) < 25) {
                GameView.mostrarParalizadoNoSeMueve(atacante.getNombre());
                return 0;
            }
        }
        
        if (movimiento.getPotencia() <= 0) {
            return 0;
        }

        // Verificar si la habilidad del DEFENSOR lo hace inmune
        if (defensor.getHabilidad().esInmune(defensor, movimiento)) {
            GameView.mostrarInmunePorHabilidad(defensor.getNombre(), movimiento.getNombre(), defensor.getHabilidad().getNombre());
            return 0;
        }

        Tipo tipoAtaque = atacante.getHabilidad().antesDeCalcularDaño(defensor, movimiento);

        double efectividad = TypeEffectiveness.getTotalEffectiveness(tipoAtaque, defensor.getTipos());
        
        if (efectividad == 0.0) {
            GameView.mostrarInmuneMovimiento(defensor.getNombre(), movimiento.getNombre());
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
       
        double daño = ((2.0 * atacante.getNivel() / 5.0 + 2.0) * movimiento.getPotencia() * (double) statsAtacante / statsDefensor / 50.0 + 2.0);

        
        daño = daño * multiplicadorCritico * efectividad;
        
        // Aplicar reducción de daño si está quemado (solo para movimientos físicos)
        if (atacante.getEstado() == Estado.QUEMADO && movimiento.getCategoria() == Categoria.FISICO) {
            daño = daño * 0.5;
            GameView.mostrarQuemadoMitadDanio(atacante.getNombre());
        }

       
        double variabilidad = VARIABILITY_MIN + (VARIABILITY_MAX - VARIABILITY_MIN) * random.nextDouble();
        daño = daño * variabilidad;

        
        mostrarInfoAtaque(movimiento.getNombre(), atacante.getNombre(), defensor.getNombre(), esCritico, efectividad);

        return Math.max(1, (int) daño);
    }

    
    /**
     * Aplica un modificador de estadisticas a un valor base.
     *
     * @param statBase valor base.
     * @param modificador niveles de modificacion.
     * @return valor ajustado.
     */
    private static int aplicarModificador(int statBase, int modificador) {
        double multiplicador;
        if (modificador >= 0) {
            multiplicador = (2.0 + modificador) / 2.0;
        } else {
            multiplicador = 2.0 / (2.0 - modificador);
        }
        return (int) (statBase * multiplicador);
    }

    
    /**
     * Determina si el movimiento es un golpe critico.
     *
     * @param atacante Pokemon atacante.
     * @param movimiento movimiento usado.
     * @return true si es critico.
     */
    private static boolean isCriticalHit(Pokemon atacante, Movimiento movimiento) {
        int probabilidadBase = 16;

        int probabilidad = probabilidadBase / movimiento.getMultcrit();

        return random.nextInt(probabilidad) == 0;
    }

    
    /**
     * Muestra informacion de ataque en la vista.
     *
     * @param nombreMovimiento nombre del movimiento.
     * @param atacante nombre del atacante.
     * @param defensor nombre del defensor.
     * @param esCritico indica si fue critico.
     * @param efectividad multiplicador de efectividad.
     */
    private static void mostrarInfoAtaque(String nombreMovimiento, String atacante, String defensor, boolean esCritico, double efectividad) {
        StringBuilder mensaje = new StringBuilder();
        mensaje.append(atacante).append(" usa ").append(nombreMovimiento).append("!");

        if (esCritico) {
            mensaje.append(" ¡Golpe crítico!");
        }

        if (efectividad != 1.0) {
            mensaje.append(" ").append(TypeEffectiveness.getEffectivenessDescription(efectividad));
        }

        GameView.mostrarInfoAtaque(mensaje.toString());
    }

    
    /**
     * Aplica el dano a un Pokemon y procesa efectos posteriores.
     *
     * @param defensor Pokemon defensor.
     * @param daño dano aplicado.
     * @param atacante Pokemon atacante.
     * @param movimiento movimiento usado.
     */
    public static void applyDamage(Pokemon defensor, int daño, Pokemon atacante, Movimiento movimiento) {
        if (daño > 0) {
            defensor.sufrirDaño(daño);
            GameView.mostrarDanioRecibido(defensor.getNombre(), daño);

        
            defensor.getHabilidad().efectoAlRecibirDaño(atacante, daño, movimiento);
        }
        
        // Aplicar daño por envenenamiento después de recibir daño
        aplicarDañoPorEstado(defensor);
    }
    
    /**
     * Aplica dano pasivo segun el estado del Pokemon.
     *
     * @param pokemon objetivo.
     */
    public static void aplicarDañoPorEstado(Pokemon pokemon) {
        if (pokemon.getEstado() == Estado.ENVENENADO) {
            int daño = Math.max(1, pokemon.getPS() / 8);
            pokemon.sufrirDaño(daño);
            GameView.mostrarDanioEnvenenamiento(pokemon.getNombre(), daño);
        } else if (pokemon.getEstado() == Estado.QUEMADO) {
            int daño = Math.max(1, pokemon.getPS() / 8);
            pokemon.sufrirDaño(daño);
            GameView.mostrarDanioQuemadura(pokemon.getNombre(), daño);
        }
    }
    
    /**
     * Despierta un Pokemon dormido con probabilidad por turno.
     *
     * @param pokemon objetivo.
     */
    public static void despertarPokemon(Pokemon pokemon) {
        // 33% de chance de despertar cada turno (1-3 turnos)
        if (random.nextInt(100) < 33) {
            pokemon.setEstado(Estado.NORMAL);
            GameView.mostrarSeDesperto(pokemon.getNombre());
        }
    }
    
    /**
     * Obtiene el multiplicador de velocidad segun el estado.
     *
     * @param pokemon objetivo.
     * @return multiplicador de velocidad.
     */
    public static double getVelocityMultiplier(Pokemon pokemon) {
        Estado estado = pokemon.getEstado();
        
        if (estado == Estado.PARALIZADO) {
            // Reduce velocidad a 25%
            return 0.25;
        } else if (estado == Estado.CONGELADO) {
            // Reduce velocidad a 50%
            return 0.5;
        }
        
        return 1.0;
    }
}
