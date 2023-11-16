package net.johnnyconsole.cp630.project.client.window;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import net.johnnyconsole.cp630.project.client.TemperatureClient;
import net.johnnyconsole.cp630.project.client.pane.AdministrationPane;
import net.johnnyconsole.cp630.project.client.pane.PredictionPane;
import net.johnnyconsole.cp630.project.client.util.AccessLevel;
import net.johnnyconsole.cp630.project.client.util.ApplicationSession;

public class ClientDashboard extends Application {
    @Override
    public void start(Stage ps) {
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);

        Label header = new Label("Welcome, " +
                (ApplicationSession.name.contains(" ") ?
                        ApplicationSession.name.substring(0, ApplicationSession.name.indexOf(" "))
                        : ApplicationSession.name) + "!");
        TabPane dashboard = new TabPane();
        Tab predictionTab = new Tab("Prediction"),
                adminTab = new Tab("Administration");
        Button signOut = new Button("Sign Out");

        header.setFont(Font.font(20));
        GridPane.setHalignment(header, HPos.CENTER);

        predictionTab.setContent(new PredictionPane());
        predictionTab.setClosable(false);

        dashboard.getTabs().add(predictionTab);
        if(ApplicationSession.accessLevel == AccessLevel.ACCESS_ELEVATED) {
            adminTab.setContent(new AdministrationPane());
            dashboard.getTabs().add(adminTab);
            adminTab.setClosable(false);
        }

        signOut.setMaxWidth(Double.MAX_VALUE);
        signOut.setMinHeight(40);

        signOut.setOnAction(e -> {
            ApplicationSession.clear();
            ps.close();
            new TemperatureClient().start(new Stage());
        });

        ps.setOnCloseRequest(e -> {
            ApplicationSession.clear();
            ps.close();
            new TemperatureClient().start(new Stage());
        });

        pane.addColumn(0, header, dashboard, signOut);

        ps.setScene(new Scene(pane));
        ps.setTitle(ApplicationSession.accessLevel == AccessLevel.ACCESS_ELEVATED ? "Administrator Dashboard" : "Dashboard");
        ps.show();
    }
}
