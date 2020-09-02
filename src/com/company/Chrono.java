package com.company;

public class Chrono implements Runnable {

    private static final int MILLIS_IN_SECOND = 1000;

    public final Object lock = new Object();
    private int periodsPassed = 0;
    private final int periodInSeconds;

    public Chrono(int periodInSeconds) {
        this.periodInSeconds = periodInSeconds;
    }

    public int getPeriodsPassed() {
        return periodsPassed;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(periodInSeconds * MILLIS_IN_SECOND);
                synchronized (this.lock) {
                    periodsPassed++;
                    this.lock.notifyAll();
                }
            } catch (InterruptedException e) {
                System.out.println("Chrono thread was interrupted ");
                e.printStackTrace();
            }
        }
    }
}