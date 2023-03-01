package ComputationLogic;

public class SudokuUtilities {
    public static void copyGridValues(int[][] oldGrid, int[][] newGrid) {
        for (int i = 0; i < 9; i++)
            System.arraycopy(oldGrid[i], 0, newGrid[i], 0, 9);
    }

    public static int[][] copyToNewGrid(int[][] oldGrid) {
        int[][] newGrid = new int[9][9];
        for (int i = 0; i < 9; i++)
            System.arraycopy(oldGrid[i], 0, newGrid[i], 0, 9);

        return newGrid;
    }
}
