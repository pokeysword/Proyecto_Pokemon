package org.example.habilidades;
import org.example.Pokemon;

public class MoldBreaker extends Habilidad {
    public MoldBreaker() {
        super("MoldBreaker");
    }

    @Override
    public void efecto(Pokemon def) {
        System.out.println(def.getNombre()+" uso Mold Breaker");;
    }}
