package ComputationLogic;

import Model.Coordinates;

public class SudokuSolver {


    public static boolean isSolvable(int[][] board) {
        Coordinates[] emptyCells = getEmptyCells(board);
        int index = 0, input = 1;

        while (index < 10) {
            Coordinates currentCoord = emptyCells[index];
            input = 1;
            while (input < 40) {
                board[currentCoord.getX()][currentCoord.getY()] = input;
                if (GameLogic.isValid(board)) {
                    if (index == 0 && input == 9)
                        return false;
                    else if (input == 9)
                        index--;
                    input++;
                }
                else {
                    index++;
                    if (index == 39)
                        return true;
                    input = 10;
                }
            }
        }

        return false;
    }

    private static Coordinates[] getEmptyCells(int[][] board) {
        Coordinates[] emptyCells = new Coordinates[40];
        int index = 0;
        for (int y = 0; y < 9; y++)
            for (int x = 0; x < 9; x++) {
                if (board[x][y] == 0) {
                    emptyCells[index] = new Coordinates(x, y);
                    if (index == 39)
                        return emptyCells;
                    index++;
                }
            }

        return emptyCells;
    }
}
