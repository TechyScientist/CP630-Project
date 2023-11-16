package net.johnnyconsole.cp630.project.client.dialog;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ErrorDialog extends Application {

    private String message;
    public ErrorDialog(String message) {
        this.message = message;
    }
    @Override
    public void start(Stage ps) {
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);

        Button dismiss = new Button("Dismiss");

        dismiss.setMaxWidth(Double.MAX_VALUE);
        dismiss.setMinHeight(40);

        Label header = new Label(message);
        header.setFont(Font.font(20));
        GridPane.setHalignment(header, HPos.CENTER);


        pane.addColumn(0, header, dismiss);


        dismiss.setOnAction(e -> ps.close());

        ps.setScene(new Scene(pane));
        ps.setTitle("Error");
        ps.show();
    }
}
