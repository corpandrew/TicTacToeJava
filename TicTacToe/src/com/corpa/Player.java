package com.corpa;

/**
 * Holds the values for the Player
 */
public class Player {
    private int winCount;
    private char letter;
    private String name;
    private Tile tilePicked;

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

    public int getLetter() {
        if (letter == 'X')
            return -1;
        else if (letter == 'O')
            return 1;
        else
            return 0;
    }

    public char getLetterChar() {
        return letter;
    }

    public int getWinCount() {
        return winCount;
    }

    public String getName() {
        return name;
    }

    public Tile getTilePicked() {
        return tilePicked;
    }

    public void setTilePicked(Tile tilePicked) {
        this.tilePicked = tilePicked;
    }

    public void setTilePicked(String tilePicked) {
        this.tilePicked = new Tile(tilePicked.charAt(0) - 48, tilePicked.charAt(1) - 48, getLetter());
    }

}
