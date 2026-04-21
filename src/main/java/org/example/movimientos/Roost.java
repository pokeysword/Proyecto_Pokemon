package org.example.movimientos;

import org.example.Categoria;
import org.example.GameView;
import org.example.Pokemon;
import org.example.Tipo;

public class Roost extends Movimiento {
    public Roost() {
        super("Roost", 0, Tipo.VOLADOR, Categoria.ESTADO, 0, 100, 10,1);

    }
    @Override
    public void efecto(Pokemon atacante, Pokemon defensor) {
        int psActuales = atacante.getModPs();
        int psMaximo = atacante.getPS();
        int psRecuperado = psMaximo / 2;
        int psNuevo = Math.min(psActuales + psRecuperado, psMaximo);
        atacante.setModPs(psNuevo);
        GameView.mostrarRecover(atacante.getNombre(), psNuevo - psActuales);
    }
}