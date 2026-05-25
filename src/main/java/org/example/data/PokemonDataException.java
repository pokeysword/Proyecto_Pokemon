package org.example.data;

public class PokemonDataException extends Exception {
    public PokemonDataException(String message) {
        super(message);
    }

    public PokemonDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
