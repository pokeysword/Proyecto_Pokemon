package org.example.habilidades;
import org.example.Pokemon;

public class Competitive extends Habilidad {
    public Competitive() {
        super("Competitive");
    }

public void alRecibirBajadaDeStat(Pokemon portador) {
    portador.modificarSpAtk(2);
    System.out.println(portador.getNombre() + " activó Competitive! ¡Su Atk. Esp. subió mucho!");
}}
