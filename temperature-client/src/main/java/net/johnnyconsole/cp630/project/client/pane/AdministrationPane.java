package net.johnnyconsole.cp630.project.client.pane;

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
import java.sql.ResultSet;
import java.sql.Statement;

public class AdministrationPane extends GridPane {

    public AdministrationPane() {
        setPadding(new Insets(20));
        setHgap(10);
        setVgap(10);

        Label addHeader = new Label("Add a User"),
                removeHeader = new Label("Remove a User");
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

       add(new Label("Add a User"), 0, 0, 2, 1);
       addColumn(0, new Label("Username:"), new Label("Name:"), new Label("Password:"), new Label("Access Level:"));
       addColumn(1, username, name, password, accessLevel);
       add(add, 0, 5, 2, 1);
       add(new Label("Remove a User"), 3, 0, 2, 1);
       add(new Label("Select User:"), 3, 1);
       add(removeUsers, 4, 1);
       add(remove, 3, 2, 2, 1);

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
