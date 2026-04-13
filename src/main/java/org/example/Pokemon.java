package org.example;


import org.example.habilidades.Habilidad;
import org.example.movimientos.Movimiento;

import java.util.ArrayList;

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
        this.ModPs = ps;  // Inicializar ModPs con los PS actuales
        bajarStats=true;
        ModAtack = 0;
        ModDefense = 0;
        ModSpAtack = 0;
        ModSpDefense = 0;
        ModSpeed = 0;
    }
    public void resetMods(){
        ModAtack = 0;
        ModDefense = 0;
        ModSpAtack = 0;
        ModSpDefense = 0;
        ModSpeed = 0;
    }
    public void cambio() {
        resetMods();
        System.out.println(nombre + " sale del campo.");
    }

    public void prepararParaBatalla() {
        ModPs = ps;  // Reiniciar PS al máximo
        resetMods();  // Resetear modificadores de stats
        flinch = false;  // Limpiar flinch
        protegido = false;  // Limpiar protección
    }
    public String getNombre() {
        return nombre;
    }

    public ArrayList<Tipo> getTipos() {
        return tipos;
    }

    public Habilidad getHabilidad() {
        return habilidad;
    }

    public boolean estaDebilitado() {
        return ModPs <= 0;
    }

    public int getPS() {
        return ps;
    }

    public int getAtack() {
        return atack;
    }

    public int getDefense() {
        return defense;
    }

    public int getSpAtack() {
        return spAtack;
    }

    public int getSpDefense() {
        return spDefense;
    }

    public int getSpeed() {
        double velocityMultiplier = DamageCalculator.getVelocityMultiplier(this);
        return (int) (speed * velocityMultiplier);
    }

    public Estado getEstado(){return estado;}

    public void setEstado(Estado estado){this.estado=estado;}

    public void setFlinch(Boolean flinch) {
        this.flinch = flinch;
    }

    private void mostrarCambio(String stat, int num) {

        if (num > 1) {
            System.out.println(nombre + " aumentó mucho su " + stat + "!");
        } else if (num == 1) {
            System.out.println(nombre + " aumentó su " + stat + ".");
        } else if (num == -1) {
            System.out.println(nombre + " bajó su " + stat + ".");
        } else if (num < -1) {
            System.out.println(nombre + " bajó mucho su " + stat + "!");
        }
    }

    private int LimitarMod(int modificador) {
        return Math.max(-6, Math.min(6, modificador));
    }

    private double getMultiplicador(int multiplicador) {

        if (multiplicador >= 0) {
            return (2.0 + multiplicador) / 2.0;
        } else {
            return 2.0 / (2.0 - multiplicador);
        }
    }
    public int getModPs(){
        return ModPs;
    }
    public void setModPs(int num){
        this.ModPs=num;
    }
    public void modificarSpAtk(int num) {
        ModSpAtack = LimitarMod(ModSpAtack + num);
        mostrarCambio("Ataque Especial", num);
    }

    public void modificarAtk(int num) {
        ModAtack = LimitarMod(ModAtack + num);
        mostrarCambio("Ataque", num);
    }

    public void modificarDef(int num) {
        ModDefense = LimitarMod(ModDefense + num);
        mostrarCambio("Defensa", num);
    }

    public void modificarSpDef(int num) {
        ModSpDefense = LimitarMod(ModSpDefense + num);
        mostrarCambio("Defensa Especial", num);
    }

    public void modificarSpe(int num) {
        ModSpeed = LimitarMod(ModSpeed + num);
        mostrarCambio("Velocidad", num);
    }

    public void setBajarStats(boolean bajarStats) {
        this.bajarStats = bajarStats;
    }

    public int getNivel() {
        return nivel;
    }

    public int getModAtack() {
        return ModAtack;
    }

    public int getModDefense() {
        return ModDefense;
    }

    public int getModSpAtack() {
        return ModSpAtack;
    }

    public int getModSpDefense() {
        return ModSpDefense;
    }

    public int getModSpeed() {
        return ModSpeed;
    }

    public void sufrirDaño(int daño) {
        this.ModPs = Math.max(0, this.ModPs - daño);
    }

    public boolean isProtegido() {
        return protegido;
    }

    public void setProtected(boolean protegido) {
        this.protegido = protegido;
    }

    public void resetProtection() {
        this.protegido = false;
    }

    public ArrayList<Movimiento> getMovimientos() {
        return movimientos;
    }

    public boolean flinchActive() {
        return flinch;
    }

    public void clearFlinch() {
        this.flinch = false;
    }
}
