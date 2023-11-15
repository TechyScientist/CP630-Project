package net.johnnyconsole.cp630.project.client;

import at.favre.lib.crypto.bcrypt.BCrypt;
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
import java.sql.ResultSet;
import net.johnnyconsole.cp630.project.client.dialog.ConfirmAppCloseDialog;
import net.johnnyconsole.cp630.project.client.dialog.SignInErrorDialog;
import net.johnnyconsole.cp630.project.client.util.ApplicationSession;
import net.johnnyconsole.cp630.project.client.util.Database;
import net.johnnyconsole.cp630.project.client.window.ClientDashboard;
import net.johnnyconsole.cp630.project.client.window.SetupWindow;

import java.sql.Connection;
import java.sql.Statement;

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

        signin.setOnAction(e -> {
            if(signIn(username.getText().toLowerCase(), password.getText())) {
                ps.close();
                new ClientDashboard().start(new Stage());
            } else {
                new SignInErrorDialog().start(new Stage());
            }
        });

        setup.setOnAction(e -> {
            ps.close();
            new SetupWindow().start(new Stage());
        });

        ps.setOnCloseRequest(e -> {
            ps.close();
            new ConfirmAppCloseDialog().start(new Stage());
        });

        ps.setScene(new Scene(pane));
        ps.setTitle("Client Sign In");
        ps.show();
    }

    private boolean signIn(String username, String password) {
        try(Connection conn = Database.connect()) {
            if(username == null || username.isEmpty() || password == null || password.isEmpty()) return false;
            String sql = "SELECT * FROM cons3250_project_user WHERE username = '" + username + "';";
            Statement stmt = conn.createStatement();
            ResultSet set = stmt.executeQuery(sql);
            if(set.next()) {
                String hash = set.getString("password");
                if(BCrypt.verifyer().verify(password.getBytes(), hash.getBytes()).verified) {
                    System.out.println("Access Granted: User(Username: " + username + ", Name: " +
                            set.getString("name") + ", Access Level: "
                            + (set.getInt("accessLevel") == 0 ? " Administrator" : "Standard") + ")");
                    ApplicationSession.username = username;
                    ApplicationSession.name = set.getString("name");
                    ApplicationSession.accessLevel = set.getInt("accessLevel");
                    return true;
                }
                else return false;
            }
            return false;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + " in TemperatureClient.signIn: " + e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
