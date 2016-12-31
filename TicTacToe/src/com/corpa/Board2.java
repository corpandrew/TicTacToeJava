package com.corpa;

import java.util.LinkedList;

/**
 * Created by corpa on 12/27/16.
 */

public class Board2 {

    private LinkedList<LinkedList<Tile>> board;
    private String boardString;
    private int tilesTaken;

    public Board2() {
        board = new LinkedList<>();
        initializeBoard();
        boardString = "";
    }


    public void initializeBoard() {
        board.clear();
        for (int i = 0; i < 3; i++) {
            LinkedList<Tile> row = new LinkedList<>();
            for (int j = 0; j < 3; j++) {
                row.add(new Tile(i, j, 0));
            }
            board.add(row);
        }
        tilesTaken = 0;
    }

    public void printBoard() {
        boardString = "";
        for (int i = 0; i < board.size(); i++) {
            LinkedList<Tile> row = board.get(i);
            for (int j = 0; j < 3; j++) {
                if (j != 2)
                    boardString += row.get(j).getLetter() + "\t|\t";
                else
                    boardString += row.get(j).getLetter();
            }

            boardString += "\n";
            for (int a = 0; a < 3; a++) {
                if (i != 2)
                    boardString += "------";
            }
            boardString += "\n";
        }
        System.out.println(boardString);
    }


    public boolean isEmptyTile(int row, int col) {
        return board.get(row).get(col).isEmpty();
    }

    public boolean isEmptyTile(Tile tile) {
        return isEmptyTile(tile.getX(), tile.getY());
    }

    public boolean setTile(int row, int col, int letter) {
        if (isEmptyTile(row, col)) {
            board.get(row).get(col).setLetter(letter);
            tilesTaken++;
            return true;
        } else {
            return false;
        }
    }

    public boolean isFull() {
        return tilesTaken == 9;
    }

    private int getNumber(char c) {
        if (c == 'X') {
            return -1;
        } else if (c == 'O') {
            return 1;
        } else {
            return 0;
        }
    }

    public int isWinner() {

        //horizontals

        if ((board.get(0).get(0).getLetter() == board.get(0).get(1).getLetter()) && (board.get(0).get(1).getLetter() == board.get(0).get(2).getLetter())) {
            return getNumber(board.get(0).get(0).getLetter());
        } else if (board.get(1).get(0).getLetter() == board.get(1).get(1).getLetter() && board.get(1).get(1).getLetter() == board.get(1).get(2).getLetter()) {
            return getNumber(board.get(1).get(0).getLetter());
        } else if (board.get(2).get(0).getLetter() == board.get(2).get(1).getLetter() && board.get(2).get(1).getLetter() == board.get(2).get(2).getLetter()) {
            return getNumber(board.get(2).get(0).getLetter());
        }

        //verticals

        else if (board.get(0).get(0).getLetter() == board.get(1).get(0).getLetter() && board.get(1).get(0).getLetter() == board.get(2).get(0).getLetter()) {
            return getNumber(board.get(0).get(0).getLetter());
        } else if (board.get(0).get(1).getLetter() == board.get(1).get(1).getLetter() && board.get(1).get(1).getLetter() == board.get(2).get(1).getLetter()) {
            return getNumber(board.get(0).get(1).getLetter());
        } else if (board.get(0).get(2).getLetter() == board.get(1).get(2).getLetter() && board.get(1).get(2).getLetter() == board.get(2).get(2).getLetter()) {
            return getNumber(board.get(0).get(2).getLetter());
        }

        //diagonals

        else if (board.get(0).get(0).getLetter() == board.get(1).get(1).getLetter() && board.get(1).get(1).getLetter() == board.get(2).get(2).getLetter()) {
            return getNumber(board.get(0).get(0).getLetter());
        } else if (board.get(0).get(2).getLetter() == board.get(1).get(1).getLetter() && board.get(1).get(1).getLetter() == board.get(2).get(0).getLetter()) {
            return getNumber(board.get(0).get(2).getLetter());
        } else {
            return 0;
        }
    }


}
