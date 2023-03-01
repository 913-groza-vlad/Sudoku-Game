package GUI;

import ComputationLogic.GameLogic;
import Model.Enums.GameState;
import Model.IStorage;
import Model.SudokuBoard;


import java.io.IOException;

public class LogicController implements IUserInterface.EventListener {
    private IStorage storage;
    private IUserInterface.View view;

    public LogicController(IStorage storage, IUserInterface.View view) {
        this.storage = storage;
        this.view = view;
    }

    @Override
    public void SudokuInput(int x, int y, int value) {
        try {
            SudokuBoard gameBoard = storage.getGameBoard();
            int[][] newBoard = gameBoard.copyGameBoard();
            newBoard[x][y] = value;

            gameBoard = new SudokuBoard(GameLogic.isCompleted(newBoard), newBoard);
            storage.updateGameBoard(gameBoard);
            view.updateCell(x, y, value);

            if (gameBoard.getGameState() == GameState.COMPLETE) {
                view.showDialog("The game is over! You have won!");
            }
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
            view.showError("An error occurred!");
        }
    }

    @Override
    public void onCellClick() {
        try {
            storage.updateGameBoard(GameLogic.createNewGame());
            view.updateBoard(storage.getGameBoard());
        }
        catch (IOException e) {
            e.printStackTrace();
            view.showError("An error occurred!");
        }
    }
}
