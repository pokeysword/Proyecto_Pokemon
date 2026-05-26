package org.example.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class PostgresConnectionFactory {
    private final String url;
    private final String user;
    private final String password;
    private final String schema;

    public PostgresConnectionFactory(DbConfig config) throws PokemonDataException {
        this.url = config.url;
        this.user = config.user;
        this.password = config.password;
        this.schema = validateSchema(config.schema);
    }

    public PostgresConnectionFactory(String url, String user, String password, String schema) throws PokemonDataException {
        this.url = url;
        this.user = user;
        this.password = password;
        this.schema = validateSchema(schema);
    }

    public Connection open() throws SQLException {
        Connection connection = DriverManager.getConnection(url, user, password);
        if (schema != null && !schema.isEmpty()) {
            try (Statement stmt = connection.createStatement()) {
                stmt.execute("SET search_path TO " + schema);
            }
        }
        return connection;
    }

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
