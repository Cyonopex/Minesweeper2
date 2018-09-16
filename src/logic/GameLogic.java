package logic;

public class GameLogic {

    private GameBoard board;

    public GameLogic(int row, int col) {
        board = new GameBoard(row, col);
    }

    /**
     * Adds the number of mines to the board as specified
     * @param numMines Number of mines to add
     */
    private void createMines(int numMines) {
        if (numMines > board.getNumRow()*board.getNumCol()) {
            throw new IllegalArgumentException("Number of mines has exceeded gameboard size");
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


}
