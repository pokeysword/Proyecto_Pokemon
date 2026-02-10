package org.example;

import java.util.Scanner;

public class GameView {
    private Scanner scanner;

    public GameView() {
        scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("Bienvenido al juego Pokémon");
        System.out.println("Selecciona una opción para comenzar...");
    }
}
