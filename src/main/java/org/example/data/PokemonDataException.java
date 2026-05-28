package org.example.data;

/**
 * Excepcion para errores de carga de datos Pokemon.
 */
public class PokemonDataException extends Exception {
    /**
     * Crea una excepcion con mensaje.
     *
     * @param message mensaje de error.
     */
    public PokemonDataException(String message) {
        super(message);
    }

    /**
     * Crea una excepcion con mensaje y causa.
     *
     * @param message mensaje de error.
     * @param cause causa original.
     */
    public PokemonDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
