package com.corpa;


public class Main {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (int i = 1; i <= 1000; i++) {
            new Game(1000, false, false).playAIGameAsync();// plays 50 AI VS AI games, without output
        }
        long end = System.currentTimeMillis();

        double totalTime = (end-start)/1000000000;

        System.out.println(totalTime);
//        new Game(1, true).playAIGame();// plays 50 AI VS AI games, with output
//        new Game(5).playGame();
    }
}