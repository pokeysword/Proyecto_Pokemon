package org.example.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Fabrica de conexiones a PostgreSQL.
 */
public class PostgresConnectionFactory {
    private final String url;
    private final String user;
    private final String password;
    private final String schema;

    /**
     * Crea la fabrica a partir de una configuracion.
     *
     * @param config configuracion de base de datos.
     * @throws PokemonDataException si el schema es invalido.
     */
    public PostgresConnectionFactory(DbConfig config) throws PokemonDataException {
        this.url = config.url;
        this.user = config.user;
        this.password = config.password;
        this.schema = validateSchema(config.schema);
    }

    /**
     * Crea la fabrica con parametros explicitos.
     *
     * @param url url jdbc.
     * @param user usuario.
     * @param password password.
     * @param schema schema a usar.
     * @throws PokemonDataException si el schema es invalido.
     */
    public PostgresConnectionFactory(String url, String user, String password, String schema) throws PokemonDataException {
        this.url = url;
        this.user = user;
        this.password = password;
        this.schema = validateSchema(schema);
    }

    /**
     * Abre una conexion y configura el schema.
     *
     * @return conexion abierta.
     * @throws SQLException si falla la conexion.
     */
    public Connection open() throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        if (schema != null && !schema.isEmpty()) {
            try (Statement stmt = connection.createStatement()) {
                stmt.execute("SET search_path TO " + schema);
            }
        }
        return connection;
    }

    /**
     * Valida el schema y lo normaliza.
     *
     * @param schema schema a validar.
     * @return schema valido o null si no se define.
     * @throws PokemonDataException si el schema es invalido.
     */
    private String validateSchema(String schema) throws PokemonDataException {
        if (schema == null || schema.trim().isEmpty()) {
            return null;
        }
        String trimmed = schema.trim();
        if (!trimmed.matches("[A-Za-z0-9_]+")) {
            throw new PokemonDataException("Schema invalido: " + trimmed);
        }
        return trimmed;
    }
}
