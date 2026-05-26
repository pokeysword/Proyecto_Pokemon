package org.example.habilidades;
import org.example.Pokemon;

public class ClearBody extends Habilidad {
    public ClearBody() {
        super("ClearBody");
    }

    @Override
    public void PuedenBajarStats(Pokemon portador) {
        portador.setBajarStats(false);
    }
}