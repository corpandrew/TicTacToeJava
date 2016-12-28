package com.corpa;

/**
 * Created by corpa on 12/27/16.
 */
public class Stats {

    private volatile static int winsP1 = 0;
    private volatile static int winsP2 = 0;
    private volatile static int ties = 0;
    private volatile static int gamesPlayed = 0;

    public void addWinP1() {
        winsP1++;
    }

    public void addWinP2() {
        winsP2++;
    }

    public void addTie() {
        ties++;
    }

    public void addGamePlayed(){
        gamesPlayed++;
    }

    public static int getWinsP1() {
        return winsP1;
    }

    public static int getWinsP2() {
        return winsP2;
    }

    public static int getTies() {
        return ties;
    }

    public String getStats() {
        String s = "";
        s +=    "Total Games: " + gamesPlayed +
                "\nPlayer 1 Wins: " + winsP1 +
                "\nPlayer 2 Wins: " + winsP2 +
                "\nTotal Ties: " + ties +
                "\nP1 Wins/Games = " + (double) winsP1/gamesPlayed +
                "\nP2 Wins/Games = " + (double) winsP2/gamesPlayed+
                "\nTies/Games = " + (double) ties/gamesPlayed;
        return s;
    }
}
