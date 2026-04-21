package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Persona {
    private String nombre;
    private ArrayList<Pokemon> listaPokemon;

    public Persona() {
        this.nombre = "Entrenador";
        this.listaPokemon = new ArrayList<>();
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

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
                        GameView.mostrarMenuElegirPokemon();
                        numero = scanner.nextInt();
                        scanner.nextLine();
                        
                        // Validar que el número esté en rango
                        if (numero >= 1 && numero <= 10) {
                            agregarPokemon(numero);
                            cont++;
                            GameView.mostrarPokemonAgregado(cont);
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

    private void agregarPokemon(int numero) {
        switch (numero) {
            case 1:
                listaPokemon.add(Main.RotomWash.crearCopia());
                break;
            case 2:
                listaPokemon.add(Main.Garchomp.crearCopia());
                break;
            case 3:
                listaPokemon.add(Main.Togekiss.crearCopia());
                break;
            case 4:
                listaPokemon.add(Main.Metagross.crearCopia());
                break;
            case 5:
                listaPokemon.add(Main.Milotic.crearCopia());
                break;
            case 6:
                listaPokemon.add(Main.Arcanine.crearCopia());
                break;
            case 7:
                listaPokemon.add(Main.Amoonguss.crearCopia());
                break;
            case 8:
                listaPokemon.add(Main.Dragapult.crearCopia());
                break;
            case 9:
                listaPokemon.add(Main.Excadrill.crearCopia());
                break;
            case 10:
                listaPokemon.add(Main.Sylveon.crearCopia());
                break;
            default:
                GameView.mostrarNoElegistePokemon();
                break;
        }
    }

    public void verEquipo() {
        GameView.mostrarTuEquipoHeader();
        for (Pokemon p : listaPokemon) {
            GameView.mostrarPokemonEquipo(p.getNombre());
        }
        GameView.saltoLinea();
    }

    public ArrayList<Pokemon> getListaPokemon() {
        return listaPokemon;
    }

    public String getNombre() {
        return nombre;
    }
}