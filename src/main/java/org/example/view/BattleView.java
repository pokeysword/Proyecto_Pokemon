package org.example.view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.util.Duration;
import org.example.Battle;
import org.example.DamageCalculator;
import org.example.Persona;
import org.example.Pokemon;
import org.example.movimientos.Movimiento;

/**
 * Vista de la batalla Pokémon
 */
public class BattleView {
    private GameWindow gameWindow;
    private Persona jugador;
    private Persona rival;
    private Battle batalla;
    private Stage battleStage;
    private TextArea logBatalla;
    private Label labelJugadorInfo;
    private Label labelRivalInfo;
    private ProgressBar hpJugador;
    private ProgressBar hpRival;
    private VBox botonesMovimientos;
    private boolean turnoEnProgreso = false;

    public BattleView(GameWindow gameWindow, Persona jugador, Persona rival) {
        this.gameWindow = gameWindow;
        this.jugador = jugador;
        this.rival = rival;
        this.batalla = new Battle(jugador, rival);
    }

    /**
     * Muestra la ventana de batalla
     */
    public void show() {
        battleStage = new Stage();
        battleStage.setTitle("⚔️ Batalla Pokémon");
        battleStage.setWidth(1200);
        battleStage.setHeight(1000);

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #1a1a1a;");

        // Top: Información de Pokémon
        HBox topBox = crearTopBox();
        root.setTop(topBox);

        // Center: Visualización de batalla
        HBox centerBox = crearCenterBox();
        root.setCenter(centerBox);

        // Bottom: Acciones y log
        VBox bottomBox = crearBottomBox();
        root.setBottom(bottomBox);

        Scene scene = new Scene(root);
        battleStage.setScene(scene);
        battleStage.show();

        // Iniciar batalla
        iniciarBatalla();
    }

    /**
     * Crea la sección superior (info de Pokémon)
     */
    private HBox crearTopBox() {
        HBox topBox = new HBox(20);
        topBox.setPadding(new Insets(15));
        topBox.setStyle("-fx-background-color: #2c3e50;");

        // Pokémon del jugador
        VBox jugadorBox = new VBox(10);
        jugadorBox.setStyle("-fx-background-color: #34495e; -fx-border-color: #3498db; -fx-border-width: 2; -fx-padding: 10;");

        Label nombreJugador = new Label("Tu Pokémon:");
        nombreJugador.setStyle("-fx-font-size: 14; -fx-text-fill: #3498db; -fx-font-weight: bold;");

        labelJugadorInfo = new Label();
        labelJugadorInfo.setStyle("-fx-font-size: 12; -fx-text-fill: #ecf0f1;");

        hpJugador = new ProgressBar(1.0);
        hpJugador.setPrefWidth(250);
        hpJugador.setStyle("-fx-control-inner-background: #27ae60;");

        jugadorBox.getChildren().addAll(nombreJugador, labelJugadorInfo, hpJugador);

        // Pokémon del rival
        VBox rivalBox = new VBox(10);
        rivalBox.setStyle("-fx-background-color: #34495e; -fx-border-color: #e74c3c; -fx-border-width: 2; -fx-padding: 10;");

        Label nombreRival = new Label("Pokémon Rival:");
        nombreRival.setStyle("-fx-font-size: 14; -fx-text-fill: #e74c3c; -fx-font-weight: bold;");

        labelRivalInfo = new Label();
        labelRivalInfo.setStyle("-fx-font-size: 12; -fx-text-fill: #ecf0f1;");

        hpRival = new ProgressBar(1.0);
        hpRival.setPrefWidth(250);
        hpRival.setStyle("-fx-control-inner-background: #e74c3c;");

        rivalBox.getChildren().addAll(nombreRival, labelRivalInfo, hpRival);

        topBox.getChildren().addAll(jugadorBox, new Separator(javafx.geometry.Orientation.VERTICAL), rivalBox);
        HBox.setHgrow(topBox, javafx.scene.layout.Priority.ALWAYS);

        return topBox;
    }

    /**
     * Crea la sección central (log de batalla)
     */
    private HBox crearCenterBox() {
        HBox centerBox = new HBox();
        centerBox.setPadding(new Insets(15));
        centerBox.setStyle("-fx-background-color: #1a1a1a;");

        logBatalla = new TextArea();
        logBatalla.setEditable(false);
        logBatalla.setWrapText(true);
        logBatalla.setStyle("-fx-control-inner-background: #2c3e50; -fx-text-fill: #ecf0f1; -fx-font-size: 12;");
        logBatalla.setText("El enfrentamiento está a punto de comenzar...\n\n");

        centerBox.getChildren().add(logBatalla);
        HBox.setHgrow(centerBox, javafx.scene.layout.Priority.ALWAYS);

        return centerBox;
    }

    /**
     * Crea la sección inferior (botones y acciones)
     */
    private VBox crearBottomBox() {
        VBox bottomBox = new VBox(10);
        bottomBox.setPadding(new Insets(15));
        bottomBox.setStyle("-fx-background-color: #2c3e50;");
        bottomBox.setPrefHeight(280);

        Label labelAccion = new Label("Selecciona tu movimiento:");
        labelAccion.setStyle("-fx-font-size: 14; -fx-text-fill: #f39c12; -fx-font-weight: bold;");

        botonesMovimientos = new VBox(10);
        botonesMovimientos.setPadding(new Insets(10));
        botonesMovimientos.setStyle("-fx-background-color: #34495e;");
        botonesMovimientos.setPrefHeight(240);

        bottomBox.getChildren().addAll(labelAccion, botonesMovimientos);
        return bottomBox;
    }

    /**
     * Inicia la batalla
     */
    private void iniciarBatalla() {
        logBatalla.appendText("¡LA BATALLA HA COMENZADO!\n");
        logBatalla.appendText(jugador.getNombre() + " vs " + rival.getNombre() + "\n");
        logBatalla.appendText("-----------------------------------\n\n");

        actualizarUI();
        mostrarOpciones();
    }

    /**
     * Actualiza la interfaz con la información actual
     */
    private void actualizarUI() {
        if (!jugador.getListaPokemon().isEmpty() && !rival.getListaPokemon().isEmpty()) {
            Pokemon miPokemon = jugador.getPokemonActual();
            Pokemon pokemonRival = rival.getPokemonActual();

            if (miPokemon != null && pokemonRival != null) {
                labelJugadorInfo.setText(miPokemon.getNombre() + " (Nivel " + miPokemon.getLevel() + ")");
                double hpActualJ = miPokemon.getEstadisticas().getHPActual();
                double hpMaxJ = miPokemon.getEstadisticas().getHPMax();
                hpJugador.setProgress(hpActualJ / hpMaxJ);

                labelRivalInfo.setText(pokemonRival.getNombre() + " (Nivel " + pokemonRival.getLevel() + ")");
                double hpActualR = pokemonRival.getEstadisticas().getHPActual();
                double hpMaxR = pokemonRival.getEstadisticas().getHPMax();
                hpRival.setProgress(hpActualR / hpMaxR);
            }
        }
    }

    /**
     * Muestra las opciones de movimiento disponibles
     */
    private void mostrarOpciones() {
        botonesMovimientos.getChildren().clear();

        Pokemon miPokemon = jugador.getPokemonActual();
        if (miPokemon != null) {
            for (Movimiento mov : miPokemon.getMovimientos()) {
                Button btnmovimiento = crearBoetonMovimiento(mov);
                botonesMovimientos.getChildren().add(btnmovimiento);
            }
        }
    }

    /**
     * Crea un botón para un movimiento
     */
    private Button crearBoetonMovimiento(Movimiento movimiento) {
        Button btn = new Button(movimiento.getNombre());
        btn.setPrefWidth(Double.MAX_VALUE);
        btn.setPrefHeight(50);
        btn.setStyle("-fx-font-size: 14; -fx-padding: 12; -fx-text-fill: white; -fx-background-color: #3498db;");
        btn.setOnAction(e -> {
            if (!turnoEnProgreso) {
                deshabilitarTodosLosBotones();
                realizarTurno(movimiento);
            }
        });
        return btn;
    }

    /**
     * Deshabilita todos los botones de movimientos
     */
    private void deshabilitarTodosLosBotones() {
        for (javafx.scene.Node node : botonesMovimientos.getChildren()) {
            if (node instanceof Button) {
                ((Button) node).setDisable(true);
            }
        }
    }

    /**
     * Realiza un turno de batalla con daño real
     */
    private void realizarTurno(Movimiento movimientoJugador) {
        turnoEnProgreso = true;
        Pokemon miPokemon = jugador.getPokemonActual();
        Pokemon pokemonRival = rival.getPokemonActual();

        if (miPokemon == null || pokemonRival == null) return;

        // Jugador ataca
        logBatalla.appendText("\n⚡ " + jugador.getNombre() + " usa " + movimientoJugador.getNombre() + "!\n");

        // Calcular daño del jugador real
        int daño = DamageCalculator.calcularDaño(miPokemon, pokemonRival, movimientoJugador);
        pokemonRival.sufrirDaño(daño);
        logBatalla.appendText("   💥 " + pokemonRival.getNombre() + " recibe " + daño + " de daño!\n");

        actualizarUI();

        // Verificar si el rival está derrotado
        if (pokemonRival.estaDebilitado()) {
            logBatalla.appendText("\n✅ ¡" + pokemonRival.getNombre() + " ha sido derrotado!\n");
            logBatalla.appendText("🎉 ¡" + jugador.getNombre() + " gana la batalla!\n");
            deshabilitarTodosLosBotones();
            terminarBatalla(true);
            return;
        }

        // Rival ataca después de un delay
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(800), e -> {
            if (!pokemonRival.estaDebilitado()) {
                int indiceAleatorio = (int) (Math.random() * pokemonRival.getMovimientos().size());
                Movimiento movimientoRival = pokemonRival.getMovimientos().get(indiceAleatorio);

                logBatalla.appendText("\n⚡ " + rival.getNombre() + " usa " + movimientoRival.getNombre() + "!\n");

                // Calcular daño del rival real
                int dañoRival = DamageCalculator.calcularDaño(pokemonRival, miPokemon, movimientoRival);
                miPokemon.sufrirDaño(dañoRival);
                logBatalla.appendText("   💥 " + miPokemon.getNombre() + " recibe " + dañoRival + " de daño!\n");

                actualizarUI();

                // Verificar si el jugador está derrotado
                if (miPokemon.estaDebilitado()) {
                    logBatalla.appendText("\n❌ ¡" + miPokemon.getNombre() + " ha sido derrotado!\n");
                    logBatalla.appendText("💀 ¡" + rival.getNombre() + " gana la batalla!\n");
                    deshabilitarTodosLosBotones();
                    terminarBatalla(false);
                    return;
                }

                turnoEnProgreso = false;
                mostrarOpciones();
            }
        }));
        timeline.play();
    }

    /**
     * Verifica el estado de la batalla (OBSOLETO - se usa en realizarTurno)
     */
    private void verificarEstadoBatalla() {
        // Método no utilizado - la verificación ahora está en realizarTurno()
    }

    /**
     * Termina la batalla
     */
    private void terminarBatalla(boolean victoria) {
        String resultado = victoria ? "¡GANASTE LA BATALLA!" : "¡PERDISTE LA BATALLA!";
        logBatalla.appendText("\n\n" + resultado + "\n");

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(2000), e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Fin de la Batalla");
            alert.setHeaderText(null);
            alert.setContentText(resultado);
            alert.showAndWait();

            battleStage.close();
        }));
        timeline.play();
    }
