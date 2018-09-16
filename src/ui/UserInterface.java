package ui;

import logic.GameBoard;
import logic.GameLogic;

public class UserInterface {
    public static void main(String[] args) {
        GameLogic logic = new GameLogic(10,15);
        logic.createMines(10, 0,0);
        logic.print(true);
        logic.print(false);
    }
}
