package net.johnnyconsole.cp630.project;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TemperatureClient extends Application {

    public void start(Stage ps) {
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);

        pane.addRow(0, new Label("Hello Client!"));

        ps.setScene(new Scene(pane));
        ps.setTitle("Hello Client!");
        ps.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
