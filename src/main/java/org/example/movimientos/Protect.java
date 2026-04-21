package org.example.movimientos;

import org.example.Categoria;
import org.example.GameView;
import org.example.Pokemon;
import org.example.Tipo;

import java.util.concurrent.ThreadLocalRandom;

public class Protect extends Movimiento {
    public Protect() {
        super("Protect", 3, Tipo.NORMAL, Categoria.ESTADO, 0, 100, 10,1);
    }
    @Override
    public void efecto(Pokemon atacante, Pokemon defensor) {
        if (atacante.usoProtectTurnoAnterior()) {
            boolean acierta = ThreadLocalRandom.current().nextDouble() < 0.33;
            if (!acierta) {
                atacante.setProtected(false);
                GameView.mostrarProtectFallo(atacante.getNombre());
                return;
            }
        }

        atacante.setProtected(true);
        GameView.mostrarProtectExito(atacante.getNombre());
    }
}
