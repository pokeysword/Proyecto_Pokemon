package org.example.data;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;

/**
 * Configuracion de conexion a base de datos (desde .env o variables de entorno).
 */
public class DbConfig {
    private static final String DEFAULT_URL = "jdbc:postgresql://localhost:5432/proyecto_pokemon";
    private static final String DEFAULT_SCHEMA = "pokemon";

    public final String url;
    public final String user;
    public final String password;
    public final String schema;

    private DbConfig(Properties dotenv) {
        this.url = readValue(dotenv, "DB_URL", "URL", DEFAULT_URL);
        this.user = readValue(dotenv, "DB_USER", "USER", "");
        this.password = readValue(dotenv, "DB_PASSWORD", "PASSWORD", "");
        this.schema = readValue(dotenv, "DB_SCHEMA", "SCHEMA", DEFAULT_SCHEMA);
    }

    /**
     * Carga la configuracion desde el entorno y el archivo .env.
     *
     * @return configuracion de base de datos.
     * @throws PokemonDataException si hay errores leyendo .env.
     */
    public static DbConfig load() throws PokemonDataException {
        Properties dotenv = loadDotEnv();
        return new DbConfig(dotenv);
    }

    /**
     * Lee un valor dando prioridad a variables de entorno.
     *
     * @param dotenv propiedades de .env.
     * @param envKey clave de entorno.
     * @param dotenvKey clave alternativa en .env.
     * @param defaultValue valor por defecto.
     * @return valor resuelto.
     */
    private static String readValue(Properties dotenv, String envKey, String dotenvKey, String defaultValue) {
        String envValue = System.getenv(envKey);
        if (envValue != null && !envValue.trim().isEmpty()) {
            return envValue.trim();
        }
        String dotenvValue = dotenv.getProperty(envKey);
        if (dotenvValue == null) {
            dotenvValue = dotenv.getProperty(dotenvKey);
        }
        if (dotenvValue != null && !dotenvValue.trim().isEmpty()) {
            return dotenvValue.trim();
        }
        return defaultValue;
    }

    /**
     * Carga el archivo .env si existe.
     *
     * @return propiedades cargadas.
     * @throws PokemonDataException si hay errores de lectura.
     */
    private static Properties loadDotEnv() throws PokemonDataException {
        Properties dotenv = new Properties();
        Path envPath = Paths.get(".env");
        if (!Files.exists(envPath)) {
            return dotenv;
        }
        try {
            List<String> lines = Files.readAllLines(envPath, StandardCharsets.UTF_8);
            for (String line : lines) {
                String trimmed = line.trim();
                if (trimmed.isEmpty() || trimmed.startsWith("#")) {
                    continue;
                }
                int equalsIndex = trimmed.indexOf('=');
                if (equalsIndex <= 0) {
                    continue;
                }
                String key = trimmed.substring(0, equalsIndex).trim();
                String value = trimmed.substring(equalsIndex + 1).trim();
                if (value.endsWith(";")) {
                    value = value.substring(0, value.length() - 1).trim();
                }
                if ((value.startsWith("\"") && value.endsWith("\"")) || (value.startsWith("'") && value.endsWith("'"))) {
                    value = value.substring(1, value.length() - 1);
                }
                if (!key.isEmpty()) {
                    dotenv.setProperty(key, value);
                }
            }
        } catch (IOException ex) {
            throw new PokemonDataException("Error leyendo .env", ex);
        }
        return dotenv;
    }
}
