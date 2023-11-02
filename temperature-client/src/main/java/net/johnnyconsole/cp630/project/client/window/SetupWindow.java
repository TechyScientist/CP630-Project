package net.johnnyconsole.cp630.project.client.window;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SetupWindow extends Application {

    @Override
    public void start(Stage ps) {
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);

        TextField model = new TextField();

        Button createDB = new Button("Create Database"),
                genRegression = new Button("Generate Regression..."),
                back = new Button("Return to Menu");

        createDB.setMaxWidth(Double.MAX_VALUE);
        createDB.setMinHeight(40);
        genRegression.setMaxWidth(Double.MAX_VALUE);
        genRegression.setMinHeight(40);
        back.setMaxWidth(Double.MAX_VALUE);
        back.setMinHeight(40);

        pane.add(createDB,0,0,2,1);

        pane.addRow(2, new Label("Regression Model Name:"), model);
        pane.add(genRegression, 0, 3, 2, 1);

        pane.add(back, 0, 5, 2, 1);


        ps.setScene(new Scene(pane));
        ps.setTitle("Setup");
        ps.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
