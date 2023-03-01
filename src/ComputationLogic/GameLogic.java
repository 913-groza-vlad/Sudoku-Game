package ComputationLogic;

import Model.Enums.GameState;
import Model.Enums.Rows;
import Model.SudokuBoard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class GameLogic {
    public static SudokuBoard createNewGame() {
        return new SudokuBoard(GameState.NEW, GenerateGame.newGameGrid());
    }

    public static GameState isCompleted(int[][] grid) {
        if (isValid(grid))
            return GameState.ONGOING;
        if (!boardIsFull(grid))
            return GameState.ONGOING;
        return GameState.COMPLETE;
    }

    public static boolean isValid(int[][] grid) {
        return !rowsAreValid(grid) || !columnsAreValid(grid) || !squaresAreValid(grid);
    }

    private static boolean rowsAreValid(int[][] grid) {
        for (int x = 0; x < 9; x++) {
            List<Integer> row = new ArrayList<>();
            for (int y = 0; y < 9; y++) {
                row.add(grid[x][y]);
            }
            if (valueRepeats(row))
                return false;
        }
        return true;
    }

    private static boolean columnsAreValid(int[][] grid) {
        for (int y = 0; y < 9; y++) {
            List<Integer> col = new ArrayList<>();
            for (int x = 0; x < 9; x++) {
                col.add(grid[x][y]);
            }
            if (valueRepeats(col))
                return false;
        }
        return true;
    }

    private static boolean squaresAreValid(int[][] grid) {
        return rowOfSquaresIsValid(Rows.TOP, grid) && rowOfSquaresIsValid(Rows.MIDDLE, grid) && rowOfSquaresIsValid(Rows.BOTTOM, grid);
    }

    private static boolean rowOfSquaresIsValid(Rows value, int[][] grid) {
        switch (value) {
            case TOP -> {
                if (squareIsValid(0, 0, grid) && squareIsValid(0, 3, grid) && squareIsValid(0, 6, grid))
                    return true;
                return false;
            }
            case MIDDLE -> {
                if (squareIsValid(3, 0, grid) && squareIsValid(3, 3, grid) && squareIsValid(3, 6, grid))
                    return true;
                return false;
            }
            case BOTTOM -> {
                if (squareIsValid(6, 0, grid) && squareIsValid(6, 3, grid) && squareIsValid(6, 6, grid))
                    return true;
                return false;
            }
            default -> {
                return false;
            }
        }
    }

    private static boolean squareIsValid(int xi, int yi, int[][] grid) {
        int xEnd = xi + 3;
        int yEnd = yi + 3;
        List<Integer> squares = new ArrayList<>();

        while (yi < yEnd) {
            while (xi < xEnd) {
                squares.add(grid[xi][yi]);
                xi++;
            }
            xi -= 3;
            yi++;
        }
        return !valueRepeats(squares);
    }

    private static boolean valueRepeats(List<Integer> squares) {
        for (int i = 1; i <= 9; i++)
            if (Collections.frequency(squares, i) > 1)
                return true;
        return false;
    }

    private static boolean boardIsFull(int[][] grid) {
        for (int x = 0; x < 9; x++)
            for (int y = 0; y < 9; y++) {
                if (grid[x][y] == 0)
                    return false;
            }
        return true;
    }

}
