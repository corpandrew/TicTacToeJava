package com.corpa;

import java.util.Random;

/**
 * Created by corpa on 12/24/16.
 */

/**
 * Adds the extra things an Opponent needs, is a derived class of Player
 */
public class Opponent extends Player {

    private Board board;

    private Random r;

    /**
     * Instantiates Opponent
     *
     * @param letter, letter to use for marking tile
     * @param name, name of Opponent
     * @param board, needs reference to the board
     */
    Opponent(char letter, String name, Board board) {
        super(letter, name);
        this.board = board;
        r = new Random();
    }

    //For now this is just going to be a random tile and play their letter here
    //Looking to make it an intelligent AI

    /**
     * Recursive Method
     * Generates 2 random numbers and checks if that tile is empty, if its not do it again
     * @return the tile picked as a string
     */
    public String pickTile() {
        String randomTile = "";
        r.setSeed(System.currentTimeMillis());
        randomTile += (r.nextInt(3));

        try {
            Thread.sleep(10);//clock time may be too close to change the seed
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        r.setSeed(System.currentTimeMillis());
        randomTile += (r.nextInt(3));
//        System.out.println(randomTile); Show how many times it tries to pick a tile
        if (board.emptyTile(randomTile.charAt(0) - 48, randomTile.charAt(1) - 48)) {
            return randomTile;
        } else {
            return pickTile();
        }
    }

}
