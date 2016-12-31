package com.corpa;


public class Main {

    public static void main(String[] args) {

        long start = System.nanoTime();


        int threadAmount = 10;
        int gamesToPlay = 10;
        int printAtProgress = 50;

        Stats s = new Stats(printAtProgress);

        Thread[] threads = new Thread[threadAmount];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Game(gamesToPlay, false, false, printAtProgress).playAIGameAsync();
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long end = System.nanoTime();

        double totalTime = (double) (end - start) / 1000000000;

        System.out.println(s.getStats());

        System.out.println("\nTotal Time Taken: " + totalTime + " seconds");

    }
}