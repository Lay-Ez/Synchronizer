package com.company;

public class PeriodPrinter implements Runnable {

    private final Chrono chrono;

    public PeriodPrinter(Chrono chrono) {
        this.chrono = chrono;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (chrono.lock) {
                try {
                    chrono.lock.wait();
                    System.out.println("Periods passed: " + chrono.getPeriodsPassed());
                } catch (InterruptedException ignored) {}
            }

        }
    }
}