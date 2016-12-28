package com.corpa;

import java.util.Scanner;

/**
 * Created by corpa on 12/25/16.
 */

/**
 * Main Logic of the game
 */
public class Game {

    private Board board;
    private Player player;
    private Opponent opponent;
    private Opponent opponent2;
    private int gamesTied;
    private int amountGamesToPlay;
    private boolean printBoardOutput;
    private boolean printWinOutput;
    private Thread t;
    private Stats s;

    /**
     * Setup game for Player VS Opponent. Amount of games to play
     *
     * @param amountGamesToPlay
     */
    public Game(int amountGamesToPlay) {//For Player vs AI Game
        board = new Board();
        this.amountGamesToPlay = amountGamesToPlay;
        this.gamesTied = 0;
    }

    /**
     * Setup game for AI Game, includes printing of output (true or false)
     *
     * @param amountGamesToPlay, how many times to play game
     * @param printBoardOutput,  true to print output, false not to
     * @param printWinOutput,
     */
    public Game(int amountGamesToPlay, boolean printBoardOutput, boolean printWinOutput) {//For AI vs AI Game
        this.amountGamesToPlay = amountGamesToPlay;
        this.printBoardOutput = printBoardOutput;
        this.printWinOutput = printWinOutput;

        board = new Board();
        this.opponent = new Opponent('X', "Opponent1", board);
        this.opponent2 = new Opponent('O', "Opponent2", board);
        this.gamesTied = 0;
        s = new Stats();
    }

    /**
     * Game flow logic for the Player Vs Opponent game
     */
    public void playGame() {
        Scanner in = new Scanner(System.in);

        //Get inputs
        System.out.print("Enter your name: ");
        String playerName = in.next();

        System.out.print("Enter which letter you would like: ");
        char letter = in.next().charAt(0);

        player = new Player(letter, playerName);//create player object
        Opponent opponent;

        if (letter != 'O' && letter != 'o') {//make sure the letters are never the same
            opponent = new Opponent('O', "Opponent", board);// create opponent object
        } else {
            opponent = new Opponent('X', "Opponent", board);
        }

        //Print out player with there letter
        System.out.println(player.getName() + " Letter: " + player.getLetter());
        System.out.println(opponent.getName() + " Letter: " + opponent.getLetter() + "\n");

        int x, y;
        for (int i = 1; i <= amountGamesToPlay; i++) {// loop for how many games want to be played
            board.initializeBoard();// reset the board
            while (true) {

                //Get input for which tile
                System.out.println(board + "\n");

                opponent.setTilePicked(opponent.pickTile());
                printAndSetTileAI(opponent);

                System.out.println(board);

                if (checkWinner(player, opponent)) {
                    System.out.println(board);
                    break;
                }

                System.out.print("Enter the x and y of the tile 11 being top left and 33 being bottom right: ");// 11 = row 1 col 1

                player.setTilePicked(in.next());
                printAndSetTile(player);

                if (checkWinner(player, opponent))
                    break;
            }
            printScoreboard(player, opponent);
        }
    }

    public void playAIGame() {
        boolean firstGame = true;
        for (int i = 1; i <= amountGamesToPlay; i++) {
            board.initializeBoard();
            while (true) {
                if (firstGame && printBoardOutput)
                    System.out.println(board);
                firstGame = false;

                opponent.setTilePicked(opponent.pickTile());

                printAndSetTileAI(opponent);

                if (checkWinner(opponent, opponent2))
                    break;

                opponent2.setTilePicked(opponent2.pickTile());

                printAndSetTileAI(opponent2);

                if (checkWinner(opponent, opponent2))
                    break;
            }
            firstGame = false;
            s.addGamePlayed();
        }

        System.out.println(s.getStats());
        printScoreboard(opponent, opponent2);
    }

    public void playAIGameAsync() {
        t = new Thread(this::playAIGame);
        t.start();
    }

    private void printScoreboard(Player p1, Player p2) {
        System.out.println("Total Games Played: " + (p1.getWinCount() + p2.getWinCount() + gamesTied));
        System.out.println(p1.getName() + " has a total of " + p1.getWinCount() + " wins.");
        System.out.println(p2.getName() + " has a total of " + p2.getWinCount() + " wins.");
        System.out.println("Total tied games: " + gamesTied);
    }

    private void printAndSetTileAI(Player player) {
        if (printBoardOutput)
            System.out.println(player.getName() + " picked: Tile " + (player.getTilePicked().charAt(0) - 47) + ", " + (player.getTilePicked().charAt(1) - 47));
        board.setTile(player.getLetter(), (player.getTilePicked().charAt(0) - 47), (player.getTilePicked().charAt(1) - 47));
        if (printBoardOutput)
            System.out.println(board);
    }

    private void printAndSetTile(Player player) {
        if (printBoardOutput)
            System.out.println(player.getName() + " picked: Tile " + (player.getTilePicked().charAt(0) - 48) + ", " + (player.getTilePicked().charAt(1) - 48));
        System.out.println(player.getTilePicked().charAt(0) - 48 + ", " + player.getTilePicked().charAt(1));
        board.setTile(player.getLetter(), (player.getTilePicked().charAt(0) - 48), (player.getTilePicked().charAt(1) - 48));
        if (printBoardOutput)
            System.out.println(board);
    }

    private boolean checkWinner(Player p1, Player p2) {
        if (board.isWinner() == p1.getLetter()) {
            p1.addWin();
            if(printWinOutput)
                System.out.println(p1.getName() + " has won, it now has " + p1.getWinCount() + " wins.");
            s.addWinP1();
            return true;
        } else if (board.isWinner() == p2.getLetter()) {
            p2.addWin();
            if(printWinOutput)
                System.out.println(p2.getName() + " has won, it now has " + p2.getWinCount() + " wins.");
            s.addWinP2();
            return true;
        } else if (board.isFull()) {
            gamesTied++;
            s.addTie();
            if(printWinOutput)
                System.out.println("No one has one, game has tied! " + "Total tied games: " + gamesTied);
            return true;
        } else {
            return false;
        }
    }

    public Stats getStats() {
        return s;
    }

    //TODO change the way I use this all around.
    private class Tile {
        private int x;
        private int y;

        Tile(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        @Override
        public String toString() {
            return "" + x + y;
        }
    }
}
