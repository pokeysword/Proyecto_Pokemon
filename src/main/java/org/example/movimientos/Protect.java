package org.example.movimientos;

import org.example.Categoria;
import org.example.GameView;
import org.example.Pokemon;
import org.example.Tipo;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Movimiento de estado que protege contra el siguiente ataque.
 */
public class Protect extends Movimiento {
    /**
     * Crea el movimiento Protect.
     */
    public Protect() {
        super("Protect", 3, Tipo.NORMAL, Categoria.ESTADO, 0, 100, 10,1);
    }

    /**
     * Activa la proteccion y gestiona el fallo consecutivo.
     *
     * @param atacante Pokemon atacante.
     * @param defensor Pokemon defensor.
     */
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
