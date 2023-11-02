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
import net.johnnyconsole.cp630.project.client.TemperatureClient;

public class ConfirmAppCloseDialog extends Application {

    @Override
    public void start(Stage ps) {
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);

        Button yes = new Button("Yes"),
                no = new Button("No");

        yes.setMaxWidth(Double.MAX_VALUE);
        yes.setMinHeight(30);
        no.setMaxWidth(Double.MAX_VALUE);
        no.setMinHeight(30);

        Label header = new Label("Are you sure you want to exit?");
        header.setFont(Font.font(20));
        GridPane.setHalignment(header, HPos.CENTER);


        pane.addColumn(0, header, yes, no);

        no.setOnAction(e -> {
            ps.close();
            new TemperatureClient().start(new Stage());
        });

        yes.setOnAction(e -> ps.close());


        ps.setScene(new Scene(pane));
        ps.setTitle("Client Sign In");
        ps.show();
    }
}
