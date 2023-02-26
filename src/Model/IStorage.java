package Model;

import java.io.IOException;

public interface IStorage {
    void updateGameBoard(SudokuBoard board) throws IOException;
    SudokuBoard getGameBoard() throws IOException;
}
