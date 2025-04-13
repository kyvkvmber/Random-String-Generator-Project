package org.example;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import javax.swing.*;

public class GenerateRandomStringScene {

    public GenerateRandomStringScene(){}
    public Parent getRoot (){

    TextField inputStringLengthField = StringLengthInputField();
    Button generateStringButton = GenerateStringButton();
    CheckBox optionOne = OptionOne();

    CheckBox optionTwo = OptionTwo();
    CheckBox optionThree = OptionThree();

    HBox hbox = SearchAreaHBox(inputStringLengthField, generateStringButton);
    GridPane gridPane = ChecklistsGrid(optionOne, optionTwo, optionThree);

    TextArea resultText = GeneratedStringResultText();
    VBox root = RootElement(hbox, gridPane, resultText);
    return root;
    }

    private VBox RootElement(HBox SearchAreaHBox, GridPane ChecklistsGrid, TextArea GeneratedStringResultText) {
        VBox root = new VBox(20);

        root.setPadding(new Insets(10));

        root.getChildren().add(SearchAreaHBox);
        root.getChildren().add(ChecklistsGrid);
        root.getChildren().add(GeneratedStringResultText);
        return root;
    }
    private HBox SearchAreaHBox(TextField inputStringLengthField, Button generateStringButton) {
        HBox hbox = new HBox(20);
        hbox.getChildren().add(inputStringLengthField);
        HBox.setHgrow(inputStringLengthField, Priority.ALWAYS);
        hbox.getChildren().add(generateStringButton);
        return hbox;
    }
    private TextField StringLengthInputField(){
        TextField randomStringLength = new TextField();
        randomStringLength.setMinWidth(10);
        randomStringLength.setMaxWidth(50);
        randomStringLength.setTextFormatter(new javafx.scene.control.TextFormatter<>(change -> {
            // Allow only digits and limit length to 10
            if (change.getControlNewText().matches("[0-9]*") && change.getControlNewText().length() <= 5) {
                return change; // Allow the change if it's numeric and the length is <= 10
            }
            return null; // Reject the change if it's not numeric or exceeds length
        }));
        return randomStringLength;
    }
    private Button GenerateStringButton(){
        return new Button("Generate");
    }

    private CheckBox OptionOne(){
        return new CheckBox("Alphabetical characters");
    }

    private CheckBox OptionTwo(){
        return new CheckBox("Numerical characters");
    }
    private CheckBox OptionThree(){
        return new CheckBox("Special characters");
    }

    private GridPane ChecklistsGrid(CheckBox optionOne, CheckBox optionTwo, CheckBox optionThree) {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(5);
        gridPane.add(optionOne, 0, 0);
        gridPane.add(optionTwo, 0, 1);
        gridPane.add(optionThree, 1, 0);

        return gridPane;
    }

    private TextArea GeneratedStringResultText (){
        TextArea resultText = new TextArea();
        resultText.setMinWidth(0);
        resultText.setMaxWidth(400);
        resultText.setWrapText(true);

        return resultText;
    }
}
