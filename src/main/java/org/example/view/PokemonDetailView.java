package org.example.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.Pokemon;

/**
 * Vista detallada de un Pokémon
 */
public class PokemonDetailView {
    private Pokemon pokemon;
    private Stage detailStage;

    public PokemonDetailView(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    /**
     * Muestra la ventana de detalles del Pokémon
     */
    public void show() {
        detailStage = new Stage();
        detailStage.setTitle("Detalles de " + pokemon.getNombre());
        detailStage.setWidth(600);
        detailStage.setHeight(700);

        VBox root = new VBox(15);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #2c3e50;");

        // Información básica
        GridPane infoGrid = crearGridInfoBasica();
        root.getChildren().add(infoGrid);

        // Estadísticas
        VBox estadisticasBox = crearBoxEstadisticas();
        root.getChildren().add(new Separator());
        root.getChildren().add(estadisticasBox);

        // Movimientos
        VBox movimientosBox = crearBoxMovimientos();
        root.getChildren().add(new Separator());
        root.getChildren().add(movimientosBox);

        // Habilidad
        VBox habilidadBox = crearBoxHabilidad();
        root.getChildren().add(new Separator());
        root.getChildren().add(habilidadBox);

        ScrollPane scrollPane = new ScrollPane(root);
        scrollPane.setFitToWidth(true);

        Scene scene = new Scene(scrollPane);
        detailStage.setScene(scene);
        detailStage.show();
    }

    /**
     * Crea el grid con información básica
     */
    private GridPane crearGridInfoBasica() {
        GridPane grid = new GridPane();
        grid.setHgap(20);
        grid.setVgap(15);
        grid.setPadding(new Insets(10));
        grid.setStyle("-fx-background-color: #34495e; -fx-border-color: #f39c12; -fx-border-width: 2; -fx-border-radius: 5;");

        // Nombre
        Label lblNombre = new Label("Nombre:");
        lblNombre.setStyle("-fx-font-weight: bold; -fx-text-fill: #ecf0f1;");
        Label valNombre = new Label(pokemon.getNombre());
        valNombre.setStyle("-fx-font-size: 14; -fx-text-fill: #f39c12;");

        // Tipo
        Label lblTipo = new Label("Tipo:");
        lblTipo.setStyle("-fx-font-weight: bold; -fx-text-fill: #ecf0f1;");
        Label valTipo = new Label(pokemon.getTipo().getNombre());
        valTipo.setStyle("-fx-font-size: 14; -fx-text-fill: #3498db;");

        // Nivel
        Label lblLevel = new Label("Nivel:");
        lblLevel.setStyle("-fx-font-weight: bold; -fx-text-fill: #ecf0f1;");
        Label valLevel = new Label(String.valueOf(pokemon.getLevel()));
        valLevel.setStyle("-fx-font-size: 14; -fx-text-fill: #27ae60;");


        grid.add(lblNombre, 0, 0);
        grid.add(valNombre, 1, 0);
        grid.add(lblTipo, 0, 1);
        grid.add(valTipo, 1, 1);
        grid.add(lblLevel, 0, 2);
        grid.add(valLevel, 1, 2);
        return grid;
    }

    /**
     * Crea el box de estadísticas
     */
    private VBox crearBoxEstadisticas() {
        VBox box = new VBox(8);
        box.setStyle("-fx-background-color: #34495e; -fx-border-color: #27ae60; -fx-border-width: 2; -fx-padding: 10; -fx-border-radius: 5;");

        Label title = new Label("⚔️ Estadísticas");
        title.setStyle("-fx-font-size: 14; -fx-font-weight: bold; -fx-text-fill: #27ae60;");

        addEstadistica(box, "HP", pokemon.getEstadisticas().getHP(), pokemon.getEstadisticas().getHPMax());
        addEstadistica(box, "Ataque", pokemon.getEstadisticas().getAtaque(), 160);
        addEstadistica(box, "Defensa", pokemon.getEstadisticas().getDefensa(), 160);
        addEstadistica(box, "Ataque Especial", pokemon.getEstadisticas().getAtaqueEspecial(), 160);
        addEstadistica(box, "Defensa Especial", pokemon.getEstadisticas().getDefensaEspecial(), 160);
        addEstadistica(box, "Velocidad", pokemon.getEstadisticas().getVelocidad(), 160);

        box.getChildren().add(0, title);
        return box;
    }

    /**
     * Agrega una estadística al box
     */
    private void addEstadistica(VBox box, String nombre, double valor, double maximo) {
        HBox estatBox = new HBox(10);
        estatBox.setAlignment(Pos.CENTER_LEFT);

        Label lblNombre = new Label(nombre + ":");
        lblNombre.setPrefWidth(150);
        lblNombre.setStyle("-fx-text-fill: #bdc3c7;");

        Label lblValor = new Label(String.format("%.0f/%.0f", valor, maximo));
        lblValor.setPrefWidth(80);
        lblValor.setStyle("-fx-text-fill: #ecf0f1; -fx-text-alignment: right;");

        ProgressBar progressBar = new ProgressBar(valor / maximo);
        progressBar.setPrefWidth(200);

        estatBox.getChildren().addAll(lblNombre, progressBar, lblValor);
        box.getChildren().add(estatBox);
    }

    /**
     * Crea el box de movimientos
     */
    private VBox crearBoxMovimientos() {
        VBox box = new VBox(8);
        box.setStyle("-fx-background-color: #34495e; -fx-border-color: #3498db; -fx-border-width: 2; -fx-padding: 10; -fx-border-radius: 5;");

        Label title = new Label("💫 Movimientos");
        title.setStyle("-fx-font-size: 14; -fx-font-weight: bold; -fx-text-fill: #3498db;");
        box.getChildren().add(title);

        for (int i = 0; i < pokemon.getMovimientos().size(); i++) {
            var movimiento = pokemon.getMovimientos().get(i);
            HBox movBox = new HBox(10);
            movBox.setPadding(new Insets(8));
            movBox.setStyle("-fx-background-color: #2c3e50; -fx-border-color: #3498db; -fx-border-width: 1; -fx-border-radius: 3;");

            Label numMov = new Label((i + 1) + ".");
            numMov.setStyle("-fx-text-fill: #f39c12; -fx-font-weight: bold;");

            Label nomMov = new Label(movimiento.getNombre());
            nomMov.setStyle("-fx-text-fill: #ecf0f1; -fx-font-size: 12;");

            Label power = new Label("Poder: " + movimiento.getPotencia());
            power.setStyle("-fx-text-fill: #bdc3c7; -fx-font-size: 10;");

            movBox.getChildren().addAll(numMov, nomMov, power);
            box.getChildren().add(movBox);
        }

        return box;
    }

    /**
     * Crea el box de habilidad
     */
    private VBox crearBoxHabilidad() {
        VBox box = new VBox(8);
        box.setStyle("-fx-background-color: #34495e; -fx-border-color: #e74c3c; -fx-border-width: 2; -fx-padding: 10; -fx-border-radius: 5;");

        Label title = new Label("🔮 Habilidad");
        title.setStyle("-fx-font-size: 14; -fx-font-weight: bold; -fx-text-fill: #e74c3c;");

        Label habilidad = new Label(pokemon.getHabilidad().getNombre());
        habilidad.setStyle("-fx-font-size: 12; -fx-text-fill: #ecf0f1;");

        box.getChildren().addAll(title, habilidad);
        return box;
    }
}
