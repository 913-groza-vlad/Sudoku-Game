package ComputationLogic;

import Model.Coordinates;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GenerateGame {
    public static int[][] newGameGrid() {
        return unsolveGame(getSolvedGame());
    }

    public static int[][] getSolvedGame() {
        Random random = new Random(System.currentTimeMillis());
        int[][] newGrid = new int[9][9];

        for (int val = 1; val <= 9; val++) {
            int alloc = 0, interrupt = 0, attempts = 0;
            List<Coordinates> allocations = new ArrayList<>();
            while (alloc < 9) {
                if (interrupt > 200) {
                    allocations.forEach(coordinates -> {
                        newGrid[coordinates.getX()][coordinates.getY()] = 0;
                    });
                    interrupt = 0;
                    alloc = 0;
                    allocations.clear();
                    attempts++;
                    if (attempts > 500) {
                        clearGrid(newGrid);
                        attempts = 0;
                        val = 1;
                    }
                }
                int coordX = random.nextInt(9);
                int coordY = random.nextInt(9);
                if (newGrid[coordX][coordY] == 0) {
                    newGrid[coordX][coordY] = val;
                    if (GameLogic.isValid(newGrid)) {
                        newGrid[coordX][coordY] = 0;
                        interrupt++;
                    } else {
                        allocations.add(new Coordinates(coordX, coordY));
                        alloc++;
                    }
                }
            }
        }

        return newGrid;
    }

    private static void clearGrid(int[][] grid) {
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                grid[i][j] = 0;
    }

    private static int[][] unsolveGame(int[][] solvedGame) {
        Random random = new Random(System.currentTimeMillis());
        boolean solvable = false;
        int[][] solvableGrid = new int[40][40];

        while (!solvable) {
            SudokuUtilities.copyGridValues(solvedGame, solvableGrid);
            int index = 0;
            while (index < 40) {
                int coordX = random.nextInt(9);
                int coordY = random.nextInt(9);
                if (solvableGrid[coordX][coordY] != 0) {
                    solvableGrid[coordX][coordY] = 0;
                    index++;
                }
            }

            int[][] toSolveGrid = new int[9][9];
            SudokuUtilities.copyGridValues(solvableGrid, toSolveGrid);

            solvable = SudokuSolver.isSolvable(toSolveGrid);
        }

        return solvableGrid;
    }
}
