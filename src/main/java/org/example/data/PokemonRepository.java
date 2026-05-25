package org.example.data;

import org.example.Pokemon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

public class PokemonRepository {
	private static final String DEFAULT_RESOURCE = "pokemons.txt";

	public Map<Integer, String> cargarNombresPokemon() throws PokemonDataException {
		return cargarNombresPokemon(DEFAULT_RESOURCE);
	}

	public Map<Integer, Pokemon> cargarPokemon(Map<String, Pokemon> catalogo) throws PokemonDataException {
		Map<Integer, String> nombres = cargarNombresPokemon(DEFAULT_RESOURCE);
		Map<Integer, Pokemon> resultado = new LinkedHashMap<>();

		for (Map.Entry<Integer, String> entry : nombres.entrySet()) {
			String nombre = entry.getValue();
			Pokemon base = catalogo.get(nombre);
			if (base == null) {
				throw new PokemonDataException("Pokemon no encontrado en catalogo: " + nombre);
			}
			resultado.put(entry.getKey(), base);
		}

		return resultado;
	}

	private Map<Integer, String> cargarNombresPokemon(String resourceName) throws PokemonDataException {
		InputStream input = getClass().getClassLoader().getResourceAsStream(resourceName);
		if (input == null) {
			throw new PokemonDataException("No se encuentra el archivo de datos: " + resourceName);
		}

		Map<Integer, String> resultado = new LinkedHashMap<>();

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8))) {
			String linea;
			int lineNumber = 0;

			while ((linea = reader.readLine()) != null) {
				lineNumber++;
				String trimmed = linea.trim();
				if (trimmed.isEmpty() || trimmed.startsWith("#")) {
					continue;
				}

				String[] parts = trimmed.split(";", 2);
				if (parts.length != 2) {
					throw new PokemonDataException("Formato invalido en linea " + lineNumber + ": " + trimmed);
				}

				int id;
				try {
					id = Integer.parseInt(parts[0].trim());
				} catch (NumberFormatException ex) {
					throw new PokemonDataException("Id invalido en linea " + lineNumber + ": " + parts[0].trim(), ex);
				}

				String nombre = TextNormalizer.normalize(parts[1]);
				if (nombre.isEmpty()) {
					throw new PokemonDataException("Nombre vacio en linea " + lineNumber);
				}

				if (resultado.containsKey(id)) {
					throw new PokemonDataException("Id duplicado en linea " + lineNumber + ": " + id);
				}

				resultado.put(id, nombre);
			}
		} catch (IOException ex) {
			throw new PokemonDataException("Error leyendo archivo de pokemon: " + resourceName, ex);
		}

		if (resultado.isEmpty()) {
			throw new PokemonDataException("El archivo de pokemon esta vacio: " + resourceName);
		}

		return resultado;
	}
}
