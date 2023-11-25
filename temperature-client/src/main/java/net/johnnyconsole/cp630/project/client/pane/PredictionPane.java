package net.johnnyconsole.cp630.project.client.pane;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import net.johnnyconsole.cp630.project.client.util.Database;
import weka.classifiers.trees.REPTree;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instances;

import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;

public class PredictionPane extends GridPane {


    public PredictionPane() {
        setPadding(new Insets(20));
        setHgap(10);
        setVgap(10);

        TextField modelname = new TextField();
        ComboBox<Integer> years = new ComboBox<>(),
                days = new ComboBox<>();
        ComboBox<String> months = new ComboBox<>();
        Button predict = new Button("Predict");
        Label prediction = new Label();

        years.getItems().addAll(range(2000, 2030));
        years.getSelectionModel().select(0);
        years.setMaxWidth(Double.MAX_VALUE);
        years.setMinHeight(40);

        months.getItems().addAll("January", "February", "March",
                "April", "May", "June", "July", "August", "September",
                "October", "November", "December");
        months.getSelectionModel().select(0);
        months.setMaxWidth(Double.MAX_VALUE);
        months.setMinHeight(40);

        days.getItems().addAll(range(1, 31));
        days.getSelectionModel().select(0);
        days.setMaxWidth(Double.MAX_VALUE);
        days.setMinHeight(40);

        predict.setMaxWidth(Double.MAX_VALUE);
        predict.setMinHeight(40);

        years.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            days.getItems().setAll(range(1, daysInMonth(newValue, months.getSelectionModel().getSelectedIndex() + 1)));
            days.getSelectionModel().select(0);
        });

        months.getSelectionModel().selectedIndexProperty().addListener((options, oldValue, newValue) -> {
            days.getItems().setAll(range(1, daysInMonth(years.getSelectionModel().getSelectedItem(), newValue.intValue() + 1)));
            days.getSelectionModel().select(0);
        });

        predict.setOnAction(e -> {
            try (Connection conn = Database.connect()){
                if(modelname.getText() == null || modelname.getText().isEmpty()) prediction.setText("Model Missing");
                else {
                    String model = modelname.getText().toLowerCase();
                    String sql = "SELECT object from cons3250_project_model WHERE name=?";
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setString(1, model);
                    ResultSet set = stmt.executeQuery();
                    if(set.next()) {
                        REPTree tree = (REPTree) (new ObjectInputStream(set.getBinaryStream("object")).readObject());
                        ArrayList<Attribute> attributes = new ArrayList<>(
                                Arrays.asList(new Attribute("Year"),
                                        new Attribute("Month"),
                                        new Attribute("Day"),
                                        new Attribute("Mean Temperature")));

                        int year = years.getSelectionModel().getSelectedItem(),
                                month = months.getSelectionModel().getSelectedIndex() + 1,
                                day = days.getSelectionModel().getSelectedIndex() + 1;

                        Instances p = new Instances("TemperaturePredict", attributes, 0);
                        p.add(new DenseInstance(1, new double[]{year, month, day}));
                        p.setClassIndex(p.numAttributes() - 1);
                        double temp =  (int) (100 * tree.classifyInstance(p.firstInstance())) / 100.0;
                        prediction.setText("The predicted temperature on " + day + getMonthName(month) + year + " is: " + temp + " °C");
                    }
                }
            } catch(Exception ex) {
                prediction.setText("Prediction Failed");
            }
        });

        addRow(0, new Label("Model Name:"), modelname);
        addRow(1, new Label("Year:"), years);
        addRow(2, new Label("Month:"), months);
        addRow(3, new Label("Day:"), days);
        add(predict, 0, 4, 2, 1);
        add(prediction, 0, 6, 10, 1);

        setMinWidth(1000);
    }


    private ArrayList<Integer> range(int start, int end) {
        ArrayList<Integer> range = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            range.add(i);
        }
        return range;
    }

    private int daysInMonth(int year, int month) {
        if(isLeapYear(year) && month == 2) {
            return 29;
        }
        else {
            switch(month) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    return 31;
                case 2:
                    return 28;
                default:
                    return 30;
            }
        }
    }

    private boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
    }

    private String getMonthName(int month) {
        switch(month) {
            case 1: return " January ";
            case 2: return " February ";
            case 3: return " March ";
            case 4: return " April ";
            case 5: return " May ";
            case 6: return " June ";
            case 7: return " July ";
            case 8: return " August ";
            case 9: return " September ";
            case 10: return " October ";
            case 11: return " November ";
            default: return " December ";
        }
    }
}
