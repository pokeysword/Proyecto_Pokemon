package org.example.habilidades;
import org.example.GameView;
import org.example.Pokemon;

public class MoldBreaker extends Habilidad {
    public MoldBreaker() {
        super("MoldBreaker");
    }

    @Override
    public void efecto(Pokemon def) {
        GameView.mostrarMoldBreaker(def.getNombre());
        ;
    }}
