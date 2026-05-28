package org.example.habilidades;

import org.example.Pokemon;

/**
 * Habilidad que cura al cambiar.
 */
public class Regenerator extends Habilidad {
    /**
     * Crea la habilidad Regenerator.
     */
    public Regenerator() {
        super("Regenerator");
    }

    /**
     * Cura al portador al salir del combate.
     *
     * @param def Pokemon portador.
     */
    @Override
    public void efectoalCambiar(Pokemon def) {
        def.setModPs(def.getModPs() + (def.getPS() / 4));
    }
}
