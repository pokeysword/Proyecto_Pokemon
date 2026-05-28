package org.example.movimientos;

import org.example.Categoria;
import org.example.Pokemon;
import org.example.Tipo;

/**
 * Clase base para movimientos de batalla.
 */
public abstract class Movimiento {
    protected String nombre;
    protected int prioridad;
    protected Tipo tipo;
    protected Categoria categoria;
    protected int potencia;
    protected int precision;
    protected int pp;
    protected int ppMax;
    protected int multcrit;

    /**
     * Crea un movimiento con sus atributos.
     *
     * @param nombre nombre del movimiento.
     * @param prioridad prioridad en el turno.
     * @param tipo tipo del movimiento.
     * @param categoria categoria del movimiento.
     * @param potencia potencia base.
     * @param precision precision del movimiento.
     * @param pp puntos de poder.
     * @param multcrit multiplicador de critico.
     */
    public Movimiento(String nombre, int prioridad, Tipo tipo, Categoria categoria, int potencia, int precision, int pp, int multcrit) {
        this.nombre = nombre;
        this.prioridad = prioridad;
        this.tipo = tipo;
        this.categoria = categoria;
        this.potencia = potencia;
        this.precision = precision;
        this.pp = pp;
        this.ppMax = pp;
        this.multcrit=multcrit;
    }

    /**
     * Aplica el efecto del movimiento.
     *
     * @param atacante Pokemon atacante.
     * @param defensor Pokemon defensor.
     */
    public abstract void efecto(Pokemon atacante, Pokemon defensor);

    /**
     * Obtiene la categoria.
     *
     * @return categoria.
     */
    public Categoria getCategoria() {
        return categoria;
    }

    /**
     * Obtiene el tipo.
     *
     * @return tipo del movimiento.
     */
    public Tipo getTipo() {
        return tipo;
    }

    /**
     * Obtiene el multiplicador de critico.
     *
     * @return multiplicador de critico.
     */
    public int getMultcrit() {
        return multcrit;
    }

    /**
     * Obtiene el nombre.
     *
     * @return nombre del movimiento.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene la potencia.
     *
     * @return potencia.
     */
    public int getPotencia() {
        return potencia;
    }

    /**
     * Obtiene la precision.
     *
     * @return precision.
     */
    public int getPrecision() {
        return precision;
    }

    /**
     * Obtiene el PP actual.
     *
     * @return PP actual.
     */
    public int getPp() {
        return pp;
    }

    /**
     * Obtiene el PP maximo.
     *
     * @return PP maximo.
     */
    public int getPpMax() {
        return ppMax;
    }

    /**
     * Indica si hay PP disponible.
     *
     * @return true si hay PP.
     */
    public boolean tienePpDisponible() {
        return pp > 0;
    }

    /**
     * Consume un PP si hay disponible.
     *
     * @return true si se consumio.
     */
    public boolean consumirPp() {
        if (pp <= 0) {
            return false;
        }
        pp--;
        return true;
    }

    /**
     * Obtiene la prioridad.
     *
     * @return prioridad del movimiento.
     */
    public int getPrioridad() {
        return prioridad;
    }
}
