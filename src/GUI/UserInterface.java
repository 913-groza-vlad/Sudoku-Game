package GUI;

import Model.Coordinates;
import Model.Enums.GameState;
import Model.SudokuBoard;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.HashMap;

public class UserInterface implements IUserInterface.View, EventHandler<KeyEvent> {
    private Stage primaryStage;
    private Group root;
    private HashMap<Coordinates, SudokuTextField> boardFields;
    private IUserInterface.EventListener listener;

    private static double HEIGHT = 750;
    private static double WIDTH = 680;
    private static double BOARD_PADDING = 50;
    private static double BOARD_XY = 572;
    private static Color BACKGROUND= Color.rgb(4, 4, 52);
    private static Color BOARD_BACKGROUND = Color.rgb(236, 232, 232);

    public UserInterface(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.root = new Group();
        boardFields = new HashMap<>();
        populateUI();
    }

    private void populateUI() {
        this.designBackground(root);
        this.designTitle(root);
        this.designGameBoard(root);
        this.designTextFields(root);
        this.drawGridLines(root);
        primaryStage.show();
    }

    private void drawGridLines(Group root) {
        int index = 0;
        while (index < 8) {
            int thick;
            if (index == 2 || index == 5)
                thick = 3;
            else
                thick = 2;

            Rectangle verticalLine = getLine(114 + 64 * index, BOARD_PADDING, BOARD_XY, thick);
            Rectangle horizontalLine = getLine(BOARD_PADDING, 114 + 64 * index, thick, BOARD_XY);

            root.getChildren().addAll(verticalLine, horizontalLine);
            index++;
        }
    }

    private Rectangle getLine(double x, double y, double height, double width) {
        Rectangle line = new Rectangle();
        line.setX(x);
        line.setY(y);
        line.setHeight(height);
        line.setWidth(width);
        line.setFill(Color.BLACK);

        return line;
    }

    private void designTextFields(Group root) {
        int xO = 50;
        int yO = 50;
        int delta = 64;

        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++) {
                int x = xO + i * delta;
                int y = yO + j * delta;

                SudokuTextField field = new SudokuTextField(i, j);
                styleField(field, x, y);
                field.setOnKeyPressed(this);
                boardFields.put(new Coordinates(i, j), field);

                root.getChildren().add(field);
            }
    }

    private void styleField(SudokuTextField field, double x, double y) {
        Font numberFont = new Font(32);
        field.setFont(numberFont);
        field.setAlignment(Pos.CENTER);
        field.setLayoutX(x);
        field.setLayoutY(y);
        field.setPrefHeight(64);
        field.setPrefWidth(64);
        field.setBackground(Background.EMPTY);
    }

    private void designGameBoard(Group root) {
        Rectangle boardBackground = new Rectangle();
        boardBackground.setX(BOARD_PADDING);
        boardBackground.setY(BOARD_PADDING);
        boardBackground.setWidth(BOARD_XY);
        boardBackground.setHeight(BOARD_XY);

        boardBackground.setFill(BOARD_BACKGROUND);

        root.getChildren().addAll(boardBackground);
    }

    private void designTitle(Group root) {
        Text title = new Text(240, 700, "Sudoku");
        title.setFill(Color.WHITE);
        Font titleFont = new Font(44);
        title.setFont(titleFont);
        root.getChildren().add(title);
    }

    private void designBackground(Group root) {
        Scene scene = new Scene(root, HEIGHT, WIDTH);
        scene.setFill(BACKGROUND);
        primaryStage.setScene(scene);

    }

    @Override
    public void setListener(IUserInterface.EventListener listener) {
        this.listener = listener;
    }

    @Override
    public void updateCell(int x, int y, int value) {
        SudokuTextField field = boardFields.get(new Coordinates(x, y));
        String cellValue = Integer.toString(value);
        if (cellValue.equals("0"))
            cellValue = "";
        field.textProperty().setValue(cellValue);
    }

    @Override
    public void updateBoard(SudokuBoard game) {
        for (int xi = 0; xi < 9; xi++)
            for (int yi = 0; yi < 9; yi++) {
                TextField field = boardFields.get(new Coordinates(xi, yi));
                String cellValue = Integer.toString(game.copyGameBoard()[xi][yi]);
                if (cellValue.equals("0"))
                    cellValue = "";
                field.setText(cellValue);

                if (game.getGameState() == GameState.NEW) {
                    if (cellValue.equals("")) {
                        field.setStyle("-fx-opacity: 1");
                        field.setDisable(false);
                    }
                    else {
                        field.setStyle("-fx-opacity: 0.75");
                        field.setDisable(true);
                    }
                }
            }

    }

    @Override
    public void showDialog(String message) {
        Alert dialog = new Alert(Alert.AlertType.CONFIRMATION, message, ButtonType.OK);
        dialog.showAndWait();
        if (dialog.getResult() == ButtonType.OK)
            listener.onCellClick();
    }

    @Override
    public void showError(String message) {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        errorAlert.showAndWait();
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        if (keyEvent.getEventType() == KeyEvent.KEY_PRESSED) {
            if (keyEvent.getText().matches("[0-9]")) {
                int value = Integer.parseInt(keyEvent.getText());
                handleInputValue(value, keyEvent.getSource());
            } else if (keyEvent.getCode() == KeyCode.BACK_SPACE)
                handleInputValue(0, keyEvent.getSource());
            else
                ((TextField) keyEvent.getSource()).setText("");
        }
        keyEvent.consume();
    }

    private void handleInputValue(int value, Object source) {
        SudokuTextField gameBoard = (SudokuTextField) source;
        listener.SudokuInput(gameBoard.getX(), gameBoard.getY(), value);
    }
}
