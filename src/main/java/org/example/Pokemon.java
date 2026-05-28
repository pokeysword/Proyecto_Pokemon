package org.example;


import org.example.habilidades.*;
import org.example.movimientos.*;

import java.util.ArrayList;

/**
 * Modelo de Pokemon con estadisticas, estado y movimientos.
 */
public class Pokemon {
    private String nombre;
    private int nivel;
    private ArrayList<Tipo> tipos;
    private Habilidad habilidad;

    private int ps;
    private int atack;
    private int defense;
    private int spAtack;
    private int spDefense;
    private int speed;

    private boolean bajarStats;
    private int ModPs;
    private int ModAtack;
    private int ModDefense;
    private int ModSpAtack;
    private int ModSpDefense;
    private int ModSpeed;

    private Estado estado;
    private ArrayList<Movimiento> movimientos;
    private boolean flinch;
    private boolean protegido;
    private boolean needsSwitch;
    private boolean usoProtectTurnoAnterior;

    /**
     * Crea un Pokemon con sus estadisticas base y movimientos.
     *
     * @param nombre nombre del Pokemon.
     * @param nivel nivel del Pokemon.
     * @param tipos lista de tipos.
     * @param habilidad habilidad del Pokemon.
     * @param ps puntos de salud base.
     * @param atack ataque base.
     * @param defense defensa base.
     * @param sAtack ataque especial base.
     * @param sDefense defensa especial base.
     * @param speed velocidad base.
     * @param movimientos lista de movimientos.
     */
    public Pokemon(String nombre, int nivel, ArrayList<Tipo> tipos, Habilidad habilidad, int ps, int atack, int defense, int sAtack, int sDefense, int speed, ArrayList<Movimiento> movimientos) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.tipos = tipos;
        this.habilidad = habilidad;
        this.ps = ps;
        this.atack = atack;
        this.defense = defense;
        this.spAtack = sAtack;
        this.spDefense = sDefense;
        this.speed = speed;
        this.estado = Estado.NORMAL;
        this.movimientos = movimientos;
        this.flinch = false;
        this.protegido = false;
        this.usoProtectTurnoAnterior = false;
        this.ModPs = ps;  // Inicializar ModPs con los PS actuales
        bajarStats=true;
        ModAtack = 0;
        ModDefense = 0;
        ModSpAtack = 0;
        ModSpDefense = 0;
        ModSpeed = 0;
    }
    
    /**
     * Crea una copia independiente para evitar compartir vida entre equipos.
     *
     * @return copia del Pokemon.
     */
    public Pokemon crearCopia() {
        Pokemon copia = new Pokemon(
            this.nombre,
            this.nivel,
            new ArrayList<>(this.tipos),
            this.habilidad,
            this.ps,
            this.atack,
            this.defense,
            this.spAtack,
            this.spDefense,
            this.speed,
            new ArrayList<>(this.movimientos)
        );
        return copia;
    }
    
    /**
     * Reinicia los modificadores de estadisticas.
     */
    public void resetMods(){
        ModAtack = 0;
        ModDefense = 0;
        ModSpAtack = 0;
        ModSpDefense = 0;
        ModSpeed = 0;
    }
    /**
     * Marca el Pokemon para cambio y reinicia modificadores.
     */
    public void cambio() {
        resetMods();
        needsSwitch = true;
        usoProtectTurnoAnterior = false;
        GameView.mostrarSaleDelCampo(nombre);
    }

    /**
     * Indica si el Pokemon solicita cambio.
     *
     * @return true si necesita cambiar.
     */
    public boolean needsSwitch() {
        return needsSwitch;
    }

    /**
     * Limpia la solicitud de cambio.
     */
    public void resetSwitch() {
        needsSwitch = false;
    }

    /**
     * Prepara el Pokemon para una nueva batalla.
     */
    public void prepararParaBatalla() {
        ModPs = ps;  // Reiniciar PS al máximo
        resetMods();  // Resetear modificadores de stats
        flinch = false;  // Limpiar flinch
        protegido = false;  // Limpiar protección
        needsSwitch = false;  // Limpiar bandera de cambio
        usoProtectTurnoAnterior = false;
    }
    
    /**
     * Prepara el Pokemon cuando vuelve a entrar sin restaurar su vida.
     */
    public void volverAEntrar() {
        resetMods();  // Resetear solo los modificadores de stats
        flinch = false;  // Limpiar flinch
        protegido = false;  // Limpiar protección
        needsSwitch = false;  // Limpiar bandera de cambio
        usoProtectTurnoAnterior = false;
        // NO restauramos ModPs - mantiene el daño recibido
    }

    /**
     * Obtiene el nombre del Pokemon.
     *
     * @return nombre del Pokemon.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene los tipos del Pokemon.
     *
     * @return lista de tipos.
     */
    public ArrayList<Tipo> getTipos() {
        return tipos;
    }

    /**
     * Obtiene la habilidad del Pokemon.
     *
     * @return habilidad del Pokemon.
     */
    public Habilidad getHabilidad() {
        return habilidad;
    }

    /**
     * Indica si el Pokemon esta debilitado.
     *
     * @return true si sus PS son 0.
     */
    public boolean estaDebilitado() {
        return ModPs <= 0;
    }

    /**
     * Obtiene los PS base.
     *
     * @return PS base.
     */
    public int getPS() {
        return ps;
    }

    /**
     * Obtiene el ataque base.
     *
     * @return ataque base.
     */
    public int getAtack() {
        return atack;
    }

    /**
     * Obtiene la defensa base.
     *
     * @return defensa base.
     */
    public int getDefense() {
        return defense;
    }

    /**
     * Obtiene el ataque especial base.
     *
     * @return ataque especial base.
     */
    public int getSpAtack() {
        return spAtack;
    }

    /**
     * Obtiene la defensa especial base.
     *
     * @return defensa especial base.
     */
    public int getSpDefense() {
        return spDefense;
    }

    /**
     * Obtiene la velocidad efectiva considerando estados.
     *
     * @return velocidad efectiva.
     */
    public int getSpeed() {
        double velocityMultiplier = DamageCalculator.getVelocityMultiplier(this);
        return (int) (speed * velocityMultiplier);
    }

    /**
     * Obtiene el estado actual.
     *
     * @return estado actual.
     */
    public Estado getEstado(){return estado;}

    /**
     * Establece el estado actual.
     *
     * @param estado nuevo estado.
     */
    public void setEstado(Estado estado){this.estado=estado;}

    /**
     * Activa o desactiva el flinch.
     *
     * @param flinch true si retrocede.
     */
    public void setFlinch(Boolean flinch) {
        this.flinch = flinch;
    }

    /**
     * Muestra un cambio de estadistica en la vista.
     *
     * @param stat estadistica afectada.
     * @param num niveles de cambio.
     */
    private void mostrarCambio(String stat, int num) {
        GameView.mostrarCambioStats(nombre, stat, num);
    }

    /**
     * Limita un modificador al rango valido.
     *
     * @param modificador valor a limitar.
     * @return modificador limitado.
     */
    private int LimitarMod(int modificador) {
        return Math.max(-6, Math.min(6, modificador));
    }

    /**
     * Calcula el multiplicador de stats segun niveles.
     *
     * @param multiplicador niveles de modificacion.
     * @return multiplicador resultante.
     */
    private double getMultiplicador(int multiplicador) {

        if (multiplicador >= 0) {
            return (2.0 + multiplicador) / 2.0;
        } else {
            return 2.0 / (2.0 - multiplicador);
        }
    }

    /**
     * Obtiene los PS actuales.
     *
     * @return PS actuales.
     */
    public int getModPs(){
        return ModPs;
    }

    /**
     * Establece los PS actuales.
     *
     * @param num nuevo valor de PS.
     */
    public void setModPs(int num){
        this.ModPs=num;
    }

    /**
     * Modifica el ataque especial.
     *
     * @param num niveles de cambio.
     */
    public void modificarSpAtk(int num) {
        ModSpAtack = LimitarMod(ModSpAtack + num);
        mostrarCambio("Ataque Especial", num);
    }

    /**
     * Modifica el ataque.
     *
     * @param num niveles de cambio.
     */
    public void modificarAtk(int num) {
        ModAtack = LimitarMod(ModAtack + num);
        mostrarCambio("Ataque", num);
    }

    /**
     * Modifica la defensa.
     *
     * @param num niveles de cambio.
     */
    public void modificarDef(int num) {
        ModDefense = LimitarMod(ModDefense + num);
        mostrarCambio("Defensa", num);
    }

    /**
     * Modifica la defensa especial.
     *
     * @param num niveles de cambio.
     */
    public void modificarSpDef(int num) {
        ModSpDefense = LimitarMod(ModSpDefense + num);
        mostrarCambio("Defensa Especial", num);
    }

    /**
     * Modifica la velocidad.
     *
     * @param num niveles de cambio.
     */
    public void modificarSpe(int num) {
        ModSpeed = LimitarMod(ModSpeed + num);
        mostrarCambio("Velocidad", num);
    }

    /**
     * Permite o bloquea la bajada de stats.
     *
     * @param bajarStats true si permite bajar stats.
     */
    public void setBajarStats(boolean bajarStats) {
        this.bajarStats = bajarStats;
    }

    /**
     * Obtiene el nivel.
     *
     * @return nivel actual.
     */
    public int getNivel() {
        return nivel;
    }

    /**
     * Obtiene el modificador de ataque.
     *
     * @return modificador de ataque.
     */
    public int getModAtack() {
        return ModAtack;
    }

    /**
     * Obtiene el modificador de defensa.
     *
     * @return modificador de defensa.
     */
    public int getModDefense() {
        return ModDefense;
    }

    /**
     * Obtiene el modificador de ataque especial.
     *
     * @return modificador de ataque especial.
     */
    public int getModSpAtack() {
        return ModSpAtack;
    }

    /**
     * Obtiene el modificador de defensa especial.
     *
     * @return modificador de defensa especial.
     */
    public int getModSpDefense() {
        return ModSpDefense;
    }

    /**
     * Obtiene el modificador de velocidad.
     *
     * @return modificador de velocidad.
     */
    public int getModSpeed() {
        return ModSpeed;
    }

    /**
     * Aplica dano a los PS actuales.
     *
     * @param daño cantidad de dano.
     */
    public void sufrirDaño(int daño) {
        this.ModPs = Math.max(0, this.ModPs - daño);
    }

    /**
     * Indica si esta protegido.
     *
     * @return true si esta protegido.
     */
    public boolean isProtegido() {
        return protegido;
    }

    /**
     * Establece la proteccion.
     *
     * @param protegido true si se protege.
     */
    public void setProtected(boolean protegido) {
        this.protegido = protegido;
    }

    /**
     * Reinicia la proteccion.
     */
    public void resetProtection() {
        this.protegido = false;
    }

    /**
     * Indica si uso Protect el turno anterior.
     *
     * @return true si uso Protect.
     */
    public boolean usoProtectTurnoAnterior() {
        return usoProtectTurnoAnterior;
    }

    /**
     * Define si uso Protect el turno anterior.
     *
     * @param usoProtectTurnoAnterior valor del flag.
     */
    public void setUsoProtectTurnoAnterior(boolean usoProtectTurnoAnterior) {
        this.usoProtectTurnoAnterior = usoProtectTurnoAnterior;
    }

    /**
     * Obtiene la lista de movimientos.
     *
     * @return movimientos disponibles.
     */
    public ArrayList<Movimiento> getMovimientos() {
        return movimientos;
    }

    /**
     * Indica si el Pokemon esta en flinch.
     *
     * @return true si no puede atacar.
     */
    public boolean flinchActive() {
        return flinch;
    }

    /**
     * Limpia el estado de flinch.
     */
    public void clearFlinch() {
        this.flinch = false;
    }
}
