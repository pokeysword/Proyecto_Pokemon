package org.example;

import org.example.movimientos.Movimiento;
import java.util.ArrayList;
import java.util.Random;


public class DamageCalculator {
    private static final Random random = new Random();
    private static final double VARIABILITY_MIN = 0.85;
    private static final double VARIABILITY_MAX = 1.0;

   
    public static int calculateDamage(Pokemon atacante, Pokemon defensor, Movimiento movimiento) {
        // Verificar si el atacante está dormido - No puede atacar
        if (atacante.getEstado() == Estado.DORMIDO) {
            System.out.println(atacante.getNombre() + " está dormido y no puede atacar!");
            despertarPokemon(atacante);
            return 0;
        }
        
        // Verificar si el atacante está congelado - 20% de chance de descongelarse y no atacar
        if (atacante.getEstado() == Estado.CONGELADO) {
            if (random.nextInt(100) < 20) {
                System.out.println(atacante.getNombre() + " se descongeló!");
                atacante.setEstado(Estado.NORMAL);
            } else {
                System.out.println(atacante.getNombre() + " está congelado y no puede atacar!");
                return 0;
            }
        }
        
        // Verificar si el atacante está paralizado - 25% de chance de no poder atacar
        if (atacante.getEstado() == Estado.PARALIZADO) {
            if (random.nextInt(100) < 25) {
                System.out.println(atacante.getNombre() + " está paralizado y no puede moverse!");
                return 0;
            }
        }
        
        if (movimiento.getPotencia() <= 0) {
            return 0;
        }

        Tipo tipoAtaque = atacante.getHabilidad().antesDeCalcularDaño(defensor, movimiento);

        double efectividad = TypeEffectiveness.getTotalEffectiveness(tipoAtaque, defensor.getTipos());
        
        if (efectividad == 0.0) {
            System.out.println(defensor.getNombre() + " es inmune a " + movimiento.getNombre() + "! ¡No afecta!");
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
        
        // Aplicar reducción de daño si está quemado (solo para movimientos físicos)
        if (atacante.getEstado() == Estado.QUEMADO && movimiento.getCategoria() == Categoria.FISICO) {
            daño = daño * 0.5;
            System.out.println(atacante.getNombre() + " está quemado y su daño se reduce a la mitad!");
        }

       
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
        
        // Aplicar daño por envenenamiento después de recibir daño
        aplicarDañoPorEstado(defensor);
    }
    
    /**
     * Aplica daño pasivo según el estado del pokémon
     */
    public static void aplicarDañoPorEstado(Pokemon pokemon) {
        if (pokemon.getEstado() == Estado.ENVENENADO) {
            int daño = Math.max(1, pokemon.getPS() / 8);
            pokemon.sufrirDaño(daño);
            System.out.println(pokemon.getNombre() + " recibió " + daño + " de daño por envenenamiento!");
        } else if (pokemon.getEstado() == Estado.QUEMADO) {
            int daño = Math.max(1, pokemon.getPS() / 8);
            pokemon.sufrirDaño(daño);
            System.out.println(pokemon.getNombre() + " recibió " + daño + " de daño por quemadura!");
        }
    }
    
    /**
     * Despierta un pokémon dormido con 1-3 turnos de duración
     */
    public static void despertarPokemon(Pokemon pokemon) {
        // 33% de chance de despertar cada turno (1-3 turnos)
        if (random.nextInt(100) < 33) {
            pokemon.setEstado(Estado.NORMAL);
            System.out.println(pokemon.getNombre() + " se despertó!");
        }
    }
    
    /**
     * Obtiene el multiplicador de velocidad según el estado
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
