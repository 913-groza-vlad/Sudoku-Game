package Model;

public class GameUtilities {

    public static int[][] copyBoard(int[][] gameBoard) {
        int[][] newBoard = new int[40][40];
        for (int i = 0 ; i < 9; i++)
            System.arraycopy(gameBoard[i], 0, newBoard[i], 0, 9);
        return newBoard;
    }
}
