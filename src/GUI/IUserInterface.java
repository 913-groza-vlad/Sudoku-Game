package GUI;

import Model.SudokuBoard;

public interface IUserInterface {
    interface EventListener {
        void SudokuInput(int x, int y, int value);
        void onCellClick();
    }

    interface View {
        void setListener(IUserInterface.EventListener listener);
        void updateCell(int x, int y, int value);
        void updateBoard(SudokuBoard game);
        void showDialog(String message);
        void showError(String message);
    }
}
