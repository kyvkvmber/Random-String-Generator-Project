import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class test extends Application {

    @Override
    public void start(Stage stage) {
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

        Button generateRandomString = new Button("Generate");
        CheckBox optionOne = new CheckBox("Alphabetical characters");
        CheckBox optionTwo = new CheckBox("Numerical characters");
        CheckBox optionThree = new CheckBox("Special characters");

        HBox hbox = new HBox(20);
        hbox.getChildren().add(randomStringLength);
        HBox.setHgrow(randomStringLength, Priority.ALWAYS);
        hbox.getChildren().add(generateRandomString);

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(5);
        gridPane.add(optionOne, 0, 0);
        gridPane.add(optionTwo, 0, 1);
        gridPane.add(optionThree, 1,0);

        VBox root = new VBox(20);
        root.setPadding(new Insets(10));

        root.getChildren().add(hbox);
        root.getChildren().add(gridPane);

        Scene scene = new Scene(root, 400,400);

        stage.setTitle("Random Generated String");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}