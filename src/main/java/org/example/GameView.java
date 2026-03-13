package org.example;

import java.util.Scanner;

public class GameView {
    private Scanner scanner;

    public GameView() {
        scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("Bienvenido al juego Pokémon");
    }

    public void mostrarEquipo(Persona persona) {
        System.out.println("\n=== Equipo de " + persona.getNombre() + " ===");
        for (Pokemon p : persona.getListaPokemon()) {
            System.out.println("- " + p.getNombre());
        }
        System.out.println();
    }


    public void iniciarSeleccionEquipo(Persona persona) {
        System.out.println("\n--- Selección de Equipo ---");
        persona.crearEquipo();
        mostrarEquipo(persona);
    }
    public void seleccionarnombre(Persona persona) {
        System.out.print("¿Cuál es tu nombre, entrenador?\n ");
        String nombre = scanner.nextLine();
        persona.setNombre(nombre);
        System.out.println("¡Suerte, " + persona.getNombre() + "!");
    }

}