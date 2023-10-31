package net.johnnyconsole.cp630.project;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TemperatureClient extends Application {

    public void start(Stage ps) {
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);

        Button setup = new Button("Setup"),
                signin = new Button("Sign In"),
                close = new Button("Close");

        setup.setMaxWidth(Double.MAX_VALUE);
        setup.setMinHeight(30);
        signin.setMaxWidth(Double.MAX_VALUE);
        signin.setMinHeight(30);
        close.setMaxWidth(Double.MAX_VALUE);
        close.setMinHeight(30);

        Label header = new Label("Temperature Client: Sign In");
        header.setFont(Font.font(20));
        GridPane.setHalignment(header, HPos.CENTER);

        TextField username = new TextField();
        PasswordField password = new PasswordField();

        pane.add(header, 0, 0, 2, 1);
        pane.addRow(1, new Label("Enter Username:"), username);
        pane.addRow(2, new Label("Enter Password:"), password);
        pane.addRow(3, close, signin);
        pane.add(setup, 0, 4, 2, 1);


        ps.setScene(new Scene(pane));
        ps.setTitle("Client Sign In");
        ps.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
