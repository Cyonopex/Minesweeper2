package logic;

public class GameBoard {

    /* One long integer array for gameboard
       Mapping for numRow/column,
       numRow = n/rowLength
       col = n%rowlength

       0 - 8 refers to number of mines
       9 means mine exists in this column

       for maskedBoard, false = still covered
     */
    private int[] gameBoard;
    private boolean[] maskedBoard;
    private int numRow, numCol;

    /**
     * Constructor for GameBoard class
     * @param row Num of rows of the gameboard
     * @param col Num of columns for gameboard
     */
    public GameBoard(int row, int col) {
        numRow = row;
        numCol = col;
        gameBoard = new int[row * col];
        maskedBoard = new boolean[row * col];
    }

    public int getCell(int row, int col) {
        return gameBoard[row*numRow + col];
    }

    public int getCell(int index) {
        return gameBoard[index];
    }

    public void setCell(int row, int col, int value) {
        gameBoard[row*numRow + col] = value;
    }

    public void setCell(int index, int value) {
        gameBoard[index] = value;
    }

    public int getNumCells() {
        return numRow * numCol;
    }

    public boolean isUncovered(int row, int col) {
        return maskedBoard[row*numRow + col];
    }

    public void setUncovered(int row, int col) {
        maskedBoard[row*numRow + col] = true;
    }

    public int getNumRow() {
        return numRow;
    }

    public int getNumCol() {
        return numCol;
    }

    public void print(boolean showHidden) {

        for (int cell=0; cell < gameBoard.length; cell++) {
            if (showHidden || maskedBoard[cell]) {
                System.out.print(gameBoard[cell] + " ");
            } else {
                System.out.print("- ");
            }

            if (cell%numCol == numCol-1) {
                System.out.println();
            }
        }
    }

}
