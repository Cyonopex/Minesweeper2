package logic;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class GameLogic {

    private GameBoard board;

    public GameLogic(int row, int col) {
        board = new GameBoard(row, col);
    }

    /**
     * Adds the number of mines to the board as specified
     * @param numMines Number of mines to add
     */
    public void createMines(int numMines, int firstMoveRow, int firstMoveCol) {
        if (numMines > board.getNumRow()*board.getNumCol()) {
            throw new IllegalArgumentException("Number of mines has exceeded gameboard size");
        }
        Set<Integer> mineIndex = new HashSet<>();

        //you do not want to give the user a bomb on the first move
        //it's pretty unpleasant
        //add it temporarily then remove afterwards
        int firstMove = firstMoveRow * board.getNumRow() + firstMoveCol;

        mineIndex.add(firstMove);
        Random r = new Random();
        int temp;

        for (int i=0; i < numMines; i++) {

            temp = r.nextInt(board.getNumCells());

            if (mineIndex.contains(temp)) {
                i--; //try again

            } else {
                mineIndex.add(temp);

            }
        }
        //the first move can now be removed from list of bombs
        mineIndex.remove(firstMove);

        for (int index : mineIndex) {
            board.setCell(index, 9);
        }
    }

    /**
     * Initialises all numbers on the board, if board has mines
     */
    private void initBoard() {

    }

    /**
     * Plays a move at the row and column.
     * @param row Row where move is placed
     * @param col Column where move is placed
     * @return If true, game is over
     */
    public boolean playMove(int row, int col) {
        return true;
    }

    public void print(boolean show){
        board.print(show);
    }
}
