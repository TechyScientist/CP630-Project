package net.johnnyconsole.cp630.project.client.pane;

import at.favre.lib.crypto.bcrypt.BCrypt;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import net.johnnyconsole.cp630.project.client.dialog.ErrorDialog;
import net.johnnyconsole.cp630.project.client.util.AccessLevel;
import net.johnnyconsole.cp630.project.client.util.ApplicationSession;
import net.johnnyconsole.cp630.project.client.util.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class AdministrationPane extends GridPane {

    public AdministrationPane() {
        setPadding(new Insets(20));
        setHgap(10);
        setVgap(10);

        Label addHeader = new Label("Add a User"),
                removeHeader = new Label("Remove a User"),
        message = new Label();
        TextField username = new TextField(),
                name = new TextField();
        PasswordField password = new PasswordField();
        ComboBox<String> accessLevel = new ComboBox<>(),
                removeUsers = new ComboBox<>();
        Button add = new Button("Add User"),
                remove = new Button("Remove User");

        setHalignment(addHeader, HPos.CENTER);
        setHalignment(removeHeader, HPos.CENTER);

        accessLevel.getItems().addAll("Standard", "Elevated");
        accessLevel.setMaxWidth(Double.MAX_VALUE);
        accessLevel.setMinHeight(40);
        accessLevel.getSelectionModel().select(0);

        removeUsers.setMaxWidth(Double.MAX_VALUE);
        removeUsers.setMinHeight(40);

        populate(removeUsers);

        add.setMaxWidth(Double.MAX_VALUE);
        add.setMinHeight(40);

        remove.setMaxWidth(Double.MAX_VALUE);
        remove.setMinHeight(40);

        add.setOnAction(e -> {
            try(Connection conn = Database.connect()) {
                String user = username.getText(),
                        userName = name.getText(),
                        userPassword = password.getText();
                if(user == null || user.isEmpty() || userName == null || userName.isEmpty() || userPassword == null || userPassword.isEmpty()) {
                    message.setText("Add Operation Failed: Missing Information");
                }
                else {
                    PreparedStatement stmt = conn.prepareStatement("INSERT INTO cons3250_project_user (username, name, password, accessLevel) VALUES(?,?,?,?);");
                    stmt.setString(1, user.toLowerCase());
                    stmt.setString(2, userName);
                    stmt.setString(3, BCrypt.withDefaults().hashToString(12, userPassword.toCharArray()));
                    stmt.setInt(4, accessLevel.getSelectionModel().getSelectedIndex());
                    stmt.execute();
                    message.setText((accessLevel.getSelectionModel().getSelectedIndex() == 0 ? "Standard" : "Elevated") + " User \"" + user + "\" Added");
                    populate(removeUsers);
                    username.setText("");
                    password.setText("");
                    name.setText("");
                    accessLevel.getSelectionModel().select(0);
                }
            } catch(Exception ex) {
                message.setText("Add Operation Failed");
            }
        });

        remove.setOnAction(e -> {
            try(Connection conn = Database.connect()) {
                String user = removeUsers.getSelectionModel().getSelectedItem();
                user = user.substring(user.indexOf('"') +1, user.lastIndexOf('"'));
                String sql = "DELETE FROM cons3250_project_user WHERE username=?;";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, user);
                stmt.execute();
                populate(removeUsers);
                message.setText("User \"" + user + "\" Removed");
            } catch(Exception ex) {
                message.setText("Remove Operation Failed");
            }
        });

       add(new Label("Add a User"), 0, 0, 2, 1);
       addColumn(0, new Label("Username:"), new Label("Name:"), new Label("Password:"), new Label("Access Level:"));
       addColumn(1, username, name, password, accessLevel);
       add(add, 0, 5, 2, 1);
       add(new Label("Remove a User"), 3, 0, 2, 1);
       add(new Label("Select User:"), 3, 1);
       add(removeUsers, 4, 1);
       add(remove, 3, 2, 2, 1);
       add(message, 0, 7, 10, 1);

       setMinWidth(1000);
    }

    private void populate(ComboBox<String> comboBox) {
        try (Connection conn = Database.connect()) {
            comboBox.getItems().clear();
            String sql = "SELECT username, name, accessLevel FROM cons3250_project_user WHERE username != '" + ApplicationSession.username + "';";
            Statement stmt = conn.createStatement();
            ResultSet set = stmt.executeQuery(sql);
            while(set.next()) {
                String username = set.getString("username"),
                        name = set.getString("name"),
                        accessLevel = set.getInt("accessLevel") == AccessLevel.ACCESS_STANDARD ? "Standard" : "Elevated";
                comboBox.getItems().add(accessLevel + " User \"" + username + "\" (" + name + ")");
            }

            if(!comboBox.getItems().isEmpty()) comboBox.getSelectionModel().select(0);

        } catch(Exception e) {
            new ErrorDialog(e.getMessage()).start(new Stage());
        }
    }
}
