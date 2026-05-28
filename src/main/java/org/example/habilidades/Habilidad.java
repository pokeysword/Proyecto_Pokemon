package org.example.habilidades;

import org.example.Pokemon;
import org.example.Tipo;
import org.example.movimientos.Movimiento;

/**
 * Clase base para habilidades de Pokemon.
 */
public abstract class Habilidad {
    protected String nombre;

    /**
     * Crea una habilidad con nombre.
     *
     * @param nombre nombre de la habilidad.
     */
    public Habilidad(String nombre) {
        this.nombre = nombre;

    }

    /**
     * Obtiene el nombre de la habilidad.
     *
     * @return nombre de la habilidad.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Efecto generico de la habilidad.
     *
     * @param portador Pokemon portador.
     */
    public void efecto(Pokemon portador){}

    /**
     * Define si pueden bajar stats.
     *
     * @param portador Pokemon portador.
     */
    public void PuedenBajarStats(Pokemon portador){}

    /**
     * Efecto al cambiar de Pokemon.
     *
     * @param portador Pokemon portador.
     */
    public void efectoalCambiar(Pokemon portador){}

    /**
     * Efecto al entrar en combate.
     *
     * @param portador Pokemon portador.
     * @param rival Pokemon rival.
     */
    public void efectoAlEntrar(Pokemon portador, Pokemon rival){}

    /**
     * Efecto al recibir dano.
     *
     * @param rival Pokemon rival.
     * @param daño dano recibido.
     * @param move movimiento que causa dano.
     */
    public void efectoAlRecibirDaño(Pokemon rival, int daño, Movimiento move) {}

    /**
     * Indica si el Pokemon es inmune al movimiento.
     *
     * @param def Pokemon defensor.
     * @param move movimiento recibido.
     * @return true si es inmune.
     */
    public boolean esInmune(Pokemon def,Movimiento move){return false;}

    /**
     * Ajusta el tipo antes de calcular dano.
     *
     * @param def Pokemon defensor.
     * @param move movimiento usado.
     * @return tipo efectivo.
     */
    public Tipo antesDeCalcularDaño(Pokemon def, Movimiento move){return move.getTipo();}

    /**
     * Ajusta el multiplicador de critico antes de aplicar efecto.
     *
     * @param def Pokemon defensor.
     * @param move movimiento usado.
     * @return multiplicador de critico.
     */
    public int antesDeCalcularefecto(Pokemon def, Movimiento move){return move.getMultcrit();}

    /**
     * Efecto cuando baja stats del rival.
     *
     * @param portador Pokemon portador.
     * @param rival Pokemon rival.
     */
    public void efectoAlBajarStats(Pokemon portador, Pokemon rival){}

    /**
     * Efecto cuando recibe una bajada de stats.
     *
     * @param portador Pokemon portador.
     */
    public void alRecibirBajadaDeStat(Pokemon portador){}
}
