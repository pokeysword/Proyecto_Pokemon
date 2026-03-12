package org.example.habilidades;
import org.example.Pokemon;

public class Regenerator extends Habilidad {
    public Regenerator() {
        super("Regenerator");
    }

    @Override
    public void efectoalCambiar(Pokemon def) {
        def.setModPs(def.getModPs()+ (def.getPS()/4));
    }}
