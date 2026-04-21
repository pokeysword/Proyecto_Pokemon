package org.example.habilidades;
import org.example.GameView;
import org.example.Pokemon;

public class Competitive extends Habilidad {
    public Competitive() {
        super("Competitive");
    }

public void alRecibirBajadaDeStat(Pokemon portador) {
    portador.modificarSpAtk(2);
    GameView.mostrarCompetitive(portador.getNombre());
}}
