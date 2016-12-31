package com.corpa;

/**
 * Created by corpa on 12/27/16.
 */
public class Tile {
    private int x;
    private int y;
    private int letter;

    Tile(int x, int y, int letter) {
        this.x = x;
        this.y = y;
        this.letter = letter;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public char getLetter() {
        switch (letter) {
            case -1:
                return 'X';
            case 1:
                return 'O';
            default:
                return ' ';
        }
    }

    public int getLetterNum() {
        if (letter == 'X')
            return -1;
        else if (letter == 'O')
            return 1;
        else
            return 0;
    }

    public boolean isX() {
        return letter == -1;
    }

    public boolean isY() {
        return letter == 1;
    }

    public boolean isEmpty() {
        return letter == 0;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setLetter(int letter) {
        this.letter = letter;
    }

    @Override
    public String toString() {
        return x + ", " + y;
    }
}