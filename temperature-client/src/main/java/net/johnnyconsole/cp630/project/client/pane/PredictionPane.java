package net.johnnyconsole.cp630.project.client.pane;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;

import java.util.ArrayList;

public class PredictionPane extends GridPane {


    public PredictionPane() {
        setPadding(new Insets(20));
        setHgap(10);
        setVgap(10);

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

        addRow(0, new Label("Year:"), years);
        addRow(1, new Label("Month:"), months);
        addRow(2, new Label("Day:"), days);
        add(predict, 0, 3, 2, 1);
        add(prediction, 0, 5, 2, 1);

        setMinWidth(500);
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
}
