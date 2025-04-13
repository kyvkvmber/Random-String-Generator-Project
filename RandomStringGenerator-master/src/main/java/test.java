import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.example.GenerateRandomStringScene;
import org.example.GenerateRandomStringSceneMethods;

public class test extends Application {

    @Override
    public void start(Stage stage) {
        GenerateRandomStringSceneMethods stringGeneratorService = new GenerateRandomStringSceneMethods();
        GenerateRandomStringScene stringGeneratorView = new GenerateRandomStringScene(stringGeneratorService);
        Scene scene = new Scene(stringGeneratorView.getRoot(), 400, 400);
        stage.setTitle("Random Generated String");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}