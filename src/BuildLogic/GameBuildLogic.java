package BuildLogic;

import ComputationLogic.GameLogic;
import GUI.IUserInterface;
import GUI.LogicController;
import Model.IStorage;
import Model.LocalStorage;
import Model.SudokuBoard;

import java.io.IOException;

public class GameBuildLogic {
    public static void build(IUserInterface.View ui) throws IOException {
        SudokuBoard initialState;
        IStorage storage = new LocalStorage();
        try {
            initialState = storage.getGameBoard();
        }
        catch (IOException e) {
            initialState = GameLogic.createNewGame();
            storage.updateGameBoard(initialState);
        }

        IUserInterface.EventListener uiLogic = new LogicController(storage, ui);
        ui.setListener(uiLogic);
        ui.updateBoard(initialState);
    }
}
