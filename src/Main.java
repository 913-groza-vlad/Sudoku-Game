import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader listLoader = new FXMLLoader();
            listLoader.setLocation(getClass().getResource("GUI/BoardView.fxml"));
            GridPane root = (GridPane) listLoader.load();
            Scene scene = new Scene(root, 600, 500);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Sudoku");
            primaryStage.show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static void main(String[] args) {
        System.out.println("Hello world!");
        launch(args);
    }
}