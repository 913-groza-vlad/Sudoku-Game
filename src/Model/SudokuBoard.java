package Model;

import Model.Enums.GameState;

import java.io.Serializable;

public class SudokuBoard implements Serializable {
    private final GameState gameState;
    private final int[][] gameBoard;

    public static final int LEN = 9;

    public SudokuBoard(GameState gameState, int[][] gameBoard) {
        this.gameBoard = gameBoard;
        this.gameState = gameState;
    }

    public GameState getGameState() {
        return gameState;
    }

    public int[][] copyGameBoard() {
        return GameUtilities.copyBoard(gameBoard);
    }
}
