package org.example.data;

import org.example.habilidades.*;

/**
 * Fabrica de habilidades a partir de codigos de base de datos.
 */
public final class HabilidadFactory {
    /**
     * Constructor privado para evitar instancias.
     */
    private HabilidadFactory() {
    }

    /**
     * Crea una habilidad segun su codigo.
     *
     * @param code codigo de la habilidad.
     * @return instancia de habilidad.
     * @throws PokemonDataException si el codigo es invalido.
     */
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
