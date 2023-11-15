package net.johnnyconsole.cp630.project.client.window;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.Label;
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
        header.setFont(Font.font(20));
        GridPane.setHalignment(header, HPos.CENTER);

        TabPane dashboard = new TabPane();
        Tab predictionTab = new Tab("Prediction"),
                adminTab = new Tab("Administrator");
        predictionTab.setContent(new PredictionPane());
        predictionTab.setClosable(false);
        adminTab.setClosable(false);

        dashboard.getTabs().add(predictionTab);
        if(ApplicationSession.accessLevel == AccessLevel.ACCESS_ELEVATED) {
            dashboard.getTabs().add(adminTab);
        }

        pane.addColumn(0, header, dashboard);

        ps.setScene(new Scene(pane));
        ps.setTitle(ApplicationSession.accessLevel == AccessLevel.ACCESS_ELEVATED ? "Administrator Dashboard" : "Dashboard");
        ps.show();
    }
}
