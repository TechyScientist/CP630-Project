package net.johnnyconsole.cp630.project.client;

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
import net.johnnyconsole.cp630.project.client.dialog.ConfirmAppCloseDialog;
import net.johnnyconsole.cp630.project.client.window.SetupWindow;

public class TemperatureClient extends Application {

    @Override
    public void start(Stage ps) {
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);

        Button setup = new Button("Setup"),
                signin = new Button("Sign In"),
                close = new Button("Close");

        setup.setMaxWidth(Double.MAX_VALUE);
        setup.setMinHeight(40);
        signin.setMaxWidth(Double.MAX_VALUE);
        signin.setMinHeight(40);
        close.setMaxWidth(Double.MAX_VALUE);
        close.setMinHeight(40);

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

        close.setOnAction(e -> {
            ps.close();
            new ConfirmAppCloseDialog().start(new Stage());
        });

        setup.setOnAction(e -> {
            ps.close();
            new SetupWindow().start(new Stage());
        });

        ps.setScene(new Scene(pane));
        ps.setTitle("Client Sign In");
        ps.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
