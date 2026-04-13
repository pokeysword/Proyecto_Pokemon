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
            System.out.println("1. Elegir pokemon\n2. Ver equipo\n3. Combatir con el equipo seleccionado");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    if (cont < 4) {
                        System.out.println("Elige un pokemon:\n1.RotomWash\n2.Garchomp\n3.Togekiss\n4.Metagross\n5.Milotic\n6.Arcanine\n7.Amoonguss\n8.Dragapult\n9.Excadrill\n10.Sylveon");
                        numero = scanner.nextInt();
                        scanner.nextLine();
                        agregarPokemon(numero);
                        cont++;
                    } else {
                        System.out.println("Equipo completo");
                    }
                    break;
                case 2:
                    verEquipo();
                    break;
                case 3:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida");
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
                System.out.println("No elegiste ningún pokemon");
                break;
        }
    }

    public void verEquipo() {
        System.out.println("\n=== Tu Equipo ===");
        for (Pokemon p : listaPokemon) {
            System.out.println("- " + p.getNombre());
        }
        System.out.println();
    }

    public ArrayList<Pokemon> getListaPokemon() {
        return listaPokemon;
    }

    public String getNombre() {
        return nombre;
    }
}