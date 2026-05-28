package org.example.data;

import org.example.Tipo;

import java.util.Locale;

/**
 * Utilidad para mapear codigos de tipos desde base de datos.
 */
public final class TipoMapper {
    /**
     * Constructor privado para evitar instancias.
     */
    private TipoMapper() {
    }

    /**
     * Convierte un codigo de base de datos a un tipo.
     *
     * @param code codigo de tipo.
     * @return tipo correspondiente.
     * @throws PokemonDataException si el codigo es invalido.
     */
    public static Tipo fromDbCode(String code) throws PokemonDataException {
        if (code == null || code.trim().isEmpty()) {
            throw new PokemonDataException("Tipo vacio en base de datos");
        }
        String normalized = code.trim().toUpperCase(Locale.ROOT);
        switch (normalized) {
            case "ELECTRICO":
                return Tipo.ELÉCTRICO;
            case "PSIQUICO":
                return Tipo.PSÍQUICO;
            case "DRAGON":
                return Tipo.DRAGÓN;
            default:
                try {
                    return Tipo.valueOf(normalized);
                } catch (IllegalArgumentException ex) {
                    throw new PokemonDataException("Tipo desconocido: " + code, ex);
                }
        }
    }
}
