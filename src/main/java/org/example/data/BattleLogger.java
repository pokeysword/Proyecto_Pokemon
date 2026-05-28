package org.example.data;

import org.example.Pokemon;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Registra eventos de batalla en un archivo de texto.
 */
public class BattleLogger {
    private static final DateTimeFormatter TIMESTAMP_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final Path logPath;

    /**
     * Crea un logger que escribe en el archivo indicado.
     *
     * @param fileName nombre o ruta del archivo de log.
     */
    public BattleLogger(String fileName) {
        this.logPath = Paths.get(fileName);
    }

    /**
     * Registra el inicio de la batalla con jugadores y lideres.
     *
     * @param jugador1 nombre del jugador 1.
     * @param jugador2 nombre del jugador 2.
     * @param pokemon1 Pokemon inicial del jugador 1.
     * @param pokemon2 Pokemon inicial del jugador 2.
     */
    public void logInicio(String jugador1, String jugador2, Pokemon pokemon1, Pokemon pokemon2) {
        appendLine("=== Battle start " + timestamp() + " ===");
        appendLine("Players: " + jugador1 + " vs " + jugador2);
        appendLine("Lead: " + pokemon1.getNombre() + " vs " + pokemon2.getNombre());
    }

    /**
     * Registra un turno con acciones y PS actuales.
     *
     * @param turno numero de turno.
     * @param jugador1 nombre del jugador 1.
     * @param jugador2 nombre del jugador 2.
     * @param pokemon1 Pokemon activo del jugador 1.
     * @param pokemon2 Pokemon activo del jugador 2.
     * @param accion1 accion del jugador 1.
     * @param accion2 accion del jugador 2.
     */
    public void logTurno(int turno, String jugador1, String jugador2, Pokemon pokemon1, Pokemon pokemon2, String accion1, String accion2) {
        StringBuilder builder = new StringBuilder();
        builder.append("Turno ").append(turno).append(" | ");
        builder.append(jugador1).append("(").append(pokemon1.getNombre()).append("): ").append(accion1);
        builder.append(" | ");
        builder.append(jugador2).append("(").append(pokemon2.getNombre()).append("): ").append(accion2);
        builder.append(" | PS ").append(pokemon1.getModPs()).append(" - ").append(pokemon2.getModPs());
        appendLine(builder.toString());
    }

    /**
     * Registra el final de la batalla.
     *
     * @param ganador nombre del ganador.
     */
    public void logFin(String ganador) {
        appendLine("Ganador: " + ganador);
        appendLine("=== Battle end ===");
    }

    /**
     * Anade una linea al archivo de log.
     *
     * @param line linea a escribir.
     */
    private void appendLine(String line) {
        try (BufferedWriter writer = Files.newBufferedWriter(logPath, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            writer.write(line);
            writer.newLine();
        } catch (IOException ex) {
            System.out.println("No se pudo escribir el log: " + ex.getMessage());
        }
    }

    /**
     * Genera un timestamp legible.
     *
     * @return marca de tiempo.
     */
    private String timestamp() {
        return LocalDateTime.now().format(TIMESTAMP_FORMAT);
    }
}
