package Model;

import java.io.*;

public class LocalStorage implements IStorage {
    private static File gameData = new File(System.getProperty("user.home"), "data.txt");

    @Override
    public void updateGameBoard(SudokuBoard board) throws IOException {
        try {
            FileOutputStream outFile = new FileOutputStream(gameData);
            ObjectOutputStream outputStream = new ObjectOutputStream(outFile);
            outputStream.writeObject(board);
            outputStream.close();
        } catch (IOException e) {
            throw new IOException("Unable to access game data!");
        }
    }

    @Override
    public SudokuBoard getGameBoard() throws IOException {
        FileInputStream inFile = new FileInputStream(gameData);
        ObjectInputStream inputStream = new ObjectInputStream(inFile);
        SudokuBoard gameState;
        try {
            gameState = (SudokuBoard) inputStream.readObject();
            inputStream.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new IOException("File not found!");
        }
        return gameState;
    }
}
