package org.example.data;

import org.example.habilidades.ClearBody;
import org.example.habilidades.Competitive;
import org.example.habilidades.Habilidad;
import org.example.habilidades.Intimidacion;
import org.example.habilidades.Levitate;
import org.example.habilidades.MoldBreaker;
import org.example.habilidades.Pixilate;
import org.example.habilidades.Regenerator;
import org.example.habilidades.RoughSkin;
import org.example.habilidades.SereneGrace;

public final class HabilidadFactory {
    private HabilidadFactory() {
    }

    public static Habilidad crear(String code) throws PokemonDataException {
        if (code == null || code.trim().isEmpty()) {
            throw new PokemonDataException("Habilidad vacia en base de datos");
        }
        switch (code.trim()) {
            case "Levitate":
                return new Levitate();
            case "RoughSkin":
                return new RoughSkin();
            case "SereneGrace":
                return new SereneGrace();
            case "ClearBody":
                return new ClearBody();
            case "Competitive":
                return new Competitive();
            case "Intimidacion":
                return new Intimidacion();
            case "Regenerator":
                return new Regenerator();
            case "MoldBreaker":
                return new MoldBreaker();
            case "Pixilate":
                return new Pixilate();
            default:
                throw new PokemonDataException("Habilidad desconocida: " + code);
        }
    }
}

