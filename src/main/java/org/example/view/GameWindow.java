package org.example.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.Main;
import org.example.Persona;
import org.example.Pokemon;

/**
 * Ventana principal del juego Pokémon
 */
public class GameWindow extends Application {
    private Stage primaryStage;
    private Scene mainScene;
    private BorderPane root;
    private Persona jugador;
    private Persona rival;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Pokémon Battle Simulator");
        primaryStage.setWidth(1000);
        primaryStage.setHeight(700);

        showWelcomeScreen();

        primaryStage.show();
    }

    /**
     * Muestra la pantalla de bienvenida
     */
    private void showWelcomeScreen() {
        root = new BorderPane();
        root.setStyle("-fx-background-color: #2c3e50;");

        VBox center = new VBox(20);
        center.setPadding(new Insets(40));
        center.setAlignment(Pos.CENTER);
        center.setStyle("-fx-background-color: linear-gradient(to bottom, #34495e, #2c3e50);");

        Label titulo = new Label("Pokémon Battle Simulator");
        titulo.setStyle("-fx-font-size: 48; -fx-font-weight: bold; -fx-text-fill: #f39c12;");

        Label subtitulo = new Label("¡Bienvenido, Entrenador!");
        subtitulo.setStyle("-fx-font-size: 24; -fx-text-fill: #ecf0f1;");

        TextField nombreField = new TextField();
        nombreField.setPromptText("Ingresa tu nombre");
        nombreField.setStyle("-fx-font-size: 14; -fx-padding: 10;");
        nombreField.setMaxWidth(300);

        Button comenzarBtn = new Button("COMENZAR AVENTURA");
        comenzarBtn.setStyle(
                "-fx-font-size: 16; -fx-padding: 12 30; -fx-background-color: #e74c3c; " +
                        "-fx-text-fill: white; -fx-border-radius: 5; -fx-cursor: hand;"
        );
        comenzarBtn.setOnAction(e -> {
            String nombre = nombreField.getText().trim();
            if (!nombre.isEmpty()) {
                jugador = new Persona();
                jugador.setNombre(nombre);
                mostrarSeleccionEquipo();
            } else {
                mostrarAlerta("Por favor, ingresa tu nombre");
            }
        });

        center.getChildren().addAll(titulo, subtitulo, nombreField, comenzarBtn);
        root.setCenter(center);

        mainScene = new Scene(root, 1000, 700);
        primaryStage.setScene(mainScene);
    }

    /**
     * Muestra la pantalla de selección de equipo
     */
    public void mostrarSeleccionEquipo() {
        root = new BorderPane();
        root.setStyle("-fx-background-color: #2c3e50;");

        // Header
        Label header = new Label("🏆 Selecciona tu Equipo Pokémon");
        header.setStyle("-fx-font-size: 28; -fx-font-weight: bold; -fx-text-fill: #f39c12; -fx-padding: 15;");
        BorderPane.setAlignment(header, Pos.CENTER);
        root.setTop(header);

        // Contenido
        VBox content = new VBox(15);
        content.setPadding(new Insets(20));
        content.setStyle("-fx-background-color: #34495e;");

        ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.setFitToWidth(true);
        root.setCenter(scrollPane);

        // Botones inferiores
        HBox bottomBox = new HBox(10);
        bottomBox.setPadding(new Insets(15));
        bottomBox.setAlignment(Pos.CENTER);
        bottomBox.setStyle("-fx-background-color: #2c3e50;");

        Button crearEquipoBtn = new Button("Crear Equipo Automático");
        crearEquipoBtn.setStyle("-fx-font-size: 14; -fx-padding: 10 20; -fx-background-color: #27ae60; -fx-text-fill: white;");

        Button continuarBtn = new Button("Continuar a Batalla");
        continuarBtn.setStyle("-fx-font-size: 14; -fx-padding: 10 20; -fx-background-color: #3498db; -fx-text-fill: white;");
        continuarBtn.setDisable(true);
        continuarBtn.setOnAction(e -> mostrarBatalla());

        crearEquipoBtn.setOnAction(e -> {
            jugador.crearEquipoAutomatico();
            continuarBtn.setDisable(false);
            mostrarEquipoSeleccionado();
        });

        bottomBox.getChildren().addAll(crearEquipoBtn, continuarBtn);
        root.setBottom(bottomBox);

        mainScene = new Scene(root, 1000, 700);
        primaryStage.setScene(mainScene);
    }

    /**
     * Muestra el equipo seleccionado
     */
    private void mostrarEquipoSeleccionado() {
        VBox content = new VBox(15);
        content.setStyle("-fx-background-color: #34495e;");
        content.setPadding(new Insets(20));

        Label titulo = new Label("Tu Equipo Actual:");
        titulo.setStyle("-fx-font-size: 18; -fx-font-weight: bold; -fx-text-fill: #ecf0f1;");
        content.getChildren().add(titulo);

        for (int i = 0; i < jugador.getListaPokemon().size(); i++) {
            Pokemon p = jugador.getListaPokemon().get(i);
            HBox pokemonBox = new HBox(15);
            pokemonBox.setPadding(new Insets(12));
            pokemonBox.setStyle("-fx-background-color: #2c3e50; -fx-border-color: #f39c12; -fx-border-width: 2; -fx-border-radius: 5;");
            pokemonBox.setAlignment(Pos.CENTER_LEFT);

            Label numero = new Label((i + 1) + ".");
            numero.setStyle("-fx-font-size: 16; -fx-font-weight: bold; -fx-text-fill: #f39c12; -fx-min-width: 30;");

            VBox infoBox = new VBox(5);
            Label nombre = new Label(p.getNombre());
            nombre.setStyle("-fx-font-size: 14; -fx-font-weight: bold; -fx-text-fill: #ecf0f1;");

            Label tipo = new Label("Tipo: " + p.getTipo().getNombre());
            tipo.setStyle("-fx-font-size: 11; -fx-text-fill: #bdc3c7;");

            Label hp = new Label("HP: " + (int)p.getEstadisticas().getHPMax());
            hp.setStyle("-fx-font-size: 11; -fx-text-fill: #27ae60;");

            infoBox.getChildren().addAll(nombre, tipo, hp);
            pokemonBox.getChildren().addAll(numero, infoBox);
            content.getChildren().add(pokemonBox);
        }

        ScrollPane scrollPane = (ScrollPane) root.getCenter();
        scrollPane.setContent(content);
    }

    /**
     * Crea una tarjeta de Pokémon
     */
    private HBox crearPokemonCard(Pokemon pokemon) {
        HBox card = new HBox(15);
        card.setPadding(new Insets(10));
        card.setStyle("-fx-background-color: #2c3e50; -fx-border-color: #f39c12; -fx-border-width: 2; -fx-border-radius: 5;");

        VBox info = new VBox(5);
        Label nombre = new Label(pokemon.getNombre());
        nombre.setStyle("-fx-font-size: 16; -fx-font-weight: bold; -fx-text-fill: #f39c12;");

        Label tipo = new Label("Tipo: " + pokemon.getTipo().getNombre());
        tipo.setStyle("-fx-font-size: 12; -fx-text-fill: #bdc3c7;");

        Label hp = new Label("HP: " + pokemon.getEstadisticas().getHP() + "/" + pokemon.getEstadisticas().getHPMax());
        hp.setStyle("-fx-font-size: 12; -fx-text-fill: #27ae60;");

        info.getChildren().addAll(nombre, tipo, hp);
        card.getChildren().add(info);

        return card;
    }

    /**
     * Muestra la pantalla de batalla
     */
    public void mostrarBatalla() {
        // Crear rival automáticamente
        if (rival == null) {
            rival = Main.crearRival();
        }
        BattleView battleView = new BattleView(this, jugador, rival);
        battleView.show();
    }

    /**
     * Muestra una alerta
     */
    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Aviso");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    /**
     * Establece el rival
     */
    public void setRival(Persona rival) {
        this.rival = rival;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
