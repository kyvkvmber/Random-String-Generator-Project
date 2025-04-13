package org.example;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import javax.swing.*;

public class GenerateRandomStringScene {
    private TextField inputStringLengthField;
    private TextArea resultText;
    private CheckBox includeAlphabeticalChars;
    private CheckBox includeNumericalChars;
    private CheckBox includeSpecialChars;
    private GenerateRandomStringSceneMethods stringGenerationService;

    public GenerateRandomStringScene(GenerateRandomStringSceneMethods stringGenerationService){
        this.stringGenerationService = new GenerateRandomStringSceneMethods();
    }
    public Parent getRoot (){
        inputStringLengthField = StringLengthInputField();
        Button generateStringButton = GenerateStringButton();

        includeAlphabeticalChars = OptionOne();

        includeNumericalChars = OptionTwo();
        includeSpecialChars = OptionThree();

        HBox hbox = SearchAreaHBox(inputStringLengthField, generateStringButton);
        GridPane gridPane = ChecklistsGrid(includeAlphabeticalChars, includeNumericalChars, includeSpecialChars);

        resultText = GeneratedStringResultText();
        VBox root = RootElement(hbox, gridPane, resultText);

        return root;
    }

    public TextField getInputStringLengthField(){
        return this.inputStringLengthField;
    }
    public TextArea getResultText(){
        return this.resultText;
    }
    public void setResultText(String value){
        resultText.setText(value);
    }
    public CheckBox getOptionOne() {
        return this.includeAlphabeticalChars;
    }

    public  CheckBox getOptionTwo(){
        return this.includeNumericalChars;
    }

    public CheckBox getOptionThree(){
        return this.includeSpecialChars;
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
        //
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
        Button generateStringButton =  new Button("Generate");
        generateStringButton.setOnAction(actionEvent -> {
            try {
                resultText.setText(stringGenerationService.GenerateRandomString(
                inputStringLengthField.getText(),
                includeAlphabeticalChars.isSelected(),
                includeNumericalChars.isSelected(),
                includeSpecialChars.isSelected()));
            }catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("An Error Occurred");
                alert.setHeaderText("Something went wrong");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        });
        return generateStringButton;
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
