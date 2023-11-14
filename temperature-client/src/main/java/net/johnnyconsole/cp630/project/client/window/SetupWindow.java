package net.johnnyconsole.cp630.project.client.window;

import at.favre.lib.crypto.bcrypt.BCrypt;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import net.johnnyconsole.cp630.project.client.TemperatureClient;
import net.johnnyconsole.cp630.project.client.util.Database;
import weka.classifiers.functions.LinearRegression;
import weka.core.Instances;
import weka.core.SelectedTag;
import weka.core.converters.ConverterUtils;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

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

        model.setDisable(true);
        genRegression.setDisable(true);

        pane.add(createDB,0,0,2,1);

        pane.addRow(2, new Label("Regression Model Name:"), model);
        pane.add(genRegression, 0, 3, 2, 1);

        pane.add(back, 0, 5, 2, 1);

        createDB.setOnAction(e -> {
            if(createDatabase()) {
                createDB.setDisable(true);
                createDB.setText("Database Created");
                model.setDisable(false);
                genRegression.setDisable(false);
            }
        });

        genRegression.setOnAction(e -> {
            if(generateRegression(model.getText())) {
                genRegression.setDisable(true);
                genRegression.setText("Regression Model Created");
            }
        });


        back.setOnAction(e -> {
            ps.close();
            new TemperatureClient().start(new Stage());
        });

        ps.setOnCloseRequest(e -> {
            ps.close();
            new TemperatureClient().start(new Stage());
        });

        ps.setScene(new Scene(pane));
        ps.setTitle("Setup");
        ps.show();
    }

    private boolean createDatabase() {
        try(Connection conn = Database.connect()) {
            String adminPassword = BCrypt.withDefaults().hashToString(12, "Admin123!".toCharArray());

            String sql = "CREATE TABLE IF NOT EXISTS cons3250_project_model (" +
                    "name VARCHAR(100) PRIMARY KEY NOT NULL," +
                    "class TINYTEXT NOT NULL," +
                    "object BLOB NOT NULL" +
                    ");";

            Statement stmt = conn.createStatement();
            stmt.execute(sql);

            sql = "CREATE TABLE IF NOT EXISTS cons3250_project_user (" +
                    "username VARCHAR(100) PRIMARY KEY NOT NULL," +
                    "name TINYTEXT NOT NULL," +
                    "password LONGTEXT NOT NULL," +
                    "accessLevel INT NOT NULL DEFAULT 0" +
                    ");";
            stmt.execute(sql);

            sql = "INSERT IGNORE INTO cons3250_project_user (username, name, password) VALUES('admin', 'Administrator','" + adminPassword + "');";
            stmt.execute(sql);

            return true;
        } catch(SQLException e) {
            System.err.println("SQLException in SetupWindow.createDatabase: " + e.getMessage());
            System.err.flush();
            return false;
        }
    }

    private boolean generateRegression(String modelName) {
        try(Connection conn = Database.connect()) {
            if (modelName == null || modelName.isEmpty()) return false;
            FileChooser chooser = new FileChooser();
            chooser.setTitle("Select Dataset File");
            chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("ARFF Dataset Files (*.arff)", "*.arff"));
            File dataset = chooser.showOpenDialog(new Stage());

            Instances dataInstances = ConverterUtils.DataSource.read(dataset.getPath());
            dataInstances.setClassIndex(dataInstances.numAttributes() - 1);

            LinearRegression regression = new LinearRegression();
            SelectedTag tag = new SelectedTag(LinearRegression.SELECTION_NONE, LinearRegression.TAGS_SELECTION);
            regression.setAttributeSelectionMethod(tag);
            regression.buildClassifier(dataInstances);

            String sql = "INSERT IGNORE INTO cons3250_project_model (name, class, object) VALUES (?,?,?);";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, modelName);
            stmt.setString(2, regression.getClass().getName());
            stmt.setObject(3, regression);
            stmt.execute();
            return true;
        } catch(Exception e) {
            return false;
        }
    }
}
