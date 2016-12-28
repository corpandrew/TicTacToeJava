package com.corpa;

/**
 * Holds the values for the Player
 */
public class Player {
    private int winCount;
    private char letter;
    private String name;
    private String tilePicked;

    /**
     * Instantiates the Player
     *
     * @param letter, letter the character to mark the tile
     * @param name,   name of the player
     */
    Player(char letter, String name) {
        this.letter = letter;
        this.name = name;
        this.winCount = 0;
    }

    /**
     * Adds a win
     */
    public void addWin() {
        this.winCount++;
    }

    /*
            Getters and Setters
     */

    public char getLetter() {
        return letter;
    }

    public int getWinCount() {
        return winCount;
    }

    public String getName() {
        return name;
    }

    public String getTilePicked() {
        return tilePicked;
    }

    public void setTilePicked(String tilePicked) {
        this.tilePicked = tilePicked;
    }

}
