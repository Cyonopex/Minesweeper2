package logic;

import java.util.ArrayList;
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

        //you do not want to give the user a mine on the first move
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
        //the first move can now be removed from list of mines
        mineIndex.remove(firstMove);

        for (int index : mineIndex) {

            board.setCell(index, 9);
            ArrayList<Integer> surrounding = getSurroundingSquares(index);
            for (int surroundingcells : surrounding) {
                int cell = board.getCell(surroundingcells);
                if (cell != 9)
                board.setCell(surroundingcells, board.getCell(surroundingcells) + 1);
            }

        }
    }

    private ArrayList<Integer> getSurroundingSquares(int index) {
        /*
          there are 8 surrounding squares
          0 1 2
          3 _ 4
          5 6 7

          if the mine is on any side, I remove corresponding ones
          eg. if on left, I remove 1, 4, 6
         */

        boolean[] beyondBorder = new boolean[8];
        if (index < board.getNumCol()) { //mine is on top
            beyondBorder[0] = true;
            beyondBorder[1] = true;
            beyondBorder[2] = true;
        }
        if (index >= board.getNumCells() - board.getNumCol()) { //mine is on bottom
            beyondBorder[5] = true;
            beyondBorder[6] = true;
            beyondBorder[7] = true;
        }
        if (index % board.getNumCol() == 0) { //mine is on left
            beyondBorder[0] = true;
            beyondBorder[3] = true;
            beyondBorder[5] = true;
        }
        if (index % board.getNumCol() == board.getNumCol()-1) { //mine is on right
            beyondBorder[2] = true;
            beyondBorder[4] = true;
            beyondBorder[7] = true;
        }

        //build all neighbours first
        int[] neighbours = new int[8];
        neighbours[0] = index - board.getNumCol() - 1;
        neighbours[1] = index - board.getNumCol();
        neighbours[2] = index - board.getNumCol() + 1;
        neighbours[3] = index - 1;
        neighbours[4] = index + 1;
        neighbours[5] = index + board.getNumCol() - 1;
        neighbours[6] = index + board.getNumCol();
        neighbours[7] = index + board.getNumCol() + 1;

        ArrayList<Integer> neighbouringCells = new ArrayList<>();
        for (int i=0; i < 8; i++) {
            if (!beyondBorder[i]) {
                neighbouringCells.add(neighbours[i]);
            }
        }
        return neighbouringCells;
    }



    /**
     * Plays a move at the row and column.
     * @param row Row where move is placed
     * @param col Column where move is placed
     * @return If true, game is over
     */
    public boolean playMove(int row, int col) {

        System.out.println("Playing row " + row + " and col " + col);
        board.setUncovered(row, col);
        if (row >= board.getNumRow() || col >= board.getNumCol()) {
            throw new IllegalArgumentException("Coordinates are out of bounds");
        }
        int cell = board.getCell(row, col);
        if (cell == 9) return true; //game over

        //play cell



        //play adjacent cells if it is a zero
        if (cell == 0){
            ArrayList<Integer> surroundingCells = getSurroundingSquares(row*board.getNumCol() + col);
            for (int surroundingCell : surroundingCells) {
                //only play if it has not been played yet
                if (!board.isUncovered(surroundingCell)) {
                    playMove(surroundingCell/board.getNumCol(), surroundingCell%board.getNumCol());
                }

            }
        }

        return false;
    }

    public void print(boolean show){
        board.print(show);
    }
}
