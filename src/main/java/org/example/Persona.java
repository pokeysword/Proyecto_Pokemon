package org.example;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import org.example.data.DbConfig;
import org.example.data.PokemonDaoPostgres;
import org.example.data.PokemonDataException;
import org.example.data.PostgresConnectionFactory;

/**
 * Representa a un entrenador con equipo y catalogo de Pokemon.
 */
public class Persona {
    private String nombre;
    private ArrayList<Pokemon> listaPokemon;
    private Map<Integer, Pokemon> catalogoPokemon;

    /**
     * Crea un entrenador con nombre por defecto y carga el catalogo.
     */
    public Persona() {
        this.nombre = "Entrenador";
        this.listaPokemon = new ArrayList<>();
        this.catalogoPokemon = new LinkedHashMap<>();
        cargarCatalogoPokemon();
    }

    /**
     * Establece el nombre del entrenador.
     *
     * @param nombre nombre del entrenador.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Permite crear el equipo mediante un menu interactivo.
     *
     * @return lista de Pokemon elegidos.
     */
    public ArrayList<Pokemon> crearEquipo() {
        Boolean salir = false;
        int cont = 0;

        do {
            int numero;
            Scanner scanner = new Scanner(System.in);
            GameView.mostrarMenuCrearEquipo();
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    if (cont < 4) {
                        if (catalogoPokemon.isEmpty()) {
                            GameView.mostrarErrorCargaPokemon("No hay datos disponibles.");
                            break;
                        }
                        GameView.mostrarMenuElegirPokemon(catalogoPokemon);
                        numero = scanner.nextInt();
                        scanner.nextLine();

                        // Validar que el número esté en rango
                        if (catalogoPokemon.containsKey(numero)) {
                            if (agregarPokemon(numero)) {
                                cont++;
                                GameView.mostrarPokemonAgregado(cont);
                            }
                        } else {
                            GameView.mostrarPokemonInexistente();
                        }
                    } else {
                        GameView.mostrarEquipoCompleto();
                    }
                    break;
                case 2:
                    verEquipo();
                    break;
                case 3:
                    if (cont >= 1) {
                        salir = true;
                    } else {
                        GameView.mostrarNecesitaPokemon(cont);
                    }
                    break;
                default:
                    GameView.mostrarOpcionNoValida();
            }
        } while (!salir);

        return listaPokemon;
    }

    /**
     * Agrega un Pokemon del catalogo al equipo.
     *
     * @param numero clave del catalogo.
     * @return true si se agrego.
     */
    private boolean agregarPokemon(int numero) {
        Pokemon base = catalogoPokemon.get(numero);
        if (base == null) {
            GameView.mostrarPokemonInexistente();
            return false;
        }
        listaPokemon.add(base.crearCopia());
        return true;
    }

    /**
     * Carga el catalogo de Pokemon desde la base de datos.
     */
    private void cargarCatalogoPokemon() {
        try {
            DbConfig config = DbConfig.load();
            PokemonDaoPostgres dao = new PokemonDaoPostgres(new PostgresConnectionFactory(config));
            catalogoPokemon = dao.cargarPokemon();
        } catch (PokemonDataException ex) {
            GameView.mostrarErrorCargaPokemon(ex.getMessage());
            System.exit(1);
        }
    }

    /**
     * Muestra el equipo actual por consola.
     */
    public void verEquipo() {
        GameView.mostrarTuEquipoHeader();
        for (Pokemon p : listaPokemon) {
            GameView.mostrarPokemonEquipo(p.getNombre());
        }
        GameView.saltoLinea();
    }

    /**
     * Obtiene la lista del equipo.
     *
     * @return lista de Pokemon.
     */
    public ArrayList<Pokemon> getListaPokemon() {
        return listaPokemon;
    }

    /**
     * Obtiene el catalogo de Pokemon disponible.
     *
     * @return mapa de Pokemon.
     */
    public Map<Integer, Pokemon> getCatalogoPokemon() {
        return catalogoPokemon;
    }

    /**
     * Obtiene el nombre del entrenador.
     *
     * @return nombre del entrenador.
     */
    public String getNombre() {
        return nombre;
    }
}