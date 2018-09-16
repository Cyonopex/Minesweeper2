package ui;

import logic.GameBoard;

public class UserInterface {
    public static void main(String[] args) {
        GameBoard board = new GameBoard(10,20);
        board.print(false);
        board.print(true);
    }
}
