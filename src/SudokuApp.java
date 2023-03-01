import GUI.IUserInterface;
import GUI.UserInterface;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import BuildLogic.GameBuildLogic;

public class SudokuApp extends Application {
    private IUserInterface.View ui;
    @Override
    public void start(Stage primaryStage) {
        ui = new UserInterface(primaryStage);
        try {
            GameBuildLogic.build(ui);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
