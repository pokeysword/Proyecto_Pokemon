package org.example.data;

import org.example.Tipo;

import java.util.Locale;

public final class TipoMapper {
    private TipoMapper() {
    }

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
