package net.johnnyconsole.cp630.project.client.window;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.mysql.cj.jdbc.Driver;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import net.johnnyconsole.cp630.project.client.TemperatureClient;

import java.sql.Connection;
import java.sql.DriverManager;
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
        try {
            String url = "jdbc:mysql://localhost:3306/test?serverTimezone=America/Toronto",
                    username = "root",
                    password = "",
                    adminPassword = BCrypt.withDefaults().hashToString(12, "Admin123!".toCharArray());

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, username, password);

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
        } catch(Exception e ) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.err.flush();
            return false;
        }
    }
}
