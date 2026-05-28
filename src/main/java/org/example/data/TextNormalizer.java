package org.example.data;

/**
 * Utilidad para normalizar textos.
 */
public final class TextNormalizer {
    /**
     * Constructor privado para evitar instancias.
     */
    private TextNormalizer() {
    }

    /**
     * Normaliza un texto quitando espacios laterales.
     *
     * @param input texto original.
     * @return texto normalizado.
     */
    public static String normalize(String input) {
        if (input == null) {
            return "";
        }
        return input.trim();
    }
}
