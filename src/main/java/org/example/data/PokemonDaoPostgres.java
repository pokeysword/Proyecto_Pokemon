package org.example.data;

import org.example.Pokemon;
import org.example.Tipo;
import org.example.habilidades.Habilidad;
import org.example.movimientos.Movimiento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class PokemonDaoPostgres {
    private static final String QUERY_POKEMON = "SELECT id, nombre, nivel, ps, ataque, defensa, ataque_especial, defensa_especial, velocidad, habilidad_code FROM pokemon ORDER BY id";
    private static final String QUERY_TIPOS = "SELECT pokemon_id, tipo_code, slot FROM pokemon_tipo ORDER BY pokemon_id, slot";
    private static final String QUERY_MOVIMIENTOS = "SELECT pokemon_id, movimiento_code, slot FROM pokemon_movimiento ORDER BY pokemon_id, slot";

    private final PostgresConnectionFactory connectionFactory;

    public PokemonDaoPostgres(PostgresConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public Map<Integer, Pokemon> cargarPokemon() throws PokemonDataException {
        Map<Integer, PokemonRow> rows = new LinkedHashMap<>();

        try (Connection connection = connectionFactory.open()) {
            cargarBasePokemon(connection, rows);
            cargarTipos(connection, rows);
            cargarMovimientos(connection, rows);
        } catch (SQLException ex) {
            throw new PokemonDataException("Error consultando la base de datos", ex);
        }

        Map<Integer, Pokemon> resultado = new LinkedHashMap<>();
        for (PokemonRow row : rows.values()) {
            Habilidad habilidad = HabilidadFactory.crear(row.habilidadCode);
            ArrayList<Tipo> tipos = buildTipos(row);
            ArrayList<Movimiento> movimientos = buildMovimientos(row);
            Pokemon pokemon = new Pokemon(
                row.nombre,
                row.nivel,
                tipos,
                habilidad,
                row.ps,
                row.ataque,
                row.defensa,
                row.ataqueEspecial,
                row.defensaEspecial,
                row.velocidad,
                movimientos
            );
            resultado.put(row.id, pokemon);
        }

        if (resultado.isEmpty()) {
            throw new PokemonDataException("No hay Pokemon cargados desde la base de datos");
        }

        return resultado;
    }

    private void cargarBasePokemon(Connection connection, Map<Integer, PokemonRow> rows) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(QUERY_POKEMON);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                PokemonRow row = new PokemonRow(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getInt("nivel"),
                    rs.getInt("ps"),
                    rs.getInt("ataque"),
                    rs.getInt("defensa"),
                    rs.getInt("ataque_especial"),
                    rs.getInt("defensa_especial"),
                    rs.getInt("velocidad"),
                    rs.getString("habilidad_code")
                );
                rows.put(row.id, row);
            }
        }
    }

    private void cargarTipos(Connection connection, Map<Integer, PokemonRow> rows) throws SQLException, PokemonDataException {
        try (PreparedStatement stmt = connection.prepareStatement(QUERY_TIPOS);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int pokemonId = rs.getInt("pokemon_id");
                PokemonRow row = rows.get(pokemonId);
                if (row == null) {
                    continue;
                }
                int slot = rs.getInt("slot");
                if (slot < 1 || slot > row.tipos.length) {
                    throw new PokemonDataException("Slot de tipo invalido para pokemon " + pokemonId + ": " + slot);
                }
                Tipo tipo = TipoMapper.fromDbCode(rs.getString("tipo_code"));
                row.tipos[slot - 1] = tipo;
            }
        }
    }

    private void cargarMovimientos(Connection connection, Map<Integer, PokemonRow> rows) throws SQLException, PokemonDataException {
        try (PreparedStatement stmt = connection.prepareStatement(QUERY_MOVIMIENTOS);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int pokemonId = rs.getInt("pokemon_id");
                PokemonRow row = rows.get(pokemonId);
                if (row == null) {
                    continue;
                }
                int slot = rs.getInt("slot");
                if (slot < 1 || slot > row.movimientos.length) {
                    throw new PokemonDataException("Slot de movimiento invalido para pokemon " + pokemonId + ": " + slot);
                }
                Movimiento movimiento = MovimientoFactory.crear(rs.getString("movimiento_code"));
                row.movimientos[slot - 1] = movimiento;
            }
        }
    }

    private ArrayList<Tipo> buildTipos(PokemonRow row) throws PokemonDataException {
        ArrayList<Tipo> tipos = new ArrayList<>();
        for (Tipo tipo : row.tipos) {
            if (tipo != null) {
                tipos.add(tipo);
            }
        }
        if (tipos.isEmpty()) {
            throw new PokemonDataException("Pokemon sin tipos: " + row.nombre);
        }
        return tipos;
    }

    private ArrayList<Movimiento> buildMovimientos(PokemonRow row) throws PokemonDataException {
        ArrayList<Movimiento> movimientos = new ArrayList<>();
        for (Movimiento movimiento : row.movimientos) {
            if (movimiento != null) {
                movimientos.add(movimiento);
            }
        }
        if (movimientos.isEmpty()) {
            throw new PokemonDataException("Pokemon sin movimientos: " + row.nombre);
        }
        return movimientos;
    }

    private static class PokemonRow {
        private final int id;
        private final String nombre;
        private final int nivel;
        private final int ps;
        private final int ataque;
        private final int defensa;
        private final int ataqueEspecial;
        private final int defensaEspecial;
        private final int velocidad;
        private final String habilidadCode;
        private final Tipo[] tipos;
        private final Movimiento[] movimientos;

        private PokemonRow(int id, String nombre, int nivel, int ps, int ataque, int defensa,
                           int ataqueEspecial, int defensaEspecial, int velocidad, String habilidadCode) {
            this.id = id;
            this.nombre = nombre;
            this.nivel = nivel;
            this.ps = ps;
            this.ataque = ataque;
            this.defensa = defensa;
            this.ataqueEspecial = ataqueEspecial;
            this.defensaEspecial = defensaEspecial;
            this.velocidad = velocidad;
            this.habilidadCode = habilidadCode;
            this.tipos = new Tipo[2];
            this.movimientos = new Movimiento[4];
        }
    }
}

