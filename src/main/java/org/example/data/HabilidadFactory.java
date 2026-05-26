package org.example.data;

import org.example.habilidades.*;

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

