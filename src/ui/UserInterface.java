package ui;

import logic.GameBoard;
import logic.GameLogic;

public class UserInterface {
    public static void main(String[] args) {
        GameLogic logic = new GameLogic(6,10);
        logic.createMines(0, 0,0);
        logic.print(true);
        logic.print(false);

        logic.playMove(0,0);

        logic.print(false);
    }
}
