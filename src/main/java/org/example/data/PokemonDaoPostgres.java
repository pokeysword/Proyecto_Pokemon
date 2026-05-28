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

/**
 * DAO para cargar Pokemon desde PostgreSQL.
 */
public class PokemonDaoPostgres {
    private static final String QUERY_POKEMON = "SELECT id, nombre, nivel, ps, ataque, defensa, ataque_especial, defensa_especial, velocidad, habilidad_code FROM pokemon ORDER BY id";
    private static final String QUERY_TIPOS = "SELECT pokemon_id, tipo_code, slot FROM pokemon_tipo ORDER BY pokemon_id, slot";
    private static final String QUERY_MOVIMIENTOS = "SELECT pokemon_id, movimiento_code, slot FROM pokemon_movimiento ORDER BY pokemon_id, slot";

    private final PostgresConnectionFactory connectionFactory;

    /**
     * Crea el DAO con una fabrica de conexiones.
     *
     * @param connectionFactory fabrica de conexiones.
     */
    public PokemonDaoPostgres(PostgresConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    /**
     * Carga el catalogo completo de Pokemon desde la base de datos.
     *
     * @return mapa de id a Pokemon.
     * @throws PokemonDataException si hay errores de datos o consulta.
     */
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

    /**
     * Carga los datos base de Pokemon.
     *
     * @param connection conexion abierta.
     * @param rows mapa de filas a completar.
     * @throws SQLException si falla la consulta.
     */
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

    /**
     * Carga los tipos de cada Pokemon.
     *
     * @param connection conexion abierta.
     * @param rows mapa de filas a completar.
     * @throws SQLException si falla la consulta.
     * @throws PokemonDataException si hay datos invalidos.
     */
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

    /**
     * Carga los movimientos de cada Pokemon.
     *
     * @param connection conexion abierta.
     * @param rows mapa de filas a completar.
     * @throws SQLException si falla la consulta.
     * @throws PokemonDataException si hay datos invalidos.
     */
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

    /**
     * Construye la lista de tipos para una fila.
     *
     * @param row fila de datos.
     * @return lista de tipos.
     * @throws PokemonDataException si no hay tipos.
     */
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

    /**
     * Construye la lista de movimientos para una fila.
     *
     * @param row fila de datos.
     * @return lista de movimientos.
     * @throws PokemonDataException si no hay movimientos.
     */
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

    /**
     * Fila interna con datos de Pokemon.
     */
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

        /**
         * Crea una fila con datos base.
         */
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
