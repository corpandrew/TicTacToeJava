package com.corpa;

/**
 * Board is a 2 dimensional char array, used for the Tic-Tac-Toe Board
 */
public class Board {


    private char[][] board;
    private int tilesTaken; // Tiles Taken are the amount of x's and o's on the board

    /**
     * Constructor initializes the board.
     */
    public Board() {
        board = new char[3][3];
        initializeBoard();
    }

    /**
     * Sets the 3x3 array to be all spaces.
     * Sets the amount of tiles taken to 0
     */
    public void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
        tilesTaken = 0;
    }

    /**
     * This creates the string of the board, for printing.
     *
     * @return the board string
     */
    @Override
    public String toString() {
        String boardString = "\n";
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (j != 2)
                    boardString += board[i][j] + "\t|\t";
                else
                    boardString += board[i][j];
            }
            boardString += "\n";
            for (int a = 0; a < 3; a++) {
                if (i != 2)
                    boardString += "------";
            }
            boardString += "\n";
        }
        return boardString;
    }

    /**
     * Sets the tile given to a letter.
     * Checks to see if the tile is empty before setting it.
     *
     * @param c letter used to mark the players wanted tile
     * @param row y position of the tile(in user input form, which is 1 greater than array index)
     * @param col x position of the tile(in user input form, which is 1 greater than array index)
     * @return true if tile was empty and put the tile there, else false.
     */
    public boolean setTile(char c, int row, int col) {
        if (emptyTile(row - 1, col - 1)) {
            board[row - 1][col - 1] = c;
            tilesTaken++;
            return true;
        }
        return false;
    }

    /**
     * To see if board is full
     *
     * @return true if board is full
     */
    public boolean isFull() {
        return tilesTaken == 9;
    }

    /**
     * Check if tile is empty or not
     *
     * @param row y position to check where tile is empty
     * @param col x position to check where tile is empty
     * @return true if its empty, else false
     */
    public boolean emptyTile(int row, int col) {
        if (board[row][col] == ' ')
            return true;
        else
            return false;
    }

    /**
     * Get the ith row as string
     *
     * @param i, integer for which row to return
     * @return the row contents as string
     */
    public String getRow(int i) {
        String rowString = "";
        for (Character c : board[i]) {
            rowString += c;
        }
        return rowString;
    }
    /**
     * Get the ath col as string
     *
     * @param a, integer for which col to return
     * @return the col contents as string
     */
    public String getCol(int a) {
        String colString = "";
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (j == a) {
                    colString += board[i][j];
                }
            }
        }
        return colString;
    }

    /**
     * Returns the DownLeft Diagonal as string
     *
     * @return downleft diagonal as string
     */
    public String getDiagRightLeft() {
        return "" + board[2][0] + board[1][1] + board[0][2];
    }

    /**
     * Returns the DownRight Diagonal as string
     *
     * @return downright diagonal as string
     */
    public String getDiagLeftRight() {
        String diagString = "";
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == j) {
                    diagString += board[i][j];
                }
            }
        }
        return diagString;
    }

    /**
     * Checks to see if all the characters in are all the same
     *
     * @param s the input string
     * @return true if all same, else false
     */
    private boolean allEqual(String s) {
        for (Character c : s.toCharArray()) {
            if (s.charAt(0) != c) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks to see if there is a winner by using the above functions
     *
     * @return the character that won, the ~ for a tie and else: `
     */
    public char isWinner() {
        char winner = '`';
        for (int i = 0; i < 3; i++) {
            if (allEqual(getRow(i))) {
                winner = getRow(i).charAt(0);
                return winner;
            } else if (allEqual(getCol(i))) {
                winner = getCol(i).charAt(0);
                return winner;
            }
        }

        if (allEqual(getDiagRightLeft())) {
            winner = getDiagRightLeft().charAt(0);
            return winner;
        } else if (allEqual(getDiagLeftRight())) {
            winner = getDiagRightLeft().charAt(0);
            return winner;
        } else if (isFull()){
            winner = '~';
            return winner;
        }

        return winner;
    }
}